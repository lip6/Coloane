package fr.lip6.move.coloane.ui.dialogs;

import fr.lip6.move.coloane.interfaces.IDialogCom;

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
	
	public Dialog (IDialogCom dialogCom) {
		this.id = dialogCom.getId();
		this.type = dialogCom.getType();
		this.buttonType = dialogCom.getButtonType();
		this.title = dialogCom.getTitle();
		this.help = dialogCom.getHelp();
		this.message = dialogCom.getMessage();
		this.inputType = dialogCom.getInputType();
		this.multiLine = dialogCom.getMultiLine();
		this.def = dialogCom.getDef();
	}
	
}
