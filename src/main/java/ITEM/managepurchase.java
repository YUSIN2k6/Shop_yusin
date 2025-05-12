package ITEM;

import java.sql.Date;

public class managepurchase {
	private int stt, totalprice, totalqty;
	private String idhd, hour;
	private Date day;
	public managepurchase() {
		
	}
	public managepurchase(int stt, String idhd, int totalprice, int totalqty, String hour, Date day) {
		super();
		this.stt = stt;
		this.idhd = idhd;
		this.totalprice = totalprice;
		this.totalqty = totalqty;
		this.hour = hour;
		this.day = day;
	}
	public int getStt() {
		return stt;
	}
	public void setStt(int stt) {
		this.stt = stt;
	}
	public int getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}
	public int getTotalqty() {
		return totalqty;
	}
	public void setTotalqty(int totalqty) {
		this.totalqty = totalqty;
	}
	public String getIdhd() {
		return idhd;
	}
	public void setIdhd(String idhd) {
		this.idhd = idhd;
	}
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}
	public Date getDay() {
		return day;
	}
	public void setDay(Date day) {
		this.day = day;
	}
	
	
}
