package fr.lip6.move.coloane.apiws.interfaces.objects.dialog;

import java.util.ArrayList;

public interface IDBAnswer {
	
	public int getId();
	
	public void setId(int id);
	
	public int getButtonAnswer();
	
	public void setButtonAnswer(int buttonAnswer);
	
	public boolean isModified();
	
	public void setModified(boolean modified);
	
	public String getValue();
	
	public void setValue(String value);
	
	public ArrayList<String> getLines();
	
	public void setLines(ArrayList<String> lines);
	
	public ArrayList<Integer> getObjects();
	
	public void setObjects(ArrayList<Integer> objects);
}
