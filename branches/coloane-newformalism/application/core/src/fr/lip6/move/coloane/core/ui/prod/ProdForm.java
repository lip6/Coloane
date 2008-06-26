package fr.lip6.move.coloane.core.ui.prod;

import java.io.File;
import java.io.IOException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class ProdForm extends DefaultHandler {

	/**
	 * Lecture des balises ouvrantes du modele
	 */
	@Override
	public final void startElement(String uri, String localName, String baliseName, Attributes attributes) throws SAXException {

		/*logger.finest("Balise lue : " + baliseName);*/

		// Remise a zero du champs data
		data = "";
	}

	// Donnees contenues dans les balises
	private String data = "";

	/**
	 * Gestion des donnees contenues dans les balises
	 */
	@Override
	public final void characters(char[] ch, int start, int length) throws SAXException {
		// Creation de la donnees (chaine de caracteres)
		for (int i = 0; i < length; i++) {
			data += ch[start + i];
		}
		data = this.deformat(data);
	}


	/**
	 * Lecture des balises fermantes du modele
	 */
	@Override
	public final void endElement(String namespaceURI, String localName, String qName) throws SAXException {
			if (qName.equals("nomFormalism")) {
				data.replace("\n","");
				data.replace("\r","");
				data.replace("\t","");
				File f;
				f=new File("./src/fr/lip6/move/coloane/core/ui/prod/"+data + ".java");
				try {
					f.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		// Remise a zero des donnees lues
		data = "";
	}

	/**
	 * Gestion des caracteres speciaux (deprotection)
	 * @param txt Le texte a deproteger
	 * @return Le texte transforme et protege
	 */
	private String deformat(String txt) {
		txt = txt.replaceAll("&lt;", "<");
		txt = txt.replaceAll("&gt;", ">");
		return txt;
	}

}
