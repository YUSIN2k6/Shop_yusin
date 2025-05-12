package ITEM;

public class detailitem {
	private String name;
	private int id, price, qty ,totalprice;
	public detailitem() {
		
	}
	public detailitem(int id, String name,int qty,  int price, int totalprice) {
		super();
		this.name = name;
		this.id = id;
		this.price = price;
		this.qty = qty;
		this.totalprice = totalprice;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}
	
}
