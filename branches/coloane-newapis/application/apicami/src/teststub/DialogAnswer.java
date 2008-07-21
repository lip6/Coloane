package teststub;

import java.util.ArrayList;

import fr.lip6.move.coloane.api.interfaces.IDialogAnswer;

public class DialogAnswer implements IDialogAnswer{

	private ArrayList<String> answer;
	private int answerType;
	private int dialogId ;
	private boolean hasBeenModified;
	private boolean isMultiLineAnswer;
		
    public DialogAnswer(ArrayList<String> answer, int answerType,int dialogId,
    		boolean hasBeenModified,boolean isMultiLineAnswer){
            this.answer=answer;
            this.answerType=answerType;
            this.dialogId =dialogId;
            this.hasBeenModified=hasBeenModified;
            this. isMultiLineAnswer=isMultiLineAnswer;
    }
	
	public ArrayList<String> getAnswer() {
		return this.answer;
	}

	public int getAnswerType() {
		
		return this.answerType;
	}

	public int getDialogId() {
		
		return this.dialogId;
	}

	public boolean hasBeenModified() {
	
		return this.hasBeenModified;
	}

	public boolean isMultiLineAnswer() {
		
		return this.isMultiLineAnswer;
	}

	
}
