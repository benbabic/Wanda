package fr.irit.wanda.serveur;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.irit.wanda.common.IRequest;
import fr.irit.wanda.dao.*;
import fr.irit.wanda.entities.*;
import fr.irit.wanda.exception.*;

public class ClientRequest implements IRequest {

	private static Logger logger = Logger.getLogger("RequestImpl");
	private MetadataAO metadataAccessObject = new MetadataAO();
	private RoleAO roleAccessObject = new RoleAO();
	private UserAO userAccessObject = new UserAO();
	private SiteAO siteAccessObject = new SiteAO();
	private WorkflowAO workflowAccessObject = new WorkflowAO();
	private CorpusAO corpusAccessObject = new CorpusAO();
	private SessionAO sessionAccessObject = new SessionAO();
	private MontageAO montageAccessObject = new MontageAO();
	private ViewAO viewAccessObject = new ViewAO();
	//private AnnotationAO annotationAccessObject = new AnnotationAO();
	private RuleAO ruleAccessObject = new RuleAO();
	private TypeAO typeAccessObject = new TypeAO();
	private VideoAO videoAccessObject = new VideoAO();
	private MetatadaContentAO metadataContentAccessObject = new MetatadaContentAO();
	private DescribedEntityAO DescribedEntityAccessObject = new DescribedEntityAO();
	private NamedEntityAO NamedEntityAccessObject = new NamedEntityAO();
	
	
	public ClientRequest(){
		logger.setLevel(Level.ALL);
	}
	
	@Override
	public int addMetadata(Metadata m) {
		try {
			return metadataAccessObject.add(m);
		} catch (AlreadyRegistredException e) {
			try {
				return metadataAccessObject.get(m.getName()).getId();
			} catch (NotFoundInDatabaseException e1) {
				return -1;
			}
		}
	}

	@Override
	public int addRole(Role r) {
		try {
			return roleAccessObject.add(r);
		} catch (AlreadyRegistredException e) {
			try {
				return roleAccessObject.get(r.getName()).getId();
			} catch (NotFoundInDatabaseException e1) {
				return -1;
			}
		}
	}

	@Override
	public Collection<Role> getRoles() {
		Collection<Role> result = new ArrayList<Role>();
		try {
			result.addAll(roleAccessObject.getAll());
		} catch (NotFoundInDatabaseException e) {
			logger.fine(e.getMessage());
		}
		return result;
	}

	@Override
	public int addUser(User u) {
		try {
			return userAccessObject.add(u);
		} catch (AlreadyRegistredException e) {
			try {
				return userAccessObject.get(u.getMail()).getId();
			} catch (NotFoundInDatabaseException e1) {
				return -1;
			}
		}
	}

	@Override
	public int addAnnotation(Annotation a) {
		/*try {
			return annotationAccessObject.add(a);
		} catch (AlreadyRegistredException e) {
			return -1;
		}*/
		return -1;
	}

	@Override
	public int addCorpus(Corpus c) {
		try {
			return corpusAccessObject.add(c);
		} catch (AlreadyRegistredException e) {
			try {
				return corpusAccessObject.get(c.getName()).getId();
			} catch (NotFoundInDatabaseException e1) {
				System.out.println(e1.toString());
				return -1;
			}
		}
	}

	@Override
	public int addMontage(Montage m) {
		try {
			return montageAccessObject.add(m);
		} catch (AlreadyRegistredException e) {
			try {
				return montageAccessObject.get(m.getName()).getId();
			} catch (NotFoundInDatabaseException e1) {
				return -1;
			}
		}
	}

	@Override
	public int addSession(Session s) {
		try {
			return sessionAccessObject.add(s);
		} catch (AlreadyRegistredException e) {
			try {
				return sessionAccessObject.get(s.getName()).getId();
			} catch (NotFoundInDatabaseException e1) {
				return -1;
			}
		}
	}

	@Override
	public int addView(View v) {
		try {
			return viewAccessObject.add(v);
		} catch (AlreadyRegistredException e) {
			try {
				return viewAccessObject.get(v.getName()).getId();
			} catch (NotFoundInDatabaseException e1) {
				return -1;
			}
		}
	}

	@Override
	public int addRule(Rule r) {
		try {
			return ruleAccessObject.add(r);
		} catch (AlreadyRegistredException e) {
			try {
				return ruleAccessObject.get(r.getName()).getId();
			} catch (NotFoundInDatabaseException e1) {
				return -1;
			}
		}
	}

