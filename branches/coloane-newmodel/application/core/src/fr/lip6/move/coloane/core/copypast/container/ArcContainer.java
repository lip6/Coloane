package fr.lip6.move.coloane.core.copypast.container;

import fr.lip6.move.coloane.core.motor.formalisms.elements.Arc;
import fr.lip6.move.coloane.core.ui.model.interfaces.IArc;
import fr.lip6.move.coloane.core.ui.model.interfaces.IAttribute;

import java.util.ArrayList;

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
	private Arc arcFormalism;

	private ArrayList<AttributContainer> attributs = new ArrayList<AttributContainer>();

	private Color color;
	private ArrayList<Point> pis = new ArrayList<Point>();

	/**
	 * @param arc
	 * @param idSource id du NodeContainer source
	 * @param idTarget id du NodeContainer cible
	 */
	public ArcContainer(IArc arc, int idSource, int idTarget) {
		id = arc.getId();
		this.idSource = idSource;
		this.idTarget = idTarget;
		color = arc.getGraphicInfo().getColor();
		arcFormalism = arc.getArcFormalism();

		// sauvegarde des points d'inflexion
		for (Bendpoint bp : arc.getInflexPoints()) {
			pis.add(bp.getLocation());
		}
		
		// sauvegarde des attributs
		for (IAttribute attr : arc.getAttributes()) {
			attributs.add(new AttributContainer(attr));
		}
	}

	/**
	 * @param model
	 * @param source
	 * @param target
	 * @return une copie de l'IArcImpl passée au constructeur
	 */
	public final IArcImpl copy(IModelImpl model, INodeImpl source, INodeImpl target) {
		// Décalage des points d'inflexion
		for (Point p : pis) {
			p.x += 10;
			p.y += 10;
		}
		// Décalage des attributs
		for (AttributContainer ac : attributs) {
			ac.setLocation(ac.getLocation().x + 10, ac.getLocation().y + 10);
		}

		ArcImplAdapter arcAdapter = new ArcImplAdapter(source, target, arcFormalism);
		arcAdapter.setModelAdapter(model);
		arcAdapter.getGraphicInfo().setColor(color);
		for (AttributContainer ac : attributs) {
			arcAdapter.setPropertyValue(ac.getId(), ac.getValue());
			arcAdapter.getAttribute(ac.getName()).getGraphicInfo().setLocation(ac.getLocation());
		}
		for (int index = 0; index < pis.size(); index++) {
			Point p = pis.get(index);
			arcAdapter.addInflexPoint(p, index);
		}
		return arcAdapter;
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
