/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Jean-Baptiste VORON (LIP6) - Project Head / Initial contributor
 *   Clément DÉMOULINS (LIP6) - Project Manager
 *
 * Official contacts:
 *   coloane@lip6.fr
 *   http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.core.ui;

import fr.lip6.move.coloane.core.copypast.CopyAction;
import fr.lip6.move.coloane.core.copypast.CutAction;
import fr.lip6.move.coloane.core.copypast.PasteAction;
import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.model.GraphModel;
import fr.lip6.move.coloane.core.session.ISession;
import fr.lip6.move.coloane.core.session.SessionManager;
import fr.lip6.move.coloane.core.ui.actions.AlternateAction;
import fr.lip6.move.coloane.core.ui.actions.CurveAction;
import fr.lip6.move.coloane.core.ui.actions.NodeMoveAction;
import fr.lip6.move.coloane.core.ui.actions.RemoveInflexAction;
import fr.lip6.move.coloane.core.ui.actions.ResetAttributesLocationAction;
import fr.lip6.move.coloane.core.ui.checker.CheckerManager;
import fr.lip6.move.coloane.core.ui.checker.CommandStackListener;
import fr.lip6.move.coloane.core.ui.checker.MarkerManager;
import fr.lip6.move.coloane.core.ui.files.ModelHandler;
import fr.lip6.move.coloane.core.ui.files.ModelLoader;
import fr.lip6.move.coloane.core.ui.files.ModelWriter;
import fr.lip6.move.coloane.core.ui.palette.PaletteFactory;
import fr.lip6.move.coloane.core.ui.palette.PaletteToolListener;
import fr.lip6.move.coloane.core.ui.rulers.EditorRuler;
import fr.lip6.move.coloane.core.ui.rulers.EditorRulerProvider;
import fr.lip6.move.coloane.interfaces.model.IGraph;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.draw2d.ConnectionLayer;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayeredPane;
import org.eclipse.draw2d.LayeredPane;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.parts.ScrollableThumbnail;
import org.eclipse.draw2d.parts.Thumbnail;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.KeyStroke;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gef.SnapToGeometry;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.dnd.TemplateTransferDragSourceListener;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.rulers.RulerProvider;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.AlignmentAction;
import org.eclipse.gef.ui.actions.ToggleRulerVisibilityAction;
import org.eclipse.gef.ui.actions.ToggleSnapToGeometryAction;
import org.eclipse.gef.ui.actions.ZoomInAction;
import org.eclipse.gef.ui.actions.ZoomOutAction;
import org.eclipse.gef.ui.palette.FlyoutPaletteComposite.FlyoutPreferences;
import org.eclipse.gef.ui.palette.PaletteViewer;
import org.eclipse.gef.ui.palette.PaletteViewerProvider;
import org.eclipse.gef.ui.parts.ContentOutlinePage;
import org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette;
import org.eclipse.gef.ui.parts.GraphicalViewerKeyHandler;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.gef.ui.rulers.RulerComposite;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.SaveAsDialog;
import org.eclipse.ui.ide.FileStoreEditorInput;
import org.eclipse.ui.ide.IGotoMarker;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.part.PageBook;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * This class describes a <b>model editor</b>
 */
public class ColoaneEditor extends GraphicalEditorWithFlyoutPalette implements ITabbedPropertySheetPageContributor, IGotoMarker {

	/**
	 * Deals with the model outline
	 */
	class OutlinePage extends ContentOutlinePage implements IAdaptable {
		private PageBook pageBook;
		private Canvas overview;
		private Thumbnail thumbnail;
		private DisposeListener disposeListener;

		/**
		 * @param viewer viewer
		 */
		public OutlinePage(EditPartViewer viewer) {
			super(viewer);
		}

		/** {@inheritDoc} */
		@Override
		public void init(IPageSite pageSite) {
			super.init(pageSite);
			ActionRegistry registry = getActionRegistry();
			IActionBars bars = pageSite.getActionBars();

			String id = ActionFactory.UNDO.getId();
			bars.setGlobalActionHandler(id, registry.getAction(id));

			id = ActionFactory.REDO.getId();
			bars.setGlobalActionHandler(id, registry.getAction(id));

			id = ActionFactory.DELETE.getId();
			bars.setGlobalActionHandler(id, registry.getAction(id));

			bars.updateActionBars();
		}

