package fr.lip6.move.coloane.api.alligator;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import org.cosyverif.alligator.service.Identifier;
import org.eclipse.jface.preference.PreferenceStore;

public final class Identifiers {

    private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.api.alligator"); //$NON-NLS-1$

    private static final PreferenceStore STORE = new PreferenceStore("alligator.tasks");

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
        Set<Identifier> getIdentifiers() {
        LOGGER.info("Retrieving task identifiers...");
        try {
            STORE.load();
            Set<Identifier> result = new HashSet<Identifier>();
            for (String s : STORE.preferenceNames()) {
                result.add(Identifier.fromString(s));
            }
            return result;
        } catch (IOException e) {
            throw new AssertionError();
        }
    }

    public static synchronized
        void add(Identifier identifier) {
        LOGGER.info("Storing task identifier '" + identifier + "'...");
        try {
            STORE.load();
            STORE.putValue(identifier.toString(), identifier.toString());
            STORE.save();
        } catch (IOException e) {
            throw new AssertionError();
        }
    }

    public static synchronized
        void remove(Identifier identifier) {
        LOGGER.info("Removing task identifier '" + identifier + "'...");
        try {
            STORE.load();
            STORE.setToDefault(identifier.toString());
            STORE.save();
        } catch (IOException e) {
            throw new AssertionError();
        }
    }

}
