package fr.polytech.myrpg.characters.exceptions;

/**
 * This exception is thrown if the character can't carry more weapons.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class TooMuchWeaponsException extends Exception
{
	/**
	 * The serial UID.
	 */
	private static final long serialVersionUID = -9017278163839610845L;

	/**
	 * Create a too much weapons exception.
	 * 
	 * @param message
	 *            The message to display.
	 */
	public TooMuchWeaponsException(String message)
	{
		super(message);
	}
}
