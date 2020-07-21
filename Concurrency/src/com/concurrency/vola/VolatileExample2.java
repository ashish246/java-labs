package com.concurrency.vola;

/**
 * Pattern #3: independent observations
 * 
 * Another application for this pattern is gathering statistics about the
 * program. This class shows how an authentication mechanism might remember the
 * name of the last user to have logged on. The lastUser reference will be
 * repeatedly used to publish a value for consumption by the rest of the
 * program.
 * 
 * @author Administrator
 * 
 */
public class VolatileExample2 {

	public volatile String lastUser;

	public boolean authenticate(String user, String password) {
		/*boolean valid = passwordIsValid(user, password);
		if (valid) {
			User u = new User();
			activeUsers.add(u);
			lastUser = user;
		}
		return valid;*/
		
		return true;
	}
}
