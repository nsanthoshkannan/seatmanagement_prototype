package com.nokia.seatmanagement.core.exceptions;


/**
 * Raised when seat is unavailable but tried to raise claim request
 * @author Santhosh
 *
 */
public class SeatUnavailableException extends Exception {

	public SeatUnavailableException(String message) {
		super(message);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
}
