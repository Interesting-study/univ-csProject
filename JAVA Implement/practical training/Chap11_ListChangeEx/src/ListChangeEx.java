import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class ListChangeEx extends JFrame{
	ListChangeEx(){
		setTitle("ListChangeEx");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());

		Vector<String> v = new Vector<String>();
		JList<String> list = new JList<String>(v);
		c.add(new JLabel("이름입력후 엔터"));
		JTextField tf = new JTextField(10);
		c.add(tf);
		v.add("홍길동");
		v.add("김세정");
		c.add(new JScrollPane(list));
		
		tf.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				v.add(tf.getText());
				tf.setText("");
				list.setListData(v);
				
			}
		
		});
		
		setSize(500, 500);
		setVisible(true);
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ListChangeEx();
	}

}
