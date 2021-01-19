
public class ArrIdxExcp {

	public static void main(String[] args) {
		int[] arr = new int[10];
		
		try{
			arr[20] = 100;
			
			for(int i=0; i<arr.length; i++)
				System.out.println(arr[i]);
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("ÀÎµ¦½º ¹üÀ§¸¦ ¹þ¾î³µ½À´Ï´Ù.");
		}
	}

}
