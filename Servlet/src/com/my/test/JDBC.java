package com.my.test;

import java.sql.*;
public class JDBC {
	static String dburl = null;	//db접속 url
	static Connection con = null; // db연결 커낵션 객체
	static PreparedStatement pstmt = null; 
	static String db_id;
	static String db_pw;
	static ResultSet rs = null;
	public JDBC() {
		db_id = "scott";
		db_pw = "mobile";
		// TODO Auto-generated constructor stub
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("디비생성 실패");	

			e.printStackTrace();
		}
	}

	public void UserRegi(String id, String pwd) {
		// TODO Auto-generated method stub
		try{
			System.out.println("회원등록시도");	

			dburl = "jdbc:oracle:thin:@localhost:1521:xe";
			con = DriverManager.getConnection(dburl, db_id, db_pw);
			
			pstmt = con.prepareStatement("INSERT INTO UserInfo VALUES(?,?)");
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.executeUpdate();
			
			System.out.println("회원등록완료");	
			

			
		} catch(IllegalArgumentException e){
			System.out.println("입력 형태를 확인하세요");
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}

	public String UserCheck(String id, String pwd) {
		// TODO Auto-generated method stub
		try {
			pstmt = con.prepareStatement("SELECT * FROM UserInfo");
			rs = pstmt.executeQuery();
			while(rs.next()){
				 System.out.println(rs.getString("id"));
				 System.out.println(rs.getString("pwd"));
				 if(id.equals(rs.getString("id"))){
					 if(pwd.equals(rs.getString("pwd"))){
						 System.out.println("로그인 완료");
							return null;
					 }
				 }
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	
	}

}
