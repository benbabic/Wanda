package fr.irit.wanda.entities;

import java.util.Collection;

//TODO: create another constructor
public class Corpus extends Container {
	private Rule rule = null;
	private Site site = null;
	private User owner = null;
	private Collection<Corpus> sons = null;
	private Corpus father = null;

    /**
     * Default constructor used to create a corpus object.
     * 
     * @param id
     *            the id
     * @param rule
     *            the rule
     * @param site
     *            the site 
     * @param owner
     *            the owner
     */   
    public Corpus(int id, Rule rule, Site site, String name) {
        super(id,"corpus",name);
        
        this.rule = rule;
        this.site = site;
    }  
    
    public Corpus(int id, Rule rule, Site site, Corpus father, String name) {
        super(id,"corpus",name);
        
        this.rule = rule;
        this.site = site;
        this.father = father;
    }  
    
    public Corpus(Rule rule, Site site, Corpus father, String name) {
        super(-1,"corpus",name);
        
        this.rule = rule;
        this.site = site;
        this.father = father;
    } 
    
    public Corpus(Rule rule, Site site, User owner, Corpus father, String name) {
        super(-1,"corpus",name);
        
        this.rule = rule;
        this.site = site;
        this.owner = owner;
        this.father = father;
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
     * @param rule new value of rule
     */
    public void setRule(Rule rule) {
        this.rule = rule;
    }
        
    /**
     * Get the value of site
     *
     * @return the value of site
     */
    public Site getSite() {
        return site;
    }

    /**
     * Set the value of site
     *
     * @param site new value of site
     */
    public void setSite(Site site) {
        this.site = site;
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
	 * @return the son
	 */
	public Collection<Corpus> getSon() {
		return sons;
	}

	/**
	 * @param son the son to set
	 */
	public void setSon(Collection<Corpus> son) {
		this.sons = son;
	}

	/**
	 * @return the father
	 */
	public Corpus getFather() {
		return father;
	}

	/**
	 * @param father the father to set
	 */
	public void setFather(Corpus father) {
		this.father = father;
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
