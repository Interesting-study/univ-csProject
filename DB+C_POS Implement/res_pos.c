#include <stdio.h>
#include <errno.h>
#include <mysql.h>
#include <stdlib.h>
#include <string.h>

#define TABLE_COUNT 10

char q_useDB[] = "use 1505_db";
char q_select[] = "select * from ";
char q_billSum[] = "select sum(Bill_";
char q_billSumDate[] = "select Bill_Date, sum(Bill_";
char q_searchStock[] = "select T_Foods.Foods_ID, Foods_Name, remained_Foods_Amount from T_Foods natural join vw_remained_Foods where T_Foods.Foods_ID = vw_remained_Foods.Foods_ID";
char q_update_Em[] = "update T_Employee set Em_TypeID = ";
char q_update_Menu[] = "update T_Menu set Menu_";
char q_delete[] = "delete from ";
char q_insert[] = "insert into ";
char q_delete_Em[] = "T_Employee where Em_ID = ";
char q_final[1000] = {0,};
char q_searchEm[] = "select * from T_Employee";
char q_printMenu[] = "select * from T_Menu_Type natural join T_Menu where T_Menu_Type.Menu_TypeID = T_Menu.Menu_TypeID ";
char q_menuType[] = "select * from T_Menu_Type ";
char q_menuID_menuType[] = "select Menu_TypeID , Menu_ID from T_Menu order by Menu_TypeID ";
char q_get_next_BuyingID[] = "select max(Buying_ID)+1 from T_Buying where Buying_Date = ";
char q_table_status[] = "selet Bill_TableID from T_Bill where Bill_Date = ";
char q_get_next_Bill_Idx[] = "select max(Bill_Idx)+1 from T_Bill_Item where Bill_Date = ";
char q_get_next_Bill_ItemID[] = "select max(Bill_ItemID)+1 from T_Bill_Item where Bill_Date = ";
char q_get_menuPrice[] = "select Menu_Price from T_Menu where Menu_TypeID = ";
char q_search_order_date[]= " select Bill_Date, Bill_Idx, Bill_TableID, Bill_Cash+Bill_Card+Bill_Balancedue as tot_pay, Menu_Type, Menu_ID, Bill_Employee, Menu_Price, Menu_Count from T_Bill natural join T_Bill_Item where T_Bill.Bill_Date = T_Bill_Item.Bill_Date and T_Bill.Bill_Idx = T_Bill_Item.Bill_Idx";
char q_table_present_status[] = "select Status from T_Res_table ";
char q_print_em[] = "select * from T_Employee where Em_TypeID = 1 ";
char q_print_cust[] = "select * from T_Customer ";
char q_tb_change_using[] = "update T_Res_table set Status = 1 where Table_ID = ";
char q_present_order[] = "select Bill_Date, Bill_Idx, Bill_TableID from T_Bill order by Bill_Date, Bill_Idx ";
char q_get_part_pay[] = "select sum(Menu_Price*Menu_Count) as final_pay from T_Bill_Item where Bill_Date = ";
char q_get_part_pay_group[] = " group by Bill_Date, Bill_Idx";
char q_print_tot_pay[] = "select Bill_Date, Bill_Idx, sum(Menu_Price*Menu_Count) as final_pay from T_Bill_Item group by Bill_Date, Bill_Idx";
char q_update_pay_cash[] = "update T_Bill set Bill_Cash = ";
char q_update_pay_card[] = "update T_Bill set Bill_Card = ";
char q_update_pay_balancedue[] = "update T_Bill set Bill_Balancedue = ";
char q_tbNum[10];
char q_bill_Idx[10];
char q_bill_ItemID[10];
char q_emID[10];
char q_custID[10];

MYSQL mysql;
MYSQL_RES *res;
MYSQL_ROW row;
MYSQL_FIELD *field;

void itoa(int , char *);
void use_Database(char *);
void sql_Error();
void print_Connecting();
void change_table_using(char *, char *);
void main_Menu();
void print_Intro();
void print_TableList();
void print_MainMenu();
void print_returnMenu1();
void print_returnMenu2();
int input_Menu();
int input_MenuError();
void print_odMenu();
void print_End();
void set_tb_status(int *);

void print_tbDetails();
void table_Details();
void order_Status();
void order_Add();
void order_Cancle();
void tb_Payment();
int input_tbNum();
void input_order(int *, int *, int *);
void print_tot_order();
void print_date_order();
void get_menuPrice();
int get_tot_pay(char *, char *);

void print_menuSetup();
void menu_Setup();
void menu_Status();
void menu_Add();
void menu_Change();
void menu_used_Change();
void input_infoMenu(char *, char *,char *,char *);
void input_infoMenu_least(char *, char *);
void input_infoMenu_name(char *);
void input_infoMenu_price(char *);
void menu_not_used();
void menu_used();

void print_buyingSetup();
void buying_Setup();
void pay_only_cash();
void pay_only_card();
void pay_only_balancedue();
void total_Pay();
void print_searchMenu();
void print_Tbalancedue();
void print_Pbalancedue();
void print_Tcard();
void print_Pcard();
void print_Tcash();
void print_Pcash();
void date_for_totalPay();
void input_Date(char *);
void print_TotalPay();

void print_stockSetup();
void stock_Setup();
void stock_Status();
void stock_Buying();
void print_stockStatusMenu();
void stockTotal();
void stockPart();
void print_Trader();
void get_next_ID(char *, char *);

void print_employeeSetup();
void employee_Setup();
void employee_status();
void employee_add();
void employeeType_change();
void employee_delete();

void recipe_Add();

void print_Usequery(char *);
void Usequery(char *);
void append_query(char *, char *);
void append_query_str(char *, char *);

int main()
{
	use_Database(q_useDB);

	main_Menu();

	return 0;
}
void sql_Error()
{
	fprintf(stderr,"%s\n",mysql_error(&mysql));
	exit(1);
}
void use_Database(char *q_useDB)
{
	mysql_init(&mysql);

	if(!mysql_real_connect(&mysql,"localhost","db1505", "zjarhdrhk15", "1505_db",0,NULL,0))
		sql_Error(&mysql);

	if(mysql_real_query(&mysql, q_useDB, strlen(q_useDB)))
		sql_Error(mysql);

	print_Connecting();
}

void print_Connecting()
{
	printf("\t\t\tDataBase Connecting......\n");
	printf("\t\t\tConnecting Success!!\n");
}
void main_Menu()
{
	int MainMenu;

	print_Intro();
	print_TableList();
	print_MainMenu();
	MainMenu = input_Menu();

	while (1) {
		if (MainMenu >= 0 && MainMenu <= 6)
			break;
		else
			MainMenu = input_MenuError();
	}

	if (MainMenu == 0) {
		print_End();
		exit(1);
	}

	puts(" ----------------------------------------------------------------");

	switch (MainMenu) {
	case 1:
		table_Details();
		break;
	case 2:
		menu_Setup();
		break;
	case 3:
		buying_Setup();
		break;
	case 4:
		stock_Setup();
		break;
	case 5:
		employee_Setup();
		break;
	case 6:
		recipe_Add();
	}
}
void print_Intro()
{
	puts(" ----------------------------------------------------------------");
	printf("\n\t\t [ (주) 안양대 SpeedPos Program ]\n");
	printf("\t\t  [어서오세요. 굿모닝김밥입니다.]\n\n");
	puts(" ----------------------------------------------------------------");
}
void print_MainMenu()
{
	puts(" ----------------------------------------------------------------");
	puts("\t\t\tMain Menu\n\n");
	printf(" 1. 테이블 상세\t\t[주문검색, 주문추가, 주문전체취소, 최종결제]\n\n");
	printf(" 2. 메뉴 관련설정\t[메뉴현황, 메뉴추가, 메뉴정보변경, 메뉴상태변경]\n\n");
	printf(" 3. 매출 정보\t\t[현금결제, 카드결제, 외상, 전체매출정보]\n\n");
	printf(" 4. 재고 정보\t\t[재고현황, 재고매입]\n\n");
	printf(" 5. 직원 관련설정\t[직원현황, 직원추가, 직원업무변경, 직원삭제]\n\n");
	printf(" 6. 레시피 추가\n\n");
	printf(" 0. 종료\n\n");
}
void print_returnMenu1()
{
	printf(" 1. 이전메뉴로\n\n");
	printf(" 2. 메인메뉴로\n\n");
	printf(" 0. 종료\n\n");
}
void print_returnMenu2()
{
	printf(" 1. 이전메뉴로\n\n");
	printf(" 2. 메인메뉴로\n\n");
	printf(" 3. 계속하려면\n\n");
	printf(" 0. 종료\n\n");
}
int input_Menu()
{
	int menuNum;

	printf(" 메뉴번호를 선택하세요 : ");
	scanf("%d", &menuNum);
	 
	puts("");

	return menuNum;
}
int input_MenuError()
{
	int newMenu;

	printf(" Error!!! : 잘못된 메뉴선택입니다.\n");
	newMenu = input_Menu();

	return newMenu;
}

