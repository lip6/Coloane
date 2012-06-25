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
	@Override
	public IResultTree getParent() {
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public void setParent(IResultTree parent) { }

	/** {@inheritDoc} */
	@Override
	public List<IResultTree> getChildren() {
		List<IResultTree> children = new ArrayList<IResultTree>(global.getChildren().size() + local.getChildren().size());
		children.addAll(global.getChildren());
		children.addAll(local.getChildren());
		return children;
	}

	/** {@inheritDoc} */
	@Override
	public void addChild(IResultTree child) {
		throw new UnsupportedOperationException();
	}

	/** {@inheritDoc} */
	@Override
	public List<Object> getElement() {
		return new ArrayList<Object>(getChildren());
	}

	/** {@inheritDoc} */
	@Override
	public List<Integer> getHighlighted() {
		return local.getHighlighted();
	}

	/** {@inheritDoc} */
	@Override
	public void addHighlighted(int... toHighlight) {
		local.addHighlighted(toHighlight);
	}

	/** {@inheritDoc} */
	@Override
	public Map<Integer, List<String>> getAttributesOutline() {
		return null;
	}

	/** {@inheritDoc} */
	@Override
	public void remove() { }

	/** {@inheritDoc} */
	@Override
	public void setSessionManager(ISessionManager sessionManager) {
		global.setSessionManager(sessionManager);
	}

	/** {@inheritDoc} */
	@Override
	public ISessionManager getSessionManager() {
		return global.getSessionManager();
	}

	/** {@inheritDoc} */
	@Override
	public List<ICoreTip> getTips() {
		return local.getTips();
	}

	/** {@inheritDoc} */
	@Override
	public List<ICoreTip> getTips(List<Integer> mayHaveTips) {
		return local.getTips(mayHaveTips);
	}

	/** {@inheritDoc} */
	@Override
	public void setTips(Map<Integer, List<ITip>> map, Integer... objectIds) {
		local.setTips(map, objectIds);
	}

}
