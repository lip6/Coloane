package essai;
import org.xml.sax.*;
import org.xml.sax.helpers.ParserFactory;
//import com.ibm.xml.parsers.*;
import java.io.*;
public class MessageXML {
  static final String DONNEES_XML =
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
  +" <LIVRE>\n"
  +" <TITRE>titre livre 3</TITRE>\n"
  +" <AUTEUR>auteur 3</AUTEUR>\n"
  +" <EDITEUR>editeur 3</EDITEUR>\n"
  +" </LIVRE>\n"
  +"</BIBLIOTHEQUE>\n";

  static final String CLASSE_PARSER = "com.ibm.xml.parsers.SAXParser";

  /**
   * Lance l'application.
   * @param args un tableau d'arguments de ligne de commande
   */
  public static void main(java.lang.String[] args) {
  
    MessageXML m = new MessageXML();
    m.parse();

    System.exit(0);
  }

  public MessageXML() {
    super();
  }

  public void parse() {
    TestXMLHandler handler = new TestXMLHandler();
  
    System.out.println("Lancement du parseur");

    try {
      Parser parser = ParserFactory.makeParser(CLASSE_PARSER);

      parser.setDocumentHandler(handler);
      parser.setErrorHandler((ErrorHandler) handler);

      parser.parse(new InputSource(new StringReader(DONNEES_XML)));

    } catch (Exception e) {
      System.out.println("Exception capturée : ");
      e.printStackTrace(System.out);
      return;
    }
  }
}