void print_tbDetails()
{
	printf("   테이블 상세정보 [주문검색, 주문추가, 주문전체취소, 최종결제]\n\n");
	printf(" 1. 주문검색\n\n");
	printf(" 2. 주문추가\n\n");
	printf(" 3. 주문전체취소\n\n");
	printf(" 4. 최종결제\n\n");
	printf(" 0. 메인메뉴로\n\n");
}
void table_Details()
{
	int sub_menu;

	puts(" ----------------------------------------------------------------");
	print_tbDetails();

	sub_menu = input_Menu();

	if (sub_menu == 0)
		main_Menu();

	while (sub_menu < 0 || sub_menu > 4)
		sub_menu = input_MenuError();

	switch (sub_menu) {
	case 1:
		order_Status();
		break;
	case 2:
		order_Add();
		break;
	case 3:
		order_Cancle();
		break;
	case 4:
		tb_Payment();
		break;
	}

	puts(" ----------------------------------------------------------------");
}
void print_odMenu()
{
	int menu;
	char query[30] = "and Menu_Used = 1";

	puts(" ----------------------------------------------------------------");
	
	printf(" 메뉴현황 페이지입니다.\n\n");
	
	memset(q_final, 0, sizeof(q_final));	
	append_query(q_final, q_printMenu);
	append_query(q_final, query);	
//	printf("%s\n", q_final);	
	print_Usequery(q_final);
}
void order_Status()
{
	int i;
	int menu;
	int submenu;

	puts(" ----------------------------------------------------------------");

	print_returnMenu1();
	
	menu = input_Menu();

	if (menu == 0) {
		print_End();
		exit(1);
	}

	while (menu < 0 || menu > 2)
		menu = input_MenuError();

	switch (menu) {
	case 1:
		table_Details();
		break;
	case 2:
		main_Menu();
		break;
	}

	printf(" 1. 전체주문내역\n\n");
	printf(" 2. 날짜검색 주문내역\n\n");

	submenu = input_Menu();

	
	while (submenu < 0 || submenu > 2)
		submenu = input_MenuError();

	switch (submenu) {
	case 1:
		print_tot_order();
		break;
	case 2:
		print_date_order();
		break;
	}

	puts(" ----------------------------------------------------------------");
}
void print_tot_order()
{
	append_query(q_final, q_present_order);
	print_Usequery(q_final);

	puts("");
}
void print_date_order()
{
	char date[10]; 
	
	printf(" 주문내역을 검색할 날짜를 입력하세요. : ");
	scanf("%s",date);
	
	append_query(q_final, q_present_order);
	append_query(q_final, " where Bill_Date = ");
	append_query(q_final, date);

	Usequery(q_final);
	print_Usequery(q_final);
	puts("");

}
void order_Add()
{
	int tbNum;
	int bill_Idx;
	int bill_ItemID;
	char date[10];
	int menuType;
	int menuID;
	int emID;
	int custID;
	int menuCount;
	char q_menuType[10];
	char q_menuID[10];
	char q_menuCount[10];
	char q_menuPrice[10];
	int menu;

	puts(" ----------------------------------------------------------------");
	printf("   주문추가 페이지입니다.\n\n");
	print_returnMenu2();

	menu = input_Menu();

	if (menu == 0) {
		print_End();
		exit(1);
	}

	while (menu < 0 || menu > 3)
		menu = input_MenuError();

	switch (menu) {
	case 1:
		table_Details();
		break;
	case 2:
		main_Menu();
		break;
	}
		
	printf(" 오늘 날짜를 입력해주세요. : ");
	scanf("%s",date);
	__fpurge(stdin);

	tbNum = input_tbNum();
	while (tbNum<0 || tbNum >TABLE_COUNT) {
		printf(" 테이블 번호 오류입니다. 재입력해주세요 : ");
		tbNum = input_tbNum();
	}	
	itoa(tbNum,q_tbNum);

	append_query(q_final, q_select);
	append_query(q_final, " T_Bill_Item where Bill_Date = ");
	append_query(q_final, date);
	
	puts("");
	print_Usequery(q_final);

	printf(" 영수증번호를  입력해주세요. : ");
	scanf("%d",&bill_Idx);
	itoa(bill_Idx, q_bill_Idx);
//	printf("%s",q_bill_Idx);
	
	printf(" 영수증세부항목 번호를 입력해주세요. : ");
	scanf("%d",&bill_ItemID);
	itoa(bill_ItemID, q_bill_ItemID);

	print_Usequery(q_print_em);

	printf(" 직원번호를 입력해주세요. : ");
	scanf("%d",&emID);
	itoa(emID, q_emID);

	print_Usequery(q_print_cust);	

	printf(" 고객번호를  입력해주세요. : ");
	scanf("%d",&custID);
	itoa(emID, q_custID);

	
	print_odMenu();
	//printf("%s\n\n",q_final);


	input_order(&menuType, &menuID, &menuCount);
	itoa(menuType, q_menuType);
	itoa(menuID, q_menuID);
	itoa(menuCount, q_menuCount);
	//printf("%s\n\n",q_final);

	append_query(q_final,q_get_menuPrice);
	append_query(q_final, q_menuType);
	append_query(q_final, " and Menu_ID = ");
	append_query(q_final, q_menuID);
//	printf("%s\n\n",q_final);
        //printf("%s\n\n",q_final);
	get_menuPrice(q_final,q_menuPrice);

	append_query(q_final, q_table_present_status);
	append_query(q_final, " where Table_ID = ");
	append_query(q_final, q_tbNum);
//        printf("%s\n\n",q_final);
	change_table_using(q_final, date);
	//check_bill_in_table_empty(date);

	append_query(q_final,q_insert);
	append_query(q_final, "T_Bill_Item values(");
	append_query_str(q_final, date);
	append_query(q_final, ", ");
	append_query(q_final, q_bill_Idx);
	append_query(q_final, ", ");
	append_query(q_final, q_bill_ItemID);
	append_query(q_final, ", ");
	append_query(q_final, q_menuType);
	append_query(q_final, ", ");
	append_query(q_final, q_menuID);
	append_query(q_final, ", ");
	append_query(q_final, q_menuPrice);
	append_query(q_final, ", ");
	append_query(q_final, q_menuCount);
	append_query(q_final, ")");

	Usequery(q_final);
	
  //      printf("%s\n\n",q_final);
	memset(q_final, 0, sizeof(q_final));

	append_query(q_final, q_select);
	append_query(q_final, " T_Bill_Item where Bill_Date = ");
	append_query(q_final, date);
//	printf("%s\n\n",q_final);
	
	puts("");
	print_Usequery(q_final);

	print_TableList();

	table_Details();
}
void change_table_using(char * query, char*date)
{
	int i,j, fields, rows;
	char change_query[200] = {0,};
	char q_date[10];

	Usequery(query);
	strcpy(q_date, date);

	//printf("%s\n\n",query);
	res = mysql_store_result(&mysql);
	fields = mysql_num_fields(res);
	rows =mysql_num_rows(res);

	field = mysql_fetch_fields(res);

	for(j=0;j<rows;j++){
                row = mysql_fetch_row(res);
                for(i=0;i<fields;i++){
                        if( atoi(row[i]) == 0){
                                append_query(change_query, q_tb_change_using);
                                append_query(change_query, q_tbNum);
          //                      printf("%s\n\n",change_query);
                                Usequery(change_query);
        			memset(q_final, 0, sizeof(q_final));

				append_query(q_final, q_insert);
				append_query(q_final, "T_Bill(Bill_Date, Bill_Idx, Bill_TableID, Bill_Employee, Bill_Cust) values(");
				append_query_str(q_final, q_date);
				append_query(q_final, ", ");
				append_query(q_final, q_bill_Idx);
				append_query(q_final, ", ");
				append_query(q_final, q_tbNum);
				append_query(q_final, ", ");
				append_query(q_final, q_emID);
				append_query(q_final, ", ");
				append_query(q_final, q_custID);
				append_query(q_final, ") ");
	//			printf("%s\n\n",q_final);
				Usequery(q_final);
                                }
        	printf("|\n");
        	puts(" +-------------------------------------------------------------------------------------+");
		}
	}		
        memset(q_final, 0, sizeof(q_final));
}
void get_menuPrice(char *query, char *q_menuPrice)
{
	int i, j, fields, rows;

	Usequery(query);

	res = mysql_store_result(&mysql);
	fields = mysql_num_fields(res);
	rows = mysql_num_rows(res);
	
	for(j=0;j<rows;j++){
		row = mysql_fetch_row(res);
		for(i=0;i<fields;i++)
			strcpy(q_menuPrice,row[i]);
	}
	memset(query, 0, sizeof(query));

}
void itoa(int num, char*str)
{
	int i=0;
	int radix =10;
	int deg =1;
	int cnt = 0;

	while(1){
		if((num/deg) > 0)
			cnt++;
		else
			break;
		deg*=radix;
	}
	deg /= radix;

	for(i=0;i<cnt;i++){
		*(str+i) = num/deg + '0';
		num -= ((num/deg) * deg);
		deg /= radix;
	}
	*(str+i) = '\0';
}
void order_Cancle()
{
	int menu;
	char date[10];
	char bill_Idx[2];

	puts(" ----------------------------------------------------------------");
	printf("   주문 전체취소 페이지입니다.\n\n");
	print_returnMenu2();

	menu = input_Menu();

	if (menu == 0) {
		print_End();
		exit(1);
	}

	while (menu < 0 || menu > 3)
		menu = input_MenuError();

	switch (menu) {
	case 1:
		table_Details();
		break;
	case 2:
		main_Menu();
		break;
	}

	append_query(q_final, q_present_order);
	print_Usequery(q_final);

	printf(" 주문을 취소할 날짜를 입력해주세요 : ");
	scanf("%s",date);	
	
	printf(" 주문을 영수증번호를 입력해주세요 : ");
	scanf("%s",bill_Idx);

	append_query(q_final, q_delete);
	append_query(q_final, "T_Bill where Bill_Date = ");
	append_query_str(q_final, date);
	append_query(q_final, "and Bill_Idx = ");
	append_query(q_final, bill_Idx);

	Usequery(q_final);
	memset(q_final, 0, sizeof(q_final));

	puts(" ----------------------------------------------------------------");
	
	table_Details();
}
void tb_Payment()
{
	int tbNum;
	int menu;
	char date[10];
	char bill_Idx[2];
	int tot_pay;
	int cash =0 ;
	int card =0 ;
	int balancedue =0 ;
	int temp;
	char q_cash[10];
	char q_card[10];
	char q_balancedue[10];

	puts(" ----------------------------------------------------------------");
	printf("   [최종결제페이지입니다.]\n\n");

	print_returnMenu2();
	menu = input_Menu();

	if (menu == 0) {
		print_End();
		exit(1);
	}

	while (menu < 0 || menu > 3)
		menu = input_MenuError();

	switch (menu) {
	case 1:
		table_Details();
		break;
	case 2:
		main_Menu();
		break;
	}

	puts("");
	append_query(q_final, q_print_tot_pay);
	print_Usequery(q_final);

	printf(" 최종결제할 영수증의 날짜를 입력해주세요 : ");
	scanf("%s", date);

	printf(" 영수증번호를 입력해주세요 : ");
	scanf("%s",bill_Idx);

	while(1){
		tot_pay = get_tot_pay(date, bill_Idx);
		temp = tot_pay;	
		
		printf("\n\n 총 결제하셔야 할 금액은 %d원입니다.\n", tot_pay);
		
		printf(" 현금으로 결제하실 금액을 입력해주세요 : ");
		scanf("%d",&cash);	
		temp -= cash;	

		printf("\n [결재잔액 = %d]\n",temp);
		
		printf(" 카드로 결제하실 금액을 입력해주세요 : ");
		scanf("%d",&card);
		temp -= card;	

		printf("\n [결재잔액 = %d]\n",temp);
		
		printf(" 외상으로 결제하실 금액을 입력해주세요 : ");
		scanf("%d",&balancedue);

		temp = 0;

		if( (cash+card+balancedue) == tot_pay){
			printf("\n   [성공적으로 결제되었습니다!!]\n");
			break;
		}
				
	}

	itoa(cash, q_cash);
	itoa(card, q_card);
	itoa(balancedue, q_balancedue);

//	printf("%s\t%s\t%s\t\n\n",q_cash,q_card,q_balancedue);

//	printf("%s\n\n",q_balancedue);
	
	if(cash != 0){
		printf("   .......현금결제완료!!\n");
		append_query(q_final, q_update_pay_cash);
		append_query(q_final, q_cash);
		append_query(q_final, " where Bill_Date = ");
		append_query(q_final, date);
		append_query(q_final, " and Bill_Idx = ");
		append_query(q_final, bill_Idx);
		//printf("%s\n\n",q_final);
		Usequery(q_final);
		memset(q_final, 0, sizeof(q_final));
	}
	
	if(card !=0){
		printf("   .......카드결제완료!!\n");
		append_query(q_final, q_update_pay_card);
		append_query(q_final, q_card);
		append_query(q_final, " where Bill_Date = ");
		append_query(q_final, date);
		append_query(q_final, " and Bill_Idx = ");
		append_query(q_final, bill_Idx);
//		printf("%s\n\n",q_final);
		Usequery(q_final);
		memset(q_final, 0, sizeof(q_final));
	}
	
	if(balancedue !=0){
		printf("   .......외상결제완료!!\n");
		append_query(q_final, q_update_pay_balancedue);
		append_query(q_final, q_balancedue);
		append_query(q_final, " where Bill_Date = ");
		append_query(q_final, date);
		append_query(q_final, " and Bill_Idx = ");
		append_query(q_final, bill_Idx);
//		printf("%s\n\n",q_final);
		Usequery(q_final);
		memset(q_final, 0, sizeof(q_final));
	}
	puts("");

	append_query(q_final, q_select);
	append_query(q_final, "T_Bill");
	append_query(q_final, " where Bill_Date = ");
	append_query(q_final, date);
	append_query(q_final, " and Bill_Idx = ");
	append_query(q_final, bill_Idx);
	
//	printf("%s\n\n",q_final);
	print_Usequery(q_final);

	puts("");
	puts(" ----------------------------------------------------------------");
	
	table_Details();
}
int get_tot_pay(char *date, char *bill_Idx)
{
	int i, j, fields, rows;
	int totPay;

	append_query(q_final, q_get_part_pay);
	append_query(q_final, date);
	append_query(q_final, " and Bill_Idx = ");
	append_query(q_final, bill_Idx);
	append_query(q_final, q_get_part_pay_group);	
	
	Usequery(q_final);

	res = mysql_store_result(&mysql);
	fields = mysql_num_fields(res);
	rows = mysql_num_rows(res);
	
	for(j=0;j<rows;j++){
		row = mysql_fetch_row(res);
		for(i=0;i<fields;i++)
			totPay = atoi(row[i]);
	}
	memset(q_final, 0, sizeof(q_final));

	return totPay;
}
int input_tbNum()
{
	int tbNum;

	printf(" 주문을 추가할 테이블 번호를 입력해주세요 : ");
	scanf("%d", &tbNum);
	 

	return tbNum;
}

