package fr.lip6.move.coloane.core.copypast.container;

import fr.lip6.move.coloane.core.motor.formalism.ElementFormalism;
import fr.lip6.move.coloane.core.ui.model.ArcImplAdapter;
import fr.lip6.move.coloane.core.ui.model.IArcImpl;
import fr.lip6.move.coloane.core.ui.model.IAttributeImpl;
import fr.lip6.move.coloane.core.ui.model.IModelImpl;
import fr.lip6.move.coloane.core.ui.model.INodeImpl;

import java.util.ArrayList;

import org.eclipse.draw2d.Bendpoint;
import org.eclipse.draw2d.geometry.Point;

/**
 * Classe permettant la reconstruction d'un arc
 */
public class ArcContainer {
	private int id;

	private int idSource;
	private int idTarget;
	private ElementFormalism elementFormalism;
	private ArrayList<AttributContainer> attributs = new ArrayList<AttributContainer>();
	private ArrayList<Point> pis = new ArrayList<Point>();

	/**
	 * @param arc
	 * @param idSource id du NodeContainer source
	 * @param idTarget id du NodeContainer cible
	 */
	public ArcContainer(IArcImpl arc, int idSource, int idTarget) {
		id = arc.getId();
		this.idSource = idSource;
		this.idTarget = idTarget;
		elementFormalism = arc.getElementBase();
		for (Bendpoint bp : arc.getInflexPoints()) {
			pis.add(bp.getLocation());
		}
		for (IAttributeImpl attr : arc.getAttributes()) {
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

		ArcImplAdapter arcAdapter = new ArcImplAdapter(source, target, elementFormalism);
		arcAdapter.setModelAdapter(model);
		for (AttributContainer ac : attributs) {
			arcAdapter.setPropertyValue(ac.getId(), ac.getValue());
		}
		for (Point p : pis) {
			arcAdapter.addInflexPoint(p, pis.indexOf(p));
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
