package fr.irit.wanda.entities;

public class Role extends DescribedEntity {

	public enum ROLE {
		ADMIN(0), REGISTRED_USER(1), UNIDENTIFIED(-1);
		private int value;

		private ROLE(int value) {
			this.value = value;
		}
		
		public int getValue(){
			return this.value;
		}

		public static ROLE fromInt(int i) {
			switch (i) {
			case 0:
				return ROLE.ADMIN;
			case 1:
				return ROLE.REGISTRED_USER;
			}
			return ROLE.UNIDENTIFIED;
		}
	}

	private static final int DEFAULT_UNREGISTERD_USER = 10;
	private int authorisationLevel;

	public Role(String name, String desc, int authorisationLevel) {
		super("role", name, desc);
		this.setAuthorisationLevel(authorisationLevel);
	}

	public Role(int id, String name, String desc) {
		super(id, "role", name, desc);
		setAuthorisationLevel(DEFAULT_UNREGISTERD_USER);
	}

	public Role(int id, String name, String desc, int authorisationLevel) {
		super(id, "role", name, desc);
		this.setAuthorisationLevel(authorisationLevel);
	}

	/**
	 * @return the authorisationLevel
	 */
	public int getAuthorisationLevel() {
		return authorisationLevel;
	}

	/**
	 * @param authorisationLevel
	 *            the authorisationLevel to set
	 */
	public void setAuthorisationLevel(int authorisationLevel) {
		this.authorisationLevel = authorisationLevel;
	}

}
