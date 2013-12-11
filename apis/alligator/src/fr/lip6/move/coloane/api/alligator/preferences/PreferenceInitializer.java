/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6). All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Jean-Baptiste VORON (LIP6) -
 * Project Head / Initial contributor Clément DÉMOULINS (LIP6) - Project Manager Official contacts: coloane@lip6.fr
 * http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.api.alligator.preferences;

import fr.lip6.move.coloane.api.alligator.Activator;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer
    extends AbstractPreferenceInitializer {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
     */
    public final
        void initializeDefaultPreferences() {
        IPreferenceStore store = Activator.getDefault()
                                          .getPreferenceStore();
        store.setDefault(PreferenceConstants.P_ALLIGATOR_LIST,
                         "Public Alligator" + "&" + "http://publigator.cosyverif.org/" + "&" + "15" + "#" +
                         "Testing Alligator" + "&" + "http://testigator.cosyverif.org/" + "&" + "15" + "#" +
                         "Local Alligator" + "&" + "http://localhost:9000/" + "&" + "2"
                         );
    }

}
