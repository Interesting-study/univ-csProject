interface Phone{
	int BTN = 20;
	void sendCall();
	void receiveCall();
}
interface MobilePhone extends Phone{
	void sendSMS();
	void receiveSMS();
}
interface MP3Phone extends Phone{
	void setRingTone();
}
interface AI{
	void recognizeSpeech();
	void recognizeChar();
}
interface AIPhone extends Phone, AI{
	void mp3Play();
	void mp3Stop();
}
class SamSungPhone implements MobilePhone, AI{

	@Override
	public void recognizeSpeech() {
		System.out.println("음성인식");
	}

	@Override
	public void recognizeChar() {
		System.out.println("문자인식");
	}

	@Override
	public void sendCall() {
		System.out.println("전화걸기");
	}

	@Override
	public void receiveCall() {
		System.out.println("전화받기");
	}

	@Override
	public void sendSMS() {
		System.out.println("문자보내기");
	}

	@Override
	public void receiveSMS() {
		System.out.println("문자받기");
	}
	
}
public class InterfaceEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

