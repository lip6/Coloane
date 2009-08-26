package testits.editors;


import its.ITypeList;
import its.TypeDeclaration;
import its.TypeList;

import java.util.StringTokenizer;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FontDialog;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.MultiPageEditorPart;

/**
 * An example showing how to create a multi-page editor.
 * This example has 3 pages:
 * <ul>
 * <li>page 0 contains a nested text editor.
 * <li>page 1 allows you to change the font used in page 2
 * <li>page 2 shows the words in page 0 in sorted order
 * </ul>
 */
public class MultiPageEditor2 extends MultiPageEditorPart implements IResourceChangeListener{

	/** The text editor used in page 0. */
	private TextEditor editor;

	/** The font chosen in page 1. */
	private Font font;

	/** The text widget used in page 2. */
	private Table table;

	private TableViewer tableViewer;
	
	private ITypeList types;
	/**
	 * Creates a multi-page editor example.
	 */
	public MultiPageEditor2() {
		super();
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this);
		types = new TypeList();
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
			ErrorDialog.openError(
					getSite().getShell(),
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

		Composite composite = new Composite(getContainer(), SWT.NONE);
		GridLayout layout = new GridLayout();
		composite.setLayout(layout);
		layout.numColumns = 2;

		Button fontButton = new Button(composite, SWT.NONE);
		GridData gd = new GridData(GridData.BEGINNING);
		gd.horizontalSpan = 2;
		fontButton.setLayoutData(gd);
		fontButton.setText("Change Font...");

		fontButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				setFont();
			}
		});

		int index = addPage(composite);
		setPageText(index, "Properties");
	}

	private enum TableColumns {
		TYPE_NAME,
		TYPE_TYPE,
		TYPE_FILE
	}
	/**
	 * Creates page 2 of the multi-page editor,
	 * which shows the sorted text.
	 */
	void createPage2() {
		Composite composite = new Composite(getContainer(), SWT.NONE);
		FillLayout layout = new FillLayout();
		composite.setLayout(layout);
		table = new Table (composite,SWT.H_SCROLL | SWT.V_SCROLL);

		GridData gridData = new GridData(GridData.FILL_BOTH);
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalSpan = 3;
		table.setLayoutData(gridData);		

		table.setLinesVisible(true);
		table.setHeaderVisible(true);

		// column Type name
		TableColumn column = new TableColumn(table, SWT.LEFT, 0);
		column.setText("Type Name");
		column.setWidth(100);

		column = new TableColumn(table, SWT.LEFT, 1);
		column.setText("Type ");
		column.setWidth(100);

		column = new TableColumn(table, SWT.LEFT, 2);
		column.setText("Path");
		column.setWidth(100);

		// Add listener to column so tasks are sorted by description when clicked 
		//		column.addSelectionListener(new SelectionAdapter() {
		//			public void widgetSelected(SelectionEvent e) {
		//				tableViewer.setSorter(new ExampleTaskSorter(ExampleTaskSorter.DESCRIPTION));
		//			}
		//		});
//		createTableViewer();
//		tableViewer.setContentProvider(new TypeListContentProvider());
//		//tableViewer.setLabelProvider();
//		// The input for the table viewer is the instance of ExampleTaskList
//		tableViewer.setInput(types);

		

		int index = addPage(composite);
		setPageText(index, "Preview");
	}
	/**
	 * Creates the pages of the multi-page editor.
	 */
	@Override
	protected void createPages() {
		createPage0();
		createPage1();
		createPage2();
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
	 */
	@Override
	public void doSave(IProgressMonitor monitor) {
		getEditor(0).doSave(monitor);
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
	}
	/* (non-Javadoc)
	 * Method declared on IEditorPart
	 */
	public void gotoMarker(IMarker marker) {
		setActivePage(0);
		IDE.gotoMarker(getEditor(0), marker);
	}
	/**
	 * The <code>MultiPageEditorExample</code> implementation of this method
	 * checks that the input is an instance of <code>IFileEditorInput</code>.
	 */
	@Override
	public void init(IEditorSite site, IEditorInput editorInput)
	throws PartInitException {
		if (!(editorInput instanceof IFileEditorInput))
			throw new PartInitException("Invalid Input: Must be IFileEditorInput");
		super.init(site, editorInput);
	}
	/* (non-Javadoc)
	 * Method declared on IEditorPart.
	 */
	@Override
	public boolean isSaveAsAllowed() {
		return true;
	}
	/**
	 * Calculates the contents of page 2 when the it is activated.
	 */
	@Override
	protected void pageChange(int newPageIndex) {
		super.pageChange(newPageIndex);
		if (newPageIndex == 2) {
			sortWords();
		}
	}
	/**
	 * Closes all project files on project close.
	 */
	public void resourceChanged(final IResourceChangeEvent event){
		if(event.getType() == IResourceChangeEvent.PRE_CLOSE){
			Display.getDefault().asyncExec(new Runnable(){
				public void run(){
					IWorkbenchPage[] pages = getSite().getWorkbenchWindow().getPages();
					for (int i = 0; i<pages.length; i++){
						if(((FileEditorInput)editor.getEditorInput()).getFile().getProject().equals(event.getResource())){
							IEditorPart editorPart = pages[i].findEditor(editor.getEditorInput());
							pages[i].closeEditor(editorPart,true);
						}
					}
				}            
			});
		}
	}
	/**
	 * Sets the font related data to be applied to the text in page 2.
	 */
	void setFont() {
		FontDialog fontDialog = new FontDialog(getSite().getShell());
		// fontDialog.setFontList(text.getFont().getFontData());
		FontData fontData = fontDialog.open();
		if (fontData != null) {
			if (font != null)
				font.dispose();
			//font = new Font(text.getDisplay(), fontData);
			//text.setFont(font);
		}
	}

	/**
	 * Create the TableViewer 
	 */
	private void createTableViewer() {

		tableViewer = new TableViewer(table);
		tableViewer.setUseHashlookup(true);
		
		tableViewer.setColumnProperties( new String[] {"Name","Type","Path"} );

//		// Create the cell editors
//		CellEditor[] editors = new CellEditor[TableColumns.values().length];
//
//		// Column 1 : Completed (Checkbox)
//		editors[0] = new CheckboxCellEditor(table);
//
//		// Column 2 : Description (Free text)
//		TextCellEditor textEditor = new TextCellEditor(table);
//		((Text) textEditor.getControl()).setTextLimit(60);
//		editors[1] = textEditor;
//
//		// Column 3 : Owner (Combo Box) 
//		editors[2] = new ComboBoxCellEditor(table, taskList.getOwners(), SWT.READ_ONLY);
//
//		// Column 4 : Percent complete (Text with digits only)
//		textEditor = new TextCellEditor(table);
//		((Text) textEditor.getControl()).addVerifyListener(
//		
//			new VerifyListener() {
//				public void verifyText(VerifyEvent e) {
//					// Here, we could use a RegExp such as the following 
//					// if using JRE1.4 such as  e.doit = e.text.matches("[\\-0-9]*");
//					e.doit = "0123456789".indexOf(e.text) >= 0 ;
//				}
//			});
//		editors[3] = textEditor;

		// Assign the cell editors to the viewer 
//		tableViewer.setCellEditors(editors);
		// Set the cell modifier for the viewer
//		tableViewer.setCellModifier(new ExampleCellModifier(this));
		// Set the default sorter for the viewer 
		tableViewer.setSorter(new ViewerSorter());
	}

	
	/**
	 * Sorts the words in page 0, and shows them in page 2.
	 */
	void sortWords() {

		String editorText =
			editor.getDocumentProvider().getDocument(editor.getEditorInput()).get();

		StringTokenizer lineTokenizer =
			new StringTokenizer(editorText, "\n");

		while (lineTokenizer.hasMoreTokens()) {
			String line = lineTokenizer.nextToken();
			StringTokenizer tokenizer =
				new StringTokenizer(line, ",");
			if (tokenizer.countTokens() == 3) {
				String [] tab = new String[3];
				int i=0;
				while (tokenizer.hasMoreTokens()) {
					tab[i++] = tokenizer.nextToken();
				}
	//			TypeDeclaration t = new TypeDeclaration(tab[0],tab[1],tab[2]);
				TableItem item = new TableItem(table,SWT.NONE);
				item.setText(tab);
		//		types.addTypeDeclaration(t);
				//else
				//	table.add
			}

//			StringWriter displayText = new StringWriter();
//			for (int i = 0; i < editorWords.size(); i++) {
//				displayText.write(((String) editorWords.get(i)));
//				displayText.write(System.getProperty("line.separator"));
//			}
			//text.setText(displayText.toString());
		}
	}
	
	
	/**
	 * InnerClass that acts as a proxy for the ExampleTaskList 
	 * providing content for the Table. It implements the ITaskListViewer 
	 * interface since it must register changeListeners with the 
	 * ExampleTaskList 
	 */
	class TypeListContentProvider implements IStructuredContentProvider, ITypeList {

		public void dispose() {
		//	.removeChangeListener(this);
		}

		// Return the tasks as an array of Objects
		public Object[] getElements(Object parent) {
			return types.toArray();
		}
		

		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void addTypeDeclaration(TypeDeclaration t) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void removeTypeDeclaration(TypeDeclaration t) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void updateTypeDeclaration(TypeDeclaration t) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Object[] toArray() {
			// TODO Auto-generated method stub
			return types.toArray();
		}

	}
	

	/**
	 * Add the "Add", "Delete" and "Close" buttons
	 * @param parent the parent composite
	 */
	private void createButtons(Composite parent) {
		
		// Create and configure the "Add" button
		Button add = new Button(parent, SWT.PUSH | SWT.CENTER);
		add.setText("Add");
		
		GridData gridData = new GridData (GridData.HORIZONTAL_ALIGN_BEGINNING);
		gridData.widthHint = 80;
		add.setLayoutData(gridData);
		add.addSelectionListener(new SelectionAdapter() {
       	
       		// Add a task to the ExampleTaskList and refresh the view
			@Override
			public void widgetSelected(SelectionEvent e) {
//				types.addTypeDeclaration(new TypeDeclaration("toto","titi","toto.its"));
			}
		});

	}
}