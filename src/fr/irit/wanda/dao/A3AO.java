package fr.irit.wanda.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import fr.irit.wanda.entities.A3;
import fr.irit.wanda.exception.AlreadyRegistredException;
import fr.irit.wanda.exception.NotFoundInDatabaseException;

public class A3AO extends DAO {
	
	public A3AO() {
		super();
	}
	
	public A3AO(ResultSet rs) {
		super();
		this.rs=rs;
	}
	
	/**
	 * Adds a A3 to the database if it does not already exists. 
	 * 
	 * @throws AlreadyRegistredException
	 *             if the A3 is already registered in database
	 *          
	 * @return database id of the created object or -1 if an error occured
	 */
	public int add(A3 a) throws AlreadyRegistredException {
		if (exists(a)) // If the annotation already exists: error
			throw new AlreadyRegistredException("A3 named " + a.getName()
					+ " already is registered in database.");

		set("INSERT INTO a3(name, uri, description) VALUES (?,?,?);",
				Statement.RETURN_GENERATED_KEYS); // Retrieve the generated id
		setString(1, a.getName());
		setString(2,a.getUri());
		setString(3, a.getDescription());
		executeUpdate();
		getGeneratedKeys();
		a.setId(getInt("certificate"));
		return a.getId();
	}
	
	/*
	 * Extracts a A3 object. 
	 * Object returned can be null.
	 */
	protected A3 extract() {
		return new A3(getInt("certificate"), getString("name"), getString("uri"), getString("description"));
	}
	
	/*
	 * Extracts a A3 object from the ResultSet containing a full row of
	 * the A3 table. Object returned can be null.
	 */
	protected A3 extract(ResultSet rs) {
		this.rs=rs;
		return extract();
	}
	
	/**
	 * Returns true if the given A3 is recorded in the database
	 * 
	 * @param a
	 *            A3
	 * @return boolean
	 */
	public boolean exists(A3 a) {
		if (a.getId() != -1)
			return exists(a.getId());
		return exists(a.getName());
	}
	
	/**
	 * Returns true if the given A3 id is recorded in the database
	 * 
	 * @param id
	 *            the id
	 * @return boolean
	 */
	public boolean exists(int id) {
		set("SELECT certificat FROM a3 WHERE certificat=?;");
		setInt(1, id);
		return executeQuery();
	}
	
	
	/**
	 * Returns true if the given A3 name is recorded in the database
	 * 
	 * @param name
	 *            the name
	 * @return boolean
	 */
	public boolean exists(String name) {
		set("SELECT certificate FROM a3 WHERE name=?;");
		setString(1, name);
		return executeQuery();
	}
	
	/**
	 * Returns the A3 object stored in the database with the given id
	 * 
	 * @param Id
	 *            the id of the A3 you are looking for
	 * @return A3 object, may be null.
	 */
	public A3 get(int id) throws NotFoundInDatabaseException {
		set("SELECT * FROM a3 WHERE certificat=?;");
		setInt(1, id);
		executeQuery();
		A3 a = extract();
		if (a == null)
			throw new NotFoundInDatabaseException("A3 " + id
					+ " appears to be inexistant.");
		return a;
	}
	
	/**
	 * Returns the all the A3 stored in the database
	 * 
	 * @throws NotFoundInDatabaseException
	 *             when there is no A3.
	 * @return Collection<A3>, may be null when unexpected error occurs.
	 */
	public Collection<A3> getAll() throws NotFoundInDatabaseException {
		Collection<A3> result = new ArrayList<A3>();
		set("SELECT * FROM a3;");
		if (!executeQuery()) {
			throw new NotFoundInDatabaseException("There are no A3.");
		}
		
		do{
			result.add(extract());
		}while(next());
		
		return result;
	}
	
	/**
	 * Returns the A3 object stored in the database with the given object
	 * 
	 * @param A3 object
	 *            
	 * @throws NotFoundInDatabaseException
	 *             when the A3 specified by given object does not exist.
	 * @return A3 object, may be null when unexpected error occurs.
	 */
	public A3 get(A3 a3) throws NotFoundInDatabaseException {
		A3 a = get(a3.getId());
		return a;
	}
	
	/**
	 * Returns the A3 object stored in the database with the given name
	 * 
	 * @param name
	 *            the name of the A3 you are looking for
	 * @throws NotFoundInDatabaseException
	 *             when the type specified by given name does not exist.
	 * @return Type object, may be null when unexpected error occurs.
	 */
	public A3 get(String name) throws NotFoundInDatabaseException {
		set("SELECT * FROM a3 WHERE name=?;");
		setString(1, name);
		if (!executeQuery()) {
			throw new NotFoundInDatabaseException("A3 " + name
					+ " appears to be inexistant.");
		}
		return extract();
	}
	
	/**
	 * Removes a A3 identified by its database id number.
	 * 
	 * @param id
	 *            id of the annotation to remove
	 * @return true if it was removed
	 */
	public boolean remove(int id) {
		set("DELETE FROM a3 WHERE certificate=?;");
		setInt(1, id);
		return executeUpdate();
	}
}