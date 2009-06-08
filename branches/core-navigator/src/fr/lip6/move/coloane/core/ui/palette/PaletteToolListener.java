package fr.lip6.move.coloane.core.ui.palette;

import org.eclipse.gef.palette.PaletteListener;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gef.tools.AbstractTool;
import org.eclipse.gef.ui.palette.PaletteViewer;


/**
 * Un ecouteur pour la palette<br>
 * Cet ecouteur permet de detecter l'outil en cours d'utilisation.
 * @see ColoaneEditor#createPaletteViewerProvider()
 */
public class PaletteToolListener implements PaletteListener {

	/**
	 * {@inheritDoc}
	 */
	public final void activeToolChanged(PaletteViewer viewer, ToolEntry tool) {
		tool.setToolProperty(AbstractTool.PROPERTY_UNLOAD_WHEN_FINISHED, Boolean.FALSE);
	}

}
