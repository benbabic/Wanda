package fr.irit.wanda.dao;

import java.util.ArrayList;
import java.util.Collection;

public class DatabaseMetadataAO extends DAO {

	public DatabaseMetadataAO() {
		super();
	}

	private Collection<String> getColumsNames(String table) {
		ArrayList<String> result = new ArrayList<String>();
		set("SELECT column_name FROM information_schema.columns WHERE table_name = ?;");
		setString(1, table);
		if (!executeQuery())
			return result;
		do {
			result.add(getString("column_name"));
		} while (next());

		return result;
	}

	private Collection<String> getTableNames() {
		ArrayList<String> result = new ArrayList<String>();
		set("SELECT table_name FROM information_schema.tables WHERE table_schema='public';");
		if (!executeQuery())
			return result;
		do {
			result.add(getString("table_name"));
		} while (next());
		return result;
	}

	public Collection<String> getContainersNames() {
		ArrayList<String> result = new ArrayList<String>();
		set("SELECT table_name FROM containers");
		if (!executeQuery())
			return result;
		do {
			result.add(getString("table_name"));
		} while (next());
		return result;
	}

	public Collection<String> getContainerContentNames(String container_name) {
		ArrayList<String> result = new ArrayList<String>();
		for (String table : getTableNames()) {
			if (getColumsNames(table).contains("_".concat(container_name))) {
				result.add(table);
			}
		}
		return result;
	}
	
	
}
