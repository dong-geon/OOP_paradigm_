package web;

import java.io.*;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet("/main/return")
public class ReturnBookServlet extends HttpServlet {
	Connection conn = null;
	Statement stmt =  null;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		String id_ = request.getParameter("id");
		try {
			String sql ="UPDATE book SET rental=\"Y\" WHERE id = "+ id_+";";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webdb?serverTimezone=UTC", "root", "rlaehdrjs3");
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			response.sendRedirect("/main/BookList");
		}catch (Exception e) {
			throw new ServletException(e);
		}
		finally {
			try {
				stmt.close();
			}
			catch(Exception ignored) {
			}
			try {
				conn.close();
			}
			catch(Exception ignored) {
			}
		}		
	}
}