		/** {@inheritDoc} */
		@Override
		public void createControl(Composite parent) {
			pageBook = new PageBook(parent, SWT.NONE);
			overview = new Canvas(pageBook, SWT.NONE);
			pageBook.showPage(overview);
			showPage();
		}

		/** {@inheritDoc} */
		@Override
		public void dispose() {
			unhookOutlineViewer();
			if (thumbnail != null) {
				thumbnail.deactivate();
				thumbnail = null;
			}
			super.dispose();
			ColoaneEditor.this.outlinePage = null;
			outlinePage = null;
		}

		/** {@inheritDoc} */
		@SuppressWarnings("rawtypes")
		public Object getAdapter(Class type) {
			if (type == ZoomManager.class) {
				return getGraphicalViewer().getProperty(
						ZoomManager.class.toString());
			}
			return null;
		}

		/** {@inheritDoc} */
		@Override
		public Control getControl() {
			return pageBook;
		}

		/**
		 * Initialisation de l'overview
		 */
		protected void initializeOverview() {
			LightweightSystem lws = new LightweightSystem(overview);
			RootEditPart rep = getGraphicalViewer().getRootEditPart();
			if (rep instanceof ScalableFreeformRootEditPart) {
				ScalableFreeformRootEditPart root = (ScalableFreeformRootEditPart) rep;
				thumbnail = new ScrollableThumbnail((Viewport) root.getFigure());
				thumbnail.setBorder(new MarginBorder(3));
				thumbnail.setSource(root
						.getLayer(LayerConstants.PRINTABLE_LAYERS));
				lws.setContents(thumbnail);
				disposeListener = new DisposeListener() {
					public void widgetDisposed(DisposeEvent e) {
						if (thumbnail != null) {
							thumbnail.deactivate();
							thumbnail = null;
						}
					}
				};
				getEditor().addDisposeListener(disposeListener);
			}
		}

		/**
		 * @param contents modèle de l'éditeur
		 */
		public void setContents(Object contents) {
			getViewer().setContents(contents);
		}

		/**
		 * Affichage de l'overview
		 */
		protected void showPage() {
			if (thumbnail == null) {
				initializeOverview();
			}
			pageBook.showPage(overview);
			thumbnail.setVisible(true);
		}

		/** TODO */
		protected void unhookOutlineViewer() {
			getSelectionSynchronizer().removeViewer(getViewer());
			if (disposeListener != null && getEditor() != null
					&& !getEditor().isDisposed()) {
				getEditor().removeDisposeListener(disposeListener);
			}
		}
	}

	private static final String CONTRIBUTOR_ID = "fr.lip6.move.coloane.properties.contributor"; //$NON-NLS-1$
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/**
	 * Focus listener.<br>
	 * This listener is used to detect editor switches.<br>
	 * In case of switch, it triggers some menus updates and recompute views dedicated to active editor.
	 */
	private static TabListener listener = null;

	/** The check stack manager */
	private CommandStackListener checkStackManager;

	/** Outline page */
	private OutlinePage outlinePage;

	/** The current graph */
	private IGraph graph;

	/** The tools palette */
	private PaletteRoot paletteRoot;
	private RulerComposite rulerComposite;

	/** Constructor */
	public ColoaneEditor() { }

	/** {@inheritDoc} */
	@Override
	protected final void initializeGraphicalViewer() {
		super.initializeGraphicalViewer();
		GraphicalViewer viewer = getGraphicalViewer();

		LOGGER.fine("Set the editor contents");  //$NON-NLS-1$
		viewer.setContents(getGraph()); // Set the contents !

		// Add the command stack event listener for listening to new added Command.
		checkStackManager = new CommandStackListener();
		this.getCommandStack().addCommandStackEventListener(checkStackManager);
	}

	/** {@inheritDoc} */
	@Override
	public final void dispose() {
		if (this.graph == null) {
			// TODO: ??
			return;
		} else {
			// Remove the stack listener dedicated to markers (problem view)
			this.getCommandStack().removeCommandStackEventListener(checkStackManager);
			IResource resource = (IResource) getEditorInput().getAdapter(IResource.class);
			// Remove all markers associated to the model that will be closed
			MarkerManager.deleteMarkers(resource);
			super.dispose();
		}
	}

