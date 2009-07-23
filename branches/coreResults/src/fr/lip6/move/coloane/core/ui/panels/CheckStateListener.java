package fr.lip6.move.coloane.core.ui.panels;

import fr.lip6.move.coloane.core.model.interfaces.ISpecialState;
import fr.lip6.move.coloane.core.motor.session.ISession;
import fr.lip6.move.coloane.core.motor.session.SessionManager;
import fr.lip6.move.coloane.core.results.IResultTree;
import fr.lip6.move.coloane.interfaces.model.IElement;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;

public class CheckStateListener implements ICheckStateListener {
	/**
	 * Map conservant pour chaque noeud mis en valeur, le nombre de résultat
	 * coché dans la vue le concernant (le noeud).
	 * */
	private Map<ISpecialState, Integer> checkStateMap;

	/** Vue représentant l'arbre des résultats */
	private CheckboxTreeViewer viewer;	


	CheckStateListener(CheckboxTreeViewer viewer, Map<ISpecialState, Integer> map) {
		this.viewer = viewer;
		this.checkStateMap = map;
	}

	/**
	 * Mise a jour de l'état check d'une ligne de résultat ainsi que de
	 * tous les fils (récursivement)
	 * @param session session courante
	 * @param result sous arbre de résultat
	 * @param wasCheck ancienne état de result
	 * @param toCheck nouvel état
	 */
	private void checkResult(ISession session, IResultTree result, boolean wasCheck, boolean toCheck) {
		// On vérifie qu'on passe de true à false ou inversement
		if (wasCheck != toCheck) {
			// On traite tous les éléments devant être mis en valeur
			for (int id : result.getHighlighted()) {
				ISpecialState element = (ISpecialState) session.getGraph().getObject(id);
				if (element != null) {
					// On compte le nombre de fois qu'un élément a été
					// demandé a être mis en valeur.
					Integer value = checkStateMap.get(element);
					if (toCheck) {
						if (value == null) {
							value = 0;
						}
						element.setSpecialState(true);
						value++;
					} else {
						value--;
						if (value == 0) {
							element.setSpecialState(false);
						}
					}
					checkStateMap.put(element, value);
				}
			}
			
			
			// On traite ensuite tous les attributs devant être mis en valeur
			Map<Integer,List<String>> attributesMap = result.getAttributesOutline();
			Iterator<Integer> it = attributesMap.keySet().iterator();
			
			while (it.hasNext()) {
				int objectId = it.next();
				
				IElement element = session.getGraph().getObject(objectId);
				if (element != null) {
					for (String str : attributesMap.get(objectId)) {
						ISpecialState attribute = (ISpecialState) element.getAttribute(str);
						
						if (attribute != null) { 
							// On compte le nombre de fois qu'un attribut a été
							// demandé a être mis en valeur.
							Integer value = checkStateMap.get(attribute);
							if (toCheck) {
								if (value == null) {
									value = 0;
								}
								attribute.setSpecialState(true);
								value++;
							} else {
								value--;
								if (value == 0) {
									attribute.setSpecialState(false);
								}
							}
							checkStateMap.put(attribute, value);
						}
					}
				}
			}
		}

		// Gestion des Tips
		if (toCheck) {
			if (result.getHighlighted().size() > 0) {
				session.removeAllTips(result.getTips(result.getHighlighted()));
				session.addAllTips(result.getTips(result.getHighlighted()));
			} else {
				session.removeAllTips(result.getTips());
				session.addAllTips(result.getTips());
			}
		} else {
			if (result.getHighlighted().size() > 0) {
				session.removeAllTips(result.getTips(result.getHighlighted()));
			} else {
				session.removeAllTips(result.getTips());
			}
		}

		// Appel récursif sur tous les fils
		for (IResultTree child : result.getChildren()) {
			checkResult(session, child, viewer.getChecked(child), toCheck);
		}
	}
	
	
	/**
	 * Permet d'empêcher de cocher les textualsResults 
	 * 
	 * @param result le sous-resultat coché dans l'arbre
	 * @param checked représente le nouvel état coché dans l'arbre : true si le résultat est coché, false s'il ne l'est pas
	 * @return true si le résultat a été coché ou si au moins un des sous-résultats est coché, false sinon
	 */
	private boolean disableTextualResults(IResultTree result, boolean checked) {
		boolean bool = false;
		// Appel récursif sur tous les children du résultat
		for (IResultTree child : result.getChildren()) {
			// Si un des fils est coché pendant l'appel récursif, bool prendra la valeur vrai en sortie de boucle
			bool = disableTextualResults(child, checked) ||bool;
		}
		// Si au moins l'un des sous-résultat a été coché, alors on coche le résultat courant (le parent du sous-résultat coché)
		// On passe dans ce if uniquement si le résultat courant comporte des sous-résultats
		if (bool) {
			viewer.setChecked(result, checked);
			return true;
		}
		// Si le résultat n'a pas de sous résultats, on regarde s'il a des objets du graphe à highlight
		// Si non, on le décoche
		// Si oui, on le laisse tel quel  car son état a déjà été modifié avant l'appel de la méthode
		// TODO : si des attributs du modèle sont supprimés, cela devient un textualResult mais ce n'est pas pris en compte
		if ((result.getHighlighted().size() == 0) && result.getAttributesOutline().isEmpty()) {
			viewer.setChecked(result, false);
			return false;
		}
		return true;
	}
	
	/** {@inheritDoc} */
	public void checkStateChanged(CheckStateChangedEvent event) {
		IResultTree result = (IResultTree) event.getElement();
		checkResult(SessionManager.getInstance().getCurrentSession(), result, !event.getChecked(), event.getChecked());
		viewer.setSubtreeChecked(event.getElement(), event.getChecked());
		disableTextualResults(result, event.getChecked());
	}

	public Map<ISpecialState, Integer> getCheckStateMap() {
		return checkStateMap;
	}
}


