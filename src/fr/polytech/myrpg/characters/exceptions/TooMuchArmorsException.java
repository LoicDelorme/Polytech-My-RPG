package fr.polytech.myrpg.characters.exceptions;

/**
 * This exception is thrown if the character can't carry more armors.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class TooMuchArmorsException extends Exception
{
	/**
	 * The serial UID.
	 */
	private static final long serialVersionUID = 6265941275421334181L;

	/**
	 * Create a too much armors exception.
	 * 
	 * @param message
	 *            The message to display.
	 */
	public TooMuchArmorsException(String message)
	{
		super(message);
	}
}
