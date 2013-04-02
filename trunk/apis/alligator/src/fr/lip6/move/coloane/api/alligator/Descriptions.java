package fr.lip6.move.coloane.api.alligator;

import java.util.logging.Logger;

import org.cosyverif.alligator.service.Description;
import org.cosyverif.alligator.service.Parameter;

public final class Descriptions {

    private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.api.alligator"); //$NON-NLS-1$

    public static synchronized
        Description get(Description description) {
        Description result = description.copy();
        for (Parameter<?> parameter : result.parameters()) {
            parameter.useDefault();
        }
        return result;
    }

}
