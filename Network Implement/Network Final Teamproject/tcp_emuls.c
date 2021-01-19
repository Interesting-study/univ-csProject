/*
 * tcp_emuls.c
 * TCP Emulator (Passive TCP)
 * 컴파일 : make tcpems
 * 컴파일 : gcc -c tcp_emuls.c tcp_header.c tcp_tcb.c tcp_print.c
 * 링크   : gcc -o tcpems tcp_emuls.o tcp_header.o tcp_tcb.o tcp_print.o
 * 사용법 : tcpems port_number
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

	int	i,nbyte,rval, addrlen;
	int	sockfd,msgsock;
	struct  sockaddr_in	cli_addr, serv_addr;
	char sendbuf[TCP_MAXWIN],recvbuf[TCP_MAXWIN];
	char abuf[20], buf[1024];

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
	if(argc != 2) {
		// DEST_IP DEST_PORT 
    		fprintf(stderr, "usage: %s dst_port \n", argv[0]);
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

	addrlen = sizeof(serv_addr);
	/*
	 * Open a TCP socket (an Internet stream socket).
	 */

//socket(AF_INET, SOCK_STREAM, IPPROTO_TCP)
	if ((sockfd = socket(AF_INET, SOCK_STREAM, 0)) < 0){
		printf("server: cat't open stream socket");
		exit(1);
	}
	printf("SOCKET = %d\n",sockfd);

	/*
	 * Bind our local address so that the client can send to  us.
	 */

	bzero((char *) &serv_addr, addrlen);
	serv_addr.sin_family	  = AF_INET;
	serv_addr.sin_addr.s_addr = htonl(INADDR_ANY);
	serv_addr.sin_port	  = htons(atoi(argv[1])); 


	if (bind(sockfd, (struct sockaddr *) &serv_addr, sizeof(serv_addr)) < 0){
		printf("server: can't bind local address");
		exit(2);
	}

	listen(sockfd, 5);
	t_tcb[conn].t_info.tcpi_state = TCP_LISTEN;
	do {
		/* accept connection */
		printf("server: waiting ......\n");
		memset(&cli_addr, 0, addrlen);
		msgsock = accept(sockfd, (struct sockaddr *)&cli_addr, (int *)&addrlen);
/*
		msgsock = accept(sockfd, (struct sockaddr *)0, (int *)0);
*/
		if(msgsock == -1) {
			printf("server: accept error\n");
			exit(1);
		}
		else {
			printf("클라이언트 연결됨:%s\n",inet_ntoa(cli_addr.sin_addr));
			conn_count++;

			if(make_newtcb(conn, &cli_addr, &serv_addr) == -1)
				return -1;
			conn++;
			do {			
				printf("\nClient's connect() data.....\n");			
				memset(buf, 0, sizeof buf);
				/* read data */
				if((rval = read(msgsock, buf, 1024)) == -1) {
					printf("server: READ error\n");
					exit(1);
				}
				else if(t_tcb[conn-1].t_info.tcpi_state == TCP_LISTEN){
					printf("\n+--- Data Receive to %s. ---+\n", argv[1]);
					print_tcphdr((struct tcphdr *)&buf);
					
	  				memset((char *) &recvseg, 0, sizeof(sendseg));
					memcpy(&recvseg.tcp, buf, sizeof buf);

					if(recvseg.tcp.syn != 1 || recvseg.tcp.urg == 1 ||  recvseg.tcp.ack == 1 ||  recvseg.tcp.psh == 1 || recvseg.tcp.fin == 1 || recvseg.tcp.rst == 1){
						printf("server : TCP PACKET CODEBIT Error!!\n");
						continue;
					}
					
					t_tcb[conn-1].t_info.tcpi_state = TCP_SYN_RECV;
					
					recvseg.tcp.seq = ntohl(recvseg.tcp.seq);
					recvseg.tcp.ack_seq = ntohl(recvseg.tcp.ack_seq);
					
					t_tcb[conn-1].t_info.tcpi_recv_seq = recvseg.tcp.seq;
					t_tcb[conn-1].t_info.tcpi_last_ack_recv = recvseg.tcp.ack_seq;
							
					src_port = serv_addr.sin_port;
					dst_port = cli_addr.sin_port;
					datalen = 0;

					seq = time(0)/1234567890;
					ack = recvseg.tcp.seq+1;

					t_tcb[conn-1].t_info.tcpi_sent_seq = seq;
					t_tcb[conn-1].t_info.tcpi_last_ack_sent = ack;

					memset((char *) &sendseg, 0, sizeof(sendseg));

					make_tcp_header(ACK,&sendseg, src_port, dst_port, seq, ack, datalen );
					make_tcp_header(SYN,&sendseg, src_port, dst_port, seq, ack, datalen );

					t_tcb[conn-1].snd_buf = (char *)malloc(sizeof sendseg);
					memcpy(t_tcb[conn-1].snd_buf, &sendseg, sizeof sendseg);

					printf("SYN|ACK send to %s.\n", argv[1]);
					if(write(msgsock, &sendseg.tcp, sizeof sendseg.tcp) == -1){
						printf("client: data transfer error\n");
						exit(1);
					}	

					print_tcphdr((struct tcphdr *) &sendseg.tcp);
					print_tcb(conn-1);
				}
				else if(t_tcb[conn-1].t_info.tcpi_state == TCP_SYN_RECV){
					memset((char *)&recvseg, 0, sizeof(sendseg));
					memcpy(&recvseg.tcp, buf, sizeof buf);
					
					if(recvseg.tcp.syn == 1 || recvseg.tcp.urg == 1 ||  recvseg.tcp.ack != 1 ||  recvseg.tcp.psh == 1 || recvseg.tcp.fin == 1 || recvseg.tcp.rst == 1){
						printf("server : TCP PACKET CODEBIT Error!!\n");
						t_tcb[conn-1].t_info.tcpi_state == TCP_LISTEN;
						continue;
					}
					t_tcb[conn-1].t_info.tcpi_recv_seq = ntohl(recvseg.tcp.seq);
					t_tcb[conn-1].t_info.tcpi_last_ack_recv = ntohl(recvseg.tcp.ack_seq);

					printf("ACK recieve from %s.\n", argv[1]);
					print_tcphdr((struct tcphdr *)&recvseg.tcp);
					t_tcb[conn-1].t_info.tcpi_state = TCP_ESTABLISHED;
					printf("\nconnecting success.....!!\n");

					break;
				}
			} while(rval != 0);//rval은 read의 결과
		}
		do{
			/*데이터 전송과정*/
			printf("server: Data waiting(Transmission/PSH)....%s.\n",argv[1]);
			memset(buf,0, sizeof buf);
			/* read data */
			if((rval = read(msgsock, buf, 1024)) == -1){
				printf("server : Data Read Error!!\n");
				exit(1);
			}
			else if(t_tcb[conn-1].t_info.tcpi_state == TCP_ESTABLISHED){
				memset((char *)&recvseg, 0, sizeof sendseg);
				memcpy(&recvseg.tcp, buf, sizeof buf);
				//print_tcphdr((struct tcphdr *)buf);
				print_tcphdr((struct tcphdr *)&recvseg.tcp);
				
				//Code Bits 오류확인
				if(recvseg.tcp.syn == 1 || recvseg.tcp.urg == 1 ||  recvseg.tcp.ack == 1 ||  recvseg.tcp.psh != 1 || recvseg.tcp.fin == 1 || recvseg.tcp.rst == 1){
						printf("server : TCP PACKET CODEBIT Error!!(Transmission)\n");
						continue;
				}
				//클라이언트가 보내는 데이터 받기
				memset(buf,0, sizeof buf);
				if((rval = read(msgsock, buf, 1024)) == -1){
					printf("server : UData Read Error!!\n");
					exit(1);
				}
				t_tcb[conn-1].rcv_buf = (u_char *)buf;
					
				print_tcphdr((struct tcphdr *)&recvseg.tcp);
				printf("+-------------------------++-------------------------+\n");
				printf("| Recieve Data  :  %20s              |\n",(char *)t_tcb[conn-1].rcv_buf);
				printf("+-------------------------++-------------------------+\n\n");
					
				//헤더작성 및 tcp_tcb 내용수정
				t_tcb[conn-1].t_info.tcpi_recv_seq = ntohl(recvseg.tcp.seq);
				t_tcb[conn-1].t_info.tcpi_last_ack_recv = ntohl(recvseg.tcp.ack_seq);
							
				src_port = serv_addr.sin_port;
				dst_port = cli_addr.sin_port;
				datalen = 0;

				seq = ntohl(recvseg.tcp.ack_seq);
				ack = (ntohl(recvseg.tcp.seq)) + strlen(buf);

				t_tcb[conn-1].t_info.tcpi_sent_seq = seq;
				t_tcb[conn-1].t_info.tcpi_last_ack_sent = ack;

				memset((char *) &sendseg, 0, sizeof(sendseg));
				
				//PSH에 대한 ACK패킷 헤더 작성, window size 변화주기
				make_tcp_header(ACK,&sendseg, src_port, dst_port, seq, ack, datalen );
				sendseg.tcp.window =  htons((ntohs(sendseg.tcp.window))-strlen(buf));
				
				t_tcb[conn-1].t_info.tcpi_local_window =  ntohs(sendseg.tcp.window);
				t_tcb[conn-1].snd_buf = (char *)malloc(sizeof sendseg);
				memcpy(t_tcb[conn-1].snd_buf, &sendseg, sizeof sendseg);

				printf("ACK(about Client's  PSH) send to %s.\n", argv[1]);
				if(write(msgsock, &sendseg.tcp, sizeof sendseg.tcp) == -1){
					printf("client: data transfer error\n");
					exit(1);
				}	
				print_tcphdr((struct tcphdr *) &sendseg.tcp);
				print_tcb(conn-1); 
				break;
			}
		}while(1);
		do{
			/*접속 해제과정*/
			printf("server: Data waiting....(FIN)%s.\n",argv[1]);
			memset(buf,0, sizeof buf);
			/* read data */
			if((rval = read(msgsock, buf, 1024)) == -1){
				printf("server : Data READ Error!!(Half Close)\n");
				exit(1);
			}
			else if(t_tcb[conn-1].t_info.tcpi_state == TCP_ESTABLISHED){
				memset((char *) &recvseg, 0, sizeof sendseg);
				memcpy(&recvseg.tcp, buf, sizeof buf);


				if(recvseg.tcp.syn == 1 || recvseg.tcp.urg == 1 ||  recvseg.tcp.ack == 1 ||  recvseg.tcp.psh == 1 || recvseg.tcp.fin != 1 || recvseg.tcp.rst == 1){
					printf("server : CODEBITS error!!\n");
					continue;
				}
				t_tcb[conn-1].t_info.tcpi_recv_seq = ntohl(recvseg.tcp.seq);
                                t_tcb[conn-1].t_info.tcpi_last_ack_recv = ntohl(recvseg.tcp.ack_seq);

                                printf("FIN recive from %s.\n", argv[1]);
                                print_tcphdr((struct tcphdr *)&recvseg.tcp);
                                t_tcb[conn-1].t_info.tcpi_state = TCP_CLOSE_WAIT;//접속 종료 대기중

                                src_port = serv_addr.sin_port; // sendseg 세팅  FIN_ACK보내기
                                dst_port = cli_addr.sin_port;
                                datalen = 0;

                                seq = t_tcb[conn-1].t_info.tcpi_sent_seq+1;
                                ack = ntohl(recvseg.tcp.seq)+1;

                                t_tcb[conn-1].t_info.tcpi_sent_seq = seq;
                                t_tcb[conn-1].t_info.tcpi_last_ack_sent = ack;

                                memset((char *) &sendseg, 0, sizeof(sendseg));//tcp 헤더 설정

                                make_tcp_header(ACK & 0xff,&sendseg, src_port, dst_port, seq, ack, datalen );

                                t_tcb[conn-1].snd_buf = (char *)malloc(sizeof sendseg);
                                memcpy(t_tcb[conn-1].snd_buf, &sendseg, sizeof sendseg);

                                printf("ACK send to %s.\n", argv[1]);
                                if(write(msgsock, &sendseg.tcp, sizeof sendseg.tcp) == -1){
                                        printf("client: data trafer error\n");
                                        exit(1);
                                }

                                print_tcphdr((struct tcphdr *)&sendseg.tcp);
                                print_tcb(conn-1);

                                src_port = serv_addr.sin_port; // sendseg 세팅  FIN보내기
                                dst_port = cli_addr.sin_port;
                                datalen = 0;

                                seq = t_tcb[conn-1].t_info.tcpi_sent_seq+1;
                                ack = ntohl(recvseg.tcp.seq)+1;

                                t_tcb[conn-1].t_info.tcpi_sent_seq = seq;
                                t_tcb[conn-1].t_info.tcpi_last_ack_sent = ack;

                                memset((char *) &sendseg, 0, sizeof(sendseg));//tcp 헤더 설정

                                make_tcp_header(FIN & 0xff,&sendseg, src_port, dst_port, seq, ack, datalen );

                                t_tcb[conn-1].snd_buf = (char *)malloc(sizeof sendseg);
                                memcpy(t_tcb[conn-1].snd_buf, &sendseg, sizeof sendseg);

                                printf("FIN send to %s.\n", argv[1]);
                                if(write(msgsock, &sendseg.tcp, sizeof sendseg.tcp) == -1){
                                        printf("client: data trafer error\n");
                                        break;
                                }
                                t_tcb[conn-1].t_info.tcpi_state = TCP_LAST_ACK;//마지막 ACK을 기다리는 중

                                print_tcphdr((struct tcphdr *)&sendseg.tcp);
                                print_tcb(conn-1);
                        }
                        else if(t_tcb[conn-1].t_info.tcpi_state == TCP_LAST_ACK){   //FIN 보내고 마지막 ACK받기


                                 memset((char *) &recvseg, 0, sizeof(sendseg));
                                 memcpy(&recvseg.tcp,buf,sizeof buf);

                                if(recvseg.tcp.syn == 1 || recvseg.tcp.fin == 1 || recvseg.tcp.rst == 1 || recvseg.tcp.psh == 1 || recvseg.tcp.ack != 1 || recvseg.tcp.urg == 1){       //코드비트 예외처리 ack=1 경우만 무시
                                        printf("server: CODEBIT error\n");
                                        continue;
                                }

                                if(ntohl(recvseg.tcp.seq) != t_tcb[conn-1].t_info.tcpi_last_ack_sent || ntohl(recvseg.tcp.ack_seq) !=  (t_tcb[conn-1].t_info.tcpi_sent_seq+1)){               //seq, ack 오류 검출 구문
                                        printf("SEQ Number or ACK Number Error\n");
                                        continue;
                                }

                                t_tcb[conn-1].t_info.tcpi_recv_seq = ntohl(recvseg.tcp.seq);
                                t_tcb[conn-1].t_info.tcpi_last_ack_recv = ntohl(recvseg.tcp.ack_seq);

                                printf("FIN_ACK recive from %s.\n", argv[1]);
                                print_tcphdr((struct tcphdr *)&recvseg.tcp);		
			}						
			break;
		}while(1);
		close(msgsock);
	} while(1);

	/*	
	t_tcb[conn].t_info.tcpi_sent_seq = time(0);

 	 TCP 헤더 설정 
	  memset((char *) &sendseg, 0, sizeof(sendseg));

	  make_tcp_header(ACK|SYN & 0xff,&sendseg, src_port, dst_port, seq, ack, datalen );

	t_tcb[conn].snd_buf = (char *)malloc(sizeof sendseg);
	memcpy(t_tcb[conn].snd_buf, &sendseg, sizeof sendseg);

	printf("\n+ sendseg.tcp출력 +\n");	
	print_tcphdr((struct tcphdr *)&sendseg.tcp);

	printf("\n+ buf출력 +\n");
	print_tcphdr((struct tcphdr *)&buf);


 data transfer 
   SYN 패킷 송신 
printf("SYN send to %s.\n", argv[1]);
	if(write(sockfd, &sendseg, sizeof sendseg) == -1){
		printf("client: data trafer error\n");
		exit(1);
	}
	if(nbyte = read(sockfd, &recvseg, MAXDATA)< 0) {
		perror("read fail\n");
		exit(0);
	}
	
	print_tcphdr((struct tcphdr *)&buf); */

	close(sockfd);
	--conn_count;

  return EXIT_SUCCESS;
} /* main */
