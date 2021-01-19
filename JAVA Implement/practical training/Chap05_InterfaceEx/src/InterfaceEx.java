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
		System.out.println("�����ν�");
	}

	@Override
	public void recognizeChar() {
		System.out.println("�����ν�");
	}

	@Override
	public void sendCall() {
		System.out.println("��ȭ�ɱ�");
	}

	@Override
	public void receiveCall() {
		System.out.println("��ȭ�ޱ�");
	}

	@Override
	public void sendSMS() {
		System.out.println("���ں�����");
	}

	@Override
	public void receiveSMS() {
		System.out.println("���ڹޱ�");
	}
	
}
public class InterfaceEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

