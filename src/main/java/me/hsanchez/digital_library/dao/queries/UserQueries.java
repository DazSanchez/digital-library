package me.hsanchez.digital_library.dao.queries;

public class UserQueries {
	public static final String GET_USER_BY_USERNAME = "SELECT USERNAME as username, PASSWORD as password FROM USERS WHERE USERNAME = ?"; 
}
