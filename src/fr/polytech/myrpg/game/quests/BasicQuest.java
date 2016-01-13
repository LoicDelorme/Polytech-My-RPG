package fr.polytech.myrpg.game.quests;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import fr.polytech.myrpg.characters.Character;
import fr.polytech.myrpg.characters.exceptions.TooHeavyCharacterException;
import fr.polytech.myrpg.characters.exceptions.TooManyArmorsException;
import fr.polytech.myrpg.characters.exceptions.TooManyWeaponsException;
import fr.polytech.myrpg.characters.items.Item;
import fr.polytech.myrpg.characters.items.edible.EdibleItem;
import fr.polytech.myrpg.characters.items.equipable.EquipableItem;
import fr.polytech.myrpg.game.observers.IGameObserver;
import fr.polytech.myrpg.game.players.Player;
import fr.polytech.myrpg.game.players.Team;

/**
 * This class represents a basic quest.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class BasicQuest implements IQuest
{
	/**
	 * The secure random generator.
	 */
	private static final SecureRandom SECURE_RANDOM_GENERATOR = new SecureRandom();

	/**
	 * The edible items in the quest.
	 */
	private final List<EdibleItem> edibleItems;

	/**
	 * The equipable items in the quest.
	 */
	private final List<EquipableItem> equipableItems;

	/**
	 * If it is to first team to play.
	 */
	private boolean isToFirstTeamToPlay;

	/**
	 * The begin text.
	 */
	private final String beginText;

	/**
	 * The end text.
	 */
	private final String endText;

	/**
	 * The first team.
	 */
	private final Team firstTeam;

	/**
	 * The second team.
	 */
	private final Team secondTeam;

	/**
	 * Create a basic quest.
	 * 
	 * @param beginText
	 *            The begin text.
	 * @param endText
	 *            The end text.
	 * @param firstTeam
	 *            The first team.
	 * @param secondTeam
	 *            The second team.
	 * @param edibleItems
	 *            The edible items in the quest.
	 * @param equipableItems
	 *            The equipable items in the quest.
	 */
	public BasicQuest(String beginText, String endText, Team firstTeam, Team secondTeam, List<EdibleItem> edibleItems, List<EquipableItem> equipableItems)
	{
		this.beginText = beginText;
		this.endText = endText;
		this.firstTeam = firstTeam;
		this.secondTeam = secondTeam;
		this.isToFirstTeamToPlay = true;
		this.edibleItems = edibleItems;
		this.equipableItems = equipableItems;
	}

	/**
	 * @see fr.polytech.myrpg.game.quests.IQuest#startQuest(fr.polytech.myrpg.game.observers.IGameObserver)
	 */
	@Override
	public void startQuest(IGameObserver gameObserver)
	{
		gameObserver.displayMessage(this.beginText);

		while ((!this.firstTeam.allPlayersAreDead()) && (!this.secondTeam.allPlayersAreDead()))
		{
			final Player currentPlayer = (this.isToFirstTeamToPlay ? this.firstTeam.getCurrentPlayer() : this.secondTeam.getCurrentPlayer());
			final int choice = gameObserver.displayChoiceForTheCurrentPlayer(currentPlayer.getName());

			switch (choice)
			{
				case 1:
					tryToPickUpAnItem(currentPlayer, gameObserver);
					displayAndAttackOpponent(currentPlayer, gameObserver);
					break;
				case 2:
					if (!currentPlayer.getCharacter().getInventory().isEmpty())
					{
						displayAndUseItem(currentPlayer, gameObserver);
					}
					else
					{
						tryToPickUpAnItem(currentPlayer, gameObserver);
						displayAndAttackOpponent(currentPlayer, gameObserver);
					}
					break;
				default:
					break;
			}

			this.isToFirstTeamToPlay = !this.isToFirstTeamToPlay;
		}

		gameObserver.displayMessage(this.endText);
	}

	/**
	 * Try to pickup an item.
	 * 
	 * @param currentPlayer
	 *            The current player.
	 * @param gameObserver
	 *            The game observer.
	 */
	private void tryToPickUpAnItem(Player currentPlayer, IGameObserver gameObserver)
	{
		if (!this.edibleItems.isEmpty())
		{
			if (SECURE_RANDOM_GENERATOR.nextDouble() < 0.4)
			{
				try
				{
					final EdibleItem droppedItem = this.edibleItems.get(SECURE_RANDOM_GENERATOR.nextInt(this.edibleItems.size()));
					currentPlayer.getCharacter().pickUp(droppedItem);
					gameObserver.hasDroppedEdibleItems(droppedItem);
					this.edibleItems.remove(droppedItem);
				}
				catch (TooHeavyCharacterException e)
				{
					gameObserver.displayMessage(e.getMessage());
				}
			}
		}

		if (!this.equipableItems.isEmpty())
		{
			if (SECURE_RANDOM_GENERATOR.nextDouble() < 0.25)
			{
				try
				{
					final EquipableItem droppedItem = this.equipableItems.get(SECURE_RANDOM_GENERATOR.nextInt(this.equipableItems.size()));
					currentPlayer.getCharacter().equipWith(droppedItem);
					gameObserver.hasDroppedEquipableItems(droppedItem);
					this.equipableItems.remove(droppedItem);
				}
				catch (TooHeavyCharacterException | TooManyArmorsException | TooManyWeaponsException e)
				{
					gameObserver.displayMessage(e.getMessage());
				}
			}
		}
	}

	/**
	 * Display and attack an opponent.
	 * 
	 * @param player
	 *            The player who attacks.
	 * @param gameObserver
	 *            The game observer.
	 */
	private void displayAndAttackOpponent(Player player, IGameObserver gameObserver)
	{
		final Team opponentTeam = (this.isToFirstTeamToPlay ? this.secondTeam : this.firstTeam);
		final Player opponent = opponentTeam.getPlayer(gameObserver.displayAndGetPlayerChoice(opponentTeam.convertPlayersIntoString(), "Which player(s) do you want to attack?") - 1);

		final Character attackerCharacter = player.getCharacter();
		final Character opponentCharacter = opponent.getCharacter();

		gameObserver.displayInformationBeforeFight(attackerCharacter, opponentCharacter);
		attackerCharacter.attack(opponentCharacter);
		gameObserver.displayInformationAfterFight(attackerCharacter, opponentCharacter);

		if (opponentCharacter.isDead())
		{
			gameObserver.displayDeadCharacter(opponentCharacter.getName());
			opponentTeam.hasDied(opponent);
		}

		if (attackerCharacter.canLevelUp())
		{
			attackerCharacter.upgradeCharacteristic(gameObserver.displayCharacterCanLevelUpAndGetCharacteristicToUpgrade());
		}
	}

	/**
	 * Display and use an item.
	 * 
	 * @param player
	 *            The current player.
	 * @param gameObserver
	 *            The game observer.
	 */
	private void displayAndUseItem(Player player, IGameObserver gameObserver)
	{
		final Character character = player.getCharacter();
		final List<EdibleItem> inventory = character.getInventory();
		final int choosenItemOffset = gameObserver.displayAndGetPlayerChoice(convertInventoryIntoString(inventory), "Which items to you want to use?");
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