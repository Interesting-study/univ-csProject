
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
		obj.replaceSpace(arr);//���� �ƴ� ���۷���(������)�� �ѱ�.
		obj.printArray(arr);
	}
}//Ȥ�� static�� �ٿ��ִ� ����� �ִ�.
/*
 * static : class �Ҽ� -> '����'���� ����Ѵ�.
 * not- static : object �Ҽ� -> '����'
 * */
 