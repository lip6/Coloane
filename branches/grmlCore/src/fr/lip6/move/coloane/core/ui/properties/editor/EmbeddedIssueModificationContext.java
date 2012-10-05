/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package fr.lip6.move.coloane.core.ui.properties.editor;

import com.google.inject.ImplementedBy;
import com.google.inject.Inject;
import com.google.inject.Provider;

import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.editor.model.edit.IModificationContext;
import org.eclipse.xtext.validation.Issue;

/**
 * Class that describes a context from which the document can be acquired
 * Is used, for example, by the quick fix mecanism to access the model so as to fix it.
 * @author Elodie Banel - Updated to be used in an embedded editor situation
 * @author Jan Koehnlein - Initial contribution and API
 */
public class EmbeddedIssueModificationContext implements IModificationContext {

	private IXtextDocument document;

	private Issue issue;

	/**
	 * @param issue The issue to set
	 */
	public final void setIssue(Issue issue) {
		this.issue = issue;
	}
	
	/**
	 * @return The issue associated with this context.
	 */
	public final Issue getIssue() {
		return issue;
	}

	/**
	 * @param document The document to set
	 */
	public final void setDocument(IXtextDocument document) {
		this.document = document;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final IXtextDocument getXtextDocument() {
		return getXtextDocument(issue.getUriToProblem());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final IXtextDocument getXtextDocument(URI uri) {
		return document;
	}

	
	/**
	 * Default factory interface to instantiate a context.
	 */
	@ImplementedBy(Factory.Default.class)
	public interface Factory {
		/**
		 * Creates a new modification context.
		 * @param issue The issue associated with this context
		 * @param document The document associated with this context
		 * @return The new context
		 */
		IModificationContext createModificationContext(Issue issue, IXtextDocument document);

		/**
		 * Default factory implementation to instantiate a context.
		 */
		public static class Default implements Factory {

			@Inject
			private Provider<EmbeddedIssueModificationContext> provider;

			/**
			 * {@inheritDoc}
			 */
			@Override
			public final IModificationContext createModificationContext(Issue issue, IXtextDocument document) {
				EmbeddedIssueModificationContext modificationContext = provider.get();
				modificationContext.setIssue(issue);
				modificationContext.setDocument(document);
				return modificationContext;
			}

		}
	}
}
