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

import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
        super(configurer);
    }

    protected void makeActions(IWorkbenchWindow window) {
    	IWorkbenchAction newFile = ActionFactory.NEW_WIZARD_DROP_DOWN.create(window);
    	newFile.setText("New");
    	register(newFile);
    	register(ActionFactory.ABOUT.create(window));
    }

	/** {@inheritDoc} */
	@Override
	protected void fillCoolBar(ICoolBarManager coolBar) {
	}

	protected void fillMenuBar(IMenuManager menuBar) {
    	IMenuManager fileMenu = new MenuManager("&File", "file");
    	fileMenu.add(getAction(ActionFactory.NEW_WIZARD_DROP_DOWN.getId()));
    	fileMenu.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));

    	IMenuManager settingsMenu = new MenuManager("&Settings", IWorkbenchActionConstants.M_HELP);
    	settingsMenu.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
    	settingsMenu.add(getAction(ActionFactory.ABOUT.getId()));
    	
    	menuBar.add(fileMenu);
    	menuBar.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
    	menuBar.add(settingsMenu);
    }
    
}
		