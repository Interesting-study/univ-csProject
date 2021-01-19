class Person{}
class Student extends Person{}
class Researcher extends Person{}
class Professor extends Researcher{}

public class InstanceOfEx {
	static void print(Person p){
		if(p instanceof Person)
			System.out.println("Person");
		if(p instanceof Student)
			System.out.println("Student");
		if(p instanceof Researcher)
			System.out.println("Researcher");
		if(p instanceof Professor)
			System.out.println("Professor");
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Person -> ");
		print(new Person());
		System.out.println("Student -> ");
		print(new Student());
		System.out.println("Researcher -> ");
		print(new Researcher());
		System.out.println("Professor -> ");
		print(new Professor());
	}

}
