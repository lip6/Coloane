package fr.lip6.move.coloane.api.alligator.dialog;

import fr.lip6.move.coloane.api.alligator.wizard.Wizard;
import fr.lip6.move.coloane.api.alligator.wizard.WizardPage;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public final class SetDefaultDialog {

    private Button reset;
    private final Wizard wizard;

    public SetDefaultDialog(Wizard wizard) {
        this.wizard = wizard;
    }

    public
        void create(final Composite parent) {
        reset = new Button(parent, SWT.PUSH);
        reset.setText("Set to default values");
        reset.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
        reset.addSelectionListener(new SelectionAdapter() {
            @Override
            public
                void widgetSelected(SelectionEvent e) {
                for (Dialog<?> dialog: wizard.dialogs) {
                    dialog.getParameter().unset();
                    dialog.getParameter().useDefault();
                }
                for (WizardPage page: wizard.pages) {
                    page.refresh();
                }
            }
        });
    }

}
