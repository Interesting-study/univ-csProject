import java.util.HashMap;
import java.util.Scanner;

public class HashMapEx {
	static void search(HashMap<String, String> h, String key){
		String value = h.get(key);
		if(value == null)
			System.out.println("No Item");
		else
			System.out.println(value);

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<String, String> h = new HashMap<String, String>();
		h.put("apple", "사과");
		h.put("banana", "바나나");
		h.put("kiwi", "키위");
		h.put("mango", "망고");
		
		Scanner sc= new Scanner(System.in);
		while(true){
			System.out.print("input key : ");
			String key = sc.next();
			if(key.equals("exit")){
				System.out.println("종료됩니다!");
				break;
			}
				
			search(h,key);
		}
	}

}
//해시맵 전체를 출력하는 기능도 만들어야 함
