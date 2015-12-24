package fr.polytech.myrpg.characters.items.equipable;

import fr.polytech.myrpg.characters.items.Item;

/**
 * This class represents an equipable item.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public abstract class EquipableItem extends Item
{
	/**
	 * Create an equipable item.
	 * 
	 * @param name
	 *            The name of the equipable item.
	 * @param weight
	 *            The weight of the equipable item.
	 */
	public EquipableItem(String name, int weight)
	{
		super(name, weight);
	}

	/**
	 * Get the equipable item type.
	 * 
	 * @return The equipable item type.
	 */
	public abstract EquipableItemType getEquipableItemType();
}