void input_order(int *menuType, int *menuNum, int*menuCount)
{
	printf(" 메뉴의 타입을 입력해주세요. : ");
	scanf("%d", menuType);

	printf(" 메뉴 번호를 입력해주세요. : ");
	scanf("%d", menuNum);
	 
	printf(" 메뉴의 수량을 입력해주세요 : ");
	scanf("%d", menuCount);
}
void print_menuSetup()
{
	printf("   메뉴관련설정 [메뉴현황, 메뉴추가, 메뉴정보 변경, 메뉴상태변경]\n\n");
	printf(" 1. 메뉴현황\n\n");
	printf(" 2. 메뉴추가\n\n");
	printf(" 3. 메뉴정보 변경\n\n");
	printf(" 4. 메뉴상태변경\n\n");
	printf(" 0. 메인메뉴로\n\n");
}
void menu_Setup()
{
	int sub_menu;

	puts(" ----------------------------------------------------------------");

	print_menuSetup();

	sub_menu = input_Menu();

	if (sub_menu == 0)
		main_Menu();

	while (sub_menu < 0 || sub_menu > 4)
		sub_menu = input_MenuError();

	switch (sub_menu) {
	case 1:
		menu_Status();
		break;
	case 2:
		menu_Add();
		break;
	case 3:
		menu_Change();
		break;
	case 4:
		menu_used_Change();
		break;
	}

	puts(" ----------------------------------------------------------------");
}
void menu_Status()
{
	int menu;
	char query[30] = "and Menu_Used = 1";

	append_query(q_final, q_printMenu);
	append_query(q_final, query);	

	puts(" ----------------------------------------------------------------");
	
	printf(" 메뉴현황 페이지입니다.\n\n");
	
	print_Usequery(q_final);

	puts("");	

	print_returnMenu1();

	menu = input_Menu();

	if (menu == 0) {
		print_End();
		exit(1);
	}

	while (menu < 0 || menu > 2)
		menu = input_MenuError();

	switch (menu) {
	case 1:
		menu_Setup();
		break;
	case 2:
		main_Menu();
		break;
	}
	puts(" ----------------------------------------------------------------");
}
void menu_Add()
{
	char infoMenu_Type[2];
	char infoMenu_ID[2];
	char infoMenu_Name[45];
	char infoMenu_Price[7];
	char temp[10];

	int menu;


	puts(" ----------------------------------------------------------------");
	printf("   메뉴추가 페이지입니다.\n\n");
	
	print_returnMenu2();

	menu = input_Menu();

	if (menu == 0) {
		print_End();
		exit(1);
	}

	while (menu < 0 || menu > 3)
		menu = input_MenuError();

	switch (menu) {
	case 1:
		menu_Setup();
		break;
	case 2:
		main_Menu();
		break;
	}
	
	puts("");
	print_Usequery(q_menuType);

	puts("");
	print_Usequery(q_menuID_menuType);

	printf("\n [추가할 메뉴에 대한 정보를 입력하세요.]\n\n");
	input_infoMenu(infoMenu_Type, infoMenu_ID, infoMenu_Name, infoMenu_Price);

	append_query(q_final,q_insert);
	append_query(q_final, "T_Menu(Menu_TypeID, Menu_ID, Menu_Name, Menu_Price) values (");

	append_query(q_final,infoMenu_Type);
	append_query(q_final, ", ");
	
	append_query(q_final, infoMenu_ID);
	append_query(q_final, ", ");

	append_query_str(q_final, infoMenu_Name);
	append_query(q_final, ", ");

	append_query_str(q_final, infoMenu_Price);
	append_query(q_final,")");

//	printf("%s\n",q_final);	
	Usequery(q_final);
	print_Usequery(q_printMenu);	

	puts("");
	menu_Setup();
}
void menu_Change()
{
	char infoMenu_Type[2];
	char infoMenu_ID[2];
	char infoMenu_Name[45];
	char infoMenu_Price[7];
	char q_where[50] = " where Menu_TypeID = ";
	int menu;

	puts(" ----------------------------------------------------------------");
	printf("   메뉴정보변경 페이지입니다.\n\n");

	print_returnMenu2();

	menu = input_Menu();

	if (menu == 0) {
		print_End();
		exit(1);
	}

	while (menu < 0 || menu > 3)
		menu = input_MenuError();

	switch (menu) {
	case 1:
		menu_Setup();
		break;
	case 2:
		main_Menu();
		break;
	}

	append_query(q_final, q_update_Menu);
	append_query(q_final, "Name = ");

	print_Usequery(q_printMenu);	

	printf(" [변경할 메뉴의 Type과 ID를 입력하세요.]\n\n");
	input_infoMenu_least(infoMenu_Type, infoMenu_ID);

	__fpurge(stdin);
	append_query(q_where,infoMenu_Type);
	
	append_query(q_where, " and Menu_ID = ");
	__fpurge(stdin);
	append_query(q_where, infoMenu_ID);
	__fpurge(stdin);

	puts(" ----------");
	printf(" [메뉴의 변경할 정보를 입력하세요.]\n\n");
	input_infoMenu_name(infoMenu_Name);
	
	append_query(q_final, q_update_Menu);
	append_query(q_final, "Name = ");
	append_query_str(q_final, infoMenu_Name);
	append_query(q_final, q_where);
	Usequery(q_final);
	__fpurge(stdin);

	memset(q_final, 0, sizeof(q_final));
	append_query(q_final, q_update_Menu);
	append_query(q_final, "Price = ");

	input_infoMenu_price(infoMenu_Price);
	__fpurge(stdin);
	append_query(q_final, infoMenu_Price);		
	append_query(q_final, q_where);
	Usequery(q_final);
	
	print_Usequery(q_printMenu);	

	puts("");
	menu_Setup();
}
void menu_used_Change()
{
	char infoMenu_Name[45];
	int menu;
	int submenu;

	puts(" ----------------------------------------------------------------");
	printf("   메뉴사용상태변경  페이지입니다.\n\n");

	print_returnMenu2();

	menu = input_Menu();

	if (menu == 0) {
		print_End();
		exit(1);
	}

	while (menu < 0 || menu > 3)
		menu = input_MenuError();

	switch (menu) {
	case 1:
		menu_Setup();
		break;
	case 2:
		main_Menu();
		break;
	}


	puts("");
	printf(" 1. 사용하지 않음 상태로 변경\n\n");
	printf(" 2. 사용함 상태로 변경\n\n");

	submenu = input_Menu();

	while (submenu < 1 || submenu > 2)
		submenu = input_MenuError();

	switch (submenu) {
	case 1:
		menu_not_used();
		break;
	case 2:
		menu_used();
		break;
	}

	puts("");
	menu_Setup();
}
void menu_not_used()
{
	char infoMenu_Name[45];

	append_query(q_final, q_select);
	append_query(q_final, "T_Menu");
	append_query(q_final, " where Menu_Used = 1");
	print_Usequery(q_final);

	puts("");
	printf(" [사용안함으로 바꿀  메뉴이름을 입력하세요.]\n\n");
	input_infoMenu_name(infoMenu_Name);

	append_query(q_final, "update T_Menu set Menu_Used = 0");
	append_query(q_final, " where Menu_Name = ");
	append_query_str(q_final,infoMenu_Name);

//	printf("%s\n\n",q_final);
	Usequery(q_final);

	print_Usequery(q_printMenu);	

}
void menu_used()
{
	char infoMenu_Name[45];

	append_query(q_final, q_select);
	append_query(q_final, "T_Menu");
	append_query(q_final, " where Menu_Used = 0");
	print_Usequery(q_final);
	puts("");

	printf(" [사용함으로 바꿀  메뉴이름을 입력하세요.]\n\n");
	input_infoMenu_name(infoMenu_Name);

	append_query(q_final, "update T_Menu set Menu_Used = 1");
	append_query(q_final, " where Menu_Name = ");
	append_query_str(q_final,infoMenu_Name);

//	printf("%s\n\n",q_final);
	Usequery(q_final);

	print_Usequery(q_printMenu);	

}
void input_infoMenu(char *infoMenu_Type, char *infoMenu_ID, char *infoMenu_Name, char *infoMenu_Price)
{
	input_infoMenu_least(infoMenu_Type, infoMenu_ID);
	input_infoMenu_name(infoMenu_Name);
	input_infoMenu_price(infoMenu_Price);
}
void input_infoMenu_price(char *infoMenu_Price)
{
	printf(" 메뉴 가격을 입력하세요. : ");
	scanf("%s",infoMenu_Price);
	__fpurge(stdin);
}
void input_infoMenu_name(char *infoMenu_Name)
{
	char menuName[45];

	__fpurge(stdin);
	printf(" 메뉴 이름을 입력하세요.: ");
	fgets(menuName, sizeof(menuName), stdin);
	menuName[strlen(menuName) - 1] = '\0';
	strcpy(infoMenu_Name, menuName);
}
void input_infoMenu_least(char *infoMenu_Type, char *infoMenu_ID)
{
	printf(" 메뉴 타입 번호를 입력하세요. : ");
	scanf("%s", infoMenu_Type);
	__fpurge(stdin);

	printf(" 메뉴 번호를 입력하세요. : ");
	scanf("%s", infoMenu_ID);
	__fpurge(stdin);
}