	/** {@inheritDoc} */
	@Override
	protected final void configureGraphicalViewer() {
		List<String> zoomContributions;

		super.configureGraphicalViewer();
		ScrollingGraphicalViewer viewer = (ScrollingGraphicalViewer) getGraphicalViewer();

		// Define the rootEditPart :
		//   * Arcs are above the other elements
		//   * An image is placed on the background
		ScalableFreeformRootEditPart rootEditPart = new ScalableFreeformRootEditPart() {

			/** {@inheritDoc} */
			@Override
			protected LayeredPane createPrintableLayers() {
				FreeformLayeredPane layeredPane = new FreeformLayeredPane();
				layeredPane.add(new BackgroundLayer(), BackgroundLayer.BACKGROUND_LAYER);
				layeredPane.add(new ConnectionLayer(), CONNECTION_LAYER);
				layeredPane.add(new FreeformLayer(), PRIMARY_LAYER);
				return layeredPane;
			}
		};

		viewer.setEditPartFactory(new PartFactory());
		viewer.setRootEditPart(rootEditPart);
		viewer.setKeyHandler(new GraphicalViewerKeyHandler(viewer));

		ContextMenuProvider cmProvider = new ColoaneContextMenuProvider(viewer, getActionRegistry());

		getSite().setSelectionProvider(viewer);

		// add default menu items (such "run as", "debug as" ...)
		// getSite().registerContextMenu(cmProvider, viewer);
		viewer.setContextMenu(cmProvider);

		// Zoom
		ZoomManager manager = rootEditPart.getZoomManager();
		getActionRegistry().registerAction(new ZoomInAction(manager));
		getActionRegistry().registerAction(new ZoomOutAction(manager));

		// Rulers
		IAction showRulers = new ToggleRulerVisibilityAction(getGraphicalViewer());
		getActionRegistry().registerAction(showRulers);

		// SnapToGeometry
		IAction snapAction = new ToggleSnapToGeometryAction(getGraphicalViewer());
		getActionRegistry().registerAction(snapAction);

		// Available zoom scales.
		// Here, 1 = 100%
		double[] zoomLevels = new double[] {0.25, 0.5, 0.75, 1.0, 1.5, 2.0,
				2.5, 3.0, 4.0, 5.0, 10.0, 20.0 };
		manager.setZoomLevels(zoomLevels);

		// Some usefull zoom scales
		zoomContributions = new ArrayList<String>();
		zoomContributions.add(ZoomManager.FIT_ALL);
		zoomContributions.add(ZoomManager.FIT_HEIGHT);
		zoomContributions.add(ZoomManager.FIT_WIDTH);
		manager.setZoomLevelContributions(zoomContributions);

		// Shortcuts (move selected nodes)
		KeyHandler keyHandler = new KeyHandler();
		keyHandler.put(KeyStroke.getPressed(SWT.ARROW_UP, 0), getActionRegistry().getAction(NodeMoveAction.UP));
		keyHandler.put(KeyStroke.getPressed(SWT.ARROW_DOWN, 0), getActionRegistry().getAction(NodeMoveAction.DOWN));
		keyHandler.put(KeyStroke.getPressed(SWT.ARROW_LEFT, 0), getActionRegistry().getAction(NodeMoveAction.LEFT));
		keyHandler.put(KeyStroke.getPressed(SWT.ARROW_RIGHT, 0), getActionRegistry().getAction(NodeMoveAction.RIGHT));
		viewer.setKeyHandler(keyHandler);

		this.loadProperties();
	}

	/**
	 * Set editor properties (for this graph)
	 */
	protected final void loadProperties() {
		// Vertical ruler properties
		EditorRuler ruler = ((GraphModel) getGraph()).getEditorProperties().getRuler(PositionConstants.WEST);
		RulerProvider provider = null;
		if (ruler != null) { provider = new EditorRulerProvider(ruler);	}
		getGraphicalViewer().setProperty(RulerProvider.PROPERTY_VERTICAL_RULER, provider);

		// Horizontal ruler properties
		ruler = ((GraphModel) getGraph()).getEditorProperties().getRuler(PositionConstants.NORTH);
		provider = null;
		if (ruler != null) { provider = new EditorRulerProvider(ruler); }
		getGraphicalViewer().setProperty(RulerProvider.PROPERTY_HORIZONTAL_RULER, provider);

		// General properties
		getGraphicalViewer().setProperty(RulerProvider.PROPERTY_RULER_VISIBILITY, ((GraphModel) getGraph()).getEditorProperties().getRulersVisibility());
		getGraphicalViewer().setProperty(SnapToGeometry.PROPERTY_SNAP_ENABLED, ((GraphModel) getGraph()).getEditorProperties().getSnapState());
	}

