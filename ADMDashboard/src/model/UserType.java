package model;

public enum UserType {
	
	ADMIN, ORGREP;
	
	public static UserType getUserType(String value) {
		switch(value) {
		case "ADMIN": return ADMIN;
		case "ORGREP": return ORGREP;
		}

		return null;
	}

}
