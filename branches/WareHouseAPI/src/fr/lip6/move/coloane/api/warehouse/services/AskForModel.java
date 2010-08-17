package fr.lip6.move.coloane.api.warehouse.services;

import fr.lip6.move.coloane.api.warehouse.WareHouseApi;
import fr.lip6.move.coloane.core.ui.files.ModelLoader;
import fr.lip6.move.coloane.interfaces.api.services.IApiService;
import fr.lip6.move.coloane.interfaces.exceptions.ServiceException;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;
import fr.lip6.move.coloane.interfaces.objects.result.Result;

import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.core.runtime.IProgressMonitor;

public class AskForModel implements IApiService {
	/** The logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$
	
	/**
	 * Constructor
	 * @param api The main API file
	 */
	public AskForModel(WareHouseApi api) { }

	/**
	 * The name of the service
	 */
	public String getName() {
		return "Get a model";
	}

	public String getDescription() {
		return "Fetch a model from the Coloane warehouse";
	}


	public List<IResult> run(IGraph model, IProgressMonitor monitor) throws ServiceException {
				
		List<IResult> results = new ArrayList<IResult>();
		Result result = new Result("newdemomodel");
		results.add(result);
		
		try {
			URL warehouse = new URL("http://localhost:8888/coloane_warehouse/demo.xml");
			HttpURLConnection connection = (HttpURLConnection)warehouse.openConnection();
			connection.setRequestMethod("GET");

			// Connection & Request
			long time = System.currentTimeMillis();
			connection.connect();

			byte buffer[] = new byte[8192];
			int read = 0;

			InputStream responseBodyStream = connection.getInputStream();
			StringBuffer responseBody = new StringBuffer();
			while ((read = responseBodyStream.read(buffer)) != -1) {
				responseBody.append(new String(buffer, 0, read));
			}
			connection.disconnect();
			time = System.currentTimeMillis() - time;

			// Start printing output
			LOGGER.warning("[read " + responseBody.length() + " chars in " + time + "ms]");

			// Look at headers
			// The 0th header has a null key, and the value is the response line ("HTTP/1.1 200 OK" or whatever)
			String header = null;
			String headerValue = null;
			int index = 0;
			while ((headerValue = connection.getHeaderField(index)) != null) {
				header = connection.getHeaderFieldKey(index);
				if (header == null)
					System.out.println(headerValue);
				else
					System.out.println(header + ": " + headerValue);
				index++;
			}

			// Dump body
			System.out.print(responseBody);
			System.out.flush();
			
			// Build the new graph from the response
			IGraph newGraph = ModelLoader.loadGraphFromXMLString(responseBody.toString());
			result.setNewGraph(newGraph);
			result.setDisplay(false);
			
		} catch (MalformedURLException e) {
			LOGGER.warning("The URL is malformed");
		} catch (ConnectException e) {
			LOGGER.warning("Unable to connect to the warehouse server");
			ServiceException se = new ServiceException(ServiceException.CONNECTION_ERROR, "Unable to connect to the warehouse server");
			throw se;
		} catch (IOException e) {
			LOGGER.warning("Unable to connect to the warehouse server");
			e.printStackTrace();
		}
		return results;
	}
}
