package fr.lip6.move.coloane.ui.dialogs;

import java.util.ArrayList;

public interface ITextArea {
	public void addChoice(String choice);
	
	/**
	 * Returns the text in this text area.<br/>
	 * 
	 * @return
	 */
	public ArrayList<String> getText();
	
	public void setToolTiptext(String toolTipText);
}
