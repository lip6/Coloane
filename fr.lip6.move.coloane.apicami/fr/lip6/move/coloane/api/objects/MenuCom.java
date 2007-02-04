package fr.lip6.move.coloane.api.objects;

import fr.lip6.move.coloane.interfaces.IMenuCom;

public class MenuCom implements IMenuCom {
	private String fatherName;
	private String serviceName;
	private boolean enabled;

	public MenuCom (String serviceName, String fatherName, boolean enabled) {
		this.fatherName = fatherName;
		this.serviceName = serviceName;
		this.enabled = enabled;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
}
