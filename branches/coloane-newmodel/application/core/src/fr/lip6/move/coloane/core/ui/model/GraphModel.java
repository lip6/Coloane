package fr.lip6.move.coloane.core.ui.model;

import java.util.HashMap;
import java.util.logging.Logger;

import fr.lip6.move.coloane.core.exceptions.BuildException;
import fr.lip6.move.coloane.core.motor.formalisms.Formalism;
import fr.lip6.move.coloane.core.motor.formalisms.FormalismManager;
import fr.lip6.move.coloane.core.motor.formalisms.elements.Arc;
import fr.lip6.move.coloane.core.motor.formalisms.elements.FormalismElement;
import fr.lip6.move.coloane.core.motor.formalisms.elements.Node;

public class GraphModel extends AbstractElement {
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	private HashMap<Integer, INode>	nodes = new HashMap<Integer, INode>();
	private HashMap<Integer, IArc> arcs = new HashMap<Integer, IArc>();
	private Formalism formalism;
	private int id;
	private int idCounter = 0;

	public GraphModel(String formalismName) {
		super(FormalismManager.getInstance().getFormalismByName(formalismName).getMasterGraph().getAttributes());
		formalism = FormalismManager.getInstance().getFormalismByName(formalismName);
		id = getNewId();

		LOGGER.fine("Création du GraphModel à partir du formalisme : " + formalismName); //$NON-NLS-1$
	}

	private int getNewId() {
		return idCounter++;
	}

	public final INode createNode(String nodeFormalismName) {
		FormalismElement formalismElement = formalism.getFormalismElement(nodeFormalismName);
		if (!(formalismElement instanceof Node)) {
			throw new BuildException("Ce formalisme ne contient pas de noeud du type " + nodeFormalismName); //$NON-NLS-1$
		}
		INode node = new NodeModel((Node) formalismElement, getNewId());
		nodes.put(node.getId(), node);

		LOGGER.fine("Création d'un nouveau noeud de type " + nodeFormalismName); //$NON-NLS-1$
		return node;
	}

	public final void deleteNode(INode node) {
		NodeModel nodeModel = (NodeModel) node;
		nodeModel.delete();
	}

	public final void deleteNode(int id) {
		INode node = nodes.get(id);
		if (node != null) {
			deleteNode(node);
		}
	}

	public final IArc createArc(String arcFormalismName, INode source, INode target) {
		if (!nodes.containsKey(source.getId()) || !nodes.containsKey(target.getId())) {
			throw new BuildException("Un des noeuds de connexion n'est pas connu"); //$NON-NLS-1$
		}

		FormalismElement formalismElement = formalism.getFormalismElement(arcFormalismName);
		if (!(formalismElement instanceof Arc)) {
			throw new BuildException("Ce formalisme ne contient pas d'arc du type " + arcFormalismName); //$NON-NLS-1$
		}
		IArc arc = new ArcModel((Arc) formalismElement, getNewId(), source, target);
		return arc;
	}

	public final int getId() {
		return id;
	}
}
