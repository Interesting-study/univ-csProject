package PackB;

import PackA.Person;

class Student extends Person{
	public void set(){
		setWeight(70);
		setAge(17);
		height = 135;
		name = "hong";
	}
	public int getHeight(){
		return height;
	}
}
public class StudentEx {
	public static void main(String args[]){
		Student s = new Student();
		s.set();
		System.out.println(s.getWeight());
		System.out.println(s.getAge());
		System.out.println(s.getHeight());
		//height의 접근지정자가 protected이므로 main에서 사용하기 위해 getHeight를 만듦.
		System.out.println(s.name);
	}
}
