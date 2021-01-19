import java.util.InputMismatchException;
import java.util.Scanner;

public class InputMisExcp {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int sum =0;
		for(int i=0; i<3; i++){
			System.out.println("input n : ");
			try{
				int n = sc.nextInt();
				sum += n;
			}catch(InputMismatchException e){
				System.out.println("정수가 아닙니다. 정수를 입력하세요. ");
				String s = sc.next();
				System.out.println(s);
				i--;
				continue;
			}
			
		}
		System.out.println(sum);
	}
}
