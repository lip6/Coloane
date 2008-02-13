
package essai;

//import du package Dom.
import javax.xml.parsers.*; 

import org.w3c.dom.*; 
import org.xml.sax.*; 

import com.sun.org.apache.xerces.internal.impl.xs.dom.DOMParser;

import java.io.*; 


public class JeDom {
	
	
	public static void afficheNoeud(Node noeud){
		System.out.println(noeud);
		NodeList noeuds = noeud.getChildNodes();
		for (int i=0;i< noeuds.getLength();i++){
			Node no= noeuds.item(i);
			afficheNoeud(no);	
		}
		
	}

	public static void afficheDocument(Document document){
		Element racine = document.getDocumentElement();
		afficheNoeud(racine);
	}
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException{
	
		
		try{
		//creation du document
		
		DocumentBuilderFactory fabrique = DocumentBuilderFactory.newInstance();
		
		//creation du contenu 
		
		DocumentBuilder constructeur = fabrique.newDocumentBuilder();
		
		//le fichier a traiter
		
		//File ficxml = new File("ex1.xml");
		
		//String ficxml = args[0];
		//Document doc = constructeur.parse(args[0]);
		
		// traitement approprié => ici affichage
		
		
		
		DOMParser parser = new DOMParser();

		  parser.parse("ex1.xml");

		  // The document is the root of the DOM tree.

		  Document doc = parser.getDocument();

		  // Get the Document Element (Top Node)

		  Element root = (Element)doc.getDocumentElement();
		afficheDocument((Document) root);
		
		}catch(ParserConfigurationException pce){
			System.out.println("Erreur de configuration du parseur DOM");
			System.out.println("lors de l'appel à fabrique.newDocumentBuilder();");
		}catch(SAXException se){
			System.out.println("Erreur lors du parsing du document");
			System.out.println("lors de l'appel à constructeur.parse(xml)");
		}catch(IOException ioe){
			System.out.println("Erreur d'entrée/sortie");
			System.out.println("ce n'est pas bon ici constructeur.parse(xml)");
		}
		
	}

	
	
	
	
	






	
	
	
	
	
	
	
}
