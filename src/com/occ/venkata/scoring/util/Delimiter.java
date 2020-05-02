package com.occ.venkata.scoring.util;

public enum Delimiter {
	
	COMMA(",");
	
	private String delimiter;
	private Delimiter(String d) {
		this.delimiter = d;
	}
	
	public String value() {
		return delimiter;
	}
}
