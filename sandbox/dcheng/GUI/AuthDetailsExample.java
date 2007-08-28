package fr.lip6.move.coloane.ui.dialogs;

import org.eclipse.swt.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.layout.*;

public class AuthDetailsExample {

	private static Text text;

	private static Text framekitIp = null;

	private static Text framekitPort = null;

	private static Label framekitIpLabel = null;

	private static Label framekitPortLabel = null;

	public static void showDetails(Shell shell) {

		GridData gdata = new GridData(GridData.FILL_HORIZONTAL);
		gdata.widthHint = 100;
		framekitIpLabel = new Label(shell, SWT.NULL);
		framekitIpLabel.setText("IP:");

		framekitIp = new Text(shell, SWT.SINGLE | SWT.BORDER | SWT.LEFT);
		framekitIp.setLayoutData(gdata);
		framekitIp.setTextLimit(255);

		framekitPortLabel = new Label(shell, SWT.NULL);
		framekitPortLabel.setText("PORT:");

		framekitPort = new Text(shell, SWT.SINGLE | SWT.BORDER | SWT.LEFT);
		framekitPort.setLayoutData(gdata);
		framekitPort.setTextLimit(255);
	}

	public static void hideDetails() {
		framekitIp.dispose();
		framekitPort.dispose();
		framekitIpLabel.dispose();
		framekitPortLabel.dispose();

		framekitIp = null;
		framekitPort = null;
		framekitIpLabel = null;
		framekitPortLabel = null;
	}

	public static void main(String[] args) {

		final Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setLayout(new GridLayout());

		new Label(shell, SWT.BORDER).setText("AUTHENTICATION");
		Button button = new Button(shell, SWT.NONE);
		button.setText("Show details");


		showDetails(shell);

		button.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				if (framekitIp == null) {
					showDetails(shell);
				} else {
					hideDetails();
				}
				shell.layout(true);
				shell.pack();
			}
		});
		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}
}
