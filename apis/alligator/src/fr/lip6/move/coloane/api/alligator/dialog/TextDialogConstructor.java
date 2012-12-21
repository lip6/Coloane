/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6). All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Jean-Baptiste VORON (LIP6) -
 * Project Head / Initial contributor Clément DÉMOULINS (LIP6) - Project Manager Official contacts: coloane@lip6.fr
 * http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.api.alligator.dialog;

import fr.lip6.move.coloane.api.alligator.wizard.ParametersPage;
import fr.lip6.move.coloane.core.main.Coloane;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.cosyverif.alligator.service.Parameter;
import org.cosyverif.alligator.service.parameter.IntegerParameter;
import org.cosyverif.alligator.service.parameter.MultiLineTextParameter;
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

/**
 * @author Clément Démoulins
 */
public final class TextDialogConstructor
    implements ItemDialog<MultiLineTextParameter> {

    private final ParametersPage page;
    private final MultiLineTextParameter parameter;
    private final Label label;
    private final Text input;
    private final Button importButton;

    public TextDialogConstructor(final ParametersPage page, final Composite parent, final MultiLineTextParameter parameter) {
        this.parameter = parameter;
        this.page = page;
        this.label = new Label(parent, SWT.WRAP);
        this.label.setText(parameter.getName() + ":");
        this.label.setToolTipText(parameter.getHelp());
        this.label.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));

        GridData layoutData = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1);
        layoutData.heightHint = 50;
        this.input = new Text(parent, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
        this.input.setLayoutData(layoutData);
        ModifyListener listener = new ModifyListener() {

            @Override
            public
                void modifyText(ModifyEvent e) {
                page.setPageComplete(page.isPageComplete());
                parameter.setValue(input.getText());
            }

        };
        this.input.addModifyListener(listener);
        if (parameter.isActualParameter()) {
            input.setText(parameter.getValue());
        } else if (parameter.hasDefaultValue()) {
            this.input.setText(parameter.getDefaultValue());
        } else {
            this.input.setText("");
        }
        this.input.setToolTipText(parameter.getHelp());
        input.setEditable(page.enabled);

        final Text input = this.input;
        this.importButton = new Button(parent, SWT.PUSH);
        this.importButton.setText("Import…");
        this.importButton.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 2, 1));
        this.importButton.addSelectionListener(new SelectionAdapter() {
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

    @Override
    public
        boolean isValid() {
        return true;
    }

    @Override
    public
        void reset() {
        parameter.cloneUnset();
        try {
            input.setText(parameter.getDefaultValue());
        } catch (IllegalArgumentException e) {
            input.setText("");
        }
    }

    @Override
    public
        void performFinish() {
    }

    @Override
    public
        void update(Parameter<?> parameter) {
        MultiLineTextParameter that = MultiLineTextParameter.of(parameter);
        if (this.parameter.equals(that)) {
            input.setBackground(null);
        } else {
            input.setText(that.getValue());
            input.setBackground(input.getDisplay()
                                     .getSystemColor(SWT.COLOR_DARK_YELLOW));
        }
    }

    @Override
    public
        MultiLineTextParameter getParameter() {
        return parameter;
    }

}
