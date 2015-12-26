package fr.polytech.myrpg.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import fr.polytech.myrpg.characters.Character;
import fr.polytech.myrpg.characters.Characteristic;
import fr.polytech.myrpg.characters.items.Item;
import fr.polytech.myrpg.characters.items.edible.EdibleItem;
import fr.polytech.myrpg.game.players.Player;
import fr.polytech.myrpg.game.players.Team;
import me.grea.antoine.utils.Log;

/**
 * This class represents a game.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class Game implements IGame
{
	/**
	 * The available choices on a new round.
	 */
	public static final String[] AVAILABLE_CHOICES = new String[] { "Attack an opponent", "Use an item" };

	/**
	 * The available characteristics to upgrade.
	 */
	public static final String[] AVAILABLE_CHARACTERISTICS = new String[] { "Strength", "Dexterity", "Defense" };

	/**
	 * The scanner.
	 */
	private static final Scanner SCANNER = new Scanner(System.in);

	/**
	 * The first team.
	 */
	private final Team firstTeam;

	/**
	 * The second team.
	 */
	private final Team secondTeam;

	/**
	 * The items in the world.
	 */
	private final List<Item> items;

	/**
	 * If it is to first team to play.
	 */
	private boolean isToFirstTeamToPlay;

	/**
	 * Create a game.
	 * 
	 * @param firstTeam
	 *            The first team.
	 * @param secondTeam
	 *            The second team.
	 * @param items
	 *            The items in the world.
	 */
	public Game(Team firstTeam, Team secondTeam, List<Item> items)
	{
		this.firstTeam = firstTeam;
		this.secondTeam = secondTeam;
		this.items = items;
		this.isToFirstTeamToPlay = true;
	}

	/**
	 * @see fr.polytech.myrpg.game.IGame#startGame()
	 */
	@Override
	public void startGame()
	{
		while ((!this.firstTeam.allPlayersAreDead()) && (!this.secondTeam.allPlayersAreDead()))
		{
			displayChoiceForTheCurrentCharacterAndAct();
		}

		Log.i("------------------------------------------------------------------");
		Log.i("The team " + (this.firstTeam.allPlayersAreDead() ? this.secondTeam.getName() : this.firstTeam.getName()) + "wins!");
		Log.i("The team " + (this.firstTeam.allPlayersAreDead() ? this.firstTeam.getName() : this.secondTeam.getName()) + "looses!");
		Log.i("------------------------------------------------------------------");
	}

	/**
	 * Display all possible choices for the current character and act.
	 */
	private void displayChoiceForTheCurrentCharacterAndAct()
	{
		final Player currentPlayer = (this.isToFirstTeamToPlay ? this.firstTeam.getCurrentPlayer() : this.secondTeam.getCurrentPlayer());

		Log.i("Current player: " + currentPlayer.getName());
		switch (displayAndGetPlayerChoice(Arrays.asList(AVAILABLE_CHOICES)))
		{
			case 1:
				displayAndAttackOpponent(currentPlayer);
				break;
			case 2:
			default:
				if (!currentPlayer.getCharacter().getInventory().isEmpty())
				{
					displayAndUseItem(currentPlayer);
				}
				else
				{
					displayAndAttackOpponent(currentPlayer);
				}
				break;
		}

		this.isToFirstTeamToPlay = !this.isToFirstTeamToPlay;
	}

	/**
	 * Display and get the player choice.
	 * 
	 * @param choices
	 *            All available choices.
	 * 
	 * @return The corresponding choice.
	 */
	private int displayAndGetPlayerChoice(List<String> choices)
	{
		int playerChoice = -1;
		while ((playerChoice < 1) || (playerChoice > choices.size()))
		{
			displayChoices(choices);
			playerChoice = parsePlayerChoiceInput(SCANNER.nextLine());
		}

		return playerChoice;
	}

	/**
	 * Display all choices.
	 * 
	 * @param choices
	 *            The choices.
	 */
	private void displayChoices(List<String> choices)
	{
		Log.i("What do you want to do?");

		int currentIndex = 1;
		for (String choice : choices)
		{
			Log.i((currentIndex++) + "- " + choice);
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
	 * Display and attack an opponent.
	 * 
	 * @param player
	 *            The player who attack.
	 */
	private void displayAndAttackOpponent(Player player)
	{
		final Team opponentTeam = (this.isToFirstTeamToPlay ? this.secondTeam : this.firstTeam);
		final Player opponent = opponentTeam.getPlayer(displayAndGetPlayerChoice(opponentTeam.convertPlayersIntoString()) - 1);

		processAttack(player, opponent, opponentTeam);
	}

	/**
	 * Process the attack.
	 * 
	 * @param attacker
	 *            The attacker.
	 * @param opponent
	 *            The opponent.
	 * @param opponentTeam
	 *            The opponent team.
	 */
	private void processAttack(Player attacker, Player opponent, Team opponentTeam)
	{
		final Character attackerCharacter = attacker.getCharacter();
		final Character opponentCharacter = opponent.getCharacter();

		Log.i("------------------------------------------------------------------");
		Log.i("Fight between " + attackerCharacter.getName() + " and " + opponentCharacter.getName());

		Log.i(attackerCharacter.getName() + " health before the fight: " + attackerCharacter.getHealth());
		Log.i(opponentCharacter.getName() + " health before the fight: " + opponentCharacter.getHealth());

		attackerCharacter.attack(opponentCharacter);

		Log.i(attackerCharacter.getName() + " health after the fight: " + attackerCharacter.getHealth());
		Log.i(opponentCharacter.getName() + " health after the fight: " + opponentCharacter.getHealth());

		if (opponentCharacter.isDead())
		{
			Log.i("The opponent character died along the fight");
			opponentTeam.hasDied(opponent);
		}

		if (attackerCharacter.canLevelUp())
		{
			Log.i("The attacker character can level up");
			attackerCharacter.upgradeCharacteristic(displayAndGetCharacteristic());
		}

		Log.i("------------------------------------------------------------------");
	}

	/**
	 * Display and get characteristic to upgrade.
	 * 
	 * @return The characteristic to upgrade.
	 */
	private Characteristic displayAndGetCharacteristic()
	{
		return Characteristic.valueOf(AVAILABLE_CHARACTERISTICS[displayAndGetPlayerChoice(Arrays.asList(AVAILABLE_CHARACTERISTICS)) - 1]);
	}

	/**
	 * Display and use an item.
	 * 
	 * @param player
	 *            The current player.
	 */
	private void displayAndUseItem(Player player)
	{
		final Character character = player.getCharacter();
		final List<EdibleItem> inventory = character.getInventory();
		final int choosenItemOffset = displayAndGetPlayerChoice(convertInventoryIntoString(inventory));
		character.consumeItem(inventory.get(choosenItemOffset - 1));
	}

	/**
	 * Convert inventory into string.
	 * 
	 * @param inventory
	 *            The inventory to convert.
	 * @return The converted inventory.
	 */
	private List<String> convertInventoryIntoString(List<EdibleItem> inventory)
	{
		final List<String> convertedItems = new ArrayList<String>();
		for (Item currentItem : inventory)
		{
			convertedItems.add(currentItem.getName());
		}

		return convertedItems;
	}
}
