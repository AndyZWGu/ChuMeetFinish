package com.admin.model;


import java.sql.Timestamp;

public class AdminVO implements java.io.Serializable{
		private Integer adminID;
		private String adminName;
		private String adminMail;
		private String adminPW;
		private String adminEmail;
		private Timestamp adminDate;
		private Integer adminStatus;
		
		public Integer getAdminID() {
			return adminID;
		}
		public void setAdminID(Integer adminID) {
			this.adminID = adminID;
		}
		public String getAdminName() {
			return adminName;
		}
		public void setAdminName(String adminName) {
			this.adminName = adminName;
		}
		public String getAdminMail() {
			return adminMail;
		}
		public void setAdminMail(String adminMail) {
			this.adminMail = adminMail;
		}
		public String getAdminPW() {
			return adminPW;
		}
		public void setAdminPW(String adminPW) {
			this.adminPW = adminPW;
		}
		public String getAdminEmail() {
			return adminEmail;
		}
		public void setAdminEmail(String adminEmail) {
			this.adminEmail = adminEmail;
		}
		public Timestamp getAdminDate() {
			return adminDate;
		}
		public void setAdminDate(Timestamp adminDate) {
			this.adminDate = adminDate;
		}
		public Integer getAdminStatus() {
			return adminStatus;
		}
		public void setAdminStatus(Integer adminStatus) {
			this.adminStatus = adminStatus;
		}
		
		
		
}