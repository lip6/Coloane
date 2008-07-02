package fr.lip6.move.coloane.apiws.objects.dialog;

import java.util.ArrayList;

import fr.lip6.move.coloane.apiws.interfaces.objects.dialog.IDBAnswer;
import fr.lip6.move.coloane.apiws.interfaces.objects.dialog.IDialogBox;
import fr.lip6.move.wrapper.ws.WrapperStub.DialogBox;

public class DialogBoxImpl implements IDialogBox{

    private int id;
    
    private int type;
    
    private int typeButton;
    
    private String title;
    
    private String help;
    
    private String message;
    
    private int inputType;
    
    private int lineType;
    
    private String defaultValue;
    
    private ArrayList<String> line;
    
    private boolean show;
    
    private IDBAnswer answer;
    
    private boolean sent;
    
    public DialogBoxImpl(DialogBox dialog){
    	this.id = dialog.getId();
        this.type = dialog.getType();
        this.typeButton = dialog.getTypeButton();
        this.title = dialog.getTitle();
        this.help = dialog.getHelp();
        this.message = dialog.getMessage();
        this.inputType = dialog.getInputType();
        this.lineType = dialog.getLineType();
        this.defaultValue = dialog.getDefaultValue();
        
        // TODO verifie dialog.getLines()
        this.line = new ArrayList<String>();
        this.show = dialog.getShow();
        this.answer = new DBAnswerImpl(dialog.getAnswer());
        this.sent= dialog.getSent();
    }
    
	public IDBAnswer getAnswer() {
		return answer;
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

	public ArrayList<String> getLine() {
		return line;
	}

	public int getLineType() {
		return lineType;
	}

	public String getMessage() {
		return message;
	}

	public boolean getShow() {
		return show;
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

	public boolean isSent() {
		return sent;
	}

}
