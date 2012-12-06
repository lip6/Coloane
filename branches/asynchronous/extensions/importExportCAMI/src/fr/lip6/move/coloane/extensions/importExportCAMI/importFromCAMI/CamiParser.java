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
package fr.lip6.move.coloane.extensions.importExportCAMI.importFromCAMI;

import fr.lip6.move.coloane.interfaces.exceptions.SyntaxErrorException;

import java.util.StringTokenizer;

/**
 * Tools used for handle CAMI commands such as:<br>
 * <ul>
 * <li> Integer parser</li>
 * <li> String parser</li>
 * <li> CAMI string producer</li>
 * </ul>
 */

public class CamiParser {

	private String command;

	/**
	 * Constructor
	 * @param cami The command that is going to be parsed
	 */
	public CamiParser(String cami) {
		this.command = cami.substring(3);
	}

	/**
	 * Parse the command and return the inside integer.<br>
	 * If no integer is fount, an empty String is returned.
	 * @param token The separator
	 * @return The integer (or an empty string is no integer)
	 */
	public final String parseInt(String token) {
		String res = ""; //$NON-NLS-1$
		int cpt = 0;

		if (!(this.command.subSequence(cpt, cpt + 1)).equals(token)) {
			while (!(this.command.subSequence(cpt, cpt + 1)).equals(token)) {
				res += this.command.subSequence(cpt, cpt + 1);
				cpt++;
			}
			cpt++;
			this.command = this.command.substring(cpt);
			return res;
		} else {
			this.command = this.command.substring(1);
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * Parse the string contained into the CAMI command
	 * @param token The separator
	 * @return The string contained into the CAMI command
	 * @throws SyntaxErrorException The length of the string is incorrect
	 */
	public final String parseString(String token) throws SyntaxErrorException {
		try {
			StringTokenizer st = new StringTokenizer(this.command);

			if (!(this.command.subSequence(0, 1)).equals(token)) {

				// Fetch the length contained into the CAMI command
				String tailleChaine = st.nextToken(":"); //$NON-NLS-1$
				int taille = Integer.parseInt(tailleChaine);

				// Fetch the remaining string
				String res = this.command.substring(tailleChaine.length() + 1, tailleChaine.length() + 1 + taille);
				this.command = this.command.substring(tailleChaine.length() + 1 + taille + 1); // ??

				return res;
			} else {
				this.command = this.command.substring(1);
				return ""; //$NON-NLS-1$
			}
		} catch (NumberFormatException e) {
			throw new SyntaxErrorException("Erreur de parse : " + token); //$NON-NLS-1$
		}
	}

	/**
	 * Convert a string to a CAMI string.<br>
	 * A CAMI string is always print after its length
	 * @param string The string to convert
	 * @return The CAMI formatted string
	 */
	public static String stringToCAMI(String string) {
		if (!string.equals("") || string == null) { //$NON-NLS-1$
			String cami = "" + string.length() + ":" + string; //$NON-NLS-1$ //$NON-NLS-2$
			return cami;
		} else {
			return ""; //$NON-NLS-1$
		}
	}
}
