package fr.lip6.move.coloane.api.warehouse.objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ListDialog;

public class ModelsListChooser implements Runnable {
	
	/** The list of available models */
	private Map<String, String> models;
	
	/** The results */
	private List<String> results;
	
	/**
	 * Constructor
	 * @param models
	 */
	public ModelsListChooser(Map<String, String> models) {
		this.models = models;
		this.results = new ArrayList<String>();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void run() {

    	/**
    	 * Label provider for the dialog box
    	 * @author Jean-Baptiste Voron
    	 */
    	class ModelsLabelProvider implements ILabelProvider {
    		/** {@inheritDoc} */
			public void addListener(ILabelProviderListener listener) { }
    		/** {@inheritDoc} */
			public void dispose() {	}
    		/** {@inheritDoc} */
			public boolean isLabelProperty(Object element, String property) { return false;	}
    		/** {@inheritDoc} */
			public void removeListener(ILabelProviderListener listener) { }
    		/** {@inheritDoc} */
			public Image getImage(Object element) { return null; }
    		/** {@inheritDoc} */
			public String getText(Object element) { 
				return element.toString(); 
			}		
    	}
    	
    	/**
    	 * Content provider for the dialog box
    	 * @author Jean-Baptiste Voron
    	 */
    	class ModelsContentProvider implements IStructuredContentProvider {
    		private Map<String, String> models;
    		/** {@inheritDoc} */
    		public ModelsContentProvider(Map<String, String> models) {
    			this.models = models;
			}
    		/** {@inheritDoc} */
			public void dispose() { }
			/** {@inheritDoc} */
			public void inputChanged(Viewer viewer, Object oldInput, Object newInput) { }
			/** {@inheritDoc} */
			public Object[] getElements(Object inputElement) {
		    	return models.values().toArray();
			}
    	}
    	
    	Shell activeShell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
    	ListDialog listDialog = new ListDialog(activeShell);
    	listDialog.setAddCancelButton(true);
    	listDialog.setContentProvider(new ModelsContentProvider(models));
    	listDialog.setLabelProvider(new ModelsLabelProvider());
    	listDialog.setInput(new Object());
    	
    	listDialog.setTitle("Select the model you want to import into Coloane:");
    	listDialog.setBlockOnOpen(true);
    	if (listDialog.open() == Dialog.OK) {
    		for (Object selectedModel : listDialog.getResult()) {
				this.results.add((String)selectedModel); 
			}
    	}
	}
	
	/**
	 * @return The list of results selected in the dialog box
	 */
	public List<String> getResults() {
		return this.results;
	}

}
