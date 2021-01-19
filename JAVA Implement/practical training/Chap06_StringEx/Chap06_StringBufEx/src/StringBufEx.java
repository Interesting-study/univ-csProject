//4
import java.util.StringTokenizer;

public class StringBufEx {

	public static void main(String[] args) {
		StringBuffer sb = new StringBuffer("Java");
		
		sb.append(" is fun");
		System.out.println(sb);
		
		sb.insert(0, "***");
		System.out.println(sb);
		
		sb.replace(0, 4, "C++");
		System.out.println(sb);
		
		sb.delete(0, 3);
		System.out.println(sb);
		
		StringTokenizer st = new StringTokenizer("Java Programming is fun", " ");
		while(st.hasMoreTokens()){
			System.out.println(st.nextToken());
		}
	}
}