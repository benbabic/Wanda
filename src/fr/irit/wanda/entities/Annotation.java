package fr.irit.wanda.entities;


public class Annotation extends NamedEntity {
    private User owner = null;
    private Workflow workflow = null;
    private Video related = null;
    private View view = null;
    private String link = null;
    
    /**
     * Default constructor used to create an annotation object.
     * 
     * @param id
     *            the id
     * @param owner
     *            the owner
     * @param workflow
     *            the workflow
     * @param related
     *            the type of the annotation
     * @param link
     *            the link
     */   
    public Annotation(int id, Workflow workflow, Video related, String name) {
        super(id,"annotation",name);
        
        this.workflow = workflow;
        this.related = related;
    }
    
    public Annotation(Workflow workflow, Video related, View view, String name, User owner) {
        super(-1,"annotation",name);
        this.workflow = workflow;
        this.related = related;
        this.name = name;
        this.owner = owner;
        this.view = view;
    }
    
    public Annotation(Workflow workflow, Video related, View view, String name) {
        super(-1,"annotation",name);
        this.workflow = workflow;
        this.related = related;
        this.name = name;
        this.view = view;
    }
    
    /**
     * Get the value of workflow
     *
     * @return the value of workflow
     */
    public Workflow getWorkflow() {
        return workflow;
    }

    /**
     * Set the value of workflow
     *
     * @param workflow new value of workflow
     */
    public void setWorkflow(Workflow workflow) {
        this.workflow = workflow;
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
     * Get the value of related
     *
     * @return the value of related
     */
    public Video getRelated() {
        return related;
    }

    /**
     * Set the value of related
     *
     * @param related new value of related
     */
    public void setRelated(Video related) {
        this.related = related;
    }

    /**
     * Get the value of link
     *
     * @return the value of link
     */
    public String getLink() {
        return link;
    }

    /**
     * Set the value of link
     *
     * @param link new value of link
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

	/**
	 * @return the view
	 */
	public View getView() {
		return view;
	}

	/**
	 * @param view the view to set
	 */
	public void setView(View view) {
		this.view = view;
	}  
	
}
