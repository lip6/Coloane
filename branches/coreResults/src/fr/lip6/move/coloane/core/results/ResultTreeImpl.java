package fr.lip6.move.coloane.core.results;

import fr.lip6.move.coloane.core.model.interfaces.ICoreTip;
import fr.lip6.move.coloane.core.motor.session.ISessionManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
		if (serviceName != null) {
			this.sessionManager = this.getSessionManager();
			if (this.sessionManager != null) {
				this.sessionManager.getCurrentSession().getServiceResults().remove(serviceName);
				setChanged();
				notifyObservers();
			}
		} else {
			parent.remove();
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
	public final void setTips(List<ICoreTip> tips) {
		if (tips != null) {
			this.tips = new ArrayList<ICoreTip>(tips);
		} else {
			this.tips = EMPTY_TIPS_LIST;
		}
	}
}
