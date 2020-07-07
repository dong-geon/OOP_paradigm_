package web;

import java.sql.*;
import java.util.*;

public class BookListService {
	public List<Book> getBookList(){
		return getBookList("name", "", 1);
	}
	public List<Book> getBookList(int page){
		return getBookList("name", "", page);
	}
	public List<Book> getBookList(String field, String query, int page){
			List<Book> list = new ArrayList<>();
			String sql = "SELECT * FROM book WHERE "+ field +" LIKE ? ORDER BY id ASC LIMIT ?, ?";
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webdb?serverTimezone=UTC", "root",
						"rlaehdrjs3");
				PreparedStatement stmt = conn.prepareStatement(sql);
				if(field.equals("rental") && query.equals("N")) {
					stmt.setString(1, "N");
				}else {
					stmt.setString(1, "%"+query+"%");
				}
				stmt.setInt(2, (page-1)*10);
				stmt.setInt(3, page*10);
				 
				ResultSet rs = stmt.executeQuery();
				while(rs.next()){
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String writer = rs.getString("writer");
					int cost = rs.getInt("cost");
					String rental = rs.getString("rental");
					int count = rs.getInt("count");
					
					Book book = new Book(id, name, writer, cost, rental, count);
					list.add(book);
				}
			   	rs.close();
			    stmt.close();
			    conn.close();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return list;
			
	}
	public List<Book> getRentalCount(){
		return getRentalCount("name", "", 1);
	}
	public List<Book> getRentalCount(int page){
		return getRentalCount("name", "", page);
	}
	public List<Book> getRentalCount(String field, String query, int page){
			List<Book> list = new ArrayList<>();
			String sql = "SELECT * FROM book WHERE "+ field +" LIKE ? ORDER BY count DESC LIMIT ?, ?";
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webdb?serverTimezone=UTC", "root",
						"rlaehdrjs3");
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+query+"%");
				stmt.setInt(2, (page-1)*10);
				stmt.setInt(3, page*10);
				 
				ResultSet rs = stmt.executeQuery();
				while(rs.next()){
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String writer = rs.getString("writer");
					int cost = rs.getInt("cost");
					String rental = rs.getString("rental");
					int count = rs.getInt("count");
					
					Book book = new Book(id, name, writer, cost, rental, count);
					list.add(book);
				}
			   	rs.close();
			    stmt.close();
			    conn.close();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return list;
			
	}
	public List<Book> getRentalBook(){
		return getRentalBook("rental", "N", 1);
	}
	public List<Book> getRentalBook(int page){
		return getRentalBook("rental", "N", page);
	}
	public List<Book> getRentalBook(String field, String query, int page){
			List<Book> list = new ArrayList<>();
			String sql = "SELECT * FROM book WHERE "+ field +" LIKE ? ORDER BY id ASC LIMIT ?, ?";
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webdb?serverTimezone=UTC", "root",
						"rlaehdrjs3");
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+query+"%");
				stmt.setInt(2, (page-1)*10);
				stmt.setInt(3, page*10);
				 
				ResultSet rs = stmt.executeQuery();
				while(rs.next()){
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String writer = rs.getString("writer");
					int cost = rs.getInt("cost");
					String rental = rs.getString("rental");
					int count = rs.getInt("count");
					
					Book book = new Book(id, name, writer, cost, rental, count);
					list.add(book);
				}
			   	rs.close();
			    stmt.close();
			    conn.close();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return list;
			
	}
	public List<Book> getRentalAvailable(){
		return getRentalAvailable("rental", "Y", 1);
	}
	public List<Book> getRentalAvailable(int page){
		return getRentalAvailable("rental", "Y", page);
	}
	public List<Book> getRentalAvailable(String field, String query, int page){
			List<Book> list = new ArrayList<>();
			String sql = "SELECT * FROM book WHERE "+ field +" LIKE ? ORDER BY id ASC LIMIT ?, ?";
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webdb?serverTimezone=UTC", "root",
						"rlaehdrjs3");
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+query+"%");
				stmt.setInt(2, (page-1)*10);
				stmt.setInt(3, page*10);
				 
				ResultSet rs = stmt.executeQuery();
				while(rs.next()){
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String writer = rs.getString("writer");
					int cost = rs.getInt("cost");
					String rental = rs.getString("rental");
					int count = rs.getInt("count");
					
					Book book = new Book(id, name, writer, cost, rental, count);
					list.add(book);
				}
			   	rs.close();
			    stmt.close();
			    conn.close();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return list;
			
	}
	
	public int getBookCount() {
		return getBookCount("name", "");
	}
	public int getBookCount(String field, String query) {
		int cnt = 0;
		List<Book> list = new ArrayList<>();
		
		String sql = "SELECT COUNT(id) cnt FROM book WHERE "+ field +" LIKE ? ORDER BY id ASC";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webdb?serverTimezone=UTC", "root",
					"rlaehdrjs3");
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			
			if(field.equals("rental") && query.equals("N")) {
				stmt.setString(1, "N");
			}else {
				stmt.setString(1, "%"+query+"%");
			}
			 
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				cnt = rs.getInt("cnt");
			}
		   	rs.close();
		    stmt.close();
		    conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	public Book getBook(int id) {
		Book book = null;
		List<Book> list = new ArrayList<>();
		String sql = "select * from book where id=?";
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/webdb?serverTimezone=UTC", "root",
					"rlaehdrjs3");
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, id);
			 
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()){
				int nid = rs.getInt("id");
				String name = rs.getString("name");
				String writer = rs.getString("writer");
				int cost = rs.getInt("cost");
				String rental = rs.getString("rental");
				int count = rs.getInt("count");
				
				book = new Book(id, name, writer, cost, rental, count);
				list.add(book);
			}
		   	rs.close();
		    stmt.close();
		    conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return book;
	}
}
