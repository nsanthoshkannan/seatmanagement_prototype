package com.nokia.seatmanagement.core.notification;

import com.nokia.seatmanagement.core.exceptions.InvalidUserException;

/**
 * 
 * @author santhosh
 *
 */
public interface Notifier {

	public void notify(Integer userId, String message)throws InvalidUserException;

}
