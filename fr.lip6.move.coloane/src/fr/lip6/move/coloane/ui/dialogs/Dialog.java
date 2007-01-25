package fr.lip6.move.coloane.ui.dialogs;

public class Dialog {
	public int id;
	public int type;
	public int buttonType;
	public String title;
	public String help;
	public String message;
	public int inputType;
	public int multiLine;
	public String def = "";

	
	public Dialog(int id, int type, int buttonType, String title, String help, String message, int inputType, int multiLine, String def) {
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
	
}
