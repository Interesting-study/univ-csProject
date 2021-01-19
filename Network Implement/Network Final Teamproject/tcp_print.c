#include "common.h"

extern struct TCB t_tcb[TCP_MAX_CONN];

void print_tcphdr(struct tcphdr *);
char *tcp_ftoa(int);
char *tcp_stoa(int);
void print_tcb(int);

/*
 * print_tcphdr()
 * TCP 헤더 값 출력
 */

void print_tcphdr(struct tcphdr *tcp)
{
	char *ptr;

	ptr = (char *)tcp;
	printf("Protocol TCP\n");
	printf("+----------------------------------------------------+\n");
	printf("| Source Port: 	     %5u| Destination Port:	%5u|\n",
			ntohs(tcp->source), ntohs(tcp->dest));
	printf("+----------------------------------------------------+\n");
	printf("| Sequence Number:  	                   %10lu|\n",
		(u_long)ntohl(tcp->seq));
	printf("+----------------------------------------------------+\n");
	printf("| Acknowledgemenet Number: 	  	   %10lu|\n",
		(u_long)ntohl(tcp->ack_seq));
	printf("+------+---------+--------+-------------------------+\n");
	printf("| DO:%2u| Reserved|F:%6s| Window Size: 	%5u|\n",
		tcp->doff, tcp_ftoa(*(ptr + 13)&0xff), ntohs(tcp->window));
	printf("+------+---------+--------+--------------------------+\n");
	printf("| Checksum:          %5u| Urgent Pointer: 	%5u|\n",
		ntohs(tcp->check), ntohs(tcp->urg_ptr));
	printf("+------+---------+--------+--------------------------+\n");
	printf("\n");
} /* functino print_tcp ends */

/*
 * tcp_ftoa()
 * TCP Flag to ASCII Character
 */

char *tcp_ftoa(int flag)
{
	static int f[] = {'U','A','P','R','S','F'};
	static 	char str[17];
	u_int mask = 1 << 5;
	int i;

	for( i=0 ; i < 6 ; i++){
		if(((flag<<i) & mask) != 0)
			str[i] = f[i];
		else
		str[i] = '0';
	} // for ends
	str[i] = '\0';
	return str;
} /* tcp_ftoa */

/*
 * tcp_stoa()
 * TCP 상태 출력
 */

char *tcp_stoa(int state)
{
	static char s[11][30] = {{"TCP_ESTABLISHED"},{"TCP_SYN_SENT"},\
		{"TCP_SYN_RECV"},{"TCP_FIN_WAIT_1"},{"TCP_FIN_WAIT2"},\
		{"TCP_TIME_WAIT"},{"TCP_CLOSE"},{"TCP_CLOSE_WAIT"},\
		{"TCP_LAST_ACK"},{"TCP_LISTEN"},{"TCP_CLOSING"}};

	if(state <1 || state > 11) {
		printf("Unknown TCP State (%d) \n",state);
		return("EXIT_FAILURE");
	}
	return (&s[state-1]);
}

/*
 * print_tcb()
 * TCP Control Block 출력
 */

void print_tcb(int i)
{
	char abuf[20];

	printf("\n");
	printf("+-------------------------+\n");
	printf("TCP CONTROL BLOCK(%d)      | \n",i);
	printf("+-------------------------+\n");

	inet_ntop(AF_INET,(struct in_addr *)&t_tcb[i].l_addr,abuf,sizeof(abuf));
	printf("Local IP      : %s\n",abuf);
	printf("Local Port    : %d\n",ntohs(t_tcb[i].l_port));
	inet_ntop(AF_INET,(struct in_addr *)&t_tcb[i].r_addr,abuf,sizeof(abuf));
	printf("Remote IP     : %s\n",abuf);
	printf("Remote Port   : %d\n",ntohs(t_tcb[i].r_port));
	printf("state         : %s\n",tcp_stoa(t_tcb[i].t_info.tcpi_state)); 
	printf("snd mss       : %d\n",t_tcb[i].t_info.tcpi_snd_mss);
	printf("rcv mss       : %d\n",t_tcb[i].t_info.tcpi_rcv_mss);
	printf("sent_seq      : %d\n",t_tcb[i].t_info.tcpi_sent_seq);
	printf("recv_seq      : %d\n",t_tcb[i].t_info.tcpi_recv_seq);
	printf("last-ack_sent : %d\n",t_tcb[i].t_info.tcpi_last_ack_sent);
	printf("local_window  : %d\n",t_tcb[i].t_info.tcpi_local_window);
	printf("remote_window : %d\n",t_tcb[i].t_info.tcpi_remote_window);
	printf("retransmits   : %d\n",t_tcb[i].t_info.tcpi_retransmits);
	printf("snd_buf       : %s\n",t_tcb[i].snd_buf);
	printf("rcv_buf       : %s\n",t_tcb[i].rcv_buf);
	printf("udata         : %s\n",t_tcb[i].udata);
}
