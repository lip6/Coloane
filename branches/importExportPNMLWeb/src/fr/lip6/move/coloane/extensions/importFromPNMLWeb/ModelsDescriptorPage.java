package fr.lip6.move.coloane.extensions.importFromPNMLWeb;

import java.io.IOException;

import javax.xml.stream.XMLStreamException;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;

import fr.lip6.move.pnmlweb.Caller;
import fr.lip6.move.pnmlweb.exceptions.ModelDescriptorException;
import fr.lip6.move.pnmlweb.exceptions.PnmlWEBException;
import fr.lip6.move.pnmlweb.interfaces.IModelDescriptor;


public class ModelsDescriptorPage extends WizardPage {
	
	//private List modelsDescriptorList;
		
	protected ModelsDescriptorPage(String pageName) {
		super(pageName);
		setTitle(pageName);
		setDescription("Choose a model descriptor to import");
		// TODO Auto-generated constructor stub
	}

	public void createControl(Composite parent) {
		// TODO Auto-generated method stub
		
		
		try {
			Caller c = new Caller("http://pnmlweb.lip6.fr", "admin", "admin1234");
			java.util.List<IModelDescriptor> ms = c.searchModeldescriptors(((ImportWizardPage)getWizard()).myText.getText());
			Composite myComposite = new Composite(parent, SWT.NONE);
			myComposite.setLayout(new FillLayout(SWT.VERTICAL));
			Label modelsDescriptorLabel = new Label(myComposite, SWT.NONE);
			modelsDescriptorLabel.setText(ms.size() + "models descriptors found");
			ListViewer modelsDescriptorListViewer = new ListViewer(myComposite, SWT.FULL_SELECTION);
			modelsDescriptorListViewer.setContentProvider(new MyStructuredContentProvider());
			modelsDescriptorListViewer.setLabelProvider(new MyLabelProvider());
			modelsDescriptorListViewer.setInput(ms);
			
			List modelDescriptorList = modelsDescriptorListViewer.getList();
			GridData gd = new GridData(GridData.FILL_BOTH);
			modelDescriptorList.setLayoutData(gd);
			
			
			
			/*
			java.util.List<IModelDescriptor> ms = c.searchModeldescriptors(((ImportWizardPage)getWizard()).myText.getText());
			modelsDescriptorList = (List) ms;
			Composite myComposite = new Composite(parent, SWT.NONE);
			myComposite.setLayout(new FillLayout(SWT.VERTICAL));
			//Label label = new Label (myComposite, SWT.UP);
		    //label.setText ("Choose a model descriptor to import: ");
		    modelsDescriptorList = new List (myComposite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		    modelsDescriptorList.setItems (new String [] {"Item 1", "Item2"});
		      */
		      
			setControl(myComposite);
			//setPageComplete(true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PnmlWEBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ModelDescriptorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		/*
		modelsDescriptorList = new List(myComposite, SWT.BORDER | SWT.READ_ONLY  );
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan =2;
		modelsDescriptorList.setLayoutData(gd);
		modelsDescriptorList.addListener(SWT.Selection, this);
		
		
		 ListViewer myListViewer = new ListViewer(myComposite, SWT.NONE);
		 myListViewer.setContentProvider(new MyStructuredContentProvider());
		 myListViewer.setLabelProvider(new MyLabelProvider());
		 myListViewer.setInput(myPerson);
		 List myList = myListViewer.getList();
		 GridData gridData = new GridData(GridData.FILL_BOTH);
		 myList.setLayoutData(gridData);
*/
		
	}

	
	public IWizardPage getNextPage() {    
		DownloadModelsPage page = ((ImportWizard)getWizard()).downloadModelsPage;
		return page;
	}
	
	public boolean canFlipToNextPage() {
		// no next page for this path through the wizard
		return true;
	}
	
	/*
	public boolean isPageComplete() {
		//ImportWizard wizard = (ImportWizard)getWizard();
		return true;
	}
	*/
	
	public class MyStructuredContentProvider implements IStructuredContentProvider {
		  public Object[] getElements(Object inputElement) {
			  //java.util.List<IModelDescriptor> ms = c.searchModeldescriptors(((ImportWizardPage)getWizard()).myText.getText());
				//modelsDescriptorList = (List) ms;
		  //java.util.List<IModelDescriptor> localInputElement = (java.util.List<IModelDescriptor>)inputElement;
			  
			  java.util.List<IModelDescriptor> ms = (java.util.List<IModelDescriptor>)inputElement;
		  return ms.toArray();
		}

		public void dispose() {
			// TODO Auto-generated method stub
			
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			// TODO Auto-generated method stub
			
		}
	}
	
		  
    public class MyLabelProvider extends LabelProvider {
    public String getText(Object element) {
    	if (element instanceof IModelDescriptor) 
             return ((IModelDescriptor)element).getName();
    	else 
             return "";
    	}
    }

	
}



