package fr.lip6.move.coloane.ui.model;

import fr.lip6.move.coloane.exceptions.BuildException;
import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.coloane.motor.formalism.ElementBase;

import java.util.Collection;
import java.util.List;

/**
 * Interface pour les noeuds du modele.<br>
 * Cette interface fournie des methodes principales pour un noeud du modele, y compris:
 * <ul>
 * 	<li>Reference du modele qui lui contient
 * 	<li>L'arbe de menu contexte
 * 	<li>Information graphique (la figure a afficher, la hauteur, largeur ...)
 * 	<li>Methodes de gestion ses connexions
 * </ul>
 * <p>
 * La classe qui implemente cette interface doit heriter de la classe AbstractModelElement pour avoir des fonctionalites de proprietes
 * </p>
 */

public interface INodeImpl {

	/** ID pour la propriete lors d'un changement des arcs sortants */
	String SOURCE_ARCS_PROP = "Node.OutputArc";

	/** ID pour la propriete lors d'un changement des arcs entants */
	String TARGET_ARCS_PROP = "Node.InputArc";

	/** ID pour la propriete lorsqu'un changement de la position */
	String LOCATION_PROP = "Node.Location";

	/** ID pour la propriete lorsqu'un changement de la valeur */
	String VALUE_PROP = "Node.ValueUpdate";

	/** ID pour la propriete lorsque le noeud est selectionne */
	String SELECT_PROP = "Node.SelectUpdate";

	/** ID pour la propriete lorsque le noeud est deselectionne */
	String UNSELECT_PROP = "Node.UnSelectUpdate";

	/** ID pour la propriete lorsque le noeud est selectionne */
	String SPECIAL_PROP = "Node.SpecialUpdate";

	/** ID pour la propriete lorsque le noeud est deselectionne */
	String UNSPECIAL_PROP = "Node.UnSpecialUpdate";

	/**
	 * Ajouter un arc entrant.
	 * L'arc doit etre rajoute dans la liste des arcs entrants du noeud adapte
	 * L'arc doit etre ajout dans la liste des arcs entrants du noeud generique
	 * @param arc L'arc a ajouter
	 * @throws BuildException
	 * @see fr.lip6.move.coloane.ui.model.IArcImpl
	 */
	void addInputArc(IArcImpl arc) throws BuildException;

	/**
	 * Ajouter un arc sortant.
	 * L'arc doit etre rajoute dans la liste des arcs sortants du noeud adapte
	 * L'arc doit etre ajout dans la liste des arcs sortants du noeud generique
	 * @param arc L'arc a ajouter
	 * @see fr.lip6.move.coloane.ui.model.IArcImpl
	 * @throws BuildException
	 */
	void addOutputArc(IArcImpl arc) throws BuildException;

	/**
	 * Suppression d'un arc entrant ou sortant du noeud
	 * Cette methode est appelee par l'arc adapte lors de sa deconnexion
	 * @param arc L'arc a supprimer
	 * @see fr.lip6.move.coloane.ui.model.IArcImpl
	 */
	void removeArc(IArcImpl arc) throws ModelException;

	/**
	 * Retourne la liste des arcs sortants du noeud
	 * @return List La liste
	 */
	List<IArcImpl> getSourceArcs();

	/**
	 * Retourner la liste des arcs entrants du noeud
	 * @return List La liste
	 */
	List<IArcImpl> getTargetArcs();

	/**
	 * Recupere l'ID du noeud generique
	 * Evite les appels direct au noeud generique
	 * @return id L'identifiant du noeud
	 */
	int getId();

	/**
	 * Retourne tous les attributs de l'objet pour qu'ils soient affiches
	 * @return La liste des attributs
	 */
	List<IElement> getAttributes();

	/**
	 * Associe le modele augmente au noeud
	 * @param modelAdapter
	 */
	void setModelAdapter(IModelImpl modelAdapter);

	/**
	 * Retourne le modele augmente auquel appartient le noeud
	 * @return Le modele augmente
	 */
	IModelImpl getModelAdapter();

	/**
	 * Retourne l'element de base du noeud
	 * @return L'element de base du noeud
	 */
	ElementBase getElementBase();

	/**
	 * Retourne la valeur de l'attribut designe par le parametre
	 * @param attribute La chaine caracterisant l'attribut qu'on cible
	 * @return La valeur de l'attribut designe
	 */
	String getNodeAttributeValue(String attribute);

	/**
	 * Retourne le noeud generique
	 * @return Node Le noeud generique
	 * @see fr.lip6.move.coloane.interfaces.model.INode
	 */
	INode getGenericNode();

	/**
	 * Retourne les informations graphiques du noeud
	 * @return INodeGraphicInfo
	 * @see INodeGraphicInfo
	 */
	INodeGraphicInfo getGraphicInfo();

	/**
	 * Retourner le menu contextuel associe au noeud
	 * @return Collection
	 */
	Collection getContextMenus();

	/**
	 * Demande la mise en valeur du noeud suite au retour de service
	 * @param state
	 */
	void setSpecial(boolean state);

	/**
	 * DEmande la mise en valeur du noeud suite a la selection d'un des objets referents
	 * @param state
	 */
	void setSelect(boolean state);

	/**
	 * Demande la mise en valeur des attributs attaches a l'objet
	 * @param light Epaisseur de la mise en valeur (survol = light, selection = heavy)
	 * @param state Selection / Deselection
	 */
	void setAttributesSelected(boolean light, boolean state);

	/**
	 * Positionne tous les attributs attaches a ce noeud en fonction du deplacement du noeud lui-meme
	 * @param deltaX Deplacement horizontal
	 * @param deltaY Deplacement vertical
	 */
	void updateAttributesPosition(int deltaX, int deltaY);

	/**
	 * Lors du deplacement d'un noeud, il est necessaire de faire suivre les attributs
	 * attaches aux arcs sortant et entrants du noeud en question
	 */
	void updateArcAttributesPosition();

}
