package fr.lip6.move.coloane.motor.formalism;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;


import fr.lip6.move.coloane.interfaces.model.IModel;
import fr.lip6.move.coloane.main.Coloane;
import fr.lip6.move.coloane.model.Model;
import fr.lip6.move.coloane.ui.model.IArcGraphicInfo;
import fr.lip6.move.coloane.ui.model.IModelImpl;
import fr.lip6.move.coloane.ui.model.INodeGraphicInfo;
import fr.lip6.move.coloane.ui.model.IAttributeGraphicInfo;
import fr.lip6.move.coloane.ui.model.ModelImplAdapter;


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
		listOfFormalisms.add(createPrefixNet());
		listOfFormalisms.add(createGraphAccess());
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
			formalism = (Formalism) it.next();
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
		Formalism petri = new Formalism("AMI-Net", "/resources/icons/ami.gif"); //$NON-NLS-1$ //$NON-NLS-2$
		petri.setExtension("rdp"); //$NON-NLS-1$

		// Ajout de tous les attributs d'un reseau de petri (Attention : different des attributs des elements.)
		attr = new AttributeFormalism(1,"declaration", IAttributeGraphicInfo.NOR, true, true); //$NON-NLS-1$
		petri.addAttributeFormalism(attr);
		attr = new AttributeFormalism(2,"author(s)", IAttributeGraphicInfo.NOR, true, true); //$NON-NLS-1$
		petri.addAttributeFormalism(attr);
		attr = new AttributeFormalism(3,"version", IAttributeGraphicInfo.NOR, true, false, "0,0"); //$NON-NLS-1$ //$NON-NLS-2$
		petri.addAttributeFormalism(attr);
		attr = new AttributeFormalism(4,"project", IAttributeGraphicInfo.NOR, true, true); //$NON-NLS-1$
		petri.addAttributeFormalism(attr);
		attr = new AttributeFormalism(5,"title", IAttributeGraphicInfo.NOR, true, true); //$NON-NLS-1$
		petri.addAttributeFormalism(attr);
		attr = new AttributeFormalism(6,"date", IAttributeGraphicInfo.NOR, true, false); //$NON-NLS-1$
		petri.addAttributeFormalism(attr);
		attr = new AttributeFormalism(7,"code", IAttributeGraphicInfo.NOR, true, true); //$NON-NLS-1$
		petri.addAttributeFormalism(attr);
		attr = new AttributeFormalism(8,"note", false, true); //$NON-NLS-1$
		petri.addAttributeFormalism(attr);

		// Creation ajout des differents elements de base d'un Reseau de Petri :
		// place, transition, arc, arc inhibiteur.

		// La place:
		elem = new NodeFormalism("place", "Place", INodeGraphicInfo.FIG_CIRCLE, 16, 16, false); //$NON-NLS-1$ //$NON-NLS-2$
		attr = new AttributeFormalism(1,"name", IAttributeGraphicInfo.L1, true, false); //$NON-NLS-1$
		elem.addAttributeFormalism(attr);
		attr = new AttributeFormalism(2,"domain", IAttributeGraphicInfo.L2, true, true); //$NON-NLS-1$
		elem.addAttributeFormalism(attr);
		attr = new AttributeFormalism(3,"marking", IAttributeGraphicInfo.NOR, true, true); //$NON-NLS-1$
		elem.addAttributeFormalism(attr);
		attr = new AttributeFormalism(4,"component", false, true); //$NON-NLS-1$
		elem.addAttributeFormalism(attr);
		attr = new AttributeFormalism(5,"note", false, true); //$NON-NLS-1$
		elem.addAttributeFormalism(attr);
		elem.setFormalism(petri);
		elem.setAddrIcone16("/resources/icons/place16.png"); //$NON-NLS-1$
		elem.setAddrIcone24("/resources/icons/place24.png"); //$NON-NLS-1$
		petri.addElementBase(elem);

		// La transition:
		elem = new NodeFormalism("transition", "Transition", INodeGraphicInfo.FIG_RECT, 24, 8, false); //$NON-NLS-1$ //$NON-NLS-2$
		attr = new AttributeFormalism(1,"name", IAttributeGraphicInfo.L1, true, false); //$NON-NLS-1$
		elem.addAttributeFormalism(attr);
		attr = new AttributeFormalism(2,"guard",IAttributeGraphicInfo.NOR, true, true,"true"); //$NON-NLS-1$ //$NON-NLS-2$
		elem.addAttributeFormalism(attr);
		attr = new AttributeFormalism(3,"priority", IAttributeGraphicInfo.NOR, true, true, "0"); //$NON-NLS-1$ //$NON-NLS-2$
		elem.addAttributeFormalism(attr);
		attr = new AttributeFormalism(4,"delay", IAttributeGraphicInfo.NOR, true, true); //$NON-NLS-1$
		elem.addAttributeFormalism(attr);
		attr = new AttributeFormalism(5,"action", false, true); //$NON-NLS-1$
		elem.addAttributeFormalism(attr);
		attr = new AttributeFormalism(6,"note", false, true); //$NON-NLS-1$
		elem.addAttributeFormalism(attr);
		elem.setFormalism(petri);
		elem.setAddrIcone16("/resources/icons/transition16.png"); //$NON-NLS-1$
		elem.setAddrIcone24("/resources/icons/transition24.png"); //$NON-NLS-1$
		petri.addElementBase(elem);

		// La transition immediate:
		elem = new NodeFormalism("immediate transition", "I. Transition", INodeGraphicInfo.FIG_RECT, 24, 8, true); //$NON-NLS-1$ //$NON-NLS-2$
		attr = new AttributeFormalism(1,"name", IAttributeGraphicInfo.L1, true, false); //$NON-NLS-1$
		elem.addAttributeFormalism(attr);
		attr = new AttributeFormalism(2,"guard", IAttributeGraphicInfo.NOR, true, true); //$NON-NLS-1$
		elem.addAttributeFormalism(attr);
		attr = new AttributeFormalism(3,"priority", IAttributeGraphicInfo.NOR, true, true, "1"); //$NON-NLS-1$ //$NON-NLS-2$
		elem.addAttributeFormalism(attr);
		attr = new AttributeFormalism(4,"weight", IAttributeGraphicInfo.NOR, true, true); //$NON-NLS-1$
		elem.addAttributeFormalism(attr);
		attr = new AttributeFormalism(5,"note", false, true); //$NON-NLS-1$
		elem.addAttributeFormalism(attr);
		elem.setFormalism(petri);
		elem.setAddrIcone16("/resources/icons/transitionimmediate16.png"); //$NON-NLS-1$
		elem.setAddrIcone24("/resources/icons/transitionimmediate24.png"); //$NON-NLS-1$
		petri.addElementBase(elem);

		// La queue:
		elem = new NodeFormalism("queue", "Queue", INodeGraphicInfo.FIG_QUEUE, 16, 8, true); //$NON-NLS-1$ //$NON-NLS-2$
		attr = new AttributeFormalism(1,"name", IAttributeGraphicInfo.L1, true, false); //$NON-NLS-1$
		elem.addAttributeFormalism(attr);
		attr = new AttributeFormalism(2,"domain", IAttributeGraphicInfo.L2, true, true); //$NON-NLS-1$
		elem.addAttributeFormalism(attr);
		attr = new AttributeFormalism(3,"marking", IAttributeGraphicInfo.NOR, true, true); //$NON-NLS-1$
		elem.addAttributeFormalism(attr);
		attr = new AttributeFormalism(4,"note", false, true); //$NON-NLS-1$
		elem.addAttributeFormalism(attr);
		elem.setFormalism(petri);
		elem.setAddrIcone16("/resources/icons/queue16.png"); //$NON-NLS-1$
		elem.setAddrIcone24("/resources/icons/queue24.png"); //$NON-NLS-1$
		petri.addElementBase(elem);

		// L'arc
		elem = new ArcFormalism("arc", "Arc", IArcGraphicInfo.FIG_ARC_SIMPLE, 8, 8, false); //$NON-NLS-1$ //$NON-NLS-2$
		attr = new AttributeFormalism(1,"valuation", IAttributeGraphicInfo.NOR, true, true, "1"); //$NON-NLS-1$ //$NON-NLS-2$
		elem.addAttributeFormalism(attr);
		attr = new AttributeFormalism(2,"note", false, true); //$NON-NLS-1$
		elem.addAttributeFormalism(attr);
		elem.setFormalism(petri);
		elem.setAddrIcone16("/resources/icons/arc16.png"); //$NON-NLS-1$
		elem.setAddrIcone24("/resources/icons/arc24.png"); //$NON-NLS-1$
		petri.addElementBase(elem);

		// L'arc hinibiteur
		elem = new ArcFormalism("inhibitor arc", "Inhibitor Arc", IArcGraphicInfo.FIG_ARC_INHIBITOR, 8, 8, false); //$NON-NLS-1$ //$NON-NLS-2$
		attr = new AttributeFormalism(1,"valuation", IAttributeGraphicInfo.NOR, true, true, "1"); //$NON-NLS-1$ //$NON-NLS-2$
		elem.addAttributeFormalism(attr);
		attr = new AttributeFormalism(2,"note", false, true); //$NON-NLS-1$
		elem.addAttributeFormalism(attr);
		elem.setFormalism(petri);
		elem.setAddrIcone16("/resources/icons/arcinhibiteur16.png"); //$NON-NLS-1$
		elem.setAddrIcone24("/resources/icons/arcinhibiteur24.png"); //$NON-NLS-1$
		petri.addElementBase(elem);

		// Ajout des regles gerant le formalisme, ces regles definissent ce qu'on ne peut pas faire.
		// Interdit place - place
		rule = new Rule("place - place",Coloane.traduction.getString("motor.formalism.FormalismManager.66")); //$NON-NLS-1$ //$NON-NLS-2$
		rule.forbidenRule(petri, "place", "place"); //$NON-NLS-1$ //$NON-NLS-2$
		petri.addRule(rule);

		// Interdit queue - queue
		rule = new Rule("queue - queue",Coloane.traduction.getString("motor.formalism.FormalismManager.70")); //$NON-NLS-1$ //$NON-NLS-2$
		rule.forbidenRule(petri, "queue", "queue"); //$NON-NLS-1$ //$NON-NLS-2$
		petri.addRule(rule);

		// Interdit transition - transition
		rule = new Rule("transition - transition",Coloane.traduction.getString("motor.formalism.FormalismManager.74")); //$NON-NLS-1$ //$NON-NLS-2$
		rule.forbidenRule(petri, "transition", "transition"); //$NON-NLS-1$ //$NON-NLS-2$
		petri.addRule(rule);

		// Interdit transition immediate - transition immediate
		rule = new Rule("transition immediate - transition immediate",Coloane.traduction.getString("motor.formalism.FormalismManager.78")); //$NON-NLS-1$ //$NON-NLS-2$
		rule.forbidenRule(petri, "immediate transition", "immediate transition"); //$NON-NLS-1$ //$NON-NLS-2$
		petri.addRule(rule);

		// Interdit transition - transition immediate
		rule = new Rule("transition - transition immediate",Coloane.traduction.getString("motor.formalism.FormalismManager.82")); //$NON-NLS-1$ //$NON-NLS-2$
		rule.forbidenRule(petri, "transition", "immediate transition"); //$NON-NLS-1$ //$NON-NLS-2$
		petri.addRule(rule);

		// Interdit transition immediate - transition
		rule = new Rule("transition immediate - transition",Coloane.traduction.getString("motor.formalism.FormalismManager.86")); //$NON-NLS-1$ //$NON-NLS-2$
		rule.forbidenRule(petri, "immediate transition", "transition"); //$NON-NLS-1$ //$NON-NLS-2$
		petri.addRule(rule);

		// Interdit transition queue - place 
		rule = new Rule("queue - place",Coloane.traduction.getString("motor.formalism.FormalismManager.90")); //$NON-NLS-1$ //$NON-NLS-2$
		rule.forbidenRule(petri, "queue", "place"); //$NON-NLS-1$ //$NON-NLS-2$
		petri.addRule(rule);

		// Interdit transition place - queue
		rule = new Rule("place - queue",Coloane.traduction.getString("motor.formalism.FormalismManager.94")); //$NON-NLS-1$ //$NON-NLS-2$
		rule.forbidenRule(petri, "place", "queue"); //$NON-NLS-1$ //$NON-NLS-2$
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

		Formalism prefix = new Formalism("Branching-Process", "/resources/icons/prefix.gif"); //$NON-NLS-1$ //$NON-NLS-2$
		prefix.setExtension("pnt"); //$NON-NLS-1$

		// Ajout de tous les attributs d'un prefix net (Attention ! Different des attributs des elements.)
		attr = new AttributeFormalism(1,"tool", IAttributeGraphicInfo.NOR, true, true); //$NON-NLS-1$
		prefix.addAttributeFormalism(attr);

		attr = new AttributeFormalism(2,"origin", IAttributeGraphicInfo.NOR, true, true); //$NON-NLS-1$
		prefix.addAttributeFormalism(attr);

		// Creation et ajout des differents elements de base d'un prefix net :
		// condition, event, cutoff, arc.

		// Condition
		elem = new NodeFormalism("condition", "Condition", INodeGraphicInfo.FIG_CIRCLE, 16, 16, false); //$NON-NLS-1$ //$NON-NLS-2$
		attr = new AttributeFormalism(1,"name", IAttributeGraphicInfo.L1, true, false); //$NON-NLS-1$
		elem.addAttributeFormalism(attr);
		attr = new AttributeFormalism(2,"label", IAttributeGraphicInfo.L2, true, true); //$NON-NLS-1$
		elem.addAttributeFormalism(attr);
		attr = new AttributeFormalism(3,"marking", IAttributeGraphicInfo.NOR, true, true); //$NON-NLS-1$
		elem.addAttributeFormalism(attr);
		elem.setFormalism(prefix);
		elem.setAddrIcone16("/resources/icons/place16.gif"); //$NON-NLS-1$
		elem.setAddrIcone24("/resources/icons/place24.gif"); //$NON-NLS-1$
		prefix.addElementBase(elem);

		// Event:
		elem = new NodeFormalism("event", "Event", INodeGraphicInfo.FIG_RECT, 24, 8, false); //$NON-NLS-1$ //$NON-NLS-2$
		attr = new AttributeFormalism(1,"name", IAttributeGraphicInfo.NOR, true, false); //$NON-NLS-1$
		elem.addAttributeFormalism(attr);
		attr = new AttributeFormalism(2,"label", IAttributeGraphicInfo.NOR, true, true, "true"); //$NON-NLS-1$ //$NON-NLS-2$
		elem.addAttributeFormalism(attr);
		elem.setFormalism(prefix);
		elem.setAddrIcone16("/resources/icons/transition16.gif"); //$NON-NLS-1$
		elem.setAddrIcone24("/resources/icons/transition24.gif"); //$NON-NLS-1$
		prefix.addElementBase(elem);

		// Cutoff:
		elem = new NodeFormalism("cutoff", "Cutoff", INodeGraphicInfo.FIG_RECT, 24, 8, true); //$NON-NLS-1$ //$NON-NLS-2$
		attr = new AttributeFormalism(1,"name", IAttributeGraphicInfo.L1, true, false); //$NON-NLS-1$
		elem.addAttributeFormalism(attr);
		attr = new AttributeFormalism(2,"label", IAttributeGraphicInfo.L2, true, true, "true"); //$NON-NLS-1$ //$NON-NLS-2$
		elem.addAttributeFormalism(attr);
		attr = new AttributeFormalism(3,"image", IAttributeGraphicInfo.NOR, true, true, "1"); //$NON-NLS-1$ //$NON-NLS-2$
		elem.addAttributeFormalism(attr);
		attr = new AttributeFormalism(4,"num_image", false, true, "1"); //$NON-NLS-1$ //$NON-NLS-2$
		elem.addAttributeFormalism(attr);
		elem.setFormalism(prefix);
		elem.setAddrIcone16("/resources/icons/transitionimmediate16.gif"); //$NON-NLS-1$
		elem.setAddrIcone24("/resources/icons/transitionimmediate24.gif"); //$NON-NLS-1$
		prefix.addElementBase(elem);

		// Arc
		elem = new ArcFormalism("arc", "Arc", IArcGraphicInfo.FIG_ARC_SIMPLE, 8, 8, false); //$NON-NLS-1$ //$NON-NLS-2$
		elem.setFormalism(prefix);
		elem.setAddrIcone16("/resources/icons/arc16.gif"); //$NON-NLS-1$
		elem.setAddrIcone24("/resources/icons/arc24.gif"); //$NON-NLS-1$
		prefix.addElementBase(elem);



		// Ajout des regles gerant le formalisme, ces regles definissent ce qu'on ne peut pas faire.

		// Interdit condition - condition
		rule = new Rule("condition - condition", Coloane.traduction.getString("motor.formalism.FormalismManager.132")); //$NON-NLS-1$ //$NON-NLS-2$
		rule.forbidenRule(prefix, "condition", "condition"); //$NON-NLS-1$ //$NON-NLS-2$
		prefix.addRule(rule);

		// Interdit event - event
		rule = new Rule("event - event", Coloane.traduction.getString("motor.formalism.FormalismManager.136")); //$NON-NLS-1$ //$NON-NLS-2$
		rule.forbidenRule(prefix, "event", "event"); //$NON-NLS-1$ //$NON-NLS-2$
		prefix.addRule(rule);

		// Interdit cutoff - event 
		rule = new Rule("cutoff - event", Coloane.traduction.getString("motor.formalism.FormalismManager.140")); //$NON-NLS-1$ //$NON-NLS-2$
		rule.forbidenRule(prefix, "cutoff", "event"); //$NON-NLS-1$ //$NON-NLS-2$
		prefix.addRule(rule);

		// Interdit event - cutoff
		rule = new Rule("event - cutoff", Coloane.traduction.getString("motor.formalism.FormalismManager.144")); //$NON-NLS-1$ //$NON-NLS-2$
		rule.forbidenRule(prefix, "event", "cutoff"); //$NON-NLS-1$ //$NON-NLS-2$
		prefix.addRule(rule);

		// Interdit cutoff - cutoff 
		rule = new Rule("cutoff - cutoff", Coloane.traduction.getString("motor.formalism.FormalismManager.148")); //$NON-NLS-1$ //$NON-NLS-2$
		rule.forbidenRule(prefix, "cutoff", "cutoff"); //$NON-NLS-1$ //$NON-NLS-2$
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
		Formalism graph = new Formalism("ReachabilityGraph", "/resources/icons/gma.gif"); //$NON-NLS-1$ //$NON-NLS-2$
		graph.setExtension("gma"); //$NON-NLS-1$

		// Ajout de tous les attributs d'un graphe d'accessibilite (Attention :
		// different des attributs des elements.)
		attr = new AttributeFormalism(1,"title", IAttributeGraphicInfo.NOR, true, true); //$NON-NLS-1$
		graph.addAttributeFormalism(attr);
		attr = new AttributeFormalism(2,"author(s)", IAttributeGraphicInfo.NOR, true, true); //$NON-NLS-1$
		graph.addAttributeFormalism(attr);
		attr = new AttributeFormalism(3,"version", IAttributeGraphicInfo.NOR, true, false, "0.0"); //$NON-NLS-1$ //$NON-NLS-2$
		graph.addAttributeFormalism(attr);
		attr = new AttributeFormalism(4,"information", IAttributeGraphicInfo.NOR, true, true); //$NON-NLS-1$
		graph.addAttributeFormalism(attr);
		attr = new AttributeFormalism(5,"type", IAttributeGraphicInfo.NOR, true, true); //$NON-NLS-1$
		graph.addAttributeFormalism(attr);
		attr = new AttributeFormalism(6,"project", IAttributeGraphicInfo.NOR, true, false); //$NON-NLS-1$
		graph.addAttributeFormalism(attr);

		// Creation ajout des differents elements de base d'un graphe d'accessibilite :
		// etat initial, etat final, etat, event (arc)

		// L'etat initial:
		elem = new NodeFormalism("initial_state", "Initial State", INodeGraphicInfo.FIG_DBLCIRCLE, 16, 16, false); //$NON-NLS-1$ //$NON-NLS-2$
		attr = new AttributeFormalism(1,"name", IAttributeGraphicInfo.L1, true, false); //$NON-NLS-1$
		elem.addAttributeFormalism(attr);
		attr = new AttributeFormalism(2,"value", IAttributeGraphicInfo.L2, true, true); //$NON-NLS-1$
		elem.addAttributeFormalism(attr);
		attr = new AttributeFormalism(3,"initial", IAttributeGraphicInfo.NOR, true, true); //$NON-NLS-1$
		elem.addAttributeFormalism(attr);
		attr = new AttributeFormalism(4,"deadlock", false, true); //$NON-NLS-1$
		elem.addAttributeFormalism(attr);
		elem.setFormalism(graph);
		elem.setAddrIcone16("/resources/icons/initial16.png"); //$NON-NLS-1$
		elem.setAddrIcone24("/resources/icons/initial24.png"); //$NON-NLS-1$
		graph.addElementBase(elem);

		// L'etat terminal:
		elem = new NodeFormalism("terminal_state", "Terminal State", INodeGraphicInfo.FIG_CIRCLE, 16, 16, true); //$NON-NLS-1$ //$NON-NLS-2$
		attr = new AttributeFormalism(1,"name", IAttributeGraphicInfo.L1, true, false); //$NON-NLS-1$
		elem.addAttributeFormalism(attr);
		attr = new AttributeFormalism(2,"value", IAttributeGraphicInfo.L2, true, true); //$NON-NLS-1$
		elem.addAttributeFormalism(attr);
		attr = new AttributeFormalism(3,"initial", IAttributeGraphicInfo.NOR, true, true); //$NON-NLS-1$
		elem.addAttributeFormalism(attr);
		attr = new AttributeFormalism(4,"deadlock", false, true); //$NON-NLS-1$
		elem.addAttributeFormalism(attr);
		elem.setFormalism(graph);
		elem.setAddrIcone16("/resources/icons/terminal16.png"); //$NON-NLS-1$
		elem.setAddrIcone24("/resources/icons/terminal24.png"); //$NON-NLS-1$
		graph.addElementBase(elem);

		// L'etat:
		elem = new NodeFormalism("state", "State", INodeGraphicInfo.FIG_CIRCLE, 16, 16, false); //$NON-NLS-1$ //$NON-NLS-2$
		attr = new AttributeFormalism(1,"name", IAttributeGraphicInfo.L1, true, false); //$NON-NLS-1$
		elem.addAttributeFormalism(attr);
		attr = new AttributeFormalism(2,"value", IAttributeGraphicInfo.L2, true, true); //$NON-NLS-1$
		elem.addAttributeFormalism(attr);
		attr = new AttributeFormalism(3,"initial", IAttributeGraphicInfo.NOR, true, true); //$NON-NLS-1$
		elem.addAttributeFormalism(attr);
		attr = new AttributeFormalism(4,"deadlock", false, true); //$NON-NLS-1$
		elem.addAttributeFormalism(attr);
		elem.setFormalism(graph);
		elem.setAddrIcone16("/resources/icons/place16.png"); //$NON-NLS-1$
		elem.setAddrIcone24("/resources/icons/place24.png"); //$NON-NLS-1$
		graph.addElementBase(elem);

		// L'event (arc)
		elem = new ArcFormalism("event", "Event", IArcGraphicInfo.FIG_ARC_SIMPLE, 8, 8, false); //$NON-NLS-1$ //$NON-NLS-2$
		attr = new AttributeFormalism(1,"label", IAttributeGraphicInfo.NOR, true, false); //$NON-NLS-1$
		elem.addAttributeFormalism(attr);
		attr = new AttributeFormalism(2,"value", false, true); //$NON-NLS-1$
		elem.addAttributeFormalism(attr);
		elem.setFormalism(graph);
		elem.setAddrIcone16("/resources/icons/arc16.png"); //$NON-NLS-1$
		elem.setAddrIcone24("/resources/icons/arc24.png"); //$NON-NLS-1$
		graph.addElementBase(elem);

		// Pas de rules : toute association autorisee

		return graph;
	}


	/**
	 * Importe un modele en determinant son formalisme en fonction de l'extension du fichier
	 * @param fileName nom de fchier a importer
	 * @return le model adapter correspondant
	 * @throws Exception leve d'exception si le fichier n'est pas valide
	 */
	public IModelImpl importModel(String fileName) throws Exception {

		// Determination du formalism avec l'extension
		StringTokenizer file = new StringTokenizer(fileName, "."); //$NON-NLS-1$

		// Debut du nom
		String fext = file.nextToken();
		// Extension
		fext = file.nextToken();
		// Formalism
		Formalism formalism = getFormalismByExtension(fext);

		// On verifie qu'un formalisme existe bien pour cette extension
		if (formalism == null) { 
			throw new Exception(Coloane.traduction.getString("motor.formalism.FormalismManager.192")); //$NON-NLS-1$
		}

		IModel apiModel = new Model(new File(fileName));
		if (apiModel == null) {
			throw new Exception(Coloane.traduction.getString("motor.formalism.FormalismManager.193")); //$NON-NLS-1$
		}

		// On indique au modele l'identifiant maximal
		// Cette etape est importante, si on souhaite ajouter de nouveaux noeuds et arcs
		// au modele
		apiModel.setMaxId(apiModel.getMaxId());


		return new ModelImplAdapter(apiModel, formalism);
	}



	/**
	 * Export un modeladapter en model en fonction de son formalisme
	 * @param modelAdapter le model a enregistrer
	 * @param fileName nom du fichier d�sir� sans les extentions
	 * @throws Exception 
	 */

	public void exportModel(IModelImpl modelAdapter, String fileName) throws Exception {

		if (fileName.equalsIgnoreCase("") || fileName == null) { //$NON-NLS-1$
			throw new Exception(Coloane.traduction.getString("motor.formalism.FormalismManager.195")); //$NON-NLS-1$
		}

		// Extention du formalism
		String ext = modelAdapter.getFormalism().getExtension();
		if (ext == null) {
			throw new Exception(Coloane.traduction.getString("motor.formalism.FormalismManager.196")); //$NON-NLS-1$
		}
		// Creation du fichier
		FileOutputStream wr = new FileOutputStream(new File(fileName +"."+ ext));  //$NON-NLS-1$
		BufferedWriter buff = new BufferedWriter(new OutputStreamWriter(wr));

		// Traduction du modele entier
		try {
			String[] cami = modelAdapter.getGenericModel().translate();
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
	 * Retourne la liste des formalismes disponibles 
	 * @return listOfFormalism
	 */
	public ArrayList getListOfFormalisms() {
		return listOfFormalisms;
	}
}
