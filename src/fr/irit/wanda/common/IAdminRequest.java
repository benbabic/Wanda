package fr.irit.wanda.common;

import fr.irit.wanda.entities.*;

public interface IAdminRequest {

	public int addA3(A3 a3);
	
	public void addGestionnaireSite(Site s, User u);
	
	public int addSite(Site s);
	
}
