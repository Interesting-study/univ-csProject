
public class MainArgumentes {

	public static void main(String[] args) {
		float sum = 0;
		
		for(int i=0; i<args.length; i++)
			sum += Float.parseFloat(args[i]);
		System.out.println(sum);
		

	}

}
