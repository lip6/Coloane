package essai;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

public class Chemin2 implements ContentHandler {

	private String cc;
	//private String Parser;
	//------------------------Debut---------Essai ---------------------------
	//private List<String> stack;
	private List<String> stackElement;
	private List<String> stackAttributName;
	private List<String> stackAttributValue;
	private List<String> stackText;
	private StringBuffer affich=new StringBuffer();
	//------------------------Fin---------Essai ---------------------------

	public Chemin2() {
	// Parser="";
	
	} // Constructeur Chemin
	
	public void setDocumentLocator(Locator locator) {
	
	} // -- setDocumentLocator()
	
	public void startDocument() throws SAXException {
		stackElement  = new ArrayList<String>();					/*creation Pile ---rajout su type String*/
		stackAttributName = new ArrayList<String>();					/*creation Pile ---rajout su type String*/
		stackAttributValue = new ArrayList<String>();					/*creation Pile ---rajout su type String*/
		stackText     = new ArrayList<String>();					/*creation Pile ---rajout su type String*/
		//stack= new ArrayList<String>();	
	    System.out.println("StartDocument \n") ;
	    
	} // evenement Debut Document
	
	public void endDocument() throws SAXException {
	    System.out.println("EndDocument") ;
	} // evenement Fin Document

	
	public void startElement(String URI, String localName,String qName, Attributes atts)throws SAXException {
		
		//stack.add(qName);
		stackElement.add(qName);
		for (int i=0;i<atts.getLength();i++){
			//if (atts.getValue(i).equals("f") && (atts.getQName(i).equals("sexe"))){
			stackAttributName.add(atts.getQName(i));
			stackAttributValue.add(atts.getValue(i));
			//stack.add(atts.getQName(i));
				//stack.add(atts.getValue(i));
			}		
	} // Debut de l'element 

	 public void endElement(String namespaceURI, String localName,
              String qName) throws SAXException {
		//------------------------Debut---------Essai ---------------------------	 
		 
		 
		 if(qName.equals("nom")){
			 for(int i=0;i<stackAttributValue.size();i++){
				 if(stackAttributValue.get(i).equals("m"))
					 System.out.println("Au revoir  Monsieur  "+stackText.get(stackText.size()-2));
				 if(stackAttributValue.get(i).equals("f"))
					 System.out.println("Au revoir  Madame  "+stackText.get(stackText.size()-2));				 
			 }
		 }
		 stackElement.remove(stackElement.size()-1);
		 for(int i=0;i<stackAttributValue.size();i++){
			 stackAttributValue.remove(i);
			 stackAttributName.remove(i);
		 }
		 //------------------------Fin---------Essai ---------------------------
	 } // Fin de l'element
	 
	 public void characters(char[] ch, int start, int length)
                            throws SAXException {
		 
		 //System.out.println("--" + new String(ch,start,length) + "--") ;
		 //------------------------Debut---------Essai ---------------------------
		 stackText.add(new String(ch,start,length));
		 for(int i=0;i<stackElement.size();i++){
			 for(int j=0;j<stackAttributValue.size();j++){
				 if(stackElement.get(i).equals("nom")&& stackAttributValue.get(j).equals("m"))
					 System.out.println("Bonjour "+ "Monsieur "+ stackText.get(stackText.size()-1));

				 if(stackElement.get(i).equals("nom")&& stackAttributValue.get(j).equals("f"))
					 System.out.println("Bonjour "+ "Madame "+ stackText.get(stackText.size()-1));
			 }
		 }
		 	//stackText.add(new String(ch,start,length));
		//------------------------Fin---------Essai ---------------------------
		 //System.out.println(stack.get(stack.size()));
		 //affich.append(new String(ch,start,length));		 	
		/*
		 cc=("-" + new String(ch,start,length) + "-") ;
		 cc=cc.trim();
		 System.out.println(cc);
		 */		 
	 } // -- characters()
	 	 
		//------------------------Debut---------Essai ---------------------------

	 public String getText(){
		 return affich.toString();
	 } 
	 
		//------------------------Fin---------Essai ---------------------------

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
	        ContentHandler listeContent = new Chemin2() ;
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
	        Chemin2 listeur = new Chemin2();
	        listeur.listeEvenements(args[0]) ;
			//------------------------Debut---------Essai ---------------------------

	        String resultat=listeur.getText();
	        System.out.println(resultat);
	        
			//------------------------Fin---------Essai ---------------------------

	        //listeur.listeEvenements(xmlPersonnes);
	    } catch (Exception e) {
	        e.printStackTrace() ;

	        System.out.println("Problème ici: "+e.getMessage());
	        //System.out.println("J'arrive pas a trouver : "+e.getStackTrace());

	    } 
	} // -- Fin du Main
	
/*
 ------------------------------START  ELEMENT-------------------
		//------------------------Debut---------Essai ---------------------------
		Iterator<String> iter=stack.iterator();
		StringBuffer context=new StringBuffer(iter.next().toString());
		while(iter.hasNext()) {
			context.append("//");
			context.append(iter.next());
			System.out.println(context);
	    }
	    
		//------------------------Fin--------Essai ---------------------------

		System.out.println("D-E :[" + qName + "]") ;
		for (int i=0;i<atts.getLength();i++){
			System.out.println("Att : {"+atts.getValue(i)+"}");
			if (atts.getValue(i).equals("f") && (atts.getQName(i).equals("sexe"))){
				System.out.print("Bonjour Madame: "+atts.getValue(i+1)+"---"+ atts.getQName(i)+"---");
				if (qName.equals("name")){
					System.out.println(cc);
				}			
			}	
			if (atts.getValue(i).equals("oui") && (atts.getQName(i).equals("mobile"))){
				System.out.println("Joignable: "+"par---"+ qName.toUpperCase()+" au :"+cc);
			}
			if	(atts.getValue(i).equals("m"))
				System.out.println("Bonjour Monsieur: "+atts.getValue(i+1));
			}
	//Iterator<String> iter=stack.iterator();
	//StringBuffer context=new StringBuffer(iter.next().toString());
	//for(int i=0;i<stack.size();i++){				
			 //if(qName.equals("nom") && stack.get(stack.size()-5).equals("m"))
				// System.out.println("-----|||||"+stack.get(stack.size()-3)+"|||||-----");
				 
		
	//}
	
	//while(iter.hasNext()) {
		//context.append("//");
		//context.append(iter.next());
		//if (iter.next().equals(""))
			while(iter.next().equals("f")){
				stack.remove(iter.next());
			}
		//System.out.println("hey!!! Madame"+iter.next());
			//this.getText();
			
    //}
	if (stack.size()>0 && stack.get(stack.size()-1).equals("nom")){
			System.out.print(stack.get(1));
	}
	
 */
	
}

