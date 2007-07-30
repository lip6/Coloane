/**
 * 
 */
package fr.lip6.move.coloane.interfaces.concretemodel;

import fr.lip6.move.coloane.interfaces.model.Node;

/**
 * @author cdcharles
 * 
 */
public class ConcreteNode extends Node {

	private static final long serialVersionUID = 1L;

	/**
	 * @param nodeType
	 */
	public ConcreteNode(String nodeType) {
		super(nodeType);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param nodeType
	 * @param x
	 * @param y
	 */
	public ConcreteNode(String nodeType, int x, int y) {
		super(nodeType, x, y);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param nodeType
	 * @param x
	 * @param y
	 * @param id
	 */
	public ConcreteNode(String nodeType, int x, int y, int id) {
		super(nodeType, x, y, id);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.lip6.move.coloane.interfaces.model.Node#translate()
	 */
	@Override
	public String[] translate() {
		// TODO Auto-generated method stub
		return null;
	}

}
