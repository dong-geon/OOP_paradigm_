package web;

import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/main/addbook")
public class AddBookServlet extends HttpServlet{
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
		if(id == null || name == null || writer == null || cost == null || id.equals("") || name.equals("") || writer.equals("") || cost.equals("")) {
			response.sendRedirect("/view/main/addBookFail.jsp?RESULT=NONE");
		}
		else if(!isDigit(request.getParameter("id"))) {
			out.println("<script>");
			out.println("alert('ID는 숫자만 입력할 수 있습니다.');");
			out.println("window.history.back();");
			out.println("</script>");
		}
		
		else if(id.length() >= 10) {
			out.println("<script>");
			out.println("alert('ID가 너무 큽니다..');");
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
				String sql ="INSERT INTO book (id, name, writer, cost, rental, count) values (?, ?, ?, ?, 'Y', 0)";
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webdb?serverTimezone=UTC", "root", "rlaehdrjs3");
				stmt = conn.prepareStatement(sql);
				if(1==idCheck(id)) {
					response.sendRedirect("/view/main/addBookFail.jsp?RESULT=IDCHECK");
				}
				else{
					int id_ = Integer.parseInt(id);
					int cost_ = Integer.parseInt(cost);
					stmt.setInt(1, id_);
					stmt.setString(2, name);
					stmt.setString(3, writer);
					stmt.setInt(4, cost_);
					
					stmt.executeUpdate();
					
					out.println("<script>");
					out.println("alert('해당 도서가 추가되었습니다.');");
					out.print("location.href = '/main/BookList';");
					out.println("</script>");
			
				}
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
	private int idCheck(String id) throws Exception {
		ResultSet rs = stmt.executeQuery("SELECT * FROM book WHERE id =" + id);
		try {
			if(rs.next())
				return 1;
			else
				return 2;
		}
		catch(Exception e) {
			throw new ServletException(e);
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
