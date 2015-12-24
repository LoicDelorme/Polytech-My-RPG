package fr.polytech.myrpg.characters.items;

import fr.polytech.myrpg.characters.Characteristic;

/**
 * This class represents an effect.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class Effect
{
	/**
	 * The characteristic on which the effect will act.
	 */
	private final Characteristic characteristic;

	/**
	 * The value which will affect the characteristic.
	 */
	private final int value;

	/**
	 * Create an effect.
	 * 
	 * @param characteristic
	 *            The characteristic on which the effect act.
	 * @param value
	 *            The value which affect the characteristic.
	 */
	public Effect(Characteristic characteristic, int value)
	{
		this.characteristic = characteristic;
		this.value = value;
	}

	/**
	 * Get the characteristic.
	 * 
	 * @return The characteristic.
	 */
	public Characteristic getCharacteristic()
	{
		return this.characteristic;
	}

	/**
	 * Get the value.
	 * 
	 * @return The value.
	 */
	public int getValue()
	{
		return this.value;
	}
}
