package com.my.manager;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
public class JDBC {
	static String dburl = null;	//db���� url
	static Connection con = null; // db���� Ŀ���� ��ü
	static PreparedStatement pstmt = null;
	static PreparedStatement pstmt_check = null; 

	static String db_id;
	static String db_pw;
	static ResultSet rs = null;
	static ResultSet rs_check = null;

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
			String Sha_result="";
		//	byte[] salt = new byte[1];
			int salt=0;
			Random random = new Random();
			salt=random.nextInt(10);
			//random.nextBytes(salt);
//			for(int i=0;i<salt.length;i++){
//				System.out.println(salt[i]);
//			}
			Sha_result=hashpwd(salt, pwd);
			
			dburl = "jdbc:oracle:thin:@localhost:1521:xe";
			con = DriverManager.getConnection(dburl, db_id, db_pw);
			pstmt = con.prepareStatement("INSERT INTO UserInfo VALUES(?,?,?)");
			pstmt.setString(1, id);
			pstmt.setString(2, Sha_result);
			pstmt.setInt(3, salt);
			pstmt.executeUpdate();
			System.out.println("����� id ==========> "+id);
			System.out.println("����� �н����� ==========> "+Sha_result);
			System.out.println("����� salt ==========> "+salt);
			System.out.println("---------------------ȸ����ϿϷ�---------------------");	
			return "success";
			
		} catch(IllegalArgumentException e){
			System.out.println("�Է� ���¸� Ȯ���ϼ���");
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(con != null){try{con.close();}catch(Exception e){}}
			if(pstmt != null){try{pstmt.close();}catch(Exception e){}}
		}
		return "failed";
		
	}

	public String UserCheck(String id, String pwd) {
		// TODO Auto-generated method stub
		try {
			//byte[] checktext=null;
			int checktext=0;
			String returnpwd=null;
			dburl = "jdbc:oracle:thin:@localhost:1521:xe";
			con = DriverManager.getConnection(dburl, db_id, db_pw);
			pstmt_check =con.prepareStatement("SELECT salt FROM UserInfo WHERE id=?");
			pstmt_check.setString(1, id);
			rs_check = pstmt_check.executeQuery();
			while(rs_check.next()){
				checktext=rs_check.getInt("salt");
			}
			if(pstmt_check != null) try{pstmt_check.close();}catch(SQLException sqle){}            // PreparedStatement ��ü ����

			if(con != null) try{con.close();}catch(SQLException sqle){}            // Connection ����
			con = DriverManager.getConnection(dburl, db_id, db_pw);
			pstmt = con.prepareStatement("SELECT * FROM UserInfo");
			rs = pstmt.executeQuery();
			while(rs.next()){
				 System.out.println("id �˻����-->"+rs.getString("id"));

				 if(id.equals(rs.getString("id"))){
					 if(checktext==(rs.getInt("salt"))){
						 returnpwd=hashpwd(checktext, pwd);
						 if(returnpwd.equals(rs.getString("pwd"))){
							 System.out.println("----------------------------���̵� ��� ��ġ------------------");
							 return "success";
						 }else{
						 }
					 }else{
					 }
				 }else{
				 }
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(con != null){try{con.close();}catch(Exception e){}}
			if(pstmt != null){try{pstmt.close();}catch(Exception e){}}
			if(rs != null){try{rs.close();}catch(Exception e){}}
		}
		return "failed";
	
	}

	public void UserVerify(String id) {
		// TODO Auto-generated method stub
		//������ �ȴٴ� �����Ͽ�
		SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy.MM.dd HH:mm:ss", Locale.KOREA );
		Date currentTime = new Date ( );
		String dTime = formatter.format ( currentTime );
		System.out.println ( dTime );
		try{
			System.out.println("-----------------------------���� ���Ƚ��ϴ�.--------------------");	

			dburl = "jdbc:oracle:thin:@localhost:1521:xe";
			con = DriverManager.getConnection(dburl, db_id, db_pw);
			pstmt = con.prepareStatement("INSERT INTO DATELOG VALUES(?,?)");
			pstmt.setString(1, id);
			pstmt.setString(2, dTime);
			pstmt.executeUpdate();
			System.out.println("--------"+dTime+"---���� ��ϿϷ�------------------------");	
			
		} catch(IllegalArgumentException e){
			System.out.println("�Է� ���¸� Ȯ���ϼ���");
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(con != null){try{con.close();}catch(Exception e){}}
			if(pstmt != null){try{pstmt.close();}catch(Exception e){}}
			if(rs != null){try{rs.close();}catch(Exception e){}}
		}
	}
	
	public String UserLog(String id) {
		// TODO Auto-generated method stub
		try {
			dburl = "jdbc:oracle:thin:@localhost:1521:xe";
			con = DriverManager.getConnection(dburl, db_id, db_pw);
			pstmt = con.prepareStatement("SELECT * FROM DATELOG WHERE id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			System.out.println("�˻��� ����� �̸�=======>"+id);
			String logtext=null;
			while(rs.next()){
				// System.out.println(rs.getString("id"));
				 //System.out.println(rs.getString("pwd"));
				 if(id.equals(rs.getString("id"))){
						System.out.println("�˻��� ����� �̸�=======>"+id);
					 if(true){
						 if(logtext==null){
							 logtext=rs.getString("datelog");
						 }else{
							 logtext +="," +rs.getString("datelog");
						 }
					}
				 }
				}
			
			System.out.println("���"+logtext);
			return logtext;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(con != null){try{con.close();}catch(Exception e){}}
			if(pstmt != null){try{pstmt.close();}catch(Exception e){}}
			if(rs != null){try{rs.close();}catch(Exception e){}}
		}
		return "failed";

	}
	
	public String hashpwd(int salt,String pwd) {
		try {
			String Sha="";
			pwd+=salt;
			MessageDigest md;
			md = MessageDigest.getInstance("SHA-256");
			md.update(pwd.getBytes());
		    byte[] byteData = md.digest();
		    StringBuffer sb = new StringBuffer();
		    for (int i = 0; i < byteData.length; ++i)
		    {
		      sb.append(Integer.toString((byteData[i] & 0xFF) + 256, 16).substring(1));
		     }
		     Sha=sb.toString();
		     return Sha;

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
		return null;
	}
	public String filtering(String id){
		try {
			dburl = "jdbc:oracle:thin:@localhost:1521:xe";
			con = DriverManager.getConnection(dburl, db_id, db_pw);
			pstmt = con.prepareStatement("SELECT * FROM UserInfo");
			rs = pstmt.executeQuery();
			while(rs.next()){
				// System.out.println(rs.getString("id"));
				 //System.out.println(rs.getString("pwd"));
				 if(id.equals(rs.getString("id"))){
					 return "id_overlap";
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(con != null){try{con.close();}catch(Exception e){}}
			if(pstmt != null){try{pstmt.close();}catch(Exception e){}}
			if(rs != null){try{rs.close();}catch(Exception e){}}
		}
		return "id_ok";		
	}

}
