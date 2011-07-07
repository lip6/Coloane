/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package fr.lip6.move.coloane.core.ui.properties.editor;

import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.editor.model.edit.IModificationContext;
import org.eclipse.xtext.validation.Issue;

import com.google.inject.ImplementedBy;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * @author Jan Koehnlein - Initial contribution and API
 */
public class EmbeddedIssueModificationContext implements IModificationContext {

	private IXtextDocument document;

	private Issue issue;

	public void setIssue(Issue issue) {
		this.issue = issue;
	}
	
	public Issue getIssue() {
		return issue;
	}

	public void setDocument(IXtextDocument document){
		this.document = document;
	}
	
	public IXtextDocument getXtextDocument() {
		return getXtextDocument(issue.getUriToProblem());
	}

	public IXtextDocument getXtextDocument(URI uri) {
		return document;
	}

	
	
	@ImplementedBy(Factory.Default.class)
	public static interface Factory {
		IModificationContext createModificationContext(Issue issue, IXtextDocument document);
		
		public static class Default implements Factory {
			
			@Inject
			private Provider<EmbeddedIssueModificationContext> provider;
			
			public IModificationContext createModificationContext(Issue issue, IXtextDocument document) {
				EmbeddedIssueModificationContext modificationContext = provider.get();
				modificationContext.setIssue(issue);
				modificationContext.setDocument(document);
				return modificationContext;
			}

		}
	}
}