	@Override
	public int addType(Type t) {
		try {
			return typeAccessObject.add(t);
		} catch (AlreadyRegistredException e) {
			try {
				return typeAccessObject.get(t.getName()).getId();
			} catch (NotFoundInDatabaseException e1) {
				return -1;
			}
		}
	}

	@Override
	public int addSite(Site s) {
		try {
			return siteAccessObject.add(s);
		} catch (AlreadyRegistredException e) {
			try {
				return siteAccessObject.get(s.getName()).getId();
			} catch (NotFoundInDatabaseException e1) {
				return -1;
			}
		}
	}

	@Override
	public int addVideo(Video v) {
		try {
			return videoAccessObject.add(v);
		} catch (AlreadyRegistredException e) {
			try {
				return videoAccessObject.get(v.getName()).getId();
			} catch (NotFoundInDatabaseException e1) {
				return -1;
			}
		}
	}

	@Override
	public int addWorkflow(Workflow w) {
		try {
			return workflowAccessObject.add(w);
		} catch (AlreadyRegistredException e) {
			try {
				return workflowAccessObject.get(w.getName()).getId();
			} catch (NotFoundInDatabaseException e1) {
				return -1;
			}
		}
	}

	@Override
	public boolean addLinkMetadata(LinkedMetadata lm) {
		try {
		return metadataContentAccessObject.add(lm);
		} catch (AlreadyRegistredException e){
			return metadataContentAccessObject.get(lm.getConcerned(), lm).getId() != -1;
		} catch (NotAllowedToProceedException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private Collection<DescribedEntity> getAllDescribedEntity(String tableName) throws NotFoundInDatabaseException{
		return DescribedEntityAccessObject.getAll(tableName);
	}

	@Override
	public Collection<Corpus> getCorpus() {
		Collection<Corpus> result = new ArrayList<Corpus>();
		try {
			result.addAll(corpusAccessObject.getAll());
		} catch (NotFoundInDatabaseException e) {
			logger.fine(e.getMessage());
		}
		return result;
	}


	@Override
	public Collection<Site> getSites() {
		Collection<Site> result = new ArrayList<Site>();
		try {
			result.addAll(siteAccessObject.getAll());
		} catch (NotFoundInDatabaseException e) {
			logger.fine(e.getMessage());
		}
		return result;
	}

	@Override
	public Collection<View> getViews() {
		Collection<View> result = new ArrayList<View>();
		try {
			result.addAll(viewAccessObject.getAll());
		} catch (NotFoundInDatabaseException e) {
			logger.fine(e.getMessage());
		}
		return result;
	}

	public NamedEntity getID(NamedEntity de){
		return NamedEntityAccessObject.getID(de);
	}

	@Override
	public Collection<Rule> getRules() {
		Collection<Rule> result = new ArrayList<Rule>();
		try {
			result.addAll(ruleAccessObject.getAll());
		} catch (NotFoundInDatabaseException e) {
			logger.fine(e.getMessage());
		}
		return result;
	}

	@Override
	public Collection<Workflow> getWorkflows() {
		Collection<Workflow> result = new ArrayList<Workflow>();
		try {
			result.addAll(workflowAccessObject.getAll());
		} catch (NotFoundInDatabaseException e) {
			logger.fine(e.getMessage());
		}
		return result;
	}

	@Override
	public Collection<Type> getTypes() {
		Collection<Type> result = new ArrayList<Type>();
		try {
			result.addAll(typeAccessObject.getAll());
		} catch (NotFoundInDatabaseException e) {
			logger.fine(e.getMessage());
		}
		return result;
	}

	@Override
	public Collection<Session> getSessions() {
		Collection<Session> result = new ArrayList<Session>();
		try {
			result.addAll(sessionAccessObject.getAll());
		} catch (NotFoundInDatabaseException e) {
			logger.fine(e.getMessage());
		}
		return result;
	}

	@Override
	public Collection<Video> getVideos() {
		Collection<Video> result = new ArrayList<Video>();
		try {
			result.addAll(videoAccessObject.getAll());
		} catch (NotFoundInDatabaseException e) {
			logger.fine(e.getMessage());
		}
		return result;
	}

	@Override
	public int getRights(NamedEntity e, User u) {
		return new RightsAO().getRights(e, u);
	}
}