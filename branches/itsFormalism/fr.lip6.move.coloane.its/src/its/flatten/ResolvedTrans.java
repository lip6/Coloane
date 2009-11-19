package its.flatten;

import fr.lip6.move.coloane.interfaces.model.INode;

/** A class to represent a pair of Transition and containing instance.
 * 
 * @author Yann
 *
 */
public final class ResolvedTrans {


	private String instance;
	private INode transition;

	/**
	 * Ctor
	 * @param instance the prefix to instance e.g: P1.P2.
	 * @param transition the transition to unfold
	 */
	public ResolvedTrans(String instance, INode transition) {
		this.instance = instance;
		this.transition = transition;
	}

	/**
	 * @return the prefix of the instance
	 */
	public String getInstance() {
		return instance;
	}
	/**
	 * @return the transition
	 */
	public INode getTransition() {
		return transition;
	}

	/**
	 * pretty print: obtains labels when unfolding
	 * @return unique name of this object
	 */
	@Override
	public String toString() {
		return instance + "." + transition.getAttribute("label").getValue();
	}
}
