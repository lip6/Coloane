package fr.lip6.move.coloane.projects.its.checks.ui.controls;

public class ParseError {

	private String msg;
	private int charAt;
	private int len;

	public ParseError(String msg, int charAt, int len) {
		this.msg = msg;
		this.charAt = charAt;
		this.len = len;
	}
	
	public String getMsg() {
		return msg;
	}
	public int getCharAt() {
		return charAt;
	}
	public int getLen() {
		return len;
	}

}
