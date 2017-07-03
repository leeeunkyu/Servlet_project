package com.my.test;

public class UserLogin {

	public UserLogin(String id, String pwd) {
		// TODO Auto-generated constructor stub
		System.out.println(id+":id°ª"+pwd+"pwd°ª");
		JDBC JD = new JDBC();
		JD.UserCheck(id,pwd);
	}

}
