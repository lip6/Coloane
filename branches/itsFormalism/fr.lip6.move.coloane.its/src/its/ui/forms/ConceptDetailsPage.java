package its.ui.forms;

import its.Concept;
import its.TypeDeclaration;
import its.TypeList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IDetailsPage;
import org.eclipse.ui.forms.IFormPart;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

/**
 * Implements the details page of a concept.
 * @author Yann
 *
 */
public final class ConceptDetailsPage implements IDetailsPage {

	private IManagedForm mform;
	private Concept input;
	private Text conceptNametf;
	private Combo effectiveEditor;
	private TypeList types;
	private Table requiredTable;
	private TableViewer viewer;

	/**
	 * set the types list
	 * @param types the types
	 */
	public ConceptDetailsPage(TypeList types) {
		this.types = types;
	}

	/**
	 * {@inheritDoc}
	 *  (non-Javadoc)
	 * @see org.eclipse.ui.forms.IDetailsPage#initialize(org.eclipse.ui.forms.IManagedForm)
	 */
	public void initialize(IManagedForm mform) {
		this.mform = mform;
	}
	/**
	 * {@inheritDoc}
	 *  (non-Javadoc)
	 * @see org.eclipse.ui.forms.IDetailsPage#createContents(org.eclipse.swt.widgets.Composite)
	 */
	public void createContents(Composite parent) {
		TableWrapLayout layout = new TableWrapLayout();
		layout.topMargin = 5;
		layout.leftMargin = 5;
		layout.rightMargin = 2;
		layout.bottomMargin = 2;
		parent.setLayout(layout);

		FormToolkit toolkit = mform.getToolkit();
		Section s1 = toolkit.createSection(parent, Section.DESCRIPTION | ExpandableComposite.TITLE_BAR);
		s1.marginWidth = 10;
		s1.setText("Concept Definition"); //$NON-NLS-1$
		//		s1.setDescription(Messages.getString("TypeOneDetailsPage.name")); //$NON-NLS-1$
		TableWrapData td = new TableWrapData(TableWrapData.FILL, TableWrapData.TOP);
		td.grabHorizontal = true;
		s1.setLayoutData(td);
		Composite client = toolkit.createComposite(s1);
		GridLayout glayout = new GridLayout();
		glayout.marginWidth = 0;
		glayout.marginHeight = 0;
		glayout.numColumns = 2;
		client.setLayout(glayout);

		GridData gd;
		//		choices = new Button[TypeOne.CHOICES.length];
		//		for (int i=0; i<TypeOne.CHOICES.length; i++) {
		//			choices[i] = toolkit.createButton(client, TypeOne.CHOICES[i], SWT.RADIO);
		//			choices[i].setData(new Integer(i));
		//			choices[i].addSelectionListener(choiceListener);
		//			gd = new GridData();
		//			gd.horizontalSpan = 2;
		//			choices[i].setLayoutData(gd);
		//		}
		//		createSpacer(toolkit, client, 2);
		//		flag = toolkit.createButton(client, Messages.getString("TypeOneDetailsPage.check"), SWT.CHECK); //$NON-NLS-1$
		//		flag.addSelectionListener(new SelectionAdapter() {
		//			public void widgetSelected(SelectionEvent e) {
		//				if (input!=null)
		//					input.setFlag(flag.getSelection());
		//			}
		//		});
		//		gd = new GridData();
		//		gd.horizontalSpan = 2;
		//		flag.setLayoutData(gd);
		//		createSpacer(toolkit, client, 2);

		toolkit.createLabel(client, "Concept Name"); //$NON-NLS-1$
		conceptNametf = toolkit.createText(client, "", SWT.SINGLE); //$NON-NLS-1$
		conceptNametf.setEditable(false);
		gd = new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING);
		gd.widthHint = 10;
		conceptNametf.setLayoutData(gd);

