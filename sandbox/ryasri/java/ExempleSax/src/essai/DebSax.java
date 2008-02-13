package essai;

import org.xml.sax.*;
import org.xml.sax.helpers.*;
import javax.xml.parsers.*; 
import java.io.*; 

public class DebSax {
	
	public static void main (String []arg){
		final String DONNEES_XML =
			  "<?xml version=\"1.0\"?>\n"
			  +"<BIBLIOTHEQUE\n>"
			  +" <LIVRE>\n"
			  +" <TITRE>titre livre 1</TITRE>\n"
			  +" <AUTEUR>auteur 1</AUTEUR>\n"
			  +" <EDITEUR>editeur 1</EDITEUR>\n"
			  +" </LIVRE>\n"
			  +" <LIVRE>\n"
			  +" <TITRE>titre livre 2</TITRE>\n"
			  +" <AUTEUR>auteur 2</AUTEUR>\n"
			  +" <EDITEUR>editeur 2</EDITEUR>\n"
			  +" </LIVRE>\n"
			  +"</BIBLIOTHEQUE>\n";
		
		// instance   debut copie
		
		
		try{
	         // création d'une fabrique de parseurs SAX
	         SAXParserFactory fabrique = SAXParserFactory.newInstance();
				
	         // création d'un parseur SAX
	         SAXParser parseur = fabrique.newSAXParser();
				
	         // lecture d'un fichier XML avec un DefaultHandler
	         //File fichier = new File("./exSax.xml");
	         DefaultHandler gestionnaire = new DefaultHandler(){
		
		// fin copie
		public void startDocument() throws SAXException{
			
			System.out.println("Je commence a lire le document");
		}
		
		public void endDocument() throws SAXException{
			
			System.out.println("J'ai fini de  lire le document");
		}	
		
		
		public void startElement(String uri,String localName, String qName, Attributes att)
						throws SAXException{
			
			System.out.println("J'en suis au"+ qName);
		}
		
		public void endElement(String uri,String localName, String qName)
		throws SAXException{

			System.out.println("J'ai fini de lire"+ qName);
		}
		
		public void characters(char[] ch, int deb, int longueur) throws SAXException{
			
			System.out.println("OK"+ new String(ch,deb,longueur));
		}
		//copie
	         };
	         //parseur.parse(DONNEES_XML, gestionnaire);
	         
	         parseur.parse(DONNEES_XML, gestionnaire);
			
	      }catch(ParserConfigurationException pce){
	         System.out.println("Erreur de configuration du parseur");
	         System.out.println("Lors de l'appel à SAXParser.newSAXParser()");
	      }catch(SAXException se){
	         System.out.println("Erreur de parsing");
	         System.out.println("Lors de l'appel à parse()"+se.getMessage());
	      }catch(IOException ioe){
	         System.out.println("Erreur d'entrée/sortie");
	         System.out.println("Lors de l'appel à parse()"+ioe.getMessage());
	      }
		//fin copie
	}

}
