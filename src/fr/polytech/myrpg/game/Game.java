package fr.polytech.myrpg.game;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import fr.polytech.myrpg.characters.Character;
import fr.polytech.myrpg.characters.Characteristic;
import fr.polytech.myrpg.characters.exceptions.TooHeavyCharacterException;
import fr.polytech.myrpg.characters.exceptions.TooManyArmorsException;
import fr.polytech.myrpg.characters.exceptions.TooManyWeaponsException;
import fr.polytech.myrpg.characters.items.Item;
import fr.polytech.myrpg.characters.items.edible.EdibleItem;
import fr.polytech.myrpg.characters.items.equipable.EquipableItem;
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
	public static final String[] AVAILABLE_CHARACTERISTICS = new String[] { "STRENGTH", "DEXTERITY", "DEFENSE" };

	/**
	 * The scanner.
	 */
	private static final Scanner SCANNER = new Scanner(System.in);

	/**
	 * The secure random generator.
	 */
	private static final SecureRandom SECURE_RANDOM_GENERATOR = new SecureRandom();

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
			displayChoiceForTheCurrentPlayerAndAct();
		}

		System.out.println("------------------------------------------------------------------");
		System.out.println("END OF THE GAME");
		System.out.println("------------------------------------------------------------------");
		System.out.println("The team " + (this.firstTeam.allPlayersAreDead() ? this.secondTeam.getName() : this.firstTeam.getName()) + " wins!");
		System.out.println("The team " + (this.firstTeam.allPlayersAreDead() ? this.firstTeam.getName() : this.secondTeam.getName()) + " looses!");
		System.out.println("------------------------------------------------------------------");
	}

	/**
	 * Display all possible choices for the current player and act.
	 */
	private void displayChoiceForTheCurrentPlayerAndAct()
	{
		final Player currentPlayer = (this.isToFirstTeamToPlay ? this.firstTeam.getCurrentPlayer() : this.secondTeam.getCurrentPlayer());

		System.out.println("------------------------------------------------------------------");
		System.out.println("NEW ROUND");
		System.out.println("------------------------------------------------------------------");
		System.out.println("Current player: " + currentPlayer.getName());

		switch (displayAndGetPlayerChoice(Arrays.asList(AVAILABLE_CHOICES), "What do you want to do?"))
		{
			case 1:
				tryToPickUpAnItem(currentPlayer);
				displayAndAttackOpponent(currentPlayer);
				break;
			case 2:
				if (!currentPlayer.getCharacter().getInventory().isEmpty())
				{
					displayAndUseItem(currentPlayer);
				}
				else
				{
					tryToPickUpAnItem(currentPlayer);
					displayAndAttackOpponent(currentPlayer);
				}
				break;
			default:
				break;
		}

		this.isToFirstTeamToPlay = !this.isToFirstTeamToPlay;
	}

	/**
	 * Try to pickup an item.
	 * 
	 * @param currentPlayer
	 *            The current player.
	 */
	private void tryToPickUpAnItem(Player currentPlayer)
	{
		if (!this.items.isEmpty())
		{
			final double generatedValue = SECURE_RANDOM_GENERATOR.nextDouble();
			if (generatedValue < 0.25)
			{
				final Item droppedItem = this.items.get(SECURE_RANDOM_GENERATOR.nextInt(this.items.size()));

				if (droppedItem instanceof EdibleItem)
				{
					try
					{
						final EdibleItem edibleItem = (EdibleItem) droppedItem;
						currentPlayer.getCharacter().pickUp(edibleItem);
						System.out.println("------------------------------------------------------------------");
						System.out.println("DROPPED ITEM");
						System.out.println("------------------------------------------------------------------");
						System.out.println("Congratulations, you have found an edible item and it was stored in your inventory!");
						System.out.println("Name: " + edibleItem.getName());
						System.out.println("Weight: " + edibleItem.getWeight());
						System.out.println("------------------------------------------------------------------");
					}
					catch (TooHeavyCharacterException e)
					{
						Log.e(e);
					}
				}

				if (droppedItem instanceof EquipableItem)
				{
					try
					{
						final EquipableItem equipableItem = (EquipableItem) droppedItem;
						currentPlayer.getCharacter().equipWith(equipableItem);
						System.out.println("------------------------------------------------------------------");
						System.out.println("DROPPED ITEM");
						System.out.println("------------------------------------------------------------------");
						System.out.println("Congratulations, you have found an equipable item and it was equipped on your character before the fight!");
						System.out.println("Name: " + equipableItem.getName());
						System.out.println("Weight: " + equipableItem.getWeight());
						System.out.println("------------------------------------------------------------------");
					}
					catch (TooHeavyCharacterException | TooManyArmorsException | TooManyWeaponsException e)
					{
						Log.e(e);
					}
				}

				this.items.remove(droppedItem);
			}
		}
	}

	/**
	 * Display and get the player choice.
	 * 
	 * @param choices
	 *            All available choices.
	 * @param title
	 *            The title.
	 * 
	 * @return The corresponding choice.
	 */
	private int displayAndGetPlayerChoice(List<String> choices, String title)
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
	 * Display and attack an opponent.
	 * 
	 * @param player
	 *            The player who attack.
	 */
	private void displayAndAttackOpponent(Player player)
	{
		final Team opponentTeam = (this.isToFirstTeamToPlay ? this.secondTeam : this.firstTeam);
		final Player opponent = opponentTeam.getPlayer(displayAndGetPlayerChoice(opponentTeam.convertPlayersIntoString(), "Which player(s) do you want to attack?") - 1);

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

		System.out.println("------------------------------------------------------------------");
		System.out.println(AsciiArtHelper.SWORDS);
		System.out.println("------------------------------------------------------------------");
		System.out.println("Fight between " + attackerCharacter.getName() + " and " + opponentCharacter.getName());

		System.out.println(attackerCharacter.getName() + " (health : " + attackerCharacter.getHealth() + ")");
		System.out.println(opponentCharacter.getName() + " (health : " + opponentCharacter.getHealth() + ")");

		attackerCharacter.attack(opponentCharacter);

		System.out.println("------------------------------------------------------------------");
		System.out.println(attackerCharacter.getName() + " (health : " + attackerCharacter.getHealth() + ")");
		System.out.println(opponentCharacter.getName() + " (health : " + opponentCharacter.getHealth() + ")");

		if (opponentCharacter.isDead())
		{
			System.out.println(AsciiArtHelper.DEATH);
			System.out.println("RIP " + opponentCharacter.getName());
			opponentTeam.hasDied(opponent);
		}

		if (attackerCharacter.canLevelUp())
		{
			System.out.println("The attacker character can level up");
			attackerCharacter.upgradeCharacteristic(displayAndGetCharacteristic());
		}

		System.out.println("------------------------------------------------------------------");
	}

	/**
	 * Display and get characteristic to upgrade.
	 * 
	 * @return The characteristic to upgrade.
	 */
	private Characteristic displayAndGetCharacteristic()
	{
		return Characteristic.valueOf(AVAILABLE_CHARACTERISTICS[displayAndGetPlayerChoice(Arrays.asList(AVAILABLE_CHARACTERISTICS), "Which characteristic(s) do you want to upgrade?") - 1]);
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
		final int choosenItemOffset = displayAndGetPlayerChoice(convertInventoryIntoString(inventory), "Which items to you want to use?");
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
