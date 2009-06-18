/*******************************************************************************
 * Copyright (c) 2007 EclipseGraphviz contributors.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     abstratt technologies
 *******************************************************************************/
package fr.lip6.move.graphviz;

import java.io.PrintWriter;

public class DOTRenderingUtils {
	public static void addAttribute(PrintWriter pw, String attribute, int value) {
		pw.println(attribute + " = " + value);
	}

	public static void addAttribute(PrintWriter pw, String attribute, String value) {
		pw.println(attribute + " = \"" + value + "\"");
	}

	public static void newLine(PrintWriter pw) {
		pw.print("\\n");
	}
}
