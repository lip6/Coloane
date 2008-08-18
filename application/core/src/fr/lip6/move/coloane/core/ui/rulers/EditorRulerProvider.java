package fr.lip6.move.coloane.core.ui.rulers;

import fr.lip6.move.coloane.core.ui.commands.CreateGuideCommand;
import fr.lip6.move.coloane.core.ui.commands.DeleteGuideCommand;
import fr.lip6.move.coloane.core.ui.commands.MoveGuideCommand;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.rulers.RulerChangeListener;
import org.eclipse.gef.rulers.RulerProvider;

/**
 * Le gestionnaire de règles et de guides
 */
public class EditorRulerProvider extends RulerProvider {

	/** La regle concerné */
	private EditorRuler ruler;

	private PropertyChangeListener rulerListener = new PropertyChangeListener() {
		public void propertyChange(PropertyChangeEvent evt) {
			if (evt.getPropertyName().equals(EditorRuler.PROPERTY_CHILDREN)) {
				EditorGuide guide = (EditorGuide) evt.getNewValue();
				if (getGuides().contains(guide)) {
					guide.addPropertyChangeListener(guideListener);
				} else {
					guide.removePropertyChangeListener(guideListener);
				}
				for (int i = 0; i < listeners.size(); i++) {
					((RulerChangeListener) listeners.get(i)).notifyGuideReparented(guide);
				}
			} else {
				for (int i = 0; i < listeners.size(); i++) {
					((RulerChangeListener) listeners.get(i)).notifyUnitsChanged(ruler.getUnit());
				}
			}
		}
	};

	private PropertyChangeListener guideListener = new PropertyChangeListener() {
		public void propertyChange(PropertyChangeEvent evt) {
			if (evt.getPropertyName().equals(EditorGuide.PROPERTY_CHILDREN)) {
				for (int i = 0; i < listeners.size(); i++) {
					((RulerChangeListener) listeners.get(i)).notifyPartAttachmentChanged(evt.getNewValue(), evt.getSource());
				}
			} else {
				for (int i = 0; i < listeners.size(); i++) {
					((RulerChangeListener) listeners.get(i)).notifyGuideMoved(evt.getSource());
				}
			}
		}
	};

	/**
	 * Constructeur
	 * @param ruler La règle (horizontale ou verticale) concernée par ce gestionnaire
	 */
	public EditorRulerProvider(EditorRuler ruler) {
		this.ruler = ruler;
		this.ruler.addPropertyChangeListener(rulerListener);
		List<EditorGuide> guides = getGuides();
		for (int i = 0; i < guides.size(); i++) {
			guides.get(i).addPropertyChangeListener(guideListener);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public final List getAttachedModelObjects(Object guide) {
		return new ArrayList(((EditorGuide) guide).getModelObjects());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Command getCreateGuideCommand(int position) {
		return new CreateGuideCommand(ruler, position);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Command getDeleteGuideCommand(Object guide) {
		return new DeleteGuideCommand((EditorGuide) guide, ruler);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Command getMoveGuideCommand(Object guide, int pDelta) {
		return new MoveGuideCommand((EditorGuide) guide, pDelta);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int[] getGuidePositions() {
		List<EditorGuide> guides = getGuides();
		int[] result = new int[guides.size()];
		for (int i = 0; i < guides.size(); i++) {
			result[i] = guides.get(i).getPosition();
		}
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Object getRuler() {
		return ruler;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getUnit() {
		return ruler.getUnit();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setUnit(int newUnit) {
		ruler.setUnit(newUnit);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getGuidePosition(Object guide) {
		return ((EditorGuide) guide).getPosition();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final List<EditorGuide> getGuides() {
		return ruler.getGuides();
	}
}
