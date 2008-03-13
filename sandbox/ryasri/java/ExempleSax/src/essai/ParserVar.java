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

// ----------------  Cette Classe Parse un fichier XML et en fonction du genre 
//	-----------------	affiche les Formules de politesses.
public class  ParserVar implements ContentHandler {

	private String chaine="";
	private String genre="";
	private String provisoire;
	
	public ParserVar() {
	} // Constructeur Chemin
	
	public void setDocumentLocator(Locator locator) {
	
	} // -- setDocumentLocator()
	
	public void startDocument() throws SAXException {
	    System.out.println("StartDocument \n") ;	    
	} // evenement Debut Document
	
	public void endDocument() throws SAXException {
	    System.out.println("EndDocument") ;
	} // evenement Fin Document

	
	public void startElement(String URI, String localName,String qName, Attributes atts)throws SAXException {
		if(qName.equals("personne")){
			for (int i=0;i<atts.getLength();i++){
				if(atts.getQName(i).equals("sexe"))
					genre=atts.getValue(i);
			}	
		}
		if (qName.equals("nom")){
			chaine="";	
		}
	} // Debut de l'element 

	 public void endElement(String namespaceURI, String localName,
              String qName) throws SAXException {
		//------------------------Debut---------Essai ---------------------------	 
		 if(qName.equals("nom")){
			if (genre.equals("m"))
					 System.out.println("Bonjour    Monsieur : "+chaine);
			if(genre.equals("f")) 
					 System.out.println("Bonjour    Madame   : "+chaine);
			provisoire=chaine;			 
		 }
		 if(qName.equals("personne")){
				if (genre.equals("m"))
						 System.out.println("Au revoir  Monsieur : "+provisoire);
				if(genre.equals("f")) 
						 System.out.println("Au revoir  Madame   : "+provisoire);				 
		 }
		 chaine="";
		 //------------------------Fin---------Essai ---------------------------
	 } // Fin de l'element
	 
	 public void characters(char[] ch, int start, int length)
                            throws SAXException {
		chaine+=new String(ch,start,length).trim();	//trim() est a mettre ici
		//chaine.trim();
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
	        ContentHandler listeContent = new ParserVar();
	        // ErrorHandler listeError = new ErrorChemin() ;
	        TraceErrorChemin listeError = new TraceErrorChemin() ;
	        
	        // Enregistre le gestionnaire de contenu
	        reader.setContentHandler(listeContent) ;
	        // Enregistre le gestionnaire d’erreurs
	        reader.setErrorHandler(listeError) ;
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
	        System.out.println("La classe de l’analyseur " + /*Parser*/""
	                           + " ne reconnaît pas l’URI d’option " +URI) ;
	        System.exit(0) ;
	        
	    } catch (SAXNotSupportedException e) {
	        System.out.println("La classe de l’analyseur " + /*Parser*/""
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
	        ParserVar listeur = new ParserVar();
	        listeur.listeEvenements(args[0]) ;
	       
	    } catch (Exception e) {
	        e.printStackTrace() ;
	        System.out.println("Problème ici: "+e.getMessage());
	      
	    } 
	} // -- Fin du Main
	
}

