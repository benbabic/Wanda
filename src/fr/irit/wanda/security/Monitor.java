package fr.irit.wanda.security;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

public class Monitor {

	protected Logger logger;

	public Monitor() {
		try {
			String loggerName = generateFileName();
			createLog(loggerName);
			Handler handler = new FileHandler(loggerName);
			logger = Logger.getLogger("SecurityMonitor");
			logger.addHandler(handler);
		} catch (SecurityException e) {
			System.err.println("SE");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("IOE");
			e.printStackTrace();
		}
	}

	private void createLog(String name) {
		try {
			File log = new File(name);
			log.createNewFile();
		} catch (IOException e) {

		}
	}

	private String generateFileName() {
		Date d = new Date();
		return (d.getTime() + "-Security.log");
	}

	public void logConnectionAttempt(HttpServletRequest request) {
		String ip = request.getRemoteAddr();
		String ressource = request.getRequestURI();
		String paramAsString = new String();
		Map<String, String[]> params = request.getParameterMap();

		Iterator i = params.keySet().iterator();
		while (i.hasNext()) {
			String key = (String) i.next();
			paramAsString += " " + key + "=";
			paramAsString += ((String[]) params.get(key))[0] + " ";
		}
		String info = "Connection attempt from [" + ip
				+ "]: requested ressource " + ressource + " with parameters"
				+ paramAsString;
		logger.info(info);
	}
}
