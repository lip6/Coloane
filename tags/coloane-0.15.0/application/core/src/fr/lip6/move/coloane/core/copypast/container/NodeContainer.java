package fr.lip6.move.coloane.core.copypast.container;

import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Color;

/**
 * Classe permettant la reconstruction d'un noeud
 */
public class NodeContainer {
	private int id;

	private String nodeFormalismName;

	private List<AttributContainer> attributs = new ArrayList<AttributContainer>();

	private Point location;
	private Color background;
	private Color foreground;
	private int scale;

	/**
	 * Constructeur
	 * @param node Le noeur considéré
	 */
	public NodeContainer(INode node) {
		node.getId();
		nodeFormalismName = node.getNodeFormalism().getName();
		location = node.getGraphicInfo().getLocation();
		foreground = node.getGraphicInfo().getForeground();
		background = node.getGraphicInfo().getBackground();
		scale = node.getGraphicInfo().getScale();
		for (IAttribute attr : node.getAttributes()) {
			attributs.add(new AttributContainer(attr));
		}
	}

	/**
	 * Copie du noeud
	 * @param graph Le graphe qui contient le noeud
	 * @return une copie du INodeImpl passée au constructeur
	 * @throws ModelException Si la création du noeud c'est mal passé.
	 */
	public final INode copy(IGraph graph) throws ModelException {
		// Décalage de la copie
		location.x += 10;
		location.y += 10;
		// Décalage des attributs
		for (AttributContainer ac : attributs) {
			ac.setLocation(ac.getLocation().x + 10, ac.getLocation().y + 10);
		}

		INode node = graph.createNode(nodeFormalismName);
		node.getGraphicInfo().setLocation(location);
		node.getGraphicInfo().setForeground(foreground);
		node.getGraphicInfo().setBackground(background);
		node.getGraphicInfo().setScale(scale);
		for (AttributContainer ac : attributs) {
			node.getAttribute(ac.getName()).setValue(ac.getValue());
			node.getAttribute(ac.getName()).getGraphicInfo().setLocation(ac.getLocation());
		}
		return node;
	}

	/**
	 * @return id du NodeContainer
	 */
	public final int getId() {
		return id;
	}
}
