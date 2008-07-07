package fr.lip6.move.coloane.apiws.objects.service;

import java.util.Hashtable;

import fr.lip6.move.coloane.apiws.interfaces.objects.service.IQuestion;
import fr.lip6.move.coloane.apiws.interfaces.objects.service.IServicesAvailable;
import fr.lip6.move.wrapper.ws.WrapperStub.MMenu;
import fr.lip6.move.wrapper.ws.WrapperStub.Option;
import fr.lip6.move.wrapper.ws.WrapperStub.Question;
import fr.lip6.move.wrapper.ws.WrapperStub.Service;
import fr.lip6.move.wrapper.ws.WrapperStub.ServiceWithObjects;
import fr.lip6.move.wrapper.ws.WrapperStub.ServiceWithOneObject;
import fr.lip6.move.wrapper.ws.WrapperStub.ServiceWithOneText;
import fr.lip6.move.wrapper.ws.WrapperStub.ServiceWithTexts;
import fr.lip6.move.wrapper.ws.WrapperStub.SubMenu;

public class ServicesAvailableImpl implements IServicesAvailable {

	private Hashtable<String, IQuestion> services;

	private Hashtable<String, IQuestion> options;


	public ServicesAvailableImpl(MMenu m){
		this.services = new Hashtable<String, IQuestion>();
		this.options = new Hashtable<String, IQuestion>();
		if (m.getRoots() != null)
			for (int i=0; i<m.getRoots().length;i++){
				addQuestion(m.getRoots()[i].getRoot());
			}
	}

	public Hashtable<String, IQuestion> getServices() {
		return services;
	}

	public Hashtable<String, IQuestion> getOptions() {
		return options;
	}

	private void addQuestion(Question q){
		if (q instanceof SubMenu){
			SubMenu sub = (SubMenu) q;

			if (sub.getOption() != null){
				for (int i=0;i<sub.getOption().length;i++)
					addQuestion(sub.getOption()[i]);
			}
			
			if (sub.getServices() != null){
				for (int i=0;i<sub.getServices().length;i++)
					addQuestion(sub.getServices()[i]);
			}

			if (sub.getServicesWithObjects() != null){
				for (int i=0;i<sub.getServicesWithObjects().length;i++)
					addQuestion(sub.getServicesWithObjects()[i]);
			}

			if (sub.getServicesWithOneObject() != null){
				for (int i=0;i<sub.getServicesWithOneObject().length;i++)
					addQuestion(sub.getServicesWithOneObject()[i]);
			}

			if (sub.getServiceWithOneText() != null){
				for (int i=0;i<sub.getServiceWithOneText().length;i++)
					addQuestion(sub.getServiceWithOneText()[i]);
			}

			if (sub.getServiceWithTexts() != null){
				for (int i=0;i<sub.getServiceWithTexts().length;i++)
					addQuestion(sub.getServiceWithTexts()[i]);
			}

			if (sub.getSubMenus() != null){
				for (int i=0;i<sub.getSubMenus().length;i++)
					addQuestion(sub.getSubMenus()[i]);
			}
		}

		if (q instanceof Option){
			options.put(q.getName(), new OptionImpl(q));
		}
		if (q instanceof Service){
			services.put(q.getName(), new ServiceSimpleImpl(q));
		}
		if (q instanceof ServiceWithOneObject){
			services.put(q.getName(), new ServiceWithOneObjectImpl(q));
		}
		if (q instanceof ServiceWithObjects){
			services.put(q.getName(), new ServiceWithObjectsImpl(q));
		}
		if (q instanceof ServiceWithOneText){
			services.put(q.getName(), new ServiceWithOneTextImpl(q));
		}
		if (q instanceof ServiceWithTexts){
			services.put(q.getName(), new ServiceWithTextsImpl(q));
		}
	}

}
