package fr.lip6.move.coloane.motor.formalism;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;


import fr.lip6.move.coloane.communications.models.Model;
import fr.lip6.move.coloane.interfaces.models.IArcGraphicInfo;
import fr.lip6.move.coloane.interfaces.models.INodeGraphicInfo;
import fr.lip6.move.coloane.motor.models.ModelImplAdapter;


/**
 * Classe du gestionnaire de formalismes. 
 * C'est ici que sont definis les formalismes.
 */
public class FormalismManager {

    /** Liste des formalismes disponibles. */
    private static ArrayList<Formalism> listOfFormalisms;
    
    /**
     * Constructeur de la classe FormalismsManager
     * Constitution de la liste des formalismes
     */
    public FormalismManager() {
    	listOfFormalisms = new ArrayList<Formalism>();
        listOfFormalisms.add(createPetriNet());
        System.out.println("Formalisme Reseaux de Petri charge !");
        listOfFormalisms.add(createPrefixNet());
        System.out.println("Formalisme Prefix Net charge !");
        listOfFormalisms.add(createGraphAccess());
        System.out.println("Formalisme Graphes d'accessibilite charge !");
    }

    /**
     * Charger un formalisme a partir de son nom
     * 
     * @param formalismName Nom du formalisme
     * @return Formalism
     * @see Formalism
     */
    public Formalism loadFormalism(String formalismName) {
        Iterator it;
        Formalism formalism = null;
        for (it = listOfFormalisms.iterator(); it.hasNext();) {
            formalism = (Formalism) it.next();
            if (formalismName.equalsIgnoreCase(formalism.getName())) {
                return formalism;
            }
        }
        return null;
    }

    /**
     * Cette methode retourne un formalisme a partir d'une extension utilisee lors de la sauvegarde
     * d'un modele
     * @param extension Extension recherchee
     * @return Formalism
     */
    public Formalism getFormalismByExtension(String extension) {
    	Formalism formalism;
    	Iterator it = listOfFormalisms.iterator();
    	for (formalism = null; it.hasNext();) {
    		formalism = ((Formalism) it.next());
    		if (extension.equals(formalism.getExtension())) {
    			return formalism;
    		}
    	}
    	return null;
    }


