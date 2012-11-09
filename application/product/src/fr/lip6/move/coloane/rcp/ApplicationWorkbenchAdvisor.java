/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Jean-Baptiste VORON (LIP6) - Project Head / Initial contributor
 *   Clément DÉMOULINS (LIP6) - Project Manager
 *
 * Official contacts:
 *   coloane@lip6.fr
 *   http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.rcp;

import fr.lip6.move.coloane.rcp.internal.IDEInternalWorkbenchImages;

import java.net.URL;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.application.IWorkbenchConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.ui.ide.IDE;
import org.osgi.framework.Bundle;

/**
 * @author Clément Démoulins
 */
public class ApplicationWorkbenchAdvisor extends WorkbenchAdvisor {

	private static final String PERSPECTIVE_ID = "fr.lip6.move.coloane.perspective"; //$NON-NLS-1$

	/**
	 * Copied from org.eclipse.ui.internal.ide.IDEWorkbenchPlugin.IDE_WORKBENCH.<br/>
	 * Dependencies on classes from internal eclipse packages is not recommended.
	 */
	private static final String IDE_WORKBENCH = "org.eclipse.ui.ide"; //$NON-NLS-1$

    /** {@inheritDoc} */
    public final WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        return new ApplicationWorkbenchWindowAdvisor(configurer);
    }

	/** {@inheritDoc} */
	public final String getInitialWindowPerspectiveId() {
		return PERSPECTIVE_ID;
	}

	/** {@inheritDoc} */
	@Override
	public final IAdaptable getDefaultPageInput() {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		return workspace.getRoot();
	}

	/** {@inheritDoc} */
	@Override
	public final void initialize(IWorkbenchConfigurer configurer) {
		super.initialize(configurer);
		configurer.setSaveAndRestore(true);
		IDE.registerAdapters();
		declareWorkbenchImages();
	}

	/**
	 * Declares an IDE-specific workbench image.
	 * 
	 * @param ideBundle Bundle
	 * @param symbolicName
	 *            the symbolic name of the image
	 * @param path
	 *            the path of the image file; this path is relative to the base
	 *            of the IDE plug-in
	 * @param shared
	 *            <code>true</code> if this is a shared image, and
	 *            <code>false</code> if this is not a shared image
	 * @see IWorkbenchConfigurer#declareImage
	 */
	private void declareWorkbenchImage(Bundle ideBundle, String symbolicName, String path, boolean shared) {
		URL url = FileLocator.find(ideBundle, new Path(path), null);
		ImageDescriptor desc = ImageDescriptor.createFromURL(url);
		getWorkbenchConfigurer().declareImage(symbolicName, desc, shared);
	}

	/**
	 * Declares all IDE-specific workbench images. This includes both "shared"
	 * images (named in {@link IDE.SharedImages}) and internal images (named in
	 * 
	 * @see IWorkbenchConfigurer#declareImage
	 */
	private void declareWorkbenchImages() {
		final String iconsPath = "$nl$/icons/full/"; //$NON-NLS-1$
		final String pathELocalTool = iconsPath + "elcl16/"; // Enabled //$NON-NLS-1$

		// toolbar
		// icons.
		final String pathDLocalTool = iconsPath + "dlcl16/"; // Disabled //$NON-NLS-1$
		// toolbar
		// icons.
		final String pathETool = iconsPath + "etool16/"; // Enabled toolbar //$NON-NLS-1$
		// //$NON-NLS-1$
		// icons.
		final String pathDTool = iconsPath + "dtool16/"; // Disabled toolbar //$NON-NLS-1$
		// //$NON-NLS-1$
		// icons.
		final String pathObject = iconsPath + "obj16/"; // Model object //$NON-NLS-1$
		// //$NON-NLS-1$
		// icons
		final String pathWizban = iconsPath + "wizban/"; // Wizard //$NON-NLS-1$
		// //$NON-NLS-1$
		// icons

		// View icons
		final String pathEView = iconsPath + "eview16/"; //$NON-NLS-1$


		Bundle ideBundle = Platform.getBundle(IDE_WORKBENCH);

		declareWorkbenchImage(ideBundle, IDEInternalWorkbenchImages.IMG_ETOOL_BUILD_EXEC, pathETool + "build_exec.gif", false); //$NON-NLS-1$
		declareWorkbenchImage(ideBundle, IDEInternalWorkbenchImages.IMG_ETOOL_BUILD_EXEC_HOVER, pathETool + "build_exec.gif", false); //$NON-NLS-1$
		declareWorkbenchImage(ideBundle, IDEInternalWorkbenchImages.IMG_ETOOL_BUILD_EXEC_DISABLED, pathDTool + "build_exec.gif", false); //$NON-NLS-1$

		declareWorkbenchImage(ideBundle, IDEInternalWorkbenchImages.IMG_ETOOL_SEARCH_SRC, pathETool + "search_src.gif", false); //$NON-NLS-1$
		declareWorkbenchImage(ideBundle, IDEInternalWorkbenchImages.IMG_ETOOL_SEARCH_SRC_HOVER, pathETool + "search_src.gif", false); //$NON-NLS-1$
		declareWorkbenchImage(ideBundle, IDEInternalWorkbenchImages.IMG_ETOOL_SEARCH_SRC_DISABLED, pathDTool + "search_src.gif", false); //$NON-NLS-1$

		declareWorkbenchImage(ideBundle, IDEInternalWorkbenchImages.IMG_ETOOL_NEXT_NAV, pathETool + "next_nav.gif", false); //$NON-NLS-1$

		declareWorkbenchImage(ideBundle, IDEInternalWorkbenchImages.IMG_ETOOL_PREVIOUS_NAV, pathETool + "prev_nav.gif", false); //$NON-NLS-1$

		declareWorkbenchImage(ideBundle, IDEInternalWorkbenchImages.IMG_WIZBAN_NEWPRJ_WIZ, pathWizban + "newprj_wiz.png", false); //$NON-NLS-1$
		declareWorkbenchImage(ideBundle, IDEInternalWorkbenchImages.IMG_WIZBAN_NEWFOLDER_WIZ, pathWizban + "newfolder_wiz.png", false); //$NON-NLS-1$
		declareWorkbenchImage(ideBundle, IDEInternalWorkbenchImages.IMG_WIZBAN_NEWFILE_WIZ, pathWizban + "newfile_wiz.png", false); //$NON-NLS-1$

		declareWorkbenchImage(ideBundle, IDEInternalWorkbenchImages.IMG_WIZBAN_IMPORTDIR_WIZ, pathWizban + "importdir_wiz.png", false); //$NON-NLS-1$
		declareWorkbenchImage(ideBundle, IDEInternalWorkbenchImages.IMG_WIZBAN_IMPORTZIP_WIZ, pathWizban + "importzip_wiz.png", false); //$NON-NLS-1$

		declareWorkbenchImage(ideBundle, IDEInternalWorkbenchImages.IMG_WIZBAN_EXPORTDIR_WIZ, pathWizban + "exportdir_wiz.png", false); //$NON-NLS-1$
		declareWorkbenchImage(ideBundle, IDEInternalWorkbenchImages.IMG_WIZBAN_EXPORTZIP_WIZ, pathWizban + "exportzip_wiz.png", false); //$NON-NLS-1$

		declareWorkbenchImage(ideBundle, IDEInternalWorkbenchImages.IMG_WIZBAN_RESOURCEWORKINGSET_WIZ, pathWizban + "workset_wiz.png", false); //$NON-NLS-1$

		declareWorkbenchImage(ideBundle, IDEInternalWorkbenchImages.IMG_DLGBAN_SAVEAS_DLG, pathWizban + "saveas_wiz.png", false); //$NON-NLS-1$

		declareWorkbenchImage(ideBundle, IDEInternalWorkbenchImages.IMG_DLGBAN_QUICKFIX_DLG, pathWizban + "quick_fix.png", false); //$NON-NLS-1$

		declareWorkbenchImage(ideBundle, IDE.SharedImages.IMG_OBJ_PROJECT, pathObject + "prj_obj.gif", true); //$NON-NLS-1$
		declareWorkbenchImage(ideBundle, IDE.SharedImages.IMG_OBJ_PROJECT_CLOSED, pathObject + "cprj_obj.gif", true); //$NON-NLS-1$
		declareWorkbenchImage(ideBundle, IDE.SharedImages.IMG_OPEN_MARKER, pathELocalTool + "gotoobj_tsk.gif", true); //$NON-NLS-1$


		// Quick fix icons
		declareWorkbenchImage(ideBundle, IDEInternalWorkbenchImages.IMG_ELCL_QUICK_FIX_ENABLED, pathELocalTool + "smartmode_co.gif", true); //$NON-NLS-1$

		declareWorkbenchImage(ideBundle, IDEInternalWorkbenchImages.IMG_DLCL_QUICK_FIX_DISABLED, pathDLocalTool + "smartmode_co.gif", true); //$NON-NLS-1$

		declareWorkbenchImage(ideBundle, IDEInternalWorkbenchImages.IMG_OBJS_FIXABLE_WARNING, pathObject + "quickfix_warning_obj.gif", true); //$NON-NLS-1$
		declareWorkbenchImage(ideBundle, IDEInternalWorkbenchImages.IMG_OBJS_FIXABLE_ERROR, pathObject + "quickfix_error_obj.gif", true); //$NON-NLS-1$


		declareWorkbenchImage(ideBundle, IDE.SharedImages.IMG_OBJS_TASK_TSK, pathObject + "taskmrk_tsk.gif", true); //$NON-NLS-1$
		declareWorkbenchImage(ideBundle, IDE.SharedImages.IMG_OBJS_BKMRK_TSK, pathObject + "bkmrk_tsk.gif", true); //$NON-NLS-1$

		declareWorkbenchImage(ideBundle, IDEInternalWorkbenchImages.IMG_OBJS_COMPLETE_TSK, pathObject + "complete_tsk.gif", true); //$NON-NLS-1$
		declareWorkbenchImage(ideBundle, IDEInternalWorkbenchImages.IMG_OBJS_INCOMPLETE_TSK, pathObject + "incomplete_tsk.gif", true); //$NON-NLS-1$
		declareWorkbenchImage(ideBundle, IDEInternalWorkbenchImages.IMG_OBJS_WELCOME_ITEM, pathObject + "welcome_item.gif", true); //$NON-NLS-1$
		declareWorkbenchImage(ideBundle, IDEInternalWorkbenchImages.IMG_OBJS_WELCOME_BANNER, pathObject + "welcome_banner.gif", true); //$NON-NLS-1$
		declareWorkbenchImage(ideBundle, IDEInternalWorkbenchImages.IMG_OBJS_ERROR_PATH, pathObject + "error_tsk.gif", true); //$NON-NLS-1$
		declareWorkbenchImage(ideBundle, IDEInternalWorkbenchImages.IMG_OBJS_WARNING_PATH, pathObject + "warn_tsk.gif", true); //$NON-NLS-1$
		declareWorkbenchImage(ideBundle, IDEInternalWorkbenchImages.IMG_OBJS_INFO_PATH, pathObject + "info_tsk.gif", true); //$NON-NLS-1$

		declareWorkbenchImage(ideBundle, IDEInternalWorkbenchImages.IMG_LCL_FLAT_LAYOUT, pathELocalTool + "flatLayout.gif", true); //$NON-NLS-1$
		declareWorkbenchImage(ideBundle, IDEInternalWorkbenchImages.IMG_LCL_HIERARCHICAL_LAYOUT, pathELocalTool + "hierarchicalLayout.gif", true); //$NON-NLS-1$
		declareWorkbenchImage(ideBundle, IDEInternalWorkbenchImages.IMG_ETOOL_PROBLEM_CATEGORY, pathETool + "problem_category.gif", true); //$NON-NLS-1$

		declareWorkbenchImage(ideBundle, IDEInternalWorkbenchImages.IMG_ETOOL_PROBLEMS_VIEW, pathEView + "problems_view.gif", true); //$NON-NLS-1$
		declareWorkbenchImage(ideBundle, IDEInternalWorkbenchImages.IMG_ETOOL_PROBLEMS_VIEW_ERROR, pathEView + "problems_view_error.gif", true); //$NON-NLS-1$
		declareWorkbenchImage(ideBundle, IDEInternalWorkbenchImages.IMG_ETOOL_PROBLEMS_VIEW_WARNING, pathEView + "problems_view_warning.gif", true); //$NON-NLS-1$
	}
}
