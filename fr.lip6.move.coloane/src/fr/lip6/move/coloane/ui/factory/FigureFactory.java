package fr.lip6.move.coloane.ui.factory;


import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.MidpointLocator;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;

import fr.lip6.move.coloane.interfaces.models.INodeGraphicInfo;
import fr.lip6.move.coloane.motor.models.*;

/**
 * Realisation des figures : Factory des figures
 */
public class FigureFactory {

	
	/**
	 * Selon le type de l'element a dessiner, on appelle la methode correspondante
	 * @param AbstractModelElement element l'element qu'on souhaite dessiner
	 * @return IFigure 
	 */
	public static IFigure createFigure(AbstractModelElement element) {

		// C'est un noeud
		if (element instanceof NodeImplAdapter)
			return createNodeFigure((NodeImplAdapter) element);
		
		// C'est un arc
		else if (element instanceof ArcImplAdapter)
			return createArcFigure((ArcImplAdapter) element);

		return null;
	}

	/**
	 * Dessin de la figure dans le cas d'un noeud
	 * @param NodeImplAdapter node Element a creer
	 * @return Ifigure l'Element crŽŽ
	 * @throws Exception en cas d'erreur
	 */
	private static IFigure createNodeFigure(NodeImplAdapter node)  {
		final INodeGraphicInfo nodeGraphInfo = node.getGraphicInfo();
		IFigure figure = null;

		// Si le noeud doit etre dessine en tant que cercle
		if (nodeGraphInfo.getFigureStyle() == INodeGraphicInfo.FIG_CIRCLE) {
			figure = new Ellipse();

		// Si le noeud doit etre dessine comme un double cercle
		} else if (nodeGraphInfo.getFigureStyle() == INodeGraphicInfo.FIG_DBLCIRCLE) {
			// Creation d'une nouvelle figure
			figure = new Figure() {

				public Dimension getPreferredSize(int wHint, int hHint) {
					return new Dimension(100, 40);
				}

				protected void paintFigure(Graphics g) {
					Rectangle r = getBounds().getCopy();
					g.translate(r.getLocation());
						
					int w2 = r.width-1;
					int h2 = r.height-1;
						
					g.drawOval(0,0,w2,h2);
					g.drawOval(2,2,w2-4,h2-4);
				}

				public Rectangle getBounds() {
					Rectangle r = super.getBounds();
					return new Rectangle(r.x, r.y, nodeGraphInfo.getWidth(), nodeGraphInfo.getHeight());
				}
			};

			// Si le noeud doit etre dessine comme un rectangle (transition)
			} else if (nodeGraphInfo.getFigureStyle() == INodeGraphicInfo.FIG_RECT) {
				figure = new RectangleFigure();

			// Si le noeud doit etre dessine comme un oval
			} else if (nodeGraphInfo.getFigureStyle() == INodeGraphicInfo.FIG_QUEUE) {	
				// Figure complexe
				figure = new Figure() {

					public Dimension getPreferredSize(int wHint, int hHint) {
						return new Dimension(100, 40);
					}

					protected void paintFigure(Graphics g) {
						Rectangle r = getBounds().getCopy();
						g.translate(r.getLocation());
						int w = r.width;
						int h = r.height;
						
						g.drawLine(h / 2, 0, w - h / 2 - 1, 0);
						g.drawLine(h / 2, h - 1, w - h / 2 - 1, h - 1);

						g.drawArc(0, 0, h - 1, h - 1, 90, 180);
						g.drawArc(w - h - 1, 0, h - 1, h - 1, -90, 180);
					}
					
					public Rectangle getBounds() {
						Rectangle r = super.getBounds();
						return new Rectangle(r.x, r.y, nodeGraphInfo.getWidth(), nodeGraphInfo.getHeight());
					}
				};

			// Si aucun type n'est specifie on renvoie un rectangle
			} else {
				figure = new RectangleFigure();
			}

			// La suite s'applique a tout le monde
			figure.setSize(nodeGraphInfo.getSize());

			figure.setOpaque(false);
			figure.setForegroundColor(ColorConstants.black);
			figure.setBackgroundColor(nodeGraphInfo.isFilled()?ColorConstants.black:ColorConstants.white);
			return figure;

	}

	/**
	 * Dessin de la figure dans le cas d'un arc
	 * @param ArcImplAdapter element Element a dessiner
	 * @return IFigure
	 */
	private static IFigure createArcFigure(ArcImplAdapter element) {

		PolylineConnection arc = new PolylineConnection();
		arc.setLineStyle(Graphics.LINE_SOLID); 
		
		// Si l'arc a dessiner est inhibiteur
		if (element.getFigureStyle() == 1) {
			PolygonDecoration decoration = new PolygonDecoration();
			PointList decorationPointList = new PointList();
			
			/* Dessin du cercle.*/
			decorationPointList.addPoint(0, 0);
			decorationPointList.addPoint(-1, -1);
			decorationPointList.addPoint(-2, -2);
			decorationPointList.addPoint(-3, -2);
			decorationPointList.addPoint(-4, -1);
			decorationPointList.addPoint(-5, 0);
			decorationPointList.addPoint(-4, 1);
			decorationPointList.addPoint(-3, 2);
			decorationPointList.addPoint(-2, 2);
			decorationPointList.addPoint(-1, 1);
			decoration.setTemplate(decorationPointList);
			decoration.setScale(1.0,1.0);
			arc.setTargetDecoration(decoration);
		} 
		 
		// Dans le cas d'un arc normal... Il n'y a rien a faire.

		// Quelque soit le type d'arc, un label est necessaire
		// Ajout du label		
		String valeur = "";
		// Recherche de la valuation
		for (int i = 0; i < element.getGenericArc().getListOfAttrSize(); i++) {
			if (element.getGenericArc().getNthAttr(i).getName().equalsIgnoreCase("rvaluation")) {
				valeur = element.getGenericArc().getNthAttr(i).getValue();
				break;
			}
		}
				
		if (valeur.equalsIgnoreCase("")) {
			return arc;
		} else {
			Label valuation = new Label(valeur);
			valuation.setOpaque(true);
			valuation.setBackgroundColor(ColorConstants.lightGray);
			valuation.setBorder(new LineBorder());
			arc.add(valuation, new MidpointLocator(arc, 0));
			return arc;
		}
	}
}
