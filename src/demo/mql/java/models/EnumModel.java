package demo.mql.java.models;

import java.util.List;

import demo.mql.java.enums.EModificator;
import demo.mql.java.enums.Visibility;


public class EnumModel extends EModel{
	


	private List<InterfaceModel> implementedEnums;

	
	public EnumModel(long id) {
		super(id);
	}


	public EnumModel(long id, String name, String simpleName,  Visibility visibility,
			EModificator modificator,
			List<InterfaceModel> implementedEnums) {
		super(id, name, simpleName, visibility, modificator);
		this.implementedEnums = implementedEnums;
	}


	public List<InterfaceModel> getImplementedEnums() {
		return implementedEnums;
	}


	public void setImplementedEnums(List<InterfaceModel> implementedEnums) {
		this.implementedEnums = implementedEnums;
	}


}
