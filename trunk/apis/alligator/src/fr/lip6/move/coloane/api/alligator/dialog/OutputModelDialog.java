/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6). All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Jean-Baptiste VORON (LIP6) -
 * Project Head / Initial contributor Clément DÉMOULINS (LIP6) - Project Manager Official contacts: coloane@lip6.fr
 * http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.api.alligator.dialog;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.cosyverif.alligator.service.Parameter;
import org.cosyverif.alligator.service.parameter.ModelParameter;
import org.cosyverif.alligator.util.FileSystem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public final class OutputModelDialog
    extends Dialog<ModelParameter> {

    /** Logger */
    private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.api.alligator"); //$NON-NLS-1$

    private Text input;
    private Label label;
    private Text help;
    private File file = null;

    public OutputModelDialog(ModelParameter parameter) {
        super(parameter);
    }

    @Override
    public
        void updateDialog() {
        if (parameter.isActualParameter() && file != null) {
            input.setText(file.toString());
        } else {
            input.setText("");
        }
    }

    @Override
    public
        void updateParameter() {
    }

    @Override
    public
        int size() {
        return 2;
    }

    @Override
    public
        void create(Composite parent) {
        // Label:
        label = new Label(parent, SWT.WRAP);
        label.setText(parameter.getName() + ":");
        label.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
        // Input:
        input = new Text(parent, SWT.BORDER | SWT.SINGLE);
        input.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
        input.setText("");
        input.setEditable(false);
        // Help message:
        help = new Text(parent, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP);
        GridData data = new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1);
        data.widthHint = width;
        help.setLayoutData(data);
        help.setText(parameter.getHelp());
        help.setEditable(false);
    }

    @Override
    public
        String errorMessage() {
        return null;
    }

    @Override
    public
        void update(Parameter<?> p) {
        try {
            ModelParameter that = (ModelParameter) p;
            if (parameter.isActualParameter() && (file == null)) {
                file = File.createTempFile(parameter.getName() + "-", ".parameter");
                file.deleteOnExit();
            } else if (parameter.isFormalParameter()) {
                file = null;
            }
            if (parameter.equals(that)) {
                input.setBackground(null);
                label.setBackground(null);
            } else {
                input.setBackground(updateColor);
                label.setBackground(updateColor);
                parameter.copy(that);
                if (file != null) {
                    OutputFileDialog.copyFile(FileSystem.modelToFile(parameter.getModel()), file);
                }
            }
            updateDialog();
        } catch (IOException e) {
            throw new AssertionError();
        }
    }

}
