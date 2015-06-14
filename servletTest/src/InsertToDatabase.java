
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InsertToDatabase
 */
@WebServlet(description = "servlet" ,urlPatterns={"/InsertToDatabase"})
public class InsertToDatabase extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public InsertToDatabase() {
        // TODO Auto-generated constructor stub
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
		response.setContentType("text/html");
		String name=request.getParameter("name");
		String experience=request.getParameter("experience");
		String designation=request.getParameter("designation");
		
		PrintWriter out=response.getWriter();
		out.println("Inserted Into Database \n");
		out.println("Username: "+name+"\n");
		out.println("Designation: "+designation+"\n");
		out.println("Experince: "+experience+"\n");
		
		
		String htmlRespone = "<html>";
		htmlRespone +="<title>"+name+"</title><br/>";
		htmlRespone +="<meta charset='utf-8'>";
		htmlRespone +="<meta name='viewport' content='width=device-width, initial-scale=1'>";
		htmlRespone +="<link rel='stylesheet' href='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css'>";
		htmlRespone +=" <script src='https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js'></script>";
		htmlRespone +="<script src='http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js'></script>";
        htmlRespone += "<h2>Welcome " + name + "<br/>";      
        htmlRespone += "</html>";
        PrintWriter out1=response.getWriter();
		out1.println(htmlRespone);
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","naval.2402");

			
			PreparedStatement stat2=con.prepareStatement("INSERT INTO employee (name, designation, experince) VALUES (?, ?, ?)");
			stat2.setString(1, name);
			stat2.setString(2, designation );
			stat2.setString(3, experience);
			stat2.executeUpdate();
			
			Statement stat1=con.createStatement();
			ResultSet result=stat1.executeQuery("SELECT * FROM employee");
			while(result.next())
			{
				out.printf("%-8s",result.getString("id"));
				out.printf("%-20s",result.getString("name"));
				out.printf("%-15s",result.getString("experincet"));
				System.out.println("");
				
			}
			stat2.close();
			con.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	
	}

}
