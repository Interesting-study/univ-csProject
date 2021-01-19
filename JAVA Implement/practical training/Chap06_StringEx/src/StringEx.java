
public class StringEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str1 = "java";
		String str2 = "		java Programming  ";
		String str3 = new String("java,programming,is,fun");
		String str4 = new String("java");
		String str5 = new String(new char[] {'j','a','v','a'});
		String str6 = str1.concat(" Programming");
		System.out.println("str6 : " + str6);
		System.out.println(str1.compareTo("java"));
		System.out.println(str1.charAt(2));
		System.out.println(str2.trim());
		
		System.out.println(str1.replace("ja", "JA"));
		System.out.println(str2.substring(10, 13));
		String[] s = str3.split(",");
		for(int i=0; i<s.length; i++)
			System.out.println(s[i]);
	}

}
