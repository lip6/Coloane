/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6). All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Jean-Baptiste VORON (LIP6) -
 * Project Head / Initial contributor Clément DÉMOULINS (LIP6) - Project Manager Official contacts: coloane@lip6.fr
 * http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.api.alligator.dialog;

import fr.lip6.move.coloane.core.main.Coloane;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.cosyverif.alligator.service.Parameter;
import org.cosyverif.alligator.service.parameter.MultipleLineTextParameter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public final class MultipleLineTextDialog
    extends Dialog<MultipleLineTextParameter> {

    private Label label;
    private Text input;
    private Text help;
    private Button importButton;

    public MultipleLineTextDialog(MultipleLineTextParameter parameter) {
        super(parameter);
    }

    @Override
    public
        String errorMessage() {
        return null;
    }

    @Override
    public
        void update(Parameter<?> p) {
        MultipleLineTextParameter that = (MultipleLineTextParameter) p;
        if (that != null) {
            if (parameter.equals(that)) {
                input.setBackground(null);
            } else {
                input.setBackground(updateColor);
                parameter.populateFrom(that);
                updateDialog();
            }
        }
    }

    @Override
    public
        int size() {
        return 7;
    }

    @Override
    public
        void create(final Composite parent) {
        // Label:
        label = new Label(parent, SWT.WRAP);
        label.setText(parameter.getName() + ":");
        label.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
        // Help message:
        help = new Text(parent, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP);
        GridData data = new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1);
        data.widthHint = width;
        help.setLayoutData(data);
        help.setText(parameter.getHelp());
        help.setEditable(false);
        // Input:
        GridData layoutData = new GridData(SWT.FILL, SWT.FILL, false, false, 3, 4);
        layoutData.heightHint = 50;
        input = new Text(parent, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
        input.setLayoutData(layoutData);
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
        // Import button:
        if (editable) {
            final Text input = this.input;
            importButton = new Button(parent, SWT.PUSH);
            importButton.setText("Import…");
            importButton.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 3, 1));
            importButton.addSelectionListener(new SelectionAdapter() {
                @Override
                public
                    void widgetSelected(SelectionEvent e) {
                    FileDialog dialog = new FileDialog(parent.getShell(), SWT.OPEN);
                    final String filePath = dialog.open();
                    if (filePath != null) {
                        parent.getDisplay()
                              .asyncExec(new Runnable() {
                                  public
                                      void run() {
                                      try {
                                          BufferedReader reader = new BufferedReader(new FileReader(filePath));
                                          StringBuilder sb = new StringBuilder();
                                          int c;
                                          while ((c = reader.read()) != -1) {
                                              sb.append((char) c);
                                          }
                                          input.setText(sb.toString());
                                          reader.close();
                                      } catch (IOException e) {
                                          Coloane.showErrorMsg(e.getLocalizedMessage());
                                      }
                                  }
                              });
                    }
                }
            });
        }
    }

    @Override
    public
        void updateDialog() {
        if (parameter.isActualParameter()) {
            input.setText(parameter.getValue()
                                   .toString());
        } else {
            input.setText("");
            parameter.setValue("");
        }
    }

    @Override
    public
        void updateParameter() {
        parameter.setValue(input.getText());
        page.refresh();
    }

}
