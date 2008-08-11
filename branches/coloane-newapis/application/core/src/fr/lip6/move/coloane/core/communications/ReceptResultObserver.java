package fr.lip6.move.coloane.core.communications;

import fr.lip6.move.coloane.core.motor.Motor;
import fr.lip6.move.coloane.interfaces.api.observers.IReceptResultObserver;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;

import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

/**
 * Permet d'être notifié de la fin de l'execution d'un service et on récupère le résultat.
 */
public class ReceptResultObserver implements IReceptResultObserver {

	/** {@inheritDoc} */
	public final void update(IResult result) {
		Motor.getInstance().addResult(result);

		// Arrêt asynchrone du job d'éxecution de service
		for (Job job : Job.getJobManager().find(null)) {
			if (job.getName().equals(Motor.SERVICE_JOB) && job.getState() == Job.RUNNING) {
				job.done(Status.OK_STATUS);
			}
		}
	}
}
