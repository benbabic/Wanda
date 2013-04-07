package fr.irit.wanda.dao;

import java.sql.ResultSet;
import java.util.ArrayList;

import fr.irit.wanda.entities.Entity;
import fr.irit.wanda.entities.NamedEntity;
import fr.irit.wanda.entities.Role;
import fr.irit.wanda.entities.User;

public class NamedEntityAO extends DAO {
	public NamedEntityAO() {
		super();
	}
	
	public NamedEntityAO(ResultSet rs) {
		super();
		this.rs=rs;
	}
	
	public ArrayList<NamedEntity> getAll(Entity e, User u) {
		ArrayList<NamedEntity> namedEntities = new ArrayList<NamedEntity>();
		set("SELECT * FROM "+e.getEntityName()+", user"+e.getEntityName()+"access " +
				"WHERE "+e.getEntityName()+".id"+e.getEntityName()+"=? " +
				"AND user"+e.getEntityName()+"access."+e.getEntityName()+"=?;");
		setString(1, "user"+e.getEntityName()+"access.wanda_user");
		setInt(2, u.getId());
		executeQuery();
		do {
			NamedEntity ne = new NamedEntity(getInt("id"+e.getEntityName()),e.getEntityName(),getString("name"));
			namedEntities.add(ne);
		} while(next());
		
		return namedEntities;
	}
	
	//TODO 
	public ArrayList<NamedEntity> getAllContainers(Entity e, User u) {
		ArrayList<NamedEntity> namedEntities = new ArrayList<NamedEntity>();
		set("SELECT * FROM "+e.getEntityName()+", user"+e.getEntityName()+"access " +
				"WHERE "+e.getEntityName()+".id"+e.getEntityName()+"=? " +
				"AND user"+e.getEntityName()+"access."+e.getEntityName()+"=?");
		setString(1, "user"+e.getEntityName()+"access.wanda_user");
		setInt(2, u.getId());
		executeQuery();
		do {
			NamedEntity ne = new NamedEntity(getInt("id"+e.getEntityName()),e.getEntityName(),getString("name"));
			namedEntities.add(ne);
		} while(next());
		
		return namedEntities;
	}
	
	public NamedEntity getID(NamedEntity ne){
		System.out.println(ne.getEntityName()+" "+ne.getName());
		set("SELECT * FROM "+ne.getEntityName()+" WHERE name=?;");
		setString(1,ne.getName());
		executeQuery();
		ne.setId(getInt("id"+ne.getEntityName()));
		return ne;
	}
}
