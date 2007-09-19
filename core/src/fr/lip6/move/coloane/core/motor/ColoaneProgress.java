package fr.lip6.move.coloane.core.motor;

import fr.lip6.move.coloane.core.main.Coloane;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;

/**
 * Objet permettant la creation d'une barre de progression.<br>
 * Toutes les operations avec la plateforme doivent etre supervisee par un tel objet.<br>
 * Cet objet permet aussi de verouiller l'arrière plan.
 */

public abstract class ColoaneProgress implements IRunnableWithProgress {

	private Object operationResult;
	private IProgressMonitor progressMonitor;

	/**
	 * Constructeur
	 * @param res L'objet qui servira a stocker le resultat de l'operation
	 */
	public ColoaneProgress(Object res) {
		this.operationResult = res;
	}

	/**
	 * Retourne les resultats de l'operation
	 * @return Un objet a caster
	 */
	public final Object getResults() {
		return this.operationResult;
	}

	/**
	 * Positionne la valeur de retour qui sera appelee par le module apelant
	 * @param res La valeur de resultat
	 */
	protected final void setResults(Object res) {
		this.operationResult = res;
	}

	/**
	 * Attendre la fin de l'operation
	 */
	public final synchronized void waitUntilEnd() {
		try {
			Coloane.getLogger().finest("Mise en attente du moniteur");
			wait(20000);
		} catch (InterruptedException e) {
			System.err.println("Erreur Wait Monitor");
		}
	}

	/**
	 * Notifier le thread de la fin de l'operation
	 */
	public final synchronized void freeMonitor() {
		Coloane.getLogger().finest("Liberation du moniteur");
		progressMonitor.done();
		notify();
	}

	/**
	 * Indique le moniteur de progression utilise par l'objet
	 * @param monitor Le moniteur genere par la methode run
	 */
	protected final void setMonitor(IProgressMonitor monitor) {
		progressMonitor = monitor;
	}

	/**
	 * Retourne le moniteur de l'operation
	 * @return Le moniteur (barre de progression)
	 */
	public final IProgressMonitor getMonitor() {
		return progressMonitor;
	}

	/**
	 * Le methode run qui lance l'operation.<br>
	 * A implementer selon l'operation...
	 */
	public abstract void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException;
}
