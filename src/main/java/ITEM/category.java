package ITEM;

public class category {
	private String name;
	private int id, price, qty;
	public category() {
		
	}
	public category(int id , String name, int price, int qty) {
		this.name = name;
		this.price = price;
		this.qty = qty;
		this.id = id;
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	
}
