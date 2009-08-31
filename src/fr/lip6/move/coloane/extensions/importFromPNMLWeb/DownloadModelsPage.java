package fr.lip6.move.coloane.extensions.importFromPNMLWeb;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;

import fr.lip6.move.pnmlweb.interfaces.IFormat;
import fr.lip6.move.pnmlweb.interfaces.IModelDescriptor;

public class DownloadModelsPage extends WizardPage {

	//private String selected;
	
	protected DownloadModelsPage(String pageName) {
		super(pageName);
		setTitle(pageName);
		setDescription("Clic to \"finish\" to import this model descriptor");
		// TODO Auto-generated constructor stub
	}

	public void createControl(Composite parent) {
		// TODO Auto-generated method stub
		
		/*
		for (IModelDescriptor m : ms) {
        	//System.out.println(m);
            // Recupere le format PNML de la version courante
            IFormat f = m.getCurrentVersion().getPNML();
            // Telecharge le PNML dans le chemin specifie
            System.out.println("*** Telechargement de " + f.getFilename() + " à partir de l'URI...");
            //c.downloadFormat(f, "/home/yaziz/Templates/" + f.getFilename());
            //System.out.println("URI de " + f.getFilename() + ": " + f.getUri());
             
            // reading directly from a url
            URL url = new URL(f.getUri());
            URLConnection uc = url.openConnection();	// open URL (HTTP query)

    		InputStreamReader input = new InputStreamReader(uc.getInputStream());	// open data stream
    		BufferedReader in = new BufferedReader(input);
		}
		*/
		
		
		Composite myComposite = new Composite(parent, SWT.NONE);
		myComposite.setLayout(new FillLayout(SWT.VERTICAL));
		Label label = new Label (myComposite, SWT.NONE);
	    label.setText ("Clic to finish to import this model: ");
	    //Label label2 = new Label(myComposite, SWT.NONE);
	    //label2.setText(selected);
	    	      
	    setControl(myComposite);		
	}

	
	public boolean canFlipToNextPage() {
		// no next page for this path through the wizard
		return false;
	}
	
	/*
	public void onEnterPage2(){
	    // Gets the model
	    ImportWizard wizard = (ImportWizard)getWizard();
		SearchModel model2 = wizard.getModel();
		System.out.println("**********"+model2.selected);
		this.selected = model2.selected;
	}
	*/
	
}
