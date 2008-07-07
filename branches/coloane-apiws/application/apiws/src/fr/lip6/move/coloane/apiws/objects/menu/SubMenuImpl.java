package fr.lip6.move.coloane.apiws.objects.menu;

import java.util.ArrayList;

import fr.lip6.move.coloane.apiws.interfaces.objects.menu.IItemMenu;
import fr.lip6.move.coloane.apiws.interfaces.objects.menu.IOptionMenu;
import fr.lip6.move.coloane.apiws.interfaces.objects.menu.ISubMenu;
import fr.lip6.move.wrapper.ws.WrapperStub.SubMenu;

public class SubMenuImpl extends ElementMenuImpl implements ISubMenu {

	private ArrayList<ISubMenu> subMenus;

	private ArrayList<IOptionMenu> options;

	private ArrayList<IItemMenu> items;


	protected SubMenuImpl(SubMenu question) {
		super(question);

		this.options  = new ArrayList<IOptionMenu>();
		this.subMenus = new ArrayList<ISubMenu>();
		this.items = new ArrayList<IItemMenu>();

		if (question.getOption() != null){
			for (int i=0; i<question.getOption().length;i++)
				this.options.add(new OptionMenuImpl(question.getOption()[i]));
		}
		
		if (question.getSubMenus() != null){
			for (int i=0; i<question.getSubMenus().length;i++)
				this.subMenus.add(new SubMenuImpl(question.getSubMenus()[i]));
		}

		if (question.getServices() != null){
			for (int i=0; i<question.getServices().length;i++)
				this.items.add(new ItemMenuImpl(question.getServices()[i]));
		}

		if (question.getServicesWithObjects() != null){
			for (int i=0; i<question.getServicesWithObjects().length;i++)
				this.items.add(new ItemMenuImpl(question.getServicesWithObjects()[i]));
		}

		if (question.getServiceWithTexts() != null){
			for (int i=0; i<question.getServiceWithTexts().length;i++)
				this.items.add(new ItemMenuImpl(question.getServiceWithTexts()[i]));
		}

		if (question.getServicesWithOneObject() != null){
			for (int i=0; i<question.getServicesWithOneObject().length;i++)
				this.items.add(new ItemMenuImpl(question.getServicesWithOneObject()[i]));
		}

		if (question.getServiceWithOneText() != null){
			for (int i=0; i<question.getServiceWithOneText().length;i++)
				this.items.add(new ItemMenuImpl(question.getServiceWithOneText()[i]));
		}

	}

	public ArrayList<ISubMenu> getSubMenus(){
		return subMenus;
	}

	public ArrayList<IOptionMenu> getOption() {
		return options;
	}
	
	public ArrayList<IItemMenu> getItems() {
		return items;
	}
	
}
