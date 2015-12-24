package fr.polytech.myrpg.characters.specialmove;

import java.security.SecureRandom;

/**
 * This class represents a special move implementation.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class SpecialMove implements ISpecialMove
{
	/**
	 * The secure random generator.
	 */
	private final SecureRandom secureRandomGenerator;

	/**
	 * The probability of success.
	 */
	private final double probabilityOfSuccess;

	/**
	 * The special move type.
	 */
	private final SpecialMoveType specialMoveType;

	/**
	 * Create a special move.
	 * 
	 * @param probabilityOfSuccess
	 *            The probability of success.
	 * @param specialMoveType
	 *            The special move type.
	 */
	public SpecialMove(double probabilityOfSuccess, SpecialMoveType specialMoveType)
	{
		this.secureRandomGenerator = new SecureRandom();
		this.probabilityOfSuccess = probabilityOfSuccess;
		this.specialMoveType = specialMoveType;
	}

	/**
	 * Get the probability of success.
	 * 
	 * @return The probability of success.
	 */
	public double getProbabilityOfSuccess()
	{
		return this.probabilityOfSuccess;
	}

	/**
	 * Get the special move type.
	 * 
	 * @return The special move type.
	 */
	public SpecialMoveType getSpecialMoveType()
	{
		return this.specialMoveType;
	}

	/**
	 * @see fr.polytech.myrpg.characters.specialmove.ISpecialMove#canSpecialMoveBeUsed()
	 */
	@Override
	public boolean canSpecialMoveBeUsed()
	{
		return (this.secureRandomGenerator.nextDouble() > this.probabilityOfSuccess);
	}
}
