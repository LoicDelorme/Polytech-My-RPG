package fr.polytech.myrpg.characters.specialmove;

import fr.polytech.myrpg.characters.items.edible.Effect;

/**
 * This interface represents a special move.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public interface ISpecialMove
{
	/**
	 * Get the probability of success.
	 * 
	 * @return The probability of success.
	 */
	public double getProbabilityOfSuccess();

	/**
	 * Get the special move type.
	 * 
	 * @return The special move type.
	 */
	public SpecialMoveType getSpecialMoveType();

	/**
	 * If the special move can be used.
	 * 
	 * @return True if it can be used, else False.
	 */
	public boolean canSpecialMoveBeUsed();

	/**
	 * Get the effect.
	 * 
	 * @return The effect.
	 */
	public Effect getEffect();
}
