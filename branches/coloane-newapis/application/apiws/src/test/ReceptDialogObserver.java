package test;

import fr.lip6.move.coloane.interfaces.api.exceptions.ApiException;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptDialogObserver;
import fr.lip6.move.coloane.interfaces.api.session.IApiSession;
import fr.lip6.move.coloane.interfaces.objects.dialog.IDialog;

public class ReceptDialogObserver implements IReceptDialogObserver {
	
	private int idDialog;
	
	private IApiSession session;
	
	public void update(IDialog dialog) {
		// TODO Auto-generated method stub
		System.out.println("DIALOG");
		this.idDialog = dialog.getId();
		try {
			session.sendDialogAnswer(getIdDialog(), IDialog.DLG_NO_BUTTON, false, "", null, null);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getIdDialog(){
		return idDialog;
	}
	
	public void setSession(IApiSession s){
		this.session =s;
	}

}