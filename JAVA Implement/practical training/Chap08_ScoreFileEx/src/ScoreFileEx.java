import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

class Score{
	int num, tot;
	String name;
	static StringBuffer str = new StringBuffer();
	Score(int num, String name, int tot){
		this.num = num;
		this.name = name;
		this.tot = tot;
		str.append(Integer.toString(num) + " " + name + " " 
				+ Integer.toString(tot) +"\n");
	}
	void printInfo(){
		System.out.println(num + " " + name + " " + tot);
	}
	static void FPrintInfo(FileWriter fout){ 
		try{
			fout.write(str.toString());
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
	}
}
public class ScoreFileEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		Score[] s = new Score[3];
		StringTokenizer st;
		for(int i=0; i<s.length; i++){
			System.out.print("input num, name, tot : ");
			String str = sc.nextLine();
			st = new StringTokenizer(str, " ");
			int num = Integer.parseInt(st.nextToken());
			String name = st.nextToken();
			int tot = Integer.parseInt(st.nextToken());
			s[i] = new Score(num, name, tot);
		}
		try{
			FileWriter fout = new FileWriter("test.out");
			Score.FPrintInfo(fout);
			fout.close();
		}catch(IOException e){
			
		}
	}

}
