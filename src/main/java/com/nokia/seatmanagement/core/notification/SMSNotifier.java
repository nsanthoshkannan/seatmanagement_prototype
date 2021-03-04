package com.nokia.seatmanagement.core.notification;

import com.nokia.seatmanagement.common.models.Users;
import com.nokia.seatmanagement.core.db.SeatManagementDbHelper;
import com.nokia.seatmanagement.core.exceptions.InvalidUserException;

/**
 * 
 * @author santhosh
 *
 */
public class SMSNotifier implements Notifier {

	@Override
	public void notify(Integer userId, String message) throws InvalidUserException {
		
		Users user = SeatManagementDbHelper.getUserDetails(userId);
		Long mobileNumber = user.getMobile();
		System.out.println("Mock SMS Notification sent to:" + mobileNumber + " message:"  + message);
		
		/*
		 * Code needed to raise message as SMS notification to mobileNumber
		 */

	}
	
	
	
	
	
	

}
