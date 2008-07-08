package fr.lip6.move.coloane.apiws.objects.menu;

import java.util.ArrayList;

import fr.lip6.move.coloane.apiws.interfaces.objects.menu.IOptionItem;
import fr.lip6.move.coloane.apiws.interfaces.objects.menu.IServiceSimpleItem;
import fr.lip6.move.coloane.apiws.interfaces.objects.menu.IServiceWithObjectsItem;
import fr.lip6.move.coloane.apiws.interfaces.objects.menu.IServiceWithOneObjectItem;
import fr.lip6.move.coloane.apiws.interfaces.objects.menu.IServiceWithOneTextItem;
import fr.lip6.move.coloane.apiws.interfaces.objects.menu.IServiceWithTextsItem;
import fr.lip6.move.coloane.apiws.interfaces.objects.menu.ISubMenu;
import fr.lip6.move.wrapper.ws.WrapperStub.SubMenu;

public class SubMenuImpl extends ItemMenuImpl implements ISubMenu {

	private ArrayList<ISubMenu> subMenus;

	private ArrayList<IOptionItem> options;
	
	private ArrayList<IServiceSimpleItem> servicesSimpleItems;
	
	private ArrayList<IServiceWithObjectsItem> servicesWithObjectsItems;
	
	private ArrayList<IServiceWithOneObjectItem> servicesWithOneObjectItems;
	
	private ArrayList<IServiceWithOneTextItem> servicesWithOneTextItems;
	
	private ArrayList<IServiceWithTextsItem> servicesWithTextsItems;

	protected SubMenuImpl(SubMenu question) {
		super(question);

		this.options  = new ArrayList<IOptionItem>();
		this.subMenus = new ArrayList<ISubMenu>();
		this.servicesSimpleItems = new ArrayList<IServiceSimpleItem>();
		this.servicesWithObjectsItems = new ArrayList<IServiceWithObjectsItem>();
		this.servicesWithOneObjectItems = new ArrayList<IServiceWithOneObjectItem>();
		this.servicesWithOneTextItems = new ArrayList<IServiceWithOneTextItem>();
		this.servicesWithTextsItems = new ArrayList<IServiceWithTextsItem>();
		

		if (question.getOption() != null){
			for (int i=0; i<question.getOption().length;i++)
				this.options.add(new OptionItemImpl(question.getOption()[i]));
		}
		
		if (question.getSubMenus() != null){
			for (int i=0; i<question.getSubMenus().length;i++)
				this.subMenus.add(new SubMenuImpl(question.getSubMenus()[i]));
		}

		if (question.getServices() != null){
			for (int i=0; i<question.getServices().length;i++)
				this.servicesSimpleItems.add(new ServiceSimpleItemImpl(question.getServices()[i]));
		}

		if (question.getServicesWithObjects() != null){
			for (int i=0; i<question.getServicesWithObjects().length;i++)
				this.servicesWithObjectsItems.add(new ServiceWithObjectsItemImpl(question.getServicesWithObjects()[i]));
		}

		if (question.getServiceWithTexts() != null){
			for (int i=0; i<question.getServiceWithTexts().length;i++)
				this.servicesWithTextsItems.add(new ServiceWithTextsItemImpl(question.getServiceWithTexts()[i]));
		}

		if (question.getServicesWithOneObject() != null){
			for (int i=0; i<question.getServicesWithOneObject().length;i++)
				this.servicesWithOneObjectItems.add(new ServiceWithOneObjectItemImpl(question.getServicesWithOneObject()[i]));
		}

		if (question.getServiceWithOneText() != null){
			for (int i=0; i<question.getServiceWithOneText().length;i++)
				this.servicesWithOneTextItems.add(new ServiceWithOneTextItemImpl(question.getServiceWithOneText()[i]));
		}

	}

	public ArrayList<ISubMenu> getSubMenus(){
		return subMenus;
	}

	public ArrayList<IOptionItem> getOptions() {
		return options;
	}

	public ArrayList<IServiceSimpleItem> getServiceSimpleItems() {
		return servicesSimpleItems;
	}

	public ArrayList<IServiceWithObjectsItem> getServiceWithObjectsItems() {
		return servicesWithObjectsItems;
	}

	public ArrayList<IServiceWithOneObjectItem> getServiceWithOneObjectItems() {
		return servicesWithOneObjectItems;
	}

	public ArrayList<IServiceWithOneTextItem> getServiceWithOneTextItems() {
		return servicesWithOneTextItems;
	}

	public ArrayList<IServiceWithTextsItem> getServiceWithTextsItems() {
		return servicesWithTextsItems;
	}
	
}
