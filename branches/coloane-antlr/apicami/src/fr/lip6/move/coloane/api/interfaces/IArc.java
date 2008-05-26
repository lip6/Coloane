package fr.lip6.move.coloane.api.interfaces;

import java.util.ArrayList;
import java.util.Vector;

/**
 * cette interface decrit un arc.
 * @author KAHOO & UU
 *
 */
public interface IArc {

	public String getArcType() ;


	public int getId() ;


	public int getStartingNode() ;


	public int getEndingNode() ;


	public  void addAttribute(IAttribute attribute) ;


	public Vector<IAttribute> getListOfAttr();


	}

