import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class ScoreHashEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<String, Integer> h = new HashMap<String, Integer>();
		h.put("kim", 70);
		h.put("hong", 60);
		h.put("choi", 50);
		h.put("jung", 20);
		h.put("lee", 90);
		
		Set<String> keys = h.keySet();
		Iterator<String> it = keys.iterator();
		while(it.hasNext()){
			String key = it.next();
			System.out.println(key +" : "+ h.get(key));
		}
	}
}
