package fr.polytech.myrpg.characters.exceptions;

/**
 * This exception is thrown if the characteristics value is higher than expected : {@value fr.polytech.myrpg.characters.Character#MAXIMAL_CHARACTERISTICS_VALUE}.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class TooHighCharacteristicsValue extends Exception
{
	/**
	 * The serial UID.
	 */
	private static final long serialVersionUID = 133349201679048730L;

	/**
	 * Create a too high characteristics value exception.
	 * 
	 * @param message
	 *            The message to display.
	 */
	public TooHighCharacteristicsValue(String message)
	{
		super(message);
	}
}
