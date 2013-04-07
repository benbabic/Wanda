package fr.irit.wanda.common;

import fr.irit.wanda.entities.*;
import fr.irit.wanda.exception.NotAllowedToProceedException;

public interface ISiteManager {

	public int addCorpus(Corpus c);

	boolean addMetadata(LinkedMetadata lm) throws NotAllowedToProceedException;

	public void addCorpusManager(Corpus corpus, User user);
}
