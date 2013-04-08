package fr.irit.wanda.ui;

import fr.irit.wanda.dao.ContainerAO;
import fr.irit.wanda.entities.NamedEntity;

public class General {

	public static String printHierarchy(NamedEntity container, String chaine) {
		ContainerAO cao = new ContainerAO();

		if (cao.isContainer(container)) {
			chaine += beginContainer(container.getName());
			for (NamedEntity e : cao.getContent(container)) {
				chaine += printHierarchy(e, ""); // on cree chaque élément fils de son <li> a son </li>
			}
			chaine += endContainer();
		} else {
			displayEntity(container.getName());
		}

		return chaine; // on retourne le texte html a afficher
	}
	
	private static String beginContainer(String name){
		String chaine = "";
		chaine += "<li>"; // on cree son element de liste

		chaine += "<label for=\"folder\">"; // on l'identifie
		chaine += name;
		chaine += "</label>";

		chaine += "<input type=\"checkbox\" id=\"folder\" />"; // syle
		chaine += "<ol>"; // on commence une sous liste
		
		return chaine;
	}
	
	private static String endContainer(){
		String chaine = "";
		chaine += "</ol>"; // on ferme la liste des fils
		chaine += "</li>"; // on clos cet élément
		
		return chaine;
	}
	
	private static String displayEntity(String name){
		String chaine = "";
		chaine += "<li class=\"file\">";
		chaine += "<a href=\"\">";
		chaine += name;
		chaine += "</a>";
		chaine += "</li>";
		
		return chaine;
	}
}
