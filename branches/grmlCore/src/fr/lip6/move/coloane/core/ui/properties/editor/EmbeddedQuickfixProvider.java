package fr.lip6.move.coloane.core.ui.properties.editor;

import com.google.common.base.Predicate;
import com.google.inject.Inject;
import com.google.inject.Provider;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.editor.quickfix.DefaultQuickfixProvider;
import org.eclipse.xtext.ui.editor.quickfix.Fix;
import org.eclipse.xtext.ui.editor.quickfix.IssueResolution;
import org.eclipse.xtext.validation.Issue;

/**
 * Class that allows providing of quickfixes to an embedded xtext editor.
 */
public class EmbeddedQuickfixProvider extends DefaultQuickfixProvider {
	
	@Inject
	private Provider<EmbeddedIssueResolutionAcceptor> embeddedIssueResolutionAcceptorProvider;
	
	private IXtextDocument document;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final Predicate<Method> getFixMethodPredicate(final String issueCode) {
		return new Predicate<Method>() {
			@Override
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
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected final List<IssueResolution> getResolutions(Issue issue, List<Method> fixMethods) {
		EmbeddedIssueResolutionAcceptor embeddedIssueResolutionAcceptor = embeddedIssueResolutionAcceptorProvider.get();
		embeddedIssueResolutionAcceptor.setDocument(document);
		for (Method fixMethod : fixMethods) {
			fixMethod.setAccessible(true);
			try {
				fixMethod.invoke(this, issue, embeddedIssueResolutionAcceptor);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}

		}
		return embeddedIssueResolutionAcceptor.getIssueResolutions();
	}
	
	/**
	 * @param document The document to be set.
	 */
	public final void setDocument(IXtextDocument document) {
		this.document = document;
	}
}
