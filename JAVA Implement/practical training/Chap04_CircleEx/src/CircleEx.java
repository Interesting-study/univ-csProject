class Circle{
	String name;
	double r, area;
	
	double getArea(){
		area = 3.14*Math.pow(r, 2.0);
		return area;
	}
	void printInfo(){
		System.out.println(name + " r = " + r + " area : " + area);
	}
}
public class CircleEx {
	public static void main(String[] args){
		Circle c1 = new Circle();//default �����ڸ޼ҵ� �ڵ�ȣ��
		Circle c2 = new Circle();
		
		c1.r = 10;
		c1.name = "pizza";
		System.out.println(c1.name + " r = " + c1.r + " area : " + c1.getArea());
		
		//	c1.printInfo();
		
		c2.r = 11;
		c2.name = "pizza2";
		c2.getArea();
		c2.printInfo();
	}
}
