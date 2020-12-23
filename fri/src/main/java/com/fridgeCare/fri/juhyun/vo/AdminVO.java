package com.fridgeCare.fri.juhyun.vo;

import java.sql.*;
import java.text.SimpleDateFormat;

public class AdminVO {
	private int bcnt, bno, mno, tno;
	private String id , mail, condate, joindate, body;
	private Date cdate, jdate;
	private Time ctime, jtime;
	
	public int getTno() {
		return tno;
	}
	public void setTno(int tno) {
		this.tno = tno;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public int getBcnt() {
		return bcnt;
	}
	public void setBcnt(int bcnt) {
		this.bcnt = bcnt;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCondate() {
		return condate;
	}
	public void setCondate() {
		SimpleDateFormat form1 = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat form2 = new SimpleDateFormat("HH:mm");
		condate = form1.format(cdate) + " " + form2.format(ctime);
	}
	public void setCondate(String condate) {
		this.condate = condate;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getJoindate() {
		return joindate;
	}
	public void setJoindate() {
		SimpleDateFormat form1 = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat form2 = new SimpleDateFormat("HH:mm");
		joindate = form1.format(jdate) + " " + form2.format(jtime);
	}
	public void setJoindate(String joindate) {
		this.joindate = joindate;
	}
	public Date getCdate() {
		return cdate;
	}
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	public Date getJdate() {
		return jdate;
	}
	public void setJdate(Date jdate) {
		this.jdate = jdate;
	}
	public Time getCtime() {
		return ctime;
	}
	public void setCtime(Time ctime) {
		this.ctime = ctime;
		setCondate();
	}
	public Time getJtime() {
		return jtime;
	}
	public void setJtime(Time jtime) {
		this.jtime = jtime;
		setJoindate();
	}
	
	
}
