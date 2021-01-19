package Game369;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import MainScreen.MainScreen;

public class Game369 extends JFrame{
   public Game369(){
        setTitle("369 Game");
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				setVisible(false);
				dispose();
				new MainScreen();
			}
			
		});
        
        
        Container c = getContentPane();
        c.add(new GamePanel());//��ü�� ������ ���ÿ� �����ӿ� �߰�
        setLocationRelativeTo(null);
        setSize(310, 250);
        setVisible(true);
    }
}

class GamePanel extends JPanel{
    TimerThread th;//���� ī��Ʈ�� ���� ������ ����
    JLabel card = new JLabel();//���� ī��Ʈ�� ������ ���̺� ����
    JButton start = new JButton("Start");//���� ��ư ����
    int n=1;//ī��Ʈ ������ ����
    boolean singleClicked=false;//Ŭ�� ����
    boolean doubleClicked=false;//����Ŭ�� ����
    
    GamePanel(){
        //���� ī��Ʈ�� ������ ���̺��� �гο� �߰�
        this.setLayout(null);
        card.setOpaque(true);
        card.setBackground(Color.orange);
        card.setFont(new Font("Arial",Font.ITALIC,30));
        card.setHorizontalAlignment(JLabel.CENTER);
        card.setText(Integer.toString(n));
        card.setSize(100, 50);
        card.setLocation(100, 50);
        
        
        card.addMouseListener(new MouseListener(){
            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
                if(e.getClickCount()>=2){//���� Ŭ���̻� �Ͽ��� �� ����Ŭ������ �ν�
                    doubleClicked=true;
                    singleClicked=false;
                }
                else{//�ѹ� Ŭ������ ��
                    singleClicked=true;
                }
                //���콺�� ������ �� ���̺� ��ü�� �ҽ��� ���� ������ �ʷ����� ����
                JLabel card = (JLabel)e.getSource();
                card.setBackground(Color.green);
                }

            public void mouseReleased(MouseEvent e) {
                //���콺�� ������ �� ���̺� ��ü�� �ҽ��� ���� ������ �������� ����
                JLabel card = (JLabel)e.getSource();
                card.setBackground(Color.orange);
            }

            public void mouseEntered(MouseEvent e) {}

            public void mouseExited(MouseEvent e) {}
        });
        this.add(card);
        
        //��ư�� �гο� �߰�
        start.setLocation(100,150);
        start.setSize(100, 30);
        start.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                //��ưŬ���� �ʱ⼳���� ������ ��ü ������ ����
                n=1;
                card.setText(Integer.toString(n));
                singleClicked=false;
                doubleClicked=false;
                th = new TimerThread();
                th.start();
                
                //���� �߿��� ��ư Ŭ���� ���ϰ� ��
                JButton b = (JButton)ae.getSource();
                b.setEnabled(false);
            }
        });
        this.add(start);
    }
    
    class TimerThread extends Thread{
        public void run(){
            while(true){
                try{
                    sleep(700);//0.7�ʸ���

                    int d1=n%10;//���� �ڸ�
                    int d2=n/10;//���� �ڸ�

                    if((d1==3||d1==6||d1==9)&&(d2==3||d2==6||d2==9)){//����Ŭ���� �ؾ��� ��Ȳ
                        if(doubleClicked==true){//����Ŭ�� ������ �ٽ� �������� �ٲ���
                            doubleClicked=false;
                        }
                        else{//���н� �޼��� ���
                            card.setText("FAIL...");
                            break;
                        }
                    }
                    else if(d1==3||d1==6||d1==9||d2==3||d2==6||d2==9){//�ѹ�Ŭ���� �ؾ��� ��Ȳ
                        if(singleClicked==true){//������
                            singleClicked=false;
                        }
                        else{//���н�
                            card.setText("FAIL...");
                        }
                    }
                    else{//�ƹ��͵� Ŭ������ ���ƾ��� ��Ȳ
                        if(singleClicked==true||doubleClicked==true){//Ŭ���� ����
                            card.setText("FAIL...");
                            break;
                        }
                    }
                    
                    n++;//ī��Ʈ �� 1�� ����
                    if(n==100){//100���� ���� �¸�
                        card.setText("WIN!!");
                        break;
                    }
                    else{//100 �Ʒ��̸� ��� ����
                        card.setText(Integer.toString(n));
                    }
                }
                catch(Exception e){ 
                   return; 
                   }
            }
            start.setEnabled(true);//������ ������ ��ư Ȱ��ȭ
        }
    }
}
//public class Game{
//    public static void main(String[] args) {
//        new Game();
//    }
//}