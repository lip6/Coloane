package fr.lip6.move.coloane.api.alligator.dialog;

import fr.lip6.move.coloane.api.alligator.wizard.WizardPage;

import org.cosyverif.alligator.service.Parameter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public abstract class ValueDialog<P extends Parameter<P>>
    extends Dialog<P> {

    protected Text input;
    protected Label label;
    protected Label help;
    protected Label error;

    protected ValueDialog(WizardPage page, P parameter, boolean editable) {
        super(page, parameter, editable);
    }

    @Override
    public final
        int size() {
        return 2;
    }

    @Override
    public final
        void create(Composite parent) {
        // Label:
        label = new Label(parent, SWT.WRAP);
        label.setText(parameter.getName() + ":");
        label.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
        // Input:
        input = new Text(parent, SWT.BORDER | SWT.SINGLE);
        input.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
        // Help message:
        help = new Label(parent, SWT.WRAP);
        help.setText(parameter.getHelp());
        help.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
        // Error:
        error = new Label(parent, SWT.WRAP);
        error.setText("");
        error.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
        error.setForeground(errorFontColor);
        // Set listener:
        input.addModifyListener(new ModifyListener() {

            @Override
            public
                void modifyText(ModifyEvent e) {
                String error = errorMessage();
                if (error == null) {
                    updateParameter();
                }
            }

        });
        input.setEditable(editable);
        updateDialog();
    }

    @Override
    public final
        String errorMessage() {
        String result = _errorMessage();
        if (result == null) {
            input.setBackground(null);
            error.setText("");
        } else {
            input.setBackground(errorColor);
            error.setText(result);
        }
        page.refresh();
        return result;
    }

    protected abstract
        String _errorMessage();

    @Override
    public final
        void update(Parameter<?> p) {
        @SuppressWarnings("unchecked")
        P that = (P) p;
        if (that != null) {
            if (parameter.equals(that)) {
                input.setBackground(null);
            } else {
                input.setBackground(updateColor);
                // TODO: parameter.copy(that);
                updateDialog();
            }
        }
        page.refresh();
    }

}