    /**
     * Cette methode retourne le formalisme decrivant un reseau de Petri.
     * @return Formalism
     */
    private static Formalism createPetriNet() {
        AttributeFormalism attr;
        ElementBase elem;
        Rule rule;
        
        // Creation du formalisme Petri Net.
        Formalism petri = new Formalism("AMI-Net", "icons/petrinet.gif");
        petri.setExtension("rdp");
        
        // Ajout de tous les attributs d'un reseau de petri (Attention : different des attributs des elements.)
        attr = new AttributeFormalism("declaration", true, true);
        petri.addAttributeFormalism(attr);
        attr = new AttributeFormalism("author(s)", true, true);
        petri.addAttributeFormalism(attr);
        attr = new AttributeFormalism("version", true, false, "0.0");
        petri.addAttributeFormalism(attr);
        attr = new AttributeFormalism("project", true, true);
        petri.addAttributeFormalism(attr);
        attr = new AttributeFormalism("title", true, true);
        petri.addAttributeFormalism(attr);
        attr = new AttributeFormalism("date", true, false);
        petri.addAttributeFormalism(attr);
        attr = new AttributeFormalism("code", true, true);
        petri.addAttributeFormalism(attr);
        attr = new AttributeFormalism("note", false, true);
        petri.addAttributeFormalism(attr);

        // Creation ajout des differents elements de base d'un Reseau de Petri :
        // place, transition, arc, arc inhibiteur.
        
        // La place:
        elem = new NodeFormalism("place", INodeGraphicInfo.FIG_CIRCLE, 16, 16, false);
        attr = new AttributeFormalism("name", true, false);
        elem.addAttributeFormalism(attr);
        attr = new AttributeFormalism("domain", true, true);
        elem.addAttributeFormalism(attr);
        attr = new AttributeFormalism("marking", true, true);
        elem.addAttributeFormalism(attr);
        attr = new AttributeFormalism("component", false, true);
        elem.addAttributeFormalism(attr);
        attr = new AttributeFormalism("note", false, true);
        elem.addAttributeFormalism(attr);
        elem.setFormalism(petri);
        elem.setAddrIcone16("/icons/place16.png");
        elem.setAddrIcone24("/icons/place24.png");
        petri.addElementBase(elem);

        // La transition:
        elem = new NodeFormalism("transition", INodeGraphicInfo.FIG_RECT, 24, 8, false);
        attr = new AttributeFormalism("name", true, false);
        elem.addAttributeFormalism(attr);
        attr = new AttributeFormalism("guard", true, true);
        elem.addAttributeFormalism(attr);
        attr = new AttributeFormalism("priority", true, true, "0");
        elem.addAttributeFormalism(attr);
        attr = new AttributeFormalism("delay", true, true, "true");
        elem.addAttributeFormalism(attr);
        attr = new AttributeFormalism("action", false, true);
        elem.addAttributeFormalism(attr);
        attr = new AttributeFormalism("note", false, true);
        elem.addAttributeFormalism(attr);
        elem.setFormalism(petri);
        elem.setAddrIcone16("/icons/transition16.png");
        elem.setAddrIcone24("/icons/transition24.png");
        petri.addElementBase(elem);

        // La transition immediate:
        elem = new NodeFormalism("immediate transition",INodeGraphicInfo.FIG_RECT, 24, 8, true);
        attr = new AttributeFormalism("name", true, false);
        elem.addAttributeFormalism(attr);
        attr = new AttributeFormalism("guard", true, true);
        elem.addAttributeFormalism(attr);
        attr = new AttributeFormalism("priority", true, true, "1");
        elem.addAttributeFormalism(attr);
        attr = new AttributeFormalism("weight", true, true);
        elem.addAttributeFormalism(attr);
        attr = new AttributeFormalism("note", false, true);
        elem.addAttributeFormalism(attr);
        elem.setFormalism(petri);
        elem.setAddrIcone16("/icons/transitionimmediate16.png");
        elem.setAddrIcone24("/icons/transitionimmediate24.png");
        petri.addElementBase(elem);
 
        // La queue:
        elem = new NodeFormalism("queue", INodeGraphicInfo.FIG_QUEUE, 16, 8, true);
        attr = new AttributeFormalism("name", true, false);
        elem.addAttributeFormalism(attr);
        attr = new AttributeFormalism("domain", true, true);
        elem.addAttributeFormalism(attr);
        attr = new AttributeFormalism("marking", true, true);
        elem.addAttributeFormalism(attr);
        attr = new AttributeFormalism("note", false, true);
        elem.addAttributeFormalism(attr);
        elem.setFormalism(petri);
        elem.setAddrIcone16("/icons/queue16.png");
        elem.setAddrIcone24("/icons/queue24.png");
        petri.addElementBase(elem);

        // L'arc
        elem = new ArcFormalism("arc", IArcGraphicInfo.FIG_ARC_SIMPLE, 8, 8, false);
        attr = new AttributeFormalism("valuation", true, true, "1");
        elem.addAttributeFormalism(attr);
        attr = new AttributeFormalism("note", false, true);
        elem.addAttributeFormalism(attr);
        elem.setFormalism(petri);
        elem.setAddrIcone16("/icons/arc16.png");
        elem.setAddrIcone24("/icons/arc24.png");
        petri.addElementBase(elem);

        // L'arc hinibiteur
        elem = new ArcFormalism("inhibitor arc", IArcGraphicInfo.FIG_ARC_INHIBITOR, 8, 8, false);
        attr = new AttributeFormalism("valuation", true, true, "1");
        elem.addAttributeFormalism(attr);
        attr = new AttributeFormalism("note", false, true);
        elem.addAttributeFormalism(attr);
        elem.setFormalism(petri);
        elem.setAddrIcone16("/icons/arcinhibiteur16.png");
        elem.setAddrIcone24("/icons/arcinhibiteur24.png");
        petri.addElementBase(elem);

        // Ajout des regles gerant le formalisme, ces regles definissent ce qu'on ne peut pas faire.
        // Interdit place - place
        rule = new Rule("place - place","Une place ne peut etre reliee qu'a une transition.");
        rule.forbidenRule(petri, "place", "place");
        petri.addRule(rule);

        // Interdit queue - queue
        rule = new Rule("queue - queue","Une queue ne peut reliee qu'a une transition.");
        rule.forbidenRule(petri, "queue", "queue");
        petri.addRule(rule);

        // Interdit transition - transition
        rule = new Rule("transition - transition","Une transition ne peut reliee qu'a une place ou une queue.");
        rule.forbidenRule(petri, "transition", "transition");
        petri.addRule(rule);

        // Interdit transition immediate - transition immediate
        rule = new Rule("transition immediate - transition immediate","Une transition immediate ne peut reliee qu'a une place ou une queue.");
        rule.forbidenRule(petri, "immediate transition", "immediate transition");
        petri.addRule(rule);

        // Interdit transition - transition immediate
        rule = new Rule("transition - transition immediate","Une transition ne peut reliee qu'a une place ou une queue.");
        rule.forbidenRule(petri, "transition", "immediate transition");
        petri.addRule(rule);

        // Interdit transition immediate - transition
        rule = new Rule("transition immediate - transition","Une transition immediate ne peut reliee qu'a une place ou une queue.");
        rule.forbidenRule(petri, "immediate transition", "transition");
        petri.addRule(rule);

        // Interdit transition queue - place 
        rule = new Rule("queue - place","Une queue ne peut reliee qu'a une transition ou une transition immediate.");
        rule.forbidenRule(petri, "queue", "place");
        petri.addRule(rule);

        // Interdit transition place - queue
        rule = new Rule("place - queue","Une place ne peut reliee qu'a une transition ou une transition immediate.");
        rule.forbidenRule(petri, "place", "queue");
        petri.addRule(rule);
        
        return petri;
    }
    /**
     * Cette methode retourne le formalisme decrivant un reseau de Prefix Nets.
     * @return Formalism
     */
    private static Formalism createPrefixNet() {
        AttributeFormalism attr;
        ElementBase elem;
        Rule rule;
        
        // Creation du formalisme Prefix nets.
        
        Formalism prefix = new Formalism("Branching-Process", "prefixnet.gif");
        prefix.setExtension("pnt");
        
        // Ajout de tous les attributs d'un prefix net (Attention ! Different des attributs des elements.)
        attr = new AttributeFormalism("tool", true, true);
        prefix.addAttributeFormalism(attr);
        
        attr = new AttributeFormalism("origin", true, true);
        prefix.addAttributeFormalism(attr);
        
        // Creation et ajout des differents elements de base d'un prefix net :
        // condition, event, cutoff, arc.
        
        // Condition
        elem = new NodeFormalism("condition", INodeGraphicInfo.FIG_CIRCLE, 16, 16, false);
        attr = new AttributeFormalism("name", true, false);
        elem.addAttributeFormalism(attr);
        attr = new AttributeFormalism("label", true, true);
        elem.addAttributeFormalism(attr);
        attr = new AttributeFormalism("marking", true, true);
        elem.addAttributeFormalism(attr);
        elem.setFormalism(prefix);
        elem.setAddrIcone16("../icons/place16.gif");
        elem.setAddrIcone24("icons/place24.gif");
        prefix.addElementBase(elem);

        // Event:
        elem = new NodeFormalism("event", INodeGraphicInfo.FIG_RECT, 24, 8, false);
        attr = new AttributeFormalism("xname", true, false);
        elem.addAttributeFormalism(attr);
        attr = new AttributeFormalism("xlabel", true, true, "true");
        elem.addAttributeFormalism(attr);
        elem.setFormalism(prefix);
        elem.setAddrIcone16("icons/transition16.gif");
        elem.setAddrIcone24("icons/transition24.gif");
        prefix.addElementBase(elem);

        // Cutoff:
        elem = new NodeFormalism("cutoff", INodeGraphicInfo.FIG_RECT, 24, 8, true);
        attr = new AttributeFormalism("name", true, false);
        elem.addAttributeFormalism(attr);
        attr = new AttributeFormalism("label", true, true, "true");
        elem.addAttributeFormalism(attr);
        attr = new AttributeFormalism("image", true, true, "1");
        elem.addAttributeFormalism(attr);
        attr = new AttributeFormalism("num_image", false, true, "1");
        elem.addAttributeFormalism(attr);
        elem.setFormalism(prefix);
        elem.setAddrIcone16("icons/transitionimmediate16.gif");
        elem.setAddrIcone24("icons/transitionimmediate24.gif");
        prefix.addElementBase(elem);
 
        // Arc
        elem = new ArcFormalism("arc", IArcGraphicInfo.FIG_ARC_SIMPLE, 8, 8, false);
        elem.setFormalism(prefix);
        elem.setAddrIcone16("icons/arc16.gif");
        elem.setAddrIcone24("icons/arc24.gif");
        prefix.addElementBase(elem);

        
        
        // Ajout des regles gerant le formalisme, ces regles definissent ce qu'on ne peut pas faire.
        
        // Interdit condition - condition
        rule = new Rule("condition - condition", "Une condition ne peut etre reliee qu'a un event ou un cutoff.");
        rule.forbidenRule(prefix, "condition", "condition");
        prefix.addRule(rule);

        // Interdit event - event
        rule = new Rule("event - event", "Un event ne peut etre relie qu'a une condition.");
        rule.forbidenRule(prefix, "event", "event");
        prefix.addRule(rule);

        // Interdit cutoff - event 
        rule = new Rule("cutoff - event", "Un cutoff ne peut etre relie qu'a une condition.");
        rule.forbidenRule(prefix, "cutoff", "event");
        prefix.addRule(rule);
        
        // Interdit event - cutoff
        rule = new Rule("event - cutoff", "Un cutoff ou un event ne peut etre relie qu'a une condition.");
        rule.forbidenRule(prefix, "event", "cutoff");
        prefix.addRule(rule);

        // Interdit cutoff - cutoff 
        rule = new Rule("cutoff - cutoff", "Un cutoff ne peut etre relie qu'a une condition.");
        rule.forbidenRule(prefix, "cutoff", "cutoff");
        prefix.addRule(rule);
        
        
        return prefix;
    }
    
    
    /**
     * Cette methode retourne le formalisme decrivant un graphe d'accessibilite.
     * @return Formalism
     */

