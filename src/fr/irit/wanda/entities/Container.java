package fr.irit.wanda.entities;

import java.util.Collection;

public class Container extends NamedEntity {
	protected Collection<Entity> content = null;
	
	public Container(int id, String entityName ,String name){
		super(id,entityName,name);		
	}
	
	/**
	 * @return the content
	 */
	public Collection<Entity> getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(Collection<Entity> content) {
		this.content = content;
	}	
}
