package com.rbc.shopping.exception;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author Anmoldeep Singh Kang Exception Class for handling exceptions.
 */
public class Exception {

	private int status;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy'T'hh:mm:ss")
	private Date timestamp;

	private String error;
	
	public Exception() {
		this.timestamp = new Date();
	}
	
	/**
	 * @param Status code for error
	 * @param Error message string.
	 */
	public Exception(int status, String error) {
		super();
		this.status = status;
		this.error = error;
		this.timestamp = new Date();
	}
	
	/**
	 * @return Timestamp when the error occurs
	 */
	public Date getTimestamp() {
		return timestamp;
	}

	/**
	 * @param Timestamp when the error occurs
	 */
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return Status code
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param Status Code for error
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return Error message string
	 */
	public String getError() {
		return error;
	}

	/**
	 * @param Error message string
	 */
	public void setError(String error) {
		this.error = error;
	}

}
