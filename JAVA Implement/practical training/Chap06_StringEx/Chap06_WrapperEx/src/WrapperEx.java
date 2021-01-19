
public class WrapperEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Integer i = new Integer(10);//boxing
		Integer i = 10;//auto boxing
		Character c = new Character('A');
		
		//int ii = i.intValue();//unboxing
		int ii =i;//auto unboxing
		
		System.out.println(i.toBinaryString(ii));
		System.out.println(i.toHexString(ii));
		System.out.println(c.toLowerCase('A'));
		System.out.println(Integer.parseInt("100"));
		
	}

}
