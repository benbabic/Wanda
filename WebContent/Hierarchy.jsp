<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>
	<%@ page import="fr.irit.wanda.dao.*"%>
	<%@ page import="fr.irit.wanda.entities.*"%>
	<%@ page import="java.util.Collection"%>
	<%@ page import="java.io.*"%>

	<div id="sidebar">
		<h3>Arborescence</h3>
		<ol class="tree">

			<%
				SiteAO sao = new SiteAO();
				Collection<Site> sites = sao.getAll();
				for (Site s : sites) {
					String chaine = afficheContainer(sao.get(s.getName()), "");
					out.print(chaine);
				}
				
			%>

		</ol>
	</div>

	<%!
	// creer le texte du <li> au </li> pour chaque élément. cf hierarchie de val
	public String afficheContainer(NamedEntity container, String chaine) {
		ContainerAO cao = new ContainerAO();

		if (cao.isContainer(container)) {
			chaine += "<li>"; // on cree son element de liste

			chaine += "<label for=\"folder\">"; // on l'identifie
			chaine += container.getEntityName();
			chaine += "</label>";

			chaine += "<input type=\"checkbox\" id=\"folder\" />"; // syle
			chaine += "<ol>"; // on commence une sous liste
			for (NamedEntity e : cao.getContent(container)) {
				chaine += afficheContainer(e, ""); // on cree chaque élément fils de son <li> a son </li>
			}
			chaine += "</ol>"; // on ferme la liste des fils

			chaine += "</li>"; // on clos cet élément
		} else {
			chaine += "<li class=\"file\">";
			chaine += "<a href=\"\">";
			chaine += container.getEntityName();
			chaine += "</a>";
			chaine += "</li>";
		}

		return chaine; // on retourne le texte html a afficher
	}%>
</body>
</html>