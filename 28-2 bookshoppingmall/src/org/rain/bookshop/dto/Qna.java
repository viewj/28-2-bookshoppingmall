// 2018.07.23 정규룡, 전재현 짝코딩
package org.rain.bookshop.dto;

public class Qna {
	private int qna_no;
	private int member_no;
	private String qna_title;
	private String qna_content;
	private String qna_date;
	
	public void setQna_no(int qna_no) {
		this.qna_no = qna_no; 
	}
	
	public int getQna_no() {
		return qna_no;
	}

	public int getMember_no() {
		return member_no;
	}

	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}

	public String getQna_title() {
		return qna_title;
	}

	public void setQna_title(String qna_title) {
		this.qna_title = qna_title;
	}

	public String getQna_content() {
		return qna_content;
	}

	public void setQna_content(String qna_content) {
		this.qna_content = qna_content;
	}

	public String getQna_date() {
		return qna_date;
	}

	public void setQna_date(String qna_date) {
		this.qna_date = qna_date;
	}
	
}
