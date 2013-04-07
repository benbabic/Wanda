package fr.irit.wanda.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import fr.irit.wanda.entities.Rule;
import fr.irit.wanda.exception.AlreadyRegistredException;
import fr.irit.wanda.exception.NotFoundInDatabaseException;

public class RuleAO extends DAO {

	public RuleAO() {
		super();
	}

	public RuleAO(ResultSet rs) {
		super();
		this.rs = rs;
	}

	/**
	 * Adds a rule to the database if it does not already exists. Name is
	 * unique.
	 * 
	 * @throws AlreadyRegistredException
	 *             if the rule is already registered in database
	 * 
	 * @return database id of the created object or -1 if an error occured
	 */
	public int add(Rule r) throws AlreadyRegistredException {
		if (exists(r)) // If the rule already exists: error
			throw new AlreadyRegistredException("Rule named " + r.getName()
					+ " already is registered in database.");

		set("INSERT INTO rule(name, description) VALUES (?,?);",
				Statement.RETURN_GENERATED_KEYS); // Retrieve the generated id
		setString(1, r.getName());
		setString(2, r.getDescription());
		executeUpdate();
		getGeneratedKeys();
		r.setId(getInt("idrule"));

		return r.getId();
	}

	/*
	 * Extracts a rule object. Object returned can be null.
	 */
	protected Rule extract() {
		return new Rule(getInt("idrule"), getString("name"),
				getString("description"));
	}

	/*
	 * Extracts a rule object from the ResultSet containing a full row of the
	 * rule table. Object returned can be null.
	 */
	protected Rule extract(ResultSet rs) {
		this.rs = rs;
		return extract();
	}

	/**
	 * Returns true if the given rule is recorded in the database
	 * 
	 * @param r
	 *            rule
	 * @return boolean
	 */
	public boolean exists(Rule r) {
		if (r.getId() != -1)
			return exists(r.getId());
		return exists(r.getName());
	}

	/**
	 * Returns true if the given rule name is recorded in the database
	 * 
	 * @param name
	 *            the name
	 * @return boolean
	 */
	public boolean exists(String name) {
		set("SELECT idrule FROM rule WHERE name=?;");
		setString(1, name);
		return executeQuery();
	}

	/**
	 * Returns true if the given rule id is recorded in the database
	 * 
	 * @param id
	 *            the id
	 * @return boolean
	 */
	public boolean exists(int id) {
		set("SELECT idrule FROM rule WHERE idrule=?;");
		setInt(1, id);
		return executeQuery();
	}

	/**
	 * Returns the rule object stored in the database with the given name
	 * 
	 * @param name
	 *            the name of the rule you are looking fore
	 * @throws NotFoundInDatabaseException
	 *             when the rule specified by given name does not exist.
	 * @return Rule object, may be null when unexpected error occurs.
	 */
	public Rule get(String name) throws NotFoundInDatabaseException {
		set("SELECT * FROM rule WHERE name=?;");
		setString(1, name);
		if (!executeQuery()) {
			throw new NotFoundInDatabaseException("Rule " + name
					+ " appears to be inexistant.");
		}
		return extract();
	}

	/**
	 * Returns the all the rules stored in the database
	 * 
	 * @throws NotFoundInDatabaseException
	 *             when there is no rules.
	 * @return Collection<Rule>, may be null when unexpected error occurs.
	 */
	public Collection<Rule> getAll() throws NotFoundInDatabaseException {
		Collection<Rule> result = new ArrayList<Rule>();
		set("SELECT * FROM rule;");
		if (!executeQuery()) {
			throw new NotFoundInDatabaseException("There are no rules.");
		}

		do {
			result.add(extract());
		} while (next());

		return result;
	}

	/**
	 * Returns the rule object stored in the database with the given id
	 * 
	 * @param Id
	 *            the id of the rule you are looking for
	 * @return Rule object, may be null.
	 */
	public Rule get(int id) throws NotFoundInDatabaseException {
		set("SELECT * FROM rule WHERE idrule=?;");
		setInt(1, id);
		executeQuery();
		Rule r = extract();
		if (r == null)
			throw new NotFoundInDatabaseException("Rule " + id
					+ " appears to be inexistant.");
		return r;
	}

	/**
	 * Returns the rule object stored in the database with the given object
	 * 
	 * @param rule
	 *            object
	 * 
	 * @throws NotFoundInDatabaseException
	 *             when the rule specified by given object does not exist.
	 * @return Rule object, may be null when unexpected error occurs.
	 */
	public Rule get(Rule rule) throws NotFoundInDatabaseException {
		Rule r = get(rule.getName());
		return r;
	}

	/**
	 * Removes a rule identified by its database id number.
	 * 
	 * @param id
	 *            id of the rule to remove
	 * @return true if it was removed
	 */
	public boolean remove(int id) {
		set("DELETE FROM rule WHERE idrule=?;");
		setInt(1, id);
		return executeUpdate();
	}
}