		toolkit.createLabel(client, "Effective Type"); //$NON-NLS-1$
		//		conceptEffectivetf = toolkit.createText(client, "", SWT.DROP_DOWN); //$NON-NLS-1$
		//		TextViewer tviewer = new TextViewer(client, SWT.DROP_DOWN);
		//		tviewer.setInput(input);
		//		setConceptEditor();
		gd = new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING);
		gd.widthHint = 10;
		//		conceptEffectivetf.setLayoutData(gd);

		effectiveEditor = new Combo(client, SWT.DROP_DOWN);
		effectiveEditor.setLayoutData(gd);
		effectiveEditor.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				Concept concept = input;
				int n = effectiveEditor.getSelectionIndex();
				if (n == -1) {
					return;
				}
				String[] suggs = effectiveEditor.getItems();

				for (TypeDeclaration type : types) {
					if (type.getTypeName().equals(suggs[n])) {
						concept.setEffective(type);
						break;
					}
				}
			}
		});

		createSpacer(toolkit, client, 2);

		toolkit.createLabel(client, "Interface"); //$NON-NLS-1$
		requiredTable = toolkit.createTable(client, SWT.SINGLE);
		viewer = new TableViewer(requiredTable);
		viewer.setContentProvider(new RequiredConceptsProvider());
		//		viewer.setLabelProvider(new RequiredConceptsLabelProvider());
		viewer.setInput(input);

		gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 50;
		gd.widthHint = 30;

		requiredTable.setLayoutData(gd);

		//		FormText rtext = toolkit.createFormText(parent, true);
		//		rtext.setText(RTEXT_DATA, true, false);
		//		td = new TableWrapData(TableWrapData.FILL, TableWrapData.TOP);
		//		td.grabHorizontal = true;
		//		rtext.setLayoutData(td);

		toolkit.paintBordersFor(s1);
		s1.setClient(client);
	}

	/**
	 * An internal class that provides the required interface of a concept.
	 * @author Yann
	 *
	 */
	final class RequiredConceptsProvider implements IStructuredContentProvider {

		/**
		 * {@inheritDoc}
		 */
		@Override
		public Object[] getElements(Object inputElement) {
			if (inputElement instanceof Concept) {
				Concept concept = (Concept) inputElement;
				String[] items = concept.getLabels().toArray(new String[concept.getLabels().size()]);
				Arrays.sort(items);
				return items;
			}
			return new String[0];
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void dispose() {
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}

	/**
	 * Create a nice separator between two subsections.
	 * @param toolkit the toolkit
	 * @param parent the parent
	 * @param span the span of the spacer
	 */
	private void createSpacer(FormToolkit toolkit, Composite parent, int span) {
		Label spacer = toolkit.createLabel(parent, ""); //$NON-NLS-1$
		GridData gd = new GridData();
		gd.horizontalSpan = span;
		spacer.setLayoutData(gd);
	}

	/**
	 * Extract the required labels andd scan type list for matching types.
	 * @param concept the concept to satisfy
	 * @return the matching type names
	 */
	private String[] getSuggestions(Concept concept) {
		if (concept == null) {
			return new String[0];
		}
		// build suggestion list
		List<String> req = concept.getLabels();
		List<String> suggestions = new ArrayList<String>();
		for (TypeDeclaration type : types) {
			if (type == concept.getParent()) {
				continue;
			}
			if (type.getLabels().containsAll(req)) {
				suggestions.add(type.getTypeName());
			}
		}
		return suggestions.toArray(new String[suggestions.size()]);
	}

	/**
	 * Refresh everybody.
	 */
	private void update() {
		// CHECKSTYLE OFF
		conceptNametf.setText(input != null && input.getName() != null ? input.getName() : ""); //$NON-NLS-1$
		// CHECKSTYLE ON
		String[] items = getSuggestions(input);
		effectiveEditor.setItems(items);
		for (int i = 0; i < items.length; i++) {
			if (input.getEffective() != null && items[i].equals(input.getEffective().getTypeName())) {
				effectiveEditor.select(i);
				break;
			}
		}
		viewer.setInput(input);
	}
	/**
	 * {@inheritDoc}
	 * (non-Javadoc)
	 * @see org.eclipse.ui.forms.IDetailsPage#inputChanged(org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void selectionChanged(IFormPart part, ISelection selection) {
		IStructuredSelection ssel = (IStructuredSelection) selection;
		if (ssel.size() == 1) {
			input = (Concept)  ssel.getFirstElement();
		} else {
			input = null;
		}
		update();
	}
	/**
	 * {@inheritDoc}
	 *  (non-Javadoc)
	 * @see org.eclipse.ui.forms.IDetailsPage#commit()
	 */
	public void commit(boolean onSave) {
	}
	/**
	 * {@inheritDoc}
	 *  (non-Javadoc)
	 * @see org.eclipse.ui.forms.IDetailsPage#setFocus()
	 */
	public void setFocus() {
	}
	/**
	 * {@inheritDoc}
	 *  (non-Javadoc)
	 * @see org.eclipse.ui.forms.IDetailsPage#dispose()
	 */
	public void dispose() {
	}
	/**
	 * {@inheritDoc}
	 *  (non-Javadoc)
	 * @see org.eclipse.ui.forms.IDetailsPage#isDirty()
	 */
	public boolean isDirty() {
		return false;
	}
	/**
	 * {@inheritDoc}
	 *  (non-Javadoc)
	 * @see org.eclipse.ui.forms.IDetailsPage#isStale()
	 */
	public boolean isStale() {
		return false;
	}
	/**
	 * {@inheritDoc}
	 *  (non-Javadoc)
	 * @see org.eclipse.ui.forms.IDetailsPage#refresh()
	 */
	public void refresh() {
		update();
	}
	/**
	 * {@inheritDoc}
	 *  (non-Javadoc)
	 * @see org.eclipse.ui.forms.IDetailsPage#setFormInput()
	 */
	public boolean setFormInput(Object input) {
		return false;
	}

}
