package in.co.bus.ticket.exception;

/**
 * DuplicateRecordException thrown when a duplicate record occurred
 * 
 * @author Navigable Set
 * @version 1.0
 * @Copyright (c) Navigable Set
 * 
 */
public class DuplicateRecordException  extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param msg
	 *            error message
	 */
	public DuplicateRecordException(String msg) {
		super(msg);
	}
}
