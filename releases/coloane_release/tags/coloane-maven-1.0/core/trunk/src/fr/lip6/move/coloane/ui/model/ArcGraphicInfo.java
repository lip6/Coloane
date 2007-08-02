package fr.lip6.move.coloane.ui.model;

import java.io.Serializable;

import org.eclipse.draw2d.geometry.Point;

public class ArcGraphicInfo implements IArcGraphicInfo, Serializable {
		
	/** Pour la serialisation */
	private static final long serialVersionUID = 1L;
	
	/** Il faut conserver le dernier middle point */
	private Point oldMiddlePoint = null;
	
	
	private IArcImpl arc;
	
	public ArcGraphicInfo (IArcImpl arc) {
		this.arc = arc;
		this.oldMiddlePoint = this.findMiddlePoint();
	}
	
	/*
	 * (non-Javadoc)
	 * @see fr.lip6.move.coloane.ui.model.IArcGraphicInfo#findMiddlePoint()
	 */
    public Point findMiddlePoint() {
    	int x = (this.arc.getSource().getGraphicInfo().getLocation().x + this.arc.getTarget().getGraphicInfo().getLocation().x) / 2;
    	int y = (this.arc.getSource().getGraphicInfo().getLocation().y + this.arc.getTarget().getGraphicInfo().getLocation().y) / 2;
    	return new Point(x,y);
    }
    
    /*
     * (non-Javadoc)
     * @see fr.lip6.move.coloane.ui.model.IArcGraphicInfo#updateMiddlePoint()
     */
    public void updateMiddlePoint() {
    	this.oldMiddlePoint = this.findMiddlePoint();
    }

    /*
     * (non-Javadoc)
     * @see fr.lip6.move.coloane.ui.model.IArcGraphicInfo#getMiddlePoint()
     */
    public Point getMiddlePoint() {
    	return this.oldMiddlePoint;
    }
}
