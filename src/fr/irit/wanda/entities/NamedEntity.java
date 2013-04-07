package fr.irit.wanda.entities;

public class NamedEntity extends Entity {
	
	protected String name = null;
	
	public NamedEntity(String entityName, String name) {
		super(entityName);
		this.name = name;
	}

	public NamedEntity(int id, String entityName, String name) {
		super(id, entityName);
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}