    private static Formalism createGraphAccess() {
        AttributeFormalism attr;
        ElementBase elem;
  
        // Creation du formalisme Graphes d'accessibilite.
        Formalism graph = new Formalism("ReachabilityGraph", "graphsaccess.gif");
        graph.setExtension("gha");
        
        // Ajout de tous les attributs d'un graphe d'accessibilite (Attention :
        // different des attributs des elements.)
        attr = new AttributeFormalism("title", true, true);
        graph.addAttributeFormalism(attr);
        attr = new AttributeFormalism("author(s)", true, true);
        graph.addAttributeFormalism(attr);
        attr = new AttributeFormalism("version", true, false, "0.0");
        graph.addAttributeFormalism(attr);
        attr = new AttributeFormalism("information", true, true);
        graph.addAttributeFormalism(attr);
        attr = new AttributeFormalism("type", true, true);
        graph.addAttributeFormalism(attr);
        attr = new AttributeFormalism("project", true, false);
        graph.addAttributeFormalism(attr);
   
        // Creation ajout des differents elements de base d'un graphe d'accessibilite :
        // etat initial, etat final, etat, event (arc)

        // L'etat initial:
        elem = new NodeFormalism("initial_state", INodeGraphicInfo.FIG_DBLCIRCLE, 16, 16, false);
        attr = new AttributeFormalism("name", true, false);
        elem.addAttributeFormalism(attr);
        attr = new AttributeFormalism("value", true, true);
        elem.addAttributeFormalism(attr);
        attr = new AttributeFormalism("initial", true, true);
        elem.addAttributeFormalism(attr);
        attr = new AttributeFormalism("deadlock", false, true);
        elem.addAttributeFormalism(attr);
        elem.setFormalism(graph);
        elem.setAddrIcone16("icons/initial16.gif");
        elem.setAddrIcone24("icons/initial24.gif");
        graph.addElementBase(elem);

        // L'etat terminal:
        elem = new NodeFormalism("terminal_state", INodeGraphicInfo.FIG_CIRCLE, 16, 16, true);
        attr = new AttributeFormalism("name", true, false);
        elem.addAttributeFormalism(attr);
        attr = new AttributeFormalism("value", true, true);
        elem.addAttributeFormalism(attr);
        attr = new AttributeFormalism("initial", true, true);
        elem.addAttributeFormalism(attr);
        attr = new AttributeFormalism("deadlock", false, true);
        elem.addAttributeFormalism(attr);
        elem.setFormalism(graph);
        elem.setAddrIcone16("icons/terminal16.gif");
        elem.setAddrIcone24("icons/terminal24.gif");
        graph.addElementBase(elem);

        // L'etat:
        elem = new NodeFormalism("state", INodeGraphicInfo.FIG_CIRCLE, 16, 16, false);
        attr = new AttributeFormalism("name", true, false);
        elem.addAttributeFormalism(attr);
        attr = new AttributeFormalism("value", true, true);
        elem.addAttributeFormalism(attr);
        attr = new AttributeFormalism("initial", true, true);
        elem.addAttributeFormalism(attr);
        attr = new AttributeFormalism("deadlock", false, true);
        elem.addAttributeFormalism(attr);
        elem.setFormalism(graph);
        elem.setAddrIcone16("../icons/place16.gif");
        elem.setAddrIcone24("icons/place24.gif");
        graph.addElementBase(elem);
     
        // L'event (arc)
        elem = new ArcFormalism("event", IArcGraphicInfo.FIG_ARC_SIMPLE, 8, 8, false);
        attr = new AttributeFormalism("label", true, false);
        elem.addAttributeFormalism(attr);
        attr = new AttributeFormalism("value", false, true);
        elem.addAttributeFormalism(attr);
        elem.setFormalism(graph);
        elem.setAddrIcone16("icons/arc16.gif");
        elem.setAddrIcone24("icons/arc24.gif");
        graph.addElementBase(elem);

        // Pas de rules : toute association autorisee
        
        return graph;
    }


