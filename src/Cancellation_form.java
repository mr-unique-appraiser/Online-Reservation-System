import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.awt.event.ActionEvent;

public class Cancellation_form {

	public JFrame frame5;
	private JTextField textField;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cancellation_form window = new Cancellation_form();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Cancellation_form() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame5 = new JFrame();
		frame5.setBounds(100, 100, 1338, 757);
		frame5.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame5.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter PNR number    :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(347, 126, 213, 48);
		frame5.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(570, 136, 197, 37);
		frame5.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Cancel Ticket Now");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					   Class.forName("com.mysql.jdbc.Driver");
					   Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","1234");
					   String pnr=(String)(textField.getText());
					   String query="delete from ticket_details where pnr_no='"+pnr+"';";
					   PreparedStatement st=(PreparedStatement)con.prepareStatement(query);
					   int i=st.executeUpdate(query);
					   if(i!=0) {
					   JOptionPane.showMessageDialog(frame5, "Ticket Cancelled Successfully");
					   textField.setText("");
					   }
					   else {
						   JOptionPane.showMessageDialog(frame5, "Ticket not found");
						   textField.setText("");
					   }
				}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(frame5,"Something went wrong");
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(484, 233, 197, 37);
		frame5.getContentPane().add(btnNewButton);
	}
}
