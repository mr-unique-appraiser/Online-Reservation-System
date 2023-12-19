import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import com.mysql.jdbc.PreparedStatement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Registration_Form {
	JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField textField_1;
	public Registration_Form() {
		initialize();
	}
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 914, 776);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username :");
		lblNewLabel.setFont(new Font("MS PGothic", Font.PLAIN, 25));
		lblNewLabel.setBounds(183, 164, 129, 38);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password :");
		lblNewLabel_1.setFont(new Font("MS PGothic", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(183, 212, 129, 38);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Confirm Password :");
		lblNewLabel_2.setFont(new Font("MS PGothic", Font.PLAIN, 25));
		lblNewLabel_2.setBounds(93, 260, 219, 38);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(322, 160, 228, 38);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Register");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","1234");
					PreparedStatement st=null;
					char[] pw=passwordField.getPassword();
					String uname=textField.getText();
					String PWord1=new String(pw); 
					pw=passwordField_1.getPassword();
					String PWord2=new String(pw);
					String uid=textField_1.getText();
					
					if(uname.equals("") || uid.equals("") || PWord1.equals("") || PWord2.equals(""))
					{
						JOptionPane.showMessageDialog(null, "Missing Feilds");
						textField_1.requestFocus();
					}
					else {
					if(!PWord1.equals(PWord2)) {
						JOptionPane.showMessageDialog(frame,"Passwords doesn't match"+PWord1+" "+PWord2);
						passwordField_1.setText("");
						passwordField.setText("");
					}
					else {
						try {
						String query="insert into userdetails values(?,?,?)";
						st=(PreparedStatement) conn.prepareStatement(query);
							String check = "select UserID from userdetails where UserID='"+uid+"'";
							Statement stmt = conn.createStatement();
							 ResultSet rs = stmt.executeQuery(check);
							 if(rs.next()) {
								 JOptionPane.showMessageDialog(frame, "Already Exist");
								 textField.setText("");
								 textField_1.setText(null);
								 passwordField.setText(null);
								 passwordField_1.setText("");
							 }
							 else {
								 st.setString(1,uid);
									st.setString(2,uname);
									st.setString(3,PWord1);
								st.executeUpdate();
								JOptionPane.showMessageDialog(frame,"Close this window and Login");
							 }
							
						}
						catch (Exception ie) {
				            ie.printStackTrace();				        }
					}
				}
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("MS PGothic", Font.BOLD, 20));
		btnNewButton.setBounds(242, 350, 149, 46);
		frame.getContentPane().add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(322, 212, 228, 38);
		frame.getContentPane().add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(322, 266, 228, 38);
		frame.getContentPane().add(passwordField_1);
		
		JLabel lblNewLabel_3 = new JLabel("User ID :");
		lblNewLabel_3.setFont(new Font("MS PGothic", Font.PLAIN, 25));
		lblNewLabel_3.setBounds(211, 111, 101, 26);
		frame.getContentPane().add(lblNewLabel_3);
		
		textField_1 = new JTextField();
		textField_1.setBounds(322, 111, 228, 39);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
	}
}
