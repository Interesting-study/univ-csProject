

public class forEachEx {
	enum WEEK{��, ȭ, ��, ��, ��, ��, ��};
	public static void main(String[] args){
		String[] str = {"kim", "hong", "kang", "lee"};
		double[] dArr = {17.8, 5.4, 100.5, 45.9};
		
		for(String i:str)
			System.out.println(i);
		
		for(double i:dArr)
			System.out.println(i);
		
		for(WEEK w:WEEK.values())
			System.out.println(w);
	}
}
