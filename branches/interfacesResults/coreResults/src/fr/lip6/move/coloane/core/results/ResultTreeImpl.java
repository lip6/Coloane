package fr.lip6.move.coloane.core.results;

import fr.lip6.move.coloane.core.model.CoreTipModel;
import fr.lip6.move.coloane.core.model.interfaces.ICoreTip;
import fr.lip6.move.coloane.core.motor.session.ISessionManager;
import fr.lip6.move.coloane.core.motor.session.SessionManager;
import fr.lip6.move.coloane.interfaces.model.IElement;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.objects.result.ITip;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.logging.Logger;

/**
 * Arbre de résultat, à voir comme un tableau avec :
 * <ul>
 *   <li>l'arborescence décris les lignes</li>
 *   <li>une liste d'éléments pour les colonnes</li>
 * </ul>
 */
public class ResultTreeImpl extends Observable implements IResultTree {
	/** Le logger pour la classe */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** Liste vide non modifiable */
	private static final List<ICoreTip> EMPTY_TIPS_LIST = Collections.unmodifiableList(new ArrayList<ICoreTip>(0));

	private String serviceName;

	private IResultTree parent;
	private List<IResultTree> children;
	private List<Object> elements;
	
	/** Liste des attributs à mettre en valeur */
	private Map<Integer, List<String>> attributesOutline;
	
	private final List<Integer> highlights;
	private List<ICoreTip> tips = EMPTY_TIPS_LIST;

	/**
	 * Le gestionnaire de session.<br>
	 * Doit être renseigné au moins une fois auprès du père
	 * @see #setSessionManager(ISessionManager)
	 */
	private ISessionManager sessionManager = null;


	/**
	 * Constructeur d'un sous-arbre de resultats avec une liste d'objets à mettre en valeur
	 * @param toHighlight La liste d'objets à mettre en valeur
	 * @param elements Les elements composant le resultat
	 */
	public ResultTreeImpl(List<Integer> toHighlight, String...elements) {
		// Element a mettre en valeur lors de la selection de cet arbre (feuille) de resultat
		highlights = new ArrayList<Integer>();
		
		this.attributesOutline = new HashMap<Integer, List<String>>();
		
		if (toHighlight != null) {
			this.highlights.addAll(toHighlight);
		}

		// Description des enfants du sous arbre
		children = new ArrayList<IResultTree>();
		this.elements = new ArrayList<Object>();
		for (String element : elements) {
			this.elements.add(element);
		}
		try {
			this.sessionManager = this.getSessionManager();
			if (this.sessionManager != null) {
				this.addObserver(sessionManager.getCurrentSession().getServiceResults());
			}
		} catch (NullPointerException e) {
			LOGGER.warning("Erreur dans l'attachement de la liste de resultats a la session"); //$NON-NLS-1$
		}
	}

	/**
	 * Constructeur d'un sous-arbre de resultats avec un objet a mettre en valeur
	 * @param toHighlight L'identifiant de l'objet a mettre en valeur
	 * @param elements Les elements composant le resultat
	 */
	public ResultTreeImpl(int toHighlight, String... elements) {
		this(null, elements);
		this.addHighlighted(toHighlight);
	}

	/**
	 * Constructeur d'un sous-arbre de resultats sans mise en valeur particulière
	 * @param elements Les éléments (colonnes) composant le résultat
	 */
	public ResultTreeImpl(String... elements) {
		this(null, elements);
	}

	/** {@inheritDoc} */
	public final void setSessionManager(ISessionManager sessionManager) {
		this.sessionManager = sessionManager;
	}

	/** {@inheritDoc} */
	public final ISessionManager getSessionManager() {
		if (this.sessionManager != null) {
			return this.sessionManager;
		}
		// Si le noeud est un fils... On demande au pere quel est le gestionnaire de sessions
		if (this.parent != null) {
			this.sessionManager = parent.getSessionManager();
			return this.sessionManager;
		}
		// Si aucun resultat : null
		return null;
	}

	/** {@inheritDoc} */
	public final IResultTree getParent() {
		return parent;
	}

	/** {@inheritDoc} */
	public final void setParent(IResultTree parent) {
		this.parent = parent;
	}

