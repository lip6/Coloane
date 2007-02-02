package fr.lip6.move.coloane.api.utils;

import java.util.Vector;

public class Lock {

	/**
	 * Le nom de service a transmettre au speaker
	 */
	private String serviceName;
	
	/**
	 * Le menu a transmettre au speacker
	 */
	private Vector menu;
	
	/**
	 * Le vecteur des mise a jours
	 */
	private Vector menuMAJ;
	
	/**
	 * Constructeur
	 *
	 */
	public Lock() {
		super();
	}

	/**
	 * Methode bloquante si locked et vrai 
	 *
	 */
	public synchronized void attendre() {
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 *Methode bloquante si locked et vrai 
	 *@return String le nom de service
	 *
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
	 * Methode bloquante
	 *@return Menu le menu 
	 *
	 */
	public synchronized Vector attendreMenu() {
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return this.menu;
	} 

	/**
	 * Methode bloquante
	 *@return Vector les mise a jour du menu 
	 *
	 */
	public synchronized Vector attendreMenuMAJ() {
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return this.menuMAJ;
	}


	
	/**
	 * debloque le speaker
	 *
	 */
	public synchronized void unlock() {
		notifyAll();
	}

	
	/**
	 * Debloque le speaker
	 *@param aServiceName le nom de service a transmettre
	 */
	public synchronized void unlock(String aServiceName) {
		this.serviceName = aServiceName;
		notifyAll();
	}
	
	/**
	 * Debloque le speaker
	 * @param aMenu le menu a transmettre
	 */
	public synchronized void unlockMenu(Vector aMenu) {
		this.menu = aMenu;
		notifyAll();
	}
	
	/**
	 * Debloque le speaker
	 * @param aMenu le menu mis a jours
	 */
	public synchronized void unlock(Vector aMenu) {
		notifyAll();
	}	
	
	
}

