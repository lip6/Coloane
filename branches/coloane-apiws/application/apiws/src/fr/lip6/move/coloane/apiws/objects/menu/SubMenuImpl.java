package fr.lip6.move.coloane.apiws.objects.menu;

import java.util.ArrayList;

import fr.lip6.move.coloane.apiws.interfaces.objects.menu.IOption;
import fr.lip6.move.coloane.apiws.interfaces.objects.menu.IService;
import fr.lip6.move.coloane.apiws.interfaces.objects.menu.IServiceWithObjects;
import fr.lip6.move.coloane.apiws.interfaces.objects.menu.IServiceWithOneObject;
import fr.lip6.move.coloane.apiws.interfaces.objects.menu.IServiceWithOneText;
import fr.lip6.move.coloane.apiws.interfaces.objects.menu.IServiceWithTexts;
import fr.lip6.move.coloane.apiws.interfaces.objects.menu.ISubMenu;
import fr.lip6.move.wrapper.ws.WrapperStub.SubMenu;

public class SubMenuImpl extends fr.lip6.move.coloane.apiws.objects.menu.QuestionImpl implements ISubMenu {

	private ArrayList<ISubMenu> sms;

	private ArrayList<IOption> ops;

	private ArrayList<IService> sss;

	private ArrayList<IServiceWithOneObject> soos;

	private ArrayList<IServiceWithObjects> sos;

	private ArrayList<IServiceWithOneText> sots;

	private ArrayList<IServiceWithTexts> sts;

	protected SubMenuImpl(SubMenu question) {
		super(question);

		this.ops  = new ArrayList<IOption>();

		if (question.getOption() != null){
			for (int i=0; i<question.getOption().length;i++)
				this.ops.add(new OptionImpl(question.getOption()[i]));
		}

		if (question.getSubMenus() != null){
			this.sms = new ArrayList<ISubMenu>();
			for (int i=0; i<question.getSubMenus().length;i++)
				this.sms.add(new SubMenuImpl(question.getSubMenus()[i]));
		}

		if (question.getServices() != null){
			this.sss = new ArrayList<IService>();
			for (int i=0; i<question.getServices().length;i++)
				this.sss.add(new ServiceImpl(question.getServices()[i]));
		}

		if (question.getServicesWithObjects() != null){
			this. sos = new ArrayList<IServiceWithObjects>();
			for (int i=0; i<question.getServicesWithObjects().length;i++)
				this.sos.add(new ServiceWithObjectsImpl(question.getServicesWithObjects()[i]));
		}

		if (question.getServiceWithTexts() != null){
			this. sts = new ArrayList<IServiceWithTexts>();
			for (int i=0; i<question.getServiceWithTexts().length;i++)
				this.sts.add(new ServiceWithTextsImpl(question.getServiceWithTexts()[i]));
		}

		if (question.getServicesWithOneObject() != null){
			this. soos = new ArrayList<IServiceWithOneObject>();
			for (int i=0; i<question.getServicesWithOneObject().length;i++)
				this.soos.add(new ServiceWithOneObjectImpl(question.getServicesWithOneObject()[i]));
		}

		if (question.getServiceWithOneText() != null){
			this.sots = new ArrayList<IServiceWithOneText>();
			for (int i=0; i<question.getServiceWithOneText().length;i++)
				this.sots.add(new ServiceWithOneTextImpl(question.getServiceWithOneText()[i]));
		}

	}

	public ArrayList<ISubMenu> getSubMenus(){
		return sms;
	}

	public ArrayList<IOption> getOption() {
		return ops;
	}

	public ArrayList<IServiceWithOneText> getServiceWithOneText() {
		return sots;
	}

	public ArrayList<IServiceWithTexts> getServiceWithTexts() {
		return sts;
	}

	public ArrayList<IService> getServices() {
		return sss;
	}
	public ArrayList<IServiceWithObjects> getServicesWithObjects() {
		return sos;
	}

	public ArrayList<IServiceWithOneObject> getServicesWithOneObject() {
		return soos;
	}
}
