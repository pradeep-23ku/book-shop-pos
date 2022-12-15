import java.awt.EventQueue;
import java.sql.*;


import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.EtchedBorder;


public class JavaCrud {

	private JFrame frame;
	private JTextField txtbname;
	private JTextField txtedition;
	private JTextField txtprice;
	private JTable table;
	private JTextField txtbid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JavaCrud window = new JavaCrud();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JavaCrud() {
		initialize();
		Connect();
		table_load();
	}
	
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField txtcopy;
	private JTextField txtsell;
	
	
	public void Connect()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/javacrud", "root","");
        }
        catch (ClassNotFoundException ex)
        {
          ex.printStackTrace();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
 
    }
	
	  public void table_load()
	    {
	     try
	     {
	    pst = con.prepareStatement("select * from books order by name asc");
	    rs = pst.executeQuery();
	    table.setModel(DbUtils.resultSetToTableModel(rs));
	}
	     catch (SQLException e)
	     {
	     e.printStackTrace();
	  }
	    }
	
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setFrame(new JFrame());
		getFrame().setBounds(100, 100, 748, 521);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		panel.setBounds(13, 61, 370, 245);
		getFrame().getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Book Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(32, 18, 80, 35);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Edition");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(43, 70, 80, 24);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Price");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(43, 112, 55, 24);
		panel.add(lblNewLabel_1_2);
		
		txtbname = new JTextField();
		txtbname.setBounds(159, 33, 161, 20);
		panel.add(txtbname);
		txtbname.setColumns(10);
		
		txtedition = new JTextField();
		txtedition.setBounds(159, 74, 161, 20);
		panel.add(txtedition);
		txtedition.setColumns(10);
		
		txtprice = new JTextField();
		txtprice.setBounds(159, 116, 161, 20);
		panel.add(txtprice);
		txtprice.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("No. of copy");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(43, 160, 89, 24);
		panel.add(lblNewLabel_2);
		
		txtcopy = new JTextField();
		txtcopy.setBounds(159, 164, 160, 20);
		panel.add(txtcopy);
		txtcopy.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("NOC to Sell");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_3.setBounds(43, 204, 107, 30);
		panel.add(lblNewLabel_3);
		
		txtsell = new JTextField();
		txtsell.setBounds(159, 211, 161, 20);
		panel.add(txtsell);
		txtsell.setColumns(10);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.setBackground(Color.YELLOW);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bname,edition,price,copy;
				bname= txtbname.getText();
				edition= txtedition.getText();
				price= txtprice.getText();
				copy=txtcopy.getText();
				
				try {
					pst = con.prepareStatement("insert into books(name,edition,price,copy)values(?,?,?,?)");
					pst.setString(1, bname);
					pst.setString(2, edition);
					pst.setString(3, price);
					pst.setString(4, copy);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Book  Addedddd!!!!!");
					
					//table_load();
					table_load();
					          
					txtbname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					txtcopy.setText("");
					txtsell.setText("");
					txtbname.requestFocus();
					   }
					 
					catch (SQLException e1)  {
					e1.printStackTrace();
					}
				
				
				
				
				
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(36, 325, 89, 36);
		getFrame().getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.setBackground(Color.YELLOW);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				System.exit(0);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setBounds(171, 325, 89, 36);
		getFrame().getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Clear");
		btnNewButton_2.setBackground(Color.YELLOW);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
		          
							txtbname.setText("");
							txtedition.setText("");
							txtprice.setText("");
							txtbid.setText("");
							txtcopy.setText("");
							txtsell.setText("");
							txtbname.requestFocus();
				
				
				
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_2.setBounds(294, 325, 89, 36);
		getFrame().getContentPane().add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(425, 70, 284, 246);
		getFrame().getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Search", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		panel_1.setBounds(55, 388, 312, 62);
		getFrame().getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Book ID");
		lblNewLabel_1_1_1.setBounds(45, 22, 77, 17);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_1.add(lblNewLabel_1_1_1);
		
		txtbid = new JTextField();
		txtbid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				
				
				try {
			          
		            String id = txtbid.getText();
		 
		                pst = con.prepareStatement("select name,edition,price,copy from books where id = ?");
		                pst.setString(1, id);
		                ResultSet rs = pst.executeQuery();
		 
		            if(rs.next()==true)
		            {
		              
		                String name = rs.getString(1);
		                String edition = rs.getString(2);
		                String price = rs.getString(3);
		                String copy= rs.getString(4);
		                
		                txtbname.setText(name);
		                txtedition.setText(edition);
		                txtprice.setText(price);
		                txtcopy.setText(copy);
		                
		                
		            }  
		            else
		            {
		             txtbname.setText("");
		             txtedition.setText("");
		                txtprice.setText("");
		                txtcopy.setText("");
		                
		            }
		            
		 
		 
		        }
		catch (SQLException ex) {
		          
		        }
		
				
				
			}
		});
		txtbid.setColumns(10);
		txtbid.setBounds(141, 22, 161, 20);
		panel_1.add(txtbid);
		
		JButton btnNewButton_1_1 = new JButton("Update");
		btnNewButton_1_1.setBackground(Color.GREEN);
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String bname,edition,price,bid,copy;
				bname = txtbname.getText();
				edition = txtedition.getText();
				price = txtprice.getText();
				copy=txtcopy.getText();
				bid  = txtbid.getText();
				try {
				pst = con.prepareStatement("update books set name= ?,edition=?,price=?,copy=? where id =?");
							pst.setString(1, bname);
				            pst.setString(2, edition);
				            pst.setString(3, price);
				            pst.setString(4, copy);
				            pst.setString(5, bid);
				            pst.executeUpdate();
				            JOptionPane.showMessageDialog(null, "Book Updated!!!!!");
				            table_load();
				          
				            txtbname.setText("");
				            txtedition.setText("");
				            txtprice.setText("");
				            txtcopy.setText("");
				            txtsell.setText("");
				            txtbname.requestFocus();
				}
				 
				            catch (SQLException e1) {
				e1.printStackTrace();
				}
				
			}
			
			
		});
		
	//my code	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1_1.setBounds(461, 344, 89, 36);
		getFrame().getContentPane().add(btnNewButton_1_1);
		JButton btnNewButton_1_2 = new JButton("Sell");
		btnNewButton_1_2.setBackground(Color.MAGENTA);
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
			    String bname1 = txtbname.getText();
			    String price1 = txtprice.getText();
			    String edition1 = txtedition.getText();
			    String copy1=txtcopy.getText();
			    String sell=txtsell.getText();
			    String bid=txtbid.getText();
			    
			    int i=Integer.parseInt(copy1);
			    int s=Integer.parseInt(sell);
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    if(s==0) {
			    	
			    
			 
			    		JOptionPane.showMessageDialog(null, "NOC to sell can't be 0");
			    		//System.exit(1);
			    		
			    	
			    	
			    }
			    
			    
			    
			    
			    
			    if(s==i) {
			    
			    
			    
			    
			    try {
			    	pst = con.prepareStatement("insert into sells(name,edition,price,copy)values(?,?,?,?)");
					
					pst.setString(1, bname1);
					
					pst.setString(2, edition1);
					
					pst.setString(3, price1);
					pst.setString(4, copy1);
					pst.executeUpdate();
					//JOptionPane.showMessageDialog(null, "Record Addedddd!!!!!");
					
		
					
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			    
			    
		
			    
			  //  String bid;
			    bid  = txtbid.getText();
			    
			
			    
			    
			    try {
			    pst = con.prepareStatement("delete from books where id =?");
			                pst.setString(1, bid);
			                pst.executeUpdate();
			                JOptionPane.showMessageDialog(null, "book selled!!!!!");
			                table_load();
			              
			                txtbname.setText("");
			                txtedition.setText("");
			                txtprice.setText("");
			                txtcopy.setText("");
			                txtsell.setText("");
			                txtbname.requestFocus();
			    }
			     
			                catch (SQLException e1) {
			    e1.printStackTrace();
			    }
				
				
			    
			    
			    }
			    
			    
			    
			    
			    
			    
			    
			    
			    if(i>s && s>=1) {
			    	//String copy2=txtcopy.getText();
			    	//int i1=Integer.parseInt(copy2);
			    		i=i-s;
			    		
			    		String copystock= String.valueOf(i);
			    		String copysell= String.valueOf(s);
			    		
			    	
			    	   
				    try {
				    	pst = con.prepareStatement("insert into sells(name,edition,price,copy)values(?,?,?,?)");
						
						pst.setString(1, bname1);
						
						pst.setString(2, edition1);
						
						pst.setString(3, price1);
						pst.setString(4, copysell);
						
						pst.executeUpdate();
						//JOptionPane.showMessageDialog(null, "Record Addedddd!!!!!");
						
			
						
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				    
				    
			    	
				    
				    
				    
				    
				    
				    
				    
				    try {
						pst = con.prepareStatement("update books set name= ?,edition=?,price=?,copy=? where id =?");
						pst.setString(1, bname1);
						pst.setString(2, edition1);
						pst.setString(3, price1);
						pst.setString(4, copystock);
						 pst.setString(5, bid);
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "Book  selled!!!!!");
						
						//table_load();
						table_load();
						          
						txtbname.setText("");
						txtedition.setText("");
						txtprice.setText("");
						txtcopy.setText("");
						txtsell.setText("");
						txtbname.requestFocus();
						   }
						 
						catch (SQLException e1)  {
						e1.printStackTrace();
						}   
				    
				    
				    
				    
				    
				    
				    
				    
				    
			    	
			    	
			    	
			    	
			    	
			    	
			    	
			    	
			    }
			    
			    else if(i<s)
			    
			    {JOptionPane.showMessageDialog(null,"NOC to sell amount exceed !!!!!");}
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    
			    
				
			}
			
		});
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//mycode
		
		
		
		btnNewButton_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1_2.setBounds(595, 344, 89, 36);
		getFrame().getContentPane().add(btnNewButton_1_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 255, 255));
		panel_2.setBounds(0, 0, 732, 50);
		frame.getContentPane().add(panel_2);
		
		JLabel lblNewLabel = new JLabel("Ganpati Book Shop");
		panel_2.add(lblNewLabel);
		lblNewLabel.setBackground(new Color(255, 69, 0));
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 30));
		
		JButton btnNewButton_1_1_1 = new JButton("Delete");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			
				   String   bid1=txtbid.getText();
				
				   
				    
				    try {
				    pst = con.prepareStatement("delete from books where id =?");
				                pst.setString(1, bid1);
				                pst.executeUpdate();
				                JOptionPane.showMessageDialog(null, "book deleted!!!!!");
				                table_load();
				              
				                txtbname.setText("");
				                txtedition.setText("");
				                txtprice.setText("");
				                txtcopy.setText("");
				                txtsell.setText("");
				                txtbname.requestFocus();
				    }
				     
				                catch (SQLException e1) {
				    e1.printStackTrace();
				    }
					
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
			}
		});
		btnNewButton_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1_1_1.setBackground(Color.RED);
		btnNewButton_1_1_1.setBounds(529, 401, 89, 36);
		frame.getContentPane().add(btnNewButton_1_1_1);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
