package fr.irit.wanda.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.irit.wanda.entities.Annotation;
import fr.irit.wanda.entities.Corpus;
import fr.irit.wanda.entities.DescribedEntity;
import fr.irit.wanda.entities.Entity;
import fr.irit.wanda.entities.Metadata;
import fr.irit.wanda.entities.Montage;
import fr.irit.wanda.entities.NamedEntity;
import fr.irit.wanda.entities.Role;
import fr.irit.wanda.entities.Rule;
import fr.irit.wanda.entities.Session;
import fr.irit.wanda.entities.Site;
import fr.irit.wanda.entities.Type;
import fr.irit.wanda.entities.User;
import fr.irit.wanda.entities.Video;
import fr.irit.wanda.entities.View;
import fr.irit.wanda.entities.Workflow;
import fr.irit.wanda.serveur.Instanciator;

/**
 * Servlet implementation class New_entities
 */
@WebServlet("/New_entities")
public class New_entities extends Servlet {
	enum Type_entities {
		METADATA, USER, RULE, ROLE, SITE, SESSION, WORKFLOW, TYPE, CORPUS, VIDEO, VIEW, ANNOTATION, MONTAGE
	}

	private static final long serialVersionUID = 1L;

	private Instanciator instanciator = new Instanciator();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public New_entities() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String message = null;
		Type_entities ent = Type_entities.valueOf(getString(request,
				"hidden_field").toUpperCase());

