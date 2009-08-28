package fr.lip6.move.coloane.tools.stats.results;

import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.objects.result.ISubResult;
import fr.lip6.move.coloane.interfaces.objects.result.ITip;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class define a sub-result.<br>
 * A sub-result is always contained in a result or a sub-result.
 * Other sub-results can be added to ensure a tree structure.<br><br>
 *
 * Cette classe contient toutes les informations qui définissent un sous-résultat<br>
 * Un sous résultat est forcément contenu dans un résultat ou un sous-résultat.
 * D'autres sous-résultats peuvent le composer pour assurer une structure arborescente.
 * 
 * @author Jean-Baptiste Voron
 * @author Florian David
 */
public class SubResult implements ISubResult {
	/** Sub-result name. */
	private String subResultName;

	/** Information about the sub-result. */
	private String information;

	/** Sub-results included in this sub-result. */
	private List<ISubResult> subResults;

	/** Tips list */
	private Map<Integer, List<ITip>> tips;

	/** 
	 * 	Liste d'objets pouvant être mis en surbrillance dans le modèle.<br>
	 *  Ces objets ne seront pas affichés dans le menu du sous-résultat.
	 */
	// TODO : traduire en anglais
	private List<Integer> objectsDesignation;

	/** 
	 * Liste d'objets pouvant être mis en surbrillance dans le modèle.<br>
	 * Ces objets seront affichés dans le menu du sous-résultat.
	 */
	// TODO : traduire en anglais
	private List<Integer> objectsOutline;

	/** Liste des attributs d'objets du graphe pouvant être mis en surbrillance dans le modèle. */
	// TODO : traduire en anglais
	private Map<Integer, List<String>> attributesOutline;

	/** List of results in the form of text. */
	private List<List<String>> textualResults;

	/** Type */
	private Integer type;

	/**
	 * Constructs an empty sub-result.
	 * 
	 * @param subResultName Name of the sub-result.
	 */
	public SubResult(String subResultName) {
		this(subResultName, ""); //$NON-NLS-1$
	}

	/**
	 * Constructs an empty sub-result with an information about it.
	 * 
	 * @param subResultName Name of the sub-result.
	 * @param information Information about the sub-result.
	 */
	public SubResult(String subResultName, String information) {
		this.subResultName = subResultName;
		this.information = information;
		this.subResults = new ArrayList<ISubResult>();
		this.objectsDesignation = new ArrayList<Integer>();
		this.objectsOutline = new ArrayList<Integer>();
		this.attributesOutline = new HashMap<Integer, List<String>>();
		this.textualResults = new ArrayList<List<String>>();
		this.tips = new HashMap<Integer, List<ITip>>();
		this.type = 0;
	}


	/**
	 * Ajoute une information à la liste des informations renvoyées par la plate-forme
	 * @param tip L'information à ajouter à la liste
	 */
	public final void addTip(ITip tip) {
		if (this.tips.containsKey(tip.getIdObject())) {
			(this.tips.get(tip.getIdObject())).add(tip);
		} else {
			List<ITip> list = new ArrayList<ITip>();
			list.add(tip);
			this.tips.put(tip.getIdObject(), list);
		}
	}

	/**
	 * Add a tip in the subResult.
	 * @param object the element in which the tip will be linked.
	 * @param name the tip name.
	 * @param value the tip value.
	 */
	public final void addTip(IElement object, String name, String value) {
		if (object != null) {
			this.addTip(new Tip(object.getId(), name, value));
		}
	}
	/**
	 * TODO : A traduire
	 * @return la liste d'informations associées au résultat
	 */
	public final Map<Integer, List<ITip>> getTips() {
		return this.tips;
	}


