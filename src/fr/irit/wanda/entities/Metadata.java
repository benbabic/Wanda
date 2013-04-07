package fr.irit.wanda.entities;

import java.util.Collection;

/**
 * This object is used to represent a metadata. It id may be null if the object
 * is not in the database. It does not contain the value of the given meta for
 * any singular objet but only the informations on the metadata itself. It is
 * not linked to any other existing object.
 * 
 * @author Benjamin Babic
 */
public class Metadata {
	protected String name;
	protected String description;
	protected String hoover;
	protected int id;
	protected boolean obligation;
	protected boolean visibility; // for future implementation
	protected Collection<Entity> concerns = null;

	/**
	 * Constructor used by MetadataDAO for creating a metadata object from the
	 * database
	 * 
	 * @param id
	 *            database id for this object
	 * @param name
	 *            the name
	 * @param obligation
	 *            true if this meta is essential
	 * @param hoover
	 *            text displayed when mouse hoovers the metadata
	 * @param description
	 *            detailed description of what is this metadata used for
	 */
	public Metadata(int id, String name, boolean obligation, String hoover,
			String description) {
		this.name = name;
		this.description = description;
		this.hoover = hoover;
		this.id = id;
		this.obligation = obligation;
	}

	/**
	 * Default constructor used to create a metadata object.
	 * 
	 * @param name
	 *            the name
	 * @param obligation
	 *            true if this meta is essential
	 * @param hoover
	 *            text displayed when mouse hoovers the metadata
	 * @param description
	 *            detailed description of what is this metadata used for
	 */
	public Metadata(String name, boolean obligation, String hoover,
			String description) {
		this.name = name;
		this.description = description;
		this.hoover = hoover;
		this.id = -1;
		this.obligation = obligation;
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

	/**
	 * @return the hoover
	 */
	public String getHoover() {
		return hoover;
	}

	/**
	 * @param hoover
	 *            the hoover to set
	 */
	public void setHoover(String hoover) {
		this.hoover = hoover;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the obligation
	 */
	public boolean isObligation() {
		return obligation;
	}

	/**
	 * @param obligation
	 *            the obligation to set
	 */
	public void setObligation(boolean obligation) {
		this.obligation = obligation;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Metadata [name=" + name + ", description=" + description
				+ ", obligation=" + obligation + "]";
	}

	/**
	 * @return the concerns
	 */
	public Collection<Entity> getConcerns() {
		return concerns;
	}

	/**
	 * @param concerns
	 *            the concerns to set
	 */
	public void setConcerns(Collection<Entity> concerns) {
		this.concerns = concerns;
	}

	/**
	 * @return the visibility
	 */
	public boolean isVisibility() {
		return visibility;
	}

	/**
	 * @param visibility
	 *            the visibility to set
	 */
	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}
}
