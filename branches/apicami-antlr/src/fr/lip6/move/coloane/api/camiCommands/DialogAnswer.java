package fr.lip6.move.coloane.api.camiCommands;

import com.sun.tools.javac.util.List;

public final class DialogAnswer {

	public int dialogID;
	public boolean canceledAnswer;
	public boolean hasModifiedEntry;
	public String valueEntered;
	public List<String> lines;

	public DialogAnswer(int dialogID, boolean canceledAnswer, boolean hasModifiedEntry, String valueEntered, List<String> lines) {
		this.dialogID = dialogID;
		this.canceledAnswer = canceledAnswer;
		this.hasModifiedEntry = hasModifiedEntry;
		this.valueEntered = valueEntered;
		this.lines = lines;
	}

	public DialogAnswer(int dialogID, boolean canceledAnswer, boolean hasModifiedEntry, String valueEntered) {
		this(dialogID,canceledAnswer,hasModifiedEntry,valueEntered,null);
	}
	
	public void addLine(String line) {
		lines.add(line);
	}

}
