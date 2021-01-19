import java.util.Scanner;

public class ScannerExam {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		System.out.print("name : ");
		String name = sc.next();
		System.out.print("age : ");
		int age = sc.nextInt();
		System.out.print("weight : ");
		float weight = sc.nextFloat();
		
		System.out.println("name = " +name + " age = " + age + "weight = " + weight);
		sc.close();
	}
}
