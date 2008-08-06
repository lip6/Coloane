package fr.lip6.move.coloane.apiws.objects.result;

import fr.lip6.move.coloane.interfaces.objects.result.ISubResult;
import fr.lip6.move.wrapper.ws.WrapperStub.RService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Cette classe définie un sous résultat
 */
public class SubResultImpl implements ISubResult {

	private String name;

	private Map<Integer, List<String>> attributesOutline;

	private List<Integer> objectsDesignation;

	private List<Integer> objectsOutline;

	private List<String> textualResults;

	private List<ISubResult> children;

	private int type;

	/**
	 * Constructeur
	 * @param result le resultat reçu de la part du wrapper
	 */
	public SubResultImpl(RService result) {
		this.textualResults = new ArrayList<String>();
		if (result.getTxts().getTxts() != null) {
			for (int i = 0; i < result.getTxts().getTxts().length; i++) {
				this.textualResults.add(result.getTxts().getTxts()[i]);
			}
		}

		this.objectsDesignation = new ArrayList<Integer>();
		if (result.getObjs().getObjs() != null) {
			for (int i = 0; i < result.getObjs().getObjs().length; i++) {
				this.objectsDesignation.add(result.getObjs().getObjs()[i]);
			}
		}

		this.objectsOutline = new ArrayList<Integer>();
		if (result.getSubLightObject() != null) {
			for (int i = 0; i < result.getSubLightObject().length; i++) {
				// Si le premier element est null c'est que le tableau est vide cela est dù à la gestion special des tableau null d'axis
				if (result.getSubLightObject()[i] == null) {
					break;
				}
				this.objectsOutline.add((Integer) result.getSubLightObject()[i].getId());
			}
		}

		this.attributesOutline = new HashMap<Integer, List<String>>();
		if (result.getSubLightAttribute() != null) {
			// On parcour la liste des attribut mis en valeurs
			for (int i = 0; i < result.getSubLightAttribute().length; i++) {
				// Si le premier element est null c'est que le tableau est vide cela est dù à la gestion special des tableau null d'axis
				if (result.getSubLightAttribute()[i] == null) {
					break;
				}
				// Si l'identifiant d'un object sur lequel s'applique un attribut est déjà présent dans la hashMap
				// alors on ajout simplement cette attribut dans la liste des attributs
				if (this.attributesOutline.containsKey((Integer) result.getSubLightAttribute()[i].getId())) {
					this.attributesOutline.get((Integer) result.getSubLightAttribute()[i].getId()).add(result.getSubLightAttribute()[i].getName());
				} else { // Sinon on ajout l'identifiant dans la hashMap et on ajout l'attribut dans la liste des attributs créée
					this.attributesOutline.put((Integer) result.getSubLightAttribute()[i].getId(), new ArrayList<String>());
					this.attributesOutline.get((Integer) result.getSubLightAttribute()[i].getId()).add(result.getSubLightAttribute()[i].getName());
				}
			}
		}

		this.children = new ArrayList<ISubResult>();

		this.type = 0;
	}

	/**
	 * Constructeur
	 * @param ensemble l'ensemble des sous-resultat reçu de la part du wrapper
	 *//*
	public SubResultImpl(Ensemble ensemble) {

		this.name = ensemble.getName();

		this.textualResults = new ArrayList<String>();
		if (ensemble.getTxts().getTxts() != null) {
			for (int i = 0; i < ensemble.getTxts().getTxts().length; i++) {
				this.textualResults.add(ensemble.getTxts().getTxts()[i]);
			}
		}

		this.objectsDesignation = new ArrayList<Integer>();
		if (ensemble.getObjs().getObjs() != null) {
			for (int i = 0; i < ensemble.getObjs().getObjs().length; i++) {
				this.objectsDesignation.add(ensemble.getObjs().getObjs()[i]);
			}
		}

		this.objectsOutline = new ArrayList<Integer>();
		if (ensemble.getSubLightObject() != null) {
			for (int i = 0; i < ensemble.getSubLightObject().length; i++) {
				// Si le premier element est null c'est que le tableau est vide cela est dù à la gestion special des tableau null d'axis
				if (ensemble.getSubLightObject()[i] == null) {
					break;
				}
				this.objectsOutline.add((Integer) ensemble.getSubLightObject()[i].getId());
			}
		}

		this.attributesOutline = new HashMap<Integer, List<String>>();
		if (ensemble.getSubLightAttribute() != null) {
			// On parcour la liste des attribut mis en valeurs
			for (int i = 0; i < ensemble.getSubLightAttribute().length; i++) {
				// Si le premier element est null c'est que le tableau est vide cela est dù à la gestion special des tableau null d'axis
				if (ensemble.getSubLightAttribute()[i] == null) {
					break;
				}
				// Si l'identifiant d'un object sur lequel s'applique un attribut est déjà présent dans la hashMap
				// alors on ajout simplement cette attribut dans la liste des attributs
				if (this.attributesOutline.containsKey((Integer) ensemble.getSubLightAttribute()[i].getId())) {
					this.attributesOutline.get((Integer) ensemble.getSubLightAttribute()[i].getId()).add(ensemble.getSubLightAttribute()[i].getName());
				} else { // Sinon on ajout l'identifiant dans la hashMap et on ajout l'attribut dans la liste des attributs créée
					this.attributesOutline.put((Integer) ensemble.getSubLightAttribute()[i].getId(), new ArrayList<String>());
					this.attributesOutline.get((Integer) ensemble.getSubLightAttribute()[i].getId()).add(ensemble.getSubLightAttribute()[i].getName());
				}
			}
		}

		this.children = new ArrayList<ISubResult>();
		if (ensemble.getEnsembles() != null) {
			for (int i = 0; i < ensemble.getEnsembles().length; i++) {
				// Si le premier element est null c'est que le tableau est vide cela est dù à la gestion special des tableau null d'axis
				if (ensemble.getEnsembles()[i] == null) {
					break;
				}
				this.children.add(new SubResultImpl(ensemble.getEnsembles()[i]));
			}
		}

		this.type = ensemble.getType();

	}*/

	/**
	 * {@inheritDoc}
	 */
	public final Map<Integer, List<String>> getAttributesOutline() {
		return attributesOutline;
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
	public final String getName() {
		return name;
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
	public final int getType() {
		return type;
	}

}
