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

public class Play implements ContentHandler{

	public void characters(char[] arg0, int arg1, int arg2) throws SAXException {
		// TODO Auto-generated method stub
		System.out.println("chaine :{ "+ new String(arg0,arg1,arg2) + "}");
	}

	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		System.out.println("J'ai fini de lire le DOC");
	}

	public void endElement(String namespaceURI, String localName,String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		System.out.println("fin de "+qName +"");
	}

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

	public void setDocumentLocator(Locator arg0) {
		// TODO Auto-generated method stub
	}

	public void skippedEntity(String arg0) throws SAXException {
		// TODO Auto-generated method stub
		
	}

	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		System.out.println("Debut DOC");
	}

	public void startElement(String namespaceURI, String localName,String qName,
			Attributes attr) throws SAXException {
		// TODO Auto-generated method stub
		System.out.println("deb Elem"+ qName +"" );
	}

	public void startPrefixMapping(String arg0, String arg1)
			throws SAXException {
		// TODO Auto-generated method stub
		
	}

	
	//----------------------------------
	public void debut (String uri) throws SAXException, IOException{
		
		//String URI = "" ;
	    //String Parser = "";
	        // Création des instances pour faire l'analyse
	        //XMLReader reader = XMLReaderFactory.createXMLReader(Parser);
        	XMLReader reader = XMLReaderFactory.createXMLReader();

	    
	        ContentHandler listeContent = new Play() ;
	 
	        reader.setContentHandler(listeContent) ;
	 
	        InputSource inputSource = new InputSource(uri) ;
	        reader.parse(inputSource) ;
	}
	
	public static void main (String[] args) {
	    try {
	        if (args.length != 1) {
	            System.out.println("Noooooooooo :Il Manque le Fichier XML a parser " +
	            					"\"Mais Reveilles Toi\"") ;
	            System.exit(0) ; //on sort
	        } 
	        Play listeur = new Play();
	        listeur.debut(args[0]) ;
	    } catch (Exception e) {
	        e.printStackTrace() ;
	        System.out.println("Impossible de: "+e.getMessage());
	        System.out.println("J'arrive pas a trouver : "+e.getStackTrace());
	    } 
	} // -- Fin du Main
}
