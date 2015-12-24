package fr.polytech.myrpg.characters;

import fr.polytech.myrpg.characters.exceptions.InvalidConstraintsException;
import fr.polytech.myrpg.characters.exceptions.TooHighCharacteristicsValue;
import fr.polytech.myrpg.characters.exceptions.TooLessSpecialMoves;
import fr.polytech.myrpg.characters.items.edible.Effect;
import fr.polytech.myrpg.characters.specialmove.SpecialMove;
import fr.polytech.myrpg.characters.specialmove.SpecialMoveType;

/**
 * This class represents a warrior.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class Warrior extends Character
{
	/**
	 * Create a warrior.
	 * 
	 * @param name
	 *            The name of the warrior.
	 * @throws TooHighCharacteristicsValue
	 *             If the characteristics value is higher than expected : {@value #MAXIMAL_CHARACTERISTICS_VALUE}.
	 * @throws TooLessSpecialMoves
	 *             If there is less than {@value #SPECIAL_MOVES_REQUIRED} special moves.
	 * @throws InvalidConstraintsException
	 *             If constraints are invalid.
	 */
	public Warrior(String name) throws TooHighCharacteristicsValue, TooLessSpecialMoves, InvalidConstraintsException
	{
		super(name);
	}

	/**
	 * @see fr.polytech.myrpg.characters.Character#initializeCharacteristics()
	 */
	@Override
	public void initializeCharacteristics()
	{
		this.characteristics.put(Characteristic.STRENGTH, 65);
		this.characteristics.put(Characteristic.DEXTERITY, 25);
		this.characteristics.put(Characteristic.DEFENSE, 10);
	}

	/**
	 * @see fr.polytech.myrpg.characters.Character#initializeSpecialMoves()
	 */
	@Override
	public void initializeSpecialMoves()
	{
		this.specialMoves.add(new SpecialMove(0.1, SpecialMoveType.PARRY, new Effect(Characteristic.DEFENSE, Integer.MAX_VALUE)));
		this.specialMoves.add(new SpecialMove(0.05, SpecialMoveType.HEAL, new Effect(Characteristic.HEALTH, 150)));
		this.specialMoves.add(new SpecialMove(0.005, SpecialMoveType.HEAL, new Effect(Characteristic.HEALTH, 350)));
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
