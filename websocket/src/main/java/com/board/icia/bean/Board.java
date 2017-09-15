package com.board.icia.bean;

import java.sql.Timestamp; //

public class Board {
	private int bnum;
	private String btitle;
	private String bcontents;
	private String bid;  //mid
	private String mname;
	private Timestamp bdate;
	private int bviews;
	private String bfile;
	
	public int getBnum() {
		return bnum;
	}
	public String getBfile() {
		return bfile;
	}
	public void setBfile(String bfile) {
		this.bfile = bfile;
	}
	public void setBnum(int bnum) {
		this.bnum = bnum;
	}
	public String getBtitle() {
		return btitle;
	}
	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}
	public String getBcontents() {
		return bcontents;
	}
	public void setBcontents(String bcontents) {
		this.bcontents = bcontents;
	}
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public Timestamp getBdate() {
		return bdate;
	}
	public void setBdate(Timestamp bdate) {
		this.bdate = bdate;
	}
	public int getBviews() {
		return bviews;
	}
	public void setBviews(int bviews) {
		this.bviews = bviews;
	}
	
	
	
}
