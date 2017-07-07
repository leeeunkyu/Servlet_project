package com.my.manager;

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
public class Manager extends HttpServlet {
	private static final long serialVersionUID = 1L;
     UserRegister user;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manager() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("TEST");
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
