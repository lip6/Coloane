/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6). All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Jean-Baptiste VORON (LIP6) -
 * Project Head / Initial contributor Clément DÉMOULINS (LIP6) - Project Manager Official contacts: coloane@lip6.fr
 * http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.api.alligator.dialog;

import fr.lip6.move.coloane.core.ui.files.ModelLoader;
import fr.lip6.move.coloane.extensions.exporttogrml.Activator;
import fr.lip6.move.coloane.interfaces.formalism.IFormalism;

import java.net.URL;
import java.util.Arrays;

import org.cosyverif.alligator.service.parameter.ModelParameter;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

public final class InputModelDialog
    extends InputResourceDialog<ModelParameter> {

    public InputModelDialog(ModelParameter parameter) {
        super(parameter);
    }

    @Override
    protected
        boolean
        keepResource(IResource resource) {
        if (resource instanceof IFile && resource.getName()
                                                 .endsWith("model")) {
            if (parameter.getFormalisms() == null) {
                return true;
            }
            IFormalism formalism = ModelLoader.loadFormalismFromXML((IFile) resource);
            assert formalism != null;
            // Step 2. find converter for this formalism:
            for (IConfigurationElement contribution : Arrays.asList(Platform.getExtensionRegistry()
                                                                            .getConfigurationElementsFor(Activator.EXTENSION_POINT_ID))) {
                String id = contribution.getAttribute(Activator.NAME_EXTENSION);
                assert id != null;
                String fml = contribution.getAttribute(Activator.FMLURL_EXTENSION);
                assert fml != null;
                assert parameter.getFormalisms() != null;
                assert formalism.getId() != null;
                System.err.println("Looking for: " + Arrays.toString(parameter.getFormalisms()));
                System.err.println("Found fml: " + fml);
                System.err.println("Formalism: " + formalism.getId());
                System.err.println("Id: " + id);
                for (URL f : parameter.getFormalisms()) {
                    if (fml.equals(f.toString()) && formalism.getId()
                                                             .equals(id)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
