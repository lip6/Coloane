package fr.lip6.move.coloane.test.apiws.editors;

import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IWorkbenchPartReference;

import fr.lip6.move.coloane.interfaces.api.connection.IApiConnection;
import fr.lip6.move.coloane.interfaces.api.exceptions.ApiException;
import fr.lip6.move.coloane.interfaces.api.session.IApiSession;
import fr.lip6.move.coloane.test.apiws.Activator;

public class TabListener implements IPartListener2 {
	private final static String idEditor = "fr.lip6.move.coloane.test.apiws.editors.ColoaneEditor";
	public void partActivated(IWorkbenchPartReference partRef) {
		// TODO Auto-generated method stub
		if (partRef.getId().equals(idEditor)){
			System.out.println("ACIVATED "+partRef.getTitle());
			if (Activator.getSessionController().isOpened() == false)
				return;
			if (Activator.getSessionController().getActiveSession().getSessionName().equals(partRef.getTitle()))
				return;
			try {
				Activator.getSessionController().getSession(partRef.getTitle()).resumeSession();
				Activator.getSessionController().setActiveSession(partRef.getTitle());
			} catch (ApiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("ACIVATED "+partRef.getTitle()+" : ERROR RESUME");
			}
		}
	}

	public void partBroughtToTop(IWorkbenchPartReference partRef) {
		// TODO Auto-generated method stub
		if (partRef.getId().equals(idEditor))
			System.out.println("BROUGHT_TO_TOP "+partRef.getTitle());

	}

	public void partClosed(IWorkbenchPartReference partRef) {
		// TODO Auto-generated method stub
		if (partRef.getId().equals(idEditor)){
			System.out.println("CLOSE "+partRef.getTitle());
			if (Activator.getSessionController().isOpened() == false)
				return;
			try {
				Activator.getSessionController().getSession(partRef.getTitle()).closeSession();
				Activator.getSessionController().removeSession(partRef.getTitle());
			} catch (ApiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("CLOSE "+partRef.getTitle()+" : ERROR CLOSE");
			}
		}

	}

	public void partDeactivated(IWorkbenchPartReference partRef) {
		// TODO Auto-generated method stub
		if (partRef.getId().equals(idEditor))
			System.out.println("DESACIVATED "+partRef.getTitle());

	}

	public void partHidden(IWorkbenchPartReference partRef) {
		// TODO Auto-generated method stub
		if (partRef.getId().equals(idEditor))
			System.out.println("HIDDEN "+partRef.getTitle());

	}

	public void partInputChanged(IWorkbenchPartReference partRef) {
		// TODO Auto-generated method stub
		if (partRef.getId().equals(idEditor))
			System.out.println("CHANGED "+partRef.getTitle());

	}

	public void partOpened(IWorkbenchPartReference partRef) {
		// TODO Auto-generated method stub
		if (partRef.getId().equals(idEditor)){
			System.out.println("OPEN "+partRef.getTitle());
			if (Activator.getSessionController().isOpened() == false)
				return;
			try {
				IApiConnection connection = Activator.getConnection();
				IApiSession session = connection.createApiSession();

				Activator.getSessionController().addSession(partRef.getTitle(), session);
				Activator.getSessionController().setActiveSession(partRef.getTitle());
				
				session.openSession(27062008, "AMI-Net", partRef.getTitle());

				System.out.println("OPEN SESSION: session active :"+Activator.getSessionController().getActiveSession().getSessionName());

			} catch (ApiException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("OPEN "+partRef.getTitle()+" : ERROR OPEN");
			}
		}

	}

	public void partVisible(IWorkbenchPartReference partRef) {
		// TODO Auto-generated method stub
		if (partRef.getId().equals(idEditor))
			System.out.println("VISIBLE "+partRef.getTitle());

	}

}
