package fr.polytech.myrpg.characters;

import fr.polytech.myrpg.characters.exceptions.InvalidConstraintsException;
import fr.polytech.myrpg.characters.exceptions.TooHighCharacteristicsValueException;
import fr.polytech.myrpg.characters.exceptions.TooFewSpecialMovesException;
import fr.polytech.myrpg.characters.items.edible.Effect;
import fr.polytech.myrpg.characters.specialmove.SpecialMove;
import fr.polytech.myrpg.characters.specialmove.SpecialMoveType;

/**
 * This class represents a magician.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class Magician extends Character
{
	/**
	 * Create a magician.
	 * 
	 * @param name
	 *            The name of the magician.
	 * @throws TooHighCharacteristicsValueException
	 *             If the characteristics value is higher than expected : {@value #MAXIMAL_CHARACTERISTICS_VALUE}.
	 * @throws TooFewSpecialMovesException
	 *             If there is less than {@value #NB_SPECIAL_MOVES_REQUIRED} special moves.
	 * @throws InvalidConstraintsException
	 *             If constraints are invalid.
	 */
	public Magician(String name) throws TooHighCharacteristicsValueException, TooFewSpecialMovesException, InvalidConstraintsException
	{
		super(name);
	}

	/**
	 * @see fr.polytech.myrpg.characters.Character#initializeCharacteristics()
	 */
	@Override
	public void initializeCharacteristics()
	{
		this.characteristics.put(Characteristic.DEXTERITY, 35);
		this.characteristics.put(Characteristic.STRENGTH, 20);
		this.characteristics.put(Characteristic.DEFENSE, 15);
	}

	/**
	 * @see fr.polytech.myrpg.characters.Character#initializeSpecialMoves()
	 */
	@Override
	public void initializeSpecialMoves()
	{
		this.specialMoves.add(new SpecialMove(0.1, SpecialMoveType.HEAL, new Effect(Characteristic.HEALTH, 35)));
		this.specialMoves.add(new SpecialMove(0.04, SpecialMoveType.PARRY, null));
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

		if (!((dexterityValue >= (strengthValue + 10)) && ((strengthValue + 10) >= defenseValue)))
		{
			throw new InvalidConstraintsException("");
		}
	}
}
