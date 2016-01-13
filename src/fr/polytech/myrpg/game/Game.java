package fr.polytech.myrpg.game;

import java.util.List;

import fr.polytech.myrpg.game.observers.IGameObserver;
import fr.polytech.myrpg.game.quests.IQuest;

/**
 * This class represents a game.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class Game implements IGame
{
	/**
	 * The list of all quests.
	 */
	private final List<IQuest> quests;

	/**
	 * The game observer.
	 */
	private final IGameObserver gameObserver;

	/**
	 * Create a game.
	 * 
	 * @param quests
	 *            The quests.
	 * @param gameObserver
	 *            The game observer.
	 */
	public Game(List<IQuest> quests, IGameObserver gameObserver)
	{
		this.quests = quests;
		this.gameObserver = gameObserver;
	}

	/**
	 * @see fr.polytech.myrpg.game.IGame#startGame()
	 */
	@Override
	public void startGame()
	{
		for (IQuest currentQuest : this.quests)
		{
			currentQuest.startQuest(this.gameObserver);
		}

		this.gameObserver.displayEndGame();
	}
}