package com.my.manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.log.CheckLog;



public class JsonProcess {
    UserRegister user_regi;
    UserLogin user_login;
    String result=null;
	public String test(String[] array, HttpServletResponse response) {
		// TODO Auto-generated method stub
		switch (array[3]) {
		case "sign up":
			System.out.println("--------------------------------회원가입 시도------------------------");
			String result=null;
			String filter_result=null;
			System.out.println("test");
			Filter_user fu = new Filter_user(array[7],array[11]);
			filter_result=fu.filtering_id();
			if(filter_result.equals("id_ok")){
				
				if(fu.filtering_injection()){
					user_regi=new UserRegister(array[7],array[11]);
					result = user_regi.adduser();
					System.out.println("회원가입 리턴값-------> "+result);
//					response.setHeader("result: ", result);
					return result;
				}else{
					return "injection";
				}

			}else{
				return "id_overlap";
			}

		case "sign in":
			System.out.println("--------------------------------로그인시도--------------------------");
			user_login=new UserLogin(array[7],array[11]);
			result = user_login.checkuser();
			System.out.println("로그인 리턴값---------> "+result);
//			response.setHeader("result: ", result);
			return result;
		case "log":
			System.out.println("--------------------------------출입기록 시도------------------------");
			CheckLog cl = new CheckLog(array[7],array[11]);
			result=cl.log();
			System.out.println("--------------------------------출입기록 확인------------------------");

			return result;
		default:
			System.out.println("test용");
			break;
		}
		return result;
	}
	public String process(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String result = JsonProcess.getBody(req);
		String[] array = result.split("\"");
		System.out.println("json 결과값"+result);		
		
		return test(array, resp);
	}
	public static String getBody(HttpServletRequest request) throws IOException {
		String body = null;
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;

		try {
			InputStream inputStream = request.getInputStream();
			if (inputStream != null) {
				bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				char[] charBuffer = new char[128];
				int bytesRead = -1;
				while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
					stringBuilder.append(charBuffer, 0, bytesRead);
				}
			}
		} catch (IOException ex) {
			throw ex;

		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException ex) {
					throw ex;
				}
			}
		}

		body = stringBuilder.toString();

		return body;
	}


	

}
