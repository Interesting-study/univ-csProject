import java.io.BufferedOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class BuffedIOEX {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedOutputStream bout = new BufferedOutputStream(System.out, 2);
		try{
			FileReader fin = new FileReader("test");
			int c;
			while((c = fin.read()) != -1){
				bout.write(c);
			}
			new Scanner(System.in).nextLine();
			fin.close();
			bout.close();
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
		
	}

}
