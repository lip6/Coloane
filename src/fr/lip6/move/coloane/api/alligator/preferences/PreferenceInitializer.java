package fr.lip6.move.coloane.api.alligator.preferences;

import fr.lip6.move.coloane.api.alligator.Activator;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

	/** {@inheritDoc}
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public final void initializeDefaultPreferences() {
		IPreferenceStore store = Activator.getDefault().getPreferenceStore();
		store.setDefault(PreferenceConstants.P_ALLIGATOR_NAME, "Localhost");
		store.setDefault(PreferenceConstants.P_ALLIGATOR_URL, "http://localhost:9000/servicemanager");
	}

}
