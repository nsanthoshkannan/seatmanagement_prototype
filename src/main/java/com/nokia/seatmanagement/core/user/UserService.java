package com.nokia.seatmanagement.core.user;

/**
 * 
 * @author santhosh
 *
 */
public class UserService {

	private static final UserService INSTANCE = new UserService();
	
	
	

	private UserService() {

	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	public static synchronized UserService getInstance() {

		return INSTANCE;

	}
	

	/**
	 * placeholder for methods needed
	 */

}
