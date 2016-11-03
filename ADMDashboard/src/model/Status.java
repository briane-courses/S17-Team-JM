package model;

public enum Status {

	PENDING, FINISHED;
	
	public static Status getUserType(String value) {
		switch(value) {
		case "PENDING": return PENDING;
		case "FINISHED": return FINISHED;
		}

		return null;
	}
}
