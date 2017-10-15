package server;

public class ChatMem {
	private String userID;
	private String receiverID;
	private String userName;
	private String message;
	
	public ChatMem(String userID, String receiverID, String userName, String message) {
		super();
		this.userID = userID;
		this.receiverID = receiverID;
		this.userName = userName;
		this.message = message;
	}
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getReceiverID() {
		return receiverID;
	}
	public void setReceiverID(String receiverID) {
		this.receiverID = receiverID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
