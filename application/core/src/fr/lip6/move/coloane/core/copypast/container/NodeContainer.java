package fr.lip6.move.coloane.core.copypast.container;

import fr.lip6.move.coloane.core.motor.formalism.ElementFormalism;
import fr.lip6.move.coloane.core.ui.model.IAttributeImpl;
import fr.lip6.move.coloane.core.ui.model.IModelImpl;
import fr.lip6.move.coloane.core.ui.model.INodeImpl;
import fr.lip6.move.coloane.core.ui.model.NodeImplAdapter;
import fr.lip6.move.coloane.interfaces.model.Node;

import java.util.ArrayList;

/**
 * Classe permettant la reconstruction d'un noeud
 */
public class NodeContainer {
	private int id;

	private ElementFormalism elementFormalism;
	private int xPosition;
	private int yPosition;
	private ArrayList<AttributContainer> attributs = new ArrayList<AttributContainer>();

	/**
	 * @param node
	 */
	public NodeContainer(INodeImpl node) {
		node.getId();
		elementFormalism = node.getElementBase();
		xPosition = node.getGenericNode().getXPosition();
		yPosition = node.getGenericNode().getYPosition();
		for (IAttributeImpl attr : node.getAttributes()) {
			attributs.add(new AttributContainer(attr));
		}
	}

	/**
	 * @param modelAdapter
	 * @return une copie du INodeImpl passée au constructeur
	 */
	public final INodeImpl copy(IModelImpl modelAdapter) {
		// Décalage de la copie
		xPosition += 10;
		yPosition += 10;

		Node node = new Node(elementFormalism.getName(), xPosition, yPosition);
		NodeImplAdapter nodeAdapter = new NodeImplAdapter(node, elementFormalism);
		nodeAdapter.setModelAdapter(modelAdapter);
		for (AttributContainer ac : attributs) {
			nodeAdapter.setPropertyValue(ac.getId(), ac.getValue());
		}
		return nodeAdapter;
	}

	/**
	 * @return id du NodeContainer
	 */
	public final int getId() {
		return id;
	}
}
