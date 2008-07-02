package fr.lip6.move.coloane.apiws.objects.menu;

import java.util.ArrayList;

import fr.lip6.move.coloane.apiws.interfaces.objects.menu.IQuestion;
import fr.lip6.move.wrapper.ws.WrapperStub.Question;

public abstract class QuestionImpl implements IQuestion {
	
    private String name;
    
    private int cardinality;
    
    private boolean validation;
    
    private boolean interaction;
    
    private boolean stop;
    
    private String domain;
    
    private boolean visibility;
    
    private ArrayList<String> helps;
    
    protected QuestionImpl(Question question){
        this.name=question.getName();
        this.cardinality=question.getCardinality();
        this.validation=question.getValidation();
        this.interaction=question.getInteraction();
        this.stop=question.getStop();
        this.domain=question.getDomain();
        this.visibility=question.getVisibility();
        
        this.helps = new ArrayList<String>();
        if (question.getHelps() != null){
        	for (int i=0; i<question.getHelps().length;i++){
        		this.helps.add(question.getHelps()[i]);
        	}
        }
    }

	public int getCardinality() {
		return cardinality;
	}

	public String getDomain() {
		return domain;
	}

	public ArrayList<String> getHelps() {
		return helps;
	}

	public String getName() {
		return name;
	}

	public boolean isInteraction() {
		return interaction;
	}

	public boolean isStop() {
		return stop;
	}

	public boolean isValidation() {
		return validation;
	}

	public boolean isVisibility() {
		return visibility;
	}
	
}
