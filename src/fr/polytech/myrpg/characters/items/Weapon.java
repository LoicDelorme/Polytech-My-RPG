package fr.polytech.myrpg.characters.items;

import fr.polytech.myrpg.characters.Characteristic;

/**
 * This class represents a weapon.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class Weapon extends Item
{
	/**
	 * The additional strength.
	 */
	private final int additionalStrength;

	/**
	 * Create a weapon.
	 * 
	 * @param name
	 *            The name of the weapon.
	 * @param weight
	 *            The weight of the weapon.
	 * @param additionalStrength
	 *            The additional strength.
	 */
	public Weapon(String name, int weight, int additionalStrength)
	{
		super(name, weight, false, true);
		this.additionalStrength = additionalStrength;
	}

	/**
	 * @see fr.polytech.myrpg.characters.items.Item#getValueByCharacteristic(fr.polytech.myrpg.characters.Characteristic)
	 */
	@Override
	public int getValueByCharacteristic(Characteristic characteristic)
	{
		if (characteristic == Characteristic.STRENGTH)
		{
			return this.additionalStrength;
		}

		return 0;
	}
}
