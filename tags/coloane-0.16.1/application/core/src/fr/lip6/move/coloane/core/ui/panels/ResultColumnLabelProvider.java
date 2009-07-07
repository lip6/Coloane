package fr.lip6.move.coloane.core.ui.panels;

import fr.lip6.move.coloane.core.results.IResultTree;

import org.eclipse.jface.viewers.ColumnLabelProvider;

/**
 * Classe qui permet à la vue de récupérer le texte en fonction de la colonne et
 * de l'objet element passé à getText qui correspond à un IResultTree donc une
 * ligne de résultat.
 */
public class ResultColumnLabelProvider extends ColumnLabelProvider {
	private final int column;

	/**
	 * @param column colonne
	 */
	public ResultColumnLabelProvider(int column) {
		this.column = column;
	}

	/** {@inheritDoc} */
	@Override
	public final String getText(Object element) {
		IResultTree node = (IResultTree) element;
		if (node.getElement().size() > column) {
			return (String) node.getElement().get(column);
		} else {
			return ""; //$NON-NLS-1$
		}
	}

}
