<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" media="screen" type="text/css" title="Design" href="css/style_new.css" />
<title>New meta</title>
</head>
<body>
	<%@ page import="fr.irit.wanda.entities.*"%>
	<%@ page import="fr.irit.wanda.servlet.ClientConfiguration"%>
	
	<strong>Ajout de metadata</strong>
	<form method="post" action="New_entities" id="add_Meta" name="Metadata"><br>
		<input type="hidden"  name="hidden_field"  value="metadata">
		<label for="name_meta">name :</label><input name="name_meta" type="text" size="40"/><br>
		<label for="description_meta">Description :</label><input name="description_meta" type="text" size="40"/><br>
		<label for="hoover_meta">Hoover :</label><input name="hoover_meta" type="text" size="40"/><br>
		<label for="obligation_meta">Obligation :</label><br>
		<input type="radio" name="obligation_meta" value="true" id="true_obligation" /><label class="radio" for="true_obligation">true</label><br>
       	<input type="radio" name="obligation_meta" value="false" id="false_obligation" /><label class="radio" for="false_obligation">false</label><br />
  		<br>
		<div id="conteneur_box">
		<%
			String ts[] = {"Video","Annotation","Vue","Session","Corpus","Site"};
			for (String s : ts){
				%>
				<input type="checkbox" name="<% out.print(s+"_meta");%>" value="<%out.print(s);%>"><% out.print(s);%>
				<%
			}
		%>
		</div>
		<br>
		<input name="valider" type="submit" size="40" value="Valider"/>
	</form><br>
	
	
	<strong>Ajout de role</strong>
	<form method="post" action="New_entities" id="add_Role" name="Role"><br>
		<input type="hidden"  name="hidden_field"  value="role">
		<label for="name_role">name :</label><input name="name_role" type="text" size="40"/><br>
		<label for="desc_role">Description :</label><input name="desc_role" type="text" size="40"/><br>
		<label for=autorisation_role>Autorisation :</label>
			<select name="autorisation_role" size="1">
				<option>0
				<option>1
				<option>2
				<option>3
				<option>4
				<option>5				
			</select>
		<br>
		<input name="valider" type="submit" size="40" value="Valider"/>
	</form><br>
	
	
	<strong>Ajout de rule</strong>
	<form method="post" action="New_entities" id="add_rule" name="Rule"><br>
		<input type="hidden"  name="hidden_field"  value="rule">
		<label for="name_rule">name :</label><input name="name_rule" type="text" size="40"/><br>
		<label for="description_rule">Description :</label><input name="description_rule" type="text" size="40"/><br>
		<br>
		<input name="valider" type="submit" size="40" value="Valider"/>
	</form><br>
	
	
	<strong>Ajout de workflow</strong>
	<form method="post" action="New_entities" id="add_worflow" name="Workflow"><br>
		<input type="hidden"  name="hidden_field"  value="workflow">
		<label for="name_workflow">Name :</label><input name="name_workflow" type="text" size="40"/><br>
		<label for="desc_worflow">Description :</label><input name="desc_workflow" type="text" size="40"/><br>
		<input name="valider" type="submit" size="40" value="Valider"/>
	</form><br>
	
	<strong>Ajout d'utilisateur</strong>
	<form method="post" action="New_entities" id="search" name="User"><br>
		<input type="hidden"  name="hidden_field"  value="user">
		<label for="name_user">name :</label><input name="name_user" type="text" size="40"/><br>
		<label for="prename_user">Prename :</label><input name="prename_user" type="text" size="40"/><br>
		<label for="role_user">Role :</label><select name="role_user" size="1">
			<%
			for (Role r:new ClientConfiguration().remoteRequest.getRoles()){
					%>
				<option><% out.print(r.getName());
				}				
				%>
			
		</select><br> 
		<label for="mail_user">Mail :</label><input name="mail_user" type="text" size="40"/><br>
		<br>
		<input name="valider" type="submit" size="40" value="Valider"/>
	</form>
	
	<strong>Ajout de session</strong>
	<form method="post" action="New_entities" id="add_session" name="Session"><br>
	<input type="hidden"  name="hidden_field"  value="session">
		<label for="name_session">name :</label><input name="name_session" type="text" size="40"/><br>
		<label for="rule_session">Rule :</label> <select name="rule_session" size="1">
			<%
			for (Rule r:new ClientConfiguration().remoteRequest.getRules()){
					%>
				<option><% out.print(r.getName());
				}				
				%>
			
		</select><br>
		<label for="corpus_session">Corpus :</label> <select name="corpus_session" size="1">
			<%
			//for (Corpus c:new ClientConfiguration().remoteRequest.getCorpus()){
					%>
				<option><% //out.print(c.getName());
				//}				
				%>
			
		</select><br>
		<input name="valider" type="submit" size="40" value="Valider"/>
	</form><br>
	<strong>Ajout de site</strong>
	<form method="post" action="New_entities" id="add_site" name="Site"><br>
		<input type="hidden"  name="hidden_field"  value="site">
		<label for="name_site">name :</label><input name="name_site" type="text" size="40"/><br>
		<label for="rule_site">Rule :</label> <select name="rule_site" size="1">
			<%
			for (Rule r:new ClientConfiguration().remoteRequest.getRules()){
					%>
				<option><% out.print(r.getName());
				}				
				%>
			
		</select><br>
		<input name="valider" type="submit" size="40" value="Valider"/>
	</form>
		<br>
		
	<strong>Ajout de type</strong>
	<form method="post" action="New_entities" id="add_type" name="Type"><br>
		<input type="hidden"  name="hidden_field"  value="type">
		<label for="name_type">name :</label><input name="name_type" type="text" size="40"/><br>
		<label for="desc_type">Description :</label><input name="desc_type" type="text" size="40"/><br>
		<input name="valider" type="submit" size="40" value="Valider"/>
	</form><br>
		<br>
		
	<strong>Ajout de corpus</strong>
	<form method="post" action="New_entities" id="add_corpus" name="Corpus"><br>
		<input type="hidden"  name="hidden_field"  value="corpus">
		<label for="name_corpus">name :</label><input name="name_corpus" type="text" size="40"/><br>
		<label for="rule_corpus">Rule :</label> 
			<select name="rule_corpus" size="1">
			<%
			for (Rule r:new ClientConfiguration().remoteRequest.getRules()){
					%>
				<option><% out.print(r.getName());
				}				
				%>
			
			</select><br>
			<label for="site_corpus">Site :</label> 
			<select name="site_corpus" size="1">
			<%
			for (Site s:new ClientConfiguration().remoteRequest.getSites()){
					%>
				<option><% out.print(s.getName());
				}				
				%>
			
			</select><br>
			<label for="corpus_corpus">Corpus :</label> 
			<select name="corpus_corpus" size="1">
			<%
			//for (Corpus c:new ClientConfiguration().remoteRequest.getCorpus()){
					%>
				<option><% //out.print(c.getName());
				//}				
				%>
			
			</select><br>
		<input name="valider" type="submit" size="40" value="Valider"/>
	</form><br>
	
	<strong>Ajout de video</strong>
	<form method="post" action="New_entities" id="add_video" name="Video"><br>
		<input type="hidden"  name="hidden_field"  value="video">
		<label for="name_video">name :</label><input name="name_user" type="text" size="40"/><br>
		<label for="workflow_video">Workflow :</label> <select name="workflow_video" size="1">
			<%
			for (Workflow w:new ClientConfiguration().remoteRequest.getWorkflows()){
					%>
				<option><% out.print(w.getName());
				}				
				%>
			
		</select>
		<label for="view_video">Workflow :</label> <select name="view_video" size="1">
			<%
			for (View v:new ClientConfiguration().remoteRequest.getViews()){
					%>
				<option><% out.print(v.getName());
				}				
				%>
			
		</select>
		<br>
		<input name="valider" type="submit" size="40" value="Valider"/>
	</form><br>
	
	<strong>Ajout de view</strong>
	<form method="post" action="New_entities" id="add_view" name="View"><br>
		<input type="hidden"  name="hidden_field"  value="view">
		<label for="name_view">name :</label><input name="name_view" type="text" size="40"/><br>
		<label for="type_view">Type :</label> 
			<select name="type_view" size="1">
			<%
			for (Type t:new ClientConfiguration().remoteRequest.getTypes()){
					%>
				<option><% out.print(t.getName());
				}				
				%>
			
			</select><br>
			<label for="rule_view">Rule :</label> 
			<select name="rule_view" size="1">
			<%
			for (Rule r:new ClientConfiguration().remoteRequest.getRules()){
					%>
				<option><% out.print(r.getName());
				}				
				%>
			
			</select><br>
			<label for="session_view">Session :</label> 
			<select name="session_view" size="1">
			<%
			for (Session s:new ClientConfiguration().remoteRequest.getSessions()){
					%>
				<option><% out.print(s.getName());
				}				
				%>
			
			</select><br>
		<input name="valider" type="submit" size="40" value="Valider"/>
	</form><br>
