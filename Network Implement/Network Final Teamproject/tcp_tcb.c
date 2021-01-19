#include "common.h"

extern struct TCB t_tcb[TCP_MAX_CONN];

void init_tcb();
int make_newtcb(int, struct sockaddr_in *, struct sockaddr_in *);

/*
 * int_tcb()
 * TCP Control Blcok 초기화
 */

void init_tcb()
{
	int i, pure_ip, mss;

	pure_ip = IP_MSS - sizeof(struct tcphdr);
	if(TCP_MSS < pure_ip)
		mss = TCP_MSS;
	else
		mss = pure_ip;

	for(i=0;i<TCP_MAX_CONN;i++)
	{
		t_tcb[i].l_addr = 0;
		t_tcb[i].l_port = 0;
		t_tcb[i].r_addr = 0;
		t_tcb[i].r_port = 0;
		t_tcb[i].t_info.tcpi_state = TCP_CLOSE;
		t_tcb[i].t_info.tcpi_snd_mss = mss;
		t_tcb[i].t_info.tcpi_rcv_mss = 0;
		t_tcb[i].t_info.tcpi_sent_seq = 0;
		t_tcb[i].t_info.tcpi_recv_seq = 0;
		t_tcb[i].t_info.tcpi_last_ack_sent = 0;
		t_tcb[i].t_info.tcpi_last_ack_recv = 0;
		t_tcb[i].t_info.tcpi_local_window = TCP_WINDOW;
		t_tcb[i].t_info.tcpi_remote_window = 0;
		t_tcb[i].t_info.tcpi_retransmits = 0;
		t_tcb[i].snd_buf = NULL;
		t_tcb[i].rcv_buf = NULL;
		t_tcb[i].udata = NULL;
	}
} /* init_tcb */

/*
 * make_newtcb()
 * 새로운 TCP Control Blcok을 생성
 */

int make_newtcb(n_conn, cli_addr, serv_addr)
	int n_conn;
	struct sockaddr_in *cli_addr, *serv_addr;
{

	int i,alen,plen;

	alen = sizeof(cli_addr->sin_addr);
	plen = sizeof(cli_addr->sin_port);
	for(i=0;i<TCP_MAX_CONN;i++)
	{
		if(!bcmp(&cli_addr->sin_addr,&t_tcb[i].l_addr,alen) &&
		   !bcmp(&cli_addr->sin_port,&t_tcb[i].l_port,plen) &&
		   !bcmp(&serv_addr->sin_addr,&t_tcb[i].r_addr,alen) &&
		   !bcmp(&serv_addr->sin_port,&t_tcb[i].r_port,plen))
		{
			printf("Connection Existed\n");
			return (-1);
		}
		else
			continue;   
	}
	
	memcpy(&t_tcb[n_conn].l_addr,&cli_addr->sin_addr, alen);
	memcpy(&t_tcb[n_conn].l_port,&cli_addr->sin_port, plen);
	memcpy(&t_tcb[n_conn].r_addr,&serv_addr->sin_addr, alen);
	memcpy(&t_tcb[n_conn].r_port,&serv_addr->sin_port, plen);

#ifdef DEBUG
	{
	char abuf[20];
	printf("make_newtcb(%d) : %x\n",n_conn,t_tcb[n_conn].l_addr);
	inet_ntop(AF_INET,(struct in_addr *)&t_tcb[n_conn].l_addr,abuf,sizeof(abuf));
	printf("make_newtcb(%d) : Local IP : %s\n",n_conn,abuf);
	}
#endif
	
	return 0;
} /* make_newtcb */
