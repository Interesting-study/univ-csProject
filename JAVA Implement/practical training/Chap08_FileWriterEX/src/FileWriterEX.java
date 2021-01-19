import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileWriterEX {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		char[] cBuf = {'가', '나', '다'};
		try{
			FileWriter fout = new FileWriter("test");
			fout.write("abcde");
			fout.write(cBuf);
			String str = sc.nextLine();
			fout.write(str);
			fout.close();
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
		
	}

}
/*
 * 1. Scanner 이용 키 입력 (한 줄로)
 * 	1 홍길동 100
 * 	2 박문수 90
 * 	3 김길동 95
 * 2. Score에 저장*
 * 	변수 : num, name, tot
 * 	메소드 : 생성자 - num, name, tot 초기화
 * 			void printInfo() 화면에 출력
 * 			void FPrintInfo(FileWriter fout) 파일에 출력
 */