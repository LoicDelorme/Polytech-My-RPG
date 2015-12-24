package fr.polytech.myrpg.characters.specialmove;

/**
 * This interface represents a special move.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public interface ISpecialMove
{
	/**
	 * If the special move can be used.
	 * 
	 * @return True if it can be used, else False.
	 */
	public boolean canSpecialMoveBeUsed();
}
