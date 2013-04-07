package fr.irit.wanda.serveur;

import fr.irit.wanda.entities.*;
import fr.irit.wanda.servlet.ClientConfiguration;

public class Instanciator {

	protected ClientConfiguration ccfg = new ClientConfiguration();

	public final Role role(String name) {
		NamedEntity ne = ccfg.remoteRequest
				.getID(new NamedEntity("role", name));
		return new Role(ne.getId(), ne.getName(), null);
	}

	public final Type type(String name) {
		NamedEntity ne = ccfg.remoteRequest
				.getID(new NamedEntity("type", name));
		return new Type(ne.getId(), ne.getName(), null);
	}

	public final Rule rule(String name) {
		NamedEntity ne = ccfg.remoteRequest
				.getID(new NamedEntity("rule", name));
		return new Rule(ne.getId(), ne.getName(), null);
	}

	public final Corpus corpus(String name) {
		NamedEntity ne = ccfg.remoteRequest
				.getID(new NamedEntity("corpus", name));
		return new Corpus(ne.getId(), null, null, ne.getName());
	}

	public final Site site(String name) {
		NamedEntity ne = ccfg.remoteRequest
				.getID(new NamedEntity("site", name));
		return new Site(ne.getId(), null, ne.getName());
	}

	public final View view(String name) {
		NamedEntity ne = ccfg.remoteRequest
				.getID(new NamedEntity("view", name));
		return new View(ne.getId(), null, null, null, ne.getName());
	}

	public final Workflow workflow(String name) {
		NamedEntity ne = ccfg.remoteRequest
				.getID(new NamedEntity("workflow", name));
		return new Workflow(ne.getId(), ne.getName(), null);
	}

	public final Session session(String name) {
		NamedEntity ne = ccfg.remoteRequest
				.getID(new NamedEntity("session", name));
		return new Session(ne.getId(), null, null, ne.getName());
	}
	
	public final Video video(String name) {
		NamedEntity ne = ccfg.remoteRequest
				.getID(new NamedEntity("video", name));
		return new Video(ne.getId(), null, null, ne.getName());
	}
}
