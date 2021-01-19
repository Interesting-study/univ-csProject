/*
 * tcp_emulc.c
 * TCP Emulator (Active TCP)
 * 컴파일 : make tcpemc
 * 컴파일 : gcc -c tcp_emulc.c tcp_header.c tcp_tcb.c tcp_print.c
 * 링크   : gcc -o tcpemc tcp_emulc.o tcp_header.o tcp_tcb.o tcp_print.o
 * 사용법 : tcpemc remote_ip remote_port
 */

#include "common.h"
#include <string.h>

enum {URG = 1, ACK, PSH, RST, SYN, FIN};

struct TCB t_tcb[TCP_MAX_CONN]; /* TCB Control Blcok */

void init_tcb();
int make_newtcb(int, struct sockaddr_in *, struct sockaddr_in *);

extern void print_tcphdr(struct tcphdr *);
extern void print_tcb(int);

extern char *tcp_stoa(int state);
extern char *tcp_ftoa(int flag);
extern void make_tcp_header(u_short t_flag, struct packet_tcp *sendseg, u_int16_t src_port, u_int16_t dst_port,  u_int32_t seq, u_int32_t ack, int datalen);

extern u_short checksum(u_short *data, int len);

int conn  = 1;		   /* TCB index */ 
int conn_count = 1;	   /* 접속 수 */ 

