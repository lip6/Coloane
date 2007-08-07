/**
 * 
 */
package fr.lip6.move.coloane.interfaces.concretemodel;

import fr.lip6.move.coloane.interfaces.model.Arc;

/**
 * @author cdcharles
 *
 */
public class ConcreteArc extends Arc {

	private static final long serialVersionUID = 1L;
	
	/**
	 * @param arcType
	 * @param id
	 */
	public ConcreteArc(String arcType, int id) {
		super(arcType, id);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arcType
	 */
	public ConcreteArc(String arcType) {
		super(arcType);
		// TODO Auto-generated constructor stub
	}
	
	public String[] translate(){
		return null;
	}
}
