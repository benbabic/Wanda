package fr.irit.wanda.common;

import fr.irit.wanda.entities.LinkedMetadata;
import fr.irit.wanda.entities.User;
import fr.irit.wanda.entities.Video;
import fr.irit.wanda.exception.NotAllowedToProceedException;

public interface ICorpusManager {

	public int addUser(User user);

	public int addVideo(Video video);

	boolean addMetadata(LinkedMetadata lm) throws NotAllowedToProceedException;
}
