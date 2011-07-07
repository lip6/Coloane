package fr.lip6.move.coloane.core.ui.properties.editor;

import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;
import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.text.source.projection.IProjectionListener;
import org.eclipse.jface.text.source.projection.ProjectionAnnotation;
import org.eclipse.jface.text.source.projection.ProjectionAnnotationModel;
import org.eclipse.jface.text.source.projection.ProjectionViewer;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.ui.editor.folding.DefaultFoldingRegionProvider;
import org.eclipse.xtext.ui.editor.folding.IFoldingRegion;
import org.eclipse.xtext.ui.editor.folding.IFoldingRegionProvider;
import org.eclipse.xtext.ui.editor.folding.IFoldingStructureProvider;
import org.eclipse.xtext.ui.editor.folding.StyledProjectionAnnotation;
import org.eclipse.xtext.ui.editor.model.IXtextModelListener;

/**
 * Default implementation of interface {@link IFoldingStructureProvider}
 * @author Michael Clay - Initial contribution and API
 * @author Mikael Barbero - Dependant of Embedded XtextEditor
 */
public class EmbeddedFoldingStructureProvider implements IXtextModelListener, IFoldingStructureProvider {
	
	@Inject
	private IFoldingRegionProvider foldingRegionProvider = new DefaultFoldingRegionProvider();
	private EmbeddedXtextEditor editor;
	private ProjectionViewer viewer;
	private ProjectionChangeListener projectionListener;

