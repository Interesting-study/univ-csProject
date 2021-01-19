class Methodoverloading{
	int add(int num1, int num2){
		return num1+num2;
	}
	int add(int num1, int num2, int num3){
		return num1+num2+num3;
	}
	float add(float num1, float num2){
		return num1+num2;
	}
	/*float add(int num1, int num2){
		return (float)(num1+num2);
	}*/
}
public class MethodOverloadingEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Methodoverloading m = new Methodoverloading();
		System.out.println(m.add(10, 20));
		System.out.println(m.add(10, 20, 30));
		System.out.println(m.add(10.0f, 20.0f));
	}

}
