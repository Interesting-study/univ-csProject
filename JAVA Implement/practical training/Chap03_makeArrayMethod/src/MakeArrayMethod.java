
public class MakeArrayMethod {
	int[] makeArray(int n){
		int arr[] = new int[n];
		for(int i=0; i<arr.length; i++)
			arr[i] = i+1;
		return arr;
	}
	public static void main(String[] args){
		MakeArrayMethod m = new MakeArrayMethod();
		int r[] = m.makeArray(10);
		//for(int i=0; i<r.length; i++)
			//System.out.println(r[i]);
		for(int i:r)
			System.out.println(i);
	}//
}
