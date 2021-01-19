
public class Array1d {
	public static void main(String[] args){
		int[] num = new int[10];
		for(int i=0; i<num.length; i++){
			num[i] = (i+1)*10;
		}
		
		for(int i:num)
			System.out.println(i);
		
		int max = 0;
		for(int i=0; i<num.length; i++){
			if(num[i] > max)
				max = num[i];
		}
		System.out.println("max : " + max);
	}
}
