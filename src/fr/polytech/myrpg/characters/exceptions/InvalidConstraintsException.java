package fr.polytech.myrpg.characters.exceptions;

/**
 * This exception is thrown if constraints are invalid.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class InvalidConstraintsException extends Exception
{
	/**
	 * The serial UID.
	 */
	private static final long serialVersionUID = -3601778728273701674L;

	/**
	 * Create an invalid constraints exception.
	 * 
	 * @param message
	 *            The message to display.
	 */
	public InvalidConstraintsException(String message)
	{
		super(message);
	}
}
