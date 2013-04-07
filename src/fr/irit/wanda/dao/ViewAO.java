package fr.irit.wanda.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import fr.irit.wanda.entities.View;
import fr.irit.wanda.entities.Session;
import fr.irit.wanda.entities.Type;
import fr.irit.wanda.entities.Rule;
import fr.irit.wanda.exception.AlreadyRegistredException;
import fr.irit.wanda.exception.NotFoundInDatabaseException;

public class ViewAO extends DAO {

	public ViewAO() {
		super();
	}

	public ViewAO(ResultSet rs) {
		super();
		this.rs = rs;
	}

	/**
	 * Adds a view to the database if it does not already exists. Name is
	 * unique.
	 * 
	 * @throws AlreadyRegistredException
	 *             if the view is already registered in database
	 * 
	 * @return database id of the created object or -1 if an error occured
	 */
	public int add(View v) throws AlreadyRegistredException {
		if (exists(v)) // If the view already exists: error
			throw new AlreadyRegistredException("View id " + v.getId()
					+ " already is registered in database.");

		set("INSERT INTO view(type,rule,owner,_session,name) VALUES (?,?,?,?,?);",
				Statement.RETURN_GENERATED_KEYS); // Retrieve the generated id
		setEntity(1, v.getType());
		setEntity(2, v.getRule());
		setEntity(3, v.getOwner());
		setEntity(4, v.getSession());
		setString(5, v.getName());
		executeUpdate();
		getGeneratedKeys();
		v.setId(getInt("idview"));
		return v.getId();
	}

	/*
	 * Extracts a view object. Object returned can be null.
	 */
	protected View extract() {
		try {
			Type t = new TypeAO().get(getInt("type"));
			Rule r = new RuleAO().get(getInt("rule"));
			Session s = new SessionAO().get(getInt("_session"));
			return new View(getInt("idview"), t, r, s, getString("name"));

		} catch (NotFoundInDatabaseException ex) {
			super.logger.warning("Unable to find the object !");
			super.logger.fine("Current Statement: " + stmt);
			super.logger.fine("Returned error: " + ex);
			return null;
		}

	}

	/*
	 * Extracts a view object from the ResultSet containing a full row of the
	 * view table. Object returned can be null.
	 */
	protected View extract(ResultSet rs) {
		this.rs = rs;
		return extract();
	}

	/**
	 * Returns true if the given view is recorded in the database
	 * 
	 * @param v
	 *            view
	 * @return boolean
	 */
	public boolean exists(View v) {
		if (v.getId() != -1)
			return exists(v.getId());
		return exists(v.getName());
	}

	/**
	 * Returns true if the given view id is recorded in the database
	 * 
	 * @param id
	 *            the id
	 * @return boolean
	 */
	public boolean exists(int id) {
		set("SELECT idview FROM view WHERE idview=?;");
		setInt(1, id);
		return executeQuery();
	}

	/**
	 * Returns true if the given view name is recorded in the database
	 * 
	 * @param name
	 *            the name
	 * @return boolean
	 */
	public boolean exists(String name) {
		set("SELECT idview FROM view WHERE name=?;");
		setString(1, name);
		return executeQuery();
	}

	/**
	 * Returns the all the views stored in the database
	 * 
	 * @throws NotFoundInDatabaseException
	 *             when there is no views.
	 * @return Collection<View>, may be null when unexpected error occurs.
	 */
	public Collection<View> getAll() throws NotFoundInDatabaseException {
		Collection<View> result = new ArrayList<View>();
		set("SELECT * FROM view;");
		if (!executeQuery()) {
			throw new NotFoundInDatabaseException("There are no views.");
		}

		do {
			result.add(extract());
		} while (next());

		return result;
	}

	/**
	 * Returns the view object stored in the database with the given id
	 * 
	 * @param Id
	 *            the id of the view you are looking for
	 * @return View object, may be null.
	 */
	public View get(int id) throws NotFoundInDatabaseException {
		set("SELECT * FROM view WHERE idview=?;");
		setInt(1, id);
		executeQuery();
		View v = extract();
		if (v == null)
			throw new NotFoundInDatabaseException("View " + id
					+ " appears to be inexistant.");
		return v;
	}

	/**
	 * Returns the view object stored in the database with the given object
	 * 
	 * @param view
	 *            object
	 * 
	 * @throws NotFoundInDatabaseException
	 *             when the view specified by given object does not exist.
	 * @return View object, may be null when unexpected error occurs.
	 */
	public View get(View view) throws NotFoundInDatabaseException {
		View v = get(view.getId());
		return v;
	}

	/**
	 * Returns the view object stored in the database with the given name
	 * 
	 * @param name
	 *            the name of the view you are looking for
	 * @throws NotFoundInDatabaseException
	 *             when the type specified by given name does not exist.
	 * @return Type object, may be null when unexpected error occurs.
	 */
	public View get(String name) throws NotFoundInDatabaseException {
		set("SELECT * FROM view WHERE name=?;");
		setString(1, name);
		if (!executeQuery()) {
			throw new NotFoundInDatabaseException("View " + name
					+ " appears to be inexistant.");
		}
		return extract();
	}

	/**
	 * Removes a view identified by its database id number.
	 * 
	 * @param id
	 *            id of the view to remove
	 * @return true if it was removed
	 */
	public boolean remove(int id) {
		set("DELETE FROM view WHERE idview=?;");
		setInt(1, id);
		return executeUpdate();
	}
}