	/**
	 * Save editor properties
	 */
	protected final void saveProperties() {
		((GraphModel) getGraph()).getEditorProperties().setRulersVisibility(((Boolean) getGraphicalViewer().getProperty(RulerProvider.PROPERTY_RULER_VISIBILITY)).booleanValue());
		((GraphModel) getGraph()).getEditorProperties().setSnapState(((Boolean) getGraphicalViewer().getProperty(SnapToGeometry.PROPERTY_SNAP_ENABLED)).booleanValue());
	}

	/**
	 * @return The graph associated with this editor
	 */
	public final IGraph getGraph() {
		return graph;
	}

	/**
	 * @return The path of the current graph displayed by this editor
	 */
	public final IPath getCurrentPath() {
		IFile file = ((IFileEditorInput) getEditorInput()).getFile();
		String projectName = file.getProject().getName();
		IPath path = new Path(projectName);
		return path.append(file.getProjectRelativePath());
	}

	/**
	 * Put a particular GEF command on the editor stack
	 * @param command The command that will be executed
	 */
	public final void executeCommand(Command command) {
		super.getCommandStack().execute(command);
	}

	/**
	 * Set editor contents.<br>
	 * Contents are read from a file (previously created by the creation wizard).
	 * @param input All information about the model
	 */
	@Override
	protected final void setInput(IEditorInput input) {
		super.setInput(input);

		// Default id
		String id = input.getName();

		// Fetch the XML file and set the name of the editor
		if (input instanceof IFileEditorInput) {
			final IFile file = ((IFileEditorInput) input).getFile();
			file.getWorkspace().addResourceChangeListener(new IResourceChangeListener() {
				public void resourceChanged(IResourceChangeEvent event) {
					if ((event.getType() & IResourceChangeEvent.POST_CHANGE) == IResourceChangeEvent.POST_CHANGE) {
						IResourceDelta delta = event.getDelta().findMember(file.getFullPath());
						if (delta != null && delta.getKind() == IResourceDelta.REMOVED) {
							LOGGER.info("The editor on \"" + delta.getFullPath() + "\" will be closed because the resource has been deleted."); //$NON-NLS-1$ //$NON-NLS-2$
							Display.getDefault().asyncExec(new Runnable() {
								public void run() {
									ColoaneEditor.this.getSite().getPage().closeEditor(ColoaneEditor.this, false);
								}
							});
						}
					}
				}
			});

			// Build the model object from its XML representation
			this.graph = ModelLoader.loadFromXML(file, new ModelHandler()).getGraph();
			id = file.getFullPath().toString();
		} else if (input instanceof FileStoreEditorInput) {
			id = ((FileStoreEditorInput) input).getURI().toString();
			this.graph = ModelLoader.loadGraphFromXML(((FileStoreEditorInput) input).getURI());
		}

		setPartName(input.getName());

		// Define the session id
		setPartProperty("session.id", id); //$NON-NLS-1$

		// If the loading fails... Display a message and quit
		if (this.graph == null) {
			Coloane.showErrorMsg("Cannot display the model..."); //$NON-NLS-1$
			setEditDomain(new DefaultEditDomain(this));
			Display.getDefault().asyncExec(new Runnable() {
				public void run() {
					getSite().getPage().closeEditor(ColoaneEditor.this, false);
				}
			});
			return;
		}

		// Build a new session
		try {
			ISession session = SessionManager.getInstance().createSession(id, this.graph);
			IResource resource = ResourcesPlugin.getWorkspace().getRoot().findMember(new Path(session.getSessionId()));
			if (resource != null) {
				CheckerManager.getInstance().checkAll(session.getChecker(), resource, this.graph);
			}
		} catch (ColoaneException ce) {
			LOGGER.warning("Cannot create a session for this model"); //$NON-NLS-1$
			LOGGER.warning(ce.getLogMessage());
			Coloane.showErrorMsg("Cannot create the session : " + ce.getMessage()); //$NON-NLS-1$
		}

		// Set the editor.
		// This operation cannot be performed earlier since the model formalism is not knew.
		// This information is needed to determine the palette to associate with the editor.
		// Do not move this instruction !
		setEditDomain(new DefaultEditDomain(this));

		// If the outline page has been built, display the graph inside
		if (outlinePage != null) {
			outlinePage.setContents(getGraph());
		}
	}

