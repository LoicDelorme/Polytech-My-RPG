package fr.polytech.myrpg.characters.exceptions;

/**
 * This exception is thrown if the character is too heavy.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class TooHeavyCharacterException extends Exception
{
	/**
	 * The serial UID.
	 */
	private static final long serialVersionUID = -1280791423295068100L;

	/**
	 * Create a too heavy character exception.
	 * 
	 * @param message
	 *            The message to display.
	 */
	public TooHeavyCharacterException(String message)
	{
		super(message);
	}
}
