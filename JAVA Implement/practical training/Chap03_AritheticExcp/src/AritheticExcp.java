import java.util.Scanner;

public class AritheticExcp {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		System.out.print("n1 : ");
		int n1 = sc.nextInt();
		System.out.print("n2 : ");
		try{
			int n2 = sc.nextInt();
			int r = n1/n2;
			System.out.println(r);
		}catch(ArithmeticException e){
			System.out.println(e.getMessage());
		}
		
	}
}
