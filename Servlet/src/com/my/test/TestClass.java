package com.my.test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestClass
 */
@WebServlet("/TestClass")
public class TestClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
     UserRegister user;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestClass() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String result=null;
		String filter_result=null;
		System.out.println("test");

		Filter_user fu = new Filter_user("lasttestid","sad");
		filter_result=fu.filtering_id();
		if(filter_result.equals("id_ok")){
			
			if(fu.filtering_injection()){
//				UserRegister ur = new UserRegister("lasttestid", "테스트 비밀번호");
//				ur.adduser();
//				UserLogin ul = new UserLogin("lasttestid", "테스트 비밀번호");
//				ul.checkuser();
				//user=new UserRegister("test아이디","yest비밀번호");
//				response.setHeader("result: ","test");	
			}else{
				response.getWriter().append("injection");
			}

		}else{
			response.getWriter().append("id_overlap");
		}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String result;
		JsonProcess jp = new JsonProcess();
		result=jp.process(request,response);
		response.getWriter().append(result);
	//	doGet(request, response);
	}

}
