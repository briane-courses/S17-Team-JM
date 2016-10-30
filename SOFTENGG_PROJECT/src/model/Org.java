package model;

import java.io.Serializable;

public class Org implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	public static final String TABLE_NAME = "org";
	public static final String COL_ORGCODE = "orgcode";
	public static final String COL_ORGNAME = "orgname";
	public static final String COL_IDNUMBER = "userID";
	
	private String orgcode;
	private String orgname;
	private int userID;
	
	public Org() {}
	
	public Org(String orgcode, String orgname, int userID) {
		super();
		this.orgcode = orgcode;
		this.orgname = orgname;
		this.userID = userID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getOrgcode() {
		return orgcode;
	}

	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

}
