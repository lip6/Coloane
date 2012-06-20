package fr.lip6.move.coloane.api.alligator.wizard;

import fr.lip6.move.coloane.core.ui.views.ModelLabelProvider;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
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

/**
 * Wizard page to select a list of resources using a filter.
 *
 * @author Clément Démoulins
 */
public class FilteredResourcesPage extends WizardPage {

	private final IResourceFilter filter;
	private CheckboxTreeViewer checkboxTreeViewer;

	/**
	 * Create the wizard.
	 * @param pageName Name of this wizard page used as a title
	 * @param filter resources filter
	 */
	public FilteredResourcesPage(String pageName, IResourceFilter filter) {
		super(pageName);
		setTitle(pageName);
		setDescription("Wizard Page description");
		this.filter = filter;
	}

	/**
	 * Create contents of the wizard.
	 * @param parent parent
	 */
	public final void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		container.setLayout(new FillLayout(SWT.VERTICAL));

		//create the input element, which has the root resource
        //as its only child
        List<IProject> input = new ArrayList<IProject>();
        IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
        for (int i = 0; i < projects.length; i++) {
            if (projects[i].isOpen()) {
				input.add(projects[i]);
			}
        }

		checkboxTreeViewer = new CheckboxTreeViewer(container, SWT.BORDER);
		checkboxTreeViewer.setContentProvider(new ITreeContentProvider() {
			@Override
			public void inputChanged(Viewer viewer, Object oldInput, Object newInput) { }
			@Override
			public void dispose() { }

			@Override
			public boolean hasChildren(Object element) {
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
			public Object getParent(Object element) {
				if (element instanceof IProject) {
					return null;
				}
				return ((IResource) element).getParent();
			}

			@Override
			public Object[] getElements(Object inputElement) {
				if (inputElement instanceof List<?>) {
					List<?> l = (List<?>) inputElement;
					return l.toArray();
				}
				return null;
			}

			@Override
			public Object[] getChildren(Object parentElement) {
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
			public String getText(Object element) {
				return ((IResource) element).getName();
			}

			@Override
			public Image getImage(Object element) {
				Image image = imageProvider.getImage(element);
				if (image == null) {
					return workbenchProvider.getImage(element);
				}
				return image;
			}

			@Override
			public void dispose() {
				super.dispose();
				imageProvider.dispose();
				workbenchProvider.dispose();
			}
		});
		checkboxTreeViewer.setInput(input);

		checkboxTreeViewer.addCheckStateListener(new ICheckStateListener() {
			@Override
			public void checkStateChanged(CheckStateChangedEvent event) {
				if (event.getElement() instanceof IContainer) {
					checkboxTreeViewer.setSubtreeChecked(event.getElement(), event.getChecked());
				}
				setPageComplete(validate());
			}
		});
		setPageComplete(validate());
	}

	/**
	 * @param resources resources
	 * @return a list a filtered resources
	 */
	private List<IResource> filtered(IResource[] resources) {
		List<IResource> fResources = new ArrayList<IResource>();
		for (IResource resource : resources) {
			if (!filter.isFiltered(resource)) {
				fResources.add(resource);
			}
		}
		return fResources;
	}

	/**
	 * @return list of selected resources
	 */
	public final List<IResource> getSelectedResources() {
		List<IResource> resources = new ArrayList<IResource>();
		for (Object o : checkboxTreeViewer.getCheckedElements()) {
			if (o instanceof IResource) {
				resources.add((IResource) o);
			}
		}
		return resources;
	}

	/**
	 * @return <code>true</code> if the selection is valid
	 */
	protected boolean validate() {
		return true;
	}
}
