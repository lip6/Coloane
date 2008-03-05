package essai;

import java.io.IOException;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
//import org.xml.sax.ErrorHandler;
//import org.xml.sax.helpers.DefaultHandler;
//import org.xml.sax.helpers.*;
//import sax2r3.src.org.xml.sax.*;
//import sax2r3.classes.org.xml.sax.*;
//import org.apache.xml.*;
//import org.apache.xerces.parsers.*;
//import org.apache.xerces.*;
//import javax.xml.parsers.*;
//import javax.xml.transform.*;
//import javax.xml.transform.sax.*;
//import javax.xml.validation.*;
//import javax.xml.parsers.SAXParserFactory;
//import javax.xml.parsers.SAXParser;
//import org.apache.*;
//import sax.*;
//import essai.TraceErrorChemin;
import javax.xml.*;

public class CheminHandler implements ContentHandler {

	public final static String xmlPersonnes =
		  "<?xml version=\"1.0\"?>\n"
		+"<personnes>\n"+
			"<personne sexe='m'>\n"+
				"Je Suis\n"+
			"</personne>\n"+
			"<persoonne sexe='m'>\n"+
				"Tu Es\n"+
			"</persoonne>\n"+
			"<personne sexe='m'>\n"+
				"Il Est\n"+
			"</personne>\n"+
			"<personne sexe='f'>\n"+
				"Elle Est\n"+
			"</personne>\n"+
			"<personne sexe='m'>\n"+
				"Nous Sommes\n"+
			"</personne>\n"+
			"<personne sexe='f'>\n"+
				"Vous Etes\n"+
			"</personne>\n"+
			"<personne sexe='m'>\n"+
				"Ils Sont\n"+
			"</personne>\n"+
			"<personne sexe='f'>\n"+
				"Elle Sont\n"+
			"</personne>\n"+
		"</personnes>  \n";
		
	
	private String Parser;
	public CheminHandler() {
	 Parser="";
	} // Constructeur Chemin
	
	public void setDocumentLocator(Locator locator) {
	
	} // -- setDocumentLocator()
	
	public void startDocument() throws SAXException {
	    System.out.println("StartDocument \n") ;
	} // evenement Debut Document
	
	public void endDocument() throws SAXException {
	    System.out.println("EndDocument") ;
	} // evenement Fin Document

	
	public void startElement(String URI, String localName,
			String qName, Attributes atts)throws SAXException {
			System.out.println("Deb élement :[" + qName + "]") ;
	} // Debut de l'element 

	 public void endElement(String namespaceURI, String localName,
              String qName) throws SAXException {
		  	System.out.println("Fin élement :[" + qName + "]") ;
	 } // Fin de l'element
	 
	 public void characters(char[] ch, int start, int length)
                            throws SAXException {
		  System.out.println("text :[" + new String(ch, start, length)
              + "]") ;
	 } // -- characters()

	public void endPrefixMapping(String arg0) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	public void ignorableWhitespace(char[] arg0, int arg1, int arg2)
			throws SAXException {
		// TODO Auto-generated method stub
		
	}

	public void processingInstruction(String arg0, String arg1)
			throws SAXException {
		// TODO Auto-generated method stub
		
	}

	public void skippedEntity(String arg0) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	public void startPrefixMapping(String arg0, String arg1)
			throws SAXException {
		// TODO Auto-generated method stub
		
	}

	public void listeEvenements(String xmlURI) throws IOException, SAXException
	{
	    String URI = "" ;
	    //String Parser = null;
		try {
	        // Création des instances pour faire l'analyse
	        //XMLReader reader = XMLReaderFactory.createXMLReader(Parser);
			XMLReader reader = XMLReaderFactory.createXMLReader();
	        ContentHandler listeContent = new CheminHandler() ;
	       // ErrorHandler listeError = new ErrorChemin() ;
	      ////// TraceErrorChemin listeError = new TraceErrorChemin() ;
	        
	        // Enregistre le gestionnaire de contenu
	        reader.setContentHandler(listeContent) ;
	        // Enregistre le gestionnaire d’erreurs
	     //////   reader.setErrorHandler(listeError) ;
	       /*
	        *  // Demande la validation du document
	        URI = "http://xml.org/sax/features/validation" ;
	        reader.setFeature(URI, false) ;
	        // Active le traitement du schéma
	        URI = "http://apache.org/xml/features/validation/schema" ;
	        reader.setFeature(URI, false) ;
	        // Active le traitement des domaines nominaux
	        URI = "http://xml.org/sax/features/namespaces" ;
	        reader.setFeature(URI, false) ;
	        URI = "http://xml.org/sax/features/namespace-prefixes" ;
	        reader.setFeature(URI, false) ;
	        // Analyse
	         
	         */
	        InputSource inputSource = new InputSource(xmlURI) ;
	        reader.parse(inputSource) ;
	        
	    } catch (SAXNotRecognizedException e) {
	        System.out.println("La classe de l’analyseur " + Parser
	                           + " ne reconnaît pas l’URI d’option " +URI) ;
	        System.exit(0) ;
	        
	    } catch (SAXNotSupportedException e) {
	        System.out.println("La classe de l’analyseur " + Parser
	                           + " ne prend pas en compte l’URI d’option " + URI) ;
	        System.exit(0) ;
	        
	    } 
	} 

	public static void main (String[] args) {
	    try {
	    	if (args.length != 1) {
	            System.out.println("Noooooooooo :Il Manque le Fichier XML a parser " +
	            					"\"Mais Reveilles Toi\"") ;
	            System.exit(0) ; //on sort
	        } 
	        CheminHandler listeur = new CheminHandler();
	        listeur.listeEvenements(args[0]) ;
	        //listeur.listeEvenements(xmlPersonnes);
	    } catch (Exception e) {
	        e.printStackTrace() ;

	        System.out.println("Impossible de: "+e.getMessage());
	        System.out.println("J'arrive pas a trouver : "+e.getStackTrace());

	    } 
	} // -- Fin du Main

	
}

