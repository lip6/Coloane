package fr.lip6.move.coloane.interfaces;

import java.util.ArrayList;

public interface IDialogResult {
	
	public ArrayList<String> getText();
	
	public int getAnswerType();
	
	public int getDialogId();
	
	public boolean hasBeenModified();
	
}
