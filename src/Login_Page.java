import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login_Page {

	 JFrame frame;
	 JTextField textField;
	 JPasswordField passwordField;
	 public static String uid;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login_Page window = new Login_Page();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	
	}

	public Login_Page() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 20));
		frame.setBounds(100, 100, 1310, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username :");
		lblNewLabel.setForeground(new Color(64, 0, 64));
		lblNewLabel.setFont(new Font("MS PGothic", Font.PLAIN, 25));
		lblNewLabel.setBounds(591, 133, 127, 42);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField.setBounds(728, 133, 201, 42);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password :");
		lblNewLabel_1.setBackground(new Color(0, 0, 255));
		lblNewLabel_1.setFont(new Font("MS PGothic", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(591, 203, 127, 42);
		frame.getContentPane().add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(728, 203, 201, 42);
		frame.getContentPane().add(passwordField);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				uid=textField.getText();
				char[] pw=passwordField.getPassword();
				String PWord=new String(pw);
				try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","1234");
				Statement st=conn.createStatement();
				ResultSet rs=st.executeQuery("select Password from userdetails where UserID='"+uid+"';");
				rs.next();
				try {
				if(PWord.equals(rs.getString("Password"))) {
					JOptionPane.showMessageDialog(frame,"Login Successful");
					Reserve_or_Cancel obj=new Reserve_or_Cancel();
					frame.setVisible(false);
					obj.frame2.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(frame,"Login UnSuccessful");
					passwordField.setText("");
					textField.setText("");
				}
				}
				catch(Exception exp) {
					JOptionPane.showMessageDialog(frame,"UserId doesn't exist");
					passwordField.setText("");
					textField.setText("");
				}
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.setBounds(727, 297, 107, 42);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("Not have an account?");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(670, 349, 186, 37);
		frame.getContentPane().add(lblNewLabel_2);
		
		final JLabel lblNewLabel_3 = new JLabel("Sign Up");
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Registration_Form window = new Registration_Form();
							window.frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 18));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
			}
		});
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3.setBounds(821, 356, 78, 23);
		frame.getContentPane().add(lblNewLabel_3);
	}
}
