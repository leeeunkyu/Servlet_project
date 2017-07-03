package com.my.access;

import com.my.test.JDBC;

public class Verify {
	String user_id;
	String user_pwd;
	public Verify(String id, String pwd) {
		// TODO Auto-generated constructor stub
		user_id=id;
		user_pwd=pwd;
	}
	public void VerifyUser(){
		JDBC JD = new JDBC();
		JD.UserVerify(user_id,user_pwd);
	}

}
