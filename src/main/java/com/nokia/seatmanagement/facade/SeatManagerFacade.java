package com.nokia.seatmanagement.facade;

import java.util.Collection;

import com.nokia.seatmanagement.common.models.Seats;
import com.nokia.seatmanagement.core.exceptions.DuplicateClaimRequestException;
import com.nokia.seatmanagement.core.exceptions.InvalidSeatException;
import com.nokia.seatmanagement.core.exceptions.InvalidUserException;
import com.nokia.seatmanagement.core.exceptions.SeatUnavailableException;
import com.nokia.seatmanagement.core.seat.SeatService;

/**
 * This class shall be invoked from REST API Controller
 * @author santhosh
 *
 */
public class SeatManagerFacade {
	

	
	
	/**
	 * Get all seats which is available
	 * @return
	 */
	public Collection<Seats> getAllAvailableSeat() {
		return SeatService.getInstance().getAvailableSeats();
	}

	/**
	 * Raise Claim request for an seat
	 * @param userId
	 * @param seatId
	 * @throws InvalidUserException
	 * @throws InvalidSeatException
	 * @throws SeatUnavailableException
	 * @throws DuplicateClaimRequestException 
	 */
	public void raiseClaimRequest(final Integer userId, final Integer seatId) throws InvalidUserException, InvalidSeatException, SeatUnavailableException, DuplicateClaimRequestException {
		
			SeatService.getInstance().raiseClaimRequest(userId, seatId);
		
	}	
	

}
