import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Reserve_or_Cancel {

	public JFrame frame2;

	public Reserve_or_Cancel() {
		initialize();
	}

	private void initialize() {
		frame2 = new JFrame();
		frame2.setBounds(100, 100, 1092, 763);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Reservation");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reservation_form obj=new Reservation_form();
				frame2.setVisible(false);
				obj.frame3.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.setBounds(135, 332, 285, 56);
		frame2.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancellation");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cancellation_form obj=new Cancellation_form();
				frame2.setVisible(false);
				obj.frame5.setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton_1.setBounds(636, 332, 285, 56);
		frame2.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Online Reservation System");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(385, 78, 245, 56);
		frame2.getContentPane().add(lblNewLabel);
	}
}
