package com.my.log;

import com.my.test.JDBC;

public class CheckLog {
	String user_id;
	String user_pwd;
	public CheckLog(String id, String pwd) {
		// TODO Auto-generated constructor stub
		user_id=id;
		user_pwd=pwd;
	}

	public void log() {
		// TODO Auto-generated method stub
		JDBC JD = new JDBC();
		JD.UserLog(user_id,user_pwd);
	}

}
