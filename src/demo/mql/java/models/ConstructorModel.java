package demo.mql.java.models;

import java.util.HashMap;

import demo.mql.java.enums.EModificator;
import demo.mql.java.enums.Visibility;

public class ConstructorModel extends ECModel{
	
	public ConstructorModel(long id) {
		super(id);
	}

	public ConstructorModel(long id, Visibility visibility, EModificator modifier, HashMap<String, String> parameters) {
		super(id, visibility, modifier, parameters);
	}




	
	

	
}
