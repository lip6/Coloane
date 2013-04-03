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
 *   Yann THIERRY-MIEG (LIP6)
 *
 * Official contacts:
 *   coloane@lip6.fr
 *   http://coloane.lip6.fr
 */
package fr.lip6.move.coloane.extension.exportGAL.exportToGAL;

import fr.lip6.move.coloane.interfaces.exceptions.ExtensionException;
import fr.lip6.move.coloane.interfaces.extensions.IExportTo;
import fr.lip6.move.coloane.interfaces.model.IArc;
import fr.lip6.move.coloane.interfaces.model.IAttribute;
import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.INode;
import fr.lip6.move.gal.And;
import fr.lip6.move.gal.Assignment;
import fr.lip6.move.gal.BinaryIntExpression;
import fr.lip6.move.gal.BooleanExpression;
import fr.lip6.move.gal.Call;
import fr.lip6.move.gal.Comparison;
import fr.lip6.move.gal.ComparisonOperators;
import fr.lip6.move.gal.Constant;
import fr.lip6.move.gal.False;
import fr.lip6.move.gal.GalFactory;
import fr.lip6.move.gal.Ite;
import fr.lip6.move.gal.Label;
import fr.lip6.move.gal.Not;
import fr.lip6.move.gal.System;
import fr.lip6.move.gal.Transient;
import fr.lip6.move.gal.Transition;
import fr.lip6.move.gal.True;
import fr.lip6.move.gal.Variable;
import fr.lip6.move.gal.VariableRef;
import fr.lip6.move.serialization.SerializationUtil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.eclipse.core.runtime.IProgressMonitor;


/**
 * Export models to GAL format
 *
 * @author Yann Thierry-Mieg
 */
public class ExportToGAL implements IExportTo {

