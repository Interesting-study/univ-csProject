
public class Array2d {
	public static void main(String[] args){
		int[][] num = new int[3][4];
		for(int i=0; i<num.length; i++)
			for(int j=0; j<num[i].length; j++)
				num[i][j] = (i+1)*(j+1);
		
		for(int i=0; i<num.length; i++){
			for(int j=0; j<num[i].length; j++)
				System.out.print(num[i][j] + " ");
			System.out.println("");
		}
	}
}
