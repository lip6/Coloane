package fr.lip6.move.coloane.interfaces.model;


public interface IAttributeFormatter {
	
	/**
	 * Apply formatting rules
	 * @param value The current attribute value. Usually sufficient
	 * @param parentElement Attribute environment. From this element, almost all graph model objects can be accessed
	 * @return the string to be displayed as the attribute value
	 */
	String applyFormat(String value, IElement parentElement);

}
