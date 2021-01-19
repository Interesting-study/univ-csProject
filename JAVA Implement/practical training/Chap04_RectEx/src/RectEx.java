import java.util.Scanner;

class Rect{
	int w,h;
	int circum, area;
	
	void calCircum(){
		circum = 2*w + 2*h;
	}
	void calArea(){
		area = w*h;
	}
	void printInfo(){
		System.out.println("w : " + w + " h : " + h);
		System.out.println("circum : " + circum + " area : " + area);
	}
}
public class RectEx {
	public static void main(String[] args){
		Rect r1 = new Rect();
		Rect r2 = new Rect();
		Scanner sc = new Scanner(System.in);
		
		System.out.print("w : ");
		r1.w = sc.nextInt();
		System.out.print("h : ");
		r1.h = sc.nextInt();
		r1.calCircum();
		r1.calArea();
		r1.printInfo();
		
		System.out.print("w : ");
		r2.w = sc.nextInt();
		System.out.print("h : ");
		r2.h = sc.nextInt();
		r2.calCircum();
		r2.calArea();
		r2.printInfo();
	}
}//class가 여러 개 있을 경우 public이라는 접근지정자를 사용하는 클래스는 오직 하나.
