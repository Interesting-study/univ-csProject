//5
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.SpinnerDateModel;

class Score{
	int num;
	String name;
	int c, java, net, tot;
	
	Score(int num, String name, int c, int java, int net){
		this.num = num; this.name = name; this.c = c; this.java = java;
		this.net = net;
	}
	void calTot(){
		tot = c + java + net;
	}
	void printInfo(){
		System.out.println(name+"\t"+tot);
	}
}

public class StrTok {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Score[] s = new Score[3];
		String[] str = new String[3];
		int num;
		String name;
		int c, java, net;
		StringTokenizer st;
		
		
		for(int i=0; i<s.length; i++){
			str[i] = sc.nextLine();
			st = new StringTokenizer(str[i], " ");
			num = Integer.parseInt(st.nextToken());
			name = st.nextToken();
			c = Integer.parseInt(st.nextToken());
			java = Integer.parseInt(st.nextToken());
			net = Integer.parseInt(st.nextToken());
			s[i] = new Score(num, name, c, java, net);
			s[i].calTot();
		}
		for(int i=0; i<s.length; i++){
			s[i].printInfo();
		}
	}
}