    /**
     * Sauvegarde un modele  dans un fichier de nom fileName
     * 
     * @param model modele
     * @param fileName nom du fichier de sauvegarde
     * @throws Exception leve une exception si erreur
     */
    public void saveModel(ModelImplAdapter model, String fileName) throws Exception {
        try {
        	ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
             
            // Ecriture de l'objet
            out.writeObject(model);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            throw e;
        }
    }

    
    /**
     * Recupere un modele a partir d'un fichier de nom fileName
     * 
     * @param fileName nom du fichier de sauvegarde avec extension 
     * @return ModelImplAdapter
     * @throws Exception fichier non trouve
     */
    public ModelImplAdapter openModel(String fileName) throws Exception {
        ModelImplAdapter model = null;
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
        model = (ModelImplAdapter) in.readObject();
        in.close();
        return model;
    }
    
    /**
     * Importe un modele en determinant son formalisme en fonction de l'extension du fichier
     * @param fileName nom de fchier a importer
     * @return le model adapter correspondant
     * @throws Exception leve d'exception si le fichier n'est pas valide
     */
    public ModelImplAdapter importModel(String fileName) throws Exception {
        
        // Determination du formalism avec l'extension
        StringTokenizer file = new StringTokenizer(fileName, ".");
        //if (file.countTokens() != 2) {
          //  throw new Exception("Nom de fichier invalide");
        //}
        // Debut du nom
        String fext = file.nextToken();
        // Extension
        fext = file.nextToken();
        // Formalism
        Formalism formalism = getFormalismByExtension(fext);
        
        // On verifie qu'un formalisme existe bien pour cette extension
        if (formalism == null) { 
        	throw new Exception("Extension inconnue");
        }
        
        System.out.println("Nom du fichier :"+fileName);
        System.out.println("Extension"+fext);
        
        Model apiModel = new Model(new File(fileName));
        if (apiModel == null) {
            throw new Exception("Creation du modele impossible d'apres le fichier");
        }
        return new ModelImplAdapter(apiModel, formalism);
    }
    
    
 
