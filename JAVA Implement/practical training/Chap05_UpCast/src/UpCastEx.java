class Person{
	int age;
	String name;
}

class Student extends Person{
	String depart;
}

public class UpCastEx {

	public static void main(String[] args) {
		Student s = new Student();
		Person p = s;
		
		p.age = 10;
		p.name = "hong";
		//p.depart = "com";
		
		s.age = 10;
		s.name = "hong";
		s.depart = "com";
		

	}

}
