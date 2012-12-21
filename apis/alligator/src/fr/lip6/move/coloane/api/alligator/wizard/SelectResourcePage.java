/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6). All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Jean-Baptiste VORON (LIP6) -
 * Project Head / Initial contributor Clément DÉMOULINS (LIP6) - Project Manager Official contacts: coloane@lip6.fr
 * http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.api.alligator.wizard;

import fr.lip6.move.coloane.api.alligator.Connection;
import fr.lip6.move.coloane.api.alligator.Utility;
import fr.lip6.move.coloane.core.ui.views.ModelLabelProvider;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.cosyverif.alligator.service.parameter.ResourceParameter;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.osgi.framework.Bundle;

/**
 * Create a wizard page to allow the user to choose the models to send to Alligator
 * 
 * @author Clément Démoulins
 */
public abstract class SelectResourcePage
    extends WizardPage {

    private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.api.alligator"); //$NON-NLS-1$

    protected IResourceFilter filter;

    private CheckboxTreeViewer checkboxTreeViewer;

    private IFile defaultSelection;

    private ResourceParameter<?, ?> parameter;

    public SelectResourcePage(String name, String title, ResourceParameter<?, ?> parameter) {
        super(name, title, Utility.getImage("alligator-logo.png"));
        setMessage(parameter.getHelp());
        this.parameter = parameter;
        try {
            this.defaultSelection = getIFile(parameter.getSource());
        } catch (IllegalArgumentException e) {
        }
    }

    /**
     * Create contents of the wizard.
     * 
     * @param parent
     *        parent
     */
    public
        void createControl(Composite parent) {
        Composite container = new Composite(parent, SWT.NULL);

        setControl(container);
        container.setLayout(new FillLayout(SWT.VERTICAL));

        // create the input element, which has the root resource as its only
        // child
        List<IProject> input = new ArrayList<IProject>();
        IProject[] projects = ResourcesPlugin.getWorkspace()
                                             .getRoot()
                                             .getProjects();
        for (int i = 0; i < projects.length; i++) {
            if (projects[i].isOpen()) {
                input.add(projects[i]);
            }
        }

        checkboxTreeViewer = new CheckboxTreeViewer(container, SWT.BORDER);
        checkboxTreeViewer.setContentProvider(new ITreeContentProvider() {
            @Override
            public
                void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
            }

            @Override
            public
                void dispose() {
            }

            @Override
            public
                boolean hasChildren(Object element) {
                if (element instanceof IContainer) {
                    IContainer container = (IContainer) element;
                    try {
                        // FIXME: can be improved
                        return filtered(container.members()).size() > 0;
                    } catch (CoreException e) {
                        return false;
                    }
                }
                return false;
            }

            @Override
            public
                Object getParent(Object element) {
                if (element instanceof IProject) {
                    return null;
                }
                return ((IResource) element).getParent();
            }

            @Override
            public
                Object[] getElements(Object inputElement) {
                if (inputElement instanceof List<?>) {
                    List<?> l = (List<?>) inputElement;
                    return l.toArray();
                }
                return null;
            }

            @Override
            public
                Object[] getChildren(Object parentElement) {
                if (parentElement instanceof IContainer) {
                    IContainer container = (IContainer) parentElement;
                    try {
                        return filtered(container.members()).toArray();
                    } catch (CoreException e) {
                        return null;
                    }
                }
                return null;
            }
        });
        checkboxTreeViewer.setLabelProvider(new LabelProvider() {
            private ModelLabelProvider imageProvider = new ModelLabelProvider();
            private ILabelProvider workbenchProvider = WorkbenchLabelProvider.getDecoratingWorkbenchLabelProvider();

            @Override
            public
                String getText(Object element) {
                return ((IResource) element).getName();
            }

            @Override
            public
                Image getImage(Object element) {
                Image image = imageProvider.getImage(element);
                if (image == null) {
                    return workbenchProvider.getImage(element);
                }
                return image;
            }

            @Override
            public
                void dispose() {
                super.dispose();
                imageProvider.dispose();
                workbenchProvider.dispose();
            }
        });
        checkboxTreeViewer.setInput(input);
        checkboxTreeViewer.expandAll();
        checkboxTreeViewer.addCheckStateListener(new ICheckStateListener() {
            @Override
            public
                void checkStateChanged(CheckStateChangedEvent event) {
                if (event.getElement() instanceof IContainer) {
                    checkboxTreeViewer.setSubtreeChecked(event.getElement(), event.getChecked());
                }
                setPageComplete(isPageComplete());
            }
        });
        if (defaultSelection != null) {
            checkboxTreeViewer.setCheckedElements(new Object[] {
                defaultSelection
            });
            checkboxTreeViewer.refresh();
        }
    }

    /**
     * @param resources
     *        resources
     * @return a list a filtered resources
     */
    private
        List<IResource> filtered(IResource[] resources) {
        List<IResource> fResources = new ArrayList<IResource>();
        for (IResource resource : resources) {
            if (!filter.isFiltered(resource)) {
                fResources.add(resource);
            }
        }
        return fResources;
    }

    public final
        IFile getSelectedFile() {
        return (IFile) checkboxTreeViewer.getCheckedElements()[0];
    }

    @Override
    public final
        boolean isPageComplete() {
        return checkboxTreeViewer.getCheckedElements().length == 1;
    }

    protected final
        IFile getIFile(File file) {
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        IPath location = Path.fromOSString(file.getAbsolutePath());
        return workspace.getRoot()
                        .getFileForLocation(location);
    }

    public final
        void performFinish() {
        parameter.setSource(getSelectedFile().getLocation()
                                             .toFile());
        parameter.setFile(getSelectedFile().getLocation()
                                           .toFile());
    }

}
