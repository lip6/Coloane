package fr.lip6.move.coloane.core.formalisms;

import fr.lip6.move.coloane.interfaces.formalism.IArcFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;
import fr.lip6.move.coloane.interfaces.formalism.IGraphFormalism;
import fr.lip6.move.coloane.interfaces.formalism.constraints.IConstraintLink;
import fr.lip6.move.coloane.interfaces.formalism.constraints.IConstraintNode;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.ArrayList;
import java.util.List;

/**
 * Define a formalism.<br>
 */
public class Formalism implements IFormalism {

	/** Formalism ID */
	private String id;

	/** Formalism Name */
	private String name;

	/** Formalism Identifier for FK platform (historical reason) */
	private String fkname;

	/** Formalism constraints about relations between objects */
	private List<IConstraintLink> linkconstraints;

	/** Formalism constraints about nodes */
	private List<IConstraintNode> nodeconstraints;

	/** Root Graph defined by this formalism */
	private IGraphFormalism rootGraphFormalism = null;

	/** Image that represents the formalism */
	private String image;

	/**
	 * Constructor
	 *
	 * @param id Formalism ID
	 * @param name Formalism Name
	 * @param fkname Formalism ID for FK platform
	 * @param image Image that describes the formalism
	 */
	Formalism(String id, String name, String fkname, String image) {
		this.id = id;
		this.name = name;
		this.fkname = fkname;
		this.image = image;

		this.linkconstraints = new ArrayList<IConstraintLink>();
		this.nodeconstraints = new ArrayList<IConstraintNode>();
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean isLinkAllowed(INode source, INode target, IArcFormalism arcFormalism) {
		// Try to find a constraint for these two kinds of nodes
		for (IConstraintLink constraint : linkconstraints) {
			if (!constraint.isSatisfied(source, target, arcFormalism)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	public final boolean isActionAllowed(INode node) {
		// Try to find a constraint for this kind of node
		for (IConstraintNode constraint : nodeconstraints) {
			if (!constraint.isSatisfied(node)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Add a link constraint to the formalism
	 * @param constraint The constraint between nodes
	 * @see {@link IConstraintLink}
	 * @see {@link IConstraint}
	 */
	final void addConstraintLink(IConstraintLink constraint) {
		if (constraint == null) { return; }
		this.linkconstraints.add(constraint);
	}

	/**
	 * Add a node constraint to the formalism
	 * @param constraint The constraint for a node
	 * @see {@link IConstraintNode}
	 * @see {@link IConstraint}
	 */
	public final void addConstraintNode(IConstraintNode constraint) {
		if (constraint == null) { return; }
		this.nodeconstraints.add(constraint);
	}

	/** {@inheritDoc} */
	public final String getId() {
		return this.id;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getName() {
		return this.name;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getFKName() {
		return this.fkname;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getImageName() {
		return "/" + image; //$NON-NLS-1$
	}

	/**
	 * {@inheritDoc}
	 */
	public final IGraphFormalism getRootGraph() {
		return this.rootGraphFormalism;
	}

	/**
	 * Set the root graph (the graph described by the formalism).<br>
	 * This information is used to get the most high level graph object very quickly from any node, arc or attribute.<br>
	 * Just use {@link #getMasterGraph()} 
	 * @param rootGraphFormalism The graph which is at the top level
	 */
	final void setRootGraph(IGraphFormalism rootGraphFormalism) {
		this.rootGraphFormalism = rootGraphFormalism;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String toString() {
		return getName();
	}
}
