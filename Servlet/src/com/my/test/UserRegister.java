package com.my.test;

public class UserRegister {

	public UserRegister(String id, String pwd) {
		// TODO Auto-generated constructor stub
			System.out.println("id°ª: "+id+"pwd°ª:  "+pwd);
			JDBC JD = new JDBC();
			JD.UserRegi(id,pwd);
	}

}
