package fr.polytech.myrpg.characters.items.equipable;

import fr.polytech.myrpg.characters.Characteristic;

/**
 * This class represents a weapon.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class Weapon extends EquipableItem
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
		super(name, weight);
		this.additionalStrength = additionalStrength;
	}

	/**
	 * @see fr.polytech.myrpg.characters.items.Item#getValueByCharacteristic(fr.polytech.myrpg.characters.Characteristic)
	 */
	@Override
	public int getValueByCharacteristic(Characteristic characteristic)
	{
		return (characteristic == Characteristic.STRENGTH ? this.additionalStrength : 0);
	}

	/**
	 * @see fr.polytech.myrpg.characters.items.equipable.EquipableItem#getEquipableItemType()
	 */
	@Override
	public EquipableItemType getEquipableItemType()
	{
		return EquipableItemType.WEAPON;
	}
}
