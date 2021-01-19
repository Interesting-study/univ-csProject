package PackA;

public class Person {
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
	public void setAge(int age){
		this.age = age;
	}//age가 default 접근지정자이므로 이러한 메소드를 추가함.
	public int getAge(){
		return age;
	}//age가 default 접근지정자이므로 이러한 메소드를 추가함.
}
