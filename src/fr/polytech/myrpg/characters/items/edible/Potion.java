package fr.polytech.myrpg.characters.items.edible;

import fr.polytech.myrpg.characters.Characteristic;

/**
 * This class represents a potion.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class Potion extends EdibleItem
{
	/**
	 * The additional health.
	 */
	private final int additionalHealth;

	/**
	 * Create a potion.
	 * 
	 * @param name
	 *            The name of the potion.
	 * @param weight
	 *            The weight of the potion.
	 * @param additionalHealth
	 *            The additional health.
	 */
	public Potion(String name, int weight, int additionalHealth)
	{
		super(name, weight);
		this.additionalHealth = additionalHealth;
	}

	/**
	 * @see fr.polytech.myrpg.characters.items.Item#getValueByCharacteristic(fr.polytech.myrpg.characters.Characteristic)
	 */
	@Override
	public int getValueByCharacteristic(Characteristic characteristic)
	{
		return (characteristic == Characteristic.HEALTH ? this.additionalHealth : 0);
	}
}
