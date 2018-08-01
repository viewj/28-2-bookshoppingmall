// 2018.07.25 28기 전재현
package org.rain.bookshop.dto;

public class Bookreview {
	
	private int bookreviewNo;
	private int bookNo;
	private int memberNo;
	private String bookreviewContent;
	
	public int getBookreviewNo() {
		return bookreviewNo;
	}
	public void setBookreviewNo(int bookreviewNo) {
		this.bookreviewNo = bookreviewNo;
	}
	public int getBookNo() {
		return bookNo;
	}
	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public String getBookreviewContent() {
		return bookreviewContent;
	}
	public void setBookreviewContent(String bookreviewContent) {
		this.bookreviewContent = bookreviewContent;
	}
	
}
