import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class ArrayListEx {
	static void printList(ArrayList<String> list){
		/*for(int i=0; i<list.size(); i++)
			System.out.println(list.get(i));*/
		Iterator<String> it = list.iterator();
		while(it.hasNext())
			System.out.println(it.next());
	}
	static void searchLongName(ArrayList<String> list){
		String longName = "";
		String temp;
		for(int i=0; i<list.size(); i++){
			temp = list.get(i);
			if(temp.length() > longName.length())
				longName = temp;
		}
		System.out.println("LongName : " + longName);	
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> list = new ArrayList<String>();
		Scanner sc = new Scanner(System.in);
		for(int i=0; i<4; i++){
			System.out.print("input name : ");
			list.add(sc.next());
		}
		printList(list);
		searchLongName(list);
	}

}
