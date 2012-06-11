package fr.lip6.move.coloane.core.ui.panels;

import fr.lip6.move.coloane.core.model.interfaces.ICoreTip;
import fr.lip6.move.coloane.core.results.ResultManager;
import fr.lip6.move.coloane.core.session.ISessionManager;
import fr.lip6.move.coloane.interfaces.objects.result.ITip;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Clément Démoulins
 */
public final class MultiResultManager implements IResultTree {

	private ResultManager global;
	private ResultManager local;

	/**
	 * Create a result manager from the global and a local (from the current session) result manager
	 * @param global Global result manager
	 * @param local Local result manager
	 */
	public MultiResultManager(ResultManager global, ResultManager local) {
		this.global = global;
		this.local = local;
	}

	/** {@inheritDoc} */
	public IResultTree getParent() {
		return null;
	}

	/** {@inheritDoc} */
	public void setParent(IResultTree parent) { }

	/** {@inheritDoc} */
	public List<IResultTree> getChildren() {
		List<IResultTree> children = new ArrayList<IResultTree>(global.getChildren().size() + local.getChildren().size());
		children.addAll(global.getChildren());
		children.addAll(local.getChildren());
		return children;
	}

	/** {@inheritDoc} */
	public void addChild(IResultTree child) {
		throw new UnsupportedOperationException();
	}

	/** {@inheritDoc} */
	public List<Object> getElement() {
		return new ArrayList<Object>(getChildren());
	}

	/** {@inheritDoc} */
	public List<Integer> getHighlighted() {
		return local.getHighlighted();
	}

	/** {@inheritDoc} */
	public void addHighlighted(int... toHighlight) {
		local.addHighlighted(toHighlight);
	}

	/** {@inheritDoc} */
	public Map<Integer, List<String>> getAttributesOutline() {
		return null;
	}

	/** {@inheritDoc} */
	public void remove() { }

	/** {@inheritDoc} */
	public void setSessionManager(ISessionManager sessionManager) {
		global.setSessionManager(sessionManager);
	}

	/** {@inheritDoc} */
	public ISessionManager getSessionManager() {
		return global.getSessionManager();
	}

	/** {@inheritDoc} */
	public List<ICoreTip> getTips() {
		return local.getTips();
	}

	/** {@inheritDoc} */
	public List<ICoreTip> getTips(List<Integer> mayHaveTips) {
		return local.getTips(mayHaveTips);
	}

	/** {@inheritDoc} */
	public void setTips(Map<Integer, List<ITip>> map, Integer... objectIds) {
		local.setTips(map, objectIds);
	}

}
