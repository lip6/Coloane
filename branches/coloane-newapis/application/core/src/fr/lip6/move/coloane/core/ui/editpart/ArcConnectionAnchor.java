package fr.lip6.move.coloane.core.ui.editpart;

import fr.lip6.move.coloane.interfaces.model.IArc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.AbstractConnectionAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.NodeEditPart;

/**
 * ConnectionAnchor pour un arc avec point d'inflexion et courbe de bézier.<br>
 * Le point de connexion correspond au plus proche milieu de chaque segment de l'arc.
 * Les segments de l'arc sont définis par la source, la liste des points d'inflexions
 *  et la destination de l'arc.
 *
 * @author Clément DEMOULINS
 *
 * @see RoundedPolylineConnection
 */
public class ArcConnectionAnchor extends AbstractConnectionAnchor implements ConnectionAnchor {
	private ArcEditPart arc;

	/**
	 * Sauvegarde du point de connexion pour pouvoir s'en servir comme point de référence
	 */
	private Point location;

	private Point source;

	private Point target;

	/**
	 * Constructeur
	 * @param arc EditPart de l'arc
	 */
	public ArcConnectionAnchor(ArcEditPart arc) {
		super(arc.getFigure());
		if (!(arc.getSource() instanceof NodeEditPart) || !(arc.getTarget() instanceof NodeEditPart)) {
			throw new IllegalArgumentException("source or target editParts doesn't implement org.eclipse.gef.NodeEditPart"); //$NON-NLS-1$
		}
		this.arc = arc;
		this.source = ((NodeEditPart) arc.getSource()).getSourceConnectionAnchor(arc).getReferencePoint();
		this.target = ((NodeEditPart) arc.getTarget()).getTargetConnectionAnchor(arc).getReferencePoint();
	}

	/** {@inheritDoc} */
	@Override
	public final Point getReferencePoint() {
		if (location != null) {
			return location;
		}
		return super.getReferencePoint();
	}

	/** {@inheritDoc} */
	public final Point getLocation(Point reference) {
		// Création de la liste des points de l'arc
		List<Point> points = new ArrayList<Point>();
		points.add(source);
		points.addAll(((IArc) arc.getModel()).getInflexPoints());
		points.add(target);

		// Recherche du milieu de segment le plus proche du point de référence
		int distance = Integer.MAX_VALUE;
		Iterator<Point> it = points.iterator();
		Point p0 = it.next(), p1 = null;
		location = p0;
		while (it.hasNext()) {
			p1 = it.next();
			Point middle = p0.getTranslated(p1.getDifference(p0).scale(0.5));
			int tmpDistance = (int) Math.round(reference.getDistance(middle));
			if (tmpDistance < distance) {
				distance = tmpDistance;
				location = middle;
			}
			p0 = p1;
		}
		return location;
	}

}
