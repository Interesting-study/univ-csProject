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
		h.put("apple", "���");
		h.put("banana", "�ٳ���");
		h.put("kiwi", "Ű��");
		h.put("mango", "����");
		
		Scanner sc= new Scanner(System.in);
		while(true){
			System.out.print("input key : ");
			String key = sc.next();
			if(key.equals("exit")){
				System.out.println("����˴ϴ�!");
				break;
			}
				
			search(h,key);
		}
	}

}
//�ؽø� ��ü�� ����ϴ� ��ɵ� ������ ��
