/**
 * 
 */
package fr.irit.wanda.entities;

/**
 * @author Benjamin Babic
 * 
 */
public class User extends Entity {

	public enum ACCESS_RIGHT{
		READ(3), EDIT(1), OWN(0), CREATE(2), SEE(4);
		private int value;

        private ACCESS_RIGHT(int value) {
                this.value = value;
        }
        
        public int getValue(){
        	return this.value;
        }
        
        public static ACCESS_RIGHT fromInt(int i){
        	switch(i){
    		case 0:
    			// posseder site = site manager
    			// posseder corpus = corpus manager
    			// posseder annotation, video ... = droit de décider de la visibilité et de qui a le droit de l'éditer
    			return ACCESS_RIGHT.OWN;
    		case 1:
    			return ACCESS_RIGHT.EDIT;
    		case 2:
    			return ACCESS_RIGHT.CREATE;
    		case 3: 
    			return ACCESS_RIGHT.READ;
    		}
    		return ACCESS_RIGHT.SEE;
        }
	}
	

	
	private String name;
	private String forename;
	private String mail;
	private Role role;
	private User creator = null;

	public User(int certificate, String name, String forename, Role role,
			String mail) {
		super(certificate, "user");
		this.name = name;
		this.mail = mail;
		this.role = role;
	}
	
	public User(int certificate, String name, String forename, Role role,
			String mail, User creator) {
		super(certificate, "user");
		this.name = name;
		this.mail = mail;
		this.role = role;
		this.creator = creator;
	}

	public User(String name, String forename, Role role, String mail) {
		super(-1, "user");
		this.name = name;
		this.mail = mail;
		this.role = role;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the forename
	 */
	public String getForename() {
		return forename;
	}

	/**
	 * @param forename
	 *            the forename to set
	 */
	public void setForename(String forename) {
		this.forename = forename;
	}

	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @param mail
	 *            the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * @return the creator
	 */
	public User getCreator() {
		return creator;
	}

	/**
	 * @param creator
	 *            the creator to set
	 */
	public void setCreator(User creator) {
		this.creator = creator;
	}

}
