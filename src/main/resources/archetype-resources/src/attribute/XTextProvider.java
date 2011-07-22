package attribute;

import attribute.ui.internal.LanguageActivator;

import com.google.inject.Injector;

import fr.lip6.move.coloane.interfaces.formalism.IXtextProvider;

public class XTextProvider implements IXtextProvider{

	public Injector getInjector(){
		Injector i = LanguageActivator.getInstance().getInjector("attribute.Language");
		return i;
	}
	
}
