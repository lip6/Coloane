package fr.lip6.move.coloane.core.ui.actions;

import java.util.Arrays;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;

import fr.lip6.move.coloane.core.communications.Com;

/**
 * Classe de test pour les jobs ou n'importe quoi d'autre
 */
public class TestAction implements IWorkbenchWindowActionDelegate {
	private static int count = 0;
	private Job job;

	/** {@inheritDoc} */
	public final void dispose() {
	}

	/** {@inheritDoc} */
	public final void init(IWorkbenchWindow window) {
	}

	/** {@inheritDoc} */
	public final void run(IAction action) {
		System.err.println(Arrays.asList(Job.getJobManager().find(null)));
		for (Job job : Job.getJobManager().find(null)) {
			System.err.println(job + " - " + job.getState());
			if (job.getName().equals("Open session")
					|| job.getName().equals("Petri net syntax checker")) {
				System.err.println("Fin de " + job);
				job.done(Status.OK_STATUS);
			}
		}
//		testJob();
//		testJob2();
	}

	/** {@inheritDoc} */
	public final void selectionChanged(IAction action, ISelection selection) {
	}

	/**
	 * Test des régles sur un job
	 */
	private void testJob2() {
		final int id = count++;
		Job j = new Job("job " + id) {
			@Override
			protected IStatus run(IProgressMonitor monitor) {
				monitor.beginTask("Tache" + id, 100);
				for (int i = 0; i < 100; i++) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
					}
					monitor.worked(1);
				}
				return Status.OK_STATUS;
			}
		};
//		j.setUser(true);
		j.setRule(ResourcesPlugin.getWorkspace().getRoot());
//		j.setRule(Com.getInstance().getRule());
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
