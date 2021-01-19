import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TextFieldEx extends JFrame{
	TextFieldEx(){
		setTitle("TextFieldEx");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());

		c.add(new JLabel("¿Ã∏ß : "));
		JTextField f = new JTextField(20);
		c.add(f);
		JLabel info =  new JLabel();
		//c.add(info);
		JTextArea ta = new JTextArea(5,20);
		c.add(new JScrollPane(ta));
		
		f.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				//info.setText(f.getText());
				ta.append(f.getText() + "\n");
				f.setText("");
				//f.setEditable(false);
			}
			
		});
		
		setSize(500, 500);
		setVisible(true);
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TextFieldEx();
	}

}
