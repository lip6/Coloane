package fr.lip6.move.coloane.ui.dialogs;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;



public class BrowseFileExample {

	private static Display display;

	private static Shell shell;

	private static Text file;


	public static void main(String[] args) {
		Label lb1;
		Button browse;
		Button ok;
		Button cancel;

		display = new Display();
		shell = new Shell(display);
		shell.setText("File browser");
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 3;
		shell.setLayout(gridLayout);

		lb1 = new Label(shell, SWT.NONE);
		lb1.setText("File:");
		file = new Text(shell, SWT.BORDER);
		file.setTextLimit(255);

		browse = new Button(shell, SWT.PUSH);
		browse.setText("Browse");

		ok = new Button(shell, SWT.NONE);
		ok.setText("OK");

		cancel = new Button(shell, SWT.NONE);
		cancel.setText("CANCEL");

		GridData gridData = new GridData();

		gridData.widthHint = 255;
		file.setLayoutData(gridData);


		browse.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				String fileName = new FileDialog(shell).open();
				file.setText(fileName);
			}
		});

		cancel.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				shell.dispose();
			}
		});


		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
