package fr.lip6.move.coloane.core.ui.properties.editor;

import com.google.inject.Inject;

import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.editor.quickfix.IssueResolution;
import org.eclipse.xtext.ui.editor.quickfix.IssueResolutionProvider;
import org.eclipse.xtext.ui.editor.quickfix.XtextQuickAssistProcessor;
import org.eclipse.xtext.validation.Issue;

/**
 * Extension of xtextquickassistprocessor that integrates use of a document for
 * embedded edition.
 */
public class EmbeddedXtextQuickAssistProcessor extends XtextQuickAssistProcessor {
	
	@Inject
	private IssueResolutionProvider resolutionProvider;
	
	@Override
	public final Iterable<IssueResolution> getResolutions(final Issue issue, final IXtextDocument document) {
		if (resolutionProvider instanceof EmbeddedQuickfixProvider) {
			((EmbeddedQuickfixProvider) resolutionProvider).setDocument(document);
		}
		Iterable<IssueResolution> result = resolutionProvider.getResolutions(issue);
		return result;
	}
}
