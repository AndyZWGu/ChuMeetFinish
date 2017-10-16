package com.FAQ.model;

import java.sql.Timestamp;

public class FAQVO implements java.io.Serializable {

	private Integer FAQID;
	private String FAQTitle;
	private String FAQContent;
	private Timestamp FAQDate;
	public Integer getFAQID() {
		return FAQID;
	}
	public void setFAQID(Integer fAQID) {
		this.FAQID = fAQID;
	}
	public String getFAQTitle() {
		return FAQTitle;
	}
	public void setFAQTitle(String fAQTitle) {
		this.FAQTitle = fAQTitle;
	}
	public String getFAQContent() {
		return FAQContent;
	}
	public void setFAQContent(String fAQContent) {
		this.FAQContent = fAQContent;
	}
	public Timestamp getFAQDate() {
		return FAQDate;
	}
	public void setFAQDate(Timestamp fAQdate) {
		this.FAQDate = fAQdate;
	}
	
	
	
}
