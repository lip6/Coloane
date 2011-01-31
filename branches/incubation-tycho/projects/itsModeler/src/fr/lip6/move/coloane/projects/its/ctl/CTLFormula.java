/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Jean-Baptiste VORON (LIP6) - Project Head / Initial contributor
 *   Clément DÉMOULINS (LIP6) - Project Manager
 *   Yann THIERRY-MIEG (LIP6)
 *
 * Official contacts:
 *   coloane@lip6.fr
 *   http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.projects.its.ctl;

public interface  CTLFormula {

	public static String CONSTANT = "Constant";
	public static String PREDICATE = "Predicate";
	public static String REFERENCE = "Reference";
	public static String AF = "AF";
	public static String AG = "AG";
	public static String AU = "AU";
	public static String AX = "AX";
	public static String EF = "EF";
	public static String EG = "EG";
	public static String EU = "EU";
	public static String EX = "EX";
	public static String NOT = "!";
	public static String AND = "*";
	public static String OR = "+";
	public static String XOR = "^";
	public static String EQUIV = "<->";
	public static String IMPLY = "->";
	
	
	String getOperator();
	
	// Singleton constant instances
	public static CTLFormula TRUE = new CTLConstant(true);
	public static CTLFormula FALSE = new CTLConstant(true);
}
