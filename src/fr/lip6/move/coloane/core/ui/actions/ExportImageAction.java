package fr.lip6.move.coloane.core.ui.actions;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.eclipse.draw2d.FreeformFigure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.SWTGraphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.editparts.LayerManager;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PlatformUI;

/**
 * Action qui permet l'enregistrement d'un modele courant en image.<br>
 * Les formats acceptés sont les suivants :
 * <ul>
 * 	<li>PNG</li>
 * 	<li>GIF</li>
 * 	<li>JPG</li>
 * 	<li>BMP</li>
 * </ul>
 *
 * @author Jean-Baptiste Voron
 * @author Alexandre Hamez
 */
public class ExportImageAction implements IWorkbenchWindowActionDelegate  {
	private IWorkbenchWindow window;

	/**
	 * Creates a new <code>ExportImageAction</code>.
	 * @param w The workbench part (must not be <code>null</code>)
	 */
	public final void init(IWorkbenchWindow w) {
		this.window = w;
	}

	/** {@inheritDoc} */
	public final void run() {
		Shell shell = PlatformUI.getWorkbench().getDisplay().getActiveShell();

		FileDialog dialog = new FileDialog(shell, SWT.SAVE);
		dialog.setText(Messages.ExportImageAction_0);
		dialog.setFilterExtensions(getImageFilterExtensions());
		dialog.setFilterNames(getFilterNames());
		String filePath = dialog.open();

		if (filePath != null) {
			File file = new File(filePath);
			if (!file.exists()) {
				save(filePath, SWT.IMAGE_JPEG);
			}
		}
	}

	/**
	 * Sauvegarde effective de l'image dans un fichier
	 * @param filePath Le chemin du fichier destination
	 * @param fileFormat Le format de l'image
	 * @return <code>True</code> si la sauvegarde s'est correctement déroulée, <code>False</code> sinon
	 */
	private boolean save(String filePath, int fileFormat) {
		byte[] data = createImage(fileFormat);
		try {
			FileOutputStream fileStream = new FileOutputStream(filePath);
			fileStream.write(data);
			fileStream.close();
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Creation de l'image en fonction du format de sortie
	 * @param format Le format de l'image
	 * @return La tableau d'octets représentant l'image
	 */
	private byte[] createImage(int format) {
		GraphicalViewer viewer = (GraphicalViewer) window.getPartService().getActivePart().getAdapter(GraphicalViewer.class);
		LayerManager layers = (LayerManager) viewer.getEditPartRegistry().get(LayerManager.ID);
		IFigure figure = layers.getLayer(LayerConstants.PRINTABLE_LAYERS);
		Rectangle bounds = getBounds(figure);
		ByteArrayOutputStream result = new ByteArrayOutputStream();
		Image image = null;
		GC gc = null;
		Graphics graph = null;
		image = new Image(null, bounds.width, bounds.height);
		gc = new GC(image);
		graph = new SWTGraphics(gc);
		graph.translate(bounds.x * (-1), bounds.y * (-1));
		figure.paint(graph);
		ImageLoader imageLoader = new ImageLoader();
		imageLoader.data = new ImageData[] {image.getImageData()};
		imageLoader.save(result, format);

		return result.toByteArray();
	}

	/**
	 * Calcule les limites (contours) de la figure
	 * @param figure La figure a enregistrer
	 * @return Limites de la figure
	 */
	private Rectangle getBounds(IFigure figure) {
		Rectangle bounds;
		if (figure instanceof FreeformFigure) {
			FreeformFigure freeformDiagramFigure = (FreeformFigure) figure;
			bounds = freeformDiagramFigure.getFreeformExtent();
		} else {
			bounds = figure.getBounds();
		}
		return bounds;
	}

	/**
	 * Retourne les filtres des extensions pour la boite de dialogue
	 * @return Les filtres d'extensions
	 */
	protected final String[] getImageFilterExtensions() {
		return new String[] {"*.bmp", "*.jpg", "*.gif", "*.png"}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	}

	/**
	 * Retourne les noms de filtres pour la boite de dialogue
	 * @return Les noms de filtres
	 */
	protected final String[] getFilterNames() {
		return new String[] {"BMP (*.bmp)", "JPEG (*.jpg)", "GIF (*.gif)", "PNG (*.png)" };  //$NON-NLS-1$ //$NON-NLS-2$//$NON-NLS-3$ //$NON-NLS-4$
	}

	/** {@inheritDoc} */
	public final void run(IAction action) {
		this.run();
	}

	/** {@inheritDoc} */
	public void selectionChanged(IAction action, ISelection selection) { }

	/** {@inheritDoc} */
	public void dispose() { }
}
