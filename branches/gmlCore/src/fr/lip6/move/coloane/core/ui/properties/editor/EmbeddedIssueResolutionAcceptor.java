/*******************************************************************************
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package fr.lip6.move.coloane.core.ui.properties.editor;

import com.google.common.collect.Lists;
import com.google.inject.Inject;

import java.util.List;

import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.editor.model.edit.IDocumentEditor;
import org.eclipse.xtext.ui.editor.model.edit.IModification;
import org.eclipse.xtext.ui.editor.model.edit.ISemanticModification;
import org.eclipse.xtext.ui.editor.model.edit.SemanticModificationWrapper;
import org.eclipse.xtext.ui.editor.quickfix.IssueResolution;
import org.eclipse.xtext.validation.Issue;

/**
 * @author Elodie Banel - Update to allow use in embedded editor
 * @author Jan Koehnlein - Initial contribution and API
 */
public class EmbeddedIssueResolutionAcceptor {

	private List<IssueResolution> issueResolutions = Lists.newArrayList();

	private EmbeddedIssueModificationContext.Factory modificationContextFactory;

	private IDocumentEditor documentEditor;
	
	private IXtextDocument document;
	
	/**
	 * Constructor.  The parameters are provided by Guice.
	 * @param modificationContextFactory provided by Guice
	 * @param documentEditor provided by Guice
	 */
	@Inject
	public EmbeddedIssueResolutionAcceptor(EmbeddedIssueModificationContext.Factory modificationContextFactory, IDocumentEditor documentEditor) {
		this.modificationContextFactory = modificationContextFactory;
		this.documentEditor = documentEditor;
	}

	/**
	 * @param document The document to set
	 */
	public final void setDocument(IXtextDocument document) {
		this.document = document;
	}
	
	/**
	 * Accept an issue.
	 * @param issue The issue.
	 * @param label The name of the issue.
	 * @param description A description of the issue.
	 * @param image An image associated with the issue.
	 * @param modification The IModification associated with this issue.
	 */
	public final void accept(Issue issue, String label, String description, String image, IModification modification) {
		issueResolutions.add(new IssueResolution(label, description, image, modificationContextFactory.createModificationContext(issue, document),
				modification));
	}

	/**
	 * Accept an issue.
	 * @param issue The issue.
	 * @param label The name of the issue.
	 * @param description A description of the issue.
	 * @param image An image associated with the issue.
	 * @param semanticModification The ISemanticModification associated with this issue.
	 */
	public final void accept(Issue issue, String label, String description, String image, ISemanticModification semanticModification) {
		SemanticModificationWrapper modificationWrapper = new SemanticModificationWrapper(issue.getUriToProblem(), semanticModification, documentEditor);
		issueResolutions.add(new IssueResolution(label, description, image, modificationContextFactory.createModificationContext(issue, document),
				modificationWrapper));
	}

	/**
	 * @return The list of issue resolutions
	 */
	public final List<IssueResolution> getIssueResolutions() {
		return issueResolutions;
	}

}
