package fr.polytech.myrpg;

import fr.polytech.myrpg.game.GameBuilder;
import fr.polytech.myrpg.game.IGame;

/**
 * The launcher of the application.
 * 
 * @author DELORME Loïc
 */
public class Launcher
{
	/**
	 * The entry of the application.
	 * 
	 * @param args
	 *            Some arguments.
	 */
	public static void main(String[] args)
	{
		final IGame buildGame = new GameBuilder().buildGame();
		if (buildGame != null)
		{
			buildGame.startGame();
		}
	}
}
