import java.util.Scanner;

class Score{
	int num;
	String name;
	int c, java, net, tot;
	float avg;
	
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
		}
		for(int i=0; i<s.length; i++)
			s[i].printInfo();
	}
}
