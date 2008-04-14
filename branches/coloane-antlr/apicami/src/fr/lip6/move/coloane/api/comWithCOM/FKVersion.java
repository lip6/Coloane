package fr.lip6.move.coloane.api.comWithCOM;

import fr.lip6.move.coloane.api.interfaces.IFKVersion;

public class FKVersion implements IFKVersion {
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
	public FKVersion(String FkName,int FKMajor,int FKMinor ){
     this.FkName = FkName;
     this.FKMajor=FKMajor;
     this.FKMinor=FKMinor;
	}

	 /**
     * le constructeur par defaut de notre classe.
      */
	public FKVersion(){
		 this.FkName = null;
	     this.FKMajor=0;
	     this.FKMinor=0;
   }

	/**
	 * retourne le numero majeur de notre FrameKit.
	 */
	public int getFKMajor() {
		return this.FKMajor;
	}

	/**
	 * retourne le numero mineur de notre FrameKit.
	 */
	public int getFKMinor() {
		return this.FKMinor;
	}

	/**
	 * retourne le nom du FrameKit.
	 */
	public String getFkName() {
		return this.FkName;
	}

}
