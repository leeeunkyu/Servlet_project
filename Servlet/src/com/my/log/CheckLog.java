package com.my.log;

import com.my.manager.JDBC;

public class CheckLog {
	String user_id;
	public CheckLog(String id) {
		// TODO Auto-generated constructor stub
		user_id=id;
	}

	public String log() {
		// TODO Auto-generated method stub
		JDBC JD = new JDBC();
		return JD.UserLog(user_id);
	}

}
