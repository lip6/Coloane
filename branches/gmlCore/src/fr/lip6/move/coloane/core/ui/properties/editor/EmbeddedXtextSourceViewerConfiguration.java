package fr.lip6.move.coloane.core.ui.properties.editor;

import com.google.inject.Inject;

import org.eclipse.jface.text.quickassist.IQuickAssistAssistant;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.xtext.ui.editor.XtextSourceViewerConfiguration;

/**
 * Extension of the source viewer configuration class that allows the use of an injected quickassistassistant
 * @author Elodie Banel
 */
public class EmbeddedXtextSourceViewerConfiguration extends XtextSourceViewerConfiguration {

	@Inject
	private IQuickAssistAssistant quickAssistant;
	
	/**
	 * Constructor.
	 */
	public EmbeddedXtextSourceViewerConfiguration() {
		super();
	}
	
	/**
	 * {@inheritDoc}
	 */
    @Override
	public final IQuickAssistAssistant getQuickAssistAssistant(ISourceViewer sourceViewer) {
        if (sourceViewer.isEditable()) {
            return quickAssistant;
        }
        return null;
    }

}
