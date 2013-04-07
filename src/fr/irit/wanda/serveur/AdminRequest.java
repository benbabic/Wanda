package fr.irit.wanda.serveur;

import fr.irit.wanda.common.IAdminRequest;
import fr.irit.wanda.dao.A3AO;
import fr.irit.wanda.dao.RoleAO;
import fr.irit.wanda.dao.SiteAO;
import fr.irit.wanda.dao.UserAO;
import fr.irit.wanda.entities.A3;
import fr.irit.wanda.entities.Role.ROLE;
import fr.irit.wanda.entities.Site;
import fr.irit.wanda.entities.User;
import fr.irit.wanda.entities.User.ACCESS_RIGHT;
import fr.irit.wanda.exception.AlreadyRegistredException;
import fr.irit.wanda.exception.InvalidParameterException;
import fr.irit.wanda.exception.NotFoundInDatabaseException;

public class AdminRequest implements IAdminRequest {
	private User admin; // for future impl

	private AdminRequest(User admin) {
		this.admin = admin;
	}

	@Override
	public int addA3(A3 a3) {
		A3AO a3AccessObject = new A3AO();
		try {
			return a3AccessObject.add(a3);
		} catch (AlreadyRegistredException e) {
			try {
				return a3AccessObject.get(a3.getName()).getId();
			} catch (NotFoundInDatabaseException e1) {
				// should not happen
				return -1;
			}
		}
	}

	@Override
	public void addGestionnaireSite(Site site, User user) {
		UserAO userAccessObject = new UserAO();
		try {
			userAccessObject.setAccessRight(site, ACCESS_RIGHT.OWN, user);
		} catch (InvalidParameterException e) {
			// should not happen
		}
	}

	@Override
	public int addSite(Site s) {
		SiteAO siteAccessObject = new SiteAO();
		try {
			return siteAccessObject.add(s);
		} catch (AlreadyRegistredException e) {
			try {
				return siteAccessObject.get(s.getName()).getId();
			} catch (NotFoundInDatabaseException e1) {
				// should not happen
				return -1;
			}
		}
	}

	public AdminRequest getInstance(User admin) {
		if (ROLE.fromInt(admin.getRole().getId()) != ROLE.ADMIN)
			return new AdminRequest(admin);
		return null;
	}

}
