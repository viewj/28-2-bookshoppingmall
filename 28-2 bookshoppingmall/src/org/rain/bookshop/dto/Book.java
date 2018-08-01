//2018-07-18 서연문
package org.rain.bookshop.dto;

public class Book {
	
	private int bookNo;
	private int bookCodeNo;
	private int publisherNo;
	private String bookName;
	private String bookAuthor;
	private int bookPrice;
	private int bookPoint;
	private int bookAmount;
	private String bookOut;
	private String bookDate;
	
	public int getBookNo() {
		return bookNo;
	}
	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}
	
	public int getBookCodeNo() {
		return bookCodeNo;
	}
	public void setBookCodeNo(int bookCodeNo) {
		this.bookCodeNo = bookCodeNo;
	}
	
	public int getPublisherNo() {
		return publisherNo;
	}
	public void setPublisherNo(int publisherNo) {
		this.publisherNo = publisherNo;
	}
	
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	
	public String getBookAuthor() {
		return bookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	
	public int getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(int bookPrice) {
		this.bookPrice = bookPrice;
	}
	
	public int getBookPoint() {
		return bookPoint;
	}
	public void setBookPoint(int bookPoint) {
		this.bookPoint = bookPoint;
	}
	
	public int getBookAmount() {
		return bookAmount;
	}
	public void setBookAmount(int bookAmount) {
		this.bookAmount = bookAmount;
	}
	
	public String getBookOut() {
		return bookOut;
	}
	public void setBookOut(String bookOut) {
		this.bookOut = bookOut;
	}
	
	public String getBookDate() {
		return bookDate;
	}
	public void setBookDate(String bookDate) {
		this.bookDate = bookDate;
	}
}