	/**
	 * Installs this structure provider on the given editor and viewer. Implementations should listen to the projection
	 * events generated by <code>viewer</code> and enable / disable generation of projection structure accordingly.
	 * 
	 * @param editor the editor that this provider works on
	 * @param viewer the projection viewer that displays the annotations created by this structure provider
	 */
	public final void install(EmbeddedXtextEditor editor, ProjectionViewer viewer) {
		Assert.isNotNull(editor);
		Assert.isNotNull(viewer);
		uninstall();
		this.editor = editor;
		this.viewer = viewer;
		projectionListener = new ProjectionChangeListener(viewer);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void initialize() {
		calculateProjectionAnnotationModel(false);
	}

	/**
	 * {@inheritDoc}
	 */
	public final void uninstall() {
		if (isInstalled()) {
			handleProjectionDisabled();
			projectionListener.dispose();
			projectionListener = null;
			editor = null;
		}
	}

	/**
	 * Returns <code>true</code> if the provider is installed, <code>false</code> otherwise.
	 * 
	 * @return <code>true</code> if the provider is installed, <code>false</code> otherwise
	 */
	protected final boolean isInstalled() {
		return editor != null;
	}

	/**
	 * @see org.eclipse.xtext.ui.editor.model.IXtextModelListener#modelChanged(org.eclipse.xtext.resource.XtextResource)
	 * @param resource Doesn't actually serve any purpose but has to be given in order to implement
	 * IXtextModelListener.
	 */
	public final void modelChanged(XtextResource resource) {
		//this stops folding from working correctly if there are errors
		//I don't see why folding should not be allowed if there are errors, so
		//I removed it.
		/*boolean existingSyntaxErrors = Iterables.any(resource.getErrors(), new Predicate<Diagnostic>() {
			public boolean apply(Diagnostic diagnostic) {
				return diagnostic instanceof XtextSyntaxDiagnostic;
			}
		});

		if (!existingSyntaxErrors) {*/
			calculateProjectionAnnotationModel(false);
		//}
	}

	/**
	 * Handles the enabling of projection
	 */
	protected final void handleProjectionEnabled() {
		handleProjectionDisabled();
		if (isInstalled()) {
			initialize();
			editor.getDocument().addModelListener(this);
		}
	}

	/**
	 * Handles the disabling of projection
	 */
	protected final void handleProjectionDisabled() {
		if (editor.getDocument() != null) {
			editor.getDocument().removeModelListener(this);
		}
	}

	/**
	 * Calculates the projection annotations for the viewer.
	 * @param allowCollapse Whether collapsing of the regions is allowed or not
	 */
	protected final void calculateProjectionAnnotationModel(boolean allowCollapse) {
 		ProjectionAnnotationModel projectionAnnotationModel = this.viewer.getProjectionAnnotationModel();
		if (projectionAnnotationModel != null) {
			List<IFoldingRegion> foldingRegions = foldingRegionProvider.getFoldingRegions(editor.getDocument());
			HashBiMap<Position, IFoldingRegion> positionsMap = toPositionIndexedMap(foldingRegions);
			Annotation[] newRegions = mergeFoldingRegions(positionsMap, projectionAnnotationModel);
			updateFoldingRegions(allowCollapse, projectionAnnotationModel, positionsMap, newRegions);
		}
	}

	/**
	 * Maps the list of regions according to their position
	 * @param foldingRegions the original list of regions
	 * @return the regions mapped according to position
	 */
	protected final HashBiMap<Position, IFoldingRegion> toPositionIndexedMap(List<IFoldingRegion> foldingRegions) {
		HashBiMap<Position, IFoldingRegion> positionsMap = Maps.newHashBiMap();
		for (IFoldingRegion foldingRegion : foldingRegions) {
			positionsMap.put(foldingRegion.getPosition(), foldingRegion);
		}
		return positionsMap;
	}

	/**
	 * Updates the given model and map so that they can be merged.  The positions that exist in the model
	 * but not in the map are returned; those that already exist are erased from the map.  At the end
	 * of the execution of this function, the model is unchanged and the map contains only those regions
	 * not contained by the model.
	 * @param positionsMap The regions mapped by position.
	 * @param projectionAnnotationModel The existing annotation model.
	 * @return The list of annotations found both in the map and the model.
	 */
	@SuppressWarnings("unchecked")
	protected final Annotation[] mergeFoldingRegions(HashBiMap<Position, IFoldingRegion> positionsMap,
			ProjectionAnnotationModel projectionAnnotationModel) {
		List<Annotation> deletions = new ArrayList<Annotation>();
		for (Iterator<Annotation> iterator = projectionAnnotationModel.getAnnotationIterator(); iterator.hasNext();) {
			Annotation annotation = iterator.next();
			if (annotation instanceof ProjectionAnnotation) {
				Position position = projectionAnnotationModel.getPosition(annotation);
				if (positionsMap.remove(position) == null) {
					deletions.add(annotation);
				}
			}
		}
		return deletions.toArray(new Annotation[deletions.size()]);
	}

	/**
	 * Updates the annotation model according to the parameters.
	 * @param allowCollapse Whether to allow new annotations to be collapsed.
	 * @param model The existing annotation model.
	 * @param positionsMap The map of new regions to be annotated in the model.
	 * @param deletions The list of annotations to delete.
	 */
	protected final void updateFoldingRegions(boolean allowCollapse, ProjectionAnnotationModel model,
			HashBiMap<Position, IFoldingRegion> positionsMap, Annotation[] deletions) {
		Map<ProjectionAnnotation, Position> additionsMap = new HashMap<ProjectionAnnotation, Position>();
		for (Iterator<IFoldingRegion> iterator = positionsMap.values().iterator(); iterator.hasNext();) {
			IFoldingRegion foldingRegion = iterator.next();
			addProjectionAnnotation(allowCollapse, foldingRegion, additionsMap);
		}
		if (deletions.length != 0 || additionsMap.size() != 0) {
			model.modifyAnnotations(deletions, additionsMap, new Annotation[] {});
		}
	}

	/**
	 * Add a projection annotation to the given map
	 * @param allowCollapse Whether the new annotation allows collapsing or not
	 * @param foldingRegion The region the new annotation will collapse
	 * @param additionsMap The map to add the new annotation to
	 */
	protected final void addProjectionAnnotation(boolean allowCollapse, IFoldingRegion foldingRegion,
			Map<ProjectionAnnotation, Position> additionsMap) {
		ProjectionAnnotation projectionAnnotation = createProjectionAnnotation(allowCollapse, foldingRegion);
		additionsMap.put(projectionAnnotation, foldingRegion.getPosition());
	}

	/**
	 * Creates a new projection annotation
	 * @param allowCollapse Whether or not collapsing is allowed with this annotation
	 * @param foldingRegion The region this annotation will collapse
	 * @return The projection annotation
	 */
	protected final ProjectionAnnotation createProjectionAnnotation(boolean allowCollapse, IFoldingRegion foldingRegion) {
		return new StyledProjectionAnnotation(allowCollapse, foldingRegion.getAlias());
	}

	/**
	 * Internal projection listener.
	 */
	public class ProjectionChangeListener implements IProjectionListener {
		private ProjectionViewer projectionViewer;

		/**
		 * Registers the listener with the viewer.
		 * 
		 * @param viewer the viewer to register a listener with
		 */
		public ProjectionChangeListener(ProjectionViewer viewer) {
			Assert.isLegal(viewer != null);
			projectionViewer = viewer;
			projectionViewer.addProjectionListener(this);
		}

		/**
		 * Disposes of this listener and removes the projection listener from the viewer.
		 */
		public final void dispose() {
			if (projectionViewer != null) {
				projectionViewer.removeProjectionListener(this);
				projectionViewer = null;
			}
		}

		/**
		 * {@inheritDoc}
		 */
		public final void projectionEnabled() {
			handleProjectionEnabled();
		}

		/**
		 * {@inheritDoc}
		 */
		public final void projectionDisabled() {
			handleProjectionDisabled();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void install(XtextEditor editor, ProjectionViewer viewer) {
		//must be implemented but is useful only for a "real" xtexteditor, not the embedded.
	}
}
