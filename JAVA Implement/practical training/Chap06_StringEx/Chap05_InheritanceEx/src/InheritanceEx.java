class Person{
	private float weight;
	int age;
	protected float height;
	public String name;
	
	public void setWeight(float weight){
		this.weight = weight;
	}
	
	public float getWeight(){
		return weight;
	}
}

class Student extends Person{
	void set(){
		age = 10;
		height = 180.9f;
		name = "hong";
		//weight = 70.5;
		setWeight(70.5f);
	}
}

public class InheritanceEx {

	public static void main(String[] args) {
		Student s = new Student();
		
		s.set();
		System.out.println(s.age);
		System.out.println(s.height);
		System.out.println(s.name);
		//System.out.println(s.weight);
		System.out.println(s.getWeight());
	}

}
