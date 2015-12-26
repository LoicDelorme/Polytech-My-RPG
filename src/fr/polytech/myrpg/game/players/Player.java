package fr.polytech.myrpg.game.players;

import fr.polytech.myrpg.characters.Character;

/**
 * This class represents a player.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class Player
{
	/**
	 * The name of the player.
	 */
	private final String name;

	/**
	 * The character which is controlled by the character.
	 */
	private final Character character;

	/**
	 * Create a player.
	 * 
	 * @param name
	 *            The name of the player.
	 * @param character
	 *            The character which is controlled by the character.
	 */
	public Player(String name, Character character)
	{
		this.name = name;
		this.character = character;
	}

	/**
	 * Get the name.
	 * 
	 * @return The name.
	 */
	public String getName()
	{
		return this.name;
	}

	/**
	 * Get the character.
	 * 
	 * @return The character.
	 */
	public Character getCharacter()
	{
		return this.character;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		final StringBuilder stringRepresentation = new StringBuilder();
		stringRepresentation.append("Player [name=");
		stringRepresentation.append(this.name);
		stringRepresentation.append(", character=");
		stringRepresentation.append(this.character);
		stringRepresentation.append("]");

		return stringRepresentation.toString();
	}
}
