package fr.irit.wanda.configuration;

import java.util.ArrayList;
import java.util.Hashtable;

import fr.irit.wanda.dao.DatabaseMetadataAO;

public final class HirarchyConfiguration {

	private static volatile HirarchyConfiguration instance = null;
	private Hashtable<String, ArrayList<String>> hierarchy = new Hashtable<String, ArrayList<String>>();

	private HirarchyConfiguration() {
		getConfiguration();
	}

	private void getConfiguration() {
		DatabaseMetadataAO DMAO = new DatabaseMetadataAO();
		for (String container_name : DMAO.getContainersNames()) {
			hierarchy.put(container_name,
					(ArrayList<String>) DMAO.getContainerContentNames(container_name));
		}
	}
	
	public final static HirarchyConfiguration getInstance() {
		if (instance == null) // we try to avoid using synchronized
		{
			synchronized (HirarchyConfiguration.class) {
				if (instance == null)
					instance = new HirarchyConfiguration();
			}
		}
		return instance;
	}

	/**
	 * @return the hierarchy
	 */
	public Hashtable<String, ArrayList<String>> getHierarchy() {
		return hierarchy;
	}

}
