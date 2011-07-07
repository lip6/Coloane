/** 
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package fr.lip6.move.coloane.core.ui.properties.editor;

import com.google.inject.Inject;
import com.google.inject.Provider;

import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.xtext.ui.editor.XtextPresentationReconciler;
import org.eclipse.xtext.ui.editor.XtextSourceViewer;
import org.eclipse.xtext.ui.editor.XtextSourceViewerConfiguration;
import org.eclipse.xtext.ui.editor.preferences.IPreferenceStoreAccess;
import org.eclipse.xtext.ui.editor.syntaxcoloring.HighlightingPresenter;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class HighlightingHelper implements IPropertyChangeListener {

	@Inject
	private Provider<HighlightingReconciler> reconcilerProvider;

	@Inject
	private Provider<HighlightingPresenter> presenterProvider;

	@Inject
	private IPreferenceStoreAccess preferenceStoreAccessor;
	
	/** Highlighting presenter */
	@Inject
	private HighlightingPresenter fPresenter;

	/** Highlighting reconciler */
	@Inject
	private HighlightingReconciler fReconciler;

	/** The source viewer */
	private XtextSourceViewer fSourceViewer;
	/** The source viewer configuration */
	private XtextSourceViewerConfiguration fConfiguration;
	/** The presentation reconciler */
	private XtextPresentationReconciler fPresentationReconciler;

	/**
	 * Install the highlighting helper
	 * @param configuration The configuration of the viewer to add highlighting to.
	 * @param sourceViewer The viewer to add highlighting to.
	 */
	public final void install(XtextSourceViewerConfiguration configuration, XtextSourceViewer sourceViewer) {
		fSourceViewer = sourceViewer;
		fConfiguration = configuration;
		fPresentationReconciler = (XtextPresentationReconciler) fConfiguration.getPresentationReconciler(sourceViewer);
		preferenceStoreAccessor.getPreferenceStore().addPropertyChangeListener(this);
		enable();
	}

	/**
	 * Enable advanced highlighting.
	 */
	private void enable() {
		fPresenter = getPresenterProvider().get();
		fPresenter.install(fSourceViewer, fPresentationReconciler);

		if (fSourceViewer.getDocument() != null) {
			fReconciler = reconcilerProvider.get();
			fReconciler.install(fSourceViewer, fPresenter);
		}
	}

	/**
	 * Uninstall the highlighting helper
	 */
	public final void uninstall() {
		disable();
		preferenceStoreAccessor.getPreferenceStore().removePropertyChangeListener(this);
		fSourceViewer = null;
		fConfiguration = null;
		fPresentationReconciler = null;
	}

	/**
	 * Disable advanced highlighting.
	 */
	private void disable() {
		if (fReconciler != null) {
			fReconciler.uninstall();
			fReconciler = null;
		}

		if (fPresenter != null) {
			fPresenter.uninstall();
			fPresenter = null;
		}
	}

	/**
	 * Returns this hightlighter's reconciler.
	 *
	 * @return the highlighter reconciler or <code>null</code> if none
	 */
	public final HighlightingReconciler getReconciler() {
		return fReconciler;
	}

	/**
	 * @param reconcilerProvider The reconciler provider to set.
	 */
	public final void setReconcilerProvider(Provider<HighlightingReconciler> reconcilerProvider) {
		this.reconcilerProvider = reconcilerProvider;
	}

	/**
	 * @return The reconciler provider.
	 */
	public final Provider<HighlightingReconciler> getReconcilerProvider() {
		return reconcilerProvider;
	}

	/**
	 * @param presenterProvider The presenter provider to set.
	 */
	public final void setPresenterProvider(Provider<HighlightingPresenter> presenterProvider) {
		this.presenterProvider = presenterProvider;
	}

	/**
	 * @return The presenter provider.
	 */
	public final Provider<HighlightingPresenter> getPresenterProvider() {
		return presenterProvider;
	}

	/**
	 * @param preferenceStoreAccessor The preference store accessor to set.
	 */
	public final void setPreferenceStoreAccessor(IPreferenceStoreAccess preferenceStoreAccessor) {
		this.preferenceStoreAccessor = preferenceStoreAccessor;
	}

	/**
	 * @return The preference store accessor.
	 */
	public final IPreferenceStoreAccess getPreferenceStoreAccessor() {
		return preferenceStoreAccessor;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void propertyChange(PropertyChangeEvent event) {
		if (fReconciler != null && event.getProperty().contains(".syntaxColorer.tokenStyles")) { //$NON-NLS-1$
			fReconciler.refresh();
		}
	}
}

