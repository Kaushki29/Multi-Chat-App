package com.brainmentors.chatapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static com.brainmentors.chatapp.utils.configReader.getValue;
//This is made interface so that obj is not created everytime to make connection for diff things
//loaded with class automatically
public interface CommonDAO {
	//Rule-Throw early(for exceptions immediately) and catch later(so that users know where is the issue)
	public static Connection createConnection() throws ClassNotFoundException,SQLException
	{
		//Step 1-Load a Driver
		Class.forName(getValue("DRIVER"));
    	//Step 2-Making a Connection
		final String CONNECTION_STRING=getValue("CONNECTION_URL");
		final String USER_ID=getValue("USERID");
		final String PASSWORD=getValue("PASSWORD");
		Connection con=DriverManager.getConnection(CONNECTION_STRING,USER_ID,PASSWORD);
     	if(con!=null)
		{
			System.out.println("Connection Created...");
			//scon.close();
		}
		return con;
	}
}
