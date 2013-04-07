package fr.irit.wanda.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import fr.irit.wanda.entities.Corpus;
import fr.irit.wanda.entities.Rule;
import fr.irit.wanda.entities.Site;
import fr.irit.wanda.exception.AlreadyRegistredException;
import fr.irit.wanda.exception.NotFoundInDatabaseException;

public class CorpusAO extends DAO {

	public CorpusAO() {
		super();
	}

	public CorpusAO(ResultSet rs) {
		super();
		this.rs = rs;
	}

	/**
	 * Adds a corpus to the database if it does not already exists. Name is
	 * unique.
	 * 
	 * @throws AlreadyRegistredException
	 *             if the corpus is already registered in database
	 * 
	 * @return database id of the created object or -1 if an error occured
	 */
	public int add(Corpus s) throws AlreadyRegistredException {
		if (exists(s)) // If the corpus already exists: error
			throw new AlreadyRegistredException("Corpus id " + s.getId()
					+ " already is registered in database.");
		if (s.getFather() == null) {
			set("INSERT INTO corpus(rule,_site,owner,name) VALUES (?,?,?,?);",
					Statement.RETURN_GENERATED_KEYS);
		} else {
			set("INSERT INTO corpus(rule,_site,owner,_corpus,name) VALUES (?,?,?,?,?);",
					Statement.RETURN_GENERATED_KEYS); // Retrieve the generated
														// id
		}
		setEntity(1, s.getRule());
		setEntity(2, s.getSite());
		setEntity(3, s.getOwner());
		if (s.getFather() == null) {
			setString(4, s.getName());
		} else {
			setEntity(4, s.getFather());
			setString(5, s.getName());
		}
		executeUpdate();
		getGeneratedKeys();
		s.setId(getInt("idcorpus"));
		return s.getId();
	}

	/*
	 * Extracts a corpus object. Object returned can be null.
	 */
	protected Corpus extract() {
		try {
			Rule r = new RuleAO().get(getInt("rule"));
			Site s = new SiteAO().get(getInt("_site"));
			if (getInt("_corpus") != 0){
				Corpus c = new CorpusAO().get(getInt("_corpus"));
				return new Corpus(getInt("idcorpus"), r, s, c, getString("name"));
			}
			return new Corpus(getInt("idcorpus"), r, s, getString("name"));


		} catch (NotFoundInDatabaseException ex) {
			super.logger.warning("Unable to find the object !");
			super.logger.fine("Current Statement: " + stmt);
			super.logger.fine("Returned error: " + ex);
			return null;
		}
	}

	/*
	 * Extracts a corpus object from the ResultSet containing a full row of the
	 * corpus table. Object returned can be null.
	 */
	protected Corpus extract(ResultSet rs) {
		this.rs = rs;
		return extract();
	}

	/**
	 * Returns true if the given corpus is recorded in the database
	 * 
	 * @param c
	 *            corpus
	 * @return boolean
	 */
	public boolean exists(Corpus c) {
		if (c.getId() != -1)
			return exists(c.getId());
		return exists(c.getName());
	}

	/**
	 * Returns true if the given corpus id is recorded in the database
	 * 
	 * @param id
	 *            the id
	 * @return boolean
	 */
	public boolean exists(int id) {
		set("SELECT idcorpus FROM corpus WHERE idcorpus=?;");
		setInt(1, id);
		return executeQuery();
	}

	/**
	 * Returns true if the given corpus name is recorded in the database
	 * 
	 * @param name
	 *            the name
	 * @return boolean
	 */
	public boolean exists(String name) {
		set("SELECT idcorpus FROM corpus WHERE name=?;");
		setString(1, name);
		return executeQuery();
	}

	/**
	 * Returns the all the corpus stored in the database
	 * 
	 * @throws NotFoundInDatabaseException
	 *             when there is no corpus.
	 * @return Collection<Corpus>, may be null when unexpected error occurs.
	 */
	public Collection<Corpus> getAll() throws NotFoundInDatabaseException {
		Collection<Corpus> result = new ArrayList<Corpus>();
		set("SELECT * FROM corpus;");
		if (!executeQuery()) {
			throw new NotFoundInDatabaseException("There are no corpus.");
		}

		do {
			result.add(extract());
		} while (next());

		return result;
	}

	/**
	 * Returns the corpus object stored in the database with the given id
	 * 
	 * @param Id
	 *            the id of the corpus you are looking for
	 * @return Corpus object, may be null.
	 */
	public Corpus get(int id) throws NotFoundInDatabaseException {
		set("SELECT * FROM corpus WHERE idcorpus=?;");
		setInt(1, id);
		executeQuery();
		Corpus c = extract();
		if (c == null)
			throw new NotFoundInDatabaseException("Corpus " + id
					+ " appears to be inexistant.");
		return c;
	}

	/**
	 * Returns the corpus object stored in the database with the given object
	 * 
	 * @param corpus
	 *            object
	 * 
	 * @throws NotFoundInDatabaseException
	 *             when the corpus specified by given object does not exist.
	 * @return Corpus object, may be null when unexpected error occurs.
	 */
	public Corpus get(Corpus corpus) throws NotFoundInDatabaseException {
		Corpus c = get(corpus.getId());
		return c;
	}

	/**
	 * Returns the corpus object stored in the database with the given name
	 * 
	 * @param name
	 *            the name of the corpus you are looking for
	 * @throws NotFoundInDatabaseException
	 *             when the type specified by given name does not exist.
	 * @return Type object, may be null when unexpected error occurs.
	 */
	public Corpus get(String name) throws NotFoundInDatabaseException {
		set("SELECT * FROM corpus WHERE name=?;");
		setString(1, name);
		if (!executeQuery()) {
			throw new NotFoundInDatabaseException("Corpus " + name
					+ " appears to be inexistant.");
		}
		return extract();
	}

	/**
	 * Removes a corpus identified by its database id number.
	 * 
	 * @param id
	 *            id of the corpus to remove
	 * @return true if it was removed
	 */
	public boolean remove(int id) {
		set("DELETE FROM corpus WHERE idcorpus=?;");
		setInt(1, id);
		return executeUpdate();
	}
}
