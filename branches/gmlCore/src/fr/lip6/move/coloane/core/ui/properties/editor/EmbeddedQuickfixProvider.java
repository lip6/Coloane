package fr.lip6.move.coloane.core.ui.properties.editor;

import java.lang.reflect.Method;
import java.util.List;

import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.editor.quickfix.DefaultQuickfixProvider;
import org.eclipse.xtext.ui.editor.quickfix.Fix;
import org.eclipse.xtext.ui.editor.quickfix.IssueResolution;
import org.eclipse.xtext.validation.Issue;

import com.google.common.base.Predicate;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class EmbeddedQuickfixProvider extends DefaultQuickfixProvider {
	
	@Inject
	private Provider<EmbeddedIssueResolutionAcceptor> embeddedIssueResolutionAcceptorProvider;
	
	private IXtextDocument document;
	
	@Override
	protected Predicate<Method> getFixMethodPredicate(final String issueCode) {
		return new Predicate<Method>() {
			public boolean apply(Method input) {
				Fix annotation = input.getAnnotation(Fix.class);
				boolean result = annotation != null && issueCode.equals(annotation.value())
						&& input.getParameterTypes().length == 2 && Void.TYPE == input.getReturnType()
						&& input.getParameterTypes()[0].isAssignableFrom(Issue.class)
						&& input.getParameterTypes()[1].isAssignableFrom(EmbeddedIssueResolutionAcceptor.class);
				return result;
			}
		};
	}
	
	@Override
	protected List<IssueResolution> getResolutions(Issue issue, List<Method> fixMethods) {
		EmbeddedIssueResolutionAcceptor embeddedIssueResolutionAcceptor = embeddedIssueResolutionAcceptorProvider.get();
		embeddedIssueResolutionAcceptor.setDocument(document);
		for (Method fixMethod : fixMethods) {
			try {
				fixMethod.setAccessible(true);
				fixMethod.invoke(this, issue, embeddedIssueResolutionAcceptor);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return embeddedIssueResolutionAcceptor.getIssueResolutions();
	}
	
/*	@Override
	public List<IssueResolution> getResolutions(Issue issue) {
		if (Diagnostic.LINKING_DIAGNOSTIC.equals(issue.getCode())) {
			List<IssueResolution> result = new ArrayList<IssueResolution>();
			result.addAll(getResolutionsForLinkingIssue(issue));
			result.addAll(super.getResolutions(issue));
			return result;
		} else
			return super.getResolutions(issue);
	}*/
	
	public void setDocument(IXtextDocument document){
		this.document = document;
	}
}
