<%@page import="fr.irit.wanda.dao.MetadataConcernsAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Display Metadata</title>
<link rel="stylesheet" media="screen" type="text/css" title="Design" href="css/display_meta.css" />
<!-- IMPORTS -->
	<%@page import="fr.irit.wanda.entities.*" %>
	<%@page import="fr.irit.wanda.dao.*" %>
	<%@page import="java.util.*" %>
</head>
<body>
	<h1 align=center>METADATA LIST</h1>
	<ul>
		<li>Video : </li><br>
		<% 
		ArrayList<Metadata> mal = null;
		try{
				mal = (ArrayList<Metadata>)new MetadataConcernsAO().get(new Entity("video"));
				if (mal.size() !=0){
					%>
					<table>
					<tr>
						<th>Nom</th>
						<th>Description</th>
						<th>Hoover</th>
						<th>Obligation</th>  
					</tr><%
					for (Metadata m : mal){
						%>
						<tr>
							<td><%out.print(m.getName());%></td>
							<td><%out.print(m.getDescription());%></td>
							<td><%out.print(m.getHoover());%></td>
							<td><%out.print(m.isObligation());%></td>  
						</tr><%}	
				%></table>
				<span><%out.println("Metadata number : "+mal.size());%></span><%
			}
		}catch(Exception e){}%>
		
		
		<div class="clear3"></div>	
		<li>Annotation : </li><br>
		<% 
		try{
			mal = (ArrayList<Metadata>)new MetadataConcernsAO().get(new Entity("annotation"));
			if (mal.size() !=0){%>
					<table>
					<tr>
					<th>Nom</th>
					<th>Description</th>
					<th>Hoover</th>
					<th>Obligation</th>  
					</tr><%
					
					for (Metadata m : mal){
						%><tr>
							<td><%out.print(m.getName());%></td>
							<td><%out.print(m.getDescription());%></td>
							<td><%out.print(m.getHoover());%></td>
							<td><%out.print(m.isObligation());%></td>  
						</tr><%}	
				%></table>
				<span><%out.println("Metadata number : "+mal.size());%></span><%
			}
		}catch(Exception e){}%>
		
		
		<div class="clear3"></div>
		<li>Session : </li><br>
		<% 
		try{
			
			mal = (ArrayList<Metadata>)new MetadataConcernsAO().get(new Entity("session"));
			if (mal.size() !=0){
				%><table>
				<tr>
					<th>Nom</th>
					<th>Description</th>
					<th>Hoover</th>
					<th>Obligation</th>  
				</tr><%
				for (Metadata m : mal){
						%><tr>
							<td><%out.print(m.getName());%></td>
							<td><%out.print(m.getDescription());%></td>
							<td><%out.print(m.getHoover());%></td>
							<td><%out.print(m.isObligation());%></td>  
						</tr><%}	
				%></table>
				<span><%out.println("Metadata number : "+mal.size());%></span><%
			}
		}catch(Exception e){}%>
		
		<div class="clear3"></div>
		<li>View : </li><br>
		<% 
		try{
			
				mal = (ArrayList<Metadata>)new MetadataConcernsAO().get(new Entity("view"));
				if (mal.size() !=0){
					%><table>
					<tr>
						<th>Nom</th>
						<th>Description</th>
						<th>Hoover</th>
						<th>Obligation</th>  
					</tr><%
					for (Metadata m : mal){
						%><tr>
							<td><%out.print(m.getName());%></td>
							<td><%out.print(m.getDescription());%></td>
							<td><%out.print(m.getHoover());%></td>
							<td><%out.print(m.isObligation());%></td>  
						</tr><%}	
				%></table>
				<span><%out.println("Metadata number : "+mal.size());%></span><%
			}
		}catch(Exception e){}%>
		
		<div class="clear3"></div>
		<li>Corpus : </li><br>
		<% 
		try{
				mal = (ArrayList<Metadata>)new MetadataConcernsAO().get(new Entity("corpus"));
				if (mal.size() !=0){	
					%><table>
					<tr>
						<th>Nom</th>
						<th>Description</th>
						<th>Hoover</th>
						<th>Obligation</th>  
					</tr><%
					for (Metadata m : mal){
						%><tr>
							<td><%out.print(m.getName());%></td>
							<td><%out.print(m.getDescription());%></td>
							<td><%out.print(m.getHoover());%></td>
							<td><%out.print(m.isObligation());%></td>  
						</tr><%}	
				%></table>
				<span><%out.println("Metadata number : "+mal.size());%></span><%
			}
		}catch(Exception e){}%>
		<div class="clear3"></div>
		
		<li>Site : </li><br>
		<% 
		try{
			
			mal = (ArrayList<Metadata>)new MetadataConcernsAO().get(new Entity("site"));
			if (mal.size() !=0){
				%><table>
					<tr>
						<th>Nom</th>
						<th>Description</th>
						<th>Hoover</th>
						<th>Obligation</th>  
					</tr><%
					for (Metadata m : mal){
						%><tr>
							<td><%out.print(m.getName());%></td>
							<td><%out.print(m.getDescription());%></td>
							<td><%out.print(m.getHoover());%></td>
							<td><%out.print(m.isObligation());%></td>  
						</tr><%}	
				%></table>
				<span><%out.println("Metadata number : "+mal.size());%></span><%
			}
		}catch(Exception e){}%>
	</ul>
	<br>
	<a href="new_entities.jsp">New Metadata</a>
</body>
</html>