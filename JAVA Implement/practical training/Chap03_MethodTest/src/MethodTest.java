
public class MethodTest {

	int[] makeArray(int n) {
		int[] arr = new int[n];
		
		for(int i=0; i<n; i++)
			arr[i] = i+1;
		
		return arr;
	}
	
	public static void main(String[] args) {
		MethodTest m = new MethodTest();
		int[] array = m.makeArray(20);
		
		for(int i=0; i<array.length; i++)
			System.out.print(array[i]+" ");

	}

}
