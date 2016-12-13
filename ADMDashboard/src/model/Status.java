package model;

public enum Status {

	PENDING, DONE, NOT_PASSED;
	
	public static Status getStatus(String value) {
		switch(value) {
		case "PENDING": return PENDING;
		case "NOT_PASSED": return NOT_PASSED;
		case "DONE": return DONE;
		}

		return null;
	}
}
