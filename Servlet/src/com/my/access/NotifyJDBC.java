package com.my.access;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class NotifyJDBC {
	static String dburl = null;	//db접속 url
	static Connection con = null; // db연결 커낵션 객체
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
			System.out.println("디비생성 실패");	

			e.printStackTrace();
		}
	}

public String SocketRegi(String id,String unique_num) {
	// TODO Auto-generated method stub
	try{
		System.out.println("---------------------소켓 등록 시도---------------------");	

		dburl = "jdbc:oracle:thin:@localhost:1521:xe";
		con = DriverManager.getConnection(dburl, db_id, db_pw);
		pstmt = con.prepareStatement("INSERT INTO Socketlist VALUES(?,?)");
		pstmt.setString(1, id);
		pstmt.setString(2, unique_num);
		pstmt.executeUpdate();
		System.out.println("저장된 id =================> "+id);
		System.out.println("저장된 고유번호 ================> "+unique_num);
		System.out.println("---------------------소켓 등록 완료---------------------");	
		return "success";
		
	} catch(IllegalArgumentException e){
		System.out.println("입력 형태를 확인하세요");
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
		System.out.println("---------------------소켓 중복찾기 시도---------------------");	

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
		System.out.println("찾아볼 id -> "+id);
		System.out.println("---------------------소켓 중복찾기 완료---------------------");	
		return "success";
		
	} catch(IllegalArgumentException e){
		System.out.println("입력 형태를 확인하세요");
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
