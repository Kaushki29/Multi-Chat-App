package com.brainmentors.chatapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.brainmentors.chatapp.dto.UserDTO;
import com.brainmentors.chatapp.utils.Encryption;


public class UserDAO {
	public boolean isLogin(UserDTO userDTO) throws ClassNotFoundException, SQLException,Exception
	{
		Connection con=null;
		//This is used in place of statement this prepares the query and store its result
		//we do not need to concat userid password as in statement
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		//query given to prepared stmt and ques mark values will be given
		final String SQL="select userid from users where userid=? and password=?";
		try {
			con=CommonDAO.createConnection();
			pstmt=con.prepareStatement(SQL);  //Here the already defined query is passed
			pstmt.setString(1,userDTO.getUserid());
			//Here both the question mark values are defined
			String encryptedPwd=Encryption.passwordEncrypt(new String(userDTO.getPassword()));
			pstmt.setString(2,encryptedPwd);   //Encrypted password is passed
			//now the result is stored from prepared statement
			rs=pstmt.executeQuery();
			// this fx returns true if record is added else false
			return rs.next();
		}
		finally{
			//closed in the reverse order as opened
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(con!=null)
				con.close();
		}
	}
	//add the data through object of userDTO here
	public int add(UserDTO userDTO) throws ClassNotFoundException, SQLException , Exception
	{
		System.out.println("Rec "+userDTO.getUserid()+" "+userDTO.getPassword());
		Connection connection=null;     //to eastablish connection  with database
		Statement stmt=null;  //for query
		try                 //try,finally is used to close stmt and connection even if there is a error in b/w and program throw(internal error)
		{
		//Step 1- Create Connection
		connection=CommonDAO.createConnection();
		//Step 2- We do a query
		stmt=connection.createStatement();
		// insert into users(userid,password) values('ram','ram123');
		//new String fx is used to convert password from char array to string
		//password is also encrptyed here
		int record=stmt.executeUpdate("insert into users(userid,password) values('"+userDTO.getUserid()+"','"+Encryption.passwordEncrypt(new String(userDTO.getPassword()))+"')");
		return record;
		}
		finally      //always executes(even if return is written before) used to clean or close resources
		             //not executes only when we write system.exit(0)
		{
			if(stmt!=null)             //if the stmt is not open what we will close so to check if is used
				stmt.close();
			if(connection!=null)
				connection.close();
		}
	}
}
