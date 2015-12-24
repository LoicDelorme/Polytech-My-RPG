package fr.polytech.myrpg.characters.items.equipable;

import fr.polytech.myrpg.characters.Characteristic;

/**
 * This class represents an armor.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class Armor extends EquipableItem
{
	/**
	 * The additional defense.
	 */
	private final int additionalDefense;

	/**
	 * Create an armor.
	 * 
	 * @param name
	 *            The name of the armor.
	 * @param weight
	 *            The weight of the armor.
	 * @param additionalDefense
	 *            The additional defense.
	 */
	public Armor(String name, int weight, int additionalDefense)
	{
		super(name, weight);
		this.additionalDefense = additionalDefense;
	}

	/**
	 * @see fr.polytech.myrpg.characters.items.Item#getValueByCharacteristic(fr.polytech.myrpg.characters.Characteristic)
	 */
	@Override
	public int getValueByCharacteristic(Characteristic characteristic)
	{
		if (characteristic == Characteristic.DEFENSE)
		{
			return this.additionalDefense;
		}

		return 0;
	}

	/**
	 * @see fr.polytech.myrpg.characters.items.equipable.EquipableItem#getEquipableItemType()
	 */
	@Override
	public EquipableItemType getEquipableItemType()
	{
		return EquipableItemType.ARMOR;
	}
}
