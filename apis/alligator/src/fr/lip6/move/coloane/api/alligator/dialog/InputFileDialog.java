/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6). All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Jean-Baptiste VORON (LIP6) -
 * Project Head / Initial contributor Clément DÉMOULINS (LIP6) - Project Manager Official contacts: coloane@lip6.fr
 * http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.api.alligator.dialog;

import org.cosyverif.alligator.service.parameter.FileParameter;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;

public final class InputFileDialog
    extends InputResourceDialog<FileParameter> {

    public InputFileDialog(FileParameter parameter) {
        super(parameter);
    }

    @Override
    protected
        boolean keepResource(IResource resource) {
        if (parameter.getContentType()
                     .equalsIgnoreCase("cami")) {
            return (resource instanceof IFile) && (resource.getFileExtension()
                                                           .equalsIgnoreCase("cami") || resource.getFileExtension()
                                                                                                .equalsIgnoreCase("model"));
        } else if (parameter.getContentType()
                            .equalsIgnoreCase("cami")) {
            return (resource instanceof IFile) && (resource.getFileExtension()
                                                           .equalsIgnoreCase("lola") || resource.getFileExtension()
                                                                                                .equalsIgnoreCase("model"));
        } else {
            return (resource instanceof IFile);
        }
    }

}
