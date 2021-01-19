
public class ArrayParameterUniEx {
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
	public static void main(String[] args) {
		ArrayParameterUniEx obj = new ArrayParameterUniEx();
		char[] arr = {'T','h','i','s',' ', 'i', 's', ' ', 'a', 'p','p'};
		obj.replaceSpace(arr);//값이 아닌 레퍼런스(참조값)를 넘김.
		obj.printArray(arr);
	}
}//혹은 static을 붙여주는 방법이 있다.
/*
 * static : class 소속 -> '공용'으로 사용한다.
 * not- static : object 소속 -> '개인'
 * */
 