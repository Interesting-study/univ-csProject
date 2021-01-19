import java.io.FileOutputStream;
import java.io.IOException;

public class ByteStreamEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		byte[] b = {43, 2, -1, 17, 20};
		try{
			FileOutputStream fout =  new FileOutputStream("test.out");
			for(int i=0; i<b.length; i++){
				fout.write(b);
				fout.close();
			}
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
	}

}
