package fr.polytech.myrpg.characters.items.edible;

import fr.polytech.myrpg.characters.items.Item;

/**
 * This class represents an edible item.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public abstract class EdibleItem extends Item
{
	/**
	 * Create an edible item.
	 * 
	 * @param name
	 *            The name of the edible item.
	 * @param weight
	 *            The weight of the edible item.
	 */
	public EdibleItem(String name, int weight)
	{
		super(name, weight);
	}
}
