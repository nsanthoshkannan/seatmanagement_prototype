package com.nokia.seatmanagement.core.notification;

import com.nokia.seatmanagement.common.models.NotifierType;
import com.nokia.seatmanagement.core.exceptions.InvalidNotifierTypeException;

/**
 * 
 * @author santhosh
 *
 */
public class NotifierFactory {
	
	/**
	 * 
	 * @param type
	 * @return
	 * @throws InvalidNotifierTypeException
	 */
	public Notifier createNotifier(NotifierType type) throws InvalidNotifierTypeException {
		
		if (type == null) {
			throw new InvalidNotifierTypeException();
		}
		Notifier notifier = null;
		switch (type) {
		
		case EMAIL:
			notifier = new EmailNotifier();
			break;
		case SMS:
			notifier = new SMSNotifier();
			break;
			default:
				throw new InvalidNotifierTypeException();
		}
		return notifier;

	}

}
