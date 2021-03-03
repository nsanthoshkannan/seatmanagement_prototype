package com.nokia.seatmanagement.core.notification;

import java.util.ArrayList;
import java.util.List;

import com.nokia.seatmanagement.common.models.NotificationType;
import com.nokia.seatmanagement.common.models.NotifierType;
import com.nokia.seatmanagement.core.exceptions.InvalidNotificationTypeException;
import com.nokia.seatmanagement.core.exceptions.InvalidNotifierTypeException;
import com.nokia.seatmanagement.core.exceptions.InvalidUserException;

/**
 * 
 * @author santhosh
 *
 */
public class NotificationHandler {

	/**
	 * get User Associated Notifier Types
	 * 
	 * @param userId
	 * @return
	 * @throws InvalidUserException
	 */
	private List<NotifierType> getUserAssociatedNotifierTypes(Integer userId) throws InvalidUserException {

		// Insert Code to fetch the user preferences list from database. Adding SMS,
		// EMAIL as both notifiers by default
		ArrayList<NotifierType> notifierTypeList = new ArrayList<NotifierType>();
		notifierTypeList.add(NotifierType.EMAIL);
		notifierTypeList.add(NotifierType.SMS);
		return notifierTypeList;
	}

	/**
	 * 
	 * @param userId
	 * @param seatId
	 * @param type
	 * @return
	 * @throws InvalidNotificationTypeException
	 */
	private String getNotificationMessage(Integer userId, Integer seatId, NotificationType type)
			throws InvalidNotificationTypeException {
		// Logic to fetch the Notification message format from Database. Hard coding
		// this for prototype
		if (type == null) {
			throw new InvalidNotificationTypeException();
		}
		String message = "";
		switch (type) {
		case CLAIM_RAISED:
			message = "User:" + userId + " has raised a claim for Seat:" + seatId;
			break;

		case CLAIM_APPROVED:
			message = "Claim for SeatId" + seatId + "is approved by " + userId;
			break;

		case CLAIM_REJECTED:
			message = "Claim for SeatId" + seatId + "is rejected by " + userId;
			break;
		}
		return message;
	}

	/**
	 * 
	 * @param userId
	 * @param seatId
	 * @param type
	 * @throws InvalidNotificationTypeException
	 * @throws InvalidUserException
	 * @throws InvalidNotifierTypeException
	 */
	public void triggerNotifications(Integer userId, Integer seatId, NotificationType type)
			throws InvalidNotificationTypeException, InvalidUserException {
		Integer notifierUserId = this.getIdOfUserToBeNotified(userId, type);
		List<NotifierType> notifierTypeList = this.getUserAssociatedNotifierTypes(notifierUserId);
		String notificationMessage = this.getNotificationMessage(userId, seatId, type);

		NotifierFactory factory = new NotifierFactory();
		for (NotifierType notifierType : notifierTypeList) {
			try {
				Notifier notifier = factory.createNotifier(notifierType);
				notifier.notify(notifierUserId, notificationMessage);
			} catch (InvalidNotifierTypeException e) {
				// TODO: handle exception
				e.printStackTrace();
			}

		}
	}

	/**
	 * This method will have the logic to find the actual user to be Notified. Eg.
	 * If user raised request, it has to notified to his manager If Manager approved
	 * request, it has to notified to his reportee(user)
	 * 
	 * @param userId
	 * @param type
	 * @return
	 * @throws InvalidNotificationTypeException
	 */
	private Integer getIdOfUserToBeNotified(Integer userId, NotificationType type)
			throws InvalidNotificationTypeException, InvalidUserException {

		Integer notifierUserId = userId;
		if (type == null) {
			throw new InvalidNotificationTypeException();
		} else if (type == NotificationType.CLAIM_APPROVED || type == NotificationType.CLAIM_REJECTED) {
			notifierUserId = 1;// code to get the user id who raised the request. manager's user Id will be
								// userId. hard coded for prototype
		} else if (type == NotificationType.CLAIM_RAISED) {
			notifierUserId = 4; // Code to get the manager's userId for the given userId hard coded for
								// proptotype

		} else {
			throw new InvalidNotificationTypeException();
		}
		return notifierUserId;

	}

}
