import java.util.StringTokenizer;

public class StringTokenEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "java,programming,is,fun";
		StringTokenizer st = new StringTokenizer(str,",");
		while(st.hasMoreTokens())
			System.out.println(st.nextToken());
	}

}
