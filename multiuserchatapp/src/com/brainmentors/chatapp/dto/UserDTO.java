package com.brainmentors.chatapp.dto;
//DTO is Data transfer object this transfers data to user dao easily
//as we can add all the input data here and its object is passed(so light weight)
public class UserDTO {
	private String userid;
	private char[] password;
	public UserDTO(String userid,char[] password)
	{
		this.userid=userid;
		this.password=password;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public char[] getPassword() {
		return password;
	}
	public void setPassword(char[] password) {
		this.password = password;
	}
}
