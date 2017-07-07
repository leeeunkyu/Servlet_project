package com.my.access;

import com.my.manager.JDBC;

public class Verify {
	String user_id;

	public Verify(String id) {
		// TODO Auto-generated constructor stub
		user_id=id;
	}
	public void VerifyUser(){
		JDBC JD = new JDBC();
		JD.UserVerify(user_id);
	}

}
