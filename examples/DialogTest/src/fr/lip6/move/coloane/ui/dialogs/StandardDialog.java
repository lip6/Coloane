package fr.lip6.move.coloane.ui.dialogs;



public class StandardDialog extends CAMIDialog {
	
	public StandardDialog(int id, int buttonType,
			String title, String help, String message,
			int inputType, int multiLine, String defaultValue) {
		super(id, buttonType, title, help, message,
				inputType, multiLine, defaultValue);
		
		this.message = message;
	}
	
	
	
	/*protected Image getImage() {
		return new Display().getSystemImage(SWT.ICON_INFORMATION);
	}*/
}
