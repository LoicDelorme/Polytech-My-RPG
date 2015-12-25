package fr.polytech.myrpg.characters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.polytech.myrpg.characters.exceptions.InvalidConstraintsException;
import fr.polytech.myrpg.characters.exceptions.TooHeavyCharacterException;
import fr.polytech.myrpg.characters.exceptions.TooHighCharacteristicsValueException;
import fr.polytech.myrpg.characters.exceptions.TooLessSpecialMovesException;
import fr.polytech.myrpg.characters.exceptions.TooMuchArmorsException;
import fr.polytech.myrpg.characters.exceptions.TooMuchWeaponsException;
import fr.polytech.myrpg.characters.items.Item;
import fr.polytech.myrpg.characters.items.edible.EdibleItem;
import fr.polytech.myrpg.characters.items.equipable.EquipableItem;
import fr.polytech.myrpg.characters.items.equipable.EquipableItemType;
import fr.polytech.myrpg.characters.specialmove.ISpecialMove;
import fr.polytech.myrpg.characters.specialmove.SpecialMoveType;

/**
 * This class represents a character.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public abstract class Character
{
	/**
	 * The number of special moves required.
	 */
	public static final int NB_SPECIAL_MOVES_REQUIRED = 2;

	/**
	 * The maximal characteristics value.
	 */
	public static final int MAXIMAL_CHARACTERISTICS_VALUE = 70;

	/**
	 * The default maximal weight that a character can carry.
	 */
	public static final int DEFAULT_MAXIMAL_WEIGHT = 75;

	/**
	 * The characteristic value to add on level up.
	 */
	public static final int CHARACTERIC_VALUE_TO_ADD_ON_LEVEL_UP = 5;

	/**
	 * The multiplier coefficient for calculating the next level.
	 */
	public static final int MULTIPLIER_COEFFICIENT_NEXT_LEVEL = 1000;

	/**
	 * The default maximal number of armor.
	 */
	public static final int DEFAULT_MAXIMAL_NB_ARMOR = 2;

	/**
	 * The default maximal number of weapon.
	 */
	public static final int DEFAULT_MAXIMAL_NB_WEAPON = 1;

	/**
	 * The name of the character.
	 */
	private final String name;

	/**
	 * The characteristics of the character.
	 */
	protected final Map<Characteristic, Integer> characteristics;

	/**
	 * The max health of the character.
	 */
	private int maxHealth;

	/**
	 * The experience level of the character.
	 */
	private int experienceLevel;

	/**
	 * The current experience of the character.
	 */
	private int currentExperience;

	/**
	 * The max weight of the character.
	 */
	private final int maxWeight;

	/**
	 * The current weight of the character.
	 */
	private int currentWeight;

	/**
	 * The inventory of the character.
	 */
	private final List<Item> inventory;

	/**
	 * The special moves of the character.
	 */
	protected final List<ISpecialMove> specialMoves;

	/**
	 * The equipped items of the character.
	 */
	private final List<EquipableItem> equippedItems;

	/**
	 * The maximal number of weapon.
	 */
	private final int maxNbWeapon;

	/**
	 * The current number of weapon.
	 */
	private int currentNbWeapon;

	/**
	 * The maximal number of armor.
	 */
	private final int maxNbArmor;

	/**
	 * The current number of armor.
	 */
	private int currentNbArmor;

	/**
	 * Create a character.
	 * 
	 * @param name
	 *            The name of the character.
	 * @throws TooHighCharacteristicsValueException
	 *             If the characteristics value is higher than expected : {@value #MAXIMAL_CHARACTERISTICS_VALUE}.
	 * @throws TooLessSpecialMovesException
	 *             If there is less than {@value #NB_SPECIAL_MOVES_REQUIRED} special moves.
	 * @throws InvalidConstraintsException
	 *             If constraints are invalid.
	 */
	public Character(String name) throws TooHighCharacteristicsValueException, TooLessSpecialMovesException, InvalidConstraintsException
	{
		this.name = name;

		this.characteristics = new HashMap<Characteristic, Integer>();
		initializeCharacteristics();
		checkCharacteristics();

		this.specialMoves = new ArrayList<ISpecialMove>();
		initializeSpecialMoves();
		checkSpecialMoves();

		this.maxHealth = computeMaxHealthValue();
		this.characteristics.put(Characteristic.HEALTH, this.maxHealth);

		this.experienceLevel = 1;
		this.currentExperience = 0;

		this.maxWeight = computeMaxWeightValue();
		this.currentWeight = 0;

		this.inventory = new ArrayList<Item>();

		this.equippedItems = new ArrayList<EquipableItem>();
		this.maxNbWeapon = DEFAULT_MAXIMAL_NB_WEAPON;
		this.currentNbWeapon = 0;
		this.maxNbArmor = DEFAULT_MAXIMAL_NB_ARMOR;
		this.currentNbArmor = 0;

		checkConstraints();
	}

	/**
	 * Compute the max health value.
	 * 
	 * @return The max health value.
	 */
	private int computeMaxHealthValue()
	{
		return (computeCharacteristicsValue() + (this.currentExperience * 3));
	}

	/**
	 * Compute the maximal weight value.
	 * 
	 * @return The maximal weight value.
	 */
	private int computeMaxWeightValue()
	{
		return DEFAULT_MAXIMAL_WEIGHT;
	}

	/**
	 * Compute the characteristics value.
	 * 
	 * @return The characteristics value.
	 */
	private int computeCharacteristicsValue()
	{
		int characteristicsValue = 0;
		for (Map.Entry<Characteristic, Integer> entry : this.characteristics.entrySet())
		{
			if (entry.getKey() != Characteristic.HEALTH)
			{
				characteristicsValue += entry.getValue();
			}
		}

		return characteristicsValue;
	}

	/**
	 * Initialize the characteristics.
	 */
	public abstract void initializeCharacteristics();

	/**
	 * Initialize the special moves.
	 */
	public abstract void initializeSpecialMoves();

	/**
	 * Check the characteristics.
	 * 
	 * @throws TooHighCharacteristicsValueException
	 *             If the characteristics value is higher than expected : {@value #MAXIMAL_CHARACTERISTICS_VALUE}.
	 */
	private void checkCharacteristics() throws TooHighCharacteristicsValueException
	{
		final int characteristicsValue = computeCharacteristicsValue();
		if (characteristicsValue > MAXIMAL_CHARACTERISTICS_VALUE)
		{
			throw new TooHighCharacteristicsValueException(String.format("The characteristics value is %d and it is higher than expected : %d", characteristicsValue, MAXIMAL_CHARACTERISTICS_VALUE));
		}
	}

	/**
	 * Check the special moves.
	 * 
	 * @throws TooLessSpecialMovesException
	 *             If there is less than {@value #NB_SPECIAL_MOVES_REQUIRED} special moves.
	 */
	private void checkSpecialMoves() throws TooLessSpecialMovesException
	{
		final int specialMovesSize = this.specialMoves.size();
		if (specialMovesSize < NB_SPECIAL_MOVES_REQUIRED)
		{
			throw new TooLessSpecialMovesException(String.format("There is only %d special moves on your character and %d are required", specialMovesSize, NB_SPECIAL_MOVES_REQUIRED));
		}
	}

	/**
	 * Check the constraints.
	 * 
	 * @throws InvalidConstraintsException
	 *             If constraints are invalid.
	 */
	public abstract void checkConstraints() throws InvalidConstraintsException;

	/**
	 * Check if the character is dead.
	 * 
	 * @return True if he is dead, else False.
	 */
	public boolean isDead()
	{
		return (this.characteristics.get(Characteristic.HEALTH) <= 0);
	}

	/**
	 * Attack an another character.
	 * 
	 * @param opponent
	 *            The character which is attacked.
	 */
	public void attack(Character opponent)
	{
		// Store in a list of special move that the opponent can use.
		final List<ISpecialMove> potentialSpecialMoves = new ArrayList<ISpecialMove>();
		for (ISpecialMove currentSpecialMove : opponent.specialMoves)
		{
			if (currentSpecialMove.canSpecialMoveBeUsed())
			{
				potentialSpecialMoves.add(currentSpecialMove);
			}
		}

		// Set up all effects on the opponent before the fight.
		boolean hasOpponentParry = false;
		boolean hasOpponentRunAway = false;
		final List<ISpecialMove> specialMovesUsed = new ArrayList<ISpecialMove>();
		for (ISpecialMove currentSpecialMoveUsed : potentialSpecialMoves)
		{
			if (currentSpecialMoveUsed.getSpecialMoveType() == SpecialMoveType.PARRY)
			{
				hasOpponentParry = true;
				continue;
			}

			if (currentSpecialMoveUsed.getSpecialMoveType() == SpecialMoveType.RUN_AWAY)
			{
				hasOpponentRunAway = true;
				continue;
			}

			if (currentSpecialMoveUsed.getSpecialMoveType() == SpecialMoveType.HEAL)
			{
				opponent.updateCharacteristic(currentSpecialMoveUsed.getEffect().getCharacteristic(), currentSpecialMoveUsed.getEffect().getValue());
				specialMovesUsed.add(currentSpecialMoveUsed);
				continue;
			}
		}

		processFight(opponent, hasOpponentParry, hasOpponentRunAway);

		// Reverse all effects on the opponent after the fight.
		for (ISpecialMove currentSpecialMoveUsed : specialMovesUsed)
		{
			opponent.updateCharacteristic(currentSpecialMoveUsed.getEffect().getCharacteristic(), currentSpecialMoveUsed.getEffect().getValue() * -1);
		}
	}

	/**
	 * Process the fight between two opponents.
	 * 
	 * @param opponent
	 *            The opponent.
	 * @param hasOpponentParry
	 *            If the opponent has parry.
	 * @param hasOpponentRunAway
	 *            If the opponent has run away.
	 */
	private void processFight(Character opponent, boolean hasOpponentParry, boolean hasOpponentRunAway)
	{
		if (hasOpponentRunAway)
		{
			return;
		}

		final int attackerStrenght = this.computeStrengthValue();
		final int opponentDefense = opponent.computeDefenseValue();
		final int damage = (hasOpponentParry ? (attackerStrenght - opponentDefense) / 2 : attackerStrenght - opponentDefense);

		if (damage > 0)
		{
			opponent.updateCharacteristic(Characteristic.HEALTH, damage * -1);
			increaseExperience(damage * 3);
		}
	}

	/**
	 * Compute the character strength value.
	 * 
	 * @return The computed character strength.
	 */
	private int computeStrengthValue()
	{
		return computeStrengthOrDefenseValue(Characteristic.STRENGTH, EquipableItemType.WEAPON);
	}

	/**
	 * Compute the character defense value.
	 * 
	 * @return The computed character defense.
	 */
	private int computeDefenseValue()
	{
		return computeStrengthOrDefenseValue(Characteristic.DEFENSE, EquipableItemType.ARMOR);
	}

	/**
	 * Compute the strength or the defense value.
	 * 
	 * @param characteristic
	 *            The characteristic.
	 * @param equipableItemType
	 *            The equipable item type.
	 * @return The computed value.
	 */
	private int computeStrengthOrDefenseValue(Characteristic characteristic, EquipableItemType equipableItemType)
	{
		int computedValue = this.characteristics.get(characteristic);

		for (EquipableItem currentEquippedItem : this.equippedItems)
		{
			if (currentEquippedItem.getEquipableItemType() == equipableItemType)
			{
				computedValue += currentEquippedItem.getValueByCharacteristic(characteristic);
			}
		}

		return computedValue;
	}

	/**
	 * Pick up an item.
	 * 
	 * @param item
	 *            The item to pick up.
	 * @throws TooHeavyCharacterException
	 *             If the character is too heavy.
	 */
	public void pickUp(Item item) throws TooHeavyCharacterException
	{
		if (this.currentWeight + item.getWeight() > this.maxWeight)
		{
			throw new TooHeavyCharacterException("The character can't pick up the item because he will be too heavy");
		}

		this.inventory.add(item);
		this.currentWeight += item.getWeight();
	}

	/**
	 * Consume an item.
	 * 
	 * @param itemToConsume
	 *            The item to consume.
	 */
	public void consumeItem(EdibleItem itemToConsume)
	{
		this.inventory.remove(itemToConsume);
		this.currentWeight -= itemToConsume.getWeight();

		for (Map.Entry<Characteristic, Integer> entry : this.characteristics.entrySet())
		{
			updateCharacteristic(entry.getKey(), entry.getValue() + itemToConsume.getValueByCharacteristic(entry.getKey()));
		}
	}

	/**
	 * Drop an item.
	 * 
	 * @param item
	 *            The item to drop.
	 */
	public void dropItem(Item item)
	{
		this.inventory.remove(item);
		this.currentWeight -= item.getWeight();
	}

	/**
	 * Equip the character with an equipable item.
	 * 
	 * @param equipableItem
	 *            The equipable item to equip.
	 * @throws TooHeavyCharacterException
	 *             If the character is too heavy.
	 * @throws TooMuchArmorsException
	 *             If the character can't carry more armor.
	 * @throws TooMuchWeaponsException
	 *             If the character can't carry more weapon.
	 */
	public void equipWith(EquipableItem equipableItem) throws TooHeavyCharacterException, TooMuchArmorsException, TooMuchWeaponsException
	{
		if (this.currentWeight + equipableItem.getWeight() > this.maxWeight)
		{
			throw new TooHeavyCharacterException("The character can't be equipped with the item because he will be too heavy");
		}

		final EquipableItemType equipableItemType = equipableItem.getEquipableItemType();

		if (equipableItemType == EquipableItemType.ARMOR)
		{
			if (this.currentNbArmor == this.maxNbArmor)
			{
				throw new TooMuchArmorsException(String.format("The character can't carry more than %d armors", this.maxNbArmor));
			}

			this.currentNbArmor++;
		}

		if (equipableItemType == EquipableItemType.WEAPON)
		{
			if (this.currentNbWeapon == this.maxNbWeapon)
			{
				throw new TooMuchWeaponsException(String.format("The character can't carry more than %d weapons", this.maxNbWeapon));
			}

			this.currentNbWeapon++;
		}

		this.equippedItems.add(equipableItem);
		this.currentWeight += equipableItem.getWeight();
	}

	/**
	 * Unequip the character with an equipable item.
	 * 
	 * @param equipableItem
	 *            The equipable item to unequip.
	 */
	public void unequipWith(EquipableItem equipableItem)
	{
		final EquipableItemType equipableItemType = equipableItem.getEquipableItemType();

		if (equipableItemType == EquipableItemType.ARMOR)
		{
			this.currentNbArmor--;
		}

		if (equipableItemType == EquipableItemType.WEAPON)
		{
			this.currentNbWeapon--;
		}

		this.equippedItems.remove(equipableItem);
		this.currentWeight -= equipableItem.getWeight();
	}

	/**
	 * Check if the character can level up.
	 * 
	 * @return True if the character can level up, else False.
	 */
	public boolean canLevelUp()
	{
		return (this.currentExperience > (this.experienceLevel * MULTIPLIER_COEFFICIENT_NEXT_LEVEL));
	}

	/**
	 * Increase the experience.
	 * 
	 * @param experienceToAdd
	 *            The experience to add.
	 */
	private void increaseExperience(int experienceToAdd)
	{
		this.currentExperience += experienceToAdd;
	}

	/**
	 * Upgrade a characteristic.
	 * 
	 * @param characteristic
	 *            The characteristic to upgrade.
	 */
	public void upgradeCharacteristic(Characteristic characteristic)
	{
		if (canLevelUp())
		{
			this.experienceLevel++;
			this.currentExperience -= this.experienceLevel * MULTIPLIER_COEFFICIENT_NEXT_LEVEL;
			updateCharacteristic(characteristic, CHARACTERIC_VALUE_TO_ADD_ON_LEVEL_UP);
			this.maxHealth = computeMaxHealthValue();
		}
	}

	/**
	 * Update a characteristic.
	 * 
	 * @param characteristic
	 *            The characteristic to update.
	 * @param value
	 *            The value to add or remove.
	 */
	private void updateCharacteristic(Characteristic characteristic, int value)
	{
		if (characteristic == Characteristic.HEALTH)
		{
			final int newHealthValue = this.characteristics.get(characteristic) + value;
			this.characteristics.put(characteristic, (newHealthValue > this.maxHealth ? this.maxHealth : newHealthValue));
		}
		else
		{
			this.characteristics.put(characteristic, this.characteristics.get(characteristic) + value);
		}
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		final StringBuilder stringRepresentation = new StringBuilder();
		stringRepresentation.append("Character [name=");
		stringRepresentation.append(this.name);
		stringRepresentation.append(", characteristics=");
		stringRepresentation.append(this.characteristics);
		stringRepresentation.append(", currentHealth=");
		stringRepresentation.append(this.characteristics.get(Characteristic.HEALTH));
		stringRepresentation.append(", maxHealth=");
		stringRepresentation.append(this.maxHealth);
		stringRepresentation.append(", experienceLevel=");
		stringRepresentation.append(this.experienceLevel);
		stringRepresentation.append(", currentExperience=");
		stringRepresentation.append(this.currentExperience);
		stringRepresentation.append(", maxWeight=");
		stringRepresentation.append(this.maxWeight);
		stringRepresentation.append(", currentWeight=");
		stringRepresentation.append(this.currentWeight);
		stringRepresentation.append(", inventory=");
		stringRepresentation.append(this.inventory);
		stringRepresentation.append(", specialMoves=");
		stringRepresentation.append(this.specialMoves);
		stringRepresentation.append(", equippedItems=");
		stringRepresentation.append(this.equippedItems);
		stringRepresentation.append(", currentNbWeapon=");
		stringRepresentation.append(this.currentNbWeapon);
		stringRepresentation.append(", currentNbArmor=");
		stringRepresentation.append(this.currentNbArmor);
		stringRepresentation.append("]");

		return stringRepresentation.toString();
	}
}
