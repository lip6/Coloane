package essai;
import java.util.*;

/**
 * Classe utilisee pour gérer les evenement emis par SAX lors du traitement du fichier XML
 */
public class TestXMLHandler extends org.xml.sax.HandlerBase {
  public TestXMLHandler() {
    super();
  }
  
  /**
   * Actions à réaliser sur les données
   */
  public void characters(char[] caracteres, int debut, int longueur) {
    String donnees = new String(caracteres, debut, longueur);
    System.out.println("   valeur = *" + donnees + "*");
  }
  
  /**
   * Actions à réaliser lors de la fin du document XML.
   */
  public void endDocument() {
    System.out.println("Fin du document");
  }
  
  /**
   * Actions à réaliser lors de la détection de la fin d'un element.
   */
  public void endElement(String name) {
    System.out.println("Fin tag " + name);
  }
  
  /**
   * Actions à réaliser au début du document.
   */
  public void startDocument() {
    System.out.println("Debut du document");
  }
  
  /**
   * Actions a réaliser lors de la detection d'un nouvel element.
   */
  public void startElement(String name, org.xml.sax.AttributeList atts) {
    System.out.println("debut tag : " + name);
  }
}