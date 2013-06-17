/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6). All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Jean-Baptiste VORON (LIP6) -
 * Project Head / Initial contributor Clément DÉMOULINS (LIP6) - Project Manager Official contacts: coloane@lip6.fr
 * http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.api.alligator.preferences;

import fr.lip6.move.coloane.api.alligator.Activator;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import pathtools.TableFieldEditor;

/**
 * This class represents a preference page that is contributed to the Preferences dialog. By subclassing
 * <samp>FieldEditorPreferencePage</samp>, we can use the field support built into JFace that allows us to create a page that is
 * small and knows how to save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They are stored in the preference store that belongs to the main plug-in class.
 * That way, preferences can be accessed directly via the preference store.
 */
// http://pathtools.googlecode.com/svn/trunk/PathTools/src/pathtools/TableFieldEditor.java
// http://sandipchitale.blogspot.fr/2008/09/enhanced-listeditor-implementation.html
public class AlligatorPreferencePage
    extends FieldEditorPreferencePage
    implements IWorkbenchPreferencePage {

    public static final String PART_SEPARATOR = "&";
    public static final String ITEM_SEPARATOR = "#";

    private AlligatorListEditor editor;

    /**
     * Protect a String by replacing separators.
     * 
     * @param s
     *        The string to protect
     * @return The protected string
     * @warning Do not use on URLs!
     */
    private static
        String
        protect(String s) {
        return s.replace(PART_SEPARATOR, "&amp;")
                .replace(ITEM_SEPARATOR, "&num;");
    }

    /**
     * Unprotect a String by replacing separators.
     * 
     * @param s
     *        The string to unprotect
     * @return The unprotected string
     * @warning Do not use on URLs!
     */
    private static
        String
        unprotect(String s) {
        return s.replace("&amp;", PART_SEPARATOR)
                .replace("&num;", ITEM_SEPARATOR);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
     */
    public final
        void
        init(IWorkbench workbench) {
        setPreferenceStore(Activator.getDefault()
                                    .getPreferenceStore());
        setDescription("Alligator connection settings");
    }

    /**
     * A list editor to set the platform name and ist URL.
     */
    public class AlligatorListEditor
        extends TableFieldEditor {

        /**
         * Build an editor
         */
        public AlligatorListEditor() {
            super(PreferenceConstants.P_ALLIGATOR_LIST, "Available alligator servers", new String[] {
                    "Name",
                    "URL",
                    "Refresh"
            }, new int[] {
                    200,
                    200,
                    30
            }, getFieldEditorParent());
        }

        @Override
        protected final
            String[]
            getNewInputObject() {
            return new String[] {
                    "New name",
                    "New URL",
                    "5"
            };
        }

        @Override
        protected final
            String[][]
            parseString(String stringList) {
            StringTokenizer itemTokenizer = new StringTokenizer(stringList, ITEM_SEPARATOR);
            List<String[]> result = new ArrayList<String[]>();
            while (itemTokenizer.hasMoreElements()) {
                String subList = itemTokenizer.nextToken();
                StringTokenizer partTokenizer = new StringTokenizer(subList, PART_SEPARATOR);
                String name = partTokenizer.nextToken();
                String url = partTokenizer.nextToken();
                String delay = partTokenizer.nextToken();
                if (!url.startsWith("http://")) {
                    url = "http://" + url;
                }
                if (!url.endsWith("/")) {
                    url = url + "/";
                }
                try {
                    Long.parseLong(delay);
                } catch (NumberFormatException e) {
                    delay = "15";
                }
                result.add(new String[] {
                        unprotect(name),
                        url,
                        delay
                });
            }
            return result.toArray(new String[result.size()][3]);
        }

        @Override
        protected final
            String
            createList(String[][] items) {
            StringBuffer result = new StringBuffer("");
            boolean first = true;
            for (String[] current : items) {
                String name = current[0];
                String url = current[1];
                String delay = current[2];
                if (!url.startsWith("http://")) {
                    url = "http://" + url;
                }
                if (!url.endsWith("/")) {
                    url = url + "/";
                }
                try {
                    Long.parseLong(delay);
                } catch (NumberFormatException e) {
                    delay = "15";
                }
                if (first) {
                    first = false;
                } else {
                    result.append(ITEM_SEPARATOR);
                }
                result.append(protect(name) + PART_SEPARATOR + url + PART_SEPARATOR + delay);
            }
            return result.toString();
        }

    }

    @Override
    protected final
        void
        createFieldEditors() {
        this.editor = new AlligatorListEditor();
        addField(editor);
    }

    /**
     * Configuration data for connections to Alligators
     */
    public static class Data {

        private String name;

        private URL address;

        private long refresh;

        /**
         * An Alligator server
         * 
         * @param name
         *        Its name
         * @param address
         *        Its URL
         */
        public Data(String name, URL address, long refresh) {
            this.name = name;
            this.address = address;
            this.refresh = refresh;
        }

        /**
         * @return the name of the server
         */
        public final
            String
            getName() {
            return name;
        }

        /**
         * @return the URL of the server
         * @throws MalformedURLException
         */
        @Deprecated
        public final
            URL
            getOldAddress()
                throws MalformedURLException {
            return new URL(address.toString() + "servicemanager");
        }

        /**
         * @return the URL of the server
         * @throws MalformedURLException
         */
        public final
            URL
            getAddress()
                throws MalformedURLException {
            return new URL(address.toString() + "services");
        }

        /**
         * @return the refresh rate for the menu of this server
         */
        public final
            long
            getRefresh() {
            return refresh;
        }

    }

    /**
     * @return a list of data read from the plugin preferences
     */
    public static
        List<Data>
        fromPreferences() {
        String s = Activator.getDefault()
                            .getPreferenceStore()
                            .getString(PreferenceConstants.P_ALLIGATOR_LIST);
        List<Data> result = new ArrayList<Data>();
        StringTokenizer itemTokenizer = new StringTokenizer(s, AlligatorPreferencePage.ITEM_SEPARATOR);
        while (itemTokenizer.hasMoreElements()) {
            String subList = itemTokenizer.nextToken();
            StringTokenizer partTokenizer = new StringTokenizer(subList, AlligatorPreferencePage.PART_SEPARATOR);
            try {
                String name = partTokenizer.nextToken();
                String url = partTokenizer.nextToken();
                String delay = partTokenizer.nextToken();
                result.add(new Data(unprotect(name), new URL(url), Long.parseLong(delay)));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
