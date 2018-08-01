package org.rain.bookshop.dto;

public class QnaComment {
	private int qnaCommentNo;
	private int qnaNo;
	private int adminNo;
	private String qnaCommentContent;
	private String qnaCommentDate;
	public int getQnaCommentNo() {
		return qnaCommentNo;
	}
	public void setQnaCommentNo(int qnaCommentNo) {
		this.qnaCommentNo = qnaCommentNo;
	}
	public int getQnaNo() {
		return qnaNo;
	}
	public void setQnaNo(int qnaNo) {
		this.qnaNo = qnaNo;
	}
	public int getAdminNo() {
		return adminNo;
	}
	public void setAdminNo(int adminNo) {
		this.adminNo = adminNo;
	}
	public String getQnaCommentContent() {
		return qnaCommentContent;
	}
	public void setQnaCommentContent(String qnaCommentContent) {
		this.qnaCommentContent = qnaCommentContent;
	}
	public String getQnaCommentDate() {
		return qnaCommentDate;
	}
	public void setQnaCommentDate(String qnaCommentDate) {
		this.qnaCommentDate = qnaCommentDate;
	}
}
