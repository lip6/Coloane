package fr.lip6.move.coloane.api.log;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Cette classe est responsable du log dans
 * au niveau de API
 * (écriture dans le fichier api-coloane.log)
 *
 * @author 2760587
 *
 */


public class ApiHandler extends Handler {

	/**
	 * Constructeur
	 */
	/*  */
	FileOutputStream fos;

	public ApiHandler() throws FileNotFoundException, IOException{
		/* création d'un fichier /tmp/api-coloane.log */
		this.fos = new FileOutputStream( File.createTempFile("api",null));

		fos.write("Logger API COLOANE\n".getBytes());
		fos.write("------------------\n".getBytes());
	}


	@Override
	public void close() throws SecurityException {
		// TODO Auto-generated method stub

	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub

	}

	@Override
	public void publish(LogRecord record) {
		try {
			this.fos.write(("[" + record.getLevel() + "] " + record.getMessage() + "\n").getBytes());
		} catch (IOException e) {
			System.err.println("Impossible de logguer");
		}

	}

}
