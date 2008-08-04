package fr.lip6.move.coloane.api.communications;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Flux dédié à la communication avec la plate-forme (CO->FK)<br>
 * Ce flux permet de traiter les messages en provenance de la plate-forme.
 * Toute lecture passe par cette classe
 *
 * @author Alexandre Hamez
 * @author Jean-Baptiste Voron
 */
public final class FkInputStream extends FilterInputStream {
	/** Un compteur pour les commandes longues */
	private int toBeRead = 0;

	/**
	 * Constructeur
	 * @param input Le flux d'entrée
	 */
	public FkInputStream(InputStream input) {
		super(input);
	}

	/**
	 * {@inheritDoc}
	 */
	public int available() throws IOException {
		return super.available();
	}

	/**
	 * {@inheritDoc}
	 */
	public int read() throws IOException {
		byte[] b = new byte[1];
		if (this.read(b, 0, 1) == -1) {
			return -1;
		}
		return new Byte(b[0]).intValue();
	}

	/**
	 * {@inheritDoc}
	 */
	public int read(byte[] b) throws IOException {
		return this.read(b, 0, b.length);
	}

	/**
	 * Conversion d'un tableau d'octets en entier
	 * @param bytes Le tableau d'octets qu'on souhaite convertir en entier
	 * @return L'entier
	 */
	protected int bytesToInt(byte[] bytes) {
		int value = 0;
		for (int i = 0; i < bytes.length; i++) {
			value = value << 8;
			value += bytes[i] & 0xff;
		}
		return value;
	}

	/**
	 * {@inheritDoc}
	 */
	public int read(byte[] b, int offset, int length) throws IOException {
		int commandLength = this.toBeRead;
		int lenghtToRead = length;

		// Si on commence une nouvelle commande...
		if (this.toBeRead == 0) {
			// Récupération des 4 premiers octets
			byte[] size = new byte[4];
			if (super.read(size, 0, 4) == -1) {
				return -1;
			}
			commandLength = this.bytesToInt(size);
		}

		// Si la commande ne doit pas prendre toute la place... On restraint la lecture
		if (commandLength < length) {
			lenghtToRead = commandLength;
		}

		// Lecture de la commande
		int nbRead = super.read(b, 0, lenghtToRead);
		if (nbRead == -1) { return -1; }

		// On regarde s'il y a du reste à lire...
		this.toBeRead = commandLength - nbRead;
		return nbRead;
	}

	/**
	 * Retrouve la dernière commande
	 * @param buffer Le buffer de commandes CAMI en cours de construction
	 * @return La dernière commande lue
	 */
	private String getLastCommand(String buffer) {
		int beginCommand = buffer.lastIndexOf('\n', buffer.length() - 2);
		String lastToken = buffer.substring(beginCommand + 1, beginCommand + 3);
		return lastToken;
	}

	/**
	 * Détermine si la lecture de la socket est terminée ou pas
	 * @param buffer Le buffer en cours de lecture
	 * @return <code>true</code> si la dernière commande lue finie la transaction
	 */
	private boolean hasRichedLastCommand(String buffer) {
		String lastToken = this.getLastCommand(buffer);
		if (lastToken.equals("OC")) {
			return true;
		} else if (lastToken.equals("QQ")) {
			return true;
		}
		return false;
	}

	/**
	 * Retourne la série de commande reçue de la palte-forme
	 * @return Une chaine de caractères contenant toutes les commmandes de la plate-forme
	 */
	public String getCommands() {
		byte[] fromInput = new byte[255];
		StringBuilder toReturn = new StringBuilder();
		try {
			do {
				this.read(fromInput);
				toReturn.append(new String(fromInput).trim());

				// On doit verifier que la commande est complete
				fromInput = new byte[255];
				if (toReturn.toString().endsWith(")")) { toReturn.append('\n'); } else { continue; }

			} while (!this.hasRichedLastCommand(toReturn.toString()));
			return toReturn.toString().replaceAll("\n", "").trim();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}
