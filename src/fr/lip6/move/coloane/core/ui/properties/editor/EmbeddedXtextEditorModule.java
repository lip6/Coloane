/**
 * Copyright (c) 2010 ProxiAD
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cedric Vidal (ProxiAD) - initial API and implementation
 */

package fr.lip6.move.coloane.core.ui.properties.editor;

import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.service.AbstractGenericModule;

/**
 * Class that defines a module, used to bind various classes
 * which will be injected with Guice.
 */
public class EmbeddedXtextEditorModule extends AbstractGenericModule {

	/**
	 * @return (ignore, this should never be called by a user)
	 */
	public final Class<? extends XtextResource> bindXtextResource() {
		return EmbeddedXtextResource.class;
	}

	/**
	 * @return (ignore, this should never be called by a user)
	 */
	public final Class<? extends org.eclipse.xtext.resource.IContainer.Manager> bindIContainerManager() {
		return EmbeddedStateBasedContainerManager.class;
	}

}