    /**
     * Export un modeladapter en model en fonction de son formalisme
     * @param modelAdapter le model a enregistrer
     * @param fileName nom du fichier désiré sans les extentions
     * @throws Exception 
     */
  
    public void exportModel(ModelImplAdapter modelAdapter, String fileName) throws Exception {
    	
        if (fileName.equalsIgnoreCase("") || fileName == null) {
            throw new Exception("Extention du fichier ou nom invalide");
        }
    	
        // Extention du formalism
        String ext = modelAdapter.getFormalism().getExtension();
        if (ext == null) {
            throw new Exception("Extention du fichier ou nom invalide");
        }
        // Creation du fichier
        FileOutputStream wr = new FileOutputStream(new File(fileName +"."+ ext)); 
        BufferedWriter buff = new BufferedWriter(new OutputStreamWriter(wr));
        
        // Traduction du modele entier
        try {
        	String[] cami = modelAdapter.getGenericModel().translateToCAMI();
        	for (int i = 0; i < cami.length; i++) {
        		buff.write(cami[i]);
        		buff.newLine();
        	}
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        buff.flush();
        wr.flush();
        buff.close();
        wr.close();
        
    }
    
    /**
     * Exporter modele directement dans un OutputStream
     * Cette mŽthode peut tre utilisee par Eclipse (progress bar et dialogues) 
     * 
     * @param model Le modele a exporter
     * @param os Flux objet
     * @throws IOException
     */
    public void exportModel(ModelImplAdapter model, OutputStream os) throws IOException {
		
		BufferedWriter buff = new BufferedWriter(new OutputStreamWriter(os));
        
        String[] cami = model.getGenericModel().translateToCAMI();
        for (int i = 0; i < cami.length; i++) {
            buff.write(cami[i]);
            buff.newLine();
        }
		buff.flush();
		buff.close();
    }
    

    /**
     * Retourne la liste des formalismes disponibles 
     * @return listOfFormalism
     */
    public ArrayList getListOfFormalisms() {
        return listOfFormalisms;
    }
}
