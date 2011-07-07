package fr.lip6.move.coloane.core.ui.properties.editor;

import org.eclipse.jface.text.quickassist.IQuickAssistAssistant;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.xtext.ui.editor.XtextSourceViewerConfiguration;

import com.google.inject.Inject;

public class EmbeddedXtextSourceViewerConfiguration extends XtextSourceViewerConfiguration {

	@Inject
	private IQuickAssistAssistant quickAssistant;
	
	public EmbeddedXtextSourceViewerConfiguration(){
		super();
	}
	
    @Override
    public IQuickAssistAssistant getQuickAssistAssistant(ISourceViewer sourceViewer) {
        if (sourceViewer.isEditable()) {
            return quickAssistant;
        }
        return null;
    }

}
