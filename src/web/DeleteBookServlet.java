package web;

import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/main/deletebook")
public class DeleteBookServlet extends HttpServlet{
	Connection conn = null;
	PreparedStatement stmt =  null;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		int length = id.length();
		try {
			String sql ="DELETE FROM book WHERE ID=?;";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webdb?serverTimezone=UTC", "root", "rlaehdrjs3");
			stmt = conn.prepareStatement(sql);
			int id_ = Integer.parseInt(id);
			stmt.setInt(1, id_);	
			stmt.executeUpdate();
			out.println("<script>");
			out.println("alert('해당 도서가 삭제되었습니다.');");
			out.print("location.href = '/main/BookList';");
			out.println("</script>");
		
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
