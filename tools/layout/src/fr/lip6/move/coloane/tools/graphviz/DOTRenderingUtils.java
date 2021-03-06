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
 *
 * Official contacts:
 *   coloane@lip6.fr
 *   http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.tools.graphviz;

import java.io.PrintWriter;

/**
 * A helper class to create dot content.
 */
public final class DOTRenderingUtils {
	
	/**
	 * hide constructor.
	 */
	private DOTRenderingUtils() { }
	
	/** 
	 * Add an attribute=value(int) pair to a node/arc.
	 * @param pw output stream
	 * @param attribute attribute
	 * @param value value
	 */
	public static void addAttribute(PrintWriter pw, String attribute, int value) {
		pw.println(attribute + " = " + value);
	}

	/** 
	 * Add an attribute=value(string) pair to a node/arc.
	 * @param pw output stream
	 * @param attribute attribute
	 * @param value value
	 */
	public static void addAttribute(PrintWriter pw, String attribute, String value) {
		pw.println(attribute + " = \"" + value + "\"");
	}

	/**
	 * push a newline onto the stream
	 * @param pw stream to add to.
	 */
	public static void newLine(PrintWriter pw) {
		pw.print("\\n");
	}
}
