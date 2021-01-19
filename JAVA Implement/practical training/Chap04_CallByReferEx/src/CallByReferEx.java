public class CallByReferEx {
	
	static void printArray(char[] c){
		for(int i=0; i<c.length; i++){
			System.out.print(c[i]);
		}
		System.out.println();
	}
	static void replaceSpace(char[] c){
		for(int i=0; i<c.length; i++){
			if(c[i] == ' ')
				c[i] = ',';
		}
	}

	public static void main(String[] args) {
		char[] c = {'p','e','n',' ','c',' ','i',' ','l'};
		
		printArray(c);
		replaceSpace(c);
		printArray(c);		

	}

}
