package fr.lip6.move.coloane.api.objects;

import fr.lip6.move.coloane.interfaces.IDialogCom;

public class DialogCom implements IDialogCom {
	private int id;
	private int type;
	private int buttonType;
	private String title;
	private String help;
	private String message;
	private int inputType;
	private int multiLine;
	private String def = "";

	
	public DialogCom(int id, int type, int buttonType, String title, String help, String message, int inputType, int multiLine, String def) {
		this.id = id;
		this.type = type;
		this.buttonType = buttonType;
		this.title = title;
		this.help = help;
		this.message = message;
		this.inputType = inputType;
		this.multiLine = multiLine;
		this.def = def;
	}


	public int getButtonType() {
		return buttonType;
	}

	
	public void setButtonType(int buttonType) {
		this.buttonType = buttonType;
	}


	public String getDef() {
		return def;
	}


	public void setDef(String def) {
		this.def = def;
	}


	public String getHelp() {
		return help;
	}


	public void setHelp(String help) {
		this.help = help;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getInputType() {
		return inputType;
	}


	public void setInputType(int inputType) {
		this.inputType = inputType;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public int getMultiLine() {
		return multiLine;
	}


	public void setMultiLine(int multiLine) {
		this.multiLine = multiLine;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}
	
}