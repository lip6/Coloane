package fr.lip6.move.coloane.ui.figures;

import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;

public class ArcFigure extends PolylineConnection implements IArcFigure {
    
    public ArcFigure(){
            
    	// La decoration de l'arc
    	PolygonDecoration dec = new PolygonDecoration();
    	dec.setScale(4,2);
    	this.setTargetDecoration(dec);
            
    	// Epaisseur de la ligne
    	setLineWidth(1);
    }
}