int main(int argc, char *argv[])
{

	int			i,nbyte,sockfd;
	struct sockaddr_in	cli_addr, serv_addr;
	int    cliaddrlen;
	struct hostent *hp, *gethostbyname();
	char sendbuf[TCP_MAXWIN],recvbuf[TCP_MAXWIN];
	char abuf[20];

  	struct packet_tcp sendseg,recvseg;  /* 송신 TCP packet         */
	u_int32_t src_ip;            /* 시작점 IP 어드레스      */
	u_int32_t dst_ip;            /* 종착점 IP 어드레스      */
	u_int16_t src_port;        /* 시작점 호스트 번호      */
	u_int16_t dst_port;	   /* 종착점 호스트 번호      */
	u_int32_t seq;		   /* 시퀀스 번호             */
	u_int32_t ack; 		   /* 확인 응답 번호	      */
	int datalen;		   /* 데이터 길이	      */
	int on = 1;		   /* ON		      */

	/* 인수 체크 */
	if(argc != 3) {
		// DEST_IP DEST_PORT 
    		fprintf(stderr, "usage: %s dst_ip dst_port \n", argv[0]);
	    	exit(EXIT_FAILURE);
	}  	

	init_tcb();
	if(conn_count > TCP_MAX_CONN) 
	{
		printf("NO MORE CONNECTION\n");
		exit(EXIT_FAILURE);
	}
	else
		conn = conn%TCP_MAX_CONN;
	/*
	 * Open a TCP socket (an Internet stream socket).
	 */
	if ( (sockfd = socket(AF_INET, SOCK_STREAM, 0)) < 0) {
		printf("client: can't open stream socket");
		exit(1);
	}
printf("SOCKET CREATED sockfd = %d\n",sockfd);

	/*
	 * Fill in the structure "serv_addr" with the address of the
	 * server that we want to send to.
	 */
	  memset((char *) &serv_addr, 0, sizeof(struct sockaddr_in));

	serv_addr.sin_family	  = AF_INET;

	hp = gethostbyname(argv[1]);
	if (hp == (struct hostent *) 0) {
		fprintf(stderr, "%s: unknown host\n", argv[1]);
		exit(1);
	}

	 memcpy(&serv_addr.sin_addr, hp->h_addr, hp->h_length);

#ifdef DEBUG
	inet_pton(AF_INET,argv[1],&serv_addr.sin_addr);
	printf("inet_pton [%s]: 0x%x\n",argv[1],serv_addr.sin_addr);
	inet_ntop(AF_INET,&serv_addr.sin_addr,abuf,sizeof(abuf));
	printf("inet_ntop [0x%x]: %s\n",serv_addr.sin_addr,abuf);
#endif

	serv_addr.sin_port	  = htons(atoi(argv[2]));

	/* connect */
printf("CONNECTING.......\n");
	if(connect(sockfd, (struct sockaddr *)&serv_addr, sizeof serv_addr) == -1) 	
        {
		printf("client: connect error\n");
		exit(1);
	}

	conn_count++;
	cliaddrlen = sizeof(cli_addr);

	getsockname(sockfd, (struct sockaddr *)&cli_addr, &cliaddrlen);

	printf("SOCK(%d) : Local_IP(%s) Local_Port(%d)\n",
		sockfd, inet_ntoa(cli_addr.sin_addr),ntohs(cli_addr.sin_port));
	if(make_newtcb(conn, &cli_addr, &serv_addr) == -1) {
		printf("main(): make_newtcb error\n");
		return -1;
	}

	/*접속설정 3-way hand shake*/

	src_port = cli_addr.sin_port;
	dst_port = serv_addr.sin_port;
	seq = time(0)%4294967295;
	ack = 0;
	datalen = 0;

	t_tcb[conn].t_info.tcpi_sent_seq = seq;

 	/* TCP 헤더 설정 */
	memset((char *) &sendseg, 0, sizeof(sendseg));

	make_tcp_header(SYN, &sendseg, src_port, dst_port, seq, ack, datalen );

	t_tcb[conn].snd_buf = (char *)malloc(sizeof sendseg);
	memcpy(t_tcb[conn].snd_buf, &sendseg, sizeof sendseg);

	print_tcphdr(&sendseg.tcp);

	conn++;

	/* data transfer */
  /* SYN 패킷 송신 */
	printf("SYN send to %s.\n", argv[1]);
	if(write(sockfd, &sendseg.tcp, sizeof sendseg.tcp) == -1){
		printf("client: data trafer error\n");
		exit(1);
	}

	t_tcb[conn-1].t_info.tcpi_state = TCP_SYN_SENT;
	print_tcb(conn-1);

	if(nbyte = read(sockfd, &recvseg, MAXDATA)< 0) {
		perror("read fail\n");
		exit(0);
	}
	
	print_tcphdr(&recvseg.tcp);

	recvseg.tcp.seq = ntohl(recvseg.tcp.seq);
	recvseg.tcp.ack_seq = ntohl(recvseg.tcp.ack_seq);

	t_tcb[conn-1].t_info.tcpi_state = TCP_ESTABLISHED;	

	memset((char *) &sendseg, 0, sizeof(sendseg));
	seq = recvseg.tcp.ack_seq;
	ack = recvseg.tcp.seq+1;

	t_tcb[conn-1].t_info.tcpi_sent_seq = seq;
	t_tcb[conn-1].t_info.tcpi_last_ack_sent = ack;

	make_tcp_header(ACK, &sendseg, src_port, dst_port, seq, ack, datalen);

	printf("ACK send to... %s.\n", argv[1]);
	if(write(sockfd, &sendseg.tcp, sizeof sendseg.tcp) == -1){
		printf("Client: data transfer error!!\n");
		exit(1);
	}

	print_tcphdr(&sendseg.tcp);
	print_tcb(conn-1);

	/*데이터 전송*/
	/*1. PSH 헤더 전송*/
	printf("\nData transmission......\n");
	memset((char *)&sendseg, 0, sizeof(sendseg));	
	
	seq = time(0)%3146246342;
	ack = 1000;

	t_tcb[conn-1].t_info.tcpi_sent_seq = seq;
	t_tcb[conn-1].t_info.tcpi_last_ack_sent = ack;

	make_tcp_header(PSH, &sendseg, src_port, dst_port, seq, ack, datalen);

	printf("PSH send to...%s.\n", argv[1]);
	if(nbyte = write(sockfd, &sendseg.tcp, sizeof sendseg.tcp) == -1){
		printf("Client: data transfer error!!\n");
		exit(1);
	}
	print_tcphdr((struct tcphdr *)&sendseg.tcp);

	/*2.입력받은 데이터 전송*/
	printf("\n+=== Input data to Transfer ===+\n");
	fgets(abuf, sizeof(abuf), stdin);
	abuf[strlen(abuf) -1] = '\0';
	t_tcb[conn-1].snd_buf= (u_char *)abuf;
	if((nbyte = write(sockfd, t_tcb[conn-1].snd_buf, strlen(abuf))) == -1){
		printf("Client: Udata transfer error!!\n");
		exit(1);
	}

	print_tcb(conn-1);


	printf("ACK recive to...%s.\n", argv[1]);
	if(nbyte = read(sockfd, &recvseg, MAXDATA)< 0) {
		perror("read fail\n");
		exit(0);
	}
	print_tcphdr(&recvseg.tcp);
	print_tcb(conn-1);

	//FIN 전송	

	//Half Close(Graceful Close)
	printf("\nHalf Close......\n");
	memset((char *)&sendseg, 0, sizeof(sendseg));	
	
	seq = time(0)%4194964123;
	ack = time(0)%2413531467;

	t_tcb[conn-1].t_info.tcpi_sent_seq = seq;
	t_tcb[conn-1].t_info.tcpi_last_ack_sent = ack;

	make_tcp_header(FIN, &sendseg, src_port, dst_port, seq, ack, datalen);

	printf("FIN send to...%s.\n", argv[1]);
	if(nbyte = write(sockfd, &sendseg.tcp, sizeof sendseg.tcp) == -1){
		printf("Client: data transfer error!!\n");
		exit(1);
	}
	t_tcb[conn-1].t_info.tcpi_state = TCP_TIME_WAIT;
	print_tcphdr((struct tcphdr *)&sendseg.tcp);
	print_tcb(conn-1);

	//FIN-ACK후, 다시 FIN 재전송
	memset((char *) &recvseg, 0, sizeof(sendseg));
	if(nbyte = read(sockfd, &recvseg, MAXDATA)< 0) {
		perror("(ACK)read fail\n");
		exit(0);
	}
	print_tcphdr(&recvseg.tcp);
	recvseg.tcp.seq = ntohl(recvseg.tcp.seq);
	recvseg.tcp.ack_seq = ntohl(recvseg.tcp.ack_seq);
	t_tcb[conn-1].t_info.tcpi_state = TCP_TIME_WAIT;
	

	memset((char *) &recvseg, 0, sizeof(sendseg));
	if(nbyte = read(sockfd, &recvseg, MAXDATA)< 0) {
		perror("(FIN)read fail\n");
		exit(0);
	}
	
	print_tcphdr(&recvseg.tcp);
	recvseg.tcp.seq = ntohl(recvseg.tcp.seq);
	recvseg.tcp.ack_seq = ntohl(recvseg.tcp.ack_seq);

	memset((char *) &recvseg, 0, sizeof(sendseg));
	seq = recvseg.tcp.ack_seq;
	ack = recvseg.tcp.seq + 1;

	t_tcb[conn-1].t_info.tcpi_sent_seq = seq;
	t_tcb[conn-1].t_info.tcpi_last_ack_sent = ack;
	
	make_tcp_header(ACK, &sendseg, src_port, dst_port, seq, ack, datalen);
	printf("FIN_ACK send to %s.\n", argv[1]);
	if(nbyte = write(sockfd, &sendseg.tcp, sizeof sendseg.tcp) == -1){
		printf("Client: data transfer error!!\n");
		exit(1);
	}
	print_tcphdr(&sendseg.tcp);
	print_tcb(conn-1);	
	t_tcb[conn-1].t_info.tcpi_state = TCP_CLOSE;
	printf("\n\nDisconect\n\n");	

	close(sockfd);
	--conn_count;

  return EXIT_SUCCESS;
} /* main */

