package fr.polytech.myrpg.characters.specialmove;

import java.security.SecureRandom;

import fr.polytech.myrpg.characters.items.edible.Effect;

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
	 * The effect.
	 */
	private final Effect effect;

	/**
	 * Create a special move.
	 * 
	 * @param probabilityOfSuccess
	 *            The probability of success.
	 * @param specialMoveType
	 *            The special move type.
	 * @param effect
	 *            The effect.
	 */
	public SpecialMove(double probabilityOfSuccess, SpecialMoveType specialMoveType, Effect effect)
	{
		this.secureRandomGenerator = new SecureRandom();
		this.probabilityOfSuccess = probabilityOfSuccess;
		this.specialMoveType = specialMoveType;
		this.effect = effect;
	}

	/**
	 * @see fr.polytech.myrpg.characters.specialmove.ISpecialMove#getProbabilityOfSuccess()
	 */
	@Override
	public double getProbabilityOfSuccess()
	{
		return this.probabilityOfSuccess;
	}

	/**
	 * @see fr.polytech.myrpg.characters.specialmove.ISpecialMove#getSpecialMoveType()
	 */
	@Override
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
		return (this.secureRandomGenerator.nextDouble() < this.probabilityOfSuccess);
	}

	/**
	 * @see fr.polytech.myrpg.characters.specialmove.ISpecialMove#getEffect()
	 */
	@Override
	public Effect getEffect()
	{
		return this.effect;
	}
}
