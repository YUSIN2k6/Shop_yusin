package ITEM;

public class temp {
	private String name;
	private int id, price, qty ,totalprice;
	public temp() {
		
	}
	public temp(int id , String name, int qty,int price,int totalprice) {
		this.name = name;
		this.price = price;
		this.qty = qty;
		this.id = id;
		this.totalprice = totalprice;
	}
	
	
	public int getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
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
