package fr.irit.wanda.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import fr.irit.wanda.entities.Metadata;
import fr.irit.wanda.exception.AlreadyRegistredException;
import fr.irit.wanda.exception.NotFoundInDatabaseException;

public class MetadataAO extends DAO {

	public MetadataAO() {
		super();
	}

	public MetadataAO(ResultSet rs) {
		super();
		this.rs = rs;
	}

	/**
	 * Adds a metadata to the database if it does not already exists. Name is
	 * unique.
	 * 
	 * @throws AlreadyRegistredException
	 *             if the metadata is already registered in database
	 * @param m
	 *            fr.irit.wanda.entities.Metadata the metadata to add
	 * @return database id of the created object or -1 if an error occured
	 */
	public int add(Metadata m) throws AlreadyRegistredException {
		if (exists(m)) // If the metadata already exists: error
			throw new AlreadyRegistredException("Metadata named " + m.getName()
					+ " already is registered in daabase.");

		set("INSERT INTO metadata(name, description, hoover, obligation) VALUES (?,?,?,?);",
				Statement.RETURN_GENERATED_KEYS); // Retrieve the generated id
		setString(1, m.getName());
		setString(2, m.getDescription());
		setString(3, m.getHoover());
		setBoolean(4, m.isObligation());
		executeUpdate();
		getGeneratedKeys();
		m.setId(getInt("idmetadata"));

		if (m.getConcerns() != null)
			new MetadataConcernsAO().add(m.getConcerns(), m);
		return m.getId();
	}

	/*
	 * Extracts a metadata object from the ResultSet containing a full row of
	 * the metadata table. Object returned can be null.
	 */
	protected Metadata extract() {
		return new Metadata(getInt("idmetadata"), getString("name"),
				getBoolean("obligation"), getString("hoover"),
				getString("description"));
	}

	/*
	 * Extracts a metadata object from the ResultSet containing a full row of
	 * the metadata table. Object returned can be null.
	 */
	protected Metadata extract(ResultSet rs) {
		this.rs = rs;
		return extract();
	}

	/**
	 * Returns true if the given metadata is recorded in the database
	 * 
	 * @param m
	 *            Metadata
	 * @return boolean
	 */
	public boolean exists(Metadata m) {
		if (m.getId() != -1)
			return exists(m.getId());
		return exists(m.getName());
	}

	/**
	 * Returns true if the given metadata name is recorded in the database
	 * 
	 * @param name
	 *            the name
	 * @return boolean
	 */
	public boolean exists(String name) {
		set("SELECT idmetadata FROM metadata WHERE name=?;");
		setString(1, name);
		return executeQuery();
	}

	/**
	 * Returns true if the given metadata id is recorded in the database
	 * 
	 * @param id
	 *            the id
	 * @return boolean
	 */
	public boolean exists(int id) {
		set("SELECT idmetadata FROM metadata WHERE idmetadata=?;");
		setInt(1, id);
		return executeQuery();
	}

	/**
	 * Gets the database id of a named metadata
	 * 
	 * @param name
	 *            of the metadata to look for
	 * @return the database metadata id of the named metadata. -1 if not found.
	 * @deprecated
	 */
	private int getId(String name) {
		set("SELECT idmetadata FROM metadata WHERE name=?;");
		setString(1, name);
		executeQuery();
		return getInt("idmetadata");
	}

	/**
	 * Returns the all the metadatas stored in the database
	 * 
	 * @throws NotFoundInDatabaseException
	 *             when there is no metadatas.
	 * @return Collection<Metadata>, may be null when unexpected error occurs.
	 */
	public Collection<Metadata> getAll() throws NotFoundInDatabaseException {
		Collection<Metadata> result = new ArrayList<Metadata>();
		set("SELECT * FROM metadata;");
		if (!executeQuery()) {
			throw new NotFoundInDatabaseException("There are no metadatas.");
		}

		do {
			result.add(extract());
		} while (next());

		return result;
	}

	/**
	 * Returns the metadata object stored in the databese with the given name
	 * 
	 * @param name
	 *            the name of the metadata you are looking fore
	 * @throws NotFoundInDatabaseException
	 *             when the metadata specified by given name does not exist.
	 * @return Metadata object, may be null when unexpected error occurs.
	 */
	public Metadata get(String name) throws NotFoundInDatabaseException {
		set("SELECT * FROM metadata WHERE name=?;");
		setString(1, name);
		if (!executeQuery()) {
			throw new NotFoundInDatabaseException("Metadata " + name
					+ " appears to be inexistant.");
		}
		return extract();
	}

	/**
	 * Returns the metadata object stored in the databese with the given name
	 * 
	 * @param entityName
	 *            the name of the metadata you are looking fore
	 * @return Metadata object, may be null.
	 */
	public Metadata get(int id) throws NotFoundInDatabaseException {
		set("SELECT * FROM metadata WHERE idmetadata=?;");
		setInt(1, id);
		executeQuery();
		Metadata m = extract();
		if (m == null)
			throw new NotFoundInDatabaseException("Metadata " + id
					+ " appears to be inexistant.");
		return m;
	}

	/**
	 * Removes a metadata identified by its database id number.
	 * 
	 * @param id
	 *            id of the metadata to remove
	 * @return true if it was removed
	 */
	public boolean remove(int id) {
		set("DELETE FROM metadata WHERE idmetadata=?;");
		setInt(1, id);
		return executeUpdate() && (new MetadataConcernsAO().remove(id));
	}

	/**
	 * Removes the named metadata.
	 * 
	 * @param name
	 *            the name of the meta to remove
	 * @return true if the object was deleted.
	 */
	@Deprecated
	public boolean remove(String name) {
		int id = getId(name);
		if (id == -1)
			return false;

		MetadataConcernsAO mcao = new MetadataConcernsAO();
		boolean b = true;

		if (mcao.hasConcerns(id))
			b = mcao.remove(id);
		set("DELETE FROM metadata WHERE name=?;");
		setString(1, name);
		return executeUpdate() && b;
	}
}
