package fr.lip6.move.coloane.core.ui.prod;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public final class CreateurFichierMain {

	public static void main(String args[]){

		String xmlFile= "./src/fr/lip6/move/coloane/core/ui/prod/producteurFormalism.xml";
		
		producteurHandler newF = new producteurHandler();
		
		SAXParserFactory factory;

		factory = SAXParserFactory.newInstance();
		try {
			// Phase de Creatin de la Classe correspondante au formalism voulu

			SAXParser saxParser = factory.newSAXParser();
			saxParser.parse(xmlFile, newF);

		} catch (Exception e) {
				e.printStackTrace();
		}
	}
}
