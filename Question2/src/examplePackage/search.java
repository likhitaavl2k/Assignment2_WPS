package examplePackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class search
 */
@WebServlet("/search")
public class search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			PrintWriter writer = response.getWriter();
			String str= request.getParameter("string");
			
			mydb db=new mydb();
			Connection con = db.getCon();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from users ");
			
			int count=0;
			while(rs.next()) {
				if(rs.getString("name").contains(str)) {
					count++;
				}
			}
			if(count==0) {
				String htmlRespone = "<html>";
				htmlRespone +="<h3 style='font-family:cursive;'>No students with substring as "+str+"</h3>";
				htmlRespone += "<a href='details.html'>Try with other string??</a>";
				htmlRespone += "</html>";
				 
				writer.println(htmlRespone);
			}
			else {
				rs = stmt.executeQuery("select * from users ");
				String htmlRespone = "<html>";
				htmlRespone += "<head>\r\n"
						+ "<style>\r\n"
						+ "#table1 {\r\n"
						+ "  font-family: \"Trebuchet MS\", Arial, Helvetica, sans-serif;\r\n"
						+ "  border-collapse: collapse;\r\n"
						+ "  width: 70%;\r\n"
						+ "}\r\n"
						+ "\r\n"
						+ "#table1 td, #table1 th {\r\n"
						+ "  border: 1px solid #ddd;\r\n"
						+ "  padding: 8px;\r\n"
						+ "}\r\n"
						+ "\r\n"
						+ "#table1 tr:nth-child(even){background-color: #f2f2f2;}\r\n"
						+ "\r\n"
						+ "#table1 tr:hover {background-color: #ddd;}\r\n"
						+ "\r\n"
						+ "#table1 th {\r\n"
						+ "  padding-top: 12px;\r\n"
						+ "  padding-bottom: 12px;\r\n"
						+ "  text-align: left;\r\n"
						+ "  background-color: #4CAF50;\r\n"
						+ "  color: white;\r\n"
						+ "}\r\n"
						+ "</style>\r\n"
						+ "</head>\r\n";
				htmlRespone += "<body>";
				htmlRespone += "<br>";
				htmlRespone +="<table id=\"table1\">\r\n"
						+ "  <tr>\r\n"
						+ "    <th>Roll Number</th>\r\n"
						+ "    <th>Name</th>\r\n"
						+ "    <th>Department name</th>\r\n"
						+ "  </tr>\r\n";
				while(rs.next()) {
					if(rs.getString("name").contains(str)) {
					htmlRespone += "  <tr>\r\n"
								+ "    <td>"+rs.getString("roll_num")+"</td>\r\n"
								+ "    <td>"+rs.getString("name")+"</td>\r\n"
								+ "    <td>"+rs.getString("dept_name")+"</td>\r\n"
								+ "  </tr>\r\n";
								
						
				}
			}
				htmlRespone += "</table><br>";
				
				htmlRespone +="<div style='background-color:black;width:20%;'>\r\n ";
				htmlRespone	+= "<a href='details.html'><label style='color:white;font-family:cursive;padding-right:40px;'>\r\n"
						+ "Try with a different string??</label></a>\r\n"
						+ "</div><br><br>";
				htmlRespone += "<div>\r\n"
						+ "<form action='servlet3' method='get' >\r\n"
						+ "<select  name=\"dept\" id=\"dept\">\r\n"
						+ 	"<option value='select'>Search using department</option>";
				ResultSet rss = stmt.executeQuery("SELECT DISTINCT DEPT_NAME FROM USERS ");
				while(rss.next()) {			
					htmlRespone += "<option value='"+rss.getString("dept_name")+"'>"+rss.getString("dept_name")+"</option>";
				}
				htmlRespone	+= "</select>\r\n"
							+ "<button type='submit' >Search</button>\r\n"
							+ "</form>\r\n"
							+ "</div>";
				
				htmlRespone += "</body>";
				htmlRespone += "</html>";
				 
				writer.println(htmlRespone);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
