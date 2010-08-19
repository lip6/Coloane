package fr.lip6.move.coloane.api.warehouse.services;

import fr.lip6.move.coloane.api.warehouse.WareHouseApi;
import fr.lip6.move.coloane.api.warehouse.objects.ModelsListChooser;
import fr.lip6.move.coloane.core.ui.files.ModelLoader;
import fr.lip6.move.coloane.interfaces.api.services.IApiService;
import fr.lip6.move.coloane.interfaces.exceptions.ServiceException;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;
import fr.lip6.move.coloane.interfaces.objects.result.Result;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.PlatformUI;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class BrowseModels implements IApiService {
	/** The logger */
	private static final Logger LOGGER = Logger.getLogger("fr.lip6.move.coloane.core"); //$NON-NLS-1$

	/**
	 * Constructor
	 * 
	 * @param api
	 *            The main API file
	 */
	public BrowseModels(WareHouseApi api) {
	}

	/**
	 * {@inheritDoc}
	 */
	public String getName() {
		return "Browse models...";
	}

	/**
	 * {@inheritDoc}
	 */
	public String getDescription() {
		return "Browse available models from the Coloane warehouse";
	}

	/**
	 * Create a connection
	 * 
	 * @param urlToConnect
	 *            The URL to which a connection has to be established
	 * @return The connection. It can be problematic. Up to you to check the
	 *         response code {@link HttpURLConnection}
	 * @throws IOException
	 *             If anything went wrong during the connection.
	 */
	private HttpURLConnection connectToUrl(URL urlToConnect, String responseType) throws IOException {
		HttpURLConnection connection = (HttpURLConnection) urlToConnect.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Accept", responseType);
		return connection;
	}

	/**
	 * Display a dialog box which presents all available models
	 * 
	 * @param models
	 *            A map that contains all model's id associated with their name
	 * @return The list of selected models
	 */
	private List<String> buildListDialog(final Map<String, String> models) {
		// Execute the creation commands inside a UI thread
		ModelsListChooser modelsListChooser = new ModelsListChooser(models);
		PlatformUI.getWorkbench().getDisplay().syncExec(modelsListChooser);
		return modelsListChooser.getResults();
	}

	public List<IResult> run(IGraph model, IProgressMonitor monitor) throws ServiceException {
		// This method must return a list of results
		List<IResult> results = new ArrayList<IResult>();
		Result result = new Result("warehousemodel");
		results.add(result);

		try {

			// Connection & Request
			HttpURLConnection connection = this.connectToUrl(new URL("http://localhost:8888/recess/warehouse/models"), "text/xml");
			connection.connect();
			InputStream responseBodyStream = connection.getInputStream();

			// Look at headers
			if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
				LOGGER.warning("Bad answer from the warehouse");
				throw new ServiceException(ServiceException.CONNECTION_ERROR, "Bad answer from the warehouse");
			}

			// Browse results
			LOGGER.fine("Browsing results from the warehouse");
			SAXBuilder sxb = new SAXBuilder();
			Document document = null;
			Map<String, String> models = new HashMap<String, String>();
			try {
				document = sxb.build(responseBodyStream);
				Element root = document.getRootElement();
				@SuppressWarnings("unchecked")
				List<Element> modelsElement = root.getChildren();

				// Build the model list (id, name)
				for (Element element : modelsElement) {
					models.put(element.getChild("id").getText(), element.getChild("name").getText());
				}
			} catch (IOException e) {
				LOGGER.warning("Something went wrong with the warehouse");
				throw new ServiceException(ServiceException.CONNECTION_ERROR,
						e.getMessage());
			} catch (JDOMException e) {
				LOGGER.warning("Something went wrong with the warehouse answer");
				throw new ServiceException(ServiceException.CONNECTION_ERROR,
						"The warehouse answer was not correct");
			}

			LOGGER.fine("Closing connection");
			connection.disconnect();

			// Display a list of available models
			List<String> selectedModels = this.buildListDialog(models);
			// What is the model to fetch from the warehouse ?
			String wantedModelId = null;
			for (String id : models.keySet()) {
				if (models.get(id).equals(selectedModels.get(0))) {
					wantedModelId = id;
					break;
				}
			}

			// Connection & Request
			connection = this.connectToUrl(new URL("http://localhost:8888/recess/warehouse/models/"	+ wantedModelId), "text/coloane");
			connection.connect();
			responseBodyStream = connection.getInputStream();

			// Look at headers
			if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
				LOGGER.warning("Bad answer from the warehouse");
				throw new ServiceException(ServiceException.CONNECTION_ERROR, "Bad answer from the warehouse");
			}

			// Build the new graph from the response

			IGraph newGraph = ModelLoader.loadGraphFromXML(toString(responseBodyStream));
			result.setNewGraph(newGraph);
			result.setDisplay(false);
			connection.disconnect();

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

	/**
	 * Writes the content of the input stream to a <code>String<code>.
	 */
	private String toString(InputStream inputStream) throws IOException {
		String string;
		StringBuilder outputBuilder = new StringBuilder();
		if (inputStream != null) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			while (null != (string = reader.readLine())) {
				outputBuilder.append(string).append('\n');
			}
		}
		return outputBuilder.toString();
	}
}
