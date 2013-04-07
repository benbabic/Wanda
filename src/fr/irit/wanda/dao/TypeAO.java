package fr.irit.wanda.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import fr.irit.wanda.entities.Type;
import fr.irit.wanda.exception.AlreadyRegistredException;
import fr.irit.wanda.exception.NotFoundInDatabaseException;

public class TypeAO extends DAO {

	public TypeAO() {
		super();
	}

	public TypeAO(ResultSet rs) {
		super();
		this.rs = rs;
	}

	/**
	 * Adds a type to the database if it does not already exists. Name is
	 * unique.
	 * 
	 * @throws AlreadyRegistredException
	 *             if the type is already registered in database
	 * 
	 * @return database id of the created object or -1 if an error occured
	 */
	public int add(Type t) throws AlreadyRegistredException {
		if (exists(t)) // If the type already exists: error
			throw new AlreadyRegistredException("Type named " + t.getName()
					+ " already is registered in database.");

		set("INSERT INTO type(name, description) VALUES (?,?);",
				Statement.RETURN_GENERATED_KEYS); // Retrieve the generated id
		setString(1, t.getName());
		setString(2, t.getDescription());
		executeUpdate();
		getGeneratedKeys();
		t.setId(getInt("idtype"));
		return t.getId();
	}

	/*
	 * Extracts a type object. Object returned can be null.
	 */
	protected Type extract() {
		return new Type(getInt("idtype"), getString("name"),
				getString("description"));
	}

	/*
	 * Extracts a type object from the ResultSet containing a full row of the
	 * type table. Object returned can be null.
	 */
	protected Type extract(ResultSet rs) {
		this.rs = rs;
		return extract();
	}

	/**
	 * Returns true if the given type is recorded in the database
	 * 
	 * @param t
	 *            type
	 * @return boolean
	 */
	public boolean exists(Type t) {
		if (t.getId() != -1)
			return exists(t.getId());
		return exists(t.getName());
	}

	/**
	 * Returns true if the given type name is recorded in the database
	 * 
	 * @param name
	 *            the name
	 * @return boolean
	 */
	public boolean exists(String name) {
		set("SELECT idtype FROM type WHERE name=?;");
		setString(1, name);
		return executeQuery();
	}

	/**
	 * Returns true if the given type id is recorded in the database
	 * 
	 * @param id
	 *            the id
	 * @return boolean
	 */
	public boolean exists(int id) {
		set("SELECT idtype FROM type WHERE idtype=?;");
		setInt(1, id);
		return executeQuery();
	}

	/**
	 * Returns the all the types stored in the database
	 * 
	 * @throws NotFoundInDatabaseException
	 *             when there is no types.
	 * @return Collection<Type>, may be null when unexpected error occurs.
	 */
	public Collection<Type> getAll() throws NotFoundInDatabaseException {
		Collection<Type> result = new ArrayList<Type>();
		set("SELECT * FROM type;");
		if (!executeQuery()) {
			throw new NotFoundInDatabaseException("There are no types.");
		}

		do {
			result.add(extract());
		} while (next());

		return result;
	}

	/**
	 * Returns the type object stored in the database with the given name
	 * 
	 * @param name
	 *            the name of the type you are looking for
	 * @throws NotFoundInDatabaseException
	 *             when the type specified by given name does not exist.
	 * @return Type object, may be null when unexpected error occurs.
	 */
	public Type get(String name) throws NotFoundInDatabaseException {
		set("SELECT * FROM type WHERE name=?;");
		setString(1, name);
		if (!executeQuery()) {
			throw new NotFoundInDatabaseException("Type " + name
					+ " appears to be inexistant.");
		}
		return extract();
	}

	/**
	 * Returns the type object stored in the database with the given id
	 * 
	 * @param Id
	 *            the id of the type you are looking for
	 * @return Type object, may be null.
	 */
	public Type get(int id) throws NotFoundInDatabaseException {
		set("SELECT * FROM type WHERE idtype=?;");
		setInt(1, id);
		executeQuery();
		Type r = extract();
		if (r == null)
			throw new NotFoundInDatabaseException("Type " + id
					+ " appears to be inexistant.");
		return r;
	}

	/**
	 * Returns the type object stored in the database with the given object
	 * 
	 * @param type
	 *            object
	 * 
	 * @throws NotFoundInDatabaseException
	 *             when the type specified by given object does not exist.
	 * @return Type object, may be null when unexpected error occurs.
	 */
	public Type get(Type type) throws NotFoundInDatabaseException {
		Type t = get(type.getName());
		return t;
	}

	/**
	 * Removes a type identified by its database id number.
	 * 
	 * @param id
	 *            id of the type to remove
	 * @return true if it was removed
	 */
	public boolean remove(int id) {
		set("DELETE FROM type WHERE idtype=?;");
		setInt(1, id);
		return executeUpdate();
	}
}
