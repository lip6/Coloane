package fr.lip6.move.coloane.api.camiObject;

import fr.lip6.move.coloane.api.interfaces.IUpdateItem;

/**
 * cette classe implemente l'interface IUpdateItem ,c'est un TQ
 * @author kahoo & uu
 *
 */
public class UpdateItem implements IUpdateItem{

	/** le nom du root name*/
    private String rootName;

    /** le nom du root name*/
    private String serviceName;

    /**l'état de l'item */
    private boolean state;

    /**
     * le constructeur de notre classe
     * @param rootName
     * @param serviceName
     * @param state
     */
    public UpdateItem(String rootName,String serviceName,boolean state){
    	this.rootName = rootName;
    	this.serviceName = serviceName;
    	this.state = state;
    }

	/**
	 * Donne le nom du root name.
	 * @return string.
	 */
	public String getRootName(){
		return this.rootName;
	}

	/**
	 * Donne le nom du service.
	 * @return string.
	 */
	public String getServiceName(){
		return this.serviceName;
	}

	/**
	 * Donne l'état de l'item (à activer ou pas).
	 * @return bool.
	 */
	public boolean getState(){
		return this.state;
	}
}
