package fr.irit.wanda.entities;

import java.util.Collection;

public class Montage extends Entity {
    private User owner = null;
    private Collection<View> views = null;
    private Session session = null;
    private String link = null;
    private String name = null;

    /**
     * Default constructor used to create a montage object.
     * 
     * @param id
     *            the id
     * @param owner
     *            the owner
     */   
    public Montage(int id, Collection<View> views, String link, String name) {
        super(id,"montage");
        
        this.views = views;
        this.link = link;
        this.name = name;
    }
    
    public Montage(Collection<View> views, User owner, Session session, String link, String name) {
        super(-1,"montage");
        
        this.views = views;
        this.owner = owner;
        this.session = session;
        this.link = link;
        this.name = name;
    }
    
    public Montage(Collection<View> views, Session session, String link, String name) {
        super(-1,"montage");
        
        this.views = views;
        this.session = session;
        this.link = link;
        this.name = name;
    }
    
    public Montage (int id){
    	super(id,"montage");
    }
    

    /**
     * Get the value of owner
     *
     * @return the value of owner
     */
    public User getOwner() {
        return owner;
    }

    /**
     * Set the value of owner
     *
     * @param owner new value of owner
     */
    public void setOwner(User owner) {
        this.owner = owner;
    }


	/**
	 * @return the views
	 */
	public Collection<View> getViews() {
		return views;
	}


	/**
	 * @param views the views to set
	 */
	public void setViews(Collection<View> views) {
		this.views = views;
	}

	/**
	 * @return the session
	 */
	public Session getSession() {
		return session;
	}

	/**
	 * @param session the session to set
	 */
	public void setSession(Session session) {
		this.session = session;
	}

	/**
	 * @return the link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * @param link the link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
