package web;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet("/main/BookDetail")
public class BookDetailServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		BookListService service = new BookListService();
		Book book = service.getBook(id);
		request.setAttribute("b", book);

		request.getRequestDispatcher("/view/main/BookDetail.jsp").forward(request, response);
	}
}
