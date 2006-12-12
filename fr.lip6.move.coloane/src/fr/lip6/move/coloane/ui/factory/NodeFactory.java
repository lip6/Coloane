package fr.lip6.move.coloane.ui.factory;

import org.eclipse.gef.requests.CreationFactory;

import fr.lip6.move.coloane.motor.formalism.ElementBase;
import fr.lip6.move.coloane.motor.models.NodeImplAdapter;

//TODO tout a remplir

/**
 * @author David Baudon
 */
public class NodeFactory implements CreationFactory{
	
	/**
	 * 
	 */
	private ElementBase element;
	
	/**
	 * @param el
	 */
	public NodeFactory(ElementBase el) {
		element = el;
		// TODO a remplir
	}

    /* (non-Javadoc)
	 * @see org.eclipse.gef.requests.CreationFactory#getNewObject()
	 */
	public Object getNewObject() {
		return new NodeImplAdapter(this.element);
	}

    /* (non-Javadoc)
	 * @see org.eclipse.gef.requests.CreationFactory#getObjectType()
	 */
	public Object getObjectType() {
		return NodeImplAdapter.class;
	}
}
