package com.my.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class JsonProcess {
    UserRegister user_regi;
    UserLogin user_login;
    String result;
	public String test(String[] array, HttpServletResponse response) {
		// TODO Auto-generated method stub
		switch (array[3]) {
		case "sign up":
			user_regi=new UserRegister(array[7],array[11]);
			result = user_regi.adduser();
			System.out.println("리턴값: "+result);
//			response.setHeader("result: ", result);
			return result;
		case "sign in":
			user_login=new UserLogin(array[7],array[11]);
			result = user_login.checkuser();
			System.out.println("리턴값: "+result);
//			response.setHeader("result: ", result);
			return result;
		default:
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
