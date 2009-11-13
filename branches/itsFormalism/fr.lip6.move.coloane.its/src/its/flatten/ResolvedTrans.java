package its.flatten;

import fr.lip6.move.coloane.interfaces.model.INode;

/** A class to represent a pair of Transition and containing instance.
 * 
 * @author Yann
 *
 */
public class ResolvedTrans {


	private String instance;
	private INode transition;

	public ResolvedTrans(String instance, INode transition) {
		this.instance = instance;
		this.transition = transition;
	}
	
	public String getInstance() {
		return instance;
	}
	public INode getTransition() {
		return transition;
	}
	
	@Override
	public String toString() {
		return instance+"."+transition.getAttribute("label").getValue();
	}
}
