package com.ideas;


import java.sql.*;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class InsertToDatabase
 */
@WebServlet(description = "servlet" ,urlPatterns={"/InsertToDatabase"})
public class UploadToDatabase extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public UploadToDatabase() {
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Inside get method");
		String department=request.getParameter("department");
		String name=request.getParameter("name");
		String experience=request.getParameter("experience");
		String designation=request.getParameter("designation");
		String comments=request.getParameter("comments");
		String resumelink=request.getParameter("resumelink");
		System.out.println(department);
		//boolean param = false;
		try {
		
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","naval.2402");
		
			
			System.out.println("Inside post method");
			PreparedStatement stat2=con.prepareStatement("INSERT INTO employee (department,name, designation, experience,comments,resumelink) VALUES (?, ?, ? ,? ,? ,? )");
			stat2.setString(1, department);
			stat2.setString(2, name);
			stat2.setString(3, designation );
			stat2.setString(4, experience);
			stat2.setString(5, comments);
			stat2.setString(6, resumelink);
			stat2.executeUpdate();
			//param=true;
			
			stat2.close();
			con.close();
			
			RequestDispatcher rd=request.getRequestDispatcher("/UploadFileServlet");  
	        rd.forward(request, response);  
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		//response.setContentType("application/json");
		//response.getWriter().print(param);
		//response.flushBuffer();
		/*response.setContentType("text/plain");
		 response.setStatus(response.SC_MOVED_TEMPORARILY);
	      response.setHeader("Location", "http://localhost:8080/recruitmentportal/Bootstrap/bootstrap-3.3.5-dist/uploadui.html");*/    
		
		
	
	}
	
}
