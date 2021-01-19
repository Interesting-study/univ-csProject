class Person{
	private int weight;
	int age;
	protected int height;
	public String name;
	
	public void setWeight(int weight){
		this.weight = weight;
	}
	public int getWeight(){
		return weight;
	}
}
class Student extends Person{
	public void set(){
		setWeight(70);
		age = 17;
		height = 135;
		name = "hong";
	}
}
public class StudentEx {
	public static void main(String args[]){
		Student s = new Student();
		s.set();
		System.out.println(s.getWeight());
		System.out.println(s.age);
		System.out.println(s.height);
		System.out.println(s.name);
	}
}
