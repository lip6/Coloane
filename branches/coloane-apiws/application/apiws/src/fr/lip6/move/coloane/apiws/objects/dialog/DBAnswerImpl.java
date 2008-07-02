package fr.lip6.move.coloane.apiws.objects.dialog;

import java.util.ArrayList;

import fr.lip6.move.coloane.apiws.interfaces.objects.dialog.IDBAnswer;
import fr.lip6.move.wrapper.ws.WrapperStub.DBAnswer;

public class DBAnswerImpl implements IDBAnswer {
	
    private int id;
    
    private int buttonAnswer;
    
    private boolean modified;
    
    private String value;
    
    private ArrayList<String> lines;
    
    private ArrayList<Integer> objects;
    
    public DBAnswerImpl(DBAnswer answer){
    	this.id = answer.getId();
    	this.buttonAnswer = answer.getButtonAnswer();
    	this.modified = answer.getModified();
    	this.value = answer.getValue();
    	
    	this.lines = new ArrayList<String>();
    	if (answer.getLines() != null){
    		for (int i=0; i<answer.getLines().length;i++)
    			this.lines.add(answer.getLines()[i]);    		
    	}
    	
    	this.objects = new ArrayList<Integer>();
    	if (answer.getObjects() != null){
    		for (int i=0; i<answer.getObjects().length;i++)
    			this.objects.add(answer.getObjects()[i]);
    	}
    }
    
    public DBAnswerImpl(int id, int buttonAnswer,boolean modified,String value){
    	this.id=id;
        this.buttonAnswer=buttonAnswer;
        this.modified=modified;
        this.value=value;
        this.lines=new ArrayList<String>();
        this.objects=new ArrayList<Integer>();
    }
    
    public DBAnswerImpl(int id, int buttonAnswer,boolean modified,String value,ArrayList<String> lines,ArrayList<Integer> objects){
    	this.id=id;
        this.buttonAnswer=buttonAnswer;
        this.modified=modified;
        this.value=value;
        this.lines=lines;
        this.objects=objects;
    }
    
	public int getButtonAnswer() {
		return buttonAnswer;
	}

	public int getId() {
		return id;
	}

	public ArrayList<String> getLines() {
		return lines;
	}

	public ArrayList<Integer> getObjects() {
		return objects;
	}

	public String getValue() {
		return value;
	}

	public boolean isModified() {
		return modified;
	}

	public void setButtonAnswer(int buttonAnswer) {
		this.buttonAnswer = buttonAnswer;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setLines(ArrayList<String> lines) {
		this.lines = lines;
	}

	public void setModified(boolean modified) {
		this.modified = modified;
	}

	public void setObjects(ArrayList<Integer> objects) {
		this.objects = objects;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
