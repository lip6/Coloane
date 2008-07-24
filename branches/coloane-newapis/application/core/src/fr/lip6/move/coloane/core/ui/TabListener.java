package fr.lip6.move.coloane.core.ui;

import java.util.logging.Logger;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.motor.Motor;

import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IWorkbenchPartReference;

public class TabListener implements IPartListener2 {
	/** Le logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** {@inheritDoc} */
	public final void partActivated(IWorkbenchPartReference partRef) {
		LOGGER.finer("$$$$Activation de l'onglet : " + partRef.getPartName()); //$NON-NLS-1$
		Motor.getInstance().resumeSession(partRef.getPartName());
	}

	/** {@inheritDoc} */
	public final void partClosed(IWorkbenchPartReference partRef) {
		LOGGER.finer("$$$$Fermeture de l'onglet : " + partRef.getPartName()); //$NON-NLS-1$
		Motor.getInstance().deleteSession(partRef.getPartName());
	}

	/** {@inheritDoc} */
	public final void partDeactivated(IWorkbenchPartReference partRef) {
		LOGGER.finer("$$$$Desactivation de l'onglet : " + partRef.getPartName()); //$NON-NLS-1$
//		Motor.getInstance().suspendSession(partRef.getPartName());
	}

	/** {@inheritDoc} */
	public final void partOpened(IWorkbenchPartReference partRef) {
		LOGGER.finer("$$$$Ouverture de l'onglet : " + partRef.getPartName()); //$NON-NLS-1$
		//Motor.getInstance().resumeSession(partRef.getPartName());
	}

	/** {@inheritDoc} */
	public final void partBroughtToTop(IWorkbenchPartReference partRef) { }

	/** {@inheritDoc} */
	public final void partHidden(IWorkbenchPartReference partRef) { }

	/** {@inheritDoc} */
	public final void partInputChanged(IWorkbenchPartReference partRef) { }

	/** {@inheritDoc} */
	public final void partVisible(IWorkbenchPartReference partRef) { }
}
