package org.rain.bookshop.dto;

public class ShoppingPurchaseList {

	private int shoppingcartNo;
	private int memberNo;
	private int bookNo;
	private int bookPoint;
	private int shoppingcartAmount;
	private int shoppingcartPrice;
	private String bookName;
	private String shoppingcartDate;
	private int shoppingcartMaxAmount;
	
	public int getShoppingcartNo() {
		return shoppingcartNo;
	}
	public void setShoppingcartNo(int shoppingcartNo) {
		this.shoppingcartNo = shoppingcartNo;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public int getBookNo() {
		return bookNo;
	}
	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}
	public int getBookPoint() {
		return bookPoint;
	}
	public void setBookPoint(int bookPoint) {
		this.bookPoint = bookPoint;
	}
	public int getShoppingcartAmount() {
		return shoppingcartAmount;
	}
	public void setShoppingcartAmount(int shoppingcartAmount) {
		this.shoppingcartAmount = shoppingcartAmount;
	}
	public int getShoppingcartPrice() {
		return shoppingcartPrice;
	}
	public void setShoppingcartPrice(int shoppingcartPrice) {
		this.shoppingcartPrice = shoppingcartPrice;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getShoppingcartDate() {
		return shoppingcartDate;
	}
	public void setShoppingcartDate(String shoppingcartDate) {
		this.shoppingcartDate = shoppingcartDate;
	}
	public int getShoppingcartMaxAmount() {
		return shoppingcartMaxAmount;
	}
	public void setShoppingcartMaxAmount(int shoppingcartMaxAmount) {
		this.shoppingcartMaxAmount = shoppingcartMaxAmount;
	}
	
}