	/**
	 * Export a model to GAL formatted file
	 * @param model The model to export
	 * @param filePath The path of the destination file
	 * @param monitor A monitor to follow the export progression
	 * @throws ExtensionException Something wrong has happened.
	 */
	public final void export(IGraph model, String filePath, IProgressMonitor monitor) throws ExtensionException {

		// Filename checks
		if (filePath.equalsIgnoreCase("") || filePath == null) { //$NON-NLS-1$
			throw new ExtensionException("The filename is not correct. Please provide a valid filename");
		}

		int totalWork = model.getNodes().size() + model.getArcs().size();
		monitor.beginTask("Export to GAL", totalWork);


		String name = "model";
		final GalFactory gf = GalFactory.eINSTANCE;
		System gal = gf.createSystem();
		gal.setName(name);
		Transient tr = gf.createTransient();
		False f = gf.createFalse();
		f.setValue("False");
		tr.setValue(f);
		gal.setTransient(tr);

		Map<INode,Variable> varMap = new HashMap<INode, Variable>();
		// nodes : each place gives rise to a variable
		for (INode node : model.getNodes()) {
			if ("place".equals(node.getNodeFormalism().getName())) {
				Variable var = gf.createVariable();
				var.setName(node.getAttribute("name").getValue());
				var.setValue(Integer.parseInt(node.getAttribute("marking").getValue()));
				gal.getVariables().add(var);
				varMap.put(node,var);
			}
		}


		// transition clocks
		boolean isTimed = false;
		for (INode node : model.getNodes()) {
			if ("transition".equals(node.getNodeFormalism().getName())) {
				int eft = eft(node);
				int lft = lft(node);
				if (hasClock(eft,lft)) {
					isTimed = true;
					Variable var = gf.createVariable();
					var.setName(node.getAttribute("label").getValue()+".clock");
					var.setValue(0);
					gal.getVariables().add(var);					
					varMap.put(node,var);
				}
			}
		}

		// prepare the creation of the reset disabled transition
		Transition reset = gf.createTransition();
		Label labReset = gf.createLabel();
		labReset.setName("reset");
		reset.setName("reset");
		reset.setLabel(labReset );
		True tru = gf.createTrue();
		tru.setValue("True");

		reset.setGuard(tru);


		// prepare the creation of the elapse disabled transition
		Transition elapse = gf.createTransition();
		Label labElapse = gf.createLabel();
		labElapse.setName("elapse");
		elapse.setName("elapse");
		elapse.setLabel(labElapse);
		tru = gf.createTrue();
		tru.setValue("True");

		elapse.setGuard(tru);

		for (INode node : model.getNodes()) {
			if ("transition".equals(node.getNodeFormalism().getName())) {
				Transition t = gf.createTransition();
				t.setName(node.getAttribute("label").getValue());

				if ("public".equals(node.getAttribute("visibility").getValue())) {
					Label lab = gf.createLabel();
					lab.setName(node.getAttribute("label").getValue());
					t.setLabel(lab );
				}

				// build elapse effect
				int eft = eft(node);
				int lft = lft(node);

				BooleanExpression guard = computeGuard(node,gf,varMap);

				if (eft != 0) {
					// add clock >= eft to guard condition
					And and = gf.createAnd();
					
					BooleanExpression comp = createComparison(node, ComparisonOperators.GE, eft, gf, varMap);

					and.setLeft(guard);
					and.setRight(comp);
					t.setGuard(and);
				} else {
					t.setGuard(guard);
				}

				// build actions : first action is reset, then take tokens, then put tokens
				for (IArc arc : node.getIncomingArcs()) {
					String arcType = arc.getArcFormalism().getName(); 

					if ("reset".equals(arcType) ) {


						// p= 0
						Assignment ass = assignVarConst(arc.getSource(), 0, gf, varMap);
						t.getActions().add(ass);
					}
				}
				// build actions :  then take tokens
				for (IArc arc : node.getIncomingArcs()) {
					String arcType = arc.getArcFormalism().getName(); 

					if ("arc".equals(arcType) ) {

						// p -= valuation
						int val;
						IAttribute iaval = arc.getAttribute("valuation");
						if (iaval != null) {
							val = - Integer.parseInt(iaval.getValue());
						} else {
							val = - 1;
						}
						Assignment ass = incrementVar(arc.getSource(), val, gf, varMap);
						
						t.getActions().add(ass);
					}
				}
				// build actions :  then put tokens
				for (IArc arc : node.getOutgoingArcs()) {
					String arcType = arc.getArcFormalism().getName(); 

					if ("arc".equals(arcType) ) {

						int value ;
						IAttribute iaval = arc.getAttribute("valuation");
						if (iaval != null) {
							value = Integer.parseInt(iaval.getValue());
						} else {
							value = 1; 
						}
						// p= 0
						Assignment ass = incrementVar(arc.getTarget(), value, gf, varMap);
						
						t.getActions().add(ass);
					}
				}


				// handle time constraints : reset this clock after firing
				if (isTimed) {
					if (hasClock(eft,lft)) {

						
						// p= 0
						Assignment ass = assignVarConst(node, 0, gf, varMap);
						
						t.getActions().add(ass);

						// also add a term to resetDisabled
						Ite ite = gf.createIte();
						Not notGuard = gf.createNot();
						notGuard.setValue(computeGuard(node, gf, varMap));
						ite.setCond(notGuard);
						Assignment ass2 = assignVarConst(node, 0, gf, varMap);
						ite.getIfTrue().add(ass2);
						
						reset.getActions().add(ite);
					}

					// reset other disabled transition clocks
					// call(reset)
					Call call = gf.createCall();
					call.setLabel(labReset);
					t.getActions().add(call);


					if (eft==0 && lft==-1) {
						// [0,inf[
						// nop
					} else if (eft==0 && lft==0) {
						// [0,0]
						// no clock, abort elapse if enabled
						Ite ite = gf.createIte();
						ite.setCond(computeGuard(node, gf, varMap));
						ite.getIfTrue().add(gf.createAbort());
						elapse.getActions().add(ite);						
					} else if (lft == -1) {
						// [a,inf[
						// increment clock variable up to a.
						Ite ite = gf.createIte();

						And and = gf.createAnd();
						and.setLeft(computeGuard(node, gf, varMap));

						BooleanExpression comp = createComparison(node, ComparisonOperators.LT, eft, gf, varMap);

						and.setRight(comp);
						// condition is : enabled && clock < eft(t)
						ite.setCond(and);

						// effect if true is :
						// clock= clock+1
						Assignment ass = incrementVar(node, 1, gf, varMap);
						
						ite.getIfTrue().add(ass);

						elapse.getActions().add(ite);
					} else {
						// [a,b] 
						// general case
						// ite(enabled, ite(clock < lft, clock++, abort ), nop )
						Ite ite = gf.createIte();

						ite.setCond(computeGuard(node, gf, varMap));
						
						Ite ite2 = gf.createIte();
						
						BooleanExpression comp = createComparison(node, ComparisonOperators.LT, lft, gf, varMap);	
						// condition is : clock < lft(t)
						ite2.setCond(comp);
						
						// effect if true is :
						// clock= clock+1
						Assignment ass = incrementVar(node, 1, gf, varMap);
						
						ite2.getIfTrue().add(ass);

						// effect if false is abort !
						ite2.getIfFalse().add(gf.createAbort());
						
						ite.getIfTrue().add(ite2);
						
						elapse.getActions().add(ite);
					}
				}

				gal.getTransitions().add(t);
			}
			
		}
		if (isTimed) {
			gal.getTransitions().add(elapse);
			gal.getTransitions().add(reset);
		}

		try {
			SerializationUtil.systemToFile(gal, filePath);
		} catch (FileNotFoundException fe) {
			Logger.getLogger("fr.lip6.move.coloane.core").warning("Echec lors de la création du fichier : Nom de fichier invalide");
			throw new ExtensionException("Invalid filename !");
		} catch (IOException ioe) {
			Logger.getLogger("fr.lip6.move.coloane.core").warning("Erreur lors de l'écriture dans le fichier");
			throw new ExtensionException("Write error :" + ioe.getMessage());
		}
		monitor.done();
	}

