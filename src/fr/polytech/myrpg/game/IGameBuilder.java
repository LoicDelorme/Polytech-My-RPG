package fr.polytech.myrpg.game;

/**
 * This interface represents a game builder.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public interface IGameBuilder
{
	/**
	 * Build the game.
	 * 
	 * @return A game.
	 */
	public IGame buildGame();
}
