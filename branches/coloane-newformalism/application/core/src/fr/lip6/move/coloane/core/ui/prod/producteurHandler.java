package fr.lip6.move.coloane.core.ui.prod;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import fr.lip6.move.coloane.core.motor.formalism.ArcFormalism;
import fr.lip6.move.coloane.core.motor.formalism.defs.Messages;
import fr.lip6.move.coloane.core.ui.model.IArcGraphicInfo;

public class producteurHandler extends DefaultHandler {

	private String pathFormalism="./src/fr/lip6/move/coloane/core/ui/prod/";
	private String nomFormalism="";
	private static String nomElement="";
	private static String messageElement="";
	private static String choixFigure=""; 

	
	private static final String lesPackages="package fr.lip6.move.coloane.core.ui.prod;\n"+
			"import fr.lip6.move.coloane.core.motor.formalism.ArcFormalism;\n" +
			"import fr.lip6.move.coloane.core.motor.formalism.AttributeFormalism;\n" +
			"import fr.lip6.move.coloane.core.motor.formalism.ElementFormalism;\n" +
			"import fr.lip6.move.coloane.core.motor.formalism.Formalism;\n" +
			"import fr.lip6.move.coloane.core.motor.formalism.NodeFormalism;\n" +
			"import fr.lip6.move.coloane.core.ui.model.IArcGraphicInfo;\n" +
			"import fr.lip6.move.coloane.core.ui.model.IAttributeGraphicInfo;\n" +
			"import fr.lip6.move.coloane.core.ui.model.INodeGraphicInfo;\n";
	private static final String nomClasse="public class ";
	private static final String extensionClasse=" extends Formalism { \n\n";
	private static final String variableNom="private static final String NAME = ";
	private static final String variableImg="\nprivate static final String IMG = \"/resources/icons/instance.gif\";\n";
	private static final String variableExtension="private static final String EXTENSION =";
	private static final String variableXSCHEMA = "\nprivate static final String XSCHEMA =\"global.xsd\";\n";
	private static final String figureLargeur="private static final int FIGURE_WIDTH = ";
	private static final String figureHauteur="private static final int FIGURE_HEIGHT = ";
	private static final String constructeur="\t public ";
	private static final String	constructeurArgs="\t \tsuper(NAME, IMG, EXTENSION, XSCHEMA);";
	private static final String constructeurCorps= "\t \tint i = 1;";
	private static final String nodeFormalism0="\t \tElementFormalism ";
	private static final String nodeFormalism1=	"eltForm = new NodeFormalism(";
	private static final String nodeFormalism2=", Messages.";
	private static final String nodeFormalism3=", this, INodeGraphicInfo.";
	private static final String nodeFormalism4=", FIGURE_WIDTH, FIGURE_HEIGHT, false);";
	
	private static final String arcFormalism="\t \t eltForm = new ArcFormalism(";
	
	private static final String voidArg="() {\n";
	private static final String cloture="\t } \n }";
	
	
	
	public String construction(String chaine){
		return chaine;
		
	} 
	
	public String sautLignes(int n){
		for (int i=0;i < n;i++)
			return "\n";
		return "\n";
	}
	
	/**
	 * Lecture des balises ouvrantes du modele
	 */
	@Override
	public final void startElement(String uri, String localName, String baliseName, Attributes attributes) throws SAXException {

		if (baliseName.equals("nomFormalism")) {
			System.out.println(data);
			nomFormalism=data;
		}

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
			nomFormalism=data;
			File f;
			f=new File(pathFormalism + nomFormalism+ ".java");
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FileWriter ecrivain;
            try {
				ecrivain = new FileWriter(pathFormalism + nomFormalism +".java",false);
				ecrivain.write(this.construction(lesPackages));
				ecrivain.write(this.construction(nomClasse + data + extensionClasse));
				ecrivain.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		  }
		
		if (qName.equals("presentationFormalism")){
			FileWriter ecrivain;
            try {
				ecrivain = new FileWriter(pathFormalism + nomFormalism +".java",true);
				ecrivain.write(this.construction(variableNom+"\""+data+"\";"));
				ecrivain.write(this.construction(variableImg));
				ecrivain.close();
            } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
		
		if (qName.equals("extensionFormalism")){
			FileWriter ecrivain;
            try {
				ecrivain = new FileWriter(pathFormalism + nomFormalism +".java",true);
				ecrivain.write(this.construction(variableExtension+"\""+data+"\";"));
				ecrivain.write(this.construction(variableXSCHEMA));
				ecrivain.close();
            } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		 } 
		
		if (qName.equals("figure_width")){
			FileWriter ecrivain;
            try {
				ecrivain = new FileWriter(pathFormalism + nomFormalism +".java",true);
				ecrivain.write(this.construction(figureLargeur+data+";\n"));
				ecrivain.close();
            } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		 } 
		
		if (qName.equals("figure_height")){
			FileWriter ecrivain;
            try {
				ecrivain = new FileWriter(pathFormalism + nomFormalism +".java",true);
				ecrivain.write(this.construction(figureHauteur+data+";\n"));
				ecrivain.write(this.construction(this.sautLignes(2)));
				ecrivain.close();
            } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		 } 
		
		if (qName.equals("constructeur")){
			FileWriter ecrivain;
            try {
				ecrivain = new FileWriter(pathFormalism + nomFormalism +".java",true);

				ecrivain.write(this.construction(constructeur+nomFormalism+voidArg));
				ecrivain.write(this.construction(constructeurArgs+"\n"));
				ecrivain.write(this.construction(constructeurCorps+"\n"+nodeFormalism0));

				ecrivain.close();
            } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		 } 
		//Construire un Noeud
		
		if (qName.equals("nomNode")){
			
			FileWriter ecrivain;
            try {
				ecrivain = new FileWriter(pathFormalism + nomFormalism +".java",true);
				ecrivain.write(this.construction(nodeFormalism1 +"\""+ data+"\""));
				ecrivain.close();
            } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
		
		if (qName.equals("nomArc")){
			
			FileWriter ecrivain;
            try {
				ecrivain = new FileWriter(pathFormalism + nomFormalism +".java",true);
				ecrivain.write(this.construction(arcFormalism  +"\""+ data+"\""));
				ecrivain.close();
            } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
		if (qName.equals("messageNode")||qName.equals("messageArc")){
			FileWriter ecrivain;
			try {
				ecrivain = new FileWriter(pathFormalism + nomFormalism +".java",true);
				ecrivain.write(this.construction(nodeFormalism2 + data));
				ecrivain.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}

		if (qName.equals("choixFigureNode")||qName.equals("choixFigureArc")){
		FileWriter ecrivain;
        try {
			ecrivain = new FileWriter(pathFormalism + nomFormalism +".java",true);
			ecrivain.write(this.construction(nodeFormalism3 + data + nodeFormalism4 + "\n"));
			ecrivain.close();
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
		if (qName.equals("formalism")){
			FileWriter ecrivain;
            try {
				ecrivain = new FileWriter(pathFormalism + nomFormalism +".java",true);
				ecrivain.write(this.construction(cloture));
				ecrivain.close();
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
