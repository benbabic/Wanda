package fr.irit.wanda.serveur;

import fr.irit.wanda.common.ISiteManager;
import fr.irit.wanda.dao.CorpusAO;
import fr.irit.wanda.dao.MetatadaContentAO;
import fr.irit.wanda.dao.UserAO;
import fr.irit.wanda.entities.Corpus;
import fr.irit.wanda.entities.LinkedMetadata;
import fr.irit.wanda.entities.User;
import fr.irit.wanda.entities.User.ACCESS_RIGHT;
import fr.irit.wanda.exception.AlreadyRegistredException;
import fr.irit.wanda.exception.InvalidParameterException;
import fr.irit.wanda.exception.NotAllowedToProceedException;
import fr.irit.wanda.exception.NotFoundInDatabaseException;

public class SiteManagerRequest implements ISiteManager {

	@Override
	public int addCorpus(Corpus c) {
		CorpusAO corpusAccessObject = new CorpusAO();

		try {
			return corpusAccessObject.add(c);
		} catch (AlreadyRegistredException e) {
			try {
				return corpusAccessObject.get(c.getName()).getId();
			} catch (NotFoundInDatabaseException e1) {
				return -1;
			}
		}
	}

	@Override
	public boolean addMetadata(LinkedMetadata lm)
			throws NotAllowedToProceedException {
		if (lm.getConcerned().getEntityName() != "site")
			throw new NotAllowedToProceedException(
					"This operation is only permitted on a site!");

		MetatadaContentAO metadataContentAccessObject = new MetatadaContentAO();
		try {
			return metadataContentAccessObject.add(lm);
		} catch (AlreadyRegistredException e) {
			return metadataContentAccessObject.get(lm.getConcerned(), lm)
					.getId() != -1;
		} catch (NotAllowedToProceedException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void addCorpusManager(Corpus corpus, User user) {
		UserAO userAccessObject = new UserAO();
		try {
			userAccessObject.setAccessRight(corpus, ACCESS_RIGHT.OWN, user);
		} catch (InvalidParameterException e) {
			// should not happen
		}
	}

}
