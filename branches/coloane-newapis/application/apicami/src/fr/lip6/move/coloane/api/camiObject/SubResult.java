package fr.lip6.move.coloane.api.camiObject;

import fr.lip6.move.coloane.interfaces.objects.result.ISubResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Cette classe contient toutes les informations qui définissent un résultat
 *
 * @author Jean-Baptiste Voron
 */
public class SubResult implements ISubResult {

	/** Les sous-résultats par rapport à ce résultat */
	private List<ISubResult> children;

	private List<Integer> objectsDesignation;

	private List<Integer> objectsOutline;

	private List<String> textualResults;

	private Map<Integer, List<String>> attributesOutline;

	private String name;

	private int type;

	/**
	 * Constructeur d'un ensemble de résultats
	 * @param name Le nom de l'ensemble de résultats
	 * @param type Le type de cet ensemble
	 */
	public SubResult(String name, int type) {
		this.name = name;
		this.type = type;
		this.children = new ArrayList<ISubResult>();
		this.objectsDesignation = new ArrayList<Integer>();
		this.objectsOutline = new ArrayList<Integer>();
		this.attributesOutline = new HashMap<Integer, List<String>>();
		this.textualResults = new ArrayList<String>();
	}

	/**
	 * Ajoute un attribut dans la liste de ceux qui doivent être mis en valeur
	 * @param objectId L'identifiant de l'objet à qui appartient l'attribut
	 * @param attributeName Le nom de l'attribut à mettre en valeur
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
	 * Ajoute l'identifiant d'un objet à désigner
	 * @param id L'identifiant qui doit être ajouté dans la liste
	 */
	public final void addObjectDesignation(Integer id) {
		this.objectsDesignation.add(id);
	}

	/**
	 * Ajoute l'identifiant d'un objet à mettre en valeur
	 * @param id L'identifiant qui doit être ajouté dans la liste
	 */
	public final void addObjectOutline(Integer id) {
		this.objectsDesignation.add(id);
	}

	/**
	 * Ajoute un résultat textuel à la liste
	 * @param result Le résultat qui doit être ajouté dans la liste
	 */
	public final void addTextualResult(String result) {
		this.textualResults.add(result);
	}

	/**
	 * {@inheritDoc}
	 */
	public final List<ISubResult> getChildren() {
		return children;
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
	public final List<String> getTextualResults() {
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
	public final String getName() {
		return name;
	}

	/**
	 * {@inheritDoc}
	 */
	public final int getType() {
		return type;
	}
}
