package fr.irit.wanda.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import fr.irit.wanda.entities.Entity;
import fr.irit.wanda.entities.Role;
import fr.irit.wanda.entities.User;
import fr.irit.wanda.entities.User.ACCESS_RIGHT;
import fr.irit.wanda.exception.AlreadyRegistredException;
import fr.irit.wanda.exception.InvalidParameterException;
import fr.irit.wanda.exception.NotFoundInDatabaseException;

public class UserAO extends DAO {

	public UserAO() {
		super();
	}

	public UserAO(ResultSet rs) {
		super();
		this.rs = rs;
	}

	/**
	 * Adds an user to the database if it does not already exists.
	 * 
	 * @throws AlreadyRegistredException
	 *             if the user is already registered in database
	 * 
	 * @return database id of the created object or -1 if an error occured
	 */
	public int add(User u) throws AlreadyRegistredException {
		if (exists(u)) // If the user already exists: error
			throw new AlreadyRegistredException("User named " + u.getName()
					+ " already is registered in database.");

		set("INSERT INTO wanda_user(role, name, forename, creator, mail) VALUES (?,?,?,?,?);",
				Statement.RETURN_GENERATED_KEYS); // Retrieve the generated id
		setEntity(1, u.getRole());
		setString(2, u.getName());
		setString(3, u.getForename());
		setEntity(4, u.getCreator());
		setString(5, u.getMail());
		executeUpdate();
		getGeneratedKeys();
		u.setId(getInt("certificate"));
		return u.getId();
	}

	/*
	 * Extracts an user object. Object returned can be null.
	 */
	protected User extract() {
		try {
			Role r = new RoleAO().get(getInt("role"));
			return new User(getInt("certificate"), getString("name"),
					getString("forename"), r, getString("mail"));
		} catch (NotFoundInDatabaseException ex) {
			super.logger.warning("Unable to find the object !");
			super.logger.fine("Current Statement: " + stmt);
			super.logger.fine("Returned error: " + ex);
			return null;
		}
	}

	/*
	 * Extracts an user object from the ResultSet containing a full row of the
	 * user table. Object returned can be null.
	 */
	protected User extract(ResultSet rs) {
		this.rs = rs;
		return extract();
	}

	/**
	 * Returns true if the given user is recorded in the database
	 * 
	 * @param u
	 *            user
	 * @return boolean
	 */
	public boolean exists(User u) {
		if (u.getId() != -1)
			return exists(u.getId());
		return exists(u.getMail());
	}

	/**
	 * Returns true if the given user mail is recorded in the database
	 * 
	 * @param mail
	 *            the mail
	 * @return boolean
	 */
	public boolean exists(String mail) {
		set("SELECT certificate FROM wanda_user WHERE mail=?;");
		setString(1, mail);
		return executeQuery();
	}

	/**
	 * Returns true if the given user id is recorded in the database
	 * 
	 * @param id
	 *            the id
	 * @return boolean
	 */
	public boolean exists(int id) {
		set("SELECT certificate FROM wanda_user WHERE certificate=?;");
		setInt(1, id);
		return executeQuery();
	}

	/**
	 * Returns the all the users stored in the database
	 * 
	 * @throws NotFoundInDatabaseException
	 *             when there is no users.
	 * @return Collection<User>, may be null when unexpected error occurs.
	 */
	public Collection<User> getAll() throws NotFoundInDatabaseException {
		Collection<User> result = new ArrayList<User>();
		set("SELECT * FROM user;");
		if (!executeQuery()) {
			throw new NotFoundInDatabaseException("There are no users.");
		}

		do {
			result.add(extract());
		} while (next());

		return result;
	}

	/**
	 * Returns the user object stored in the database with the given mail
	 * 
	 * @param mail
	 *            the mail of the user you are looking for
	 * @throws NotFoundInDatabaseException
	 *             when the user specified by given mail does not exist.
	 * @return User object, may be null when unexpected error occurs.
	 */
	public User get(String mail) throws NotFoundInDatabaseException {
		set("SELECT * FROM wanda_user WHERE mail=?;");
		setString(1, mail);
		if (!executeQuery()) {
			throw new NotFoundInDatabaseException("User with mail " + mail
					+ " appears to be inexistant.");
		}
		return extract();
	}

	/**
	 * Returns the user object stored in the database with the given id
	 * 
	 * @param id
	 *            the id of the user you are looking fore
	 * @return User object, may be null.
	 */
	public User get(int id) throws NotFoundInDatabaseException {
		set("SELECT * FROM wanda_user WHERE certificate=?;");
		setInt(1, id);
		executeQuery();
		User u = extract();
		if (u == null)
			throw new NotFoundInDatabaseException("User " + id
					+ " appears to be inexistant.");
		return u;
	}

	/**
	 * Returns the user object stored in the database with the given object
	 * 
	 * @param user
	 *            object
	 * 
	 * @throws NotFoundInDatabaseException
	 *             when the user specified by given object does not exist.
	 * @return User object, may be null when unexpected error occurs.
	 */
	public User get(User user) throws NotFoundInDatabaseException {
		User u = get(user.getMail());
		return u;
	}

	/**
	 * Removes a role identified by its database id number.
	 * 
	 * @param id
	 *            id of the role to remove
	 * @return true if it was removed
	 */
	public boolean remove(int id) {
		set("DELETE FROM wanda_user WHERE certificate=?;");
		setInt(1, id);
		return executeUpdate();
	}
	
	/**
	 * User id must be known
	 * Entity id must be known
	 * Entity: montage, view, video, annotation, session, corpus or site
	 * 
	 * @param entity
	 * @param access
	 */
	public boolean setAccessRight(Entity entity, ACCESS_RIGHT access, User user)  throws InvalidParameterException{
		if (!exists(user)) throw new InvalidParameterException("User does not exist.");
		if (entity.getId() == -1) throw new InvalidParameterException("Entity does not exist.");
		//TODO automatic entity existence.
		String entity_name = entity.getEntityName();
		String table_name = "user"+entity_name+"access";
		
		if (isRightSet(entity, user)) {
			set("UPDATE "+table_name+" SET rights=? WHERE wanda_user=? AND "+entity_name+"=?;");
		}else {
			set("INSERT INTO "+table_name+"(rights,wanda_user,"+entity_name+") VALUES (?,?,?);");
		}
		
		setInt(1,access.getValue());
		setInt(2,user.getId());
		setInt(3,entity.getId());
		return executeUpdate();	
	}
	
	public ACCESS_RIGHT getAccessRight(Entity entity, User user) throws NotFoundInDatabaseException{
		String entity_name = entity.getEntityName();
		String table_name = "user"+entity_name+"access";
		set("SELECT rights FROM "+table_name+" WHERE wanda_user=? AND "+entity_name+"=?;");
		setInt(1,user.getId());
		setInt(2,entity.getId());
		if (!executeQuery()) throw new NotFoundInDatabaseException("No access right on "+entity_name+" for "+user.getMail());
		return ACCESS_RIGHT.fromInt(getInt("rights"));
	}
	
	
	private boolean isRightSet(Entity entity, User user){
		String entity_name = entity.getEntityName();
		String table_name = "user"+entity_name+"access";
		set("SELECT * FROM "+table_name+" WHERE wanda_user=? AND "+entity_name+"=?;");
		setInt(1,user.getId());
		setInt(2,entity.getId());
		return executeQuery();
	}
}
