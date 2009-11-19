package fr.lip6.move.coloane.its.plugin.editors;


import fr.lip6.move.coloane.core.ui.files.ModelWriter;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.its.CompositeTypeDeclaration;
import fr.lip6.move.coloane.its.Concept;
import fr.lip6.move.coloane.its.TypeDeclaration;
import fr.lip6.move.coloane.its.TypeList;
import fr.lip6.move.coloane.its.actions.AddTypeAction;
import fr.lip6.move.coloane.its.conceptsui.ConceptsTable;
import fr.lip6.move.coloane.its.flatten.ModelFlattener;
import fr.lip6.move.coloane.its.io.ITSModelWriter;
import fr.lip6.move.coloane.its.labelsui.LabelsTable;
import fr.lip6.move.coloane.its.obs.ISimpleObserver;
import fr.lip6.move.coloane.its.tpnui.NodesTable;
import fr.lip6.move.coloane.its.typesui.TypesTable;
import fr.lip6.move.coloane.its.ui.forms.MasterDetailsPage;
import fr.lip6.move.coloane.tools.layout.GraphLayout;


import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.FileEditorInput;

/**
 * An example showing how to create a multi-page editor.
 * This example has 3 pages:
 * <ul>
 * <li>page 0 contains a nested text editor.
 * <li>page 1 allows you to change the font used in page 2
 * <li>page 2 shows the words in page 0 in sorted order
 * </ul>
 */
