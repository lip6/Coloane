package fr.lip6.move.coloane.api.warehouse.services;

import fr.lip6.move.coloane.api.warehouse.WareHouseApi;
import fr.lip6.move.coloane.interfaces.api.services.IApiService;
import fr.lip6.move.coloane.interfaces.exceptions.ServiceException;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.objects.dialog.AuthenticationDialog;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

public class Authentication implements IApiService {
	private WareHouseApi api;
	
	public Authentication(WareHouseApi api) {
		this.api = api;
	}

	private void getAuthenticationData () {
		PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
		    public void run() {
		    	Shell activeShell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
		    	AuthenticationDialog authDialog = new AuthenticationDialog(activeShell);
		    	if (authDialog.open() == Dialog.OK) {
		    		api.setAuthenticationCredential("login", authDialog.getLoginValue());
		    		api.setAuthenticationCredential("password", authDialog.getLoginValue());
		    		api.setAuthenticated(true);
		    	}
		    }
		});

	}
	
	public List<IResult> run(IGraph model, IProgressMonitor monitor) throws ServiceException {
		this.getAuthenticationData();
		return null;
	}

	public String getName() {
		return null;
	}

	public String getDescription() {
		return null;
	}

}
