package fr.irit.wanda.test.dbaccess;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import fr.irit.wanda.dao.MetadataConcernsAO;
import fr.irit.wanda.dao.UserAO;
import fr.irit.wanda.entities.Corpus;
import fr.irit.wanda.entities.Entity;
import fr.irit.wanda.entities.LinkedMetadata;
import fr.irit.wanda.entities.Metadata;
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
import fr.irit.wanda.servlet.ClientConfiguration;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ClientConfiguration ccfg = new ClientConfiguration();

		// Test add rule
		Rule r = new Rule("Rule ", "Description");
		r.setId(ccfg.remoteRequest.addRule(r));

		// Test add site
		Site s = new Site(r, "MonSite");
		s.setId(ccfg.remoteRequest.addSite(s));

		// Test add role
		Role role = new Role("name", "desc", 1);
		role.setId(ccfg.remoteRequest.addRole(role));

		// Test creating new user with a father
		User creator = null;
		try {
			creator = new UserAO().get(0);
		} catch (NotFoundInDatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		User u = new User(1, "name", "forename", role, "mail", creator);
		u.setId(ccfg.remoteRequest.addUser(u));

		// Test add Corpus
		Corpus c = new Corpus(r, s, u, null, "Corpus1");
		c.setId(ccfg.remoteRequest.addCorpus(c));

		// Test add session
		Session se = new Session(r, c, "Session1", u);
		se.setId(ccfg.remoteRequest.addSession(se));

		// Test add workflow
		Workflow w = new Workflow("monWorkflow", "description");
		w.setId(ccfg.remoteRequest.addWorkflow(w));

		// Test add type
		Type t = new Type("monType", "extension");
		t.setId(ccfg.remoteRequest.addType(t));

		// Test add view
		View view = new View(t, r, se, "View1", u);
		view.setId(ccfg.remoteRequest.addView(view));

		// Test add video
		Video v = new Video(w, view, "Video1", u);
		v.setId(ccfg.remoteRequest.addVideo(v));

		// Test add metadata concerns
		ArrayList<Entity> concerns = new ArrayList<Entity>();
		concerns.add(v);
		concerns.add(se);

		// Test add metadata
		Metadata m = new Metadata("Description2", true, " ", "Public description");
		m.setConcerns(concerns);
		m.setId(ccfg.remoteRequest.addMetadata(m));
		
		// Test add linked metadata
		LinkedMetadata lm = new LinkedMetadata(m,v,"This video is about a movie");
		ccfg.remoteRequest.addLinkMetadata(lm);
		
		//Previous test all succeed 07/04/2013 00:08 [GMT +1]

		// System.out.println(get("http://localhost:8888/NewWanda/Test?action=hgkjgfjhkg"));
	}

	public static String get(String urlALire) {
		URL url;
		HttpURLConnection conn;
		BufferedReader rd;
		String line;
		String result = "";
		try {
			url = new URL(urlALire);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			rd = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			while ((line = rd.readLine()) != null) {
				result += line;
			}
			rd.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
