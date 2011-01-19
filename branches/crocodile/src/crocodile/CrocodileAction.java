package crocodile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;

import fr.lip6.move.coloane.extensions.exporttogml.ExportToGML;
import fr.lip6.move.coloane.interfaces.exceptions.ExtensionException;
import fr.lip6.move.coloane.interfaces.exceptions.ServiceException;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;
import fr.lip6.move.coloane.interfaces.objects.services.IService;

public class CrocodileAction implements IService {

	public CrocodileAction() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<IResult> run(IGraph model, IProgressMonitor monitor)
			throws ServiceException {
		try {
			File tmpFile = File.createTempFile("tmp", ".gml");
			tmpFile.deleteOnExit();
			
			ExportToGML exporter = new ExportToGML();
			// TODO use a sub-monitor instead of monitor
			exporter.export(model, tmpFile.getAbsolutePath(), monitor);
			
		} catch (IOException e) {
			throw new ServiceException(e.getMessage());
		} catch (ExtensionException e) {
			throw new ServiceException(e.getMessage());
		}
		
		return null;
	}

}
