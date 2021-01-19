import java.util.Scanner;

class Circle{
	double r, area;
	
	Circle(double r){
		this.r = r;
	}
	void getArea(){
		area = 3.14*Math.pow(r, 2.0);
	}
	void printInfo(){
		System.out.println(this + " : " + r + ", " + area);
	}
}
public class CircleArray {
	public static void main(String[] args){
		Circle[] c = new Circle[5];
		Scanner sc = new Scanner(System.in);
		for(int i=0; i<c.length; i++){
			System.out.print("input r : ");
			c[i] = new Circle(sc.nextDouble());
			c[i].getArea();
			c[i].printInfo();
		}
	}
}
/*
 * 2017.09.20 자바프로그래밍 과제
 * class score
 * 정보 : num, c, java, net, tot, avg
 * 초기화 : num, c, java, net <- 만듦과 동시에 키입력으로(생성자)
 * 기능 :	calTot
 * 		calAvg
 * 		printInfo
 * 객체는 3개, 단 객체배열로.
 * 9월 26일까지
 * 제출방법 : File - Export - General - Archive File - Report01_학번_이름 
 * */