public final class MultiPageEditor
	extends FormEditor
	implements IResourceChangeListener, ISimpleObserver {

	/** The text editor used in page 0. */
	private TextEditor editor;

	/** The text widget used in page 2. */
	private TypesTable table;

	private TypeList types = null;

	private Composite detailITSCZone;

	private Composite typePage;

	private ConceptsTable concepts;

	private LabelsTable requiredLabels;

	private Composite detailTPNZone;

	private NodesTable tpnTable;

	private Button flatTypeButton;

	private Button exportTypeButton;

	private TypeDeclaration currentSelectedTypeDecl;

	private boolean isDirty = false;

	private AddTypeAction addAction;

	private MasterDetailsPage treePage;
	/**
	 * Creates a multi-page editor example.
	 */
	public MultiPageEditor() {
		super();
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this);
		setTypes(new TypeList());
	}

	/** 
	 * Handle setting of types + registering of observer.
	 * @param types the types to set as input.
	 */
	public void setTypes(TypeList types) {
		if (types != null) {
			types.deleteObserver(this);
		}
		this.types = types;
		types.addObserver(this);
	}
	/**
	 * Creates page 0 of the multi-page editor,
	 * which contains a text editor.
	 */
	void createPage0() {
		try {
			editor = new TextEditor();
			int index = addPage(editor, getEditorInput());
			setPageText(index, editor.getTitle());
		} catch (PartInitException e) {
			ErrorDialog.openError(getSite().getShell(),
					"Error creating nested text editor",
					null,
					e.getStatus());
		}
	}
	/**
	 * Creates page 1 of the multi-page editor,
	 * which allows you to change the font used in page 2.
	 */
	void createPage1() {

		typePage = new Composite(getContainer(), SWT.NONE);
		FillLayout layout = new FillLayout();
		layout.type = SWT.VERTICAL;
		typePage.setLayout(layout);

		Composite tableContainer = new Composite(typePage, SWT.NONE);
		tableContainer.setLayout(new FillLayout());
		// create a table
		table = new TypesTable(types);
		table.createWidgets(tableContainer);
		// Make the selection available
		getSite().setSelectionProvider(table.getViewer());




		detailITSCZone = new Composite(typePage, SWT.NONE);
		detailITSCZone.setLayout(new FillLayout());
		createITSCDetails(detailITSCZone);

		detailTPNZone = new Composite(typePage, SWT.NONE);
		detailTPNZone.setLayout(new FillLayout());
		createTPNDetails(detailTPNZone);


		// Create Buttons
		Composite buttonZone = new Composite(typePage, SWT.NONE);
		RowLayout gl = new RowLayout();
		buttonZone.setLayout(gl);

		Button addTypeButton = new Button(buttonZone, SWT.NONE);
		addTypeButton.setText("Add a type");
		addTypeButton.setToolTipText("Select a model file to load as an ITS type declaration.");
		//
		//		newTypeTextfield = new Text(buttonZone, WT.BORDER);
		//		newTypeTextfield.setText("Type"+ types.size());
		//		newTypeTextfield.setToolTipText("Enter the name under which to import your new type");


		flatTypeButton = new Button(buttonZone, SWT.PUSH);
		flatTypeButton.setText("Flatten a type");
		flatTypeButton.setToolTipText("Flatten the selected type to a model bearing the current Type declaration name.");


		//		layoutTypeButton = new Button(buttonZone, SWT.PUSH);
		//		layoutTypeButton.setText("Layout a coloane graph");
		//		layoutTypeButton.setToolTipText("Use ATT graphviz to layout the graph.");

		exportTypeButton = new Button(buttonZone, SWT.PUSH);
		exportTypeButton.setText("Export to SDD-ITS");
		exportTypeButton.setToolTipText("Export the instance Romeo-SDD format.");

		/** add listener to connect the two tables */
		table.getViewer().getTable().addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				TableItem ti = (TableItem) event.item;
				currentSelectedTypeDecl = (TypeDeclaration) ti.getData();
				displayDetails(currentSelectedTypeDecl);
				typePage.redraw();
				if (currentSelectedTypeDecl.isSatisfied()) {
					flatTypeButton.setEnabled(true);
				} else {
					flatTypeButton.setEnabled(false);
				}
			}
		});

		table.getViewer().addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				openEditor(currentSelectedTypeDecl);
			}
		});

		//		layoutTypeButton.addSelectionListener(new SelectionAdapter() {
		//			@Override
		//			public void widgetSelected(SelectionEvent event) {
		//				GraphLayout.layout(currentSelectedTypeDecl.getGraph());
		//
		//			}
		//		});

		flatTypeButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				ModelFlattener mf = new ModelFlattener();
				try {
					mf.doFlatten((CompositeTypeDeclaration) currentSelectedTypeDecl);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					FileDialog fileDialog = new FileDialog(getSite().getShell(), SWT.SAVE);
					// fontDialog.setFontList(text.getFont().getFontData());
					String filePath = fileDialog.open();
					if (filePath == null) {
						ErrorDialog.openError(getSite().getShell(),
								"Cancelled by user",
								"Add new type operation cancelled by user",
								new Status(1, "fr.lip6.move.coloane.its", "Cancel Dialog"));
					}
					IPath path = new Path(filePath);
					IFile outputff = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(path);
					IGraph flatModel = mf.getFlatModel();
					GraphLayout.layout(flatModel);
					outputff.create(new ByteArrayInputStream(ModelWriter.translateToXML(flatModel).getBytes()), 0, null);
					IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
					try {
						if (outputff.exists()) {
							IDE.openEditor(page, outputff);
						}
					} catch (PartInitException e) {
						e.printStackTrace();
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});


		exportTypeButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				exportToSDD(currentSelectedTypeDecl);
			}
		});

		addAction = new AddTypeAction(this);
		addTypeButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				addAction.run();
			}
		});

		int index = addPage(typePage);
		setPageText(index, "new table");
	}

	/**
	 * Export a given type to SDD XML format : starts a small directory chooser dialog.
	 * @param td the type to export.
	 */
	public void exportToSDD(TypeDeclaration td) {
		ITSModelWriter mw = new ITSModelWriter();
		try {
			DirectoryDialog dialog = new DirectoryDialog(getSite().getShell());
			String directory = dialog.open();
			mw.exportITSModel(types, td, directory);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Return the unique add action associated to this editor.
	 * {@link AddTypeAction}
	 * @return the add action.
	 */
	public AddTypeAction getAddAction() {
		return addAction;
	}
	/**
	 * open or focus the editor on the file proposed
	 * @param td current slected type
	 */
	public void openEditor(TypeDeclaration td) {
		if (td != null) {
			IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			try {
				if (td != null) {
					IDE.openEditor(page, td.getTypeFile());
				}
			} catch (PartInitException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * Build the composite details section
	 * @param parent the parent composite
	 */
	private void createITSCDetails(Composite parent) {
		Text title = new Text(parent, SWT.BORDER);
		title.setEditable(false);
		title.setText("Composite Type Details");

		/** Build two tables: Table 1 has available Concept names
		 *  Table 2 has the required labels for this concept.
		 */
		concepts = new ConceptsTable(null, types);
		concepts.createWidgets(parent);

		requiredLabels = new LabelsTable(null, "Required labels");
		requiredLabels.createWidgets(parent);

		/** synchronize display of the two tables */
		concepts.getViewer().getTable().addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				TableItem ti = (TableItem) event.item;
				Concept typeDecl = (Concept) ti.getData();
				requiredLabels.getViewer().setInput(typeDecl.getLabels());
				requiredLabels.getViewer().refresh();
			}
		});

	}

	/**
	 * Table view oriented : build the interface of a TPN.
	 * @param parent the parent
	 */
	private void createTPNDetails(Composite parent) {
		Text title = new Text(parent, SWT.BORDER);
		title.setEditable(false);
		title.setText("TPN Type Details");

		tpnTable = new NodesTable(null);
		tpnTable.createWidgets(parent);
	}

	/**
	 * Shows the composite holding the details zone.
	 * @param typeDecl the currently focused typedecl
	 */
	protected void displayDetails(TypeDeclaration typeDecl) {
		detailITSCZone.setVisible(false);
		detailTPNZone.setVisible(false);
		if (typeDecl.getTypeType().equals("Time Petri Net")) {
			displayTPNdetails(typeDecl);
			detailTPNZone.setVisible(true);
		} else if (typeDecl.getTypeType().equals("ITSComposite")) {
			displayITSCdetails((CompositeTypeDeclaration) typeDecl);
			detailITSCZone.setVisible(true);
		}
		// Scalar / circular not supported
	}

	/**
	 * called to update tpn details
	 * @param typeDecl the selected tpn
	 */
	private void displayTPNdetails(TypeDeclaration typeDecl) {
		tpnTable.getViewer().setInput(typeDecl.getGraph());
	}
	/**
	 * called to update the composite details view
	 * @param ctd the current selected ctd
	 */
	private void displayITSCdetails(CompositeTypeDeclaration ctd) {

		/** Build two tables: Table 1 has available Concept names
		 *  Table 2 has the required labels for this concept.
		 */
		concepts.getViewer().setInput(ctd);
		concepts.getViewer().refresh();

	}

	/**
	 * The <code>MultiPageEditorPart</code> implementation of this
	 * <code>IWorkbenchPart</code> method disposes all nested editors.
	 * Subclasses may extend.
	 */
	@Override
	public void dispose() {
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
		super.dispose();
	}
	/**
	 * Saves the multi-page editor's document.
	 * {@inheritDoc}
	 */
	@Override
	public void doSave(IProgressMonitor monitor) {
		String xmlStr = fr.lip6.move.coloane.its.io.ModelWriter.translateToXML(types);
		InputStream is = new ByteArrayInputStream(xmlStr.getBytes());
		try {
			((FileEditorInput) getEditorInput()).getFile().setContents(is, false, false, null);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDirty(false);
	}
	/**
	 * Saves the multi-page editor's document as another file.
	 * Also updates the text for page 0's tab, and updates this multi-page editor's input
	 * to correspond to the nested editor's.
	 */
	@Override
	public void doSaveAs() {
		IEditorPart editor = getEditor(0);
		editor.doSaveAs();
		setPageText(0, editor.getTitle());
		setInput(editor.getEditorInput());
		setDirty(false);
	}
	/**
	 * {@inheritDoc}
	 *  (non-Javadoc)
	 * Method declared on IEditorPart
	 */
	public void gotoMarker(IMarker marker) {
		setActivePage(0);
		IDE.gotoMarker(getEditor(0), marker);
	}
	/**
	 * The <code>MultiPageEditorExample</code> implementation of this method
	 * checks that the input is an instance of <code>IFileEditorInput</code>.
	 * {@inheritDoc}
	 */
	@Override
	public void init(IEditorSite site, IEditorInput editorInput)
	throws PartInitException {
		if (!(editorInput instanceof IFileEditorInput)) {
			throw new PartInitException("Invalid Input: Must be IFileEditorInput");
		}
		super.init(site, editorInput);
	}
	/**
	 * {@inheritDoc}
	 *  (non-Javadoc)
	 * Method declared on IEditorPart.
	 */
	@Override
	public boolean isSaveAsAllowed() {
		return true;
	}
	/**
	 * {@inheritDoc}
	 *  (non-Javadoc)
	 * Calculates the contents of page 2 when the it is activated.
	 */
	@Override
	protected void pageChange(int newPageIndex) {
		super.pageChange(newPageIndex);
		//	sortWords();
		table.refresh();


	}
	/**
	 * {@inheritDoc}
	 *  (non-Javadoc)
	 * Closes all project files on project close.
	 */
	@Override
	public void resourceChanged(final IResourceChangeEvent event) {
		if (event.getType() == IResourceChangeEvent.PRE_CLOSE) {
			Display.getDefault().asyncExec(new Runnable() {
				public void run() {
					IWorkbenchPage[] pages = getSite().getWorkbenchWindow().getPages();
					for (IWorkbenchPage page : pages) {
						if (((FileEditorInput) editor.getEditorInput()).getFile().getProject().equals(event.getResource()))
						{
							IEditorPart editorPart = page.findEditor(editor.getEditorInput());
							page.closeEditor(editorPart, true);
						}
					}
				}
			});
		}
	}
	/**
	 * Returns the types that is central model of this editor.
	 * @return the current type list
	 */
	public TypeList getTypes() {
		return types;
	}

	/**
	 * The table viewer.
	 * @return the table viewer
	 */
	public TableViewer getTableviewer() {
		return table.getViewer();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isDirty() {
		return editor.isDirty() || isDirty;
	}

	/**
	 * set state to dirty (should save)
	 * @param isDirty the state
	 */
	public void setDirty(boolean isDirty) {
		this.isDirty = isDirty;
		firePropertyChange(PROP_DIRTY);
		refresh();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update() {
		setDirty(true);
	}

	/**
	 * {@inheritDoc}
	 */
	public void refresh() {
		table.getViewer().refresh();
		requiredLabels.getViewer().refresh();
		typePage.redraw();
		treePage.getPartControl().redraw();
		treePage.refresh();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void addPages() {
		TypeList tmptypes = fr.lip6.move.coloane.its.io.ModelLoader.loadFromXML(((FileEditorInput) getEditorInput()).getFile());
		if (tmptypes == null) {
			tmptypes = new TypeList();
		}
		setTypes(tmptypes);

		try {
			treePage = new MasterDetailsPage(this);
			addPage(treePage);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
		createPage1();
		createPage0();

	}
}

