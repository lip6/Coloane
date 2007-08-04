package fr.lip6.move.coloane.ui.panels;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.ITextOperationTarget;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

import fr.lip6.move.coloane.main.Coloane;

/**
 * Cette classe impl�mente la f�tre de l'historique
 * 
 * @author Dzung NGUYEN
 * @author Jean-Baptiste VORON
 * 
 */
public class HistoryView extends ViewPart {

	/** Point d'acc�s pour les autres classes */
	public static HistoryView instance;

	/** Visionneuse */
	private TextViewer viewer = null;

	/** Document */
	private Document document = null;

	/** Action copier */
	private Action copyAction = null;

	/** Action Trouver */
	private Action findAction = null;

	/** Action Tout selectionner */
	private Action selectAllAction = null;

	/**
	 * Cette m�thode permet de creer la visionneuse et de l'initaliser
	 * 
	 * @param parent Interface Composite, pour la cr�ation de la visionneuse et des controls
	 * 
	 * @see org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets.Composite)
	 */
	public void createPartControl(Composite parent) {
		viewer = new TextViewer(parent, SWT.MULTI | SWT.V_SCROLL);

		// Readonly window
		viewer.setEditable(false);
		document = new Document();
		viewer.setDocument(document);
		viewer.setInput(getViewSite());
		createActions();
		hookContextMenu();
		contributeToActionsBar();

		// Set static
		instance = this;
		
	}

	/**
	 * Donner une valeur � Focus
	 * 
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	public void setFocus() {
		viewer.getControl().setFocus();

	}

	/**
	 * Creer le menu contextuel pour la fenetre de l'historique, contenant deux actions: Copy, Find
	 */
	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu"); //$NON-NLS-1$
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				HistoryView.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);
	}

	/**
	 * Creer des actions
	 * 
	 */
	private void createActions() {
		// Copy
		copyAction = new Action() {
			public void run() {
				HistoryView.this.viewer.doOperation(ITextOperationTarget.COPY);
			};

			public boolean isEnabled() {
				return HistoryView.this.viewer
						.canDoOperation(ITextOperationTarget.COPY);
			}
		};
		copyAction.setText(Coloane.traduction.getString("ui.panels.HistoryView.1")); //$NON-NLS-1$
		copyAction.setToolTipText(Coloane.traduction.getString("ui.panels.HistoryView.2")); //$NON-NLS-1$
		copyAction.setImageDescriptor(PlatformUI.getWorkbench()
				.getSharedImages().getImageDescriptor(
						ISharedImages.IMG_TOOL_COPY));
		
		// Select all
		selectAllAction = new Action() {
			public void run() {
				HistoryView.this.viewer
						.doOperation(ITextOperationTarget.SELECT_ALL);
			}
		};
		selectAllAction.setText(Coloane.traduction.getString("ui.panels.HistoryView.3")); //$NON-NLS-1$
		selectAllAction.setToolTipText(Coloane.traduction.getString("ui.panels.HistoryView.4")); //$NON-NLS-1$
		selectAllAction.setImageDescriptor(PlatformUI.getWorkbench()
				.getSharedImages().getImageDescriptor(
						ISharedImages.IMG_OBJ_FILE));
		// Find action
		findAction = new Action() {
			public void run() {
			}
		};
		findAction.setText(Coloane.traduction.getString("ui.panels.HistoryView.5")); //$NON-NLS-1$
		findAction.setToolTipText(Coloane.traduction.getString("ui.panels.HistoryView.6")); //$NON-NLS-1$
	}

	/**
	 * Creer des actions et les ajouter au menu
     *  
	 * @param manager
	 *            Menu contextuel
	 */
	private void fillContextMenu(IMenuManager manager) {
		manager.add(copyAction);
		manager.add(selectAllAction);
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
		manager.add(findAction);
	}

	/**
	 * Ajouter des actions au local Pulldown menu
	 * 
	 * @param manager
	 *            Local pulldown menu
	 */
	private void fillLocalPullDown(IMenuManager manager) {
		manager.add(copyAction);
		manager.add(selectAllAction);
		manager.add(new Separator());
		manager.add(findAction);
	}

	/**
	 * Ajouter des actions � la barre d'outils
	 * 
	 * @param manager
	 *            Toolbar
	 */
	private void fillLocalToolBar(IToolBarManager manager) {
		manager.removeAll();
		manager.add(copyAction);
		manager.add(selectAllAction);
		manager.add(new Separator());
	}

	/** 
	 * Creer des actions
	 */
	private void contributeToActionsBar() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	/**
	 * Inserer une nouvelle ligne � la fenetre de l'historique
	 * 
	 * @param text
	 *            Texte � ins�rer
	 */
	public void addLine(String text) {
		if (document != null) {

			try {
				document.replace(document.getLength(), 0, text + "\n"); //$NON-NLS-1$
 
			} catch (Exception e) { ;
				/*
				 * BadLocationException this exception never raised because we
				 * call function with constants
				 */
			}
		}
	}

	/**
	 * Ins�rer du texte dans la fen�tre de l'historique.
	 * 
	 * @param text
	 *            Texte � ins�rer
	 */
	public void addText(String text) {
		if (document != null) {

			try {
				document.replace(document.getLength(), 0, text);

			} catch (Exception be) { ;
				/*
				 * BadLocationException this exception never raised because we
				 * call function with constants
				 */
			}
		}
	}

}
