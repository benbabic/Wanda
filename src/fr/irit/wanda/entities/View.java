package fr.irit.wanda.entities;

public class View extends Container {
	private Type type = null;
	private Rule rule = null;
	private User owner = null;
	private Session session = null;

	/**
	 * Default constructor used to create a view object.
	 * 
	 * @param id
	 *            the id
	 * @param type
	 *            the type
	 * @param rule
	 *            the rule
	 * @param owner
	 *            the owner
	 * @param session
	 *            the session
	 */
	public View(int id, Type type, Rule rule, Session session, String name) {
		super(id, "view", name);

		this.type = type;
		this.rule = rule;
		this.session = session;
	}

	public View(Type type, Rule rule, Session session, String name) {
		super(-1, "view", name);

		this.type = type;
		this.rule = rule;
		this.session = session;
	}
	
	public View(Type type, Rule rule, Session session, String name, User owner) {
		super(-1, "view", name);

		this.type = type;
		this.rule = rule;
		this.session = session;
		this.owner = owner;
	}

	/**
	 * Get the value of type
	 * 
	 * @return the value of type
	 */
	public Type getType() {
		return type;
	}

	/**
	 * Set the value of type
	 * 
	 * @param type
	 *            new value of type
	 */
	public void setType(Type type) {
		this.type = type;
	}

	/**
	 * Get the value of rule
	 * 
	 * @return the value of rule
	 */
	public Rule getRule() {
		return rule;
	}

	/**
	 * Set the value of rule
	 * 
	 * @param rule
	 *            new value of rule
	 */
	public void setRule(Rule rule) {
		this.rule = rule;
	}

	/**
	 * Get the value of wandaUser
	 * 
	 * @return the value of wandaUser
	 */
	public User getOwner() {
		return owner;
	}

	/**
	 * Set the value of wandaUser
	 * 
	 * @param wandaUser
	 *            new value of wandaUser
	 */
	public void setOwner(User owner) {
		this.owner = owner;
	}

	/**
	 * Get the value of session
	 * 
	 * @return the value of session
	 */
	public Session getSession() {
		return session;
	}

	/**
	 * Set the value of session
	 * 
	 * @param session
	 *            new value of session
	 */
	public void setSession(Session session) {
		this.session = session;
	}
}
