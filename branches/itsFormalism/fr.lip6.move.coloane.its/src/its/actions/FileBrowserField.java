package its.actions;

import its.obs.ISimpleObservable;
import its.obs.ISimpleObserver;
import its.obs.SimpleObservable;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Text;

/**
 * Just a combination text entry box and browse button. Right now it's
 * specialized for browsing files but it would be easy to refactor to do just
 * about anything: just extract doBrowse().
 * <br>
 * Cribbed from StringButtonFieldEditor and friends.
 * 
 * @author Bronson
 */

public class FileBrowserField extends Composite implements ISimpleObservable {
	private Text text;
	private Button button;
	SimpleObservable notifier = new SimpleObservable();

	public void addObserver(ISimpleObserver o) {
		notifier.addObserver(o);
	}

	public void deleteObserver(ISimpleObserver o) {
		notifier.deleteObserver(o);
	}

	/**
	 * Constructor.
	 * @param parent the composite we add stuff to.
	 */
	public FileBrowserField(Composite parent) {
		super(parent, 0);

		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		setLayout(gridLayout);

		text = new Text(this, SWT.SINGLE | SWT.BORDER);
		text.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				valueChanged();
			}
		});
		text.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				valueChanged();
			}
		});

		// tell the text field to use up as much horizontal space as it can
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		text.setLayoutData(gridData);

		button = new Button(this, SWT.PUSH);
		button.setText("Browse");
		button.setFont(getFont());
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String newValue = doBrowse();
				if (newValue != null) {
					setText(newValue);
				}
			}
		});

		button.addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent event) {
				button = null;
			}
		});
	}

	/**
	 * invoke a file chooser and return selected path.
	 * @return the path
	 */
	private String doBrowse() {
		File f = new File(text.getText());
		if (!f.exists()) {
			f = null;
		}
		File d = getFile(f);
		if (d == null) {
			return null;
		}
		return d.getAbsolutePath();
	}

	/**
	 * instanciate a file chooser in the same folder as before.
	 * @param startingDirectory the start dir
	 * @return a file
	 */
	private File getFile(File startingDirectory) {
		FileDialog dialog = new FileDialog(getShell(), SWT.OPEN);
		if (startingDirectory != null) {
			dialog.setFileName(startingDirectory.getPath());
		}
		String file = dialog.open();
		if (file != null) {
			file = file.trim();
			if (file.length() > 0) {
				return new File(file);
			}
		}

		return null;
	}

	/**
	 * return the user provided path to dot
	 * @return the path or "" if not set
	 */
	public final String getText() {
		if (text != null) {
			return text.getText();
		} else {
			return "";
		}
	}

	/**
	 * {@inheritDoc}
	 * @see org.eclipse.swt.widgets.Control#setEnabled(boolean)
	 */
	@Override
	public final void setEnabled(boolean enabled) {
		text.setEnabled(enabled);
		button.setEnabled(enabled);
		super.setEnabled(enabled);
	}

	/**
	 * Sets this field editor's value ensuring that textChanged() is called.
	 * 
	 * @param vvalue
	 *            the new value, or <code>null</code> meaning the empty string
	 */
	public final void setText(String vvalue) {
		String value = vvalue;
		if (value == null) {
			value = "";
		}

		if (!text.getText().equals(value)) {
			text.setText(value);
			valueChanged();
		}
	}

	/**
	 * Called every time the text changes (i.e. every keypress or when the user
	 * clicks on the browse button.
	 * 
	 * Not called at init or teardown time.
	 */
	public void valueChanged() {
		notifier.notifyObservers();
	}

}
