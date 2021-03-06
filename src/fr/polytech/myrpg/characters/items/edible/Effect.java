package fr.polytech.myrpg.characters.items.edible;

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
	 *            The characteristic on which the effect will act.
	 * @param value
	 *            The value which will affect the characteristic.
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

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		final StringBuilder stringRepresentation = new StringBuilder();
		stringRepresentation.append("Effect [characteristic=");
		stringRepresentation.append(this.characteristic);
		stringRepresentation.append(", value=");
		stringRepresentation.append(this.value);
		stringRepresentation.append("]");

		return stringRepresentation.toString();
	}
}
