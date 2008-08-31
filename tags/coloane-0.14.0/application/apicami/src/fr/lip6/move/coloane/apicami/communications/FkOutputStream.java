package fr.lip6.move.coloane.apicami.communications;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Logger;

/**
 * Flux dédié à la communication avec la plate-forme<br>
 * Ce flux permet de traiter les besoins de la plate-forme en termes de format de message.
 * Toute  écriture <b>DOIT</b> passer par cette classe
 *
 * @author Alexandre Hamez
 * @author Jean-Baptiste Voron
 */
public final class FkOutputStream extends FilterOutputStream {
	/** Le logger */
	private static Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.apicami");

	/**
	 * Constructeur
	 * @param out Le stream associée à la socket
	 */
	public FkOutputStream(OutputStream out) {
		super(out);
	}

	/**
	 * Ecriture sur le flux de sortie
	 * @param data Les donées à écrire
	 * @throws IOException En cas de problème lors de l'écriture
	 */
	public void write(String data) throws IOException {
		LOGGER.finest("[CO->FK] " + data);
		byte[] dataByte = data.getBytes();

		byte[] toSend = new byte[dataByte.length + 4];
		toSend[0] = 0;
		toSend[1] = 0;
		toSend[2] = 0;
		toSend[3] = (byte) dataByte.length;

		for (int i = 0; i < dataByte.length; ++i) {
			toSend[i + 4] = dataByte[i];
		}

		super.write(toSend);
	}

}