<!-- 
	<strong>Ajout d'annotation</strong>
	<form method="post" action="New_annotation" id="add_Annotation" name="Annotation"><br>
		<label for="name_annotation">name :</label><input name="name_user" type="text" size="40"/><br>
		<label for="workflow_annotation">Worflow :</label> <select name="worflow_annotation" size="1">
			<%
			//for (Workflow w:new ClientConfiguration().remoteRequest.getWorkflows()){
					%>
				<option><% //out.print(w.getName());
				//}				
				%>
			
		</select><br>
		<label for="view_annotation">View :</label> <select name="view_annotation" size="1">
			<%
			//for (View vi:new ClientConfiguration().remoteRequest.getViews()){
					%>
				<option><% //out.print(vi.getName());
				//}				
				%>
			
		</select><br>
		<label for="video_annotation">Video :</label> <select name="video_annotation" size="1">
			<%
			//for (Video v:new ClientConfiguration().remoteRequest.getVideos()){
					%>
				<option><% //out.print(v.getName());
			//}			
				%>
			
		</select><br>
		<input name="valider" type="submit" size="40" value="Valider"/>
	</form><br>
	
	
	<strong>Ajout de montage</strong>
	<form method="post" action="New_montage" id="add_montage" name="Montage"><br>
		<label for="name_user">name :</label><input name="name_user" type="text" size="40"/><br>
		<label for="prenom_user">Prename :</label><input name="prenom_user" type="text" size="40"/><br>
		<label for="role_user">Role :</label> <select name="role_user" size="1">
			<%
			//for (Role r:new ClientConfiguration().remoteRequest.getRoles()){
					%>
				<option><% //out.print(r.getName());
				//}				
				%>
			
		</select><br>
		<br>
		<input name="valider" type="submit" size="40" value="Valider"/>
	</form><br>
	-->
		<% String c =(String) request.getAttribute("confirm");
		String e = (String) request.getAttribute("erreur");
			// Récupération du message de confirmation
			if (c != null) {
				%><script>alert('<%out.print(c);%>');</script><%	
			}else if(e != null){
				%><script>alert('<%out.print(e);%>');</script><%
			}
			%><br>
			<a href="display_metadata.jsp">Display Metadata</a>
</body>
</html>