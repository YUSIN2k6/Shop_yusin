package ITEM;

public class managedetail {
	private int stt, qty, price, totalprice;
	private String idhd, name;
	public managedetail() {
		
	}
	public managedetail(int stt, String idhd, String name, int qty, int price, int totalprice) {
		super();
		this.stt = stt;
		this.idhd = idhd;
		this.name = name;
		this.qty = qty;
		this.price = price;
		this.totalprice = totalprice;
	}
	public int getStt() {
		return stt;
	}
	public void setStt(int stt) {
		this.stt = stt;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}
	public String getIdhd() {
		return idhd;
	}
	public void setIdhd(String idhd) {
		this.idhd = idhd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
