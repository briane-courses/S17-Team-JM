package model;

import java.io.Serializable;

public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public static final String TABLE_NAME = "user";
	public static final String COL_IDNUMBER = "userID";
	public static final String COL_EMAIL = "email";
	public static final String COL_USERTYPE = "usertype";
	public static final String COL_ORGCODE = "orgcode";
	
	private int userID;
	private String email;
	private UserType userType;
	private String orgcode;
	
	public User() {}

	public User(int userID, String email, UserType userType, String orgcode) {
		super();
		this.userID = userID;
		this.email = email;
		this.userType = userType;
		this.orgcode = orgcode;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public String getOrgcode() {
		return orgcode;
	}

	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}

}
