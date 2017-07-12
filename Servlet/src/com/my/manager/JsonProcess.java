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
    private static final int KEY_SIZE = 128;
    private static final int ITERATION_COUNT = 10000;
    private static final String IV = "F27D5C9927726BCEFE7510B1BDD3D137";
    private static final String SALT = "3FF2EC019C627B945225DEBAD71A01B6985FE84C95A70EB132882F88C0A59A55";
    private static final String PASSPHRASE = "passPhrase passPhrase aes encoding algorithm";
    private static final String PLAIN_TEXT = "AES ENCODING ALGORITHM PLAIN TEXT";    
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
			String de_pwd=null;
			System.out.println("암호화된 비밀번호=====>"+array[11]);
			
			AesUtil util = new AesUtil(KEY_SIZE, ITERATION_COUNT);
//	        String encrypt = util.encrypt(SALT, IV, PASSPHRASE, PLAIN_TEXT);
			de_pwd = util.decrypt(SALT, IV, PASSPHRASE, array[11]);
	  
			System.out.println("복호화======>"+de_pwd);
			user_login=new UserLogin(array[7],de_pwd);
			result = user_login.checkuser();
			System.out.println("로그인 리턴값---------> "+result);
//			response.setHeader("result: ", result);
			return result;
		case "log":
			System.out.println("--------------------------------출입기록 시도------------------------");
//			CheckLog cl = new CheckLog(array[7]);
			CheckLog cl = new CheckLog("id");

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
