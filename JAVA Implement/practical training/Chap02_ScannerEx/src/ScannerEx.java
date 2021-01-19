import java.util.Scanner;

public class ScannerEx {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		
		String str = sc.next();
		int iNum = sc.nextInt();
		double dNum = sc.nextDouble();
		boolean bData = sc.nextBoolean();
		
		System.out.println(str);
		System.out.println(iNum);
		System.out.println(dNum);
		System.out.println(bData);
	}

}
