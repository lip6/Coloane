package fr.lip6.move.coloane.core.ui;

import fr.lip6.move.coloane.core.copypast.CopyAction;
import fr.lip6.move.coloane.core.copypast.CutAction;
import fr.lip6.move.coloane.core.copypast.PasteAction;
import fr.lip6.move.coloane.core.main.Coloane;
import fr.lip6.move.coloane.core.model.GraphModel;
import fr.lip6.move.coloane.core.ui.actions.NodeMoveAction;
import fr.lip6.move.coloane.core.ui.files.ModelLoader;
import fr.lip6.move.coloane.core.ui.files.ModelWriter;
import fr.lip6.move.coloane.core.ui.menus.UpdatePlatformMenu;
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
import java.util.logging.Logger;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.draw2d.FigureCanvas;
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
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.SaveAsDialog;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.part.PageBook;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

public class ColoaneEditor extends GraphicalEditorWithFlyoutPalette implements ITabbedPropertySheetPageContributor {

	class OutlinePage extends ContentOutlinePage implements IAdaptable {
		private PageBook pageBook;
		private Canvas overview;
		private Thumbnail thumbnail;
		private DisposeListener disposeListener;

		public OutlinePage(EditPartViewer viewer) {
			super(viewer);
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see org.eclipse.ui.part.Page#init(org.eclipse.ui.part.IPageSite)
		 */
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

		/*
		 * (non-Javadoc)
		 *
		 * @see org.eclipse.gef.ui.parts.ContentOutlinePage#createControl(org.eclipse.swt.widgets.Composite)
		 */
		@Override
		public void createControl(Composite parent) {
			pageBook = new PageBook(parent, SWT.NONE);
			overview = new Canvas(pageBook, SWT.NONE);
			pageBook.showPage(overview);
			showPage();
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see org.eclipse.ui.part.Page#dispose()
		 */
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

		/*
		 * (non-Javadoc)
		 *
		 * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
		 */
		@SuppressWarnings("unchecked")
		public Object getAdapter(Class type) {
			if (type == ZoomManager.class) {
				return getGraphicalViewer().getProperty(
						ZoomManager.class.toString());
			}
			return null;
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see org.eclipse.gef.ui.parts.ContentOutlinePage#getControl()
		 */
		@Override
		public Control getControl() {
			return pageBook;
		}

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

		public void setContents(Object contents) {
			getViewer().setContents(contents);
		}

		protected void showPage() {
			if (thumbnail == null) {
				initializeOverview();
			}
			pageBook.showPage(overview);
			thumbnail.setVisible(true);
		}

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

	/** La page d'apercu */
	private OutlinePage outlinePage;

	/** Le graphe */
	private IGraph graph;

	/** La palette */
	private PaletteRoot paletteRoot;
	private RulerComposite rulerComposite;

	/** Le listener de focus */
	private static TabListener listener = null;

	/** Constructeur de l'editeur */
	public ColoaneEditor() {
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette#initializeGraphicalViewer()
	 */
	@Override
	protected final void initializeGraphicalViewer() {
		super.initializeGraphicalViewer();
		GraphicalViewer viewer = getGraphicalViewer();
		viewer.setContents(getGraph()); // Mise en place du contenu dans l'editeur

		Coloane.getParent().getDisplay().asyncExec(new UpdatePlatformMenu(Coloane.getParam("CONNECT_ITEM"), false)); //$NON-NLS-1$
		Coloane.getParent().getDisplay().asyncExec(new UpdatePlatformMenu(Coloane.getParam("DISCONNECT_ITEM"), false)); //$NON-NLS-1$
	}

	/** {@inheritDoc} */
	@Override
	protected final void configureGraphicalViewer() {
		ArrayList<String> zoomContributions;

		super.configureGraphicalViewer();
		ScrollingGraphicalViewer viewer = (ScrollingGraphicalViewer) getGraphicalViewer();

		ScalableFreeformRootEditPart rootEditPart = new ScalableFreeformRootEditPart();

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

		// Regles
		IAction showRulers = new ToggleRulerVisibilityAction(getGraphicalViewer());
		getActionRegistry().registerAction(showRulers);

		// SnapToGeometry
		IAction snapAction = new ToggleSnapToGeometryAction(getGraphicalViewer());
		getActionRegistry().registerAction(snapAction);

		// Liste des zooms possibles 1 = 100%
		double[] zoomLevels = new double[] {0.25, 0.5, 0.75, 1.0, 1.5, 2.0,
				2.5, 3.0, 4.0, 5.0, 10.0, 20.0 };
		manager.setZoomLevels(zoomLevels);

		// On ajoute certains zooms predefinis
		zoomContributions = new ArrayList<String>();
		zoomContributions.add(ZoomManager.FIT_ALL);
		zoomContributions.add(ZoomManager.FIT_HEIGHT);
		zoomContributions.add(ZoomManager.FIT_WIDTH);
		manager.setZoomLevelContributions(zoomContributions);

		// On ajoute des raccourcis clavier
		KeyHandler keyHandler = new KeyHandler();
		keyHandler.put(KeyStroke.getPressed(SWT.ARROW_UP, 0), getActionRegistry().getAction(NodeMoveAction.UP));
		keyHandler.put(KeyStroke.getPressed(SWT.ARROW_DOWN, 0), getActionRegistry().getAction(NodeMoveAction.DOWN));
		keyHandler.put(KeyStroke.getPressed(SWT.ARROW_LEFT, 0), getActionRegistry().getAction(NodeMoveAction.LEFT));
		keyHandler.put(KeyStroke.getPressed(SWT.ARROW_RIGHT, 0), getActionRegistry().getAction(NodeMoveAction.RIGHT));
		viewer.setKeyHandler(keyHandler);

		this.loadProperties();
	}

	/**
	 * Chargement des propriétés de l'éditeur (pour ce graphe)
	 */
	protected final void loadProperties() {
		// Propriétés pour la règle verticale
		EditorRuler ruler = ((GraphModel) getGraph()).getEditorProperties().getRuler(PositionConstants.WEST);
		RulerProvider provider = null;
		if (ruler != null) { provider = new EditorRulerProvider(ruler);	}
		getGraphicalViewer().setProperty(RulerProvider.PROPERTY_VERTICAL_RULER, provider);

		// Propriétés pour la règle horizontale
		ruler = ((GraphModel) getGraph()).getEditorProperties().getRuler(PositionConstants.NORTH);
		provider = null;
		if (ruler != null) { provider = new EditorRulerProvider(ruler); }
		getGraphicalViewer().setProperty(RulerProvider.PROPERTY_HORIZONTAL_RULER, provider);

		// Les autres propriétés
		getGraphicalViewer().setProperty(RulerProvider.PROPERTY_RULER_VISIBILITY,  new Boolean(((GraphModel) getGraph()).getEditorProperties().getRulersVisibility()));
		getGraphicalViewer().setProperty(SnapToGeometry.PROPERTY_SNAP_ENABLED,  new Boolean(((GraphModel) getGraph()).getEditorProperties().getSnapState()));
	}

	/**
	 * Sauvegarde des propriétés de l'éditeur (pour ce graphe)
	 */
	protected final void saveProperties() {
		((GraphModel) getGraph()).getEditorProperties().setRulersVisibility(((Boolean) getGraphicalViewer().getProperty(RulerProvider.PROPERTY_RULER_VISIBILITY)).booleanValue());
		((GraphModel) getGraph()).getEditorProperties().setSnapState(((Boolean) getGraphicalViewer().getProperty(SnapToGeometry.PROPERTY_SNAP_ENABLED)).booleanValue());
	}

	/**
	 * @return le graph attache a l'editeur
	 */
	public final IGraph getGraph() {
		return graph;
	}

	/**
	 * Retourne le path du modele en cours d'edition
	 * @return Le chemin du modele en cours dans l'editeur
	 */
	public final IPath getCurrentPath() {
		IFile file = ((IFileEditorInput) getEditorInput()).getFile();
		String projectName = file.getProject().getName();
		IPath path = new Path(projectName);
		return path.append(file.getProjectRelativePath());
	}

	/**
	 * Determine le contenu de l'editeur. Ce contenu est lu depuis un fichier.
	 * Ce fichier a bien sur ete cree par l'assistant
	 * @param input Toutes les informations concernant le modele
	 */
	@Override
	protected final void setInput(IEditorInput input) {
		super.setInput(input);

		// Recuperation du fichier XML decrivant un modele
		IFile file = ((IFileEditorInput) input).getFile();
		setPartName(file.getName());

		// Construction d'un modele en memoire a partir de se representation en XML
		graph = ModelLoader.loadFromXML(file);
		if (graph == null) {
			Coloane.showErrorMsg("Cannot display the model..."); //$NON-NLS-1$
			setEditDomain(new DefaultEditDomain(this));
			getSite().getPage().closeEditor(this, false);
			return;
		}

		// Mise en place de l'editeur
		// On est oblige d'attendre le formalisme pour creer le domaine d'edition
		// En effet, le formalisme determine la palette qui sera affichee
		setEditDomain(new DefaultEditDomain(this));

		// Si la fenetre d'apercu existe... On affiche la miniature
		if (outlinePage != null) {
			outlinePage.setContents(getGraph());
		}
	}

	/**
	 * Sauvegarde d'un fichier
	 */
	@Override
	public final void doSave(IProgressMonitor monitor) {
		// Sauvegarde des propriétés de l'éditeur
		this.saveProperties();

		IFile file = ((IFileEditorInput) getEditorInput()).getFile();
		// Traduction du modele au format xml
		String xmlString = ModelWriter.translateToXML(graph);

		try {
			// Creation de l'input stream a partir d'une chaine de caractere
			InputStream inputS = new ByteArrayInputStream(xmlString.getBytes("UTF-8")); //$NON-NLS-1$
			// Ecriture du fichier de sauvegarder a partir du l'input stream
			file.setContents(inputS, true, false, monitor);
			file.setCharset("UTF-8", monitor); //$NON-NLS-1$
		} catch (CoreException e) {
			LOGGER.warning("Erreur lors de la sauvegarde du modele"); //$NON-NLS-1$
		} catch (UnsupportedEncodingException e) {
			LOGGER.warning("Erreur lors de la sauvegarde du modele (charset)"); //$NON-NLS-1$
		}

		getCommandStack().markSaveLocation();
	}

	/**
	 * Sauvegarde du modele dans un autre fichier
	 */
	@Override
	public final void doSaveAs() {
		// Sauvegarde des propriétés de l'éditeur
		this.saveProperties();

		// Ouvre une boite de dialogue
		Shell shell = getSite().getWorkbenchWindow().getShell();
		SaveAsDialog dialog = new SaveAsDialog(shell);
		dialog.setOriginalFile(((IFileEditorInput) getEditorInput()).getFile());
		dialog.open();

		// Recupere le resultat de la boite de dialogue
		IPath path = dialog.getResult();

		if (path != null) {
			// try to save the editor's contents under a different file name
			final IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
			try {
				new ProgressMonitorDialog(shell).run(false, false, new WorkspaceModifyOperation() {
					@Override
					public void execute(final IProgressMonitor monitor) {
						// Recuperation du modele generique
						String xmlString = ModelWriter.translateToXML(graph);
						InputStream inputS = new ByteArrayInputStream(xmlString.getBytes());
						try {
							// Si le fichier existe alors on l'ecrase
							// sinon on en cree un nouveau
							if (file.exists()) {
								file.setContents(inputS, true, false, monitor);
							} else {
								file.create(inputS, true, monitor);
							}
						} catch (CoreException ce) {
							ce.printStackTrace();
						}
					}
				});

				// Suppression de ces lignes pour corriger le bug du saveAS
				// On installe le nouveau contenu dans l'editeur
				// setInput(new FileEditorInput(file));
				// getCommandStack().markSaveLocation();
				firePropertyChange(PROP_TITLE);

			} catch (InterruptedException ie) {
				// Should not happen, since the monitor dialog is not cancelable
				ie.printStackTrace();
			} catch (InvocationTargetException ite) {
				ite.printStackTrace();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette#createPartControl(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public final void createPartControl(Composite parent) {
		if (listener == null) {
			LOGGER.config("Mise en place de l'ecouteur de focus"); //$NON-NLS-1$
			listener = new TabListener();
			getSite().getPage().addPartListener(listener);
		}
		super.createPartControl(parent);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.gef.ui.parts.GraphicalEditor#dispose()
	 */
	@Override
	public final void dispose() {
		if (this.graph == null) {
			Coloane.showErrorMsg(Messages.ColoaneEditor_1);
			return;
		} else {
			super.dispose();
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.gef.ui.parts.GraphicalEditor#isSaveAsAllowed()
	 */
	@Override
	public final boolean isSaveAsAllowed() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.gef.ui.parts.GraphicalEditor#commandStackChanged(java.util.EventObject)
	 */
	@Override
	public final void commandStackChanged(EventObject event) {
		firePropertyChange(IEditorPart.PROP_DIRTY);
		super.commandStackChanged(event);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.gef.ui.parts.GraphicalEditorWithFlyoutPalette#createPaletteViewerProvider()
	 */
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

	@Override
	protected final void createGraphicalViewer(Composite parent) {
		rulerComposite = new RulerComposite(parent, SWT.NONE);
		super.createGraphicalViewer(rulerComposite);
		rulerComposite.setGraphicalViewer((ScrollingGraphicalViewer) getGraphicalViewer());
	}

	@Override
	protected final Control getGraphicalControl() {
		return rulerComposite;
	}

	/** {@inheritDoc} */
	@SuppressWarnings("unchecked")
	@Override
	public final Object getAdapter(Class type) {
		if (type == IContentOutlinePage.class) {
			outlinePage = new OutlinePage(getGraphicalViewer());
			return outlinePage;

		// On definit le manager de Zoom
		} else if (type == ZoomManager.class) {
			return ((ScalableFreeformRootEditPart) getGraphicalViewer()
					.getRootEditPart()).getZoomManager();

		// Fenêtre de propriété avec onglets
		} else if (type == IPropertySheetPage.class) {
			return new TabbedPropertySheetPage(this);
		}


		// Dans tous les autres cas
		return super.getAdapter(type);
	}

	/**
	 * Retourne un handle sur l'editeur
	 */
	protected final FigureCanvas getEditor() {
		return (FigureCanvas) getGraphicalViewer().getControl();
	}

	/**
	 * Creation de la palette d'outils
	 *
	 * @return PaletteRoot Le pere de la palette
	 */
	@Override
	protected final PaletteRoot getPaletteRoot() {
		// Logiquementle modele n'est jamais nul... Mais on est jamais trop prudent
		if (graph == null) {
			LOGGER.warning("Impossible de creer la palette d'outils associee au formalisme"); //$NON-NLS-1$
			return null;
		}

		// Calcul et Creation de la palette adequate
		paletteRoot = PaletteFactory.createPalette(graph.getFormalism());

		// Si la creation dela palette a echouee
		if (paletteRoot == null) {
			LOGGER.warning("Impossible de creer la palette d'outils associee au formalisme"); //$NON-NLS-1$
		}

		return paletteRoot;
	}

	/**
	 * Retourne les preferences de la palette
	 *
	 * @return FlyoutPreferences
	 */
	@Override
	protected final FlyoutPreferences getPalettePreferences() {
		return PaletteFactory.createPalettePreferences();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.eclipse.gef.ui.parts.GraphicalEditor#createActions()
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected final void createActions() {
		super.createActions();

		ActionRegistry registry = getActionRegistry();
		IAction action;

		// Création de l'action pour couper
		action = new CutAction((IWorkbenchPart) this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		// Création de l'action pour copier
		action = new CopyAction((IWorkbenchPart) this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		// Création de l'action pour coller
		action = new PasteAction((IWorkbenchPart) this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		// Création des action pour le déplacement
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

	public final String getContributorId() {
		return CONTRIBUTOR_ID;
	}
}
