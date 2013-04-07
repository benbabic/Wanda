package fr.irit.wanda.entities;

public class Site extends Container {
	private Rule rule = null;

	/**
	 * Default constructor used to create a site object.
	 * 
	 * @param id
	 *            the id
	 * @param rule
	 *            the rule
	 */
	public Site(int id, Rule rule, String name) {
		super(id,"site",name);

		this.rule = rule;
	}
	
	public Site(Rule rule, String name) {
		super(-1,"site",name);

		this.rule = rule;
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
}
