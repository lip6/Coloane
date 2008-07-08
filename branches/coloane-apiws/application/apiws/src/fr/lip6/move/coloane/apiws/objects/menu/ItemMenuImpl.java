package fr.lip6.move.coloane.apiws.objects.menu;

import java.util.ArrayList;

import fr.lip6.move.coloane.apiws.interfaces.objects.menu.IItemMenu;
import fr.lip6.move.wrapper.ws.WrapperStub.Question;

public abstract class ItemMenuImpl implements IItemMenu{

	private String name;
    
    private boolean visibility;
    
    private boolean validation;
    
    private ArrayList<String> helps;
    
    protected ItemMenuImpl(Question question){
        this.name=question.getName();
        this.visibility=question.getVisibility();
        this.validation=question.getValidation();
        this.helps = new ArrayList<String>();
        if (question.getHelps() != null){
        	for (int i=0; i<question.getHelps().length;i++){
        		this.helps.add(question.getHelps()[i]);
        	}
        }
    }

	public String getName() {
		return name;
	}

	public boolean isVisibility() {
		return visibility;
	}
	
	public boolean isValidation(){
		return validation;
	}

	public ArrayList<String> getHelps() {
		return helps;
	}

}