void print_buyingSetup()
{
	printf("   매출정보 [현금결제, 카드결제, 외상, 전체매출정보]\n\n");
	printf(" 1. 현금결제\n\n");
	printf(" 2. 카드결제\n\n");
	printf(" 3. 외상\n\n");
	printf(" 4. 전체매출정보\n\n");
	printf(" 0. 메인메뉴로\n\n");
}
void buying_Setup()
{
	int sub_menu;

	puts(" ----------------------------------------------------------------");
	print_buyingSetup();

	sub_menu = input_Menu();

	if (sub_menu == 0)
		main_Menu();

	while (sub_menu < 0 || sub_menu > 4)
		sub_menu = input_MenuError();

	switch (sub_menu) {
	case 1:
		pay_only_cash();
		break;
	case 2:
		pay_only_card();
		break;
	case 3:
		pay_only_balancedue();
		break;
	case 4:
		total_Pay();
		break;
	}

	puts(" ----------------------------------------------------------------");
}
void pay_only_cash()
{
	int menu; 
	int submenu;

	puts(" ----------------------------------------------------------------");
	printf("   [현금결제정보 페이지입니다.]\n\n");
	
	print_returnMenu2();

	menu = input_Menu();

	if (menu == 0) {
		print_End();
		exit(1);
	}

	while (menu < 0 || menu > 3)
		menu = input_MenuError();

	switch (menu) {
	case 1:
		buying_Setup();
		break;
	case 2:
		main_Menu();
		break;
	}

	puts("");

	print_searchMenu();

	submenu = input_Menu();

	while (submenu <= 0 || submenu > 2)
		menu = input_MenuError();

	switch (submenu) {
	case 1:
		print_Tcash();
		break;
	case 2:
		print_Pcash();
		break;
	}

	puts(" ----------------------------------------------------------------");
	buying_Setup();
}
void pay_only_card()
{
	int menu;
	int submenu;

	puts(" ----------------------------------------------------------------");
	
	printf("   [카드결제정보 페이지입니다.]\n\n");
	
	print_returnMenu2();

	menu = input_Menu();

	if (menu == 0) {
		print_End();
		exit(1);
	}

	while (menu < 0 || menu > 3)
		menu = input_MenuError();

	switch (menu) {
	case 1:
		buying_Setup();
		break;
	case 2:
		main_Menu();
		break;
	}
	
	puts("");

	print_searchMenu();

	submenu = input_Menu();

	while (submenu <= 0 || submenu > 2)
		submenu = input_MenuError();

	switch (submenu) {
	case 1:
		print_Tcard();
		break;
	case 2:
		print_Pcard();
		break;
	}

	puts(" ----------------------------------------------------------------");
	buying_Setup();
}
void pay_only_balancedue()
{
	int menu;
	int submenu;

	puts(" ----------------------------------------------------------------");
	printf("   [외상결제정보 페이지입니다.]\n\n");

	print_returnMenu2();

	menu = input_Menu();

	if (menu == 0) {
		print_End();
		exit(1);
	}

	while (menu < 0 || menu > 3)
		menu = input_MenuError();

	switch (menu) {
	case 1:
		buying_Setup();
		break;
	case 2:
		main_Menu();
		break;
	}

	puts("");

	print_searchMenu();
	
	submenu = input_Menu();

	while (submenu <= 0 || submenu > 2)
		submenu = input_MenuError();

	switch (submenu) {
	case 1:
		print_Tbalancedue();
		break;
	case 2:
		print_Pbalancedue();
		break;
	}

	puts(" ----------------------------------------------------------------");
	buying_Setup();
}
void total_Pay()
{
	int menu;

	puts(" ----------------------------------------------------------------");
	printf("   [전체 매출정보 페이지입니다.]\n\n");
	print_searchMenu();

	menu = input_Menu();

	while (menu <= 0 || menu > 2)
		menu = input_MenuError();

	switch (menu) {
	case 1:
		print_TotalPay();
		break;
	case 2:
		date_for_totalPay();
		break;
	}

	puts(" ----------------------------------------------------------------");

	buying_Setup();
}
void print_searchMenu()
{
	int menu;

	printf(" 1. 전체 결제 정보\n\n");
	printf(" 2. 날짜검색 결제 정보\n\n");
}
void print_Tbalancedue()
{
	char query[25] = "Balancedue) from T_Bill";

	puts(" ----------------------------------------------------------------");
	printf("   [외상매출정보 페이지입니다.]\n\n");

	printf("   [전체 외상매출합계]\n");
	append_query(q_final, q_billSum);
	append_query(q_final, query);
	
	print_Usequery(q_final);
	
	puts("");

	printf("   [날짜별 외상매출합계\n]");
	append_query(q_final, q_billSumDate);
	append_query(q_final, query);
	append_query(q_final, " group by Bill_Date");
	
	print_Usequery(q_final);
	
	puts("");
	
	buying_Setup();
}
void print_Pbalancedue()
{
	char date[15];
	char query[] = "Balancedue) from T_Bill where Bill_Date =";

	append_query(q_final, q_billSumDate);
	append_query(q_final, query);

	puts(" ----------------------------------------------------------------");
	printf("   [외상매출정보 '날짜로 검색' 페이지입니다.]\n\n");

	input_Date(date);
	append_query(q_final,date);
	
	print_Usequery(q_final);
	puts("");

	buying_Setup();
}
void print_Tcard()
{
	char query[25] = "Card) from T_Bill";
	append_query(q_final, q_billSum);
	append_query(q_final, query);
	
	puts(" ----------------------------------------------------------------");
	printf("   [카드매출정보 페이지입니다.]\n\n");
	
	printf("   [전체 카드매출합계]\n");
	print_Usequery(q_final);
	
	puts("");

	printf("   [날짜별 카드매출합계]\n");	
	append_query(q_final, q_billSumDate);
	append_query(q_final, query);
	append_query(q_final, " group by Bill_Date");
	print_Usequery(q_final);
	

	puts("");
	buying_Setup();
}
void print_Pcard()
{
	char date[15];
	char query[40] = "Card) from T_Bill where Bill_Date =";

	append_query(q_final, q_billSumDate);
	append_query(q_final, query);

	puts(" ----------------------------------------------------------------");
	printf("   [카드매출정보 '날짜로 검색' 페이지입니다.]\n\n");

	input_Date(date);
	append_query(q_final,date);
	
	print_Usequery(q_final);
	puts("");

	buying_Setup();
}
void print_Tcash()
{
	char query[25] = "Cash) from T_Bill";
	
	append_query(q_final, q_billSum);
	append_query(q_final, query);
	
	puts(" ----------------------------------------------------------------");
	printf("   [현금매출정보 페이지입니다.]\n\n");
	
	printf("   [전체 현금매출합계]\n");
	print_Usequery(q_final);
	
	puts("");

	printf("   [날짜별 현금매출합계]\n");	
	append_query(q_final, q_billSumDate);
	append_query(q_final, query);
	append_query(q_final, " group by Bill_Date");
	print_Usequery(q_final);
	
	
	puts("");
	buying_Setup();
}
void print_Pcash()
{
	char date[15];
	char query[40] = "Cash) from T_Bill where Bill_Date =";

	append_query(q_final, q_billSumDate);
	append_query(q_final, query);

	puts(" ----------------------------------------------------------------");
	printf("   [현금매출정보 '날짜로 검색' 페이지입니다.]\n\n");
	
	input_Date(date);
	append_query(q_final,date);
	
	print_Usequery(q_final);
	puts("");

	buying_Setup();
}
void print_TotalPay()
{
	char query[] = "Cash+Bill_Card+Bill_Balancedue) as totalSales from T_Bill";

	puts(" ----------------------------------------------------------------");
	printf("   [총매출정보]\n\n");

	printf("   [전체 총매출합계]\n");
	append_query(q_final, q_billSum);
	append_query(q_final,query);

	print_Usequery(q_final);
	
	puts("");

	printf("   [날짜별 총매출합계]\n");


	append_query(q_final, q_billSumDate);
	append_query(q_final, query);
	append_query(q_final, " group by Bill_Date");

	print_Usequery(q_final);
	

	puts("");

	buying_Setup();
}
void date_for_totalPay()
{
	char date[15];
	char query[] = "Card+Bill_Card+Bill_Balancedue) as totalSales(Date) from T_Bill where Bill_Date =";

	append_query(q_final, q_billSumDate);
	append_query(q_final, query);

	puts(" ----------------------------------------------------------------");
	printf("   [총매출정보 '날짜로 검색' 페이지입니다.]\n\n");

	input_Date(date);
	append_query(q_final,date);
	
	print_Usequery(q_final);
	
	puts("");

	buying_Setup();
}
void input_Date(char *desc)
{
	char date[15];

	printf(" 검색할 날짜를 입력해주세요.(YYYYMMDD) : ");
	
	__fpurge(stdin);
	fgets(date, sizeof(date), stdin);
	date[strlen(date) - 1] = '\0';
	strcpy(desc, date);
}