	/** {@inheritDoc} */
	@Override
	public final void doSave(IProgressMonitor monitor) {
		// Save editor properties
		this.saveProperties();

		// Fetch the (real) file and produce the XML representation of the current graph
		IFile file = ((IFileEditorInput) getEditorInput()).getFile();
		String xmlString = ModelWriter.translateToXML(graph);

		try {
			// Produce an input stream and write it to the file
			InputStream inputStream = new ByteArrayInputStream(xmlString.getBytes("UTF-8")); //$NON-NLS-1$
			file.setContents(inputStream, true, false, monitor);
			file.setCharset("UTF-8", monitor); //$NON-NLS-1$

			// As the model is now saved, we consider it as clean... (sync with its file)
			if (((GraphModel) graph).isDirty()) {
				((GraphModel) graph).setDirtyState(false);
			}
		} catch (CoreException e) {
			LOGGER.warning("Error while writing the graph to the file " + file.getName()); //$NON-NLS-1$
		} catch (UnsupportedEncodingException e) {
			LOGGER.warning("Unsupported charset used by the graph..."); //$NON-NLS-1$
		}

		// Tells the command stack that the model has been saved
		getCommandStack().markSaveLocation();
	}

	/** {@inheritDoc} */
	@Override
	public final void doSaveAs() {
		// Save editor properties
		this.saveProperties();

		// Open a dialog box
		Shell shell = getSite().getWorkbenchWindow().getShell();
		SaveAsDialog dialog = new SaveAsDialog(shell);
		dialog.setOriginalFile(((IFileEditorInput) getEditorInput()).getFile());
		dialog.open();

		// Fetch the dialog box result
		IPath path = dialog.getResult();
		if (path != null) {
			// Try to save the editor's contents under a different file name
			final IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(path);

			try {
				new ProgressMonitorDialog(shell).run(false, false, new WorkspaceModifyOperation() {
					@Override
					public void execute(final IProgressMonitor monitor) {
						// Write the graph to its XML representation
						String xmlString = ModelWriter.translateToXML(graph);
						InputStream inputS = new ByteArrayInputStream(xmlString.getBytes());
						try {
							// If the file already exists, we replace its contents
							if (file.exists()) {
								file.setContents(inputS, true, false, monitor);
							// Otherwise, we create the new file
							} else {
								file.create(inputS, true, monitor);
							}
						} catch (CoreException ce) {
							LOGGER.warning("Error while writing the graph to the file " + file.getName()); //$NON-NLS-1$
							ce.printStackTrace();
						}
					}
				});

				// Those lines are triggering a strange behavior.
				// setInput(new FileEditorInput(file));
				// getCommandStack().markSaveLocation();
				firePropertyChange(PROP_TITLE);

			} catch (InterruptedException ie) {
				// Should not happen, since the monitor dialog cannot be canceled
				ie.printStackTrace();
			} catch (InvocationTargetException ite) {
				ite.printStackTrace();
			}
		}
	}

	/** {@inheritDoc} */
	@Override
	public final void createPartControl(Composite parent) {
		if (listener == null) {
			LOGGER.config("Set the focus listener"); //$NON-NLS-1$
			listener = new TabListener();
			getSite().getPage().addPartListener(listener);
		}
		super.createPartControl(parent);
	}

	/** {@inheritDoc} */
	@Override
	public final boolean isSaveAsAllowed() {
		return true;
	}

