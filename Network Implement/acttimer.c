#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <signal.h>
#include <sys/ioctl.h>
#include <sys/errno.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/time.h>

#define REQUEST 1
#define STOP 2
#define SUCCESS 0
#define FAILURE -1

#define RTIMER 1
#define WTIMER 2
#define KTIMER 3
#define ATIMER 4
#define FTIMER 5

#define PROTOCOL_MACHINE 1
#define KERNEL_TIMER 2

struct delta{
	int machp;
	char *request;
	int cep_id;
	int delta_name;
	int delta_time;
	long ctime;
	struct delta *left;
	struct delta *right;
};

struct delta *delta_q;
struct delta *delta_head = NULL;
void sig(), timer(), timerrsp();
int delta_insert(), delta_delete();
//long ctime();
void ERRstatus();
int ETIMrsp();

struct sigaction act, sact, rspact;
sigset_t masksets, smasksets, rspmasksets;

int main(argc, argv)
int argc;
char *argv[];
{
	int num, i;
	long xtime, ctime;

	if(argc < 2){
		printf("사용법: timer scenario_number\n");
		exit(0);
	}

	num = atoi(argv[1]);
	switch(num){
	case 1:
		timer(1,"RTIMER17",REQUEST,1, 17);	
		timer(1,"WTIMER27",REQUEST,2, 27);	
		timer(1,"KTIMER28",REQUEST,3, 28);	
		timer(1,"ATIMER32",REQUEST,4, 32);
		break;
	case 2:
		timer(1,"RTIMER18",REQUEST,1, 18);	
		sleep(1);
		timer(1,"WTIMER6",REQUEST,2, 6);	
		sleep(1);
		timer(1,"KTIMER10",REQUEST,3, 10);	
		sleep(1);
		timer(1,"KTIMER10",STOP,3, 10);
		break;
	default: 
		printf("알 수 없는 시나리오 번호 : %d\n", num);
		break;	
	}
	for(;;) ;

	/*
	 for(i=1; i<3; i++){
	 	printf("Input Test Scenario Number : ");
		scanf("%d". &num);
		getchar();
		if(num == -1) exit(0);

		
	switch(num){
	case 1:
		timer(1,"RTIMER17",REQUEST,1, 17);	
		timer(1,"WTIMER27",REQUEST,2, 27);	
		timer(1,"KTIMER28",REQUEST,3, 28);	
		timer(1,"ATIMER32",REQUEST,4, 32);
		break;
	case 2:
		timer(1,"RTIMER18",REQUEST,1, 18);	
		sleep(1);
		timer(1,"WTIMER6",REQUEST,2, 6);	
		sleep(1);
		timer(1,"KTIMER10",REQUEST,3, 10);	
		sleep(1);
		timer(1,"KTIMER10",STOP,3, 10);
		break;
	default: 
		break;	
	}
	 }
	 printf("main() : i = %d\n", i);
	 */
}
void timer(machp, request, service, timer_type, quantum)
int machp;
char *request;
int service;
int timer_type;
int quantum;
{
	static char *me = "timer";
	int error;

	printf("********** %s **********\n", me);
	
	sigfillset(&act.sa_mask);
	sigfillset(&sact.sa_mask);
	sigfillset(&rspact.sa_mask);

	act.sa_handler = SIG_IGN;
	act.sa_flags = 0;
	sigaction(SIGHUP, &act,  NULL);
	sigaction(SIGINT, &act, NULL);
	sigaction(SIGQUIT, &act, NULL);

	sact.sa_handler = sig;
	sact.sa_flags = 0;
	sigaction(SIGFPE, &sact, NULL);
	sigaction(SIGPIPE, &sact, NULL);

	rspact.sa_handler = timerrsp;
	rspact.sa_flags = 0;

	if(delta_head == NULL){
		delta_head = (struct delta *)malloc(sizeof(struct delta));
		delta_head->right = NULL;
		delta_head->left = delta_head;
	}

	switch(service){
	case REQUEST : 
		delta_q = (struct delta *)malloc(sizeof(struct delta));
		delta_q->machp = machp;
		delta_q->request = (char *)malloc(20);
		strncpy(delta_q->request, request, strlen(request));
		delta_q->delta_name = timer_type;
		delta_insert(delta_q, quantum);

		if(delta_head->right != NULL){
			alarm(delta_head->right->delta_name);
			sigaction(SIGALRM, &rspact, NULL);
		}
		else
			alarm(0);
		break;
	case STOP :
		delta_delete(PROTOCOL_MACHINE, machp, timer_type);
		if(delta_head->right == NULL)
			alarm(0);
		else{
			alarm(delta_head->right->delta_time);
			sigaction(SIGALRM, &rspact, NULL);
		}
		break;
	default : 
		printf("%s: NO service \n", me);
		return;
	}
}