void print_stockSetup()
{
	printf("   재고정보 [재고현황, 재고매입]\n\n");
	printf(" 1. 재고현황\n\n");
	printf(" 2. 재고매입\n\n");
	printf(" 0. 메인메뉴로\n\n");
}
void stock_Setup()
{
	int sub_menu;

	puts(" ----------------------------------------------------------------");

	print_stockSetup();

	sub_menu = input_Menu();

	if (sub_menu == 0)
		main_Menu();

	while (sub_menu < 0 || sub_menu > 2)
		sub_menu = input_MenuError();

	switch (sub_menu) {
	case 1:
		stock_Status();
		break;
	case 2:
		stock_Buying();
		break;
	}
	puts(" ----------------------------------------------------------------");
}
void stock_Status()
{
	int menu;
	int sub_menu;

	puts(" ----------------------------------------------------------------");
	printf("   재고현황 페이지입니다.\n\n");
	
	print_returnMenu2();

	menu = input_Menu();

	if (menu == 0) {
		print_End();
		exit(1);
	}

	while (menu < 0 || menu > 3)
		menu = input_MenuError();

	switch (menu) {
	case 1:
		stock_Setup();
		break;
	case 2:
		main_Menu();
		break;
	}

	print_stockStatusMenu();

	sub_menu = input_Menu();

	while (sub_menu < 0 || sub_menu > 3)
		sub_menu = input_MenuError();

	switch (sub_menu) {
	case 1:
		stockTotal();
		break;
	case 2:
		stockPart();
		break;
	}

	stock_Setup();
}
void stock_Buying()
{
	char amount[5];
	char foods[5];
	char trader[5];
	char BuyingID[2];
	char BuyingDate[10];
	char EmID[5];
	char buyingPrice[10];

	puts(" ----------------------------------------------------------------");
	printf("   재고매입 페이지입니다.\n\n");
	
	append_query(q_final, q_get_next_BuyingID);

	printf(" 매입하는 날짜를 입력하세요. :");
	scanf("%s", BuyingDate);
	__fpurge(stdin);

	append_query(q_final, BuyingDate);	
	printf("%s\n\n",q_final);
	__fpurge(stdin);

	get_next_ID(q_final,BuyingID);

	append_query(q_final, q_searchEm);
	print_Usequery(q_final);
	puts("");

	printf(" 매입하는 직원ID를  입력하세요. :");
	scanf("%s", EmID);
	__fpurge(stdin);

	append_query(q_final, q_select);
	append_query(q_final, "T_Foods");
	print_Usequery(q_final);
	puts("");

	printf(" 매입할 식재료코드를 입력하세요. :");
	scanf("%s", foods);
	__fpurge(stdin);

	printf(" 매입할 수량을 입력하세요. :");
	scanf("%s", amount);
	__fpurge(stdin);

	printf(" 매입한 가격을 입력하세요. :");
	scanf("%s", buyingPrice);
	__fpurge(stdin);

	print_Trader();
	puts("");

	printf(" 매입처 코드를 입력하세요. : ");
	scanf("%s", trader);
	__fpurge(stdin);
	
	append_query(q_final, q_insert);
	append_query(q_final, "T_Buying(Buying_Date, Trader_ID, Buying_ID, Em_ID, Foods_ID, Buying_Price, Buying_Count) ");
	append_query(q_final, "values(");

	__fpurge(stdin);
	append_query(q_final, BuyingDate);
	append_query(q_final, ", ");

	__fpurge(stdin);
	append_query(q_final, trader);
	append_query(q_final, ", ");

	__fpurge(stdin);
	append_query(q_final, BuyingID);
	append_query(q_final, ", ");

	__fpurge(stdin);
	append_query(q_final, EmID);
	append_query(q_final, ", ");

	__fpurge(stdin);
	append_query(q_final, foods);
	append_query(q_final, ", ");
	append_query(q_final, buyingPrice);
	append_query(q_final, ", ");
	append_query(q_final, amount);
	append_query(q_final,")");

//	printf("%s\n\n",q_final);
	Usequery(q_final);
	memset(q_final, 0, sizeof(q_final));
	__fpurge(stdin);
	
	append_query(q_final, "update T_Buying set L_ID = (select L_ID from T_Foods where Foods_ID = ");
	append_query(q_final, foods);
	append_query(q_final, ") where Foods_ID = ");
	append_query(q_final, foods);
	Usequery(q_final);
//	printf("%s\n\n",q_final);
	memset(q_final, 0, sizeof(q_final));
	__fpurge(stdin);

	append_query(q_final, " update T_Buying set M_ID = (select M_ID from T_Foods where Foods_ID = ");
	append_query(q_final, foods);
	append_query(q_final, ") where Foods_ID = ");
	append_query(q_final, foods);

//	printf("%s\n\n",q_final);
	Usequery(q_final);
	memset(q_final, 0, sizeof(q_final));

	puts("");
	puts(" ----------------------------------------------------------------");

	stock_Setup();
}
void print_stockStatusMenu()
{
	puts(" ----------------");
	printf(" [ 재고현황검색 상세메뉴 ]\n\n");
	printf(" 1. 총재고현황\n\n");
	printf(" 2. 식재료명으로 재고 검색\n\n");
}
void stockTotal()
{
	
	printf(" [ 전체 재고현황 페이지 ]\n\n");
	print_Usequery(q_searchStock);

}
void stockPart()
{
	char foods[45];
	char q_where[50] = " and Foods_Name like";

	append_query(q_final, q_searchStock);
	append_query(q_final, q_where);
	 
	printf(" [ 식재료명으로 재고검색 페이지 ]\n\n");

	printf(" 재고현황을 검색 할 식재료명을 입력하세요. : ");
	__fpurge(stdin);
	fgets(foods, sizeof(foods), stdin);
	foods[strlen(foods) - 1] = '\0';
	
	append_query_str(q_final, foods);

	print_Usequery(q_final);

	puts("");
}

