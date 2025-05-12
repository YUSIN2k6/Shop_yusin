package ITEM;

import java.sql.Date;

public class infobuy {
	private int stt, totalprice;
	private String idhd, hour;
	private Date day;
	public infobuy() {
		
	}
	public infobuy(int stt, String idhd, int totalprice, String hour, Date day) {
		super();
		this.stt = stt;
		this.totalprice = totalprice;
		this.idhd = idhd;
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