	/**
	 * Add a result in the form of text in the list.
	 * Ajoute un résultat sous forme de texte dans la liste.
	 * 
	 * @param result Le résultat textuel qui doit être ajouté dans la liste.<br>
	 * Celui-ci est stocké sous forme de tableau pour être affiché dans les colonnes de la vue.
	 * @param result the textual result to be added to the list.<br>
	 * It's stored in an array for being displayed in the columns of the view.
	 */
	public final void addTextualResult(String... result) {
		// emptyList permet de savoir si le tableau construit est constitué uniquement de chaînes vides
		// emptyList allow us to know is the constructed array is only constituted by empty string
		boolean emptyList = true;
		List<String> array = new ArrayList<String>(result.length);
		for (int i = 0; i < result.length; i++) {
			array.add(result[i]);
			emptyList = emptyList && ("".equals(result[i])); //$NON-NLS-1$
		}

		if (emptyList) {
			// Si toutes chaînes sont vides, on renvoie un tableau vide avec "No Result" pour l'indiquer à l'utilisateur
			// If all strings are empty, we return instead an array with "No result" inside to indicate to the user
			array.clear();
			array.add("No result"); //$NON-NLS-1$
			this.textualResults.add(array);
		} else {
			this.textualResults.add(array);
		}
	}
	
	
	/**
	 * Add an attribute (associated with a graph object) in the list of those who will be able to be highlighted in the model.
	 * Ajoute un attribut (associé à un objet du graphe) dans la liste de ceux qui doivent être mis en surbrillance dans le modèle.
	 * 
	 * @param objectId The object ID to whom belongs the attribute.
	 * @param attributeName The name of the attribute to be highlighted.
	 */
	public final void addAttributeOutline(Integer objectId, String attributeName) {
		if (this.attributesOutline.containsKey(objectId)) {
			this.attributesOutline.get(objectId).add(attributeName);
		} else {
			List<String> first = new ArrayList<String>();
			first.add(attributeName);
			this.attributesOutline.put(objectId, first);
		}
	}

	/**
	 * Add an attribute (associated with a graph object) in the list of those who will be able to be highlighted in the model.
	 * Ajoute un attribut (associé à un objet du graphe) dans la liste de ceux qui doivent être mis en surbrillance dans le modèle.
	 * 
	 * @param object The object to whom belongs the attribute.
	 * @param attributeName The name of the attribute to be highlighted.
	 */
	public final void addAttributeOutline(IElement object, String attributeName) {
		if (object != null) {
			addAttributeOutline(object.getId(), attributeName);
		}
	}
	
	/**
	 * Add an object which will be able to be highlighted in the model but won't be added to the menu.
	 * Ajoute un objet qui pourra être mis en surbrillance dans le modèle mais qui ne sera pas ajouté au menu.
	 * 
	 * @param objectId The object ID to be added to the list.
	 */
	public final void addObjectDesignation(Integer objectId) {
		this.objectsDesignation.add(objectId);
	}
	
	/**
	 * Add an object which will be able to be highlighted in the model but won't be added to the menu.
	 * Ajoute un objet qui pourra être mis en surbrillance dans le modèle mais qui ne sera pas ajouté au menu.
	 * 
	 * @param object The object to be added to the list.
	 */
	public final void addObjectDesignation(IElement object) {
		if (object != null) {
			this.objectsDesignation.add(object.getId());
		}
	}

	/**
	 * Add an object which will be able to be highlighted in the model and will be added to the menu.
	 * Ajoute un objet qui pourra être mis en surbrillance dans le modèle et qui sera ajouté au menu.
	 * 
	 * @param objectId The object ID to be added to the list.
	 */
	public final void addObjectOutline(Integer objectId) {
		this.objectsOutline.add(objectId);
	}

	/**
	 * Add an object which will be able to be highlighted in the model and will be added to the menu.
	 * Ajoute un objet qui pourra être mis en surbrillance dans le modèle et qui sera ajouté au menu.
	 * 
	 * @param object The object to be added to the list.
	 */
	public final void addObjectOutline(IElement object) {
		if (object != null) {
			this.objectsOutline.add(object.getId());
		}
	}

	/**
	 * Add a sub-result in the sub-result list.
	 * Ajoute un sous-resultat à la liste des sous-résultats.
	 * 
	 * @param subResult The sub-result added to the list.
	 */
	public final void addSubResult(ISubResult subResult) {
		this.subResults.add(subResult);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final List<ISubResult> getSubResults() {
		return this.subResults;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final List<ISubResult> getChildren() {
		return this.subResults;
	}

	/**
	 * {@inheritDoc}
	 */
	public final List<Integer> getObjectsDesignation() {
		return objectsDesignation;
	}

	/**
	 * {@inheritDoc}
	 */
	public final List<Integer> getObjectsOutline() {
		return objectsOutline;
	}

	/**
	 * {@inheritDoc}
	 */
	public final List<List<String>> getTextualResults() {
		return textualResults;
	}

	/**
	 * {@inheritDoc}
	 */
	public final Map<Integer, List<String>> getAttributesOutline() {
		return attributesOutline;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getSubResultName() {
		return this.subResultName;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getName() {
		return this.subResultName;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getInformation() {
		return information;
	}

	/**
	 * {@inheritDoc}
	 */
	public final int getType() {
		return this.type;
	}

	/**
	 * Set the subresult type.
	 * @param type the type.
	 */
	public final void setType(Integer type) {
		this.type = type;
	}
}
