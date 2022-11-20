package harendra;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jakarta.servlet.RequestDispatcher;
 
/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			PrintWriter out=response.getWriter();
			Class.forName("com.mysql.cj.jdbc.Driver");
			 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/haredra", "root", "root@123");
			String n=request.getParameter("txtName");
			String p=request.getParameter("txtPwd");
			
			java.sql.PreparedStatement ps=con.prepareStatement("select uname from login where uname=? and password=?");
			ps.setString(1,n);
			ps.setString(2,p);
			ResultSet rs= ps.executeQuery();
			if(rs.next()) 
			{
				RequestDispatcher rd= (RequestDispatcher) request.getRequestDispatcher("welcome.jsp");
				rd.forward(request, response);
			}else {
				out.println("<font color=red size=15>Login Failed!!<br>");
				out.println("<a href=login.jsp> Try Again!!</a>");
			}
			
			}catch(ClassNotFoundException e){
				e.printStackTrace();
			}catch(SQLException e) {
				e.printStackTrace();
			};
	}

}
