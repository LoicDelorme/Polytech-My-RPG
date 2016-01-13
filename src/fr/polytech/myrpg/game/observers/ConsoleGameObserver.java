package fr.polytech.myrpg.game.observers;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import fr.polytech.myrpg.characters.Character;
import fr.polytech.myrpg.characters.Characteristic;
import fr.polytech.myrpg.characters.items.edible.EdibleItem;
import fr.polytech.myrpg.characters.items.equipable.EquipableItem;

/**
 * This class represents a console game observer.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class ConsoleGameObserver implements IGameObserver
{
	/**
	 * The available choices on a new round.
	 */
	public static final String[] AVAILABLE_CHOICES = new String[] { "Attack an opponent", "Use an item" };

	/**
	 * The available characteristics to upgrade.
	 */
	public static final String[] AVAILABLE_CHARACTERISTICS = new String[] { "STRENGTH", "DEXTERITY", "DEFENSE" };

	/**
	 * The scanner.
	 */
	private static final Scanner SCANNER = new Scanner(System.in);

	/**
	 * @see fr.polytech.myrpg.game.observers.IGameObserver#displayInformationBeforeFight(fr.polytech.myrpg.characters.Character, fr.polytech.myrpg.characters.Character)
	 */
	@Override
	public void displayInformationBeforeFight(Character attacker, Character opponnent)
	{
		System.out.println("------------------------------------------------------------------");
		System.out.println(AsciiArtHelper.SWORDS);
		System.out.println("------------------------------------------------------------------");
		System.out.println("Fight between " + attacker.getName() + " and " + opponnent.getName());

		System.out.println(attacker.getName() + " (health : " + attacker.getHealth() + ")");
		System.out.println(opponnent.getName() + " (health : " + opponnent.getHealth() + ")");
	}

	/**
	 * @see fr.polytech.myrpg.game.observers.IGameObserver#displayInformationAfterFight(fr.polytech.myrpg.characters.Character, fr.polytech.myrpg.characters.Character)
	 */
	@Override
	public void displayInformationAfterFight(Character attacker, Character opponnent)
	{
		System.out.println("------------------------------------------------------------------");
		System.out.println(attacker.getName() + " (health : " + attacker.getHealth() + ")");
		System.out.println(opponnent.getName() + " (health : " + opponnent.getHealth() + ")");
	}

	/**
	 * @see fr.polytech.myrpg.game.observers.IGameObserver#displayDeadCharacter(java.lang.String)
	 */
	@Override
	public void displayDeadCharacter(String name)
	{
		System.out.println(AsciiArtHelper.DEATH);
		System.out.println("RIP " + name);
	}

	/**
	 * @see fr.polytech.myrpg.game.observers.IGameObserver#displayCharacterCanLevelUpAndGetCharacteristicToUpgrade()
	 */
	@Override
	public Characteristic displayCharacterCanLevelUpAndGetCharacteristicToUpgrade()
	{
		System.out.println("The attacker character can level up");
		return Characteristic.valueOf(AVAILABLE_CHARACTERISTICS[displayAndGetPlayerChoice(Arrays.asList(AVAILABLE_CHARACTERISTICS), "Which characteristic(s) do you want to upgrade?") - 1]);
	}

	/**
	 * @see fr.polytech.myrpg.game.observers.IGameObserver#displayAndGetPlayerChoice(java.util.List, java.lang.String)
	 */
	@Override
	public int displayAndGetPlayerChoice(List<String> choices, String title)
	{
		int playerChoice = -1;
		while ((playerChoice < 1) || (playerChoice > choices.size()))
		{
			displayChoices(choices, title);
			playerChoice = parsePlayerChoiceInput(SCANNER.nextLine());
		}

		return playerChoice;
	}

	/**
	 * Display all choices.
	 * 
	 * @param choices
	 *            The choices.
	 * @param title
	 *            The title.
	 */
	private void displayChoices(List<String> choices, String title)
	{
		System.out.println(title);

		int currentIndex = 1;
		for (String choice : choices)
		{
			System.out.println((currentIndex++) + "- " + choice);
		}
	}

	/**
	 * Parse the player choice.
	 * 
	 * @param choice
	 *            The choice.
	 * @return The parsed player choice.
	 */
	private int parsePlayerChoiceInput(String choice)
	{
		try
		{
			return Integer.parseInt(choice);
		}
		catch (NumberFormatException e)
		{
			return -1;
		}
	}

	/**
	 * @see fr.polytech.myrpg.game.observers.IGameObserver#displayChoiceForTheCurrentPlayer(String)
	 */
	@Override
	public int displayChoiceForTheCurrentPlayer(String name)
	{
		System.out.println("------------------------------------------------------------------");
		System.out.println("NEW ROUND");
		System.out.println("------------------------------------------------------------------");
		System.out.println("Current player: " + name);

		return displayAndGetPlayerChoice(Arrays.asList(AVAILABLE_CHOICES), "What do you want to do?");
	}

	/**
	 * @see fr.polytech.myrpg.game.observers.IGameObserver#displayMessage(java.lang.String)
	 */
	@Override
	public void displayMessage(String message)
	{
		System.out.println(message);
	}

	/**
	 * @see fr.polytech.myrpg.game.observers.IGameObserver#hasDroppedEdibleItems(fr.polytech.myrpg.characters.items.edible.EdibleItem)
	 */
	@Override
	public void hasDroppedEdibleItems(EdibleItem droppedItem)
	{
		System.out.println("------------------------------------------------------------------");
		System.out.println("DROPPED ITEM");
		System.out.println("------------------------------------------------------------------");
		System.out.println("Congratulations, you have found an edible item and it was stored in your inventory!");
		System.out.println("Name: " + droppedItem.getName());
		System.out.println("Weight: " + droppedItem.getWeight());
		System.out.println("------------------------------------------------------------------");
	}

	/**
	 * @see fr.polytech.myrpg.game.observers.IGameObserver#hasDroppedEquipableItems(EquipableItem)
	 */
	@Override
	public void hasDroppedEquipableItems(EquipableItem droppedItem)
	{
		System.out.println("------------------------------------------------------------------");
		System.out.println("DROPPED ITEM");
		System.out.println("------------------------------------------------------------------");
		System.out.println("Congratulations, you have found an equipable item and it was equipped on your character before the fight!");
		System.out.println("Name: " + droppedItem.getName());
		System.out.println("Weight: " + droppedItem.getWeight());
		System.out.println("------------------------------------------------------------------");
	}

	/**
	 * @see fr.polytech.myrpg.game.observers.IGameObserver#displayEndGame()
	 */
	@Override
	public void displayEndGame()
	{
		System.out.println("------------------------------------------------------------------");
		System.out.println("END OF THE GAME");
		System.out.println("------------------------------------------------------------------");
	}
}