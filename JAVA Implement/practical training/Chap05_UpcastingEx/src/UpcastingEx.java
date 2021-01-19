class Person{
	String name, id;
	Person(String name){
		this.name = name;
	}
}
class Student extends Person{
	String depart;
	Student(String name){
		super(name);
	}
}
public class UpcastingEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*Student s = new Student("hong");
		Person p = s;
		p.name = "kim";
		p.id = "123";
		//p.depart = "Com";
		s.name = "kim";
		s.id = "123";
		s.depart = "Com";*/
		Person p = new Student("kang");
		Student s = (Student)p;
		s.name = "kim";
		s.id = "123";
		s.depart = "Com";
	}

}
