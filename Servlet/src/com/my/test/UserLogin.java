package com.my.test;

public class UserLogin {
	String user_id;
	String user_pwd;
	public UserLogin(String id, String pwd) {
		// TODO Auto-generated constructor stub
		System.out.println("�α��� -> id��: "+id+"  /pwd��:  "+pwd);
		user_id=id;
		user_pwd=pwd;
	}

	public String checkuser() {
		// TODO Auto-generated method stub
		JDBC JD = new JDBC();
		return JD.UserCheck(user_id,user_pwd);
	}

}
