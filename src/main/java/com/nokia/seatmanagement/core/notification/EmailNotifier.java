package com.nokia.seatmanagement.core.notification;

import com.nokia.seatmanagement.common.models.Users;
import com.nokia.seatmanagement.core.db.SeatManagementDbHelper;
import com.nokia.seatmanagement.core.exceptions.InvalidUserException;

/**
 * 
 * @author santhosh
 *
 */
public class EmailNotifier implements Notifier {

	@Override
	public void notify(Integer userId, String message)throws InvalidUserException  {
		
		
		Users user = SeatManagementDbHelper.getUserDetails(userId);
		String emailId = user.getEmail();
		System.out.println("Mock Email Notification sent to:" + emailId + " message:"  + message);
		/*
		 * Code needed to raise message as EMAIL notification to emailId
		 */

	}
	
	
	
	
	
	

}
