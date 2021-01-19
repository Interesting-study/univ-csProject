
public class NumFormatExcpEx {

	public static void main(String[] args) {
		try{
			for(int i=0; i<args.length; i++){
				System.out.println(Integer.parseInt(args[i]));
			}
		}catch(NumberFormatException e){
			System.out.println(e.getMessage()+"->정수가 아닙니다.");
		}
	}

}