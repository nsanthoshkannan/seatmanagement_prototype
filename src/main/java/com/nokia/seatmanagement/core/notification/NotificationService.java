package com.nokia.seatmanagement.core.notification;

import com.nokia.seatmanagement.common.models.NotificationType;
import com.nokia.seatmanagement.core.exceptions.InvalidNotificationTypeException;
import com.nokia.seatmanagement.core.exceptions.InvalidSeatException;
import com.nokia.seatmanagement.core.exceptions.InvalidUserException;

/**
 * 
 * @author santhosh 
 * This shall be run as separate service in a thread and as per
 *         proposed design, we can use ArrayBlocking queue to produce and
 *         consume the notification messages
 *
 */
public class NotificationService {

	private static final NotificationService INSTANCE = new NotificationService();

	private NotificationHandler handler;

	private NotificationService() {
		handler = new NotificationHandler();
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	public NotificationHandler getHandler() {
		return handler;
	}

	public static synchronized NotificationService getInstance() {

		return INSTANCE;

	}

	/**
	 * 
	 * @param actionperformedUserId
	 * @param seatId
	 * @param type
	 * @throws InvalidNotificationTypeException
	 * @throws InvalidUserException
	 * @throws InvalidSeatException
	 */
	public void raiseNotification(Integer actionperformedUserId, Integer seatId, NotificationType type)
			throws InvalidNotificationTypeException, InvalidUserException, InvalidSeatException {
		this.getHandler().triggerNotifications(actionperformedUserId, seatId, type);

	}

}
