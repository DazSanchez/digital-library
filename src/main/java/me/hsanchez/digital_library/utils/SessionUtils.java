package me.hsanchez.digital_library.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import me.hsanchez.digital_library.dto.DocumentDTO;
import me.hsanchez.digital_library.exceptions.PreRequirementException;

public class SessionUtils {
	public static boolean isLoggedIn(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		
		return session != null && session.getAttribute("user") != null;
	}
	
	public static DocumentDTO getDocument(HttpServletRequest request) throws PreRequirementException {
		if(!isLoggedIn(request)) {
			throw new PreRequirementException("No session found", "/login");
		}
		
		DocumentDTO document = (DocumentDTO) request.getSession(false).getAttribute("document");
		
		if(document == null) {
			throw new PreRequirementException("No previous document found", "/dashboard");
		}
		
		return document;
	}
	
	public static void removeDocument(HttpServletRequest request) {
		request.getSession(false).removeAttribute("document");
	}
	
	public static void set(HttpServletRequest request, String name, Object value) {
		HttpSession session = request.getSession(false);
		
		if(session != null) {
			session.setAttribute(name, value);
		}
	}
	
	public static Object getAndFlush(HttpServletRequest request, String name) {
		HttpSession session = request.getSession(false);
		
		if(session == null) return null;
		
		Object value = session.getAttribute(name); 
		
		session.removeAttribute(name);
		
		return value;
	}
}
