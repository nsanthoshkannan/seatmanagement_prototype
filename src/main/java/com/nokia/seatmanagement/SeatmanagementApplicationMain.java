package com.nokia.seatmanagement;

import com.nokia.seatmanagement.core.exceptions.DuplicateClaimRequestException;
import com.nokia.seatmanagement.core.exceptions.InvalidSeatException;
import com.nokia.seatmanagement.core.exceptions.InvalidUserException;
import com.nokia.seatmanagement.core.exceptions.SeatUnavailableException;
import com.nokia.seatmanagement.facade.SeatManagerFacade;

public class SeatmanagementApplicationMain {

	public static void main(String[] args) throws InvalidUserException, InvalidSeatException, SeatUnavailableException, DuplicateClaimRequestException {

		SeatManagerFacade facade = new SeatManagerFacade();
		System.out.println(facade.getAllAvailableSeat());
		facade.raiseClaimRequest(1, 4);
		facade.raiseClaimRequest(2, 3);
		facade.raiseClaimRequest(3, 5);
	}

}
