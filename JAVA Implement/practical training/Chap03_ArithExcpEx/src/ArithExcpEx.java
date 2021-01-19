import java.util.Scanner;

public class ArithExcpEx {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Input n1: ");
		int n1 = sc.nextInt();
		System.out.print("Input n2: ");
		int n2 = sc.nextInt();
		try{
			int r = n1/n2;
			System.out.println(r);
		}catch(ArithmeticException e){
			System.out.println(e.getMessage());
		}

	}

}
