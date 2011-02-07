/**
 * Copyright (c) 2006-2010 MoVe - Laboratoire d'Informatique de Paris 6 (LIP6).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Jean-Baptiste VORON (LIP6) - Project Head / Initial contributor
 *   Clément DÉMOULINS (LIP6) - Project Manager
 *
 * Official contacts:
 *   coloane@lip6.fr
 *   http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.core.ui.rulers;

import fr.lip6.move.coloane.core.ui.commands.GuideCreateCmd;
import fr.lip6.move.coloane.core.ui.commands.GuideDeleteCmd;
import fr.lip6.move.coloane.core.ui.commands.GuideMoveCmd;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.rulers.RulerChangeListener;
import org.eclipse.gef.rulers.RulerProvider;

/**
 * Rulers and Guides Manager.<br>
 * There is one of such manager for each ruler.
 *
 * @author Jean-Baptiste Voron
 */
public class EditorRulerProvider extends RulerProvider {
	/** Horizontal guide */
	public static final int HORIZONTAL_ORIENTATION = 1;
	/** Vertical guide */
	public static final int VERTICAL_ORIENTATION = 2;

	/** The ruler managed by this manager */
	private EditorRuler ruler;

	/** A listener of rulers events */
	private PropertyChangeListener rulerListener = new PropertyChangeListener() {
		public void propertyChange(PropertyChangeEvent evt) {
			// What to do when ruler units have been changed ?
			if (evt.getPropertyName().equals(EditorRuler.UNIT_PROP)) {
				for (int i = 0; i < listeners.size(); i++) {
					((RulerChangeListener) listeners.get(i)).notifyUnitsChanged(ruler.getUnit());
				}
				return;
			}

			EditorGuide guide = null;
			// What to do when a new guide is added to the ruler ?
			if (evt.getPropertyName().equals(EditorRuler.NEW_GUIDE_PROP)) {
				guide = (EditorGuide) evt.getNewValue();
				guide.addPropertyChangeListener(guideListener);
			}

			// What to do when a new guide is removed from the ruler ?
			if (evt.getPropertyName().equals(EditorRuler.REMOVE_GUIDE_PROP)) {
				guide = (EditorGuide) evt.getOldValue();
				guide.removePropertyChangeListener(guideListener);
			}

			// For both cases, a message has to be sent to listeners
			for (int i = 0; i < listeners.size(); i++) {
				((RulerChangeListener) listeners.get(i)).notifyGuideReparented(guide);
			}
		}
	};

	/** A listener of guide events */
	private PropertyChangeListener guideListener = new PropertyChangeListener() {
		public void propertyChange(PropertyChangeEvent evt) {
			// When an element is attached or detached from a guide
			if (evt.getPropertyName().equals(EditorGuide.ELEMENT_PROP)) {
				for (int i = 0; i < listeners.size(); i++) {
					((RulerChangeListener) listeners.get(i)).notifyPartAttachmentChanged(evt.getNewValue(), evt.getSource());
				}
			// When a guide is moved along the ruler
			} else {
				for (int i = 0; i < listeners.size(); i++) {
					((RulerChangeListener) listeners.get(i)).notifyGuideMoved(evt.getSource());
				}
			}
		}
	};

	/**
	 * Constructor
	 * @param ruler The ruler that is managed by this manager
	 */
	public EditorRulerProvider(EditorRuler ruler) {
		this.ruler = ruler;
		// Be aware of ruler changes
		this.ruler.addPropertyChangeListener(rulerListener);
		List<EditorGuide> guides = getGuides();
		// Be aware of guides changes
		for (int i = 0; i < guides.size(); i++) {
			guides.get(i).addPropertyChangeListener(guideListener);
		}
	}

	/** {@inheritDoc} */
	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public final List getAttachedModelObjects(Object guide) {
		return new ArrayList(((EditorGuide) guide).getAttachedElements());
	}

	/** {@inheritDoc} */
	@Override
	public final Command getCreateGuideCommand(int position) {
		return new GuideCreateCmd(ruler, position);
	}

	/** {@inheritDoc} */
	@Override
	public final Command getDeleteGuideCommand(Object guide) {
		return new GuideDeleteCmd((EditorGuide) guide, ruler);
	}

	/** {@inheritDoc} */
	@Override
	public final Command getMoveGuideCommand(Object guide, int pDelta) {
		return new GuideMoveCmd((EditorGuide) guide, pDelta);
	}

	/** {@inheritDoc} */
	@Override
	public final int[] getGuidePositions() {
		List<EditorGuide> guides = getGuides();
		int[] result = new int[guides.size()];
		for (int i = 0; i < guides.size(); i++) {
			result[i] = guides.get(i).getPosition();
		}
		return result;
	}

	/** {@inheritDoc} */
	@Override
	public final Object getRuler() {
		return ruler;
	}

	/** {@inheritDoc} */
	@Override
	public final int getUnit() {
		return ruler.getUnit();
	}

	/** {@inheritDoc} */
	@Override
	public final void setUnit(int newUnit) {
		ruler.setUnit(newUnit);
	}

	/** {@inheritDoc} */
	@Override
	public final int getGuidePosition(Object guide) {
		return ((EditorGuide) guide).getPosition();
	}

	/** {@inheritDoc} */
	@Override
	public final List<EditorGuide> getGuides() {
		return ruler.getGuides();
	}
}
