import module java.desktop;
public class SudoAskPass{
	public static void main(String[] args){
		SwingUtilities.invokeLater(()->{
			JFrame jframe=new JFrame("sudo");
			JPasswordField field=new JPasswordField(20);
			JButton submit=new JButton("OK");
			JButton show=new JButton("show or hide password");
			JPanel bottom=new JPanel();
			bottom.setLayout(new FlowLayout());
			jframe.setLayout(new BorderLayout());
			jframe.add(field,BorderLayout.NORTH);
			bottom.add(submit);
			bottom.add(show);
			jframe.add(bottom,BorderLayout.SOUTH);
			char echo=field.getEchoChar();
			show.addActionListener(e->{
				field.setEchoChar(field.echoCharIsSet()?(char)0:echo);
			});
			submit.addActionListener(e->{
				try{
					char[] pass=field.getPassword();
					for(char ch:pass){
						System.out.print(ch);
					}
				}finally{
					System.exit(0);
				}
			});
			jframe.setSize(400,300);
			jframe.setVisible(true);
			jframe.addWindowListener(new WindowAdapter(){
				@Override
				public void windowClosing(WindowEvent e){
					System.exit(0);
				}
			});
		});
	}
}
