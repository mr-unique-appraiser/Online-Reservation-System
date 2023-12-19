import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class Select_seat {

	public JFrame frame4;
	public String coach[];
	public Object seat[];
	public Reservation_form obj=new Reservation_form();
	public Connection con;
	public Login_Page lp=new Login_Page();
	private JTextField Mobile_no;
	private JTextField textField;


	/**
	 * Launch the application.
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Select_seat window = new Select_seat();
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
	public Select_seat() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame4 = new JFrame();
		frame4.setBounds(100, 100, 1355, 768);
		frame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame4.getContentPane().setLayout(null);
		
		
		textField = new JTextField();
		textField.setToolTipText("YYYY-MM-DD");
		textField.setBounds(544, 137, 130, 32);
		frame4.getContentPane().add(textField);
		textField.setColumns(10);
		 
		
		JLabel date = new JLabel("Date   :");
		date.setFont(new Font("Tahoma", Font.PLAIN, 20));
		date.setBounds(431, 137, 82, 23);
		frame4.getContentPane().add(date);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			   con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","1234");
			   Statement st=con.createStatement();
			   ResultSet rs=st.executeQuery("select coach from train_details where train_no="+Reservation_form.value+";");
			   rs.next();
			   coach=rs.getString("coach").split(",");
			   ResultSet rs2=st.executeQuery("select seat_no,date_of_journey from ticket_details where train_no="+Reservation_form.value+";");
			   seat=new Object[75];
			   List<Integer> s=new ArrayList<Integer>();
			   while(rs2.next()) {
				   s.add(rs2.getInt("seat_no"));
			   }
			   int k=0;
			   for(int i=1;i<=75;i++) {
				   if(s.contains(i))
					   continue;
				   seat[k++]=i;
			   }
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(frame4,e);
		}
		final JComboBox<?> comboBox = new JComboBox<Object>(coach);
		comboBox.setBounds(544, 65, 133, 32);
		frame4.getContentPane().add(comboBox);
		
		final JComboBox<?> comboBox_1 = new JComboBox<Object>(seat);
		comboBox_1.setBounds(544, 216, 133, 32);
		frame4.getContentPane().add(comboBox_1);
		
		JLabel lblNewLabel = new JLabel("Select  Coach   :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(368, 75, 162, 22);
		frame4.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Seat No   :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(410, 217, 103, 23);
		frame4.getContentPane().add(lblNewLabel_1);

		Mobile_no = new JTextField();
		Mobile_no.setBounds(544, 294, 133, 32);
		frame4.getContentPane().add(Mobile_no);
		Mobile_no.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Mobile Number   :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(350, 289, 180, 32);
		frame4.getContentPane().add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Book");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String res=Reservation_form.value+(String) comboBox.getItemAt(comboBox.getSelectedIndex()) + comboBox_1.getItemAt(comboBox_1.getSelectedIndex());
				String query="insert into ticket_details values(?,?,?,?,?,?,?,?)";
				String d=textField.getText();
				if(Mobile_no.getText().equals("") || textField.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Missing Feilds");
					Mobile_no.requestFocus();
				}
				else {
				try {
					PreparedStatement st=(PreparedStatement) con.prepareStatement(query);
					 st.setString(1,res);
						st.setString(2,Login_Page.uid);
						st.setString(3,Reservation_form.src);
						st.setString(4, Reservation_form.dst);
						st.setInt(5, (int) (comboBox_1.getItemAt(comboBox_1.getSelectedIndex())));
						st.setDate(6, Date.valueOf(d));
						st.setString(7, Mobile_no.getText());
						st.setInt(8,Integer.parseInt((String) Reservation_form.value));
					st.executeUpdate();
					JOptionPane.showMessageDialog(frame4, "Success");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(499, 443, 103, 32);
		frame4.getContentPane().add(btnNewButton);
		
		
	}
}
