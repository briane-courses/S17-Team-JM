package model;

public enum Status {

	PENDING, DONE;
	
	public static Status getStatus(String value) {
		switch(value) {
		case "PENDING": return PENDING;
		case "DONE": return DONE;
		}

		return null;
	}
}
