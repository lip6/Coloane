package fr.lip6.move.coloane.model;

import fr.lip6.move.coloane.interfaces.IModelCom;

public interface IModel extends IModelCom {
	
	public void setFormalism(String formalism);
	
	public String getFormalism();
	
	public void addAttribute(Attribute attribute);
	
	public int getListOfNodeSize();
	
	public int getListOfArcSize();
	
	public int getListOfAttrSize();
	
	public Node getNthNode(int index);
	
	public Arc getNthArc(int index);
	
	public Attribute getNthAttr(int index);
	
	public void addNode(Node node);
	
	public void addArc(Arc arc);
	
	public void removeNode(Node node);

}
