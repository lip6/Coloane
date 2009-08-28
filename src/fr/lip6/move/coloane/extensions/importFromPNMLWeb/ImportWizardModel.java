package fr.lip6.move.coloane.extensions.importFromPNMLWeb;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;


import org.apache.commons.httpclient.HttpException;
import org.eclipse.swt.widgets.Text;

import fr.lip6.move.pnmlweb.Caller;
import fr.lip6.move.pnmlweb.exceptions.CallerException;
import fr.lip6.move.pnmlweb.exceptions.ModelDescriptorException;
import fr.lip6.move.pnmlweb.exceptions.ParserException;
import fr.lip6.move.pnmlweb.exceptions.PnmlWEBException;
import fr.lip6.move.pnmlweb.interfaces.IFormat;
import fr.lip6.move.pnmlweb.interfaces.IModelDescriptor;

public class ImportWizardModel {

	private static Text myText;
	
	public static void main(String[] args) throws HttpException, PnmlWEBException, ModelDescriptorException, IOException, ParserException, CallerException {
		// TODO Auto-generated method stub

		
        // Creation d'un objet Caller pour interagir avec PnmlWEB.
        Caller c = new Caller("http://pnmlweb.lip6.fr","admin","admin1234");
        List<IModelDescriptor> ms = c.searchModeldescriptors(myText.getText());
        
        // Parcours la liste des modeldescriptors
        System.out.println( ms.size() + "models descriptors found"); // Label
        
        
        for (IModelDescriptor m : ms) {
        	//System.out.println(m);
            // Recupere le format PNML de la version courante
            IFormat f = m.getCurrentVersion().getPNML();
            // Telecharge le PNML dans le chemin specifie
            //System.out.println("*** Telechargement de " + f.getFilename() + " à partir de l'URI...");
            //c.downloadFormat(f, "/home/yaziz/Templates/" + f.getFilename());
            //System.out.println("URI de " + f.getFilename() + ": " + f.getUri());
             
            // reading directly from a url
            URL url = new URL(f.getUri());
            URLConnection uc = url.openConnection();	// open URL (HTTP query)

    		InputStreamReader input = new InputStreamReader(uc.getInputStream());	// open data stream
    		BufferedReader in = new BufferedReader(input);
    		String inputLine;

    		// reading line by line
    		int nbLines=0;
    		while ((inputLine = in.readLine()) != null) {
    			nbLines++;
    			//System.out.println(inputLine);
    		}
    		System.out.println(nbLines);
    		in.close();
        }
	}
}
