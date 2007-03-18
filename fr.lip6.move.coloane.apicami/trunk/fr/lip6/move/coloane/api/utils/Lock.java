package fr.lip6.move.coloane.api.utils;

public class Lock {

	/**
	 * Le nom de service a transmettre au speaker
	 */
	private String serviceName;
	
	/**
	 * Constructeur
	 */
	public Lock() {
		super();
	}

	/**
	 * Methode bloquante si locked et vrai 
	 */
	public synchronized void attendre() {
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Methode bloquante si locked et vrai 
	 * @return String Le nom de service
	 */
	public synchronized String attendreServiceName() {
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return this.serviceName;
	}

	/**
	 * Debloque le speaker
	 */
	public synchronized void unlock() {
		notifyAll();
	}
	
	/**
	 * Debloque le speaker
	 * @param serviceName le nom de service a transmettre
	 */
	public synchronized void unlock(String serviceName) {
		this.serviceName = serviceName;
		notifyAll();
	}
}