	private BooleanExpression computeGuard(INode node, GalFactory gf, Map<INode, Variable> varMap) {
		True tru = gf.createTrue();
		tru.setValue("True");
		BooleanExpression guard = tru ;
		for (IArc arc : node.getIncomingArcs()) {
			String arcType = arc.getArcFormalism().getName(); 
			if ("arc".equals(arcType) || "inhibitor".equals(arcType) || "test".equals(arcType) ) {

				// A ref to the place : p
				INode p = arc.getSource();
				ComparisonOperators op ;
				if ("arc".equals(arcType) || "test".equals(arcType) ) {
					// is greater or equal >=
					op = ComparisonOperators.GE;
				} else if ("inhibitor".equals(arcType) ) {
					// is strictly less than
					op = ComparisonOperators.LT;							
				} else {
					// Should not happen
					op = ComparisonOperators.EQ;
				}
				int val;
				IAttribute iaval = arc.getAttribute("valuation");
				if (iaval != null) {
					val = Integer.parseInt(iaval.getValue());
				} else {
					val = 1;
				}
				
				
				BooleanExpression cmp = createComparison(p, op, val,gf, varMap);				

				if (guard==tru) {
					guard = cmp;
				} else {
					And g2 = gf.createAnd();
					g2.setLeft(guard);
					g2.setRight(cmp);
					guard = g2;
				}
			} else if ("reset".equals(arcType)){
				// no effect on guard
			} else {
				throw new UnsupportedOperationException("unknown arc type!!");
			}
		}
		return guard;
	}
	
	private BooleanExpression createComparison (INode node, ComparisonOperators op, int value, GalFactory gf, Map<INode, Variable> varMap) {
		Comparison cmp = gf.createComparison();
		VariableRef pl = gf.createVariableRef();
		pl.setReferencedVar(varMap.get(node));
		cmp.setLeft(pl);

		cmp.setOperator(op);
		

		// to valuation of arc
		Constant val = gf.createConstant();
		val.setValue(value);
		cmp.setRight(val);
		return cmp;
	}
	
	private Assignment assignVarConst (INode node, int value, GalFactory gf, Map<INode, Variable> varMap) {
		Assignment ass = gf.createAssignment();

		// A ref to the place : p
		VariableRef pl = gf.createVariableRef();
		pl.setReferencedVar(varMap.get(node));
		ass.setLeft(pl);

		Constant c0 = gf.createConstant();
		c0.setValue(value);
		ass.setRight(c0);

		return ass;
	}
	
	private Assignment incrementVar (INode node, int value, GalFactory gf, Map<INode, Variable> varMap) {
		// A ref to the place : p
		VariableRef pl = gf.createVariableRef();
		pl.setReferencedVar(varMap.get(node));

		// p= 0
		Assignment ass = gf.createAssignment();
		ass.setLeft(pl);

		Constant c1 = gf.createConstant();
		c1.setValue(Math.abs(value));

		BinaryIntExpression add = gf.createBinaryIntExpression();
		VariableRef pl2 = gf.createVariableRef();
		pl2.setReferencedVar(varMap.get(node));

		add.setLeft(pl2);
		if (value >= 0) {
			add.setOp("+");
		} else {
			add.setOp("-");
		}
		add.setRight(c1);

		ass.setRight(add);
		return ass;
	}
	
		
	private boolean hasClock(int eft, int lft) {
		// [0,0] and [0,inf[ clocks are discarded
		return eft!=0 ||  (lft!=0 && lft != -1 );
	}

	private int eft(INode node) {
		return Integer.parseInt(node.getAttribute("earliestFiringTime").getValue() );
	}

	private int lft(INode node) {
		String lft = node.getAttribute("latestFiringTime").getValue();
		try {
			return Integer.parseInt(lft);
		} catch (NumberFormatException e) {
			return -1;
		}
	}


}
