class ArrayParameter{
	void replaceSpace(char[] a){
		for(int i=0; i<a.length; i++){
			if(a[i] == ' ')
				a[i] = ',';
		}
	}
	void printArray(char[] a){
		for(int i=0; i<a.length; i++)
			System.out.print(a[i]);
	}
}
public class ArrayParameterEx {

	public static void main(String[] args) {
		ArrayParameter obj1 = new ArrayParameter();
		char[] arr = {'T','h','i','s',' ', 'i', 's', ' ', 'a', 'p','p'};
		obj1.replaceSpace(arr);
		obj1.printArray(arr);
	}

}
