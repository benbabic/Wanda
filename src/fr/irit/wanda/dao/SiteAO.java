package fr.irit.wanda.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import fr.irit.wanda.entities.Site;
import fr.irit.wanda.entities.Rule;
import fr.irit.wanda.exception.AlreadyRegistredException;
import fr.irit.wanda.exception.NotFoundInDatabaseException;

public class SiteAO extends DAO {

	public SiteAO() {
		super();
	}

	public SiteAO(ResultSet rs) {
		super();
		this.rs = rs;
	}

	/**
	 * Adds a site to the database if it does not already exists. Name is
	 * unique.
	 * 
	 * @throws AlreadyRegistredException
	 *             if the site is already registered in database
	 * 
	 * @return database id of the created object or -1 if an error occured
	 */
	public int add(Site s) throws AlreadyRegistredException {
		if (exists(s)) // If the site already exists: error
			throw new AlreadyRegistredException("Site id " + s.getId()
					+ " already is registered in database.");

		set("INSERT INTO site(rule,name) VALUES (?,?);",
				Statement.RETURN_GENERATED_KEYS); // Retrieve the generated id
		setEntity(1, s.getRule());
		setString(2, s.getName());
		executeUpdate();
		getGeneratedKeys();
		s.setId(getInt("idsite"));
		return s.getId();
	}

	/*
	 * Extracts a site object. Object returned can be null.
	 */
	protected Site extract() {
		try {
			Rule r = new RuleAO().get(getInt("rule"));
			return new Site(getInt("idsite"), r, getString("name"));
		} catch (NotFoundInDatabaseException ex) {
			super.logger.warning("Unable to find rule !");
			super.logger.fine("Current Statement: " + stmt);
			super.logger.fine("Returned error: " + ex);
			return null;
		}
	}

	/*
	 * Extracts a site object from the ResultSet containing a full row of the
	 * site table. Object returned can be null.
	 */
	protected Site extract(ResultSet rs) {
		this.rs = rs;
		return extract();
	}

	/**
	 * Returns true if the given site is recorded in the database
	 * 
	 * @param s
	 *            site
	 * @return boolean
	 */
	public boolean exists(Site s) {
		if (s.getId() != -1)
			return exists(s.getId());
		return exists(s.getName());
	}

	/**
	 * Returns true if the given site id is recorded in the database
	 * 
	 * @param id
	 *            the id
	 * @return boolean
	 */
	public boolean exists(int id) {
		set("SELECT idsite FROM site WHERE idsite=?;");
		setInt(1, id);
		return executeQuery();
	}

	/**
	 * Returns true if the given site name is recorded in the database
	 * 
	 * @param name
	 *            the name
	 * @return boolean
	 */
	public boolean exists(String name) {
		set("SELECT idsite FROM site WHERE name=?;");
		setString(1, name);
		return executeQuery();
	}

	/**
	 * Returns the all the sites stored in the database
	 * 
	 * @throws NotFoundInDatabaseException
	 *             when there is no sites.
	 * @return Collection<Site>, may be null when unexpected error occurs.
	 * @deprecated
	 */
	public Collection<Site> getAll() throws NotFoundInDatabaseException {
		Collection<Site> result = new ArrayList<Site>();
		set("SELECT * FROM site;");
		if (!executeQuery()) {
			throw new NotFoundInDatabaseException("There are no sites.");
		}

		do {
			result.add(extract());
		} while (next());

		return result;
	}

	/**
	 * Returns the site object stored in the database with the given id
	 * 
	 * @param Id
	 *            the id of the site you are looking for
	 * @return Site object, may be null.
	 */
	public Site get(int id) throws NotFoundInDatabaseException {
		set("SELECT * FROM site WHERE idsite=?;");
		setInt(1, id);
		executeQuery();
		Site s = extract();
		if (s == null)
			throw new NotFoundInDatabaseException("Site " + id
					+ " appears to be inexistant.");
		return s;
	}

	/**
	 * Returns the site object stored in the database with the given object
	 * 
	 * @param site
	 *            object
	 * 
	 * @throws NotFoundInDatabaseException
	 *             when the site specified by given object does not exist.
	 * @return Site object, may be null when unexpected error occurs.
	 * @deprecated
	 */
	public Site get(Site site) throws NotFoundInDatabaseException {
		Site s = get(site.getId());
		return s;
	}

	/**
	 * Returns the site object stored in the database with the given name
	 * 
	 * @param name
	 *            the name of the site you are looking for
	 * @throws NotFoundInDatabaseException
	 *             when the type specified by given name does not exist.
	 * @return Type object, may be null when unexpected error occurs.
	 */
	public Site get(String name) throws NotFoundInDatabaseException {
		set("SELECT * FROM site WHERE name=?;");
		setString(1, name);
		if (!executeQuery()) {
			throw new NotFoundInDatabaseException("Site " + name
					+ " appears to be inexistant.");
		}
		return extract();
	}

	/**
	 * Removes a site identified by its database id number.
	 * 
	 * @param id
	 *            id of the site to remove
	 * @return true if it was removed
	 */
	public boolean remove(int id) {
		set("DELETE FROM site WHERE idsite=?;");
		setInt(1, id);
		return executeUpdate();
	}
}
