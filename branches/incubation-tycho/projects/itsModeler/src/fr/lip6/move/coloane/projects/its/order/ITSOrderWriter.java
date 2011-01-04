package fr.lip6.move.coloane.projects.its.order;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.logging.Logger;

import fr.lip6.move.coloane.interfaces.exceptions.ExtensionException;
import fr.lip6.move.coloane.projects.its.TypeDeclaration;

public class ITSOrderWriter {

	public void writeOrder(String orderFileName, TypeDeclaration td, Ordering order) throws ExtensionException {
		try {
			// test folder existence, create if it does not exist
			
			// File creation
			FileOutputStream writer = new FileOutputStream(new File(orderFileName)); //$NON-NLS-1$
			BufferedWriter sb = new BufferedWriter(new OutputStreamWriter(writer));

			Ordering order2 = order.iterator().next();
			// Start of file
			sb.append("#TYPE "+td.getTypeName()+"\n");
			for (Ordering o : order2) {
				sb.append(o.getName()+"\n");
			}
			sb.append("#END\n");
			
			
			// End of writing : clean & close
			sb.flush();
			writer.flush();
			sb.close();
			writer.close();
		} catch (FileNotFoundException fe) {
			Logger.getLogger("fr.lip6.move.coloane.core").warning("Error when creating file : bad file name."+fe);
			throw new ExtensionException("Invalid filename !" +fe);
		} catch (IOException ioe) {
			Logger.getLogger("fr.lip6.move.coloane.core").warning("Error writing in file " + ioe);
			throw new ExtensionException("Write error :" + ioe.getMessage());
		}
		
	}

}
