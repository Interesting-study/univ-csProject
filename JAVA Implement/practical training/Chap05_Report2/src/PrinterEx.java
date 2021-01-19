class Printer{
	private String model, comp;
	private int paper;
	private static int tot =0;
	
	public Printer(String model, String comp, int paper){
		this.model = model;
		this.comp = comp;
		this.paper = paper;
		incre_tot(paper);
	}
	public String getModel(){
		return model;
	}
	public String getComp(){
		return comp;
	}
	public int getPaper(){
		return paper;
	}
	public static int getTot(){
		return tot;
	}
	void Print(){
		paper--;
		decre_tot();
	}
	void incre_tot(int n){
		tot += n;
	}
	void decre_tot(){
		tot--;
	}
}
class Inkjet extends Printer{
	private int ink;
	Inkjet(String model, String comp, int paper, int ink){
		super(model, comp, paper);
		this.ink = ink;
	}
	public int getInk(){
		return ink;
	}
	void inkPrint(){
		ink--;
		Print();
		System.out.println("Ink : " + getInk() + " Paper : " + getPaper());
	}
}
class Lajer extends Printer{
	private float toner;
	Lajer(String model, String comp, int paper, float toner){
		super(model, comp, paper);
		this.toner = toner;
	}
	public float getToner(){
		return toner;
	}
	void LajerPrint(){
		toner -= 0.1f;
		Print();
		System.out.println("Toner :  " + getToner() + " Paper : " + getPaper());
	}
}
public class PrinterEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Inkjet ink = new Inkjet("hp1234", "hp", 100, 1000);
		Lajer laj = new Lajer("epson1234", "epson", 200, 100.0f);
		System.out.println("tot : " + Printer.getTot());
		for(int i=0; i<10; i++)
			ink.inkPrint();
		laj.LajerPrint();
		System.out.println("tot : " + Printer.getTot());
	}

}
