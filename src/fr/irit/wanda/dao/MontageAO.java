package fr.irit.wanda.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import fr.irit.wanda.entities.Montage;
import fr.irit.wanda.exception.AlreadyRegistredException;
import fr.irit.wanda.exception.NotFoundInDatabaseException;

public class MontageAO extends DAO {

	public MontageAO() {
		super();
	}

	public MontageAO(ResultSet rs) {
		super();
		this.rs = rs;
	}

	/** 
	 * Adds a montage to the database if it does not already exists. Name is
	 * unique.
	 * 
	 * @throws AlreadyRegistredException
	 *             if the montage is already registered in database
	 * 
	 * @return database id of the created object or -1 if an error occured
	 */
	public int add(Montage m) throws AlreadyRegistredException {
		if (exists(m)) // If the montage already exists: error
			throw new AlreadyRegistredException("montage id " + m.getId()
					+ " already is registered in database.");

		set("INSERT INTO montage(owner, _session) VALUES (?,?);",
				Statement.RETURN_GENERATED_KEYS); // Retrieve the generated id
		setEntity(1, m.getOwner());
		setEntity(2, m.getSession());
		// TODO view
		executeUpdate();
		getGeneratedKeys();
		m.setId(getInt("idmontage"));
		return m.getId();
	}

	/*
	 * Extracts a montage object. Object returned can be null.
	 */
	protected Montage extract() {
		// TODO
		return new Montage(getInt("idmontage"));
	}

	/*
	 * Extracts a montage object from the ResultSet containing a full row of the
	 * montage table. Object returned can be null.
	 */
	protected Montage extract(ResultSet rs) {
		this.rs = rs;
		return extract();
	}

	/**
	 * Returns true if the given montage is recorded in the database
	 * 
	 * @param m
	 *            montage
	 * @return boolean
	 */
	public boolean exists(Montage s) {
		if (s.getId() != -1)
			return exists(s.getId());
		return exists(s.getName());
	}

	/**
	 * Returns true if the given montage id is recorded in the database
	 * 
	 * @param id
	 *            the id
	 * @return boolean
	 */
	public boolean exists(int id) {
		set("SELECT idmontage FROM montage WHERE idmontage=?;");
		setInt(1, id);
		return executeQuery();
	}
	
	/**
	 * Returns true if the given montage name is recorded in the database
	 * 
	 * @param id
	 *            the id
	 * @return boolean
	 */
	public boolean exists(String name) {
		set("SELECT idmontage FROM montage WHERE name=?;");
		setString(1, name);
		return executeQuery();
	}

	/**
	 * Returns the all the montages stored in the database
	 * 
	 * @throws NotFoundInDatabaseException
	 *             when there is no montages.
	 * @return Collection<Montage>, may be null when unexpected error occurs.
	 */
	public Collection<Montage> getAll() throws NotFoundInDatabaseException {
		Collection<Montage> result = new ArrayList<Montage>();
		set("SELECT * FROM montage;");
		if (!executeQuery()) {
			throw new NotFoundInDatabaseException("There are no montages.");
		}

		do {
			result.add(extract());
		} while (next());

		return result;
	}

	/**
	 * Returns the montage object stored in the database with the given id
	 * 
	 * @param Id
	 *            the id of the corpus you are looking for
	 * @return Corpus object, may be null.
	 */
	public Montage get(int id) throws NotFoundInDatabaseException {
		set("SELECT * FROM montage WHERE idmontage=?;");
		setInt(1, id);
		executeQuery();
		Montage s = extract();
		if (s == null)
			throw new NotFoundInDatabaseException("Montage " + id
					+ " appears to be inexistant.");
		return s;
	}

	/**
	 * Returns the montage object stored in the database with the given object
	 * 
	 * @param montage
	 *            object
	 * 
	 * @throws NotFoundInDatabaseException
	 *             when the montage specified by given object does not exist.
	 * @return Montage object, may be null when unexpected error occurs.
	 */
	public Montage get(Montage montage) throws NotFoundInDatabaseException {
		Montage s = get(montage.getId());
		return s;
	}

	/**
	 * Returns the montage object stored in the database with the given name
	 * 
	 * @param name
	 *            the name of the montage you are looking fore
	 * @throws NotFoundInDatabaseException
	 *             when the montage specified by given name does not exist.
	 * @return Montage object, may be null when unexpected error occurs.
	 */
	public Montage get(String name) throws NotFoundInDatabaseException {
		set("SELECT * FROM montage WHERE name=?;");
		setString(1, name);
		if (!executeQuery()) {
			throw new NotFoundInDatabaseException("Montage " + name
					+ " appears to be inexistant.");
		}
		return extract();
	}

	/**
	 * Removes a montage identified by its database id number.
	 * 
	 * @param id
	 *            id of the montage to remove
	 * @return true if it was removed
	 */
	public boolean remove(int id) {
		set("DELETE FROM montage WHERE idmontage=?;");
		setInt(1, id);
		return executeUpdate();
	}
}
