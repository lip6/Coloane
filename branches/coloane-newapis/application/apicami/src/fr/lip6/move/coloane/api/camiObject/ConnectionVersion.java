package fr.lip6.move.coloane.api.camiObject;

import fr.lip6.move.coloane.api.interfaces.IConnectionVersion;

/**
 * cette classe implemente linterface IFKVersion
 * retourné en resultat a la méthode openconnection.
 * @author kahoo & UU
 *
 */

public class ConnectionVersion implements IConnectionVersion {
    /** le nom du FrameKit*/
    private String FkName;

    /** le numero majeur de FrameKit*/
    private int FKMajor;

    /** le numero mineur de FrameKit*/
    private int FKMinor;

    /**
     * le constructeur de notre classe
     * @param FkName le nom du FrameKit.
     * @param FKMajor le numero majeur de FrameKit.
     * @param FKMinor le numero mineur de FrameKit.
     */
	public ConnectionVersion(String FkName,int FKMajor,int FKMinor){
     this.FkName = FkName;
     this.FKMajor=FKMajor;
     this.FKMinor=FKMinor;
	}

	 /**
     * le constructeur par defaut de notre classe.
      */
	public ConnectionVersion(){
		 this.FkName = null;
	     this.FKMajor=0;
	     this.FKMinor=0;
   }

	/**
	 * retourne le numero majeur de notre FrameKit.
	 */
	public int getFkMajor() {
		return this.FKMajor;
	}

	/**
	 * retourne le numero mineur de notre FrameKit.
	 */
	public int getFkMinor() {
		return this.FKMinor;
	}

	/**
	 * retourne le nom du FrameKit.
	 */
	public String getFkName() {
		return this.FkName;
	}

}
