<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Wanda - Home</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" media="screen" type="text/css" title="Design"
	href="css/home_style.css" />
<link rel="stylesheet" media="screen" type="text/css" title="Design"
	href="css/menu_style.css" />
<link rel="stylesheet" media="screen" type="text/css" title="Design"
	href="css/arborescence_style.css" />
<script type="text/javascript" src="js/script.js"></script>
<script type="text/javascript" src="js/script_displayDiv.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#MainBack').load('html-content-file.html');
	});

	function change_div(id) {
		var id_tohide = $('.current').attr('id');
		$('#' + id_tohide).removeClass('current');
		$('#' + id_tohide).addClass('hidden');
		$('#' + id + 'Form').removeClass('hidden');
		$('#' + id + 'Form').addClass('current');
	}
</script>
</head>
<body>

	<%@ page import="fr.irit.wanda.entities.*"%>
	<%@ page import="fr.irit.wanda.servlet.ClientConfiguration"%>
	<%@ page import="fr.irit.wanda.dao.*"%>
	<%@ page import="java.util.Collection"%>
	<%@ page import="java.io.*"%>
	<%@ page import="fr.irit.wanda.ui.*"%>

	<p id="top"></p>
	<div id="main_container">
		<div class="dotted_line"></div>
		<div id="header_container">
			<img src="img/logo_app.png" alt="Wanda" title="Wanda" width="350"
				height="100" style="margin-left: 29px; float: left;">
			<div id="search_tool">
				<form method="get" action="/search" id="search">
					<input id="search_barre" name="search_barre" type="text" size="40"
						placeholder="Search..." />
				</form>
			</div>
		</div>
		<div class="dotted_line"></div>
		<div class="clear"></div>
		<div id="container">
			<ul class="dark_menu">
				<li><a href="Home.jsp" class="selected">Home</a></li>
				<li><a id="create_Site" href="#" onclick="change_div(this.id)">Site</a>
					<ul>
						<li><a id="create_Site" href="#"
							onclick="change_div(this.id)">Create</a></li>
						<li><a id="create_Meta" href="#"
							onclick="change_div(this.id)">Add Metadata</a></li>
					</ul></li>
				<li><a id="create_Corpus" href="#"
					onclick="change_div(this.id)">Corpus</a>
					<ul>
						<li><a id="create_Corpus" href="#"
							onclick="change_div(this.id)">Create</a></li>
						<li><a id="create_Meta" href="#"
							onclick="change_div(this.id)">Add Metadata</a></li>
					</ul></li>
				<li><a id="create_Video" href="#" onclick="change_div(this.id)">Video</a>
					<ul>
						<li><a id="create_Video" href="#"
							onclick="change_div(this.id)">Create</a></li>
						<li><a id="create_Meta" href="#"
							onclick="change_div(this.id)">Add Metadata</a></li>
					</ul></li>
				<li><a id="create_Session" href="#"
					onclick="change_div(this.id)">Session</a>
					<ul>
						<li><a id="create_Session" href="#"
							onclick="change_div(this.id)">Create</a></li>
						<li><a id="create_Meta" href="#"
							onclick="change_div(this.id)">Add Metadata</a></li>
					</ul></li>
				<li><a href="#">Admin</a>
					<ul>
						<li><a id="create_User" href="#"
							onclick="change_div(this.id)">Create User</a></li>
						<li><a id="create_Role" href="#"
							onclick="change_div(this.id)">Create Role</a></li>
						<li><a id="create_Rule" href="#"
							onclick="change_div(this.id)">Create Rule</a></li>
						<li><a id="create_Type" href="#"
							onclick="change_div(this.id)">Create Type</a></li>
						<li><a id="create_Workflow" href="#"
							onclick="change_div(this.id)">Create Workflow</a></li>
						<li><a id="create_Meta" href="#"
							onclick="change_div(this.id)">Add Metadata</a></li>
					</ul></li>
			</ul>
		</div>
		<div class="dotted_line"></div>
		<div id="content">
			<div id="contenu">
				<div id="home_page" class="current">Ex his quidam aeternitati
					se commendari posse per statuas aestimantes eas ardenter adfectant
					quasi plus praemii de figmentis aereis sensu carentibus adepturi,
					quam ex conscientia honeste recteque factorum, easque auro curant
					inbracteari, quod Acilio Glabrioni delatum est primo, cum consiliis
					armisque regem superasset Antiochum. quam autem sit pulchrum exigua
					haec spernentem et minima ad ascensus verae gloriae tendere longos
					et arduos, ut memorat vates Ascraeus, Censorius Cato monstravit.
					qui interrogatus quam ob rem inter multos... statuam non haberet
					malo inquit ambigere bonos quam ob rem id non meruerim, quam quod
					est gravius cur inpetraverim mussitare. Eminuit autem inter humilia
					supergressa iam impotentia fines mediocrium delictorum nefanda
					Clematii cuiusdam Alexandrini nobilis mors repentina; cuius socrus
					cum misceri sibi generum, flagrans eius amore, non impetraret, ut
					ferebatur, per palatii pseudothyrum introducta, oblato pretioso
					reginae monili id adsecuta est, ut ad Honoratum tum comitem
					orientis formula missa letali omnino scelere nullo contactus idem
					Clematius nec hiscere nec loqui permissus occideretur. Mox dicta
					finierat, multitudo omnis ad, quae imperator voluit, promptior
					laudato consilio consensit in pacem ea ratione maxime percita, quod
					norat expeditionibus crebris fortunam eius in malis tantum
					civilibus vigilasse, cum autem bella moverentur externa, accidisse
					plerumque luctuosa, icto post haec foedere gentium ritu perfectaque
					sollemnitate imperator Mediolanum ad hiberna discessit.</div>
				<div id="create_SiteForm" class="hidden">
					<h2>Administration site section</h2>
					<div style="margin-bottom: 5px" class="description">
						<p>
							This section allows you to admin site. <br> You can create a
							new site thanks to this following from. Else, if you have the
							rights on a site, you can admin (cf bottom section).<br>
							It's reserved for administration and and should not be misused.
						</p>
					</div>
					<div class="space"></div>
					<form class="form" method="post" action="New_entities"
						id="add_site" name="site">
						<input type="hidden" name="hidden_field" value="site"> <label
							for="name_site"><span>Name</span></label> <input type="text"
							name="name_site" placeholder="Name" autofocus="" required="" />
						<label for="rule_site"><span>Rule</span></label> <select
							name="rule_site" size="1" required="">
							<%
								for (Rule r:new ClientConfiguration().remoteRequest.getRules()){
							%>
							<option>
								<%
									out.print(r.getName());
								%>
							</option>
							<%
								}
							%>
						</select>
						<p class="validate">
							<input class="validate_button" name="validate" type="submit"
								size="40" value="Validate" />
						</p>
					</form>
				</div>
				<div id="create_MetaForm" class="hidden">
					<h2>Add metadate</h2>
					<div style="margin-bottom: 5px" class="description">
						<p>Add metadata. Décrire la page d'ajout de metadonnée. Il
							sera judicieux de préselectionner le type d'entity concernée</p>
					</div>
					<div class="space"></div>
					<form class="form" method="post" action="New_entities"
						id="add_metadata" name="metadata">
						<input type="hidden" name="hidden_field" value="metadata">
						<label for="name_meta"><span>Name</span></label> <input
							type="text" name="name_meta" placeholder="Name" autofocus=""
							required="" /> <label for="description_meta"><span>Description</span></label>
						<input type="text" name="description_meta"
							placeholder="Description" required="" /> <label
							for="hoover_meta"><span>Hoover</span></label> <input type="text"
							name="hoover_meta" placeholder="Hoover" required=""
							style="margin-bottom: 7px;" /> <label for="obligation_meta"
							style="display: inline; padding-top: 5px;"><span>Obligation
								:</span></label> <span style="font-size: 12px;">True</span><input type="radio"
							name="obligation_meta" checked value="true" /> <span
							style="font-size: 12px;">False</span><input type="radio"
							name="obligation_meta" value="false" />
						<div>
							<%
								String ts[] = {"Video","Annotation","Vue","Session","Corpus","Site"};
																	for (String s : ts){
							%>
							<input type="checkbox" name="<%out.print(s+"_meta");%>"
								value="<%out.print(s);%>" /> <span style="font-size: 12px;">
								<%
									out.print(s);
								%>
							</span>
							<%
								}
							%>
						</div>
						<p class="validate">
							<input class="validate_button" name="validate" type="submit"
								size="40" value="Validate" />
						</p>
					</form>
				</div>


				<div id="create_CorpusForm" class="hidden">
					<h2>Add metadate</h2>
					<div style="margin-bottom: 5px" class="description">
						<p>Add Corpus.</p>
					</div>
					<div class="space"></div>
					<form class="form" method="post" action="New_entities"
						id="add_corpus" name="Corpus">
						<br> <input type="hidden" name="hidden_field" value="corpus">
						<label for="name_corpus"><span>Name</span></label> <input
							name="name_corpus" type="text" size="40" /><br> <label
							for="site_corpus"><span>Rule</span></label> <select
							name="site_corpus" size="1" required="">
							<%
								for (Site s:new ClientConfiguration().remoteRequest.getSites()){
							%>
							<option>
								<%
									out.print(s.getName());
								%>
							</option>
							<%
								}
							%>
						</select> <label for="corpus_corpus"><span>Corpus</span></label> <select
							name="corpus_corpus" size="1" required="">
							<%
								//for (Corpus c:new ClientConfiguration().remoteRequest.getCorpus()){
							%>
							<option>
								<%
									//out.print(c.getName());
																					//}
								%>
							
						</select> <label for="rule_corpus"><span>Rule</span></label> <select
							name="rule_corpus" size="1" required="">
							<%
								for (Rule r:new ClientConfiguration().remoteRequest.getRules()){
							%>
							<option>
								<%
									out.print(r.getName());
								%>
							</option>
							<%
								}
							%>
						</select>
						<p class="validate">
							<input class="validate_button" name="validate" type="submit"
								size="40" value="Validate" />
						</p>
					</form>
				</div>

				<div id="create_VideoForm" class="hidden">
					<h2>Add Video</h2>
					<div style="margin-bottom: 5px" class="description">
						<p>Add video.</p>
					</div>
					<div class="space"></div>
					<form class="form" method="post" action="New_entities"
						id="add_video" name="Video">
						<br> <input type="hidden" name="hidden_field" value="video">
						<label for="name_video"><span>Name</span></label> <input
							name="name_video" type="text" size="40" /><br> <label
							for="view_video"><span>View</span></label> <select
							name="view_video" size="1" required="">
							<%
								for (View v:new ClientConfiguration().remoteRequest.getViews()){
							%>
							<option>
								<%
									out.print(v.getName());
								%>
							</option>
							<%
								}
							%>
						</select> <label for="workflow_video"><span>Workflow</span></label> <select
							name="workflow_video" size="1" required="">
							<%
								for (Workflow w:new ClientConfiguration().remoteRequest.getWorkflows()){
							%>
							<option>
								<%
									out.print(w.getName());
								%>
							</option>
							<%
								}
							%>
						</select>
						<p class="validate">
							<input class="validate_button" name="validate" type="submit"
								size="40" value="Validate" />
						</p>
					</form>
				</div>





				<div id="create_RoleForm" class="hidden">
					<h2>Add role</h2>
					<div style="margin-bottom: 5px" class="description">
						<p>Add Role. Réserver à l'admin</p>
					</div>
					<div class="space"></div>
					<form class="form" method="post" action="New_entities"
						id="add_Role" name="Role">
						<input type="hidden" name="hidden_field" value="role"> <label
							for="name_role"><span>Name</span></label> <input type="text"
							name="name_role" placeholder="Name" autofocus="" required="" />
						<label for="desc_role"><span>Description</span></label> <input
							type="text" name="desc_role" placeholder="Description"
							required="" /> <label for=autorisation_role>Autorisation
							:</label> <select name="autorisation_role" size="1" required="">
							<option>0
							<option>1
							<option>2
							<option>3
							<option>4
							<option>5
						</select>
						<p class="validate">
							<input class="validate_button" name="validate" type="submit"
								size="40" value="Validate" />
						</p>
					</form>
				</div>

				<div id="create_RuleForm" class="hidden">
					<h2>Add rule</h2>
					<div style="margin-bottom: 5px" class="description">
						<p>Add Rule. Réserver à l'admin</p>
					</div>
					<div class="space"></div>
					<form class="form" method="post" action="New_entities"
						id="add_rule" name="Rule">
						<input type="hidden" name="hidden_field" value="rule"> <label
							for="name_rule"><span>Name</span></label> <input type="text"
							name="name_rule" placeholder="Name" autofocus="" required="" />
						<label for="desc_rule"><span>Description</span></label> <input
							type="text" name="desc_rule" placeholder="Description"
							required="" />
						<p class="validate">
							<input class="validate_button" name="validate" type="submit"
								size="40" value="Validate" />
						</p>
					</form>
				</div>

				<div id="create_WorkflowForm" class="hidden">
					<h2>Add workflow</h2>
					<div style="margin-bottom: 5px" class="description">
						<p>Add Workflow. Réserver à l'admin</p>
					</div>
					<div class="space"></div>
					<form class="form" method="post" action="New_entities"
						id="add_worflow" name="Workflow">
						<input type="hidden" name="hidden_field" value="workflow">
						<label for="name_workflow"><span>Name</span></label> <input
							type="text" name="name_workflow" placeholder="Name" autofocus=""
							required="" /> <label for="desc_worflow"><span>Description</span></label>
						<input type="text" name="desc_worflow" placeholder="Description"
							required="" />
						<p class="validate">
							<input class="validate_button" name="validate" type="submit"
								size="40" value="Validate" />
						</p>
					</form>
				</div>

				<div id="create_TypeForm" class="hidden">
					<h2>Add type</h2>
					<div style="margin-bottom: 5px" class="description">
						<p>Add Type. Réserver à l'admin</p>
					</div>
					<div class="space"></div>
					<form class="form" method="post" action="New_entities"
						id="add_type" name="Type">
						<input type="hidden" name="hidden_field" value="workflow">
						<label for="name_type"><span>Name</span></label> <input
							type="text" name="name_type" placeholder="Name" autofocus=""
							required="" /> <label for="desc_type"><span>Description</span></label>
						<input type="text" name="desc_type" placeholder="Description"
							required="" />
						<p class="validate">
							<input class="validate_button" name="validate" type="submit"
								size="40" value="Validate" />
						</p>
					</form>
				</div>

				<div id="create_UserForm" class="hidden">
					<h2>Add User</h2>
					<div style="margin-bottom: 5px" class="description">
						<p>Add user. Réserver à l'admin</p>
					</div>
					<form class="form" method="post" action="New_entities"
						id="add_user" name="User">
						<br> <input type="hidden" name="hidden_field" value="user">
						<label for="name_user"><span>Name</span></label> <input
							type="text" name="name_user" placeholder="Name" autofocus=""
							required="" /> <label for="prenom_user"><span>Prenom</span></label>
						<input type="text" name="prenom_user" placeholder="Prenom"
							required="" /> <label for="role_user"><span>Role</span></label>
						<select name="role_user" size="1" required="">
							<%
								for (Role r:new ClientConfiguration().remoteRequest.getRoles()){
							%>
							<option>
								<%
									out.print(r.getName());
								%>
							</option>
							<%
								}
							%>
						</select>
						<p class="validate">
							<input class="validate_button" name="validate" type="submit"
								size="40" value="Validate" />
						</p>
					</form>
				</div>

			</div>
		</div>
		<div id="sidebar">
			<h3>Arborescence</h3>
			<ol class="tree">
				<%
					SiteAO sao = new SiteAO();
						Collection<Site> sites = sao.getAll();
						for (Site s : sites) {
							String chaine = General.printHierarchy(sao.get(s.getName()), "");
							out.print(chaine);
						}
				%>
			</ol>
		</div>
		<div class="clear"></div>
		<div class="dotted_line"></div>
		<div id="copyright">Copyright Wanda 2013.</div>
	</div>
	<div id="footer">
		<div id="footer-menu">
			<ul>
				<li><a title="Home" href="Home.jsp" class="color1">Home</a></li>
				<li><a title="Admin" href="" class="color1">Contact</a></li>
				<li><a title="About us" href="" class="color1">About us</a></li>
				<li><a title="Top page" href="#top" class="color1">Top page</a></li>
			</ul>
		</div>
		<div id="time">
			<script>
				dT();
			</script>
		</div>
	</div>
</body>
</html>