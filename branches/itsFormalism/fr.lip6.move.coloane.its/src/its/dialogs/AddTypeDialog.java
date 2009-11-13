package its.dialogs;

import its.TypeDeclaration;
import its.TypeList;
import its.actions.FileBrowserField;
import its.obs.ISimpleObserver;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class AddTypeDialog extends TitleAreaDialog {

	private TypeDeclaration type;
	private FileBrowserField fileField;
	private Text newTypeTextfield;
	private TypeList types;
	private String hint;

	public TypeDeclaration getDeclaration() {
		return type;
	}

	public AddTypeDialog(Shell parentShell, TypeList types) {
		super(parentShell);
		this.types = types;
	}

	@Override
	protected Control createContents(Composite parent) {
		Control contents = super.createContents(parent);
		setTitle("Add a new Type");
		setMessage("Please select the model file containing your type definition.");
		return contents;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Group fileComposite = new Group(parent, SWT.LEFT);
		GridLayout layout = new GridLayout();
		fileComposite.setLayout(layout);
		GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
		fileComposite.setLayoutData(data);
		fileComposite.setText("Select model file to import");

		fileField = new FileBrowserField(fileComposite);
		fileField.setToolTipText("Select model file to import");
		fileField.setLayoutData(data);
		if (hint != null) {
			fileField.setText(hint);
		}
		
		Group nameComposite = new Group(parent, SWT.LEFT);
		GridLayout layout2 = new GridLayout();
		nameComposite.setLayout(layout2);
		GridData data2 = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
		nameComposite.setLayoutData(data2);
		nameComposite.setText("Select name of type after import");

		newTypeTextfield = new Text(nameComposite,SWT.BORDER); 
		newTypeTextfield.setText("Type "+types.size());
		newTypeTextfield.setToolTipText("Enter the name under which to import your new type");
		newTypeTextfield.setLayoutData(data);
		
		fileField.addObserver(new ISimpleObserver() {
			public void update() {
				suggestName();
			}
		});

		if (hint != null)
			suggestName();
		
		return parent;
	}
	
	
	protected void suggestName() {
		Path path = new Path(fileField.getText());
		path.removeFileExtension();
		String name = path.lastSegment();
		name = generateValidName(name);
		newTypeTextfield.setText(name);
	}

	protected String generateValidName(String name) {
		for (TypeDeclaration td : types) {
			if (td.getTypeName().equals(name)) {
				return generateValidName(name+"1");
			}
		}
		return name;
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		((GridLayout) parent.getLayout()).numColumns++;
		((GridLayout) parent.getLayout()).numColumns++;

		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, true);
		
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		
	}

	@Override
	protected void cancelPressed() {
		type = null;
		hint = null;
		super.cancelPressed();
	}
	
	@Override
	protected void okPressed() {
		hint = null;
		String filePath = fileField.getText();
		if (filePath == null || "".equals(filePath)) {
			setErrorMessage("Please Specify a model file using the \"Browse\" button");
			return;
		}
		IPath path = new Path(filePath);
		IFile file = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(path);

		String typeName = newTypeTextfield.getText();
		if (typeName == null || typeName.equals("")) {
			setErrorMessage("The model type name cannot be empty.");
			return;
		}
		for (TypeDeclaration itType : types) {
			if (itType.getTypeName().equals(typeName)) {
				setErrorMessage("Type name already exists");
				return;
			}
		}
		try {
			type = TypeDeclaration.create(typeName,file,types);
		} catch (Exception ex) {
			setErrorMessage("Error loading model file: "+
					"Your file does not seem to contain a recognized Coloane model. Details:\n"+ex.getMessage());
			return;
		}
		super.okPressed();
	}
	
	public TypeDeclaration getType() {
		return type;
	}
	
	public void setFileField(String fileField) {
		this.hint = fileField;
	}
}
