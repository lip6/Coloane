package fr.lip6.move.coloane.projects.its.checks.ui;

import fr.lip6.move.coloane.projects.its.ITypeListProvider;
import fr.lip6.move.coloane.projects.its.TypeList;
import fr.lip6.move.coloane.projects.its.checks.CTLCheckService;
import fr.lip6.move.coloane.projects.its.checks.CheckList;
import fr.lip6.move.coloane.projects.its.checks.CheckService;
import fr.lip6.move.coloane.projects.its.checks.OrderingService;
import fr.lip6.move.coloane.projects.its.plugin.editors.MultiPageEditor;
import fr.lip6.move.coloane.projects.its.ui.forms.ITSEditorPlugin;

import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.widgets.ScrolledForm;


/**
 * Hold a Master/Details GUI design pattern Forms based ui.
 * Master is a TypeList tree view, details show all relevant data.
 * @author Yann
 */
public final class ChecksMasterDetailsPage extends FormPage implements ITypeListProvider {
	private ChecksScrolledPropertiesBlock block;
	private MultiPageEditor mpe;
	private CheckList checkList;
	/**
	 * Ctor.
	 * @param editor the parent editor (for getTypes())
	 * @param cl 
	 */
	public ChecksMasterDetailsPage(MultiPageEditor editor, CheckList cl) {
		super(editor, "treeview", cl.getType().getTypeName()+ " analysis"); //$NON-NLS-1$ //$NON-NLS-2$
		block = new ChecksScrolledPropertiesBlock(this);
		this.mpe = editor;
		this.checkList = cl;
		
		cl.addCheck(new OrderingService(cl));
		cl.addCheck(new CheckService(cl));
		cl.addCheck(new CTLCheckService(cl));
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
	public TypeList getTypes() {
		return mpe.getTypes();
	}
	
	public CheckList getCheckList() {
		return checkList;
	}
}

