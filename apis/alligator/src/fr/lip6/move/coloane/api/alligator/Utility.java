package fr.lip6.move.coloane.api.alligator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

public final class Utility {

    public static
        ImageDescriptor getImage(String name) {
        URL fullPathString = Utility.class.getResource("/resources/" + name);
        return ImageDescriptor.createFromURL(fullPathString);
    }

    public static
        Image getImage(File file) {
        try {
            return new Image(Display.getDefault(), new FileInputStream(file));
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
