package fr.lip6.move.coloane.ui.editpart;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import fr.lip6.move.coloane.motor.models.AttributeImplAdapter;
import fr.lip6.move.coloane.ui.views.AttributeFigure;
import fr.lip6.move.coloane.ui.views.IAttributeFigure;

public class AttributeEditPart extends AbstractGraphicalEditPart {

	@Override
	protected IFigure createFigure() {
		System.out.println("Creation de la figure d'un attribut");
		IFigure figure = new AttributeFigure();
		return figure;
	}

	@Override
	protected void createEditPolicies() {
		// TODO Auto-generated method stub
		
	}
	
	protected void refreshVisuals() {
		System.out.println("Refresh Attributes");
		IAttributeFigure attributeFigure = (IAttributeFigure)getFigure();
		AttributeImplAdapter attributeModel = (AttributeImplAdapter)getModel();
		System.out.println(">>> Atttribut : "+attributeModel.getDisplayName());
		super.refreshVisuals();
	}

}
