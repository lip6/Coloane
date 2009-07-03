package fr.lip6.move.coloane.tools.stats;


import java.util.ArrayList;
import java.util.List;

import fr.lip6.move.coloane.interfaces.model.IGraph;
import fr.lip6.move.coloane.interfaces.model.command.ICommand;
import fr.lip6.move.coloane.interfaces.objects.result.IResult;
import fr.lip6.move.coloane.interfaces.objects.result.ISubResult;
import fr.lip6.move.coloane.interfaces.objects.result.ITip;

public class MyResultMenu implements IResult {
	/** Le nom du menu racine qui contient le service invoqué */
	private String rootName;

	/** Le nom du service qui a été invoqué */
	private String serviceName;
	
	/** Liste des résultats */
	private List<MyResult> myResultsList;
	
	/** Liste des menus résultats */
	private List<MyResultMenu> myResultsMenusList;
	
	public MyResultMenu(String rootName, String serviceName){
		this.rootName = rootName;
		this.serviceName = serviceName;
		this.myResultsMenusList = new ArrayList<MyResultMenu>();
		this.myResultsList = new ArrayList<MyResult>();
	}
	
	public void addMyResult(MyResult myResult) {
		this.myResultsList.add(myResult);
	}
	
	public void addMyResultMenu(MyResultMenu myResultMenu) {
		this.myResultsMenusList.add(myResultMenu);
	}
	
	/*
	public List<MyResult> getMyResultsList() {
		for(int i=0;i<)
	}
	*/
	
	public void myToString() {
		this.myToStringBis(0);
	}
	
	private void myToStringBis(int decalage) {
		System.out.println(this.rootName);
		for(MyResultMenu menu : this.myResultsMenusList) {
			for(int i=0;i<decalage;i++)
				System.out.print("|");
			System.out.print("|-");
			menu.myToStringBis(decalage+1);
			
		}
		
		for(MyResult result : this.myResultsList) {
			for(int i=0;i<decalage;i++)
				System.out.print("|");
			System.out.print("|-");
			result.myToString();
		}
	}
	
	
	
	
	
	
	
	
	public List<ICommand> getModificationsOnCurrentGraph() {
		// TODO Auto-generated method stub
		return null;
	}

	public IGraph getNewGraph() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getRootName() {
		return this.rootName;
	}

	/**
	 * {@inheritDoc}
	 */
	public final String getServiceName() {
		return this.serviceName;
	}

	public List<ISubResult> getSubResults() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ITip> getTipsList() {
		// TODO Auto-generated method stub
		return null;
	}

}
