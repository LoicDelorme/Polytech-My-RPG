package fr.polytech.myrpg.game.players;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a team.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class Team
{
	/**
	 * The name of the team.
	 */
	private final String name;

	/**
	 * The list of all players.
	 */
	private final List<Player> players;

	/**
	 * The current player offset.
	 */
	private int currentPlayerOffset;

	/**
	 * Create a team.
	 * 
	 * @param name
	 *            The name.
	 */
	public Team(String name)
	{
		this.name = name;
		this.players = new ArrayList<Player>();
		this.currentPlayerOffset = 0;
	}

	/**
	 * Add a player.
	 * 
	 * @param player
	 *            The player to add.
	 */
	public void addPlayer(Player player)
	{
		this.players.add(player);
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
	 * Check if all players are dead.
	 * 
	 * @return True if all players are dead, else False.
	 */
	public boolean allPlayersAreDead()
	{
		return this.players.isEmpty();
	}

	/**
	 * Get the current player.
	 * 
	 * @return The current player.
	 */
	public Player getCurrentPlayer()
	{
		final Player player = this.players.get(this.currentPlayerOffset);
		this.currentPlayerOffset = (this.currentPlayerOffset + 1) % (this.players.size() - 1);
		return player;
	}

	/**
	 * Get the player.
	 * 
	 * @param offset
	 *            The offset.
	 * @return The corresponding player.
	 */
	public Player getPlayer(int offset)
	{
		return this.players.get(offset);
	}

	/**
	 * The player has died.
	 * 
	 * @param player
	 *            The player.
	 */
	public void hasDied(Player player)
	{
		this.players.remove(player);
	}

	/**
	 * Convert players into string.
	 * 
	 * @return The converted players.
	 */
	public List<String> convertPlayersIntoString()
	{
		final List<String> convertedPlayers = new ArrayList<String>();
		for (Player currentPlayer : this.players)
		{
			convertedPlayers.add(currentPlayer.getCharacter().getName() + " / health: " + currentPlayer.getCharacter().getHealth());
		}

		return convertedPlayers;
	}
}
