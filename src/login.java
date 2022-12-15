import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class login {

	private static JFrame frame;
	private JTextField txtusername;
	private JPasswordField txtpass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login();
					login.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 428, 318);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("User Name");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setBounds(101, 80, 95, 32);
		lblNewLabel.setFont(new Font("Arial Unicode MS", Font.PLAIN, 14));
		frame.getContentPane().add(lblNewLabel);
		
		txtusername = new JTextField();
		txtusername.setBounds(190, 82, 126, 32);
		frame.getContentPane().add(txtusername);
		txtusername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.BLUE);
		lblPassword.setBounds(101, 141, 95, 32);
		lblPassword.setFont(new Font("Arial Unicode MS", Font.PLAIN, 14));
		frame.getContentPane().add(lblPassword);
		
		JButton btnlogin = new JButton("LOGIN");
		btnlogin.setBounds(173, 197, 89, 32);
		btnlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String uname = txtusername.getText();
				@SuppressWarnings("deprecation")
				String pass = txtpass.getText();
				
				
				
				
				
				
				if(uname.equals("admin") && pass.equals("1234") ||uname.equals("admin1") && pass.equals("12341")||uname.equals("admin2") && pass.equals("12342") ) {
					
					
				//	JOptionPane.showMessageDialog(null, "login sucessfully");
					JavaCrud m= new JavaCrud();
					login.frame.setVisible(false);
					try {
						try {
							m.getFrame().setVisible(true);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				else {
					
					JOptionPane.showMessageDialog(null, "UserName & Password  do not mathch");
					
					
				} 
				
				
				
			}
		});
		btnlogin.setBackground(new Color(147, 112, 219));
		btnlogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		frame.getContentPane().add(btnlogin);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 255, 255));
		panel.setBounds(0, 0, 412, 47);
		frame.getContentPane().add(panel);
		
		JLabel lblNewLabel_1 = new JLabel("Ganpati Book Shop");
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Arial Black", Font.BOLD, 30));
		lblNewLabel_1.setForeground(Color.RED);
		
		txtpass = new JPasswordField();
		txtpass.setBounds(190, 137, 126, 32);
		frame.getContentPane().add(txtpass);
	}
}
