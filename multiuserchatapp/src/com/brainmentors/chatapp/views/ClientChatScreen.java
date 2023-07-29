package com.brainmentors.chatapp.views;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.brainmentors.chatapp.network.Client;
import com.brainmentors.chatapp.utils.UserInfo;


public class ClientChatScreen extends JFrame {
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_2;
    private JTextArea textArea;
    private Client client;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
					try {
						ClientChatScreen frame = new ClientChatScreen();
					} catch (UnknownHostException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	}
	private void Sendit()
	{
		String message=textField_2.getText();
		try {
			client.sendMessage(UserInfo.USER_NAME+" - "+message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Create the frame.
	 */
	public ClientChatScreen() throws UnknownHostException,IOException{
		setResizable(false);
		textArea=new JTextArea();
		client=new Client(textArea);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1140, 593);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(28, 28, 1057, 433);
		contentPane.add(scrollPane);
		
		//JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
		textArea.setBounds(40, 25, 900, 420);
		scrollPane.setViewportView(textArea);
		
		textField = new JTextField();
		textField.setBounds(28, 521, 827, 0);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_2.setBounds(28, 487, 774, 39);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton Sendit = new JButton("Send Message");
		Sendit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sendit();
			}
		});
		Sendit.setFont(new Font("Tahoma", Font.BOLD, 18));
		Sendit.setBounds(840, 486, 174, 39);
		contentPane.add(Sendit);
		setVisible(true);
	}
}
