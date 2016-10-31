package model;

import java.io.Serializable;

public class Requirement implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String TABLE_NAME = "requirement";
	public static final String COL_REQID = "requirementID";
	public static final String COL_REQNAME = "requirementname";
	
	private int reqID;
	private String reqName;
	
	public Requirement() {}

	public Requirement(int reqID, String reqName) {
		super();
		this.reqID = reqID;
		this.reqName = reqName;
	}

	public int getReqID() {
		return reqID;
	}

	public void setReqID(int reqID) {
		this.reqID = reqID;
	}

	public String getReqName() {
		return reqName;
	}

	public void setReqName(String reqName) {
		this.reqName = reqName;
	}	

}
