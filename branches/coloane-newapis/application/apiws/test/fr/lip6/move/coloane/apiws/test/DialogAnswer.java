package fr.lip6.move.coloane.apiws.test;

import java.util.List;

import fr.lip6.move.coloane.interfaces.objects.dialog.IDialogAnswer;

public class DialogAnswer implements IDialogAnswer {
	
	private int buttonType;
	
	private int idDialg;
	
	private List<Integer> objects;
	
	private String value;
	
	private boolean modified;
	
	public DialogAnswer(int idDialg, int buttonType, boolean modified, String value, List<Integer> objects){
		this.buttonType = buttonType;
		this.idDialg = idDialg;
		this.objects = objects;
		this.value = value;
		this.modified = modified;
	}

	public int getButtonType() {
		return buttonType;
	}

	public int getIdDialog() {
		return idDialg;
	}

	public List<Integer> getObjects() {
		return objects;
	}

	public String getValue() {
		return value;
	}

	public boolean isModified() {
		return modified;
	}

}