	/** {@inheritDoc} */
	public final List<IResultTree> getChildren() {
		return children;
	}

	/** {@inheritDoc} */
	public final void addChild(IResultTree child) {
		children.add(child);
		child.setParent(this);
	}

	/** {@inheritDoc} */
	public final List<Object> getElement() {
		return elements;
	}


	/** {@inheritDoc} */
	public final void addHighlighted(int... toHighlight) {
		for (Integer id : toHighlight) {
			this.highlights.add(id);
		}
	}

	/** {@inheritDoc} */
	public final List<Integer> getHighlighted() {
		return this.highlights;
	}

	/** {@inheritDoc} */
	public final void remove() {
		// Si le parent du noeud est un ResultTreeList, ce noeud est donc le noeud racine du résultat.
		// Il faut donc enlever le résultat du ResultTreeList
		if (this.parent instanceof ResultTreeList) {
			((ResultTreeList)parent).remove(serviceName);
			setChanged();
			notifyObservers();
		} else {
			// Sinon le parent est un ResultTreeImpl, on cherche donc le noeud à enlever dans son père et on le supprime
			for (IResultTree resultTree : this.parent.getChildren()) {
				if (resultTree.equals(this)) {
					this.parent.getChildren().remove(this);
					setChanged();
					notifyObservers();
					return;
				}
			}
		}
	}

	/**
	 * Champ initialisé par le ResultTreeList après la création par la méthode build du IReport.
	 * @return nom du service
	 */
	public final String getServiceName() {
		return serviceName;
	}

	/**
	 * Champ initialisé par le ResultTreeList après la création par la méthode build du IReport.
	 * @param serviceName nom du service
	 */
	public final void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	/** {@inheritDoc} */
	public final List<ICoreTip> getTips() {
		return Collections.unmodifiableList(tips);
	}

	/** {@inheritDoc} */
	public final List<ICoreTip> getTips(List<Integer> haveTips) {
		List<ICoreTip> toReturn = new ArrayList<ICoreTip>();
		List<ICoreTip> toBrowse = new ArrayList<ICoreTip>();

		toBrowse.addAll(parent.getTips());
		toBrowse.addAll(tips);

		for (ICoreTip tip : toBrowse) {
			for (int num : haveTips) {
				if (num == tip.getIdObject()) {
					toReturn.add(tip);
					break;
				}
			}
		}
		return toReturn;
	}

	/** {@inheritDoc} */
	// TODO : Expliquer
	public void setTips(Map<Integer, List<ITip>> map, Integer... objectIds) {
		IGraph currentGraph = SessionManager.getInstance().getCurrentSession().getGraph();
		List<ICoreTip> coreTips = new ArrayList<ICoreTip>();
		
		
		for (Integer id : objectIds) {
			IElement element = currentGraph.getObject(id);
			if (element != null) {
				List<ITip> listTip = map.get(id);
				if (listTip != null) {
					for (ITip tip : listTip) {
						coreTips.add(new CoreTipModel(tip));
					}
					
				}
			}
		}
		
		if (coreTips != null) {
			this.tips = coreTips;
		} else {
			this.tips = EMPTY_TIPS_LIST;
		}
	}

	/**
	 * Ajoute une liste d'attributs à mettre en surbrillance.<br>
	 * Avant de les rajouter, on vérifie que ces attributs existent bien dans le graphe courant.
	 * @param map L'identifiant de l'objet à qui appartient l'attribut
	 * 
	 * TODO : expliquer le code
	 */
	public void addAttributesOutline(Map<Integer, List<String>> map, Integer... objectIds) {
		IGraph currentGraph = SessionManager.getInstance().getCurrentSession().getGraph();
		
		for (int id : objectIds) {
			IElement element = currentGraph.getObject(id);
			if (element != null) {
				List<String> listAttribute = map.get(id);
				if (listAttribute != null) {
					for (String attribute : listAttribute) {
						if (element.getAttribute(attribute) != null) {
							if (this.attributesOutline.containsKey(id)) {
								this.attributesOutline.get(id).add(attribute);
							} else {
								List<String> first = new ArrayList<String>();
								first.add(attribute);
								this.attributesOutline.put(id, first);
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public final Map<Integer, List<String>> getAttributesOutline() {
		return attributesOutline;
	}
}
