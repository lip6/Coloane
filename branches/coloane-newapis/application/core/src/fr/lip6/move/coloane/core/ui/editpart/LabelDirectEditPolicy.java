package fr.lip6.move.coloane.core.ui.editpart;

import fr.lip6.move.coloane.core.model.interfaces.IStickyNote;
import fr.lip6.move.coloane.core.ui.commands.StickyNoteEditCommand;
import fr.lip6.move.coloane.core.ui.figures.sticky.StickyNoteFigure;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.DirectEditPolicy;
import org.eclipse.gef.requests.DirectEditRequest;


/**
 * Règles pour l'édition des notes.
 */
public class LabelDirectEditPolicy extends DirectEditPolicy {

	/** {@inheritDoc} */
	@Override
	protected final Command getDirectEditCommand(DirectEditRequest edit) {
		String labelText = (String) edit.getCellEditor().getValue();
		StickyEditPart label = (StickyEditPart) getHost();
		StickyNoteEditCommand command = new StickyNoteEditCommand((IStickyNote) label.getModel(), labelText);
		return command;
	}

	/** {@inheritDoc} */
	@Override
	protected final void showCurrentEditValue(DirectEditRequest request) {
		String value = (String) request.getCellEditor().getValue();
		((StickyNoteFigure) getHostFigure()).setText(value);
		//hack to prevent async layout from placing the cell editor twice.
		getHostFigure().getUpdateManager().performUpdate();
	}
}
