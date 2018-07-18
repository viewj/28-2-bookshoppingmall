// 2018-07-18 정규룡
package service;

public class Publisher {
	private int publisherNo;
	private String publisherName;
	private String publisherWebsite;
	
	public void setPublisherNo(int publisherNo) {
		this.publisherNo = publisherNo;
	}
	public int getPublisherNo() {
		return publisherNo;
	}
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}
	public String getPublisherName() {
		return publisherName;
	}
	
	public void setPublisherWebsite(String publisherWebsite) {
		this.publisherWebsite = publisherWebsite;
	}
	public String getPublisherWebsite() {
		return publisherWebsite;
	}
}
