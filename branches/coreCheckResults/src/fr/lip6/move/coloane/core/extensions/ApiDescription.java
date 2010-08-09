package fr.lip6.move.coloane.core.extensions;

import fr.lip6.move.coloane.core.exceptions.ColoaneException;
import fr.lip6.move.coloane.interfaces.api.IApi;

import org.eclipse.jface.resource.ImageDescriptor;

/**
 * Describe a connected API.<br>
 * Those information will be useful to build the menus.
 * 
 * @author Jean-Baptiste Voron
 */
public class ApiDescription {
	
	/** Name of the services API */
	private String name;
	
	/** Description of the API */
	private String description;
	
	/** Icon associated with the API */
	private ImageDescriptor icon = null;
	
	/** The main class of the API */
	private IApi apiClass;

	/**
	 * Constructor
	 * @param apiClass The main class of the API
	 * @param name The API name
	 * @throws ColoaneException if the API name is <code>null</code>
	 */
	public ApiDescription(IApi apiClass, String name) throws ColoaneException {
		this(apiClass, name, ""); //$NON-NLS-1$
	}
	
	/**
	 * Constructor
	 * @param apiClass The main class of the API
	 * @param name The API name
	 * @param description The API description (will be used as tip)
	 * @throws ColoaneException if the API name is <code>null</code>
	 */
	public ApiDescription(IApi apiClass, String name, String description) throws ColoaneException {
		this(apiClass, name, description, null);
	}
	
	/**
	 * Constructor
	 * @param apiClass The main class of the API
	 * @param name The API name
	 * @param description The API description (will be used as tip)
	 * @param iconPath The icon associated with the API
	 * @throws ColoaneException if the API name is <code>null</code>
	 */
	public ApiDescription(IApi apiClass, String name, String description, String iconPath) throws ColoaneException {
		this.apiClass = apiClass;

		this.name = name;
		if (name == null)
			throw new ColoaneException("The API name cannot be null"); //$NON-NLS-1$
		
		this.description = description;
		if (this.description == null)
			this.description = ""; //$NON-NLS-1$
		
		if (iconPath != null) {
			this.icon = ImageDescriptor.createFromFile(this.apiClass.getClass(), "/" + iconPath); //$NON-NLS-1$
		}
	}
	
	/**
	 * @return the API name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return the API description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * @return the icon associated with the API
	 */
	public ImageDescriptor getIcon() {
		return icon;
	}
	
	/**
	 * @return the main class of the API
	 */
	public IApi getApiClass() {
		return apiClass;
	}
}
