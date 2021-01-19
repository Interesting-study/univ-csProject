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
 * 2017.09.20 �ڹ����α׷��� ����
 * class score
 * ���� : num, c, java, net, tot, avg
 * �ʱ�ȭ : num, c, java, net <- ����� ���ÿ� Ű�Է�����(������)
 * ��� :	calTot
 * 		calAvg
 * 		printInfo
 * ��ü�� 3��, �� ��ü�迭��.
 * 9�� 26�ϱ���
 * ������ : File - Export - General - Archive File - Report01_�й�_�̸� 
 * */
