package fr.lip6.move.coloane.api.alligator;

import java.io.IOException;
import java.util.logging.Logger;

import org.cosyverif.alligator.service.Description;
import org.cosyverif.alligator.service.Parameter;
import org.eclipse.jface.preference.PreferenceStore;

public final class Descriptions {

    private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.api.alligator"); //$NON-NLS-1$

    private static final PreferenceStore STORE = new PreferenceStore("alligator.descriptions");

    static {
        try {
            STORE.load();
        } catch (IOException e) {
            try {
                STORE.save();
            } catch (IOException ee) {
                throw new AssertionError(ee);
            }
        }
    }

    public static synchronized
        void add(Description description) {
        LOGGER.info("Storing service description '" + description.getIdentifier() + "'...");
        try {
            STORE.load();
            STORE.putValue(description.getIdentifier(), description.toXML());
            STORE.save();
        } catch (IOException e) {
            throw new AssertionError();
        }
    }

    public static synchronized
        void remove(Description description) {
        LOGGER.info("Removing service description '" + description.getIdentifier() + "'...");
        try {
            STORE.load();
            STORE.setToDefault(description.getIdentifier());
            STORE.save();
        } catch (IOException e) {
            throw new AssertionError();
        }
    }

    public static synchronized
        Description get(Description description) {
        Description copy = description.clone();
        copy.unset();
        try {
            STORE.load();
            if (STORE.contains(copy.getIdentifier())) {
                return Description.fromXML(STORE.getString(copy.getIdentifier()));
            } else {
                for (Parameter<?> parameter : copy.getParameters()) {
                    parameter.useDefault();
                }
                STORE.putValue(copy.getIdentifier(), copy.toXML());
                STORE.save();
                return copy;
            }
        } catch (IOException e) {
            throw new AssertionError();
        }
    }

}
