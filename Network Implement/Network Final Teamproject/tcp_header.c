#include "common.h"

enum {URG = 1,ACK,PSH,RST,SYN,FIN};
extern struct TCB t_tcb[TCP_MAX_CONN]; /* TCB Control Blcok */

void make_tcp_header(u_short t_flag, struct packet_tcp *sendseg, u_int16_t src_port, u_int16_t dst_port,  u_int32_t seq, u_int32_t ack, int datalen);

u_short checksum(u_short *data, int len);

extern int conn;		   /* TCB index */ 
extern int conn_count;		   /* 접속 수 */ 

/*
 * make_tcp_header()
 */

void make_tcp_header(u_short t_flag, struct packet_tcp *packet, u_int16_t src_port, u_int16_t dst_port, u_int32_t seq, u_int32_t ack, int datalen)
{
  /* TCP 헤더 작성 */
  packet->tcp.seq   = htonl(seq);
  packet->tcp.ack_seq   = htonl(ack);
  packet->tcp.source = src_port;
  packet->tcp.dest = dst_port;
  packet->tcp.doff   = 5;
  packet->tcp.window   = htons(TCP_WINDOW);
  packet->tcp.urg_ptr   = 0;

  switch(t_flag) 
  {
	case URG:
		packet->tcp.urg = 1;
		break;
	case ACK:
		packet->tcp.ack = 1;
		break;
	case PSH:
		packet->tcp.psh = 1;
		break;
	case RST:
		packet->tcp.rst = 1;
		break;
	case SYN:
		packet->tcp.syn = 1;
		break;
	case FIN:
		packet->tcp.fin = 1;
		break;
	default :
		printf("Unknown TCP Segment (%d)\n",t_flag);
		break;
 }

  /* 체크섬의 계산 */
  packet->tcp.check = 0;
  packet->tcp.check = checksum((u_short *)(&(packet->tcp)),
				sizeof(packet->tcp)
				+ datalen);
} /* make_tcp_header */

/* 
 * u_short checksum(u_short *data, int len);
 * 기능
 * 	체크섬의 계산
 * 인수
 * 	u_short *data;   체크섬을 구하는 데이터
 * 	int len;         데이터 바이트 수
 * 리턴값
 * 	u_short		 체크섬의 값(복수치)
 */
u_short checksum(u_short *data, int len)
{
  u_long sum = 0; /* 구하는 체크섬 */

  /* 2바이트씩 가산 */
  for ( ; len > 1; len -= 2) {
    sum += *data++;
    if ( sum & 0x80000000)
      sum = (sum & 0xffff) + (sum >> 16);
  }

  /* 데이터 길이가 기수 바이트인 경우 처리 */
  if(len == 1) {
    u_short i = 0;
    *(u_char*)(&i) = *(u_char *)data;
    sum += i;
  }

  /* 항에 넘는 수는 되돌림 */
  while (sum >> 16)
    sum = (sum & 0xffff) + (sum >> 16);

  return (sum == 0xffff)?sum:~sum;
} /* checksum */

