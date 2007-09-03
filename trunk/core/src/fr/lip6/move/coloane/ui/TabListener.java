package fr.lip6.move.coloane.ui;

import fr.lip6.move.coloane.main.Coloane;
import fr.lip6.move.coloane.motor.Motor;

import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IWorkbenchPartReference;

public class TabListener implements IPartListener2 {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener2#partActivated(org.eclipse.ui.IWorkbenchPartReference)
	 */
	public final void partActivated(IWorkbenchPartReference partRef) {
		Coloane.getLogger().finer("Activation de l'onglet : " + partRef.getPartName()); //$NON-NLS-1$
		Motor.getInstance().resumeSession(partRef.getPartName());
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener2#partClosed(org.eclipse.ui.IWorkbenchPartReference)
	 */
	public final void partClosed(IWorkbenchPartReference partRef) {
		Coloane.getLogger().finer("Fermeture de l'onglet : " + partRef.getPartName()); //$NON-NLS-1$
		//Motor.getInstance().suspendSession(partRef.getPartName());
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener2#partDeactivated(org.eclipse.ui.IWorkbenchPartReference)
	 */
	public final void partDeactivated(IWorkbenchPartReference partRef) {
		Coloane.getLogger().finer("Desactivation de l'onglet : " + partRef.getPartName()); //$NON-NLS-1$
		//Motor.getInstance().suspendSession(partRef.getPartName());
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener2#partOpened(org.eclipse.ui.IWorkbenchPartReference)
	 */
	public final void partOpened(IWorkbenchPartReference partRef) {
		Coloane.getLogger().finer("Ouverture de l'onglet : " + partRef.getPartName()); //$NON-NLS-1$
		//Motor.getInstance().resumeSession(partRef.getPartName());
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener2#partBroughtToTop(org.eclipse.ui.IWorkbenchPartReference)
	 */
	public final void partBroughtToTop(IWorkbenchPartReference partRef) { }

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener2#partHidden(org.eclipse.ui.IWorkbenchPartReference)
	 */
	public final void partHidden(IWorkbenchPartReference partRef) { }

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener2#partInputChanged(org.eclipse.ui.IWorkbenchPartReference)
	 */
	public final void partInputChanged(IWorkbenchPartReference partRef) { }

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IPartListener2#partVisible(org.eclipse.ui.IWorkbenchPartReference)
	 */
	public final void partVisible(IWorkbenchPartReference partRef) { }
}
