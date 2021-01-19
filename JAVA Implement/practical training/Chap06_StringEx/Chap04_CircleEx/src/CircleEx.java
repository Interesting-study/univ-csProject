class Circle{
	String name;
	double r, area;
	
	Circle() {}//default �����ڸ� �������
	Circle(double r, String name){
		this.r = r;//�Ű������� ��������� ������ ��(�̸��� ������ ��)����ϱ⵵ �ϴ� this.
		this.name = name;
	}
	double getArea(){
		area = 3.14*Math.pow(r, 2.0);
		return area;
	}
	void printInfo(){
		System.out.println(name + " r = " + r + " area : " + area);
	}
}//�����ϸ� Circle() {} �� ���� ������ �����ڸ޼ҵ����(= default ������).
public class CircleEx {
	public static void main(String[] args){
		Circle c1 = new Circle(10,"pizza");//default �����ڸ޼ҵ� �ڵ�ȣ�� <- �����ڰ� ���� ����
		Circle c2 = new Circle(5, "donut");
		Circle c3 = new Circle();//�޼ҵ� �����ε� ex : Circle()�� Circle(double r, String name)
		c3.printInfo();
		System.out.println(c1.name + " r = " + c1.r + " area : " + c1.getArea());
		
		//	c1.printInfo();
		c2.getArea();
		c2.printInfo();
	}
}
