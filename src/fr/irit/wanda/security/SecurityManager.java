package fr.irit.wanda.security;


public final class SecurityManager {

	private static volatile SecurityManager instance = null;
	private static Monitor monitor = new Monitor();
	
	private SecurityManager(){
		
	}
	
	public final static SecurityManager getInstance(){
		if (instance == null) // we try to avoid using synchronized
		{
			synchronized (SecurityManager.class) {
				if (instance == null)
					instance = new SecurityManager();
			}
		}
		return instance;
	}

	/**
	 * @return the monitor
	 */
	public Monitor getMonitor() {
		return monitor;
	}	
}
