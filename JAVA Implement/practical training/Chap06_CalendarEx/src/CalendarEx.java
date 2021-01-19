import java.util.Calendar;

public class CalendarEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calendar now = Calendar.getInstance();
		int y = now.get(Calendar.YEAR);
		int m = now.get(Calendar.MONTH) +1;
		int d = now.get(Calendar.DATE);
		int min = now.get(Calendar.MINUTE);
		int s = now.get(Calendar.SECOND);
		
		System.out.println(y);
		System.out.println(m);
		System.out.println(d);
		System.out.println(min);
		System.out.println(s);
	}

}
