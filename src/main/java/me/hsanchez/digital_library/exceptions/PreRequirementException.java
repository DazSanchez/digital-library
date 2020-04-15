package me.hsanchez.digital_library.exceptions;

public class PreRequirementException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5724386884682018675L;

	private String url;
	
	public PreRequirementException(String message, String redirectUrl) {
		super(message);
		this.url = redirectUrl;
	}
	
	public String getUrl() {
		return this.url;
	}
}
