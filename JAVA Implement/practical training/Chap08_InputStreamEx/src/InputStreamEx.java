import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputStreamEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			FileInputStream fin = new FileInputStream("test");
			InputStreamReader in = new InputStreamReader(fin, "US-ASCII");
			int c;
			while((c = in.read()) != -1){
				System.out.print((char)c);
			}
			fin.close();
			in.close();
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
	}

}
