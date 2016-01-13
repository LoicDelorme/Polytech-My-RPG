package fr.polytech.myrpg.game;

import java.util.ArrayList;
import java.util.List;

import fr.polytech.myrpg.characters.Archer;
import fr.polytech.myrpg.characters.Athlete;
import fr.polytech.myrpg.characters.Characteristic;
import fr.polytech.myrpg.characters.Magician;
import fr.polytech.myrpg.characters.Warrior;
import fr.polytech.myrpg.characters.exceptions.InvalidConstraintsException;
import fr.polytech.myrpg.characters.exceptions.TooFewSpecialMovesException;
import fr.polytech.myrpg.characters.exceptions.TooHighCharacteristicsValueException;
import fr.polytech.myrpg.characters.items.edible.EdibleItem;
import fr.polytech.myrpg.characters.items.edible.Effect;
import fr.polytech.myrpg.characters.items.edible.Food;
import fr.polytech.myrpg.characters.items.edible.Potion;
import fr.polytech.myrpg.characters.items.edible.Spell;
import fr.polytech.myrpg.characters.items.equipable.Armor;
import fr.polytech.myrpg.characters.items.equipable.EquipableItem;
import fr.polytech.myrpg.characters.items.equipable.Weapon;
import fr.polytech.myrpg.game.observers.ConsoleGameObserver;
import fr.polytech.myrpg.game.players.Player;
import fr.polytech.myrpg.game.players.Team;
import fr.polytech.myrpg.game.quests.BasicQuest;
import fr.polytech.myrpg.game.quests.IQuest;
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

			Log.d("Creating first quest");
			final List<EdibleItem> firstEdibleItems = new ArrayList<EdibleItem>();
			firstEdibleItems.add(new Food("Bread", 1, 4));
			firstEdibleItems.add(new Food("Raspeberry", 1, 3));
			firstEdibleItems.add(new Food("Apple", 1, 5));
			firstEdibleItems.add(new Potion("EarlyPotion", 1, 20));
			firstEdibleItems.add(new Spell("StrengthBooster", 3, new Effect(Characteristic.STRENGTH, 45)));
			firstEdibleItems.add(new Spell("InvicibleSpell", 4, new Effect(Characteristic.DEFENSE, 150), new Effect(Characteristic.STRENGTH, 45)));

			final List<EquipableItem> firstEquipableItems = new ArrayList<EquipableItem>();
			firstEquipableItems.add(new Armor("IronArmor", 20, 14));
			firstEquipableItems.add(new Armor("GoldArmor", 22, 18));
			firstEquipableItems.add(new Weapon("IronSword", 8, 10));
			firstEquipableItems.add(new Weapon("PlatinumSword", 14, 10));

			final Team firstFirstTeam = new Team("First Team");
			firstFirstTeam.addPlayer(new Player("GollumPlayer", new Athlete("Gollum")));
			firstFirstTeam.addPlayer(new Player("FrodonPlayer", new Warrior("Frodon")));

			final Team firstSecondTeam = new Team("Second Team");
			firstSecondTeam.addPlayer(new Player("BalrogPlayer", new Magician("Balrog")));

			final IQuest firstQuest = new BasicQuest("Gollum s'est retrouvé en possession de l'Anneau : celui-ci lui donne le pouvoir de vivre éternellement.\nCependant, Sauron, en quête de la vie éternelle, apprend que Gollum possède l'Anneau. Il envoie donc son fidèle serviteur Balrog combattre Gollum et tenter de lui voler l'Anneau Gollum s'allie avec Frodon afin d\'affronter Balrog et garder son précieux.", "Félicitations à l'équipe pour cette victoire écrasante ! L'Anneau est sauvé pour l'instant.", firstFirstTeam, firstSecondTeam, firstEdibleItems, firstEquipableItems);
			Log.d("First quest was created successfully");

			Log.d("Creating second quest");
			final List<EdibleItem> secondEdibleItems = new ArrayList<EdibleItem>();
			secondEdibleItems.add(new Food("Bread", 1, 4));
			secondEdibleItems.add(new Food("Raspeberry", 1, 3));
			secondEdibleItems.add(new Potion("EarlyPotion", 1, 20));
			secondEdibleItems.add(new Potion("BasicPotion", 1, 35));
			secondEdibleItems.add(new Potion("AdvancedPotion", 2, 50));
			secondEdibleItems.add(new Potion("MasterPotion", 3, 100));
			secondEdibleItems.add(new Spell("DefenseBooster", 5, new Effect(Characteristic.DEFENSE, 150)));
			secondEdibleItems.add(new Spell("StrengthBooster", 3, new Effect(Characteristic.STRENGTH, 45)));

			final List<EquipableItem> secondEquipableItems = new ArrayList<EquipableItem>();
			secondEquipableItems.add(new Armor("IronArmor", 20, 14));
			secondEquipableItems.add(new Armor("GoldArmor", 22, 18));
			secondEquipableItems.add(new Armor("PlatinumArmor", 25, 28));
			secondEquipableItems.add(new Weapon("IronSword", 8, 10));
			secondEquipableItems.add(new Weapon("PlatinumSword", 14, 10));

			final Team secondFirstTeam = new Team("First Team");
			secondFirstTeam.addPlayer(new Player("GollumPlayer", new Athlete("Gollum")));
			secondFirstTeam.addPlayer(new Player("FrodonPlayer", new Warrior("Frodon")));
			secondFirstTeam.addPlayer(new Player("LegolasPlayer", new Archer("Legolas")));
			secondFirstTeam.addPlayer(new Player("AragornPlayer", new Warrior("Aragorn")));
			secondFirstTeam.addPlayer(new Player("GimliPlayer", new Warrior("Gimli")));

			final Team secondSecondTeam = new Team("Second Team");
			secondSecondTeam.addPlayer(new Player("GothmogPlayer", new Warrior("Gothmog")));
			secondSecondTeam.addPlayer(new Player("AzogPlayer", new Warrior("Azog")));
			secondSecondTeam.addPlayer(new Player("OrcPlayer", new Warrior("Orc")));

			final IQuest secondQuest = new BasicQuest("Sauron, enragé par la mort de Balrog, ordonne à ses troupes de venir à bout de Gollum et Frodon. Gollum, en apprenant la nouvelle, vient demander de l'aide à ses amis Legolas, Aragorn et Gimli. Heureux de se retrouver, ils promettent à Gollum qu'ils ne laisseront pas Sauron s'emparer de l'Anneau.", "Après ce combat épique, nos héros sont épuisés... Mais c'est dans cette victoire qu'ils se retrouvent unis pour la première fois depuis des années. Ils décident de fêter celle-ci avec un grand repas.", secondFirstTeam, secondSecondTeam, secondEdibleItems, secondEquipableItems);
			Log.d("Second quest was created successfully");

			Log.d("Creating third quest");
			final List<EdibleItem> thirdEdibleItems = new ArrayList<EdibleItem>();
			thirdEdibleItems.add(new Food("Bread", 1, 4));
			thirdEdibleItems.add(new Food("Raspeberry", 1, 3));
			thirdEdibleItems.add(new Potion("EarlyPotion", 1, 20));
			thirdEdibleItems.add(new Potion("BasicPotion", 1, 35));
			thirdEdibleItems.add(new Potion("AdvancedPotion", 2, 50));
			thirdEdibleItems.add(new Potion("MasterPotion", 3, 100));
			thirdEdibleItems.add(new Spell("DefenseBooster", 5, new Effect(Characteristic.DEFENSE, 150)));
			thirdEdibleItems.add(new Spell("StrengthBooster", 3, new Effect(Characteristic.STRENGTH, 45)));

			final List<EquipableItem> thirdEquipableItems = new ArrayList<EquipableItem>();
			thirdEquipableItems.add(new Armor("IronArmor", 20, 14));
			thirdEquipableItems.add(new Armor("GoldArmor", 22, 18));
			thirdEquipableItems.add(new Armor("PlatinumArmor", 25, 28));
			thirdEquipableItems.add(new Weapon("IronSword", 8, 10));
			thirdEquipableItems.add(new Weapon("PlatinumSword", 14, 10));

			final Team thirdFirstTeam = new Team("First Team");
			thirdFirstTeam.addPlayer(new Player("GollumPlayer", new Athlete("Gollum")));
			thirdFirstTeam.addPlayer(new Player("FrodonPlayer", new Warrior("Frodon")));
			thirdFirstTeam.addPlayer(new Player("LegolasPlayer", new Archer("Legolas")));
			thirdFirstTeam.addPlayer(new Player("AragornPlayer", new Warrior("Aragorn")));
			thirdFirstTeam.addPlayer(new Player("GimliPlayer", new Warrior("Gimli")));
			thirdFirstTeam.addPlayer(new Player("GandalfPlayer", new Magician("Gandalf")));

			final Team thirdSecondTeam = new Team("Second Team");
			thirdSecondTeam.addPlayer(new Player("SauronPlayer", new Warrior("Sauron")));
			thirdSecondTeam.addPlayer(new Player("OrcPlayer1", new Warrior("Orc")));
			thirdSecondTeam.addPlayer(new Player("OrcPlayer2", new Warrior("Orc")));
			thirdSecondTeam.addPlayer(new Player("OrcPlayer3", new Warrior("Orc")));
			thirdSecondTeam.addPlayer(new Player("OrcPlayer4", new Warrior("Orc")));

			final IQuest thirdQuest = new BasicQuest("Malgré tous les serviteurs que Sauron a envoyé pour tuer Gollum et ses frères d'armes, il n'arrive décidément pas à s'emparer de l'Anneau. Dans un dernier effort, il réunit ses orcs et part les affronter lui-même. Gandalf, en apprenant la nouvelle, propose son aide et réunit l\'équipe.", "Après un combat épuisant, Sauron a été finalement vaincu - et l'oeil de Sauron détruit par la même occasion. ~~The End~~", thirdFirstTeam, thirdSecondTeam, thirdEdibleItems, thirdEquipableItems);
			Log.d("Third quest was created successfully");

			List<IQuest> quests = new ArrayList<IQuest>();
			quests.add(firstQuest);
			quests.add(secondQuest);
			quests.add(thirdQuest);

			return new Game(quests, new ConsoleGameObserver());
		}
		catch (TooHighCharacteristicsValueException | TooFewSpecialMovesException | InvalidConstraintsException e)
		{
			Log.e(e);
			return null;
		}
	}
}
