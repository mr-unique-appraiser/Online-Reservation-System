import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import com.mysql.jdbc.Connection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
public class Reservation_form {

	public JFrame frame3;
	private JTextField source;
	private JTextField destination;
	public List<String> res=new ArrayList<String>();
	public Object[][] result;
	public JTable table;
	public static Object value;
	public static String src;
	public static String dst;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reservation_form window = new Reservation_form();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public Reservation_form() {
		initialize();
	}
	private void initialize() {
		frame3 = new JFrame();
		frame3.setBounds(100, 100, 1071, 772);
		frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame3.getContentPane().setLayout(null);
		
		source = new JTextField();		
		source.setToolTipText("From Station");
		source.setText("");
		source.setBounds(388, 37, 193, 41);
		frame3.getContentPane().add(source);
		source.setColumns(10);
		
		destination = new JTextField();
		destination.setToolTipText("To Station");
		destination.setBounds(388, 88, 193, 41);
		frame3.getContentPane().add(destination);
		destination.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("From :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(272, 38, 71, 33);
		frame3.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("To :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(298, 96, 45, 33);
		frame3.getContentPane().add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Find Trains");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					   Class.forName("com.mysql.jdbc.Driver");
					   Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","1234");
					   Statement st=con.createStatement();
					   ResultSet rs=st.executeQuery("select * from train_details;");
					   //rs.next();
					   src=source.getText().toUpperCase();
					   dst=destination.getText().toUpperCase();
					   int k=0;
					  while(rs.next()) {
						   int dstart=-1;
						   boolean s=false,d=false;
					   String start[]=rs.getString("Start_City").split(",");
					   String dest[]=rs.getString("Dest_City").split(",");
					   for(int i=0;i<start.length;i++) {
						   if(start[i].equals(src)) {
							   dstart=i;
							   s=true;
							   break;
						   }
					   }
					   if(dstart==-1) {
						   continue;
					   }
					   for(int i=dstart;i<dest.length;i++) {
						   if(dest[i].equals(dst)) {
							   d=true;
							   break;
						   }
					   }
					   if(d && s) {
						   String t_name=rs.getString("Train_No")+","+rs.getString("Train_Name")+","+"Book Now";
						  res.add(t_name);
					   }
					   }
					  result=new Object[res.size()][3];
					  for(String i:res) {
						  String[] t=i.split(",");
						  result[k][0]=new String(t[0]);
						  result[k][1]=new String(t[1]);
						  result[k][2]=new String(t[2]);
						  k++;
					  }
					  String []col=new String[] {"Train No.","Train Name","Booking"};
						table = new JTable(result,col);
						table.setBounds(227, 321, 606, 332);
						table.setVisible(true);
						frame3.getContentPane().add(table);
						 JScrollPane scrollPane = new JScrollPane(table);
						scrollPane.setBounds(227, 321, 606, 332);
						 scrollPane.setVisible(true);
						 frame3.add(scrollPane);
						 table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						 ListSelectionModel selectionModel = table.getSelectionModel();

						 selectionModel.addListSelectionListener(new ListSelectionListener() {
						     public void valueChanged(ListSelectionEvent e) {
						    	 TableModel tm = table.getModel();
						    	 int sel[]=table.getSelectedRows();
						         value= tm.getValueAt(sel[0],0);
						         frame3.setVisible(false);
						         Select_seat s=new Select_seat();
						         s.frame4.setVisible(true);
						         //JOptionPane.showMessageDialog(frame3,value);
						         
						     }
						 });
						 
					 
				}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(frame3, e1);
				}	
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(298, 177, 377, 47);
		frame3.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Swap");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String from=source.getText();
				String to=destination.getText();
				source.setText(to);
				destination.setText(from);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnNewButton_1.setBounds(614, 66, 85, 33);
		frame3.getContentPane().add(btnNewButton_1);
		
	}
}
