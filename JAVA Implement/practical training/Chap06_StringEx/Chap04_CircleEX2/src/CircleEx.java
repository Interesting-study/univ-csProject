import java.util.Scanner;

class Circle {
	double r, circum, area;
	
	Circle(){}
	
	Circle(double r){
		this.r = r;
	}
	
	void calCircum(){
		circum = 2*3.14*r;
	}
	
	void calArea(){
		area = 3.14*Math.pow(r, 2.0);
	}
	
	void printInfo(){
		System.out.println("r: "+r);
		System.out.println("Circum: "+circum+" area: "+area);
	}
}

public class CircleEx {

	public static void main(String[] args) {
		Circle[] c = new Circle[5];
		Scanner sc = new Scanner(System.in);
		
		for(int i=0; i<c.length; i++){
			System.out.print("Input r: ");
			
			c[i] = new Circle(sc.nextDouble());
			c[i].calCircum();
			c[i].calArea();
			c[i].printInfo();
		}

	}

}
