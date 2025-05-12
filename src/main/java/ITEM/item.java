package ITEM;

public class item {
	private int id;
	private String pname;
	private String color;
	private int qty;
	protected long price;
	private String date;
	public item() {	}
	public item(int id, String pname, String color, int qty, long price, String date) {
		this.id = id;
		this.pname = pname;
		this.color = color;
		this.qty = qty;
		this.price = price;
		this.date = date;
	}
	public int getPid() {
		return id;
	}
	public void setPid(int id) {
		this.id = id;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
