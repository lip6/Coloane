package fr.lip6.move.coloane.projects.its.ui.forms;

import fr.lip6.move.coloane.projects.its.Concept;
import fr.lip6.move.coloane.projects.its.ITypeListProvider;
import fr.lip6.move.coloane.projects.its.TypeDeclaration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
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
public final class ConceptDetailsPage extends ITSDetailsPage<Concept> {

	private Text conceptNametf;
	private Combo effectiveEditor;
	private ITypeListProvider types;
	private Table requiredTable;
	private TableViewer viewer;

	/**
	 * set the types list
	 * @param types the types
	 */
	public ConceptDetailsPage(ITypeListProvider types) {
		this.types = types;
	}

	/**
	 * {@inheritDoc}
	 *  (non-Javadoc)
	 * @see org.eclipse.ui.forms.IDetailsPage#createContents(org.eclipse.swt.widgets.Composite)
	 */
	public void createContents(Composite parent) {
		TableWrapLayout layout = new TableWrapLayout();

		parent.setLayout(layout);

		FormToolkit toolkit = getToolkit();
		Section s1 = toolkit.createSection(parent, ExpandableComposite.TITLE_BAR);
		s1.marginWidth = 4;
		s1.marginHeight = 4;
		s1.setText("Concept Definition"); //$NON-NLS-1$
		//		s1.setDescription(Messages.getString("TypeOneDetailsPage.name")); //$NON-NLS-1$
		TableWrapData td = new TableWrapData(TableWrapData.FILL, TableWrapData.TOP);
		td.grabHorizontal = true;
		s1.setLayoutData(td);
		Composite client = toolkit.createComposite(s1);
		GridLayout glayout = new GridLayout();
		glayout.marginWidth = 10;
		glayout.marginHeight = 5;
		glayout.numColumns = 2;
		client.setLayout(glayout);

		GridData gd;

		toolkit.createLabel(client, "Concept Name"); //$NON-NLS-1$
		conceptNametf = toolkit.createText(client, "", SWT.SINGLE); //$NON-NLS-1$
		conceptNametf.setEditable(false);
		gd = new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING);
		gd.widthHint = 10;
		conceptNametf.setLayoutData(gd);

		toolkit.createLabel(client, "Effective Type"); //$NON-NLS-1$
		gd = new GridData(GridData.FILL_HORIZONTAL | GridData.VERTICAL_ALIGN_BEGINNING);
		gd.widthHint = 10;

		effectiveEditor = new Combo(client, SWT.DROP_DOWN);
		effectiveEditor.setLayoutData(gd);
		effectiveEditor.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				Concept concept = getInput();
				int n = effectiveEditor.getSelectionIndex();
				if (n == -1) {
					return;
				}
				String[] suggs = effectiveEditor.getItems();

				for (TypeDeclaration type : types.getTypes()) {
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
		viewer.setInput(getInput());

		gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 50;
		gd.widthHint = 30;

		requiredTable.setLayoutData(gd);
 
		toolkit.paintBordersFor(s1);
		toolkit.paintBordersFor(client);
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
		public void dispose() {
		}

		/**
		 * {@inheritDoc}
		 */
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
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
		for (TypeDeclaration type : types.getTypes()) {
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
	protected void update() {
		Concept input = getInput();
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

}
