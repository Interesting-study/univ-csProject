import java.io.File;

public class FileEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File f = new File("C:\\windows\\system.ini");
		File ff = new File("D:\\temp");
		System.out.println(ff.getName());
		System.out.println(ff.getPath());
		System.out.println(ff.getParent());
		if(ff.isFile())
			System.out.println("File");
		else
			System.out.println("Directory");
		
	}

}
