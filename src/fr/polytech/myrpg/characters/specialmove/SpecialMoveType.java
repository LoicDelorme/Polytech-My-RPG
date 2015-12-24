package fr.polytech.myrpg.characters.specialmove;

/**
 * This enumeration represents a special move type.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public enum SpecialMoveType
{
    /**
     * The character try to parry the attack.
     */
	PARRY,

	/**
	 * The character try to heal himself.
	 */
	HEAL,

	/**
	 * The character try to avoid the attack.
	 */
	AVOID_ATTACK,

	/**
	 * The character try to run away.
	 */
	RUN_AWAY;
}
