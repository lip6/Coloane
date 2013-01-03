/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6). All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0 which accompanies this
 * distribution, and is available at http://www.eclipse.org/legal/epl-v10.html Contributors: Jean-Baptiste VORON (LIP6) -
 * Project Head / Initial contributor Clément DÉMOULINS (LIP6) - Project Manager Official contacts: coloane@lip6.fr
 * http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.api.alligator.dialog;

import fr.lip6.move.coloane.api.alligator.wizard.Wizard;
import fr.lip6.move.coloane.core.ui.views.ModelLabelProvider;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.cosyverif.alligator.service.Parameter;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.model.WorkbenchLabelProvider;

public abstract class InputResourceDialog<P extends Parameter<P>>
    extends Dialog<P> {

    private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.api.alligator"); //$NON-NLS-1$

    protected Label label;
    protected Label help;
    protected Label error;
    protected CheckboxTreeViewer checkboxTreeViewer;

    public InputResourceDialog(P parameter, boolean editable) {
        super(parameter, editable);
        /* setMessage(parameter.getHelp()); this.parameter = parameter; try { this.defaultSelection =
         * getIFile(parameter.getSource()); } catch (IllegalArgumentException e) { } */
    }

    @Override
    public
        int size() {
        return Wizard.PAGE_SIZE;
    }

    @Override
    public
        void create(Composite parent) {
        // Label:
        label = new Label(parent, SWT.WRAP);
        label.setText(parameter.getName() + ":");
        label.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
        // Error:
        error = new Label(parent, SWT.WRAP);
        error.setText("");
        error.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
        error.setForeground(errorFontColor);
        // Help message:
        help = new Label(parent, SWT.WRAP);
        help.setText(parameter.getHelp());
        help.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
        // create the input element, which has the root resource as its only
        // child
        List<IProject> input = new ArrayList<IProject>();
        for (IProject project : ResourcesPlugin.getWorkspace()
                                               .getRoot()
                                               .getProjects()) {
            if (project.isOpen()) {
                input.add(project);
            }
        }
        checkboxTreeViewer = new CheckboxTreeViewer(parent, SWT.BORDER);
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
            }
        });
    }

    @Override
    public
        String errorMessage() {
        if (checkboxTreeViewer.getCheckedElements().length == 1) {
            return null;
        } else {
            return "Only one resource cam be selected.";
        }
    }

    @Override
    public
        void update(Parameter<?> that) {

    }

    abstract
        boolean keepResource(IResource resource);

    private
        List<IResource> filtered(IResource[] resources) {
        List<IResource> result = new ArrayList<IResource>();
        for (IResource resource : resources) {
            if (keepResource(resource)) {
                result.add(resource);
            }
        }
        return result;
    }

    protected final
        IFile getSelectedFile() {
        return (IFile) checkboxTreeViewer.getCheckedElements()[0];
    }

    protected final
        IFile getIFile(File file) {
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        IPath location = Path.fromOSString(file.getAbsolutePath());
        return workspace.getRoot()
                        .getFileForLocation(location);
    }

}
