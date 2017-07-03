package com.my.test;

public class UserRegister {
	String user_id;
	String user_pwd;
	public UserRegister(String id, String pwd) {
		// TODO Auto-generated constructor stub
			System.out.println("id°ª: "+id+"pwd°ª:  "+pwd);
			user_id=id;
			user_pwd=pwd;
	}

	public String adduser() {
		// TODO Auto-generated method stub
		JDBC JD = new JDBC();
		
		return JD.UserRegi(user_id,user_pwd);

	}

}
