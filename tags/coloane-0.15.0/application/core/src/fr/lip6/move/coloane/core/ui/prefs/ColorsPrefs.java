package fr.lip6.move.coloane.core.ui.prefs;

import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.ui.dialogs.Messages;

import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * Color preference page
 */
public class ColorsPrefs extends PreferencePage implements IWorkbenchPreferencePage {

	private ColorFieldEditor nodeColorEditor;
	private ColorFieldEditor nodeColorEditor1;
	private ColorFieldEditor nodeColorEditor2;

	private ColorFieldEditor arcColorEditor;
	private ColorFieldEditor arcColorEditor1;

	/** {@inheritDoc} */
	public final void init(IWorkbench workbench) {
		setPreferenceStore(Coloane.getInstance().getPreferenceStore());
	}

	/** {@inheritDoc} */
	@Override
	protected final Control createContents(Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);
		composite.setLayout(new GridLayout(2, false));

		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		data.horizontalSpan = 2;
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;

		//NODE GROUP
		Group node = new Group(composite, SWT.NONE);
		node.setText(Messages.ColorsPrefs_5);
		node.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		node.setLayout(layout);


		//Node color
		nodeColorEditor = new ColorFieldEditor("COLORNODE", Messages.ColorsPrefs_6, node); //$NON-NLS-1$
		nodeColorEditor.setPreferenceStore(getPreferenceStore());
		nodeColorEditor.load();

		//Node highlight color
		nodeColorEditor1 = new ColorFieldEditor("COLORNODE_HIGHLIGHT", Messages.ColorsPrefs_7, node); //$NON-NLS-1$
		nodeColorEditor1.setPreferenceStore(getPreferenceStore());
		nodeColorEditor1.load();

		//Node mouseover color
		nodeColorEditor2 = new ColorFieldEditor("COLORNODE_MOUSE", Messages.ColorsPrefs_8, node); //$NON-NLS-1$
		nodeColorEditor2.setPreferenceStore(getPreferenceStore());
		nodeColorEditor2.load();

		//ARC GROUP
		Group arc = new Group(composite, SWT.NONE);
		arc.setText(Messages.ColorsPrefs_9);
		arc.setLayoutData(data);

		//Node color
		arcColorEditor = new ColorFieldEditor("COLORARC", Messages.ColorsPrefs_10, arc); //$NON-NLS-1$
		arcColorEditor.setPreferenceStore(getPreferenceStore());
		arcColorEditor.load();

		//Node highlight color
		arcColorEditor1 = new ColorFieldEditor("COLORARC_HIGHLIGHT", Messages.ColorsPrefs_11, arc); //$NON-NLS-1$
		arcColorEditor1.setPreferenceStore(getPreferenceStore());
		arcColorEditor1.load();

		return composite;
	}

	/**
	 * Performs special processing when this page's Restore Defaults button has been pressed.
	 * Sets the contents of the nameEntry field to
	 * be the default
	 */
	@Override
	protected final void performDefaults() {
		nodeColorEditor.loadDefault();
		nodeColorEditor1.loadDefault();
		nodeColorEditor2.loadDefault();
		arcColorEditor.loadDefault();
		arcColorEditor1.loadDefault();
	}

	/** {@inheritDoc} */
	@Override
	public final boolean performOk() {
		nodeColorEditor.store();
		nodeColorEditor1.store();
		nodeColorEditor2.store();
		arcColorEditor.store();
		arcColorEditor1.store();
		return super.performOk();
	}

	/**
	 * Return a new Color based on preference string "r,g,b"
	 * @param key color
	 * @return Color object Color
	 */
	public static Color getColor(String key) {
		Color color = JFaceResources.getColorRegistry().get(key);
		if (color == null) {
			RGB rgb = PreferenceConverter.getColor(Coloane.getInstance().getPreferenceStore(), key);
			JFaceResources.getColorRegistry().put(key, rgb);
			color = JFaceResources.getColorRegistry().get(key);
		}
		return color;
	}
}
