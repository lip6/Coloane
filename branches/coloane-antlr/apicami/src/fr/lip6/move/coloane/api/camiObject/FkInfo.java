package fr.lip6.move.coloane.api.camiObject;

import fr.lip6.move.coloane.api.interfaces.IFKInfo;

/**
 * cette classe implemente linterface IFKInfo
 * retourné en resultat a la méthode opensession.
 * @author kahoo & UU
 *
 */

public class FkInfo implements IFKInfo{

	 /** informations relatives au service*/
	private String aboutService;

	 /** l'incremental*/
	private String incremental;

	 /** le nom du service*/
	private String nameService;

	 /** le resultat calculé*/
	private String resultatCalcule;

 /**
  * le constructeur de notre classe
  * @param aboutService
  * @param incremental
  * @param nameService
  * @param resultatCalcule
  */
	public FkInfo (String aboutService,String incremental,String nameService,String resultatCalcule ) {
		this.aboutService = aboutService;
			this.incremental=incremental;
			this.nameService=nameService;
			this.resultatCalcule=resultatCalcule;

	}

	/**
	 * constructeur par defaut.
	 */
	public FkInfo () {
		this.aboutService = null;
			this.incremental=null;
			this.nameService=null;
			this.resultatCalcule=null;

	}
	public String getAboutService() {
     return this.aboutService;
	}

	public String getIncremental (){
		return this.incremental;
	}

	public String getNameService() {
		return this.nameService;
	}

	public String getResultatCalcule() {
		return this.resultatCalcule;
	}


}
