package fr.lip6.move.coloane.apiws.objects.dialog;

import java.util.ArrayList;

import fr.lip6.move.coloane.interfaces.objects.dialog.IDialog;
import fr.lip6.move.wrapper.ws.WrapperStub.DialogBox;

public class Dialog implements IDialog {
	
	private int id;
    
    private int type;
    
    private int typeButton;
    
    private String title;
    
    private String help;
    
    private String message;
    
    private int inputType;
    
    private int lineType;
    
    private String defaultValue;
    
    private ArrayList<String> lines;
    
	public Dialog(DialogBox dialog){
    	this.id = dialog.getId();
        this.type = dialog.getType();
        this.typeButton = dialog.getTypeButton();
        this.title = dialog.getTitle();
        this.help = dialog.getHelp();
        this.message = dialog.getMessage();
        this.inputType = dialog.getInputType();
        this.lineType = dialog.getLineType();
        this.defaultValue = dialog.getDefaultValue();
        
        this.lines = new ArrayList<String>();
        if ( dialog.getLine() != null){
        	for (int i=0; i< dialog.getLine().length; i++)
        		this.lines.add(dialog.getLine()[i]);
        }
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public String getHelp() {
		return help;
	}

	public int getId() {
		return id;
	}

	public int getInputType() {
		return inputType;
	}

	public ArrayList<String> getLines() {
		return lines;
	}

	public int getLineType() {
		return lineType;
	}

	public String getMessage() {
		return message;
	}

	public String getTitle() {
		return title;
	}

	public int getType() {
		return type;
	}

	public int getTypeButton() {
		return typeButton;
	}
	
}