void timerrsp()
{
	static char *me = "timerrsp";
	int machp;
	int type;
	char *request;

	printf("***** %s *****\n", me);
	machp = delta_head->right->machp;
	type = delta_head->right->delta_name;
	request = (char *)malloc(20);
	strncpy(request, delta_head->right->request, strlen(delta_head->right->request));
	delta_delete(KERNEL_TIMER, delta_head->right->machp, delta_head->right->delta_name);
	ETIMrsp(machp, request, type);
	if(delta_head->right != NULL){
		alarm(delta_head->right->delta_time);
		sigaction(SIGALRM, &rspact, NULL);
	}
	else
		alarm(0);
}

void sig(n)
int n;
{
	static char *arg[] = {"%s", "sig"};
	static char *me = "sig";
	printf("signal = %d\n", me, n);

	//signal(n, sig);
}

void ERRstatus()
{
}

int delta_insert(delta_q, quantum)
struct delta *delta_q;
int quantum;
{
	static char *me = "delta_insert";
	struct delta *next;
	int total;
	int elapse_time;

	printf("****** %s : machp(%x), %s\n", me, delta_q->machp, delta_q->request);
	if(delta_head->right == NULL){
		delta_q->right = NULL;
		delta_q->left = delta_head;
		delta_q->delta_time = quantum;
		delta_q->ctime = time(0);
		delta_head->right = delta_q;
		printf("%s:ctime=%x\n", me, delta_q->ctime);
		return (SUCCESS);
	}

	delta_q->ctime = time(0);
	elapse_time = (delta_q->ctime) - (delta_head->right->ctime);
	next = delta_head->right;
	next->delta_time -= elapse_time;

	printf("%s:ctime1=%x etime1=%d ntime1 = %d\n, me, delta_q->ctime, elapse_time, next->delta_time");

	while(next->right != NULL && next->delta_time < quantum){
		next->ctime += elapse_time;
		quantum -= next->delta_time;
		next = next->right;
		printf("%s:quantum1=%d, me, quantum");
	}

	if(next->right == NULL && next->delta_time <quantum){
		next->ctime += elapse_time;
		delta_q->right = NULL;
		delta_q->left = next;
		delta_q->delta_time = quantum - next->delta_time;
		next->right = delta_q;
		printf("%s=ctime2=%x	rtime2=%d	dtime2=%d \n", me, delta_q->ctime, elapse_time, delta_q->delta_time);
	}
	else{
		delta_q->right = next;
		delta_q->left = next->left;
		delta_q->delta_time = quantum;
		(next->left)->right = delta_q;
		next->left = delta_q;
		next->delta_time -= quantum;
		printf("%s:ctime3=%x	rtime3=%d	dtime3=%d	ntime3=%d\n",me,delta_q->ctime, elapse_time, delta_q->delta_time, next->delta_time);

		next->ctime = delta_q->ctime;
		while(next->right != NULL){
			next = next->right;
			next->ctime = delta_q->ctime;
		}
	}
	return(SUCCESS);
}

int delta_delete(from, id, timer_type)
int from;
int id;
int timer_type;
{
	static char *me = "delta_delete";
	struct delta *next, *firstp;
	int elapsed_time;

	printf("*** %s : Where (%d) machp (%x) Timer_Type (%d) \n", me, from, id, timer_type);
	
	next = firstp = delta_head->right;

	if(next->machp == id && next->delta_name == timer_type)
	{
		ETIMrsp(next->machp, next->request, next->delta_name);
		if(next->right == NULL){
			printf("첫번째가 마지막임\n");
			delta_head->right = NULL;
		}
		else{
			printf("첫번째가 마지막이 아님\n");
			(next->right)->ctime = time(0);
			elapsed_time = (next->right)->ctime - next->ctime;
			(next->right)->delta_time += (next->delta_time - elapsed_time);
			next= next->right;
			while(next->right != NULL){
				next->right->ctime = next->ctime;
				next = next->right;
			}
			(firstp->left)->right = firstp->right;
			(firstp->right)->left = firstp->left;
		}
		free(firstp);
		return(SUCCESS);
	}

	next = next->right;
	for(;next!=NULL; next = next->right){
		if(next->machp == id && next->delta_name == timer_type){
			ETIMrsp(next->machp, next->request, next->delta_time);
			if(next->right!= NULL){
				(next->left)->right = next->right;
				(next->right)->left = next->left;
				(next->right)->delta_time += next->delta_time;
			}
			else
				(next->left)->right = NULL;
			free(next);
			return(SUCCESS);
		}
	}
	return(FAILURE);
}

int ETIMrsp(machp, request, type)
int machp;
char *request;
int type;
{
	printf("ETIMrsp: machp->%d, %s, TYPE->%d\n", machp, request, type);
	return 8;
}
