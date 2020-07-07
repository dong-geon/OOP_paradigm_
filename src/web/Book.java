package web;

public class Book {
	private int id;
	private String name;
	private String writer;
	private int cost;
	private String rental;
	private int count;
	
	public Book() {
	}
	public Book(int id, String name, String writer, int cost, String rental, int count) {
		this.id = id;
		this.name = name;
		this.writer = writer;
		this.cost = cost;
		this.rental = rental;
		this.count = count;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public String getRental() {
		return rental;
	}
	public void setRental(String rental) {
		this.rental = rental;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}	
}
