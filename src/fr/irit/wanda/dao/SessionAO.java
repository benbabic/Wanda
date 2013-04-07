package fr.irit.wanda.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import fr.irit.wanda.entities.Session;
import fr.irit.wanda.entities.Corpus;
import fr.irit.wanda.entities.Rule;
import fr.irit.wanda.exception.AlreadyRegistredException;
import fr.irit.wanda.exception.NotFoundInDatabaseException;

public class SessionAO extends DAO {

	public SessionAO() {
		super();
	}

	public SessionAO(ResultSet rs) {
		super();
		this.rs = rs;
	}

	/**
	 * Adds a session to the database if it does not already exists. Name is
	 * unique.
	 * 
	 * @throws AlreadyRegistredException
	 *             if the session is already registered in database
	 * 
	 * @return database id of the created object or -1 if an error occured
	 */
	public int add(Session s) throws AlreadyRegistredException {
		if (exists(s)) // If the session already exists: error
			throw new AlreadyRegistredException("session id " + s.getId()
					+ " already is registered in database.");

		set("INSERT INTO session(rule, _corpus, owner, name) VALUES (?,?,?,?);",
				Statement.RETURN_GENERATED_KEYS); // Retrieve the generated id
		setEntity(1, s.getRule());
		setEntity(2, s.getCorpus());
		setEntity(3, s.getOwner());
		setString(4, s.getName());
		executeUpdate();
		getGeneratedKeys();
		s.setId(getInt("idsession"));
		return s.getId();
	}

	/*
	 * Extracts a session object. Object returned can be null.
	 */
	protected Session extract() {
		try {
			Rule r = new RuleAO().get(getInt("rule"));
			Corpus c = new CorpusAO().get(getInt("_corpus"));
			return new Session(getInt("idsession"), r, c, getString("name"));

		} catch (NotFoundInDatabaseException ex) {
			super.logger.warning("Unable to find the object !");
			super.logger.fine("Current Statement: " + stmt);
			super.logger.fine("Returned error: " + ex);
			return null;
		}
	}

	/*
	 * Extracts a session object from the ResultSet containing a full row of the
	 * session table. Object returned can be null.
	 */
	protected Session extract(ResultSet rs) {
		this.rs = rs;
		return extract();
	}

	/**
	 * Returns true if the given session is recorded in the database
	 * 
	 * @param s
	 *            session
	 * @return boolean
	 */
	public boolean exists(Session s) {
		if (s.getId() != -1)
			return exists(s.getId());
		return exists(s.getName());
	}

	/**
	 * Returns true if the given session id is recorded in the database
	 * 
	 * @param id
	 *            the id
	 * @return boolean
	 */
	public boolean exists(int id) {
		set("SELECT idsession FROM session WHERE idsession=?;");
		setInt(1, id);
		return executeQuery();
	}

	/**
	 * Returns true if the given session name is recorded in the database
	 * 
	 * @param name
	 *            the name
	 * @return boolean
	 */
	public boolean exists(String name) {
		set("SELECT idsession FROM session WHERE name=?;");
		setString(1, name);
		return executeQuery();
	}

	/**
	 * Returns the all the sessions stored in the database
	 * 
	 * @throws NotFoundInDatabaseException
	 *             when there is no sessions.
	 * @return Collection<Session>, may be null when unexpected error occurs.
	 */
	public Collection<Session> getAll() throws NotFoundInDatabaseException {
		Collection<Session> result = new ArrayList<Session>();
		set("SELECT * FROM session;");
		if (!executeQuery()) {
			throw new NotFoundInDatabaseException("There are no sessions.");
		}

		do {
			result.add(extract());
		} while (next());

		return result;
	}

	/**
	 * Returns the session object stored in the database with the given id
	 * 
	 * @param Id
	 *            the id of the corpus you are looking for
	 * @return Corpus object, may be null.
	 */
	public Session get(int id) throws NotFoundInDatabaseException {
		set("SELECT * FROM session WHERE idsession=?;");
		setInt(1, id);
		executeQuery();
		Session s = extract();
		if (s == null)
			throw new NotFoundInDatabaseException("Session " + id
					+ " appears to be inexistant.");
		return s;
	}

	/**
	 * Returns the session object stored in the database with the given object
	 * 
	 * @param session
	 *            object
	 * 
	 * @throws NotFoundInDatabaseException
	 *             when the session specified by given object does not exist.
	 * @return Session object, may be null when unexpected error occurs.
	 */
	public Session get(Session session) throws NotFoundInDatabaseException {
		Session s = get(session.getId());
		return s;
	}

	/**
	 * Returns the session object stored in the database with the given name
	 * 
	 * @param name
	 *            the name of the session you are looking for
	 * @throws NotFoundInDatabaseException
	 *             when the type specified by given name does not exist.
	 * @return Type object, may be null when unexpected error occurs.
	 */
	public Session get(String name) throws NotFoundInDatabaseException {
		set("SELECT * FROM session WHERE name=?;");
		setString(1, name);
		if (!executeQuery()) {
			throw new NotFoundInDatabaseException("Session " + name
					+ " appears to be inexistant.");
		}
		return extract();
	}

	/**
	 * Removes a session identified by its database id number.
	 * 
	 * @param id
	 *            id of the session to remove
	 * @return true if it was removed
	 */
	public boolean remove(int id) {
		set("DELETE FROM session WHERE idsession=?;");
		setInt(1, id);
		return executeUpdate();
	}
}
