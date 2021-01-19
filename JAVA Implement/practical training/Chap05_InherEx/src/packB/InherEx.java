package packB;

import packA.Person;

class Student extends Person{
	void set(){
		//age = 20;
		setAge(20);
		height = 180.1f;
		name = "hong";
		setWeight(70.5f);
	}
	float getHeight(){
		return height;
	}
}

public class InherEx {
	public static void main(String[] args) {
		Student s = new Student();
		
		s.set();
		System.out.println(s.getAge());
		//System.out.println(s.height);
		System.out.println(s.getHeight());
		System.out.println(s.name);
		//System.out.println(s.weight);
		System.out.println(s.getWeight());
	}
}

