package web;

import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/main/editbook")
public class EditBookServlet extends HttpServlet{
	Connection conn = null;
	PreparedStatement stmt =  null;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String writer = request.getParameter("writer");
		String cost = request.getParameter("cost");
		int length = id.length();
		if(name == null || writer == null || cost == null || name.equals("") || writer.equals("") || cost.equals("")) {
			out.println("<script>");
			out.println("alert('입력하지 않은 항목이 있습니다.');");
			out.println("window.history.back();");
			out.println("</script>");
		}
		
		else if(!isDigit(cost)) {
			out.println("<script>");
			out.println("alert('가격은 숫자만 입력해주세요.');");
			out.println("window.history.back();");
			out.println("</script>");
		}
		else if(cost.length() >= 10) {
			out.println("<script>");
			out.println("alert('가격이 너무 큽니다.');");
			out.println("window.history.back();");
			out.println("</script>");
		}
		else {
			try {
				String sql ="UPDATE book SET name=?, writer=?, cost=? WHERE id= ?";
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webdb?serverTimezone=UTC", "root", "rlaehdrjs3");
				stmt = conn.prepareStatement(sql);
				int id_ = Integer.parseInt(id);
				int cost_ = Integer.parseInt(cost);
				stmt.setString(1, name);
				stmt.setString(2, writer);
				stmt.setString(3, cost);
				stmt.setInt(4, id_);
				
				stmt.executeUpdate();
				
				out.println("<script>");
				out.println("alert('해당 도서가 수정되었습니다.');");
				out.print("window.history.back();");
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
	private static boolean isDigit(String id) {
		char tmp;
		boolean state = true;
		for(int i=0; i<id.length(); i++) {
			tmp = id.charAt(i);
			if(!('0' <= tmp && tmp <= '9')) {
				state = false;
			}
		}
		return state;
	}
}
