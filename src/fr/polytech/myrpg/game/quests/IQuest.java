package fr.polytech.myrpg.game.quests;

import fr.polytech.myrpg.game.observers.IGameObserver;

/**
 * This interface represents a quest.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public interface IQuest
{
	/**
	 * Start the quest.
	 * 
	 * @param gameObserver
	 *            The game observer.
	 */
	public void startQuest(IGameObserver gameObserver);
}