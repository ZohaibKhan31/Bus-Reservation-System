package in.co.bus.ticket.exception;

/**
 * RecordNotFoundException thrown when a record not found occurred
 * 
 * @author Navigable Set
 * @version 1.0
 * @Copyright (c) Navigable Set
 * 
 */

public class RecordNotFoundException extends Exception
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param msg
	 *            error message
	 */
	public RecordNotFoundException(String msg) {
		super(msg);

	}
}
