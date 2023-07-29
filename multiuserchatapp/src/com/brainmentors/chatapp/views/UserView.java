package com.brainmentors.chatapp.views;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class UserView extends JFrame{
	int counter;
	UserView()
	{
		counter=0;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //after setVisible frame is shown but on cross it does not close we have to do this with this mentioned function
		setVisible(true);             //coming from parent class(JFrame)
		setSize(500,500);             //Size of frame is set
		setResizable(false);          //to stop maximization
		setLocationRelativeTo(null);  //to open frame always in centre
		setTitle("Login");            //title of frame
		
		//Now labels are added(that is the content)
		JLabel welcome=new JLabel("Login");    //Labels are added with JLabel class (by obj making)
		welcome.setFont(new Font("Arial",Font.BOLD,40));  //Font of Label is set
		
		//The label need to shown in frame so we have to connect label with frame
		Container container=this.getContentPane(); //Through container connection is done we get content pane where content will be displayed
		container.setLayout(null);     //The default layout is set null to set our own accordingly
		welcome.setBounds(100,70,200,70);   //x,y-coordinates,height,width is set to show content
		container.add(welcome);  //Now the content of label in welcome is added
		
		//Now adding a button
		JButton button=new JButton("Count"); //Jbutton class is imported to add button and name of button is count
		button.setBounds(100,300,200,50);     //dimensions of button is set
		container.add(button);       //button is added in the container
		button.addActionListener(new ActionListener() {    //We want an event to happen when button is pressed so action listener class is added
			@Override
			public void actionPerformed(ActionEvent event) { 
				counter++;
				welcome.setText("count "+counter);  
			}
		});	
	}
	public static void main(String ar[])
	{
		UserView userView=new UserView();
	}
}
