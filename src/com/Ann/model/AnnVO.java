package com.Ann.model;

import java.sql.Timestamp;

public class AnnVO implements java.io.Serializable{
		private Integer annID;
		private Integer adminID;
		private String annName;
		private String annContent;
		private Timestamp annDate;
		public Integer getAnnID() {
			return annID;
		}
		public void setAnnID(Integer annID) {
			this.annID = annID;
		}
		public Integer getAdminID() {
			return adminID;
		}
		public void setAdminID(Integer adminID) {
			this.adminID = adminID;
		}
		public String getAnnName() {
			return annName;
		}
		public void setAnnName(String annName) {
			this.annName = annName;
		}
		public String getAnnContent() {
			return annContent;
		}
		public void setAnnContent(String annContent) {
			this.annContent = annContent;
		}
		public Timestamp getAnnDate() {
			return annDate;
		}
		public void setAnnDate(Timestamp annDate) {
			this.annDate = annDate;
		}
		
	
	
}
