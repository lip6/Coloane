package fr.lip6.move.coloane.ui.views;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MidpointLocator;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;

public class ArcFigure extends PolylineConnection implements IArcFigure {
    private Label label;
    
    public ArcFigure(){
    	label = new Label("");
    	label.setBackgroundColor(ColorConstants.white);
    	label.setOpaque(true);
    	add(label, new MidpointLocator(this, 0));
            
    	// La decoration de l'arc
    	PolygonDecoration dec = new PolygonDecoration();
    	dec.setScale(4,2);
    	this.setTargetDecoration(dec);
            
    	// Epaisseur de la ligne
    	setLineWidth(1);
    }
    
    /**
     * Indique la valeur associee a l'arc
     * @param text La valeur de l'arc
     */
    public void setLabelText(String text){
    	label.setText(text);
    }
}