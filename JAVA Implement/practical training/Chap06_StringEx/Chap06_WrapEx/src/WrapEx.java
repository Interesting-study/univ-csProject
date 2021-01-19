//2
public class WrapEx {

	public static void main(String[] args) {
		Integer i = new Integer(10);//Boxing
		int ii = i.intValue();//UnBoxing
		
		Integer i2 = 20;//Auto Boxing
		int ii2 = i2;//Auto UnBoxing
		
		System.out.println(Integer.toBinaryString(10));
		System.out.println(Integer.toHexString(10));
		System.out.println(Character.isDigit('1'));
		System.out.println(Character.isAlphabetic('A'));
	}

}
