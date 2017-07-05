package com.my.test;

public class Filter_user {
	String user_id;
	String user_pwd;
	public Filter_user(String id, String pwd) {
		// TODO Auto-generated constructor stub
		user_id=id;
		user_pwd=pwd;
	}
	public String filtering_id(){
		JDBC jd = new JDBC();
		return jd.filtering(user_id);
	}
	public boolean filtering_injection(){
		if(user_id.contains("@")||user_id.contains("-")||user_id.contains(",")||user_id.contains("'")){
			return false;
		}else if(user_pwd.contains("@")||user_pwd.contains("-")||user_pwd.contains(",")||user_pwd.contains("'")){
			return false;
		}else {
			return true;
		}
	}

}
