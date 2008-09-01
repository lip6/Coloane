package fr.lip6.move.coloane.core.copypast.container;

import fr.lip6.move.coloane.interfaces.formalism.IFormalism;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Classe conteneur de NodeContainer et d'ArcContainer
 */
public class GraphContainer {
	private final IFormalism formalism;

	private final Map<Integer, NodeContainer> nodes = new HashMap<Integer, NodeContainer>();
	private final Set<ArcContainer> arcs = new HashSet<ArcContainer>();

	/**
	 * Constructeur
	 * @param formalism Le formalisme du modèle
	 */
	public GraphContainer(IFormalism formalism) {
		this.formalism = formalism;
	}

	/**
	 * Ajoute un noeud
	 * @param node Le noeud à ajouter
	 */
	public final void addNode(INode node) {
		nodes.put(node.getId(), new NodeContainer(node));
	}

	/**
	 * @param id L'identifiant du conteneur de noeud recherché
	 * @return le conteneur ou <code>null</code> s'il est introuvable
	 */
	public final NodeContainer getNode(int id) {
		return nodes.get(id);
	}

	/**
	 * @return l'ensemble des conteneurs de noeud
	 */
	public final Collection<NodeContainer> getNodes() {
		return nodes.values();
	}

	/**
	 * @param arc L'arc qui doit être ajouté
	 */
	public final void addArc(IArc arc) {
		arcs.add(new ArcContainer(arc, arc.getSource().getId(), arc.getTarget().getId()));
	}

	/**
	 * @return l'ensemble des conteneurs d'arc du graphe
	 */
	public final Collection<ArcContainer> getArcs() {
		return arcs;
	}

	/**
	 * @return <code>true</code> s'il n'y a ni conteneur de noeud ni conteneurs d'arcs
	 */
	public final boolean isEmpty() {
		return nodes.isEmpty() && arcs.isEmpty();
	}

	/**
	 * @return le formalisme du graphe
	 */
	public final IFormalism getFormalism() {
		return formalism;
	}
}
