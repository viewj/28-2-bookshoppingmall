package org.rain.bookshop.dto;

public class Member {
	private int memberNo;
	private String memberId;
	private String memberPw;
	private String memberName;
	private String memberAddr;
	private String memberPoint;
	private String memberDate;
	
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberId() {
		return memberId;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberAddr() {
		return memberAddr;
	}
	public void setMemberAddr(String memberAddr) {
		this.memberAddr = memberAddr;
	}
	public String getMemberPoint() {
		return memberPoint;
	}
	public void setMemberPoint(String memberPoint) {
		this.memberPoint = memberPoint;
	}
	public String getMemberDate() {
		return memberDate;
	}
	public void setMemberDate(String memberDate) {
		this.memberDate = memberDate;
	}
}
