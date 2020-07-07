package web;

import java.io.*;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet("/main/rental")
public class RentalServlet extends HttpServlet {
	Connection conn = null;
	Statement stmt =  null;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		String id_ = request.getParameter("id");
		if (id_ == null || id_.equals("")) {
			out.println("<script>");
			out.println("alert('ID를 입력해주세요.');");
			out.println("window.history.back();");
			out.println("</script>");
		}
		else if(!isDigit(id_)) {
			out.println("<script>");
			out.println("alert('ID는 숫자로 입력해주세요.');");
			out.println("window.history.back();");
			out.println("</script>");
		}
		else {
			try {
				int id = Integer.parseInt(id_);
				String sql ="UPDATE book SET rental=\"N\", count=count+1 WHERE id = "+id+";";
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webdb?serverTimezone=UTC", "root", "rlaehdrjs3");
				stmt = conn.createStatement();
				if(1==rentCh(id_)) {
					stmt.executeUpdate(sql);
					response.sendRedirect("/view/main/rentalDone.jsp?OUTPUT=SUCCESS");
				}
				else if(2==rentCh(id_))
					response.sendRedirect("/view/main/rentalDone.jsp?OUTPUT=NONE");
				else
					response.sendRedirect("/view/main/rentalDone.jsp?OUTPUT=FAIL");
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
	private int rentCh(String id_) throws Exception {
		ResultSet rs = stmt.executeQuery("SELECT * FROM book WHERE id =" + id_);
		try {
			if(rs.next()) {
				String nowRental = rs.getString("rental");
				if(nowRental.charAt(0) == 'Y')
					return 1;
				else
					return 2;
			}
			else
				return 3;
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
