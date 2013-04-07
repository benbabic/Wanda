package fr.irit.wanda.serveur;

import fr.irit.wanda.common.ICorpusManager;
import fr.irit.wanda.dao.MetatadaContentAO;
import fr.irit.wanda.dao.VideoAO;
import fr.irit.wanda.entities.LinkedMetadata;
import fr.irit.wanda.entities.User;
import fr.irit.wanda.entities.Video;
import fr.irit.wanda.exception.AlreadyRegistredException;
import fr.irit.wanda.exception.NotAllowedToProceedException;
import fr.irit.wanda.exception.NotFoundInDatabaseException;

public class CorpusManagerRequest implements ICorpusManager {

	@Override
	public boolean addMetadata(LinkedMetadata lm)
			throws NotAllowedToProceedException {
		if (lm.getConcerned().getEntityName() != "corpus")
			throw new NotAllowedToProceedException(
					"This operation is only permitted on a corpus!");

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
	public int addUser(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addVideo(Video video) {
		VideoAO videoAccessObject = new VideoAO();

		try {
			return videoAccessObject.add(video);
		} catch (AlreadyRegistredException e) {
			try {
				return videoAccessObject.get(video.getName()).getId();
			} catch (NotFoundInDatabaseException e1) {
				return -1;
			}
		}
	}

}
