package fr.irit.wanda.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import fr.irit.wanda.entities.Video;
import fr.irit.wanda.entities.View;
import fr.irit.wanda.entities.Workflow;
import fr.irit.wanda.exception.AlreadyRegistredException;
import fr.irit.wanda.exception.NotFoundInDatabaseException;

public class VideoAO extends DAO {

	public VideoAO() {
		super();
	}

	public VideoAO(ResultSet rs) {
		super();
		this.rs = rs;
	}

	/**
	 * Adds a video to the database if it does not already exists. Name is
	 * unique.
	 * 
	 * @throws AlreadyRegistredException
	 *             if the video is already registered in database
	 * 
	 * @return database id of the created object or -1 if an error occured
	 */
	public int add(Video v) throws AlreadyRegistredException {
		if (exists(v)) // If the video already exists: error
			throw new AlreadyRegistredException("video id " + v.getId()
					+ " already is registered in database.");

		set("INSERT INTO video(workflow, _view, owner,name) VALUES (?,?,?,?);",
				Statement.RETURN_GENERATED_KEYS); // Retrieve the generated id
		setEntity(1, v.getWorkflow());
		setEntity(2, v.getView());
		setEntity(3, v.getOwner());
		setString(4, v.getName());
		executeUpdate();
		getGeneratedKeys();
		v.setId(getInt("idvideo"));
		return v.getId();
	}

	/*
	 * Extracts a video object. Object returned can be null.
	 */
	protected Video extract() {
		try {
			Workflow s = new WorkflowAO().get(getInt("workflow"));
			View v = new ViewAO().get(getInt("_view"));
			return new Video(getInt("idvideo"), s, v, getString("name"));

		} catch (NotFoundInDatabaseException ex) {
			super.logger.warning("Unable to find the object !");
			super.logger.fine("Current Statement: " + stmt);
			super.logger.fine("Returned error: " + ex);
			return null;
		}
	}

	/*
	 * Extracts a video object from the ResultSet containing a full row of the
	 * video table. Object returned can be null.
	 */
	protected Video extract(ResultSet rs) {
		this.rs = rs;
		return extract();
	}

	/**
	 * Returns true if the given video is recorded in the database
	 * 
	 * @param s
	 *            video
	 * @return boolean
	 */
	public boolean exists(Video v) {
		if (v.getId() != -1)
			return exists(v.getId());
		return exists(v.getName());
	}

	/**
	 * Returns true if the given video id is recorded in the database
	 * 
	 * @param id
	 *            the id
	 * @return boolean
	 */
	public boolean exists(int id) {
		set("SELECT idvideo FROM video WHERE idvideo=?;");
		setInt(1, id);
		return executeQuery();
	}

	/**
	 * Returns true if the given video name is recorded in the database
	 * 
	 * @param name
	 *            the name
	 * @return boolean
	 */
	public boolean exists(String name) {
		set("SELECT idvideo FROM video WHERE name=?;");
		setString(1, name);
		return executeQuery();
	}

	/**
	 * Returns the all the videos stored in the database
	 * 
	 * @throws NotFoundInDatabaseException
	 *             when there is no videos.
	 * @return Collection<Video>, may be null when unexpected error occurs.
	 */
	public Collection<Video> getAll() throws NotFoundInDatabaseException {
		Collection<Video> result = new ArrayList<Video>();
		set("SELECT * FROM video;");
		if (!executeQuery()) {
			throw new NotFoundInDatabaseException("There are no videos.");
		}

		do {
			result.add(extract());
		} while (next());

		return result;
	}

	/**
	 * Returns the video object stored in the database with the given id
	 * 
	 * @param Id
	 *            the id of the corpus you are looking for
	 * @return Corpus object, may be null.
	 */
	public Video get(int id) throws NotFoundInDatabaseException {
		set("SELECT * FROM video WHERE idvideo=?;");
		setInt(1, id);
		executeQuery();
		Video v = extract();
		if (v == null)
			throw new NotFoundInDatabaseException("Video " + id
					+ " appears to be inexistant.");
		return v;
	}

	/**
	 * Returns the video object stored in the database with the given object
	 * 
	 * @param video
	 *            object
	 * 
	 * @throws NotFoundInDatabaseException
	 *             when the video specified by given object does not exist.
	 * @return Video object, may be null when unexpected error occurs.
	 */
	public Video get(Video video) throws NotFoundInDatabaseException {
		Video v = get(video.getId());
		return v;
	}

	/**
	 * Returns the video object stored in the database with the given name
	 * 
	 * @param name
	 *            the name of the video you are looking for
	 * @throws NotFoundInDatabaseException
	 *             when the type specified by given name does not exist.
	 * @return Type object, may be null when unexpected error occurs.
	 */
	public Video get(String name) throws NotFoundInDatabaseException {
		set("SELECT * FROM video WHERE name=?;");
		setString(1, name);
		if (!executeQuery()) {
			throw new NotFoundInDatabaseException("Video " + name
					+ " appears to be inexistant.");
		}
		return extract();
	}

	/**
	 * Removes a video identified by its database id number.
	 * 
	 * @param id
	 *            id of the video to remove
	 * @return true if it was removed
	 */
	public boolean remove(int id) {
		set("DELETE FROM video WHERE idvideo=?;");
		setInt(1, id);
		return executeUpdate();
	}

	public Video makeConcrete(Video v) {
		HashMap<String, HashMap<String, String>> concreteVideo = new HashMap<String, HashMap<String, String>>();

		set("SELECT * FROM concretevideo WHERE video=?;");
		setInt(1, v.getId());
		executeQuery();
		do {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put(getString("resolution"), getString("format"));
			concreteVideo.put(getString("link"), map);
		} while (next());

		v.setConcreteVideo(concreteVideo);
		return v;
	}
}
