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
	}//age�� default �����������̹Ƿ� �̷��� �޼ҵ带 �߰���.
	public int getAge(){
		return age;
	}//age�� default �����������̹Ƿ� �̷��� �޼ҵ带 �߰���.
}
