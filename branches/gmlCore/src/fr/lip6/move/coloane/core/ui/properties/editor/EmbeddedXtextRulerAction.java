/*******************************************************************************
 * Copyright (c) 2008 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 *******************************************************************************/
package fr.lip6.move.coloane.core.ui.properties.editor;

import java.util.Iterator;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextOperationTarget;
import org.eclipse.jface.text.source.AnnotationModel;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.IVerticalRulerInfo;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.texteditor.IUpdate;
import org.eclipse.ui.texteditor.MarkerAnnotation;
import org.eclipse.xtext.ui.editor.validation.XtextAnnotation;

/**
 * Super class is marked as not extendable, but it should be extendable
 * 
 * @author Dennis H�bner - Initial contribution and API
 * @author Holger Schill and Sebastian Zarnekow - https://bugs.eclipse.org/bugs/show_bug.cgi?id=310813
 */
public final class EmbeddedXtextRulerAction extends Action implements IUpdate {
	private EmbeddedXtextEditor textEditor;
	private IVerticalRulerInfo ruler;
	private IDocument document;
	private IAnnotationModel annotationModel;

	/**
	 * Create a new embedded ruler action
	 * @param document The document associated with this action.
	 * @param editor The editor associated with the ruler associated with this action.
	 * @param ruler The ruler associated with the action.
	 * @param model The annotation model associated with this model (used to find quick fixes).
	 */
	public EmbeddedXtextRulerAction(IDocument document, EmbeddedXtextEditor editor, IVerticalRulerInfo ruler, IAnnotationModel model) {
		this.textEditor = editor;
		this.ruler = ruler;
		this.document = document;
		this.annotationModel = model;
	}

	@Override
	public void run() {
		try {
			// Move offset to the line of the annotation, if necessary
			int annotationLine = ruler.getLineOfLastMouseButtonActivity();
			int annotationLineOffet = document.getLineOffset(annotationLine);
			Point currentSelection = textEditor.getViewer().getSelectedRange();
			int currentLine = document.getLineOfOffset(currentSelection.x);
			if (currentLine != annotationLine) {
				textEditor.getViewer().setSelectedRange(annotationLineOffet, 0);
			}

			// show QuickFix dialog
			ITextOperationTarget operation = textEditor.getViewer().getTextOperationTarget();
			final int opCode = ISourceViewer.QUICK_ASSIST;
			if (operation != null && operation.canDoOperation(opCode)) {
				operation.doOperation(opCode);
			}
		} catch (BadLocationException e) {
			return;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update() {
		setEnabled(hasQuickFixableAnnotationInCurrentLine());
	}

	/**
	 * @return Whether there is a quickfixable annotation in the current line.
	 */
	protected boolean hasQuickFixableAnnotationInCurrentLine() {
		try {
			int line = ruler.getLineOfLastMouseButtonActivity();
			IRegion region = document.getLineInformation(line);
			Iterator<?> iterator = ((AnnotationModel) annotationModel).getAnnotationIterator(region.getOffset(), region.getLength(), true, true);
			while (iterator.hasNext()) {
				Object element = iterator.next();
				if (element instanceof XtextAnnotation) {
					XtextAnnotation annotation = (XtextAnnotation) element;
					if (annotation.isQuickFixable()) {
						return true;
					}
				} else if (element instanceof MarkerAnnotation) {
					MarkerAnnotation annotation = (MarkerAnnotation) element;
					if (annotation.isQuickFixable()) {
						return true;
					}
				}
			}
		} catch (BadLocationException e) {
			return false;
		}
		return false;
	}

}
