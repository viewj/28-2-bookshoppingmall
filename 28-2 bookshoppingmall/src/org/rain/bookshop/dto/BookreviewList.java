package org.rain.bookshop.dto;

public class BookreviewList {
	
	private Book book;
	private Member member;
	private Bookreview bookreview;
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public Bookreview getBookreview() {
		return bookreview;
	}
	public void setBookreview(Bookreview bookreview) {
		this.bookreview = bookreview;
	}
	
}