	/** {@inheritDoc} */
	@Override
	public final void commandStackChanged(final EventObject event) {
		super.commandStackChanged(event);
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				firePropertyChange(IEditorPart.PROP_DIRTY);
			}
		});
	}

	/** {@inheritDoc} */
	@Override
	protected final PaletteViewerProvider createPaletteViewerProvider() {
		return new PaletteViewerProvider(getEditDomain()) {
			@Override
			protected void configurePaletteViewer(PaletteViewer viewer) {
				super.configurePaletteViewer(viewer);
				viewer.addDragSourceListener(new TemplateTransferDragSourceListener(viewer));
				viewer.addPaletteListener(new PaletteToolListener());
			}
		};
	}

	/** {@inheritDoc} */
	@Override
	protected final void createGraphicalViewer(Composite parent) {
		rulerComposite = new RulerComposite(parent, SWT.NONE);
		super.createGraphicalViewer(rulerComposite);
		rulerComposite.setGraphicalViewer((ScrollingGraphicalViewer) getGraphicalViewer());
	}

	/** {@inheritDoc} */
	@Override
	protected final Control getGraphicalControl() {
		return rulerComposite;
	}

	/** {@inheritDoc} */
	@SuppressWarnings("rawtypes")
	@Override
	public final Object getAdapter(Class type) {
		if (type == IContentOutlinePage.class) {
			outlinePage = new OutlinePage(getGraphicalViewer());
			return outlinePage;

		// Zoom Manager
		} else if (type == ZoomManager.class) {
			return ((ScalableFreeformRootEditPart) getGraphicalViewer().getRootEditPart()).getZoomManager();

		// Properties Page
		} else if (type == IPropertySheetPage.class) {
			return new TabbedPropertySheetPage(this);
		}

		return super.getAdapter(type);
	}

	/**
	 * @return The editor
	 */
	protected final FigureCanvas getEditor() {
		return (FigureCanvas) getGraphicalViewer().getControl();
	}

	/**
	 * Build the tools palette
	 * @return The palette
	 */
	@Override
	protected final PaletteRoot getPaletteRoot() {
		// Should never be null
		if (graph == null) {
			LOGGER.warning("Cannot build the palette. The associated graph is null"); //$NON-NLS-1$
			return null;
		}

		// Build the entire palette
		paletteRoot = PaletteFactory.createPalette(graph.getFormalism());

		// If the building process has failed
		if (paletteRoot == null) {
			LOGGER.warning("Something went wring during the palette building process"); //$NON-NLS-1$
		}

		return paletteRoot;
	}

	/**
	 * @return The palette preferences
	 */
	@Override
	protected final FlyoutPreferences getPalettePreferences() {
		return PaletteFactory.createPalettePreferences();
	}

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	protected final void createActions() {
		super.createActions();

		ActionRegistry registry = getActionRegistry();
		IAction action;

		// CUT
		action = new CutAction((IWorkbenchPart) this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		// COPY
		action = new CopyAction((IWorkbenchPart) this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		// PASTE
		action = new PasteAction((IWorkbenchPart) this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		// MOVE ACTIONS
		action = new NodeMoveAction((IWorkbenchPart) this, NodeMoveAction.UP);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());
		action = new NodeMoveAction((IWorkbenchPart) this, NodeMoveAction.DOWN);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());
		action = new NodeMoveAction((IWorkbenchPart) this, NodeMoveAction.LEFT);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());
		action = new NodeMoveAction((IWorkbenchPart) this, NodeMoveAction.RIGHT);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		// CURVE
        action = new CurveAction((IWorkbenchPart) this);
        registry.registerAction(action);
        getSelectionActions().add(action.getId());

        // ALTERNATE FIGURE
        action = new AlternateAction((IWorkbenchPart) this);
        registry.registerAction(action);
        getSelectionActions().add(action.getId());

        // DELETE INFLEX POINT
        action = new RemoveInflexAction((IWorkbenchPart) this);
        registry.registerAction(action);
        getSelectionActions().add(action.getId());

        // RESET ATTRIBUTES LOCATIONS
        action = new ResetAttributesLocationAction((IWorkbenchPart) this);
        registry.registerAction(action);
        getSelectionActions().add(action.getId());

        // ALIGNMENT ACTIONS
		action = new AlignmentAction((IWorkbenchPart) this, PositionConstants.LEFT);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());
		action = new AlignmentAction((IWorkbenchPart) this, PositionConstants.RIGHT);
		registry.registerAction(action);
		getSelectionActions().add(((IAction) (action)).getId());
		action = new AlignmentAction((IWorkbenchPart) this, PositionConstants.TOP);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());
		action = new AlignmentAction((IWorkbenchPart) this, PositionConstants.BOTTOM);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());
		action = new AlignmentAction((IWorkbenchPart) this, PositionConstants.CENTER);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());
		action = new AlignmentAction((IWorkbenchPart) this, PositionConstants.MIDDLE);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());
	}

	/** {@inheritDoc} */
	public final String getContributorId() {
		return CONTRIBUTOR_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	public final void gotoMarker(IMarker marker) {
		MarkerManager.getInstance().doGotoMarker(marker);
	}
}
