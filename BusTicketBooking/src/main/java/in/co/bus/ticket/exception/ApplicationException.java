package in.co.bus.ticket.exception;

/**
 * ApplicationException is propogated from Service classes when an business
 * logic exception occurered.
 * 
 * @author Navigable Set
 * @version 1.0
 * @Copyright (c) Navigable Set
 * 
 */

public class ApplicationException  extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param msg
	 *            : Error message
	 */
	public ApplicationException(String msg) {
		super(msg);
	}
}
