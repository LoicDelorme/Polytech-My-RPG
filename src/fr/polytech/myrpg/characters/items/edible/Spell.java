package fr.polytech.myrpg.characters.items.edible;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.polytech.myrpg.characters.Characteristic;

/**
 * This class represents a spell.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class Spell extends EdibleItem
{
	/**
	 * The list of available effects.
	 */
	private final List<Effect> effects;

	/**
	 * Create a spell.
	 * 
	 * @param name
	 *            The name of the spell.
	 * @param weight
	 *            The weight of the spell.
	 * @param effects
	 *            The effects.
	 */
	public Spell(String name, int weight, Effect... effects)
	{
		super(name, weight);
		this.effects = new ArrayList<Effect>(Arrays.asList(effects));
	}

	/**
	 * @see fr.polytech.myrpg.characters.items.Item#getValueByCharacteristic(fr.polytech.myrpg.characters.Characteristic)
	 */
	@Override
	public int getValueByCharacteristic(Characteristic characteristic)
	{
		for (Effect currentEffect : this.effects)
		{
			if (characteristic == currentEffect.getCharacteristic())
			{
				return currentEffect.getValue();
			}
		}

		return 0;
	}
}
