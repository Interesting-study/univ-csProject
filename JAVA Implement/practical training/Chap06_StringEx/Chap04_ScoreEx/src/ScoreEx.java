import java.util.Scanner;

class Score{
	int num;
	String name;
	int c, java, net, tot;
	float avg;
	static float c_Avg, java_Avg, net_Avg, tot_Avg, avg_Avg;
	
	Score(int num, String name){
		this.num = num;
		this.name = name;
	}
	Score(int num, String name, int c, int java, int net){
		this(num, name);
		this.c = c;
		this.java = java;
		this.net = net;
	}
	
	static void calAverage(){
		c_Avg /= 3.0f;
		java_Avg /= 3.0f;
		net_Avg /= 3.0f;
		tot_Avg /= 3.0f;
		avg_Avg /= 3.0f;
	}
	void calTot(){
		tot = c + java + net;
	}
	void calAvg(){
		avg = tot/3.0f;
	}
	void printInfo(){
		System.out.println(num + "\t" + name + "\t" + c + "\t" + java +"\t" + net + "\t" + tot + "\t" + avg);
	}
}
public class ScoreEx {
	public static void main(String[] args){
		Score[] s = new Score[3];
		Scanner sc = new Scanner(System.in);
		for(int i=0; i<s.length; i++){
			s[i] = new Score(sc.nextInt(), sc.next(), sc.nextInt(), sc.nextInt(), sc.nextInt());
			s[i].calTot();
			s[i].calAvg();
			Score.c_Avg += s[i].c;
			Score.java_Avg += s[i].java;
			Score.net_Avg += s[i].net;
			Score.avg_Avg += s[i].avg;
			Score.tot_Avg += s[i].tot;
		}
		Score.calAverage();
		System.out.println(Score.c_Avg);
		System.out.println(Score.java_Avg);
		System.out.println(Score.net_Avg);
		System.out.println(Score.tot_Avg);
		System.out.println(Score.avg_Avg);
		
		for(int i=0; i<s.length; i++)
			s[i].printInfo();
	}
}
