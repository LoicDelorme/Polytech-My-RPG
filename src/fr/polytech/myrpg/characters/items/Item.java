package fr.polytech.myrpg.characters.items;

import fr.polytech.myrpg.characters.Characteristic;

/**
 * This class represents an item.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public abstract class Item
{
	/**
	 * The name of the item.
	 */
	private final String name;

	/**
	 * The weight of the item.
	 */
	private final int weight;

	/**
	 * If the item is edible.
	 */
	private final boolean isEdible;

	/**
	 * If the item is equipable.
	 */
	private final boolean isEquipable;

	/**
	 * Create an item.
	 * 
	 * @param name
	 *            The name of the item.
	 * @param weight
	 *            The weight of the item.
	 * @param isEdible
	 *            If the item is edible.
	 * @param isEquipable
	 *            If the item is equipable.
	 */
	public Item(String name, int weight, boolean isEdible, boolean isEquipable)
	{
		this.name = name;
		this.weight = weight;
		this.isEdible = isEdible;
		this.isEquipable = isEquipable;
	}

	/**
	 * Get the name of the item.
	 * 
	 * @return The name of the item.
	 */
	public String getName()
	{
		return this.name;
	}

	/**
	 * Get the weight of the item.
	 * 
	 * @return The weight of the item.
	 */
	public int getWeight()
	{
		return this.weight;
	}

	/**
	 * Get if the item is edible.
	 * 
	 * @return True if the item is edible, else False.
	 */
	public boolean isEdible()
	{
		return this.isEdible;
	}

	/**
	 * Get if the item is equipable.
	 * 
	 * @return True if the item is equipable, else False.
	 */
	public boolean isEquipable()
	{
		return this.isEquipable;
	}

	/**
	 * Get the value for a specific characteristic.
	 * 
	 * @param characteristic
	 *            The specific characteristic.
	 * @return The corresponding value.
	 */
	public abstract int getValueByCharacteristic(Characteristic characteristic);
}