void print_Trader()
{
	append_query(q_final, q_select);
	append_query(q_final, "T_Trader");

//	printf("%s\n\n",q_final);
	print_Usequery(q_final);

	puts("");
}


void print_employeeSetup()
{
	printf("   직원관련설정 [직원현황, 직원추가, 직원업무변경, 직원삭제]\n\n");
	printf(" 1. 직원현황\n\n");
	printf(" 2. 직원추가\n\n");
	printf(" 3. 직원업무변경\n\n");
	printf(" 4. 직원삭제\n\n");
	printf(" 0. 메인메뉴로\n\n");
}
void employee_Setup()
{
	int sub_menu;

	puts(" ----------------------------------------------------------------");

	print_employeeSetup();

	sub_menu = input_Menu();

	if (sub_menu == 0)
		main_Menu();

	while (sub_menu < 0 || sub_menu > 4)
		sub_menu = input_MenuError();

	switch (sub_menu) {
	case 1:
		employee_status();
		break;
	case 2:
		employee_add();
		break;
	case 3:
		employeeType_change();
		break;
	case 4:
		employee_delete();
		break;
	}

	puts(" ----------------------------------------------------------------");
}
void employee_status()
{
	int menu;
	char query[100] ="T_Private_Type natural join T_Employee where T_Private_Type.Em_TypeID = T_Employee.Em_TypeID";
	puts(" ----------------------------------------------------------------");

	printf(" 직원현황 페이지입니다.\n\n");

	append_query(q_final, q_select);
	append_query(q_final, query);
	
	print_Usequery(q_final);
	puts("");

	print_returnMenu1();

	menu = input_Menu();

	if (menu == 0) {
		print_End();
		exit(1);
	}

	while (menu < 0 || menu > 2)
		menu = input_MenuError();

	switch (menu) {
	case 1:
		employee_Setup();
		break;
	case 2:
		main_Menu();
		break;
	}
	
	puts(" ----------------------------------------------------------------");
}
void employee_add()
{
	int menu;
	char emType[2];
	char hp[15];
	char name[45];

	puts(" ----------------------------------------------------------------");
	printf("   직원추가 페이지입니다.\n\n");
	
	print_returnMenu2();

	menu = input_Menu();

	if (menu == 0) {
		print_End();
		exit(1);
	}

	while (menu < 0 || menu > 3)
		menu = input_MenuError();

	switch (menu) {
	case 1:
		menu_Setup();
		break;
	case 2:
		main_Menu();
		break;
	}

	append_query(q_final, q_select);
	append_query(q_final, "T_Private_Type");
	print_Usequery(q_final);


	append_query(q_final, q_insert);
	append_query(q_final, "T_Employee(Em_TypeID, Em_Name, HP) values(");
	
	__fpurge(stdin);
	printf("   [직원 상세정보 입력]\n\n");

	__fpurge(stdin);
	printf(" 1. 직원 TYPE입력 : ");
	scanf("%s",&emType);

	printf(" 2. 직원 이름입력 : ");
	__fpurge(stdin);
	fgets(name, sizeof(name), stdin);
	name[strlen(name) - 1] = '\0';

	printf(" 3. 직원 HP입력 : ");
	__fpurge(stdin);
	fgets(hp, sizeof(hp), stdin);
	hp[strlen(hp) - 1] = '\0';

	append_query(q_final,emType);
	append_query(q_final, ",");
	
	append_query_str(q_final,name);
	append_query(q_final, ",");

	append_query_str(q_final,hp);
	append_query(q_final,")");

	Usequery(q_final);
	memset(q_final, 0, sizeof(q_final));

	print_Usequery("select * from T_Employee");
	puts("");

	employee_Setup();
}
void employeeType_change()
{
	int menu;
	char emID[2], emChType[2];

	puts(" ----------------------------------------------------------------");
	printf("   직원업무변경 페이지입니다.\n\n");

	print_returnMenu2();

	menu = input_Menu();

	if (menu == 0) {
		print_End();
		exit(1);
	}

	while (menu < 0 || menu > 3)
		menu = input_MenuError();

	switch (menu) {
	case 1:
		menu_Setup();
		break;
	case 2:
		main_Menu();
		break;
	}

	append_query(q_final, q_update_Em);

	printf("   [직원 업무변경 정보입력]\n\n");
	printf(" 1. 변경 할 직원 ID입력 : ");
	scanf("%s", &emID);

	printf(" 2. 바뀔 직원의 Type입력 : ");
	scanf("%s", &emChType);

	append_query(q_final, emChType);
	append_query(q_final, " where Em_ID =  ");
	append_query(q_final, emID);

	Usequery(q_final);
	print_Usequery(q_searchEm);

	employee_Setup();
}
void employee_delete()
{
	int menu;
	char emID[2];

	puts(" ----------------------------------------------------------------");
	printf("   직원삭제 페이지입니다.\n\n");

	print_returnMenu2();

	menu = input_Menu();

	if (menu == 0) {
		print_End();
		exit(1);
	}

	while (menu < 0 || menu > 3)
		menu = input_MenuError();

	switch (menu) {
	case 1:
		menu_Setup();
		break;
	case 2:
		main_Menu();
		break;
	}
	
	print_Usequery(q_searchEm);

	append_query(q_final, q_delete);
	append_query(q_final, q_delete_Em);

	__fpurge(stdin);
	printf("   [삭제할 직원 정보입력]\n\n");
	printf(" 1. 삭제 할 직원 ID입력 : ");
	scanf("%s", emID);

	append_query(q_final, emID);
	
	Usequery(q_final);
	
	puts("");
	print_Usequery(q_searchEm);

	puts("");

	employee_Setup();
}
void recipe_Add()
{
	int menu;
	int menuType;
	int menuID;
	int Foods_ID;
	char q_menuType[10];
	char q_menuID[10];
	char q_Foods_ID[10];

	puts(" ----------------------------------------------------------------");
	printf("   레시피추가 페이지입니다.\n\n");
	
	print_returnMenu2();

	menu = input_Menu();

	if (menu == 0) {
		print_End();
		exit(1);
	}

	while (menu < 0 || menu > 3)
		menu = input_MenuError();

	switch (menu) {
	case 1:
		menu_Setup();
		break;
	case 2:
		main_Menu();
		break;
	}

	puts(" ----------------------------------------------------------------");

	
	printf("   [레시피 상세정보 입력]\n\n");

	append_query(q_final, q_select);
	append_query(q_final, "T_Menu where Menu_TypeID not in (4)");
	print_Usequery(q_final);
	puts("");

	printf(" 1. 메뉴 TYPE입력 : ");
	scanf("%d",&menuType);
	itoa(menuType, q_menuType);

	printf(" 2. 메뉴 ID입력 : ");
	scanf("%d",&menuID);
	itoa(menuID, q_menuID);
	
	append_query(q_final, q_select);
	append_query(q_final, "T_Foods");
	print_Usequery(q_final);
	puts("");

	printf(" 3. 식재료 ID입력 : ");
	scanf("%d",&Foods_ID);
	itoa(Foods_ID, q_Foods_ID);

	append_query(q_final, q_insert);
	append_query(q_final, "T_Recipe(Menu_Type, Menu_ID, Foods_ID) values(");

	append_query(q_final,q_menuType);
	append_query(q_final, ",");
	__fpurge(stdin);
	
	append_query(q_final,q_menuID);
	append_query(q_final, ",");

	append_query(q_final,q_Foods_ID);
	append_query(q_final,")");
//	printf("%s\n\n",q_final);
	Usequery(q_final);
	memset(q_final, 0, sizeof(q_final));
	
	append_query(q_final, "update T_Recipe set L_ID = (select L_ID from T_Foods where Foods_ID = ");
	append_query(q_final, q_Foods_ID);
	append_query(q_final, ") where Foods_ID = ");
	append_query(q_final, q_Foods_ID);
//	printf("%s\n\n",q_final);
	Usequery(q_final);
	memset(q_final, 0, sizeof(q_final));

	append_query(q_final, " update T_Recipe set M_ID = (select M_ID from T_Foods where Foods_ID = ");
	append_query(q_final, q_Foods_ID);
	append_query(q_final, ") where Foods_ID = ");
	append_query(q_final, q_Foods_ID);
//	printf("%s\n\n",q_final);
	Usequery(q_final);
	memset(q_final, 0, sizeof(q_final));

	append_query(q_final, q_select);
	append_query(q_final, " T_Recipe where Menu_Type = ");
	append_query(q_final, q_menuType);
	append_query(q_final, " and Menu_ID = ");
	append_query(q_final, q_menuID);
//	printf("%s\n\n",q_final);
	print_Usequery(q_final);
//	memset(q_final, 0, sizeof(q_final));
	puts("");

	main_Menu();

}
void print_TableList()
{
	int tb_status[TABLE_COUNT];
	int i;

	set_tb_status(tb_status);

	puts(" ----------------------------------------------------------------");
	puts(" [테이블 현황] ");

	for (i = 0; i<TABLE_COUNT / 2; i++)
		printf(" TB[%d]\t\t", i + 1);
	puts("");

	for (i = 0; i<TABLE_COUNT / 2; i++)
		printf("\t+------+");
	puts("");

	for (i = 0; i<TABLE_COUNT / 2; i++) {
		if (tb_status[i] == 0)
			printf("\t|      |");
		else if (tb_status[i] == 1)
			printf("\t+------+");
	}
	puts("");

	for (i = 0; i<TABLE_COUNT / 2; i++)
		printf("\t+------+");

	puts("");
	puts("");

	for (i = TABLE_COUNT / 2; i<TABLE_COUNT; i++)
		printf(" TB[%d]\t\t", i + 1);
	puts("");

	for (i = TABLE_COUNT / 2; i<TABLE_COUNT; i++)
		printf("\t+------+");
	puts("");

	for (i = TABLE_COUNT / 2; i<TABLE_COUNT; i++) {
		if (tb_status[i] == 0)
			printf("\t|      |");
		else if (tb_status[i] == 1)
			printf("\t+------+");
	}
	puts("");

	for (i = TABLE_COUNT / 2; i<TABLE_COUNT; i++)
		printf("\t+------+");
	puts("");

	puts(" ----------------------------------------------------------------");
}
void set_tb_status(int *tb_status)
{
	int i, j, fields, rows;
	int cnt = 0;

	append_query(q_final,q_table_present_status);
	Usequery(q_final);

	res = mysql_store_result(&mysql);
	fields = mysql_num_fields(res);
	rows = mysql_num_rows(res);
	
	for(j=0;j<rows;j++){
		row = mysql_fetch_row(res);
		for(i=0;i<fields;i++){
			tb_status[cnt] = atoi(row[i]);
			cnt++;
		}
	}
	memset(q_final, 0, sizeof(q_final));
}
void Usequery(char * query)
{
	if(mysql_real_query(&mysql, query, strlen(query))){
		fprintf(stderr, "%s\n", mysql_error(&mysql));
		return;
	}

}
void print_Usequery(char * query)
{
	int i, j, fields, rows;

	Usequery(query);

	res = mysql_store_result(&mysql);
	fields = mysql_num_fields(res);
	rows = mysql_num_rows(res);

	field = mysql_fetch_fields(res);
	puts(" +-------------------------------------------------------------------------------------+");
	for(i=0;i<fields;i++)
		printf(" | %10s \t", field[i].name);
	printf("|\n");
	puts(" +-------------------------------------------------------------------------------------+");
	
	for(j=0;j<rows;j++){
		row = mysql_fetch_row(res);
		for(i=0;i<fields;i++)
			printf(" | %10s \t", row[i]);
	printf("|\n");
	puts(" +-------------------------------------------------------------------------------------+");
	}
	memset(q_final, 0, sizeof(q_final));
}
void get_next_ID(char *query, char *nextID)
{
	int i, j, fields, rows;

	Usequery(query);

	res = mysql_store_result(&mysql);
	fields = mysql_num_fields(res);
	rows = mysql_num_rows(res);
	
	for(j=0;j<rows;j++){
		row = mysql_fetch_row(res);
		for(i=0;i<fields;i++){
			if(i == fields -1){
				if(row[i] == NULL){
					strcpy(nextID, "1");
					break;
				}
				strcpy(nextID,row[i]);
			}
		}
	}
	memset(query, 0, sizeof(query));
}
void print_End()
{
	Usequery("update T_Res_table set Status =0");
	mysql_close(&mysql);

	puts(" ----------------------------------------------------------------");
	printf("\t\t   사용해주셔서 감사합니다.\n");
	printf("\t\tSpeedPos Program을 종료합니다.\n");
	printf("\n\t\t[ (주) 안양대 SpeedPos ]\n");
	puts(" ----------------------------------------------------------------");
}
void append_query(char *desc, char *src)
{
	strcat(desc, src);
}
void append_query_str(char *desc, char *src)
{
	strcat(desc, "'");
	strcat(desc, src);
	strcat(desc, "'");
}
