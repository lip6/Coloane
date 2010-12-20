package fr.lip6.move.coloane.core.ui.editpart;

import fr.lip6.move.coloane.interfaces.formalism.IAttributeFormalism;
import fr.lip6.move.coloane.interfaces.model.IAttribute;

import org.eclipse.draw2d.Label;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editparts.ZoomListener;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gef.tools.DirectEditManager;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.TextCellEditor;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import org.eclipse.ui.IActionBars;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.part.CellEditorActionHandler;

/**
 * Manager that handle attributes editing.<br>
 */
public class AttributeEditManager extends DirectEditManager {

	private IActionBars actionBars;
	private CellEditorActionHandler actionHandler;
	private IAction copy, cut, paste, undo, redo, find, selectAll, delete;
	private double cachedZoom = -1.0;
	private Font scaledFont;
	private ZoomListener zoomListener = new ZoomListener() {
		public void zoomChanged(double newZoom) {
			updateScaledFont(newZoom);
		}
	};
	private int style;

	/**
	 * @param source Attribute's parent (in graphical hierarchy)
	 * @param attributeModel Model object associate to the attribute
	 * @param locator CellEditorLocator 
	 */
	public AttributeEditManager(GraphicalEditPart source, IAttribute attributeModel, CellEditorLocator locator) {
		super(source, null, locator);
		if (((IAttributeFormalism) attributeModel.getAttributeFormalism()).isMultiLine()) {
			this.style = SWT.MULTI;
		} else {
			this.style = SWT.SINGLE;
		}
	}

	/** {@inheritDoc} */
	@Override
	protected final void bringDown() {
//		if (getEditPart().getParent() != null) {
			ZoomManager zoomMgr = (ZoomManager) getEditPart().getViewer().getProperty(ZoomManager.class.toString());
			if (zoomMgr != null) {
				zoomMgr.removeZoomListener(zoomListener);
			}
//		}

		if (actionHandler != null) {
			actionHandler.dispose();
			actionHandler = null;
		}
		if (actionBars != null) {
			restoreSavedActions(actionBars);
			actionBars.updateActionBars();
			actionBars = null;
		}

		super.bringDown();
		// dispose any scaled fonts that might have been created
		disposeScaledFont();
	}

	/** {@inheritDoc} */
	@Override
	protected final CellEditor createCellEditorOn(Composite composite) {
		return new TextCellEditor(composite, style | SWT.WRAP);
	}

	/**
	 * Free fonts resources 
	 */
	private void disposeScaledFont() {
		if (scaledFont != null) {
			scaledFont.dispose();
			scaledFont = null;
		}
	}

	/** {@inheritDoc} */
	@Override
	protected final void initCellEditor() {
		// update text
		Label attributeFigure = (Label) getEditPart().getFigure();
		getCellEditor().setValue(attributeFigure.getText());
		// update font
		ZoomManager zoomMgr = (ZoomManager) getEditPart().getViewer().getProperty(ZoomManager.class.toString());
		if (zoomMgr != null) {
			// this will force the font to be set
			cachedZoom = -1.0;
			updateScaledFont(zoomMgr.getZoom());
			zoomMgr.addZoomListener(zoomListener);
		} else {
			getCellEditor().getControl().setFont(attributeFigure.getFont());
		}

		// Hook the cell editor's copy/paste actions to the actionBars so that they can
		// be invoked via keyboard shortcuts.
		actionBars = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor().getEditorSite().getActionBars();
		saveCurrentActions(actionBars);
		actionHandler = new CellEditorActionHandler(actionBars);
		actionHandler.addCellEditor(getCellEditor());
		actionBars.updateActionBars();
	}

	/**
	 * Permet de restaurer l'état des actions après l'édition.
	 * @param actionBars Barre contenant les actions
	 */
	private void restoreSavedActions(IActionBars actionBars) {
		actionBars.setGlobalActionHandler(ActionFactory.COPY.getId(), copy);
		actionBars.setGlobalActionHandler(ActionFactory.PASTE.getId(), paste);
		actionBars.setGlobalActionHandler(ActionFactory.DELETE.getId(), delete);
		actionBars.setGlobalActionHandler(ActionFactory.SELECT_ALL.getId(), selectAll);
		actionBars.setGlobalActionHandler(ActionFactory.CUT.getId(), cut);
		actionBars.setGlobalActionHandler(ActionFactory.FIND.getId(), find);
		actionBars.setGlobalActionHandler(ActionFactory.UNDO.getId(), undo);
		actionBars.setGlobalActionHandler(ActionFactory.REDO.getId(), redo);
	}

	/**
	 * Sauvegarde de l'état des actions avant l'édition.
	 * @param actionBars Barre contenant les actions
	 */
	private void saveCurrentActions(IActionBars actionBars) {
		copy = actionBars.getGlobalActionHandler(ActionFactory.COPY.getId());
		paste = actionBars.getGlobalActionHandler(ActionFactory.PASTE.getId());
		delete = actionBars.getGlobalActionHandler(ActionFactory.DELETE.getId());
		selectAll = actionBars.getGlobalActionHandler(ActionFactory.SELECT_ALL.getId());
		cut = actionBars.getGlobalActionHandler(ActionFactory.CUT.getId());
		find = actionBars.getGlobalActionHandler(ActionFactory.FIND.getId());
		undo = actionBars.getGlobalActionHandler(ActionFactory.UNDO.getId());
		redo = actionBars.getGlobalActionHandler(ActionFactory.REDO.getId());
	}

	/**
	 * Mise à jour de la taille des polices en fonction du facteur de zoom
	 * @param zoom Zoom actuel
	 */
	private void updateScaledFont(double zoom) {
		if (cachedZoom == zoom) {
			return;
		}

		Text text = (Text) getCellEditor().getControl();
		Font font = getEditPart().getFigure().getFont();

		disposeScaledFont();
		cachedZoom = zoom;
		if (zoom == 1.0) {
			text.setFont(font);
		} else {
			FontData fd = font.getFontData()[0];
			font.dispose();
			fd.setHeight((int) (fd.getHeight() * zoom));
			scaledFont = new Font(font.getDevice(), fd);
			text.setFont(scaledFont);
		}
	}
}
