package fr.polytech.myrpg.characters.items.edible;

import fr.polytech.myrpg.characters.Characteristic;

/**
 * This class represents a food.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class Food extends EdibleItem
{
	/**
	 * The additional dexterity.
	 */
	private final int additionalDexterity;

	/**
	 * Create a food.
	 * 
	 * @param name
	 *            The name of the food.
	 * @param weight
	 *            The weight of the food.
	 * @param additionalDexterity
	 *            The additional dexterity.
	 */
	public Food(String name, int weight, int additionalDexterity)
	{
		super(name, weight);
		this.additionalDexterity = additionalDexterity;
	}

	/**
	 * @see fr.polytech.myrpg.characters.items.Item#getValueByCharacteristic(fr.polytech.myrpg.characters.Characteristic)
	 */
	@Override
	public int getValueByCharacteristic(Characteristic characteristic)
	{
		return (characteristic == Characteristic.DEXTERITY ? this.additionalDexterity : 0);
	}
}
