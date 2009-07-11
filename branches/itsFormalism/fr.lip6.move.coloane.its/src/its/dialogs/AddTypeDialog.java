package its.dialogs;

import fr.lip6.move.coloane.tools.layout.FileBrowserField;
import its.TypeDeclaration;
import its.TypeList;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
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

		Group nameComposite = new Group(parent, SWT.LEFT);
		GridLayout layout2 = new GridLayout();
		nameComposite.setLayout(layout2);
		GridData data2 = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
		nameComposite.setLayoutData(data2);
		nameComposite.setText("Select name of type after import");

		newTypeTextfield = new Text(nameComposite,SWT.BORDER); 
		newTypeTextfield.setText("Type "+types.size());
		newTypeTextfield.setToolTipText("Enter the name under which to import your new type");

		return parent;
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		((GridLayout) parent.getLayout()).numColumns++;
		((GridLayout) parent.getLayout()).numColumns++;

		Button cancel = new Button(parent, SWT.PUSH);
		cancel.setText("Cancel");
		cancel.setFont(JFaceResources.getDialogFont());
		cancel.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				type = null;
				setErrorMessage(null);
				setMessage("Please select the model file containing your type definition.");				
			}
		});
		
		Button button = new Button(parent, SWT.PUSH);
		button.setText("OK");
		button.setFont(JFaceResources.getDialogFont());
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
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
					type = TypeDeclaration.create(typeName,file);
				} catch (Exception ex) {
					setErrorMessage("Error loading model file: "+
							"Your file does not seem to contain a recognized Coloane model. Details:\n"+ex.getMessage());
					return;
				}
				setErrorMessage(null);
				setMessage("Model successfully loaded, quit this dialog to confirm.");
			}
		});
	}

	public TypeDeclaration getType() {
		return type;
	}
}
