package fr.lip6.move.coloane.interfaces;

/**
 * Interface fournie par l'API a Coloane pour la manipulation de sous-menus
 */
public interface IMenuCom {
	
	public boolean isEnabled();
	
	public void setEnabled(boolean enabled);
	
	public String getFatherName();
	
	public String getServiceName();

}
