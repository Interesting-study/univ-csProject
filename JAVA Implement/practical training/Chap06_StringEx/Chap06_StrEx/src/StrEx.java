//3
public class StrEx {

	public static void main(String[] args) {
		String str1 = "Java";
		String str2 = "Hello";
		String str3 = new String("\t\tHello Java  -");
		String str4 = new String(new char[] {'J', 'a', 'v', 'a'});
		
		System.out.println(str1.concat(str2));
		System.out.println(str1);
		System.out.println(str2);
		
		System.out.println(str1.compareTo(str4));
		System.out.println(str1 == str4);
		
		System.out.println(str3.trim());
		System.out.println(str2.charAt(1));
		
		System.out.println(str3.replace("Java", "C++"));
		System.out.println(str3.substring(10));
		String str5 = "Java,Programming,is,fun";
		String[] str = str5.split(",");
		for(int i=0; i<str.length; i++)
			System.out.println(str[i]);
	}
}