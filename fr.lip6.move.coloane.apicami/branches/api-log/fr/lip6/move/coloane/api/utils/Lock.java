package fr.lip6.move.coloane.api.utils;

import fr.lip6.move.coloane.api.log.utils.LogsUtils;
import fr.lip6.move.coloane.api.main.Api;
public class Lock {

	/**
	 * Le nom de service a transmettre au speaker
	 */
	private String serviceName;
	
	private LogsUtils logsutils;
	/**
	 * Constructeur
	 */
	public Lock() {
		super();
		logsutils = new LogsUtils();
	}

	/**
	 * Methode bloquante si locked et vrai 
	 */
	public synchronized void attendre() {
		Api.apiLogger.entering("Lock", "attendre");
		try {
			wait();
		} catch (InterruptedException e) {
			Api.apiLogger.throwing("Lock", "attendre", e);
			Api.apiLogger.warning(e.getMessage() + logsutils.StackToString(e));
		}
		Api.apiLogger.exiting("Lock", "attendre");
	}

	/**
	 * Methode bloquante si locked et vrai 
	 * @return String Le nom de service
	 */
	public synchronized String attendreServiceName() {
		Api.apiLogger.entering("Lock", "attendreServiceName");
		try {
			wait();
		} catch (InterruptedException e) {
			Api.apiLogger.throwing("Lock", "attendreServiceName", e);
			Api.apiLogger.warning(e.getMessage() + logsutils.StackToString(e));
		}
		Api.apiLogger.exiting("Lock", "attendre", this.serviceName);
		return this.serviceName;
	}

	/**
	 * Debloque le speaker
	 */
	public synchronized void unlock() {
		Api.apiLogger.entering("Lock", "unlock");
		notifyAll();
		Api.apiLogger.exiting("Lock", "unlock");
	}
	
	/**
	 * Debloque le speaker
	 * @param serviceName le nom de service a transmettre
	 */
	public synchronized void unlock(String serviceName) {
		Api.apiLogger.entering("Lock", "unlock", serviceName);
		this.serviceName = serviceName;
		notifyAll();
		Api.apiLogger.exiting("Lock", "unlock");
	}
}

