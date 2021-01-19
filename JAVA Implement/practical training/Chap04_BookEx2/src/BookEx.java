class Book{
	String title, author;
	int isbn;
	
	Book(){
		this.title = "정보없음";
		this.author = "미정";
		this.isbn = -1;
		//this("정보없음", "미정", -1);
		
	}
	
	Book(String title){
	/*  this.title = title;
		this.author = "미정";
		this.isbn = -1;*/
		//this(title, "미정", -1);
		this();
		this.title = title;
	}
	
	Book(String title, String author){
	/*  this.title = title;
		this.author = author;
		this.isbn = -1;*/
		//this(title, author, -1);
		this(title);
		this.author = author;
	}
	
	Book(String title, String author, int isbn){
		//this.title = title;
		//this.author = author;
		this(title, author);
		this.isbn = isbn;
		
	}
	
	void printInfo(){
		System.out.println(title+","+author+","+isbn);
	}
}
public class BookEx {

	public static void main(String[] args) {
		Book b1 = new Book();
		Book b2 = new Book("java");
		Book b3 = new Book("c", "Hong");
		Book b4 = new Book("c++", "kim", 123);
		
		b1.printInfo();
		b2.printInfo();
		b3.printInfo();
		b4.printInfo();
		
	}

}
