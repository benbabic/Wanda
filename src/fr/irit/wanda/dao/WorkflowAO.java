package fr.irit.wanda.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import fr.irit.wanda.entities.Workflow;
import fr.irit.wanda.exception.AlreadyRegistredException;
import fr.irit.wanda.exception.NotFoundInDatabaseException;

public class WorkflowAO extends DAO {

	public WorkflowAO() {
		super();
	}

	public WorkflowAO(ResultSet rs) {
		super();
		this.rs = rs;
	}

	/**
	 * Adds a workflow to the database if it does not already exists. Name is
	 * unique.
	 * 
	 * @throws AlreadyRegistredException
	 *             if the workflow is already registered in database
	 * 
	 * @return database id of the created object or -1 if an error occured
	 */
	public int add(Workflow w) throws AlreadyRegistredException {
		if (exists(w)) // If the workflow already exists: error
			throw new AlreadyRegistredException("Workflow named " + w.getName()
					+ " already is registered in database.");

		set("INSERT INTO workflow(name, description) VALUES (?,?);",
				Statement.RETURN_GENERATED_KEYS); // Retrieve the generated id
		setString(1, w.getName());
		setString(2, w.getDescription());
		executeUpdate();
		getGeneratedKeys();
		w.setId(getInt("idworkflow"));
		return w.getId();
	}

	/*
	 * Extracts a workflow object. Object returned can be null.
	 */
	protected Workflow extract() {
		return new Workflow(getInt("idworkflow"), getString("name"),
				getString("description"));
	}

	/*
	 * Extracts a workflow object from the ResultSet containing a full row of
	 * the workflow table. Object returned can be null.
	 */
	protected Workflow extract(ResultSet rs) {
		this.rs = rs;
		return extract();
	}

	/**
	 * Returns true if the given workflow is recorded in the database
	 * 
	 * @param w
	 *            workflow
	 * @return boolean
	 */
	public boolean exists(Workflow w) {
		if (w.getId() != -1)
			return exists(w.getId());
		return exists(w.getName());
	}

	/**
	 * Returns true if the given workflow name is recorded in the database
	 * 
	 * @param name
	 *            the name
	 * @return boolean
	 */
	public boolean exists(String name) {
		set("SELECT idworkflow FROM workflow WHERE name=?;");
		setString(1, name);
		return executeQuery();
	}

	/**
	 * Returns true if the given workflow id is recorded in the database
	 * 
	 * @param id
	 *            the id
	 * @return boolean
	 */
	public boolean exists(int id) {
		set("SELECT idworkflow FROM workflow WHERE idworkflow=?;");
		setInt(1, id);
		return executeQuery();
	}

	/**
	 * Returns the all the workflows stored in the database
	 * 
	 * @throws NotFoundInDatabaseException
	 *             when there is no workflows.
	 * @return Collection<Workflow>, may be null when unexpected error occurs.
	 */
	public Collection<Workflow> getAll() throws NotFoundInDatabaseException {
		Collection<Workflow> result = new ArrayList<Workflow>();
		set("SELECT * FROM workflow;");
		if (!executeQuery()) {
			throw new NotFoundInDatabaseException("There are no workflows.");
		}

		do {
			result.add(extract());
		} while (next());

		return result;
	}

	/**
	 * Returns the workflow object stored in the database with the given name
	 * 
	 * @param name
	 *            the name of the workflow you are looking for
	 * @throws NotFoundInDatabaseException
	 *             when the workflow specified by given name does not exist.
	 * @return Workflow object, may be null when unexpected error occurs.
	 */
	public Workflow get(String name) throws NotFoundInDatabaseException {
		set("SELECT * FROM workflow WHERE name=?;");
		setString(1, name);
		if (!executeQuery()) {
			throw new NotFoundInDatabaseException("Workflow " + name
					+ " appears to be inexistant.");
		}
		return extract();
	}

	/**
	 * Returns the workflow object stored in the database with the given id
	 * 
	 * @param Id
	 *            the id of the workflow you are looking for
	 * @return Workflow object, may be null.
	 */
	public Workflow get(int id) throws NotFoundInDatabaseException {
		set("SELECT * FROM workflow WHERE idworkflow=?;");
		setInt(1, id);
		executeQuery();
		Workflow w = extract();
		if (w == null)
			throw new NotFoundInDatabaseException("Workflow " + id
					+ " appears to be inexistant.");
		return w;
	}

	/**
	 * Returns the workflow object stored in the database with the given object
	 * 
	 * @param workflow
	 *            object
	 * 
	 * @throws NotFoundInDatabaseException
	 *             when the workflow specified by given object does not exist.
	 * @return Workflow object, may be null when unexpected error occurs.
	 */
	public Workflow get(Workflow workflow) throws NotFoundInDatabaseException {
		Workflow w = get(workflow.getName());
		return w;
	}

	/**
	 * Removes a workflow identified by its database id number.
	 * 
	 * @param id
	 *            id of the workflow to remove
	 * @return true if it was removed
	 */
	public boolean remove(int id) {
		set("DELETE FROM workflow WHERE idworkflow=?;");
		setInt(1, id);
		return executeUpdate();
	}
}
