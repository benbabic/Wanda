package fr.irit.wanda.entities;


public class Session extends Container {
	private Rule rule = null;
	private Corpus corpus = null;
	private User owner = null;	
    
    /**
     * Default constructor used to create a session object.
     * 
     * @param id
     *            the id
     * @param rule
     *            the rule
     * @param corpus
     *            the corpus 
     * @param owner
     *            the owner
     */   
    public Session(int id, Rule rule, Corpus corpus, String name) {
    	super(id,"session",name);
        
        this.rule = rule;
        this.corpus = corpus;
    }
    
    public Session(Rule rule, Corpus corpus, String name) {
    	super(-1,"session",name);
        
        this.rule = rule;
        this.corpus = corpus;
    }
    
    public Session(Rule rule, Corpus corpus, String name, User owner) {
    	super(-1,"session",name);
        
        this.rule = rule;
        this.corpus = corpus;
        this.owner = owner;
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
     * Get the value of corpus
     *
     * @return the value of corpus
     */
    public Corpus getCorpus() {
        return corpus;
    }

    /**
     * Set the value of corpus
     *
     * @param corpus new value of corpus
     */
    public void setCorpus(Corpus corpus) {
        this.corpus = corpus;
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
}
