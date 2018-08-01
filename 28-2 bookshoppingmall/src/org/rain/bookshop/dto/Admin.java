package org.rain.bookshop.dto;

public class Admin {
	private int adminNo;
	private String adminId;
	private String adminPw;
	private String adminName;
	private String adminDate;
	
	public void setAdminNo(int adminNo) {
		this.adminNo = adminNo;
	}
	public int getAdminNo() {
		return adminNo;
	}
	public void setAdminId(String adminId) {
		this .adminId = adminId;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminPw(String adminPw) {
		this .adminPw = adminPw;
	}
	public String getAdminPw() {
		return adminPw;
	}
	public void setAdminName(String adminName) {
		this .adminName = adminName;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminDate(String adminDate) {
		this .adminDate = adminDate;
	}
	public String getAdminDate() {
		return adminDate;
	}
}
