package fr.irit.wanda.dao;

import java.util.ArrayList;
import java.util.Collection;

import fr.irit.wanda.entities.Entity;
import fr.irit.wanda.entities.Metadata;
import fr.irit.wanda.exception.NotFoundInDatabaseException;

public class MetadataConcernsAO extends DAO {

	public MetadataConcernsAO() {
		super();
	}

	/**
	 * Adds concerned entities to a Metadata
	 * 
	 * @param c
	 *            collection of entities's to add
	 * @param m
	 *            metadata
	 */
	public void add(Collection<Entity> c, Metadata m) {
		for (Entity e : c) {
			add(e, m);
		}
	}

	/**
	 * Adds concerned entities to a Metadata
	 * 
	 * @param e
	 *            a single entity to add
	 * @param m
	 *            metadata
	 */
	public void add(Entity e, Metadata m) {
		set("INSERT INTO metaconcerns(idmetadata,concerns) VALUES (?,?);");
		setInt(1, m.getId());
		setString(2, e.getEntityName());
		executeUpdate();
	}

	/**
	 * Returns the metadata that are concerning the entity
	 * 
	 * @param e
	 *            the entity
	 * @return Collection<Metadata> collection of the entity metadata
	 */
	public Collection<Metadata> get(Entity e)
			throws NotFoundInDatabaseException {
		Collection<Metadata> c = new ArrayList<Metadata>();
		set("SELECT idmetadata FROM metaconcerns WHERE concerns=?;");
		setString(1, e.getEntityName());
		if (!executeQuery())
			throw new NotFoundInDatabaseException(e.getEntityName()
					+ " is not concerned");
		MetadataAO mao = new MetadataAO(rs);

		do {
			c.add(mao.get(getInt("idmetadata")));
		} while (next());

		return c;
	}

	/**
	 * Returns the metadata concerned entities names
	 * 
	 * @param m
	 *            the metadata
	 * @return Collection<String> collection of entities names
	 */
	public Collection<String> get(Metadata m) {
		Collection<String> c = new ArrayList<String>();
		set("SELECT concerns FROM metaconcerns WHERE idmetadata=?;");
		setInt(1, m.getId());
		executeQuery();
		do {
			c.add(getString("concerns"));
		} while (next());

		return c;
	}

	/**
	 * Tells if a Metadata concerns any table.
	 * 
	 * @param idM
	 *            Metadata id
	 * @return boolean true if this meta has concerns
	 */
	public boolean hasConcerns(int idM) {
		set("SELECT idmetadata FROM metaconcerns WHERE idmetadata=?;");
		setInt(1, idM);
		return executeQuery();
	}

	/**
	 * Tells if a Metadata concerns any table.
	 * 
	 * @param m
	 *            Metadata
	 * @return boolean true if this meta has concerns
	 */
	public boolean hasConcerns(Metadata m) {
		return hasConcerns(m.getId());
	}

	/**
	 * Tells if the named entity is concerned by the identified metadata
	 * 
	 * @param idM
	 *            metadata id
	 * @param name
	 *            name of the entity
	 * @return boolean true if the entity is concerned by the metadata
	 */
	public boolean isConcerned(int idM, String name) {
		set("SELECT idmetadata FROM metaconcerns WHERE idmetadata=? AND concerns=?;");
		setInt(1, idM);
		setString(2, name);
		return executeQuery();
	}

	/**
	 * Tells if the entity is concerned by the metadata
	 * 
	 * @param m
	 *            metadata
	 * @param e
	 *            entity
	 * @return boolean true if the entity is concerned by the metadata
	 */
	public boolean isConcerned(Metadata m, Entity e) {
		return isConcerned(m.getId(), e.getEntityName());
	}
	
	/**
	 * Removes a metadata and all it concerns from the database
	 * 
	 * @param idM
	 *            id of the metadata
	 * @return true if the removal succeed
	 */
	public boolean remove(int idM) {
		set("DELETE FROM metaconcerns WHERE idmetadata=?;");
		setInt(1, idM);
		return executeUpdate();
	}

	/**
	 * Removes a metadata's concerns metadata from the database.
	 * 
	 * @param idM
	 *            id of the metadata
	 * @return true if the removal succeed
	 */
	public boolean remove(String toDelete, int idM) {
		set("DELETE FROM metaconcerns WHERE idmetadata=? AND concerns=?;");
		setInt(1, idM);
		setString(2, toDelete);
		return executeUpdate();
	}
}
