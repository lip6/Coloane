package fr.lip6.move.coloane.api.alligator.dialog;

import java.util.logging.Logger;

import org.cosyverif.alligator.service.Parameter;
import org.eclipse.osgi.framework.internal.core.Msg;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public abstract class ValueDialog<P extends Parameter<P>>
    extends Dialog<P> {

    /** Logger */
    private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.api.alligator"); //$NON-NLS-1$

    protected Text input;
    protected Label label;
    protected Text help;
    protected Label error;

    protected ValueDialog(P parameter) {
        super(parameter);
    }

    @Override
    public final
        int size() {
        return 3;
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
        help = new Text(parent, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP);
        GridData data = new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1);
        data.widthHint = width;
        help.setLayoutData(data);
        help.setText(parameter.getHelp());
        help.setEditable(false);
        // Error:
        error = new Label(parent, SWT.WRAP);
        error.setText("");
        error.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 3, 1));
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
    }

    @Override
    public final
        String errorMessage() {
        if (editable) {
            String result = _errorMessage();
            if (result == null) {
                input.setBackground(null);
                error.setText("");
            } else {
                input.setBackground(errorColor);
                error.setText(result);
                LOGGER.info("Set error text: " + result);
            }
            return result;
        } else {
            return null;
        }
    }

    protected abstract
        String _errorMessage();

    @Override
    public final
        void update(Parameter<?> p) {
        @SuppressWarnings("unchecked")
        P that = (P) p;
        if (parameter.equals(that)) {
            input.setBackground(null);
            label.setBackground(null);
        } else {
            input.setBackground(updateColor);
            label.setBackground(updateColor);
            parameter.populateFrom(that);
            updateDialog();
        }
    }

}
