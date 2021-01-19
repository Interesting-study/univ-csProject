import java.io.FileReader;
import java.io.IOException;

public class FileReaderEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileReader fin = null;
		int c;
		try{
			fin = new FileReader("test");
			while((c = fin.read()) != -1){
				System.out.print(c + " ");
			}
			fin.close();
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
	}

}
