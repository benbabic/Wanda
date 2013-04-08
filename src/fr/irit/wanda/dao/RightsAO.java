package fr.irit.wanda.dao;

import fr.irit.wanda.entities.NamedEntity;
import fr.irit.wanda.entities.Rights;
import fr.irit.wanda.entities.User;

public class RightsAO extends DAO{
	
	public RightsAO(){
		super();
	}
	
	public int getRights(NamedEntity accessed, User accessor){
		String accessed_name = accessed.getEntityName();
		String table_name = "user"+accessed_name+"access";
		set("SELECT rights FROM "+table_name+" WHERE wanda_user=? AND "+accessed_name+"=?;");
		setInt(1,accessor.getId());
		setInt(2,accessed.getId());
		if (!executeQuery()) return Rights.DEFAULT;
		
		return getInt("rights");
	}

}
