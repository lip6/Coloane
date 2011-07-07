package fr.lip6.move.coloane.core.ui.properties.editor;

import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.editor.quickfix.IssueResolution;
import org.eclipse.xtext.ui.editor.quickfix.IssueResolutionProvider;
import org.eclipse.xtext.ui.editor.quickfix.XtextQuickAssistProcessor;
import org.eclipse.xtext.validation.Issue;

import com.google.inject.Inject;

public class EmbeddedXtextQuickAssistProcessor extends XtextQuickAssistProcessor{
	
	@Inject 
	private IssueResolutionProvider resolutionProvider;
	
	@Override
	public Iterable<IssueResolution> getResolutions(final Issue issue, final IXtextDocument document) {
		if (resolutionProvider instanceof EmbeddedQuickfixProvider) ((EmbeddedQuickfixProvider) resolutionProvider).setDocument(document);
		Iterable<IssueResolution> result = resolutionProvider.getResolutions(issue);
		return result;
	}
}
