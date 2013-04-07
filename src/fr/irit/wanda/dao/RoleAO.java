package fr.irit.wanda.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import fr.irit.wanda.entities.Role;
import fr.irit.wanda.exception.AlreadyRegistredException;
import fr.irit.wanda.exception.NotFoundInDatabaseException;

public class RoleAO extends DAO {

	public RoleAO() {
		super();
	}

	public RoleAO(ResultSet rs) {
		super();
		this.rs = rs;
	}

	/**
	 * Adds a role to the database if it does not already exists. Name is
	 * unique.
	 * 
	 * @throws AlreadyRegistredException
	 *             if the role is already registered in database
	 * 
	 * @return database id of the created object or -1 if an error occured
	 */
	public int add(Role r) throws AlreadyRegistredException {
		if (exists(r)) // If the role already exists: error
			throw new AlreadyRegistredException("Role named " + r.getName()
					+ " already is registered in database.");

		set("INSERT INTO role(name, authlvl, description) VALUES (?,?,?);",
				Statement.RETURN_GENERATED_KEYS); // Retrieve the generated id
		setString(1, r.getName());
		setInt(2, r.getAuthorisationLevel());
		setString(3, r.getDescription());
		executeUpdate();
		getGeneratedKeys();
		r.setId(getInt("idrole"));
		return r.getId();
	}

	/*
	 * Extracts a role object. Object returned can be null.
	 */
	protected Role extract() {
		return new Role(getInt("idrole"), getString("name"),
				getString("description"), getInt("authlvl"));
	}

	/*
	 * Extracts a role object from the ResultSet containing a full row of the
	 * role table. Object returned can be null.
	 */
	protected Role extract(ResultSet rs) {
		this.rs = rs;
		return extract();
	}

	/**
	 * Returns true if the given role is recorded in the database
	 * 
	 * @param r
	 *            role
	 * @return boolean
	 */
	public boolean exists(Role r) {
		if (r.getId() != -1)
			return exists(r.getId());
		return exists(r.getName());
	}

	/**
	 * Returns true if the given role name is recorded in the database
	 * 
	 * @param name
	 *            the name
	 * @return boolean
	 */
	public boolean exists(String name) {
		set("SELECT idrole FROM role WHERE name=?;");
		setString(1, name);
		return executeQuery();
	}

	/**
	 * Returns true if the given role id is recorded in the database
	 * 
	 * @param id
	 *            the id
	 * @return boolean
	 */
	public boolean exists(int id) {
		set("SELECT idrole FROM role WHERE idrole=?;");
		setInt(1, id);
		return executeQuery();
	}

	/**
	 * Returns the role object stored in the database with the given name
	 * 
	 * @param name
	 *            the name of the role you are looking fore
	 * @throws NotFoundInDatabaseException
	 *             when the role specified by given name does not exist.
	 * @return Role object, may be null when unexpected error occurs.
	 */
	public Role get(String name) throws NotFoundInDatabaseException {
		set("SELECT * FROM role WHERE name=?;");
		setString(1, name);
		if (!executeQuery()) {
			throw new NotFoundInDatabaseException("Role " + name
					+ " appears to be inexistant.");
		}
		return extract();
	}

	/**
	 * Returns the all the roles stored in the database
	 * 
	 * @throws NotFoundInDatabaseException
	 *             when there is no roles.
	 * @return Collection<Role>, may be null when unexpected error occurs.
	 */
	public Collection<Role> getAll() throws NotFoundInDatabaseException {
		Collection<Role> result = new ArrayList<Role>();
		set("SELECT * FROM role;");
		if (!executeQuery()) {
			throw new NotFoundInDatabaseException("There are no roles.");
		}
		do {
			result.add(extract());
		} while (next());

		return result;
	}

	/**
	 * Returns the role object stored in the database with the given id
	 * 
	 * @param Id
	 *            the id of the role you are looking for
	 * @return Role object, may be null.
	 */
	public Role get(int id) throws NotFoundInDatabaseException {
		set("SELECT * FROM role WHERE idrole=?;");
		setInt(1, id);
		executeQuery();
		Role r = extract();
		if (r == null)
			throw new NotFoundInDatabaseException("Role " + id
					+ " appears to be inexistant.");
		return r;
	}

	/**
	 * Returns the role object stored in the database with the given object
	 * 
	 * @param role
	 *            object
	 * 
	 * @throws NotFoundInDatabaseException
	 *             when the role specified by given object does not exist.
	 * @return Role object, may be null when unexpected error occurs.
	 */
	public Role get(Role role) throws NotFoundInDatabaseException {
		Role r = get(role.getName());
		return r;
	}

	/**
	 * Removes a role identified by its database id number.
	 * 
	 * @param id
	 *            id of the role to remove
	 * @return true if it was removed
	 */
	public boolean remove(int id) {
		set("DELETE FROM role WHERE idrole=?;");
		setInt(1, id);
		return executeUpdate();
	}
}
