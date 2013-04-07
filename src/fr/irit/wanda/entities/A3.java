package fr.irit.wanda.entities;

public class A3 extends Entity {

	private String name;
	private String uri;
	private String description;
	
	public A3(int certificate, String name, String uri, String description) {
		super(certificate, "a3");
		this.name = name;
		this.uri = uri;
		this.description = description;
	}
	
	public A3(String name, String uri, Role role, String description) {
		super(-1, "a3");
		this.name = name;
		this.uri = uri;
		this.description = description;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the uri
	 */
	public String getUri() {
		return uri;
	}
	
	/**
	 * @param uri
	 *            the uri to set
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
