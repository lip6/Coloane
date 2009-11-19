package its.ui.forms;

import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.ScrolledForm;

import testits.editors.MultiPageEditor;

/**
 * Hold a Master/Details GUI design pattern Forms based ui.
 * Master is a TypeList tree view, details show all relevant data.
 * @author Yann
 */
public final class MasterDetailsPage extends FormPage {
	private ScrolledPropertiesBlock block;
	private MultiPageEditor mpe;
	/**
	 * Ctor.
	 * @param editor the parent editor (for getTypes())
	 */
	public MasterDetailsPage(MultiPageEditor editor) {
		super(editor, "treeview", "Types Editor"); //$NON-NLS-1$ //$NON-NLS-2$
		block = new ScrolledPropertiesBlock(this);
		this.mpe = editor;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void createFormContent(final IManagedForm managedForm) {
		final ScrolledForm form = managedForm.getForm();
		//FormToolkit toolkit = managedForm.getToolkit();
		form.setText("Tree view"); //$NON-NLS-1$
		form.setBackgroundImage(ITSEditorPlugin.getDefault().getImage(ITSEditorPlugin.IMG_FORM_BG));
		block.createContent(managedForm);
	}
	/**
	 * @return the parent editor
	 */
	public MultiPageEditor getMpe() {
		return mpe;
	}
	/**
	 * Refresh display
	 */
	public void refresh() {
		block.refresh();
	}
}

