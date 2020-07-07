package web;

import java.io.*;
import java.sql.*;
import java.util.List;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet("/main/rentalBook")
public class rentalBookServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String field_ = request.getParameter("f");
		String query_ = request.getParameter("q");
		String page_ = request.getParameter("p");
		
		String field = "rental";
		if(field_ != null && !field_.equals("")) {
			field = field_;
		}
		String query = "N";
		if(query_ != null && !query_.equals("")) {
			query = query_;
		}
		int page = 1;
		if(page_ != null && !page_.equals("")) {
			page = Integer.parseInt(page_);
		}
		BookListService service = new BookListService();
		List<Book> list = service.getRentalBook(field, query, page);
		int cnt = service.getBookCount(field, query);
		
		request.setAttribute("list", list);
		request.setAttribute("cnt", cnt);
		request.getRequestDispatcher("/view/main/BookList.jsp").forward(request, response);
	}
}
