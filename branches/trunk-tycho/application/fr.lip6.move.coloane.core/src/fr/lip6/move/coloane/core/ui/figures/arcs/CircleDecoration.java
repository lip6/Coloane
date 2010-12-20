package fr.lip6.move.coloane.core.ui.figures.arcs;

import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.RotatableDecoration;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;

/**
 * Circle decoration.
 * @author Clément Démoulins
 */
public class CircleDecoration extends Ellipse implements RotatableDecoration {

	/**
	 * Constructor
	 * @param diameter Diameter of this circle
	 */
	public CircleDecoration(int diameter) {
		setSize(diameter, diameter);
	}
	
	/** 
	 * Set the diameter of the circle.
	 * @param diameter diameter of the circle
	 */
	public final void setSize(int diameter) {
		super.setSize(diameter, diameter);
	}

	/** 
	 * Set the diameter of the circle.
	 * @param w diameter of the circle
	 * @param h not used !
	 */
	@Override
	public final void setSize(int w, int h) {
		super.setSize(w, w);
	}

	/** {@inheritDoc} */
	public final void setReferencePoint(Point ref) {
		Point a = getLocation().getCopy();
		Point ab = ref.getTranslated(a.getNegated());
		int diameter = getSize().height;
		PrecisionPoint center = new PrecisionPoint();

		center.preciseX = a.x + (ab.x * diameter) / (2. * Math.sqrt(ab.x * ab.x + ab.y * ab.y)) - diameter / 2.;
		center.preciseY = a.y + (ab.y * diameter) / (2. * Math.sqrt(ab.x * ab.x + ab.y * ab.y)) - diameter / 2.;

		center.x = (int) Math.round(center.preciseX);
		center.y = (int) Math.round(center.preciseY);

		// Pour corriger l'arrondi
		if ((ab.x * diameter) / (2. * Math.sqrt(ab.x * ab.x + ab.y * ab.y)) < 0) {
			center.x++;
		}
		if ((ab.y * diameter) / (2. * Math.sqrt(ab.x * ab.x + ab.y * ab.y)) < 0) {
			center.y++;
		}

		setLocation(center);
	}

}
