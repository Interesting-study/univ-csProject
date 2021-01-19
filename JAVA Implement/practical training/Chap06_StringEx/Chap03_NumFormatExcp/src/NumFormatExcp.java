
public class NumFormatExcp {
	public static void main(String[] args) {
		try {
			for(int i = 0; i < args.length; i++) {
				int n = Integer.parseInt(args[i]);
				System.out.println(n);
			}
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
		}
	}
}
