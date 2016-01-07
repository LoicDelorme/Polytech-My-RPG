package fr.polytech.myrpg.game;

import java.util.ArrayList;
import java.util.List;

import fr.polytech.myrpg.characters.Archer;
import fr.polytech.myrpg.characters.Athlete;
import fr.polytech.myrpg.characters.Characteristic;
import fr.polytech.myrpg.characters.Magician;
import fr.polytech.myrpg.characters.Warrior;
import fr.polytech.myrpg.characters.exceptions.InvalidConstraintsException;
import fr.polytech.myrpg.characters.exceptions.TooHighCharacteristicsValueException;
import fr.polytech.myrpg.characters.exceptions.TooFewSpecialMovesException;
import fr.polytech.myrpg.characters.items.Item;
import fr.polytech.myrpg.characters.items.edible.Effect;
import fr.polytech.myrpg.characters.items.edible.Food;
import fr.polytech.myrpg.characters.items.edible.Potion;
import fr.polytech.myrpg.characters.items.edible.Spell;
import fr.polytech.myrpg.characters.items.equipable.Armor;
import fr.polytech.myrpg.characters.items.equipable.Weapon;
import fr.polytech.myrpg.game.players.Player;
import fr.polytech.myrpg.game.players.Team;
import me.grea.antoine.utils.Log;

/**
 * This class represents a basic game builder.
 *
 * @author DELORME Loïc
 * @since 1.0.0
 */
public class GameBuilder implements IGameBuilder
{
	/**
	 * The first team.
	 */
	private final Team firstTeam;

	/**
	 * The second team.
	 */
	private final Team secondTeam;

	/**
	 * The items in the world.
	 */
	private final List<Item> items;

	/**
	 * Create a game builder.
	 */
	public GameBuilder()
	{
		this.firstTeam = new Team("God Team");
		this.secondTeam = new Team("Devil Team");
		this.items = new ArrayList<Item>();
	}

	/**
	 * @see fr.polytech.myrpg.game.IGameBuilder#buildGame()
	 */
	@Override
	public IGame buildGame()
	{
		try
		{
			Log.d("------------------------------------------------------------------");
			Log.d("RESOURCES INITIALIZATION");
			Log.d("------------------------------------------------------------------");
			Log.d("Creating all characters");
			Magician gandalf = new Magician("Gandalf");
			Archer legolas = new Archer("Legolas");
			Warrior aragorn = new Warrior("Aragorn");
			Warrior gimli = new Warrior("Gimli");
			Athlete gollum = new Athlete("Gollum");
			Warrior frodon = new Warrior("Frodon");

			Warrior sauron = new Warrior("Sauron");
			Warrior nazgul = new Warrior("Nazgul");
			Warrior gothmog = new Warrior("Gothmog");
			Warrior azog = new Warrior("Azog");
			Warrior orc = new Warrior("Orc");
			Magician balrog = new Magician("Balrog");
			Log.d("Characters were created successfully");

			Log.d("Creating the first team");
			this.firstTeam.addPlayer(new Player("LegolasPlayer", legolas));
			this.firstTeam.addPlayer(new Player("GandalfPlayer", gandalf));
			this.firstTeam.addPlayer(new Player("AragornPlayer", aragorn));
			this.firstTeam.addPlayer(new Player("GimliPlayer", gimli));
			this.firstTeam.addPlayer(new Player("GollumPlayer", gollum));
			this.firstTeam.addPlayer(new Player("FrodonPlayer", frodon));
			Log.d("The first team was created successfully");

			Log.d("Creating the second team");
			this.secondTeam.addPlayer(new Player("SauronPlayer", sauron));
			this.secondTeam.addPlayer(new Player("NazgulPlayer", nazgul));
			this.secondTeam.addPlayer(new Player("GothmogPlayer", gothmog));
			this.secondTeam.addPlayer(new Player("AzogPlayer", azog));
			this.secondTeam.addPlayer(new Player("OrcPlayer", orc));
			this.secondTeam.addPlayer(new Player("BalrogPlayer", balrog));
			Log.d("The second team was created successfully");

			Log.d("Creating all items in the world");
			Log.d("Creating all armors");
			this.items.add(new Armor("IronArmor", 20, 14));
			this.items.add(new Armor("GoldArmor", 22, 18));
			this.items.add(new Armor("PlatinumArmor", 25, 28));
			Log.d("Creating all weapons");
			this.items.add(new Weapon("IronSword", 8, 10));
			this.items.add(new Weapon("PlatinumSword", 14, 10));
			this.items.add(new Weapon("FlamingPlatinumSword", 18, 25));
			Log.d("Creating all foods");
			this.items.add(new Food("Bread", 1, 4));
			this.items.add(new Food("Raspeberry", 1, 3));
			this.items.add(new Food("Apple", 1, 5));
			Log.d("Creating all potions");
			this.items.add(new Potion("EarlyPotion", 1, 20));
			this.items.add(new Potion("BasicPotion", 1, 35));
			this.items.add(new Potion("AdvancedPotion", 2, 50));
			this.items.add(new Potion("MasterPotion", 3, 100));
			Log.d("Creating all spells");
			this.items.add(new Spell("DefenseBooster", 5, new Effect(Characteristic.DEFENSE, 150)));
			this.items.add(new Spell("StrengthBooster", 3, new Effect(Characteristic.STRENGTH, 45)));
			this.items.add(new Spell("InvicibleSpell", 4, new Effect(Characteristic.DEFENSE, 150), new Effect(Characteristic.STRENGTH, 45)));
			Log.d("Items in the world were created successfully");
			Log.d("------------------------------------------------------------------");
		}
		catch (TooHighCharacteristicsValueException | TooFewSpecialMovesException | InvalidConstraintsException e)
		{
			Log.e(e);
			return null;
		}

		return new Game(this.firstTeam, this.secondTeam, this.items);
	}
}
