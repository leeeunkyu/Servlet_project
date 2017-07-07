package com.my.log;

import com.my.manager.JDBC;

public class CheckLog {
	String user_id;
	String user_pwd;
	public CheckLog(String id, String pwd) {
		// TODO Auto-generated constructor stub
		user_id=id;
		user_pwd=pwd;
	}

	public String log() {
		// TODO Auto-generated method stub
		JDBC JD = new JDBC();
		return JD.UserLog(user_id,user_pwd);
	}

}
