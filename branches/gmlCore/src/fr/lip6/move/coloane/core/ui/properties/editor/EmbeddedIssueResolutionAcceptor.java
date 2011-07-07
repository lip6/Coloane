/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package fr.lip6.move.coloane.core.ui.properties.editor;

import java.util.List;

import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.editor.model.edit.IDocumentEditor;
import org.eclipse.xtext.ui.editor.model.edit.IModification;
import org.eclipse.xtext.ui.editor.model.edit.ISemanticModification;
import org.eclipse.xtext.ui.editor.model.edit.SemanticModificationWrapper;
import org.eclipse.xtext.ui.editor.quickfix.IssueResolution;
import org.eclipse.xtext.validation.Issue;

import com.google.common.collect.Lists;
import com.google.inject.Inject;

/**
 * @author Jan Koehnlein - Initial contribution and API
 */
public class EmbeddedIssueResolutionAcceptor {

	private List<IssueResolution> issueResolutions = Lists.newArrayList();

	private EmbeddedIssueModificationContext.Factory modificationContextFactory;

	private IDocumentEditor documentEditor;
	
	private IXtextDocument document;
	
	@Inject
	public EmbeddedIssueResolutionAcceptor(EmbeddedIssueModificationContext.Factory modificationContextFactory, IDocumentEditor documentEditor) {
		this.modificationContextFactory = modificationContextFactory;
		this.documentEditor = documentEditor;
	}

	public void setDocument(IXtextDocument document){
		this.document = document;
	}
	
	public void accept(Issue issue, String label, String description, String image, IModification modification) {
		issueResolutions.add(new IssueResolution(label, description, image, modificationContextFactory.createModificationContext(issue, document),
				modification));
	}

	public void accept(Issue issue, String label, String description, String image, ISemanticModification semanticModification) {
		SemanticModificationWrapper modificationWrapper = new SemanticModificationWrapper(issue.getUriToProblem(), semanticModification, documentEditor);
		issueResolutions.add(new IssueResolution(label, description, image, modificationContextFactory.createModificationContext(issue, document),
				modificationWrapper));
	}

	public List<IssueResolution> getIssueResolutions() {
		return issueResolutions;
	}

}
