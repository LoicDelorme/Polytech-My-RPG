package fr.polytech.myrpg.game.observers;

import java.util.List;

import fr.polytech.myrpg.characters.Character;
import fr.polytech.myrpg.characters.Characteristic;
import fr.polytech.myrpg.characters.items.edible.EdibleItem;
import fr.polytech.myrpg.characters.items.equipable.EquipableItem;

/**
 * This interface represents a game observer.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public interface IGameObserver
{
	/**
	 * Display information before the fight.
	 * 
	 * @param attacker
	 *            The attacker.
	 * @param opponent
	 *            The opponent.
	 */
	public void displayInformationBeforeFight(Character attacker, Character opponent);

	/**
	 * Display information after the fight.
	 * 
	 * @param attacker
	 *            The attacker.
	 * @param opponent
	 *            The opponent.
	 */
	public void displayInformationAfterFight(Character attacker, Character opponent);

	/**
	 * Display that the character is dead.
	 * 
	 * @param name
	 *            The name.
	 */
	public void displayDeadCharacter(String name);

	/**
	 * Display that the character can level up and get the characteristic to upgrade.
	 * 
	 * @return The selected characteristic.
	 */
	public Characteristic displayCharacterCanLevelUpAndGetCharacteristicToUpgrade();

	/**
	 * Display all possible choices for the current player.
	 * 
	 * @param name
	 *            The name.
	 * @return The choice of the current character.
	 */
	public int displayChoiceForTheCurrentPlayer(String name);

	/**
	 * Display a message.
	 * 
	 * @param message
	 *            The message.
	 */
	public void displayMessage(String message);

	/**
	 * The player has dropped an edible items.
	 * 
	 * @param droppedItem
	 *            The item.
	 */
	public void hasDroppedEdibleItems(EdibleItem droppedItem);

	/**
	 * The player has dropped an equipable items.
	 * 
	 * @param droppedItem
	 *            The item.
	 */
	public void hasDroppedEquipableItems(EquipableItem droppedItem);

	/**
	 * Display and get the player choice.
	 * 
	 * @param choices
	 *            The available choices.
	 * @param title
	 *            The title.
	 * @return The choice.
	 */
	public int displayAndGetPlayerChoice(List<String> choices, String title);

	/**
	 * Display end of the game.
	 */
	public void displayEndGame();
}