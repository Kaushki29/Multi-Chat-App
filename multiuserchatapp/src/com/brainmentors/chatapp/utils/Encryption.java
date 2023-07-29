package com.brainmentors.chatapp.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//This class is to encrypt the password and not show it on database also
public class Encryption  {
	public static String passwordEncrypt(String plainPassword) throws NoSuchAlgorithmException
	{
		String encryptedPassword=null;
		//Message Digesr is class coming from security package to hash password
		//hashing password means hashcode of password is generated of equal length for all lengths of password
		//MD5 is an algo to hash password in message digest
		MessageDigest messageDigest=MessageDigest.getInstance("MD5");
		//will convert the plainpassword in to bytes
		messageDigest.update(plainPassword.getBytes());
		byte [] encrypt=messageDigest.digest();
		System.out.println(encrypt);
		//now bytes convert to string buffer
		StringBuffer sb=new StringBuffer();
		for(byte b:encrypt)
			sb.append(b);
		//String Buffer to string
		encryptedPassword=sb.toString();
		return encryptedPassword;
	}
}
