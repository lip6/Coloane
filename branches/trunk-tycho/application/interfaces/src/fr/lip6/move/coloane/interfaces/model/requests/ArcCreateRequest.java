package fr.lip6.move.coloane.interfaces.model.requests;

import fr.lip6.move.coloane.interfaces.formalism.IArcFormalism;
import fr.lip6.move.coloane.interfaces.model.INode;

/**
 * Ask the core project to create an arc
 *
 * @author Jean-Baptiste Voron
 */
public class ArcCreateRequest implements IRequest {
	/** Arc source */
	private INode source;
	/** Arc target */
	private INode target;
	/** The formalism used by the new arc */
	private IArcFormalism formalism;

	/**
	 * Constructor
	 * @param source The source of the new arc
	 * @param target The target of the new arc
	 * @param formalism The formalism used by this arc (describe its properties)
	 */
	public ArcCreateRequest(INode source, INode target, IArcFormalism formalism) {
		this.source = source;
		this.target = target;
		this.formalism = formalism;
	}

	/**
	 * @return the source of the new arc
	 */
	public INode getSource() {
		return source;
	}

	/**
	 * @return the target of the new arc
	 */
	public INode getTarget() {
		return target;
	}

	/**
	 * @return The formalism used by the new arc
	 */
	public IArcFormalism getFormalism() {
		return formalism;
	}

	/**
	 * {@inheritDoc}
	 */
	public int getRequestType() {
		return IRequest.ARC_CREATE_REQUEST;
	}
}
