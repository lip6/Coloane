package fr.lip6.move.coloane.test.apiws.editors;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.editors.text.TextEditor;

public class XMLEditor extends TextEditor {

	private ColorManager colorManager;
	
	private final static TabListener listener = new TabListener();;
	
	public XMLEditor() {
		super();
		colorManager = new ColorManager();
		setSourceViewerConfiguration(new XMLConfiguration(colorManager));
		setDocumentProvider(new XMLDocumentProvider());
	}
	
	public final void createPartControl(Composite parent) {
		/*if (listener == null){
			listener = new TabListener();
			System.out.println("AJOUT DU LISTENER");
			getSite().getPage().addPartListener(listener);
			
		}*/
		getSite().getPage().addPartListener(listener);
		super.createPartControl(parent);
	}
	
	public void dispose() {
		colorManager.dispose();
		super.dispose();
	}

}
