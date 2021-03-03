package com.nokia.seatmanagement;

import org.junit.Test;

import com.nokia.seatmanagement.core.exceptions.DuplicateClaimRequestException;
import com.nokia.seatmanagement.core.exceptions.InvalidSeatException;
import com.nokia.seatmanagement.core.exceptions.InvalidUserException;
import com.nokia.seatmanagement.core.exceptions.SeatUnavailableException;
import com.nokia.seatmanagement.facade.SeatManagerFacade;

public class SeatmanagementTests {

	SeatManagerFacade facade = new SeatManagerFacade();

	@Test(expected = InvalidUserException.class)
	public void assertInvalidUserException() throws InvalidUserException, InvalidSeatException,
			SeatUnavailableException, DuplicateClaimRequestException {
		facade.raiseClaimRequest(6, 4);

	}

	@Test(expected = InvalidSeatException.class)
	public void assertInvalidSeatException() throws InvalidUserException, InvalidSeatException,
			SeatUnavailableException, DuplicateClaimRequestException {
		facade.raiseClaimRequest(4, 7);

	}

	@Test
	public void assertSuccessfulClaim() throws InvalidUserException, InvalidSeatException, SeatUnavailableException,
			DuplicateClaimRequestException {
		facade.raiseClaimRequest(1, 4);
	}

	@Test(expected = SeatUnavailableException.class)
	public void assertSeatUnavailableException() throws InvalidUserException, InvalidSeatException,
			SeatUnavailableException, DuplicateClaimRequestException {
		facade.raiseClaimRequest(4, 4);

	}

	@Test(expected = DuplicateClaimRequestException.class)
	public void assertDuplicateClaimRequestException() throws InvalidUserException, InvalidSeatException,
			SeatUnavailableException, DuplicateClaimRequestException {
		facade.raiseClaimRequest(1, 3);

	}

}
