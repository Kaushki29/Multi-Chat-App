package  com.brainmentors.chatapp.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.brainmentors.chatapp.dao.UserDAO;
import com.brainmentors.chatapp.dto.UserDTO;
import com.brainmentors.chatapp.utils.UserInfo;

public class UserScreen extends JFrame {
	private JTextField useridtxt;
	private JPasswordField passwordField;

	public static void main(String[] args) {
					UserScreen window = new UserScreen();
	}
	//Common in both login and register so created globally
	UserDAO userDAO=new UserDAO();
	private void doLogin()
	{
		String userid=useridtxt.getText();
		char []password=passwordField.getPassword();
		//UserDAO userDAO=new UserDAO();
		//diff for all enteries so seperate in both fx
		UserDTO userDTO=new UserDTO(userid,password);
		try {
			String message="";
			if(userDAO.isLogin(userDTO))
			{
				message="Welcome "+userid;
				UserInfo.USER_NAME=userid;
			   JOptionPane.showMessageDialog(this, message);
			   setVisible(false);
			   dispose();
			   DashBoard dashBoard=new DashBoard(message);
			   dashBoard.setVisible(true);
			}
			else
			{
				message="Invalid Userid or Password";
				JOptionPane.showMessageDialog(this, message);
			}
			//JOptionPane.showMessageDialog(this, message);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void register()
	{
		String userid=useridtxt.getText();
		char []password=passwordField.getPassword();
		System.out.println("userid= "+userid+" Password= "+password); //Password-:ClassName@HashCode(Hexa)
		
		//add data through screen in userdao from userdto
		UserDAO userDAO=new UserDAO();
		UserDTO userDTO=new UserDTO(userid,password);
		//try catch bcz here userDAO is called which could cause exceptions
		try {
		int result=userDAO.add(userDTO);
		if(result>0)   //to know if record added or not
			//With JOptionPane a box will pop on on registering to tell whether registered successfully or not
			JOptionPane.showMessageDialog(this,"Register Successfully");
		else
			JOptionPane.showMessageDialog(this,"Register Successfully");
		}
		catch(ClassNotFoundException | SQLException ex)  //this is to specify the exceptions
		{
			System.out.println("DB issue..");
			ex.printStackTrace();   //to know where is the exception
		}
		catch(Exception ex)    //for generic exceptions
		{
			System.out.println("Some Generic Exception raised..");
			ex.printStackTrace();
		}
	}
	/**
	 * Create the application.
	 */
	public UserScreen() {
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 40));
		lblNewLabel.setBounds(373, 46, 170, 43);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(143, 112, 49, 14);
		getContentPane().add(lblNewLabel_1);
		
		useridtxt = new JTextField();
		useridtxt.setBounds(429, 146, 247, 43);
		getContentPane().add(useridtxt);
		useridtxt.setColumns(10);
		
		JLabel useridlbl = new JLabel("UserId");
		useridlbl.setFont(new Font("Tahoma", Font.BOLD, 18));
		useridlbl.setBounds(271, 150, 73, 29);
		getContentPane().add(useridlbl);
		
		JLabel pwdlbl = new JLabel("Password");
		pwdlbl.setFont(new Font("Tahoma", Font.BOLD, 18));
		pwdlbl.setBounds(271, 246, 89, 43);
		getContentPane().add(pwdlbl);
		
		JButton loginbt = new JButton("Login");
		loginbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doLogin();
			}
		});
		loginbt.setFont(new Font("Tahoma", Font.BOLD, 16));
		loginbt.setBounds(318, 364, 110, 36);
		getContentPane().add(loginbt);
		
		JButton Registerbt = new JButton("Register");
		Registerbt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				register();
			}
		});
		Registerbt.setFont(new Font("Tahoma", Font.BOLD, 16));
		Registerbt.setBounds(494, 364, 110, 36);
		getContentPane().add(Registerbt);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(429, 250, 247, 41);
		getContentPane().add(passwordField);
		setTitle("LOGIN");
		setBackground(Color.WHITE);
		setBounds(100,100,833, 542);
		setLocationRelativeTo(null);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setVisible(true);

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
	}
}
