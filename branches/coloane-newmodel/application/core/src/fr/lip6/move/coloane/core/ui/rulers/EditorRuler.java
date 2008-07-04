package fr.lip6.move.coloane.core.ui.rulers;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.rulers.RulerProvider;

public class EditorRuler implements Serializable {

	/** Des propriétés nécessaire pour avertir les objets de changements */
	public static final String PROPERTY_CHILDREN = "children changed"; //$NON-NLS-1$
	public static final String PROPERTY_UNIT = "units changed"; //$NON-NLS-1$

	static final long serialVersionUID = 1L;
	protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
	
	/** L'unité utilisée pour la règle */
	private int unit;
	
	/** Indicateur de configuration : <code>true</code> pour une règle horizontale */
	private boolean horizontal;
	
	/** Liste des guides associé à cette règle */
	private List<EditorGuide> guides = new ArrayList<EditorGuide>();

	/**
	 * Constructeur
	 * @param isHorizontal Indicateur de configuration : <code>true</code> si la règle est horizontale
	 */
	public EditorRuler(boolean isHorizontal) {
		// Utilisation des pixels par défaut
		this(isHorizontal, RulerProvider.UNIT_PIXELS);
	}

	/**
	 * Constructeur
	 * @param isHorizontal Indicateur de configuration : <code>true</code> si la règle est horizontale
	 * @param unit L'unité de mesure utilisée
	 */
	public EditorRuler(boolean isHorizontal, int unit) {
		horizontal = isHorizontal;
		setUnit(unit);
	}

	/**
	 * Ajoute un guide à la règle courante
	 * @param guide Le guide à ajouter
	 * @see EditorGuide
	 */
	public void addGuide(EditorGuide guide) {
		if (!guides.contains(guide)) {
			guide.setHorizontal(!isHorizontal());
			guides.add(guide);
			listeners.firePropertyChange(PROPERTY_CHILDREN, null, guide);
		}
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		listeners.addPropertyChangeListener(listener);
	}

	// the returned list should not be modified
	public List<EditorGuide> getGuides() {
		return guides;
	}

	/**
	 * @return L'unité utilisée
	 */
	public int getUnit() {
		return unit;
	}

	/**
	 * @return <code>true</code> si la regle est cachée
	 */
	public boolean isHidden() {
		return false;
	}

	/**
	 * @return <code>true</code> si la règle est horizontale
	 */
	public boolean isHorizontal() {
		return horizontal;
	}

	/**
	 * Supprimer le guide désigné
	 * @param guide Le guide à supprimer
	 */
	public void removeGuide(EditorGuide guide) {
		if (guides.remove(guide)) {
			listeners.firePropertyChange(PROPERTY_CHILDREN, null, guide);
		}
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		listeners.removePropertyChangeListener(listener);
	}

	/**
	 * Masquer la règle
	 * @param isHidden
	 */
	public void setHidden(boolean isHidden) { }

	/**
	 * Change l'unite de la regle
	 * @param newUnit La nouvelle unite a considérer
	 */
	public void setUnit(int newUnit) {
		if (unit != newUnit) {
			int oldUnit = unit;
			unit = newUnit;
			listeners.firePropertyChange(PROPERTY_UNIT, oldUnit, newUnit);
		}
	}
}