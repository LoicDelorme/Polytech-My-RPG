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
	 * Create an item.
	 * 
	 * @param name
	 *            The name of the item.
	 * @param weight
	 *            The weight of the item.
	 */
	public Item(String name, int weight)
	{
		this.name = name;
		this.weight = weight;
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
	 * Get the value for a specific characteristic.
	 * 
	 * @param characteristic
	 *            The specific characteristic.
	 * @return The corresponding value.
	 */
	public abstract int getValueByCharacteristic(Characteristic characteristic);

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		final StringBuilder stringRepresentation = new StringBuilder();
		stringRepresentation.append("Item [name=");
		stringRepresentation.append(this.name);
		stringRepresentation.append(", weight=");
		stringRepresentation.append(this.weight);
		stringRepresentation.append("]");

		return stringRepresentation.toString();
	}
}
