package test;

import java.util.ArrayList;

import fr.lip6.move.coloane.interfaces.api.exceptions.ApiException;
import fr.lip6.move.coloane.interfaces.api.session.IApiSession;

public class DialogThread extends Thread {

	private IApiSession session;
	
	private int idDialog;
	
	private int buttonAnswer;
	
	private boolean modified;
	
	private String value;
	
	private ArrayList<String> lines;
	
	private ArrayList<Integer> objects;
	
	public DialogThread(int idDialog, int buttonAnswer, boolean modified, String value, ArrayList<String> lines, ArrayList<Integer> objects) {
		this.idDialog = idDialog;
		this.buttonAnswer = buttonAnswer;
		this.modified = modified;
		this.value = value;
		this.lines = lines;
		this.objects = objects;
	}
		
	public void run() {
		try {
			session.sendDialogAnswer(idDialog, buttonAnswer, modified, value, lines, objects);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
