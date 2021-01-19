
public class MainArgu {
	public static void main(String[] args){
		//for(int i=0; i<args.length; i++)
			//System.out.println(args[i]);
		int sum = 0;
		for(int i=0; i<args.length; i++)
			sum += Integer.parseInt(args[i]);
		System.out.println(sum);
	}
}
