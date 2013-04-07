package fr.irit.wanda.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import fr.irit.wanda.entities.Annotation;
import fr.irit.wanda.entities.Type;
import fr.irit.wanda.entities.Video;
import fr.irit.wanda.entities.Workflow;
import fr.irit.wanda.exception.AlreadyRegistredException;
import fr.irit.wanda.exception.NotFoundInDatabaseException;

public class AnnotationAO extends DAO {


	public AnnotationAO() {
		super();
	}
	
	public AnnotationAO(ResultSet rs) {
		super();
		this.rs=rs;
	}
	
	/**
	 * Adds a annotation to the database if it does not already exists. 
	 * If the annotation is not related with a video, the video field will contains -1
	 * 
	 * @throws AlreadyRegistredException
	 *             if the annotation is already registered in database
	 *          
	 * @return database id of the created object or -1 if an error occured
	 */
	public int add(Annotation a) throws AlreadyRegistredException {
		if (exists(a)) // If the annotation already exists: error
			throw new AlreadyRegistredException("Annotation id " + a.getId()
					+ " already is registered in database.");

		set("INSERT INTO annotation(link,workflow,owner,video,_view,name) VALUES (?,?,?,?,?,?);",
				Statement.RETURN_GENERATED_KEYS); // Retrieve the generated id
		setString(1, a.getLink());
		setEntity(2, a.getWorkflow());
		setEntity(3, a.getOwner());
		if(a.getRelated().getEntityName().equals("video")) {
			setEntity(4, a.getRelated());
		} else {
			setInt(4,-1);
		}
		setEntity(5, a.getView());
		setString(6, a.getName());
		executeUpdate();
		getGeneratedKeys();
		a.setId(getInt("idannotation"));
		return a.getId();
	}
	
	/*
	 * Extracts a annotation object. 
	 * Object returned can be null.
	 */
	protected Annotation extract() {
		try {
			Workflow w = new WorkflowAO().get(getInt("workflow"));
			Video vid;
			if(getInt("video") == -1)
				vid = null;
			else 
				vid = new VideoAO().get(getInt("video"));
			return new Annotation(getInt("idannotation"), w, vid, getString("name"));
			
		} catch (NotFoundInDatabaseException ex) {
			super.logger.warning("Unable to find the object !");
			super.logger.fine("Current Statement: " + stmt);
			super.logger.fine("Returned error: " + ex);
			return null;
		}
	}
	
	/*
	 * Extracts a annotation object from the ResultSet containing a full row of
	 * the annotation table. Object returned can be null.
	 */
	protected Annotation extract(ResultSet rs) {
		this.rs=rs;
		return extract();
	}
	
	/**
	 * Returns true if the given annotation is recorded in the database
	 * 
	 * @param a
	 *            annotation
	 * @return boolean
	 */
	public boolean exists(Annotation a) {
		if (a.getId() != -1)
			return exists(a.getId());
		return exists(a.getName());
	}
	
	/**
	 * Returns true if the given annotation id is recorded in the database
	 * 
	 * @param id
	 *            the id
	 * @return boolean
	 */
	public boolean exists(int id) {
		set("SELECT idannotation FROM annotation WHERE idannotation=?;");
		setInt(1, id);
		return executeQuery();
	}
	
	
	/**
	 * Returns true if the given annotation name is recorded in the database
	 * 
	 * @param name
	 *            the name
	 * @return boolean
	 */
	public boolean exists(String name) {
		set("SELECT idannotation FROM annotation WHERE name=?;");
		setString(1, name);
		return executeQuery();
	}
	
	/**
	 * Returns the annotation object stored in the database with the given id
	 * 
	 * @param Id
	 *            the id of the annotation you are looking for
	 * @return Annotation object, may be null.
	 */
	public Annotation get(int id) throws NotFoundInDatabaseException {
		set("SELECT * FROM annotation WHERE idannotation=?;");
		setInt(1, id);
		executeQuery();
		Annotation a = extract();
		if (a == null)
			throw new NotFoundInDatabaseException("Annotation " + id
					+ " appears to be inexistant.");
		return a;
	}
	
	/**
	 * Returns the all the annotations stored in the database
	 * 
	 * @throws NotFoundInDatabaseException
	 *             when there is no annotations.
	 * @return Collection<Annotation>, may be null when unexpected error occurs.
	 */
	public Collection<Annotation> getAll() throws NotFoundInDatabaseException {
		Collection<Annotation> result = new ArrayList<Annotation>();
		set("SELECT * FROM annotation;");
		if (!executeQuery()) {
			throw new NotFoundInDatabaseException("There are no annotations.");
		}
		
		do{
			result.add(extract());
		}while(next());
		
		return result;
	}
	
	/**
	 * Returns the annotation object stored in the database with the given object
	 * 
	 * @param annotation object
	 *            
	 * @throws NotFoundInDatabaseException
	 *             when the annotation specified by given object does not exist.
	 * @return Annotation object, may be null when unexpected error occurs.
	 */
	public Annotation get(Annotation annotation) throws NotFoundInDatabaseException {
		Annotation a = get(annotation.getId());
		return a;
	}
	
	/**
	 * Returns the annotation object stored in the database with the given name
	 * 
	 * @param name
	 *            the name of the annotation you are looking for
	 * @throws NotFoundInDatabaseException
	 *             when the type specified by given name does not exist.
	 * @return Type object, may be null when unexpected error occurs.
	 */
	public Annotation get(String name) throws NotFoundInDatabaseException {
		set("SELECT * FROM annotation WHERE name=?;");
		setString(1, name);
		if (!executeQuery()) {
			throw new NotFoundInDatabaseException("Annotation " + name
					+ " appears to be inexistant.");
		}
		return extract();
	}
	
	/**
	 * Removes a annotation identified by its database id number.
	 * 
	 * @param id
	 *            id of the annotation to remove
	 * @return true if it was removed
	 */
	public boolean remove(int id) {
		set("DELETE FROM annotation WHERE idannotation=?;");
		setInt(1, id);
		return executeUpdate();
	}
}
