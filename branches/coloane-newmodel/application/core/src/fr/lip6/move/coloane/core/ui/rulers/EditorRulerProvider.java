package fr.lip6.move.coloane.core.ui.rulers;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.rulers.RulerChangeListener;
import org.eclipse.gef.rulers.RulerProvider;

import fr.lip6.move.coloane.core.ui.commands.CreateGuideCommand;
import fr.lip6.move.coloane.core.ui.commands.DeleteGuideCommand;
import fr.lip6.move.coloane.core.ui.commands.MoveGuideCommand;

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

	public EditorRulerProvider(EditorRuler ruler) {
		this.ruler = ruler;
		this.ruler.addPropertyChangeListener(rulerListener);
		List<EditorGuide> guides = getGuides();
		for (int i = 0; i < guides.size(); i++) {
			guides.get(i).addPropertyChangeListener(guideListener);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.rulers.RulerProvider#getAttachedModelObjects(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public List getAttachedModelObjects(Object guide) {
		return new ArrayList(((EditorGuide) guide).getModelObjects());
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.rulers.RulerProvider#getCreateGuideCommand(int)
	 */
	public Command getCreateGuideCommand(int position) {
		return new CreateGuideCommand(ruler, position);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.rulers.RulerProvider#getDeleteGuideCommand(java.lang.Object)
	 */
	public Command getDeleteGuideCommand(Object guide) {
		return new DeleteGuideCommand((EditorGuide) guide, ruler);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.rulers.RulerProvider#getMoveGuideCommand(java.lang.Object, int)
	 */
	public Command getMoveGuideCommand(Object guide, int pDelta) {
		return new MoveGuideCommand((EditorGuide) guide, pDelta);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.rulers.RulerProvider#getGuidePositions()
	 */
	public int[] getGuidePositions() {
		List<EditorGuide> guides = getGuides();
		int[] result = new int[guides.size()];
		for (int i = 0; i < guides.size(); i++) {
			result[i] = guides.get(i).getPosition();
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.rulers.RulerProvider#getRuler()
	 */
	public Object getRuler() {
		return ruler;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.rulers.RulerProvider#getUnit()
	 */
	public int getUnit() {
		return ruler.getUnit();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.rulers.RulerProvider#setUnit(int)
	 */
	public void setUnit(int newUnit) {
		ruler.setUnit(newUnit);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.rulers.RulerProvider#getGuidePosition(java.lang.Object)
	 */
	public int getGuidePosition(Object guide) {
		return ((EditorGuide) guide).getPosition();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.gef.rulers.RulerProvider#getGuides()
	 */
	public List<EditorGuide> getGuides() {
		return ruler.getGuides();
	}
}
