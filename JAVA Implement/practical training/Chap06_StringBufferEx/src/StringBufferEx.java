
public class StringBufferEx {

	public static void main(String[] args) {
		StringBuffer sb = new StringBuffer("java");
		sb.append(" is");
		System.out.println(sb);
		System.out.println(sb.length());
		sb.delete(4, 5);
		System.out.println(sb);
		sb.replace(4, 6, "**");
		System.out.println(sb);
		sb.insert(0, "**");
		System.out.println(sb);
		
	}

}
