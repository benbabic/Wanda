package fr.irit.wanda.dao;

import java.util.ArrayList;

import fr.irit.wanda.entities.DescribedEntity;
import fr.irit.wanda.exception.NotFoundInDatabaseException;

public class DescribedEntityAO extends DAO {

	public DescribedEntityAO(){
		super();
	}
	
	public DescribedEntity add(DescribedEntity de){
		
		return null;
	}
	
	public ArrayList<DescribedEntity> getAll(String tableName) throws NotFoundInDatabaseException{
		ArrayList<DescribedEntity> result = new ArrayList<DescribedEntity>();
		set("SELECT * FROM "+tableName+";");
		if (!executeQuery()) {
			throw new NotFoundInDatabaseException(tableName+" is empty.");
		}
		do{
			result.add(extract(tableName));
		}while(next());
		
		return result;
	}
	
	protected DescribedEntity extract(String tableName) {
		return new DescribedEntity(getInt("id"+tableName+""), tableName,getString("name"),
				getString("description"));
	}
	
	
	
}
