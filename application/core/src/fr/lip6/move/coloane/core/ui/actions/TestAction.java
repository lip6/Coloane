package fr.lip6.move.coloane.core.ui.actions;

import fr.lip6.move.coloane.core.model.ArcModel;
import fr.lip6.move.coloane.core.model.CoreTipModel;
import fr.lip6.move.coloane.core.model.NodeModel;
import fr.lip6.move.coloane.core.model.interfaces.ICoreTip;
import fr.lip6.move.coloane.core.motor.session.ISession;
import fr.lip6.move.coloane.core.motor.session.ISessionManager;
import fr.lip6.move.coloane.core.motor.session.MessageType;
import fr.lip6.move.coloane.core.motor.session.SessionManager;
import fr.lip6.move.coloane.core.ui.UserInterface;
import fr.lip6.move.coloane.core.ui.dialogs.DialogFactory;
import fr.lip6.move.coloane.core.ui.dialogs.IDialogUI;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.interfaces.objects.dialog.IDialog;
import fr.lip6.move.coloane.interfaces.objects.result.ITip;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

/**
 * Classe de test pour les jobs ou n'importe quoi d'autre
 */
public class TestAction implements IWorkbenchWindowActionDelegate {
	private static int count = 0;
	private Job job;

	private List<ICoreTip> tips = new ArrayList<ICoreTip>();
	private boolean tipsFlag = false;

	/** {@inheritDoc} */
	public final void dispose() {
	}

	/** {@inheritDoc} */
	public final void init(IWorkbenchWindow window) {
//		ConsolePlugin.getDefault().getConsoleManager().addConsoles(new IConsole[] {myConsole});
	}

	/** {@inheritDoc} */
	public final void run(IAction action) {
//		testConsole();
//		testTips();
//		printGraph();
//		printSessionsState();
//		testDialog();
//		testJob();
//		testJob2();
	}

	/**
	 * test l'affichage de messages dans la console pour tous les types.
	 */
	@SuppressWarnings("unused")
	private void testConsole() {
		UserInterface.getInstance().printConsoleMessage("debug", MessageType.DEBUG); //$NON-NLS-1$
		UserInterface.getInstance().printConsoleMessage("error", MessageType.ERROR); //$NON-NLS-1$
		UserInterface.getInstance().printConsoleMessage("fine", MessageType.FINE); //$NON-NLS-1$
		UserInterface.getInstance().printConsoleMessage("info", MessageType.INFO); //$NON-NLS-1$
		UserInterface.getInstance().printConsoleMessage("warning", MessageType.WARNING); //$NON-NLS-1$
	}

	/**
	 * test des tips
	 */
	@SuppressWarnings("unused")
	private void testTips() {
		final INode node = SessionManager.getInstance().getCurrentSession().getGraph().getNodes().iterator().next();
		final IArc arc = SessionManager.getInstance().getCurrentSession().getGraph().getArcs().iterator().next();
		tipsFlag = !tipsFlag;
		if (tipsFlag) {
			tips.add(new CoreTipModel(new ITip() {
				public int getIdObject() { return arc.getId(); }
				public String getName() { return "label"; } //$NON-NLS-1$
//				public String getValue() { return node.getAttribute("name").getValue(); } //$NON-NLS-1$
				public String getValue() { return "test arc"; } //$NON-NLS-1$
			}));
			tips.add(new CoreTipModel(new ITip() {
				public int getIdObject() { return node.getId(); }
				public String getName() { return "label"; } //$NON-NLS-1$
				public String getValue() { return node.getAttribute("name").getValue(); } //$NON-NLS-1$
//				public String getValue() { return "test arc"; } //$NON-NLS-1$
			}));
			System.err.println("ajout tip"); //$NON-NLS-1$
			SessionManager.getInstance().getCurrentSession().addAllTips(tips);
			((NodeModel) node).updateTips();
			((ArcModel) arc).updateTips();
		} else {
			System.err.println("suppression tip"); //$NON-NLS-1$
			SessionManager.getInstance().getCurrentSession().removeAllTips(tips);
			tips.clear();
			((NodeModel) node).updateTips();
			((ArcModel) arc).updateTips();
		}
	}

