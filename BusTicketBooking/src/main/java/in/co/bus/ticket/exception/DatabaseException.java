package in.co.bus.ticket.exception;

/**
 * DatabaseException is propogated by DAO classes when an unhandled Database
 * exception occurred
 *
 * @author Navigable Set
 * @version 1.0
 * @Copyright (c) Navigable Set
 *
 */

public class DatabaseException  extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
    * @param msg
    *            : Error message
    */
   public DatabaseException(String msg) {
       super(msg);
   }
}

