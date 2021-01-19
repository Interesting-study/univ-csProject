import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BinaryCopyEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			FileInputStream fin = new FileInputStream("Penguins.jpg");
			FileOutputStream fout = new FileOutputStream("copy.jpg");
			int c;
			while((c = fin.read()) != -1){
				fout.write((byte)c);
			}
			fin.close();
			fout.close();
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
	}

}
