package fr.irit.wanda.dao;

import java.util.ArrayList;
import java.util.Collection;
import org.junit.internal.matchers.IsCollectionContaining;

import fr.irit.wanda.entities.Entity;
import fr.irit.wanda.entities.LinkedMetadata;
import fr.irit.wanda.entities.Metadata;
import fr.irit.wanda.exception.AlreadyRegistredException;
import fr.irit.wanda.exception.NotAllowedToProceedException;

public class MetatadaContentAO extends DAO {

	public MetatadaContentAO() {
		super();
	}

	public LinkedMetadata get(Entity e, Metadata m) {
		LinkedMetadata lm = new LinkedMetadata(m, e);
		set("SELECT * FROM " + getLinkingTable(e) + " WHERE metadata = ?;");
		setInt(1, lm.getId());
		if (executeQuery())
			lm.setContent(getString("content"));
		return lm;
	}

	/**
	 * 
	 * @param lm
	 * @return
	 * @throws AlreadyRegistredException
	 * @throws NotAllowedToProceedException entity not concerned by the metadata
	 */
	public boolean add(LinkedMetadata lm) throws AlreadyRegistredException,
			NotAllowedToProceedException {
		MetadataConcernsAO concernsAccessObject = new MetadataConcernsAO();
		if (exists(lm)) // If the metadata already exists: error
			throw new AlreadyRegistredException("Entity and metadata are already bound by a value.");
		if (!concernsAccessObject.isConcerned(lm, lm.getConcerned()))
			throw new NotAllowedToProceedException(
					"Entity is not concerned by the metadata: cannot add content to it.");

		set("INSERT INTO " + getLinkingTable(lm.getConcerned()) + " ("
				+ lm.getConcerned().getEntityName()
				+ ",metadata,content) VALUES (?,?,?);");
		setInt(1, lm.getConcerned().getId());
		setInt(2, lm.getId());
		setString(3, lm.getContent());
		return executeUpdate();
	}

	public Collection<LinkedMetadata> getMetadatas(Entity e) {
		ArrayList<LinkedMetadata> lmc = new ArrayList<LinkedMetadata>();
		String linkingTable = getLinkingTable(e);
		set("SELECT "
				+ linkingTable
				+ ".content, metadata.idmetadata, metadata.name, metadata.description, metadata.hoover, metadata.obligation "
				+ "FROM metaconcerns, metadata, " + linkingTable
				+ " WHERE metaconcerns.concerns=? "
				+ "AND metaconcerns.idmetadata=metadata.idmetadata " + "AND "
				+ linkingTable + ".metadata=metaconcerns.idmetadata " + "AND "
				+ linkingTable + "." + e.getEntityName() + "=?;");
		setString(1, e.getEntityName());
		setInt(2, e.getId());
		executeQuery();
		do {
			lmc.add(new LinkedMetadata(new MetadataAO().extract(this.rs), e));
		} while (next());

		return lmc;
	}

	/**
	 * Returns true if the given linked meatada is set
	 * 
	 * @param lm
	 *            LinkedMetadata
	 * @return boolean
	 */
	public boolean exists(LinkedMetadata lm) {
		if (lm.getId() != -1 && lm.getConcerned().getId() != -1) {
			Entity concerned = lm.getConcerned();
			set("SELECT * FROM " + getLinkingTable(concerned) + " WHERE "
					+ concerned.getEntityName() + "=? AND metadata=?");
			setInt(1, concerned.getId());
			setInt(2, lm.getId());
			return executeQuery();
		}
		return false;
	}

	private String getLinkingTable(Entity e) {
		return e.getEntityName() + "meta";
	}
}
