package fr.lip6.move.coloane.core.copypast.container;

import fr.lip6.move.coloane.interfaces.exceptions.ModelException;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.Bendpoint;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Color;

/**
 * Classe permettant la reconstruction d'un arc
 */
public class ArcContainer {
	private int id;

	private int idSource;
	private int idTarget;
	private String arcFormalismName;

	private List<AttributContainer> attributs = new ArrayList<AttributContainer>();

	private Color color;
	private List<Point> pis = new ArrayList<Point>();

	/**
	 * Constructeur
	 * @param arc L'arc considéré
	 * @param idSource id du NodeContainer source
	 * @param idTarget id du NodeContainer cible
	 */
	public ArcContainer(IArc arc, int idSource, int idTarget) {
		id = arc.getId();
		this.idSource = idSource;
		this.idTarget = idTarget;
		color = arc.getGraphicInfo().getColor();
		arcFormalismName = arc.getArcFormalism().getName();

		// Sauvegarde des points d'inflexion
		for (Bendpoint bp : arc.getInflexPoints()) {
			pis.add(bp.getLocation());
		}
		// Sauvegarde des attributs
		for (IAttribute attr : arc.getAttributes()) {
			attributs.add(new AttributContainer(attr));
		}
	}

	/**
	 * Copier un arc
	 * @param graph Le graphe qui contient l'arc
	 * @param source La source de l'arc
	 * @param target La cible de l'arc
	 * @return une copie de l'IArcImpl passée au constructeur
	 * @throws ModelException Si la création de l'arc c'est mal passé.
	 */
	public final IArc copy(IGraph graph, INode source, INode target) throws ModelException {
		// Décalage des points d'inflexion
		for (Point p : pis) {
			p.x += 10;
			p.y += 10;
		}
		// Décalage des attributs
		for (AttributContainer ac : attributs) {
			ac.setLocation(ac.getLocation().x + 10, ac.getLocation().y + 10);
		}

		IArc arc = graph.createArc(arcFormalismName, source, target);
		arc.getGraphicInfo().setColor(color);
		for (AttributContainer ac : attributs) {
			arc.getAttribute(ac.getName()).setValue(ac.getValue());
			arc.getAttribute(ac.getName()).getGraphicInfo().setLocation(ac.getLocation());
		}
		for (int index = 0; index < pis.size(); index++) {
			Point p = pis.get(index);
			arc.addInflexPoint(p, index);
		}
		return arc;
	}

	/**
	 * @return id du NodeContainer source
	 */
	public final int getIdSource() {
		return idSource;
	}

	/**
	 * @return id du NodeContainer cible
	 */
	public final int getIdTarget() {
		return idTarget;
	}

	/**
	 * @return id de cette ArcContainer
	 */
	public final int getId() {
		return id;
	}
}
