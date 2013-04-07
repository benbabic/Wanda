package fr.irit.wanda.entities;

public class DescribedEntity extends NamedEntity {

		
		protected String description = null;
		
		public DescribedEntity(String entityName, String name, String desc){
			super(entityName,name);
			this.description=desc;
		}
		
		public DescribedEntity(int id, String entityName, String name, String desc){
			super(id,entityName,name);
			this.description=desc;
		}

		/**
		 * @return the description
		 */
		public String getDescription() {
			return description;
		}

		/**
		 * @param description the description to set
		 */
		public void setDescription(String description) {
			this.description = description;
		}
}
