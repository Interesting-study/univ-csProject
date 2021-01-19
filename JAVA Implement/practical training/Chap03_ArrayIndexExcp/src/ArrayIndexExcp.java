
public class ArrayIndexExcp {
	public static void main(String[] args){
		int arr[] = new int[10];
		try{
			for(int i=0; i<15; i++)
				arr[i] = i+1;
			for(int i=0; i<arr.length; i++)
				System.out.println(arr[i]);
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("배열의 사이즈는 " + e.getMessage());
		}
	}
}
