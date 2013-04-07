package fr.irit.wanda.dao;

import java.util.ArrayList;
import java.util.Collection;

import fr.irit.wanda.configuration.HirarchyConfiguration;
import fr.irit.wanda.entities.Container;
import fr.irit.wanda.entities.Entity;
import fr.irit.wanda.entities.NamedEntity;

public class ContainerAO extends DAO {

	HirarchyConfiguration hc = HirarchyConfiguration.getInstance();

	/**
	 * @param container
	 * @return
	 */
	public final Collection<NamedEntity> getContent(final Entity container) {
		ArrayList<NamedEntity> result = new ArrayList<NamedEntity>();
		String cTableName = container.getEntityName();

		for (String content : hc.getHierarchy().get(cTableName)) {
			set("SELECT id" + content + ",name FROM " + content + " WHERE _"
					+ cTableName + "=?;");
			setInt(1, container.getId());
			if (executeQuery()) {
				do {
					result.add(new NamedEntity(getInt("id" + content), content, getString("name")));
				} while (next());

				closeAll();
			}
		}
		return result;
	}

	public final boolean isContainer(final Entity entity) {
		return hc.getHierarchy().containsKey(entity.getEntityName());
	}
}
