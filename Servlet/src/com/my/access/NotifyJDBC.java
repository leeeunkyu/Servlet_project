package com.my.access;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class NotifyJDBC {
	static String dburl = null;	//db���� url
	static Connection con = null; // db���� Ŀ���� ��ü
	static PreparedStatement pstmt = null;
	static PreparedStatement pstmt_check = null; 

	static String db_id;
	static String db_pw;
	static ResultSet rs = null;
	static ResultSet rs_check = null;
	public NotifyJDBC(){
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

public String SocketRegi(String id,String unique_num) {
	// TODO Auto-generated method stub
	try{
		System.out.println("---------------------���� ��� �õ�---------------------");	

		dburl = "jdbc:oracle:thin:@localhost:1521:xe";
		con = DriverManager.getConnection(dburl, db_id, db_pw);
		pstmt = con.prepareStatement("INSERT INTO Socketlist VALUES(?,?)");
		pstmt.setString(1, id);
		pstmt.setString(2, unique_num);
		pstmt.executeUpdate();
		System.out.println("����� id =================> "+id);
		System.out.println("����� ������ȣ ================> "+unique_num);
		System.out.println("---------------------���� ��� �Ϸ�---------------------");	
		return "success";
		
	} catch(IllegalArgumentException e){
		System.out.println("�Է� ���¸� Ȯ���ϼ���");
	}catch(SQLException e){
		e.printStackTrace();
	}finally{
		if(con != null){try{con.close();}catch(Exception e){}}
		if(pstmt != null){try{pstmt.close();}catch(Exception e){}}
		if(rs != null){try{rs.close();}catch(Exception e){}}
	}
	return "failed";
	
	}

public String Socketfilter(String id) {
	// TODO Auto-generated method stub
	String result;
	try{
		System.out.println("---------------------���� �ߺ�ã�� �õ�---------------------");	

		dburl = "jdbc:oracle:thin:@localhost:1521:xe";
		con = DriverManager.getConnection(dburl, db_id, db_pw);
		pstmt_check = con.prepareStatement("SELECT id FROM Socketlist WHERE id=?");
		pstmt_check.setString(1, id);
		pstmt_check.executeUpdate();
		rs_check = pstmt_check.executeQuery();
		while(rs_check.next()){
			if(rs_check.getString("id")!=null){
				result="failed";
			}else{
				result="success";

			}
		}
		System.out.println("ã�ƺ� id -> "+id);
		System.out.println("---------------------���� �ߺ�ã�� �Ϸ�---------------------");	
		return "success";
		
	} catch(IllegalArgumentException e){
		System.out.println("�Է� ���¸� Ȯ���ϼ���");
	}catch(SQLException e){
		e.printStackTrace();
	}finally{
		if(con != null){try{con.close();}catch(Exception e){}}
		if(pstmt_check != null){try{pstmt.close();}catch(Exception e){}}
		if(rs_check != null){try{rs.close();}catch(Exception e){}}
	}
	return "failed";
	
	}
//git test

}
