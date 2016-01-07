package fr.polytech.myrpg.characters;

import fr.polytech.myrpg.characters.exceptions.InvalidConstraintsException;
import fr.polytech.myrpg.characters.exceptions.TooHighCharacteristicsValueException;
import fr.polytech.myrpg.characters.exceptions.TooFewSpecialMovesException;
import fr.polytech.myrpg.characters.items.edible.Effect;
import fr.polytech.myrpg.characters.specialmove.SpecialMove;
import fr.polytech.myrpg.characters.specialmove.SpecialMoveType;

/**
 * This class represents an archer.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class Archer extends Character
{
	/**
	 * Create an archer.
	 * 
	 * @param name
	 *            The name of the archer.
	 * @throws TooHighCharacteristicsValueException
	 *             If the characteristics value is higher than expected : {@value #MAXIMAL_CHARACTERISTICS_VALUE}.
	 * @throws TooFewSpecialMovesException
	 *             If there is less than {@value #NB_SPECIAL_MOVES_REQUIRED} special moves.
	 * @throws InvalidConstraintsException
	 *             If constraints are invalid.
	 */
	public Archer(String name) throws TooHighCharacteristicsValueException, TooFewSpecialMovesException, InvalidConstraintsException
	{
		super(name);
	}

	/**
	 * @see fr.polytech.myrpg.characters.Character#initializeCharacteristics()
	 */
	@Override
	public void initializeCharacteristics()
	{
		this.characteristics.put(Characteristic.STRENGTH, 45);
		this.characteristics.put(Characteristic.DEXTERITY, 20);
		this.characteristics.put(Characteristic.DEFENSE, 5);
	}

	/**
	 * @see fr.polytech.myrpg.characters.Character#initializeSpecialMoves()
	 */
	@Override
	public void initializeSpecialMoves()
	{
		this.specialMoves.add(new SpecialMove(0.1, SpecialMoveType.HEAL, new Effect(Characteristic.HEALTH, 25)));
		this.specialMoves.add(new SpecialMove(0.05, SpecialMoveType.PARRY, null));
	}

	/**
	 * @see fr.polytech.myrpg.characters.Character#checkConstraints()
	 */
	@Override
	public void checkConstraints() throws InvalidConstraintsException
	{
		final int strengthValue = this.characteristics.get(Characteristic.STRENGTH);
		final int dexterityValue = this.characteristics.get(Characteristic.DEXTERITY);
		final int defenseValue = this.characteristics.get(Characteristic.DEFENSE);

		if (!((strengthValue >= (dexterityValue + 10)) && ((dexterityValue + 10) >= defenseValue)))
		{
			throw new InvalidConstraintsException("");
		}
	}
}
