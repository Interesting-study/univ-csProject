package score;

import java.util.Scanner;

class Score {
	int num;
	String name;
	int c, java, net, tot;
	float avg;
	static float c_avg, java_avg, net_avg, tot_avg, avg_avg;
	
	Score(int num, String name) {
		this.num = num; 
		this.name = name;
	}
	Score(int num, String name, int c, int java, int net) {
		this(num, name);
		this.c = c; this.java = java; this.net = net;
	}
	void calTot() {
		tot = c + java + net;
	}
	void calAvg() {
		avg = tot/3.0f;
	}
	void printInfo() {
		System.out.println(num +"\t"+name+"\t"+c+"\t"+java+"\t"+net+"\t"+tot+"\t"+avg);
		
	}
	static void Average(int n){
		c_avg /= n;
		java_avg /= n;
		net_avg /= n;
		tot_avg /= n;
		avg_avg /= n;
	}
}
 
public class ScoreEx {
 
	public static void main(String[] args) {
		Score[] s = new Score[5];
		Scanner sc = new Scanner(System.in);
		for(int i=0; i<s.length; i++) {
			s[i] = new Score(sc.nextInt(), sc.next(), sc.nextInt(), sc.nextInt(), sc.nextInt());
			s[i].calTot();
			s[i].calAvg();
			Score.c_avg += s[i].c;
			Score.java_avg += s[i].java;
			Score.net_avg += s[i].net;
			Score.tot_avg += s[i].tot;
			Score.avg_avg += s[i].avg;
		}
		Score.Average(s.length);
		System.out.println(Score.c_avg);
		System.out.println(Score.java_avg);
		System.out.println(Score.net_avg);
		System.out.println(Score.tot_avg);
		System.out.println(Score.avg_avg);
		sc.close();
		for(int i=0; i<s.length; i++)
			s[i].printInfo();
	}
 
}