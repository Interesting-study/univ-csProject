abstract class Calculator{
	abstract int add(int a, int b);
	abstract int sub(int a, int b);
	abstract float avg(int[] a);
}
public class GoodCalc extends Calculator{

	@Override
	int add(int a, int b) {
		return a+b;
	}

	@Override
	int sub(int a, int b) {
		return a-b;
	}

	@Override
	float avg(int[] a) {
		int sum = 0;
		
		for(int i=0; i<a.length; i++){
			sum += a[i];
		}
		return sum/(float)a.length;
	}

	public static void main(String[] args) {
		GoodCalc c = new GoodCalc();
		System.out.println(c.add(1, 2));
		System.out.println(c.sub(10, 5));
		int[] a = {1, 2, 3, 4};
		System.out.println(c.avg(a));
	}
}