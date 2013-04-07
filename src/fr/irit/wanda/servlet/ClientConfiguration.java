package fr.irit.wanda.servlet;

import fr.irit.wanda.common.IRequest;
import fr.irit.wanda.serveur.ClientRequest;

/**
 * Si le client se complexifie ou se délocalise (e.g application client chargée par le navigateur en JAVA)
 * cette classe de configuration servira à récupérer l'interface de requête et sa configuration général.
 * 
 * @author Wanda Team
 *
 */

public class ClientConfiguration {
	public IRequest remoteRequest;
	
	public ClientConfiguration(){
		remoteRequest = new ClientRequest();
	}
}
