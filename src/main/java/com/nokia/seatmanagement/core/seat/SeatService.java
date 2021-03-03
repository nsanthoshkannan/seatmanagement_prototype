package com.nokia.seatmanagement.core.seat;

import java.util.Collection;

import com.nokia.seatmanagement.common.models.ClaimRequest;
import com.nokia.seatmanagement.common.models.NotificationType;
import com.nokia.seatmanagement.common.models.SeatStatus;
import com.nokia.seatmanagement.common.models.Seats;
import com.nokia.seatmanagement.common.models.Users;
import com.nokia.seatmanagement.core.db.SeatManagementDbHelper;
import com.nokia.seatmanagement.core.exceptions.DuplicateClaimRequestException;
import com.nokia.seatmanagement.core.exceptions.InvalidNotificationTypeException;
import com.nokia.seatmanagement.core.exceptions.InvalidSeatException;
import com.nokia.seatmanagement.core.exceptions.InvalidUserException;
import com.nokia.seatmanagement.core.exceptions.SeatUnavailableException;
import com.nokia.seatmanagement.core.notification.NotificationService;

/**
 * 
 * @author santhosh
 *
 */
public class SeatService {

	private static final SeatService INSTANCE = new SeatService();
	private static final Object claimRequestLock = new Object();
	private SeatService() {
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	public static synchronized SeatService getInstance() {

		return INSTANCE;

	}

	/**
	 * 
	 * @return
	 */
	public Collection<Seats> getAvailableSeats() {
		Collection<Seats> availableSeats = SeatManagementDbHelper.getAllSeatsDetailsByStatus(SeatStatus.AVAILABLE);
		return availableSeats;
	}

	/**
	 * 
	 * @param userId
	 * @param seatId
	 * @throws InvalidUserException
	 * @throws InvalidSeatException
	 * @throws SeatUnavailableException
	 * @throws DuplicateClaimRequestException
	 */
	public void raiseClaimRequest(final Integer userId, final Integer seatId) throws InvalidUserException,
			InvalidSeatException, SeatUnavailableException, DuplicateClaimRequestException {
		Users user = SeatManagementDbHelper.getUserDetails(userId);
		synchronized (SeatService.claimRequestLock) {

			ClaimRequest existingClaimRequest = SeatManagementDbHelper.getClaimRequestByUserId(userId);
			if (existingClaimRequest != null) {
				throw new DuplicateClaimRequestException("Existing claim request:" + existingClaimRequest);
			}

			Seats claimedSeat = SeatManagementDbHelper.getSeatDetails(seatId);
			if (!claimedSeat.getStatus().equals(SeatStatus.AVAILABLE)) {
				throw new SeatUnavailableException(seatId + " is currently unavailable");
			}
			SeatManagementDbHelper.createClaimRequest(userId, seatId);
			SeatManagementDbHelper.updateSeatStatus(seatId, SeatStatus.CLAIM_RAISED);
		}
		try {
			NotificationService.getInstance().raiseNotification(userId, seatId, NotificationType.CLAIM_RAISED);
		} catch (InvalidNotificationTypeException e) {
			// TODO: handle exception suppressing here as we give constant NotificationType
			e.printStackTrace();
		}
	}

}
