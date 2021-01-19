class Circle{
	String name;
	double r, area;
	
	Circle() {}//default 생성자를 만들어줌
	Circle(double r, String name){
		this.r = r;//매개변수와 멤버변수를 구별할 때(이름이 동일할 때)사용하기도 하는 this.
		this.name = name;
	}
	double getArea(){
		area = 3.14*Math.pow(r, 2.0);
		return area;
	}
	void printInfo(){
		System.out.println(name + " r = " + r + " area : " + area);
	}
}//실행하면 Circle() {} 와 같은 형식의 생성자메소드생성(= default 생성자).
public class CircleEx {
	public static void main(String[] args){
		Circle c1 = new Circle(10,"pizza");//default 생성자메소드 자동호출 <- 생성자가 없을 때만
		Circle c2 = new Circle(5, "donut");
		Circle c3 = new Circle();//메소드 오버로딩 ex : Circle()과 Circle(double r, String name)
		c3.printInfo();
		System.out.println(c1.name + " r = " + c1.r + " area : " + c1.getArea());
		
		//	c1.printInfo();
		c2.getArea();
		c2.printInfo();
	}
}
