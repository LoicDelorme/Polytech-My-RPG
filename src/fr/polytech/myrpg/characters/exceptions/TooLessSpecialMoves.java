package fr.polytech.myrpg.characters.exceptions;

/**
 * This exception is thrown if there is less than {@value fr.polytech.myrpg.characters.Character#SPECIAL_MOVES_REQUIRED} special moves on your character.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class TooLessSpecialMoves extends Exception
{
	/**
	 * The serial UID.
	 */
	private static final long serialVersionUID = 4599094418605013416L;

	/**
	 * Create a too less special moves exception.
	 * 
	 * @param message
	 *            The message to display.
	 */
	public TooLessSpecialMoves(String message)
	{
		super(message);
	}
}
