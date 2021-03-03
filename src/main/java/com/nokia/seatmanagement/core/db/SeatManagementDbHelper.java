package com.nokia.seatmanagement.core.db;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import com.nokia.seatmanagement.common.models.ClaimRequest;
import com.nokia.seatmanagement.common.models.ClaimRequestStatus;
import com.nokia.seatmanagement.common.models.SeatStatus;
import com.nokia.seatmanagement.common.models.Seats;
import com.nokia.seatmanagement.common.models.Users;
import com.nokia.seatmanagement.core.exceptions.InvalidSeatException;
import com.nokia.seatmanagement.core.exceptions.InvalidUserException;

/**
 * 
 * @author santhosh. 
 * This class shall is used instead of actual Database
 *         Communication classes. Used Hashmap with default set of Users, Seats
 *         on program load. This data shall not be persisted
 *
 */
public class SeatManagementDbHelper {

	static private HashMap<Integer, Seats> seatsMap;
	static private HashMap<Integer, Users> usersMap;

	// Map of User Id and their request
	static private HashMap<Integer, ClaimRequest> claimRequestMap;

	static {

		seatsMap = new HashMap<Integer, Seats>();
		seatsMap.put(1, new Seats(1, SeatStatus.AVAILABLE));
		seatsMap.put(2, new Seats(2, SeatStatus.AVAILABLE));
		seatsMap.put(3, new Seats(3, SeatStatus.AVAILABLE));
		seatsMap.put(4, new Seats(4, SeatStatus.AVAILABLE));

		usersMap = new HashMap<Integer, Users>();
		usersMap.put(1, new Users(1, "santhosh", "nsanthoshkannan@gmail.com"));
		usersMap.put(2, new Users(2, "kannan", "kannan@gmail.com"));
		usersMap.put(3, new Users(3, "bhoopal", "bhoopal@gmail.com"));
		usersMap.put(4, new Users(4, "mahendran", "mahendran@gmail.com"));

		claimRequestMap = new HashMap<Integer, ClaimRequest>();

	}

	public static synchronized void createClaimRequest(Integer userId, Integer seatId) {
		claimRequestMap.put(userId, new ClaimRequest(userId, seatId, ClaimRequestStatus.NEW));
		//we can return auto generated claimId once we use database
	}

	public static synchronized void updateClaimRequest(Integer userId, Integer SeatId, ClaimRequestStatus status) {
		ClaimRequest request = claimRequestMap.get(userId);
		request.setStatus(status);

	}

	
	public static ClaimRequest getClaimRequestByUserId(Integer userId) {
		//If it returns null it means no claim request found for userid
		return claimRequestMap.get(userId);
	}

	public static Users getUserDetails(Integer userId) throws InvalidUserException {
		Users user = usersMap.get(userId);
		if (user == null) {
			throw new InvalidUserException("No such user found for userid:" + userId);
		}
		return user;
	}

	public static Collection<Seats> getAllSeatsDetailsByStatus(SeatStatus status) {

		ArrayList<Seats> list = new ArrayList<Seats>();

		for (Seats seat : seatsMap.values()) {
			if (seat.getStatus().equals(status)) {
				list.add(seat);
			}
		}

		return seatsMap.values();
	}

	public static Seats getSeatDetails(Integer seatId) throws InvalidSeatException {
		Seats seat = seatsMap.get(seatId);
		if (seat == null) {
			throw new InvalidSeatException();
		}
		return seat;
	}

	public static synchronized void updateSeatStatus(Integer seatId, SeatStatus status) throws InvalidSeatException {
		Seats seat = seatsMap.get(seatId);
		if (seat == null) {
			throw new InvalidSeatException();
		}
		seat.setStatus(status);

	}

}
