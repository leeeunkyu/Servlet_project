package com.my.test;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
public class JDBC {
	static String dburl = null;	//db���� url
	static Connection con = null; // db���� Ŀ���� ��ü
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
			System.out.println("������ ����");	

			e.printStackTrace();
		}
	}

	public String UserRegi(String id, String pwd) {
		// TODO Auto-generated method stub
		try{
			System.out.println("ȸ����Ͻõ�");	

			dburl = "jdbc:oracle:thin:@localhost:1521:xe";
			con = DriverManager.getConnection(dburl, db_id, db_pw);
			pstmt = con.prepareStatement("INSERT INTO UserInfo VALUES(?,?)");
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.executeUpdate();
			System.out.println("ȸ����ϿϷ�");	
			return "success";
			
		} catch(IllegalArgumentException e){
			System.out.println("�Է� ���¸� Ȯ���ϼ���");
		}catch(SQLException e){
			e.printStackTrace();
		}
		return "failed";
		
	}

	public String UserCheck(String id, String pwd) {
		// TODO Auto-generated method stub
		try {
			dburl = "jdbc:oracle:thin:@localhost:1521:xe";
			con = DriverManager.getConnection(dburl, db_id, db_pw);
			pstmt = con.prepareStatement("SELECT * FROM UserInfo");
			rs = pstmt.executeQuery();
			while(rs.next()){
				 System.out.println(rs.getString("id"));
				 System.out.println(rs.getString("pwd"));
				 if(id.equals(rs.getString("id"))){
					 if(pwd.equals(rs.getString("pwd"))){
						 System.out.println("�α��� �Ϸ�");
							return "success";
					 }
				 }
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "failed";
	
	}

	public void UserVerify(String id, String pwd) {
		// TODO Auto-generated method stub
		//������ �ȴٴ� �����Ͽ�
		SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy.MM.dd HH:mm:ss", Locale.KOREA );
		Date currentTime = new Date ( );
		String dTime = formatter.format ( currentTime );
		System.out.println ( dTime );
		try{
			System.out.println("���� ���Ƚ��ϴ�.");	

			dburl = "jdbc:oracle:thin:@localhost:1521:xe";
			con = DriverManager.getConnection(dburl, db_id, db_pw);
			pstmt = con.prepareStatement("INSERT INTO DATELOG VALUES(?,?,?)");
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, dTime);
			pstmt.executeUpdate();
			System.out.println("���� ��ϿϷ�");	
			
		} catch(IllegalArgumentException e){
			System.out.println("�Է� ���¸� Ȯ���ϼ���");
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	public void UserLog(String id, String pwd) {
		// TODO Auto-generated method stub
		try {
			dburl = "jdbc:oracle:thin:@localhost:1521:xe";
			con = DriverManager.getConnection(dburl, db_id, db_pw);
			pstmt = con.prepareStatement("SELECT * FROM DATELOG");
			rs = pstmt.executeQuery();
			String logtext=null;
			while(rs.next()){
				// System.out.println(rs.getString("id"));
				 //System.out.println(rs.getString("pwd"));
				 if(id.equals(rs.getString("id"))){
					 if(pwd.equals(rs.getString("pwd"))){
						 if(logtext==null){
							 logtext=rs.getString("datelog");
						 }else{
							 logtext +="," +rs.getString("datelog");
						 }
					}
				 }
				}
			
			System.out.println(logtext);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
