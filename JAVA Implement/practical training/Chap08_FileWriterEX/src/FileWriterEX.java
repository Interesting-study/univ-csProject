import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileWriterEX {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		char[] cBuf = {'��', '��', '��'};
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
 * 1. Scanner �̿� Ű �Է� (�� �ٷ�)
 * 	1 ȫ�浿 100
 * 	2 �ڹ��� 90
 * 	3 ��浿 95
 * 2. Score�� ����*
 * 	���� : num, name, tot
 * 	�޼ҵ� : ������ - num, name, tot �ʱ�ȭ
 * 			void printInfo() ȭ�鿡 ���
 * 			void FPrintInfo(FileWriter fout) ���Ͽ� ���
 */