		switch (ent) {
		case METADATA:
			message = handlerMetadata(request);
			break;
		case USER:
			message = handlerUser(request);
			break;
		case RULE:
			message = handlerRule(request);
			break;
		case ROLE:
			message = handlerRole(request);
			break;
		case SITE:
			message = handlerSite(request);
			break;
		case SESSION:
			message = handlerSession(request);
			break;
		case WORKFLOW:
			message = handlerWorkflow(request);
			break;
		case TYPE:
			message = handlerType(request);
			break;
		case CORPUS:
			message = handlerCorpus(request);
			break;
		case VIDEO:
			message = handlerVideo(request);
			break;
		case VIEW:
			message = handlerView(request);
			break;
		case ANNOTATION:
			message = handlerAnnotation(request);
			break;
		case MONTAGE:
			message = handlerMontage(request);
			break;

		default:
		}
		try {
			request.setAttribute("confirm", message);
			getServletContext().getRequestDispatcher("/Home.jsp")
					.forward(request, response);
		} catch (ServletException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			e.printStackTrace();
		}
	}

	private String handlerAnnotation(HttpServletRequest request) {
	    Workflow w = instanciator.workflow(getString(request, "workflow_annotation"));
	    Video e = instanciator.video(getString(request, "video_annotation"));
	    View v = instanciator.view(getString(request, "view_annotation"));
		Annotation a = new Annotation(w, e, v, getString(request, "name_annotation"));


		ccfg.remoteRequest.addAnnotation(a);
		return "Votre annotation a bien été ajoutée";
	}

	private String handlerMontage(HttpServletRequest request) {

		String[] list_view = request.getParameterValues("views_montage");
		Collection<View> views = new ArrayList<View>();
		
		for (String s : list_view){
			views.add(instanciator.view(s));
		}
		
	    Session s = instanciator.session(getString(request, "session_montage"));
		
		Montage m = new Montage(views, s, getString(request, "link_montage"), getString(request, "name_montage"));
		ccfg.remoteRequest.addMontage(m);
		return "Votre montage a bien été ajouté";
		
	}

	private String handlerMetadata(HttpServletRequest request) {
		ArrayList<Entity> ear = new ArrayList<Entity>();
		if (getString(request, "Video_meta") != null) {
			ear.add(new Entity("video"));
		}
		if (getString(request, "Annotation_meta") != null) {
			ear.add(new Entity("annotation"));
		}
		if (getString(request, "Corpus_meta") != null) {
			ear.add(new Entity("corpus"));
		}
		if (getString(request, "Vue_meta") != null) {
			ear.add(new Entity("view"));
		}
		if (getString(request, "Site_meta") != null) {
			ear.add(new Entity("site"));
		}
		if (getString(request, "Session_meta") != null) {
			ear.add(new Entity("session"));
		}
		Metadata m = new Metadata(getString(request, "name_meta"), getBoolean(
				request, "obligation_meta"), getString(request, "hoover_meta"),
				getString(request, "description_meta"));
		m.setConcerns(ear);

		// Ajout de la metadata
		ccfg.remoteRequest.addMetadata(m);

		return "Votre metadonnee a bien été ajoutée";
	}

	private String handlerUser(HttpServletRequest request) {
		Role r = instanciator.role(getString(request, "role_user"));

		User u = new User(getString(request, "name_user"), getString(request,
				"prenom_user"), r, getString(request, "mail_user"));
		ccfg.remoteRequest.addUser(u);

		return "Votre user a bien été ajouté";
	}

	private String handlerRule(HttpServletRequest request) {
		Rule r = new Rule(getString(request, "name_rule"), getString(request,
				"description_rule"));

		ccfg.remoteRequest.addRule(r);

		return "Votre rule a bien été ajoutée";
	}

	private String handlerRole(HttpServletRequest request) {
		Role r = new Role(getString(request, "name_role"), getString(request,
				"desc_role"), getInt(request, "autorisation_role"));
		ccfg.remoteRequest.addRole(r);

		return "Votre role a bien été ajouté";
	}

	private String handlerSite(HttpServletRequest request) {
		Rule r = instanciator.rule(getString(request, "rule_site"));

		Site s = new Site(r, getString(request, "name_site"));
		ccfg.remoteRequest.addSite(s);

		return "Votre site a bien été ajouté";
	}

	private String handlerSession(HttpServletRequest request) {
		Rule r = instanciator.rule(getString(request, "rule_session"));
		Corpus c = instanciator.corpus(getString(request, "corpus_session"));

		Session s = new Session(r, c, getString(request, "name_session"));
		ccfg.remoteRequest.addSession(s);

		return "Votre session a bien été ajoutée";
	}

	private String handlerWorkflow(HttpServletRequest request) {
		Workflow w = new Workflow(getString(request, "name_workflow"),
				getString(request, "desc_worflow"));

		ccfg.remoteRequest.addWorkflow(w);

		return "Votre workflow a bien été ajouté";
	}

	private String handlerType(HttpServletRequest request) {
		Type w = new Type(getString(request, "name_type"), getString(request,
				"desc_typew"));

		ccfg.remoteRequest.addType(w);

		return "Votre type a bien été ajouté";
	}

	private String handlerCorpus(HttpServletRequest request) {
		Rule r = instanciator.rule(getString(request, "rule_corpus"));
		Corpus father = instanciator.corpus(getString(request, "corpus_corpus"));
		Site s = instanciator.site(getString(request, "site_corpus"));
		
		Corpus c = new Corpus(r, s, father, getString(request, "name_corpus"));
		ccfg.remoteRequest.addCorpus(c);

		return "Votre corpus a bien été ajouté";
	}

	private String handlerVideo(HttpServletRequest request) {
		Workflow w = instanciator
				.workflow(getString(request, "workflow_video"));
		View vus = instanciator.view(getString(request, "view_video"));

		Video v = new Video(w, vus, getString(request, "name_video"));
		ccfg.remoteRequest.addVideo(v);

		return "Votre video a bien été ajoutée";
	}

	private String handlerView(HttpServletRequest request) {
		Type t = instanciator.type(getString(request, "type_view"));
		Rule r = instanciator.rule(getString(request, "rule_view"));
		Session s = instanciator.session(getString(request,
						"session_view"));

		View v = new View(t, r, s, getString(request, "name_view"));
		ccfg.remoteRequest.addView(v);

		return "Votre view a bien été ajoutée";
	}
}
