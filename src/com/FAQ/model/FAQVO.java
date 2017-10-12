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
		FAQID = fAQID;
	}
	public String getFAQTitle() {
		return FAQTitle;
	}
	public void setFAQTitle(String fAQTitle) {
		FAQTitle = fAQTitle;
	}
	public String getFAQContent() {
		return FAQContent;
	}
	public void setFAQContent(String fAQComtent) {
		FAQContent = fAQComtent;
	}
	public Timestamp getFAQDate() {
		return FAQDate;
	}
	public void setFAQDate(Timestamp fAQdate) {
		FAQDate = fAQdate;
	}
	
	
	
}
