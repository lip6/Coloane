package fr.lip6.move.coloane.core.ui.properties;

import java.util.List;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * Classe permettant de simplifiant la création d'un LabelText
 */
public class LabelTextFactory {
	private final Composite parent;
	private final TabbedPropertySheetWidgetFactory factory;

	private IAttributeLabel last;

	/**
	 * @param parent parent fourni à tous les LabelText créé
	 * @param factory factory fourni à tous les LabelText créé
	 */
	public LabelTextFactory(Composite parent, TabbedPropertySheetWidgetFactory factory) {
		this.parent = parent;
		this.factory = factory;
	}

	/**
	 * Création de LabelText les uns en dessous des autres.
	 * @param label label
	 * @param value valeur
	 * @param style style SWT
	 * @return un nouveau LabelText
	 */
	public final IAttributeLabel create(String label, String value, int style) {
		LabelText lt;
		if (last == null) {
			lt = new LabelText(
					parent,
					factory,
					label,
					value,
					style);
		} else {
			lt = new LabelText(
					parent,
					factory,
					label,
					value,
					style,
					last);
		}
		// On conserve le dernier LabelText créé pour que le suivant soient placé en dessous.
		last = lt;
		return lt;
	}

	/**
	 * Creation d'un widget attribut muni d'une liste déroulante pour l'edition.
	 * @param label le nom de l'attribut
	 * @param value sa valeur initiale
	 * @param enumeration les valeurs possibles (a priori spécifiées via le formalisme)
	 * @return an attribute label
	 */
	public IAttributeLabel create(String label, String value,
			List<String> enumeration) {
		IAttributeLabel lt;
		if (last == null) {
			lt = new LabelCombo(parent, factory, label, value, enumeration);
		} else {
			lt = new LabelCombo(parent, factory, label, value, enumeration, last);
		}
		last = lt;
		return lt;
	}
}

