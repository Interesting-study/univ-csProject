class Book{
	String title, author;
	int isbn;
	
	Book(){
		this("정보없음", "미정", -1);
	}
	Book(String title){
		this(title, "미정", -1);
	}
	Book(String title, String author){
		this(title, author, -1);
	}
	Book(String title, String author, int isbn){
		this.title = title;
		this.author = author;
		this.isbn = isbn;
	}
	void printInfo(){
		System.out.println(title + ", " + author + ", " + isbn);
	}
}
public class BookEx {
	public static void main(String[] args){
		Book b1 = new Book();
		Book b2 = new Book("java");
		Book b3 = new Book("C", "hong");
		Book b4 = new Book("network", "kim", 123);
		
		b1.printInfo();
		b2.printInfo();
		b3.printInfo();
		b4.printInfo();
	}
}
