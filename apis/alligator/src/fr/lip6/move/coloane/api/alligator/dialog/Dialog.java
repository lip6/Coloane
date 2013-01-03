/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6). All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Jean-Baptiste VORON (LIP6) -
 * Project Head / Initial contributor Clément DÉMOULINS (LIP6) - Project Manager Official contacts: coloane@lip6.fr
 * http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.api.alligator.dialog;

import org.cosyverif.alligator.service.Parameter;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

public abstract class Dialog<P extends Parameter<P>> {

    protected final P parameter;
    protected final boolean editable;
    protected final Color errorFontColor;
    protected final Color errorColor;
    protected final Color updateColor;

    protected Dialog(P parameter, boolean editable) {
        this.parameter = parameter;
        this.editable = editable;
        Device device = Display.getCurrent();
        // See http://web.njit.edu/~kevin/rgb.txt.html for colors.
        this.errorFontColor = new Color(device, 205, 38, 38); // firebrick3
        this.errorColor = new Color(device, 240, 128, 128); // LightCoral
        this.updateColor = new Color(device, 112, 219, 147); // Aquamarine
    }

    public final
        P getParameter() {
        return parameter;
    }

    public final
        boolean isEditable() {
        return editable;
    }

    public final
        void reset() {
        parameter.unset();
        updateDialog();
    }

    public abstract
        int size();

    public abstract
        void create(final Composite parent);

    public abstract
        String errorMessage();

    public abstract
        void update(Parameter<?> that);

    protected abstract
        void updateDialog();

    protected abstract
        void updateParameter();

}
