package parser;

import org.xtext.example.mydsl.ui.internal.MyDslActivator;

import com.google.inject.Injector;

import fr.lip6.move.coloane.interfaces.formalism.IXtextProvider;

public class XTextProvider implements IXtextProvider{

	public Injector getInjector(){
		return MyDslActivator.getInstance().getInjector("org.xtext.example.mydsl.MyDsl");
	}
	
}
