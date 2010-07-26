package fr.lip6.move.coloane.core.ui.panels;

import fr.lip6.move.coloane.core.model.CoreTipModel;
import fr.lip6.move.coloane.core.model.interfaces.ICoreTip;
import fr.lip6.move.coloane.core.results.ResultManager;
import fr.lip6.move.coloane.core.session.ISessionManager;
import fr.lip6.move.coloane.core.session.SessionManager;
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
 * Describe a tree of results.<br>
 * The first level of this tree is used to show useful information about service that provides those results.<br>
 * You can use details 
 *
 * @author Jean-Baptiste Voron
 * @author Clément Demoulins
 * @author Florian David
 */
public class ResultTreeImpl extends Observable implements IResultTree {
	/** The logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/** Empty list of tips */
	private static final List<ICoreTip> EMPTY_TIPS_LIST = Collections.unmodifiableList(new ArrayList<ICoreTip>(0));

	/** Service name associated with this list of results */
	private String serviceName;

	/** Parent, if it exists */
	private IResultTree parent;
	
	/** All children */
	private List<IResultTree> children;
	
	/** Details associated with this result */
	private List<Object> details;
	
	/** Attributes to highlight */
	private Map<Integer, List<String>> attributesOutline;
	
	/** Objects to highlight */
	private final List<Integer> highlights;
	
	/** Tip list */
	private List<ICoreTip> tips = EMPTY_TIPS_LIST;

	/**
	 * Session Manager<br>
	 * Must be set at least once.
	 * @see #setSessionManager(ISessionManager)
	 */
	private ISessionManager sessionManager = null;

	/**
	 * Build a results tree with a <i>list</i> of objects to highlight
	 * @param toHighlight The list of objects
	 * @param details Some details about the service (each one will be shown in a single column)
	 */
	public ResultTreeImpl(List<Integer> toHighlight, String...details) {
		this.highlights = new ArrayList<Integer>();
		this.attributesOutline = new HashMap<Integer, List<String>>();

		// Contribute to the list of highlight objects
		if (toHighlight != null) {
			this.highlights.addAll(toHighlight);
		}

		// Prepare the list of result children
		children = new ArrayList<IResultTree>();
		
		// Contribute to result columns (for details)
		this.details = new ArrayList<Object>();
		for (String element : details) {
			this.details.add(element);
		}
		
		try {
			this.sessionManager = this.getSessionManager();
			if (this.sessionManager != null) {
				this.addObserver(sessionManager.getCurrentSession().getResultManager());
			}
		} catch (NullPointerException e) {
			LOGGER.warning("Erreur dans l'attachement de la liste de resultats a la session"); //$NON-NLS-1$
		}
	}

	/**
	 * Build a results tree with a unique object to highlight
	 * @param toHighlight The object to highlight
	 * @param details Details about the service (columns)
	 */
	public ResultTreeImpl(int toHighlight, String... details) {
		this(null, details);
		this.addHighlighted(toHighlight);
	}

	/**
	 * Build a results tree without any object to highlight but details about the service
	 * @param details Details about the service
	 */
	public ResultTreeImpl(String... details) {
		this(null, details);
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
		
		// If this result tree is the child of another result tree, we ask the parent for the session manager
		if (this.parent != null) {
			this.sessionManager = parent.getSessionManager();
			return this.sessionManager;
		}

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
		return details;
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
		// If the parent is the result manager itself, we remove the results tree associated to the service
		if (this.parent instanceof ResultManager) {
			((ResultManager) parent).remove(serviceName);
			setChanged();
			notifyObservers();
		// Else, we remove this resultTree
		} else {
			this.parent.getChildren().remove(this);
		}
	}

	/**
	 * @return The name of the service for which results are presented
	 */
	public final String getServiceName() {
		return serviceName;
	}

	/**
	 * Set the name of the service for which results are presented
	 * @param serviceName The service name
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
	public final void setTips(Map<Integer, List<ITip>> map, Integer... objectIds) {
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
	 * Set a list of attribute to highlight.<br>
	 * Before adding them to the definitive list, we check that they exist and are defined in the current graph.
	 * @param map a map that contains the ids and the element description to highlight
	 */
	public final void addAttributesOutline(Map<Integer, List<String>> map) {
		IGraph currentGraph = SessionManager.getInstance().getCurrentSession().getGraph();

		// For each object ID,
		for (int id : map.keySet()) {
			// We get the associated element in the graph.
			IElement element = currentGraph.getObject(id);
			// If the element is not null (so if the element is present in the graph),
			if (element != null) {
				// we get it attributes list in the map.
				List<String> listAttribute = map.get(id);
				if (listAttribute != null) {
					// For each attribute of this model object, we check if this attribute exists for the graph element.
					for (String attribute : listAttribute) {
						// If it exists,
						if (element.getAttribute(attribute) != null) {
							// we add the id of the graph element with the attribute name in the attributesOutline map.
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
