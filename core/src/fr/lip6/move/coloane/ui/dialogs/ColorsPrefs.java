package fr.lip6.move.coloane.ui.dialogs;

import fr.lip6.move.coloane.main.Coloane;

import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;


import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class ColorsPrefs extends PreferencePage implements
		IWorkbenchPreferencePage {

	private ColorFieldEditor nodeColorEditor;
	private ColorFieldEditor nodeColorEditor1;
	private ColorFieldEditor nodeColorEditor2;

	private ColorFieldEditor arcColorEditor;
	private ColorFieldEditor arcColorEditor1;

	private static final String COLORNODE = "colorNode";
	private static final String COLORNODE_HIGHLIGHT = "colorNode_Highlight";
	private static final String COLORNODE_MOUSE = "colorNode_Mouse";

	private static final String COLORARC = "colorArc";
	private static final String COLORARC_HIGHLIGHT = "colorArc_Highlight";

	public final void init(IWorkbench workbench) {
		setPreferenceStore(Coloane.getDefault().getPreferenceStore());
	}

	protected final Control createContents(Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);

		// Create a data that takes up the extra space in the dialog .
		GridData data = new GridData(GridData.FILL_HORIZONTAL);
		data.grabExcessHorizontalSpace = true;
		composite.setLayoutData(data);

		GridLayout layout = new GridLayout();
		composite.setLayout(layout);

		Composite colorComposite = new Composite(composite, SWT.NONE);

		colorComposite.setLayout(new GridLayout());

		colorComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		//NODE
		Group node = new Group(colorComposite, SWT.NONE);
		node.setText("Node");
		node.setLayoutData(data);

		//Node color
		nodeColorEditor = new ColorFieldEditor(COLORNODE, "Color:", node);
		nodeColorEditor.setPreferenceStore(getPreferenceStore());
		nodeColorEditor.load();

		//Node highlight color
		nodeColorEditor1 = new ColorFieldEditor(COLORNODE_HIGHLIGHT, "Highlight:", node);
		nodeColorEditor1.setPreferenceStore(getPreferenceStore());
		nodeColorEditor1.load();

		//Node mouseover color
		nodeColorEditor2 = new ColorFieldEditor(COLORNODE_MOUSE, "Mouse over:", node);
		nodeColorEditor2.setPreferenceStore(getPreferenceStore());
		nodeColorEditor2.load();

		//ARC
		Group arc = new Group(colorComposite, SWT.NONE);
		arc.setText("Arc");
		arc.setLayoutData(data);

		//Node color
		arcColorEditor = new ColorFieldEditor(COLORARC, "Color:", arc);
		arcColorEditor.setPreferenceStore(getPreferenceStore());
		arcColorEditor.load();

		//Node highlight color
		arcColorEditor1 = new ColorFieldEditor(COLORARC_HIGHLIGHT, "Highlight:", arc);
		arcColorEditor1.setPreferenceStore(getPreferenceStore());
		arcColorEditor1.load();


		return composite;
	}

	/**
	 * Performs special processing when this page's Restore Defaults button has been pressed.
	 * Sets the contents of the nameEntry field to
	 * be the default
	 */
	protected final void performDefaults() {
		nodeColorEditor.loadDefault();
		nodeColorEditor1.loadDefault();
		nodeColorEditor2.loadDefault();
		arcColorEditor.loadDefault();
		arcColorEditor1.loadDefault();
	}
	/**
	 * Method declared on IPreferencePage. Save the
	 * author name to the preference store.
	 */
	public final boolean performOk() {
		nodeColorEditor.store();
		nodeColorEditor1.store();
		nodeColorEditor2.store();
		arcColorEditor.store();
		arcColorEditor1.store();
		return super.performOk();
	}

	//TODO performApply effectue un performOK, rajouter un redraw a preformOK

	/**
	 * Return a new Color based on preference string "r,g,b"
	 * @param key
	 * @return Color
	 */
	public static Color setColor(String key) {
		String s = Coloane.getDefault().getPreference(key);
		String[] rgb = s.split(",");
		return new Color(null, Integer.parseInt(rgb[0]), Integer.parseInt(rgb[1]), Integer.parseInt(rgb[2]));
	}
}
