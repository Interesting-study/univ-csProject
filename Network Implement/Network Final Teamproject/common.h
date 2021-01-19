#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in_systm.h>
#include <netinet/in.h>
#include <netinet/ip.h>
//#define __FAVOR_BSD
#define __linux
#include <netinet/tcp.h>
#include <arpa/inet.h>
#include <sys/time.h>
#include <netdb.h>

#define MAXDATA 	1500
#define TCP_MAX_CONN 	100
#define TCP_WINDOW	8192

//#define DEBUG

struct tcpn_info {
	u_int8_t tcpi_state;
	u_int32_t tcpi_snd_mss;
	u_int32_t tcpi_rcv_mss;
	u_int32_t tcpi_sent_seq;
	u_int32_t tcpi_recv_seq;
	u_int32_t tcpi_last_ack_sent;
	u_int32_t tcpi_last_ack_recv;
	u_int32_t tcpi_local_window;
	u_int32_t tcpi_remote_window;

	u_int8_t tcpi_retransmits;
};

struct TCB {
	in_addr_t l_addr;
	u_int16_t l_port;
	in_addr_t r_addr;
	u_int16_t r_port;

	struct tcpn_info t_info;

	u_char *snd_buf;
	u_char *rcv_buf;

	u_char *udata;
};

struct packet_tcp {
  struct tcphdr tcp;
  u_char data[MAXDATA];
};

