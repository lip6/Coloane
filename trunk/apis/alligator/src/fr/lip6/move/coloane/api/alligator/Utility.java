package fr.lip6.move.coloane.api.alligator;

import java.net.URL;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.osgi.framework.Bundle;

public final class Utility {

    public static
        ImageDescriptor getImage(String name) {
        URL fullPathString = Utility.class.getResource("/resources/" + name);
        return ImageDescriptor.createFromURL(fullPathString);
    }

}
