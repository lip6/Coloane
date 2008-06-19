package fr.lip6.move.coloane.core.copypast.container;

import fr.lip6.move.coloane.core.motor.formalism.ElementFormalism;
import fr.lip6.move.coloane.core.ui.model.IAttributeImpl;
import fr.lip6.move.coloane.core.ui.model.IModelImpl;
import fr.lip6.move.coloane.core.ui.model.INodeImpl;
import fr.lip6.move.coloane.core.ui.model.NodeImplAdapter;
import fr.lip6.move.coloane.interfaces.model.Node;

import java.util.ArrayList;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Color;

/**
 * Classe permettant la reconstruction d'un noeud
 */
public class NodeContainer {
	private int id;

	private ElementFormalism elementFormalism;

	private ArrayList<AttributContainer> attributs = new ArrayList<AttributContainer>();

	private Point location;
	private Color background;
	private Color foreground;
	private int scale;

	/**
	 * @param node
	 */
	public NodeContainer(INodeImpl node) {
		node.getId();
		elementFormalism = node.getElementBase();
		location = node.getGraphicInfo().getLocation();
		foreground = node.getGraphicInfo().getForeground();
		background = node.getGraphicInfo().getBackground();
		scale = node.getGraphicInfo().getScale();
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
		location.x += 10;
		location.y += 10;
		// Décalage des attributs
		for (AttributContainer ac : attributs) {
			ac.setLocation(ac.getLocation().x + 10, ac.getLocation().y + 10);
		}

		Node node = new Node(elementFormalism.getName());
		NodeImplAdapter nodeAdapter = new NodeImplAdapter(node, elementFormalism);
		nodeAdapter.setModelAdapter(modelAdapter);
		nodeAdapter.getGraphicInfo().setLocation(location);
		nodeAdapter.getGraphicInfo().setForeground(foreground);
		nodeAdapter.getGraphicInfo().setBackground(background);
		nodeAdapter.getGraphicInfo().setScale(scale);
		for (AttributContainer ac : attributs) {
			nodeAdapter.setPropertyValue(ac.getId(), ac.getValue());
			nodeAdapter.getAttribute(ac.getName()).getGraphicInfo().setLocation(ac.getLocation());
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
