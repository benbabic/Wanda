package fr.irit.wanda.common;

import fr.irit.wanda.entities.Annotation;
import fr.irit.wanda.entities.Container;
import fr.irit.wanda.entities.Corpus;
import fr.irit.wanda.entities.DescribedEntity;
import fr.irit.wanda.entities.LinkedMetadata;
import fr.irit.wanda.entities.Metadata;
import fr.irit.wanda.entities.Montage;
import fr.irit.wanda.entities.NamedEntity;
import fr.irit.wanda.entities.Rights;
import fr.irit.wanda.entities.Role;
import fr.irit.wanda.entities.Rule;
import fr.irit.wanda.entities.Session;
import fr.irit.wanda.entities.Site;
import fr.irit.wanda.entities.Type;
import fr.irit.wanda.entities.User;
import fr.irit.wanda.entities.Video;
import fr.irit.wanda.entities.View;
import fr.irit.wanda.entities.Workflow;
import fr.irit.wanda.exception.NotFoundInDatabaseException;

import java.util.ArrayList;
import java.util.Collection;

public interface IRequest {
	
	/**
	 * Returns the id of the created metadata or -1 if none was created
	 * 
	 * @param m
	 * @return
	 */
	public int addMetadata(Metadata m);
	
	public int addRole(Role r);
	
	public Collection<Role> getRoles();
	
	public int addUser(User u);
	
	public int addAnnotation(Annotation a);
	
	public int addCorpus(Corpus c);
	
	public Collection<Corpus> getCorpus();
	
	public int addMontage(Montage m);
	
	public int addSession(Session s);
	
	public Collection<Session> getSessions();
	
	public int addView(View v);
	
	public int addRule(Rule r);
	
	public Collection<Rule> getRules();
	
	public int addType(Type t);
	
	public Collection<Type> getTypes();
	
	public int addSite(Site s);
	
	public Collection<Site> getSites();
	
	public int addVideo(Video v);
	
	public Collection<Video> getVideos();
	
	public Collection<View> getViews();
	
	public int addWorkflow(Workflow w);
	
	public Collection<Workflow> getWorkflows();
	
	public boolean addLinkMetadata(LinkedMetadata lm);
	
	public NamedEntity getID(NamedEntity de);
	
	public int getRights(NamedEntity e, User u);
	
		
}