	/**
	 * Affichage basique du graphe courant
	 */
	@SuppressWarnings("unused")
	private void printGraph() {
		IGraph g = SessionManager.getInstance().getCurrentSession().getGraph();
		System.err.println("Graph(" + g.getId() + ")"); //$NON-NLS-1$ //$NON-NLS-2$
		for (INode n : g.getNodes()) {
			System.err.println("Node(" + n.getId() + ") " + (g.getNode(n.getId()) != null)); //$NON-NLS-1$ //$NON-NLS-2$
		}
		for (IArc a : g.getArcs()) {
			System.err.println("Arc(" + a.getId() + ") " + (g.getArc(a.getId()) != null)); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * Affichage de l'état des sessions
	 */
	@SuppressWarnings("unused")
	private void printSessionsState() {
		ISessionManager sm = SessionManager.getInstance();
		System.err.println();
		for (ISession session : sm.getSessions()) {
			System.err.println(session.getName() + " : " + session.getStatus()); //$NON-NLS-1$
		}
	}

	/** {@inheritDoc} */
	public final void selectionChanged(IAction action, ISelection selection) {
	}

	/**
	 * Test des boite de dialogue
	 */
	@SuppressWarnings("unused")
	private void testDialog() {
		final IDialog dialog = new IDialog() {
			public int getButtonType() { return DLG_OK_CANCEL; }
			public String getDefaultValue() { return "Valeur par défaut"; } //$NON-NLS-1$
			public String getHelp() { return "Aide"; } //$NON-NLS-1$
			public int getId() { return 0; }
			public int getInputType() { return INPUT_AUTHORIZED; }
			public int getLineType() { return MULTI_LINE_WITH_SINGLE_SELECTION; }
			public List<String> getLines() { return Arrays.asList("Ligne 1", "Ligne 2", "Ligne 3", "Ligne 4", "Ligne 5"); } //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
			public String getMessage() { return "Message"; } //$NON-NLS-1$
			public String getTitle() { return "Titre"; } //$NON-NLS-1$
			public int getType() { return DLG_ERROR; }
		};
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				IDialogUI dialogUI = DialogFactory.create(dialog);
				System.err.println(dialogUI.open());
				System.err.println(dialogUI.getDialogResult());
			}
		});
	}

	/**
	 * Test des régles sur un job
	 */
	@SuppressWarnings("unused")
	private void testJob2() {
		final int id = count++;
		Job j = new Job("job " + id) { //$NON-NLS-1$
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				monitor.beginTask("Tache" + id, 100); //$NON-NLS-1$
				for (int i = 0; i < 100; i++) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					monitor.worked(1);
				}
				return Status.OK_STATUS;
			}
		};
//		j.setUser(true);
		j.setRule(ResourcesPlugin.getWorkspace().getRoot());
		j.schedule();
	}

	/**
	 * Si aucun job de lancé, lance un nouveau job qui s'execute pendant 10 secondes
	 * puis se met en attente asynchrone jusqu'à ce qu'on relance l'action.
	 *
	 * L'annulation est gérée
	 */
	@SuppressWarnings("unused")
	private void testJob() {
		if (job != null && job.getState() != Job.NONE) {
			job.done(Status.OK_STATUS);
			job = null;
			return;
		}

		job = new Job("Mon premier job") { //$NON-NLS-1$
			private Thread current;

			@Override
			protected IStatus run(IProgressMonitor monitor) {
				current = Thread.currentThread();
				monitor.beginTask("mon job", 10); //$NON-NLS-1$
				for (int i = 0; i < 10; i++) {
					monitor.subTask("tache " + i); //$NON-NLS-1$
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						System.err.println(Thread.currentThread().isInterrupted());
					}
					monitor.worked(1);
					if (monitor.isCanceled()) {
						return Status.CANCEL_STATUS;
					}

					// Pour arréter le job sur en cas d'erreur
//					if (i == 3) {
//						return new Status(IStatus.ERROR, "apiws", "i == 3"); //$NON-NLS-1$ //$NON-NLS-2$
//					}
				}
				return ASYNC_FINISH;
			}

			@Override
			protected void canceling() {
				// Gestion en cas d'annulation
				current.interrupt();
			}
		};
		job.setPriority(Job.SHORT);
		job.setUser(true);
		job.schedule();

		job.addJobChangeListener(new JobChangeAdapter() {
			@Override
			public void done(IJobChangeEvent event) {
				System.err.println(event.getResult());
			}
		});
	}
}
