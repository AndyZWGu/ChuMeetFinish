package com.adminMemTicket.model;

import java.sql.Timestamp;

public class AdminMemTicketVO implements java.io.Serializable{

		private Integer memTkID;
		private Integer reporter;
		private Integer memID;
		private String memTkMsg;
		private Integer memTkStatID;
		private Timestamp memTkDate;
		private String memTkCat;
		public Integer getMemTkID() {
			return memTkID;
		}
		public void setMemTkID(Integer memTkID) {
			this.memTkID = memTkID;
		}
		public Integer getReporter() {
			return reporter;
		}
		public void setReporter(Integer reporter) {
			this.reporter = reporter;
		}
		public Integer getMemID() {
			return memID;
		}
		public void setMemID(Integer memID) {
			this.memID = memID;
		}
		public String getMemTkMsg() {
			return memTkMsg;
		}
		public void setMemTkMsg(String memTkMsg) {
			this.memTkMsg = memTkMsg;
		}
		public Integer getMemTkStatID() {
			return memTkStatID;
		}
		public void setMemTkStatID(Integer memTkStatID) {
			this.memTkStatID = memTkStatID;
		}
		public Timestamp getMemTkDate() {
			return memTkDate;
		}
		public void setMemTkDate(Timestamp memTkDate) {
			this.memTkDate = memTkDate;
		}
		public String getMemTkCat() {
			return memTkCat;
		}
		public void setMemTkCat(String memTkCat) {
			this.memTkCat = memTkCat;
		}
		
		
		
}
