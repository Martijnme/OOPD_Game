package me.main;

/**
 * Central class and the program entry point.
 * Creates instances of Maze, Difficulty and ObjectSpawner to pass to other objects.
 * Also handles a list of BoosterEffects
 */

import HUD.HUD;
import enemy.Enemy;
import items.BoosterEffect;
import items.BoosterId;
import items.Coin;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.view.View;

import java.util.ArrayList;

public class Game extends GameEngine {
    private ObjectSpawner objectSpawner;
    private ArrayList<BoosterEffect> activeBoosterEffects;

    public static final int WIDTH = 945, HEIGHT = WIDTH / 12 * 9;
    public static final String MEDIA_URL = "src/media/";

    private Difficulty difficulty;
    private Player player;
    private HUD hud;

    /**
     * the start of the program.
     * @param args default string array that is given to any main method
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.runSketch();
    }

    /**
     * Creates instances of the central objects that run the game:
     * Maze, ObjectSpawner, Difficulty
     * Also finds and references the player object
     */
    @Override
    public void setupGame() {
        final int dashBoardHeight = 35;
        //creates Dashboard
        hud = new HUD(WIDTH, dashBoardHeight,this);
        addDashboard(hud);

        //sets window size
        setWindowView(WIDTH, HEIGHT);

        //sets core game Objects
        tileMap = new Maze();
        objectSpawner = new ObjectSpawner(this, tileMap,hud);
        difficulty = new Difficulty(objectSpawner, StartingDifficulty.EASY.getDifficultyLevel());
        activeBoosterEffects = new ArrayList<>();

        //spawns initial coins and the player
        objectSpawner.spawnStartingObjects(difficulty);
        player = findPlayer();
    }

    /**
     * Mandated by GameEngine, but not used.
     */
    @Override
    public void update() {}

    /**
     * Sets the size of the programs window
     * @param width Total width of the window
     * @param height Total Height og the window
     */
    public void setWindowView(int width, int height) {
        setView(new View(width, height));
        size(width, height);
    }

    /**
     * Casts the Vector of all GameObjects into an ArrayList.
     * This is needed because the Vector used in the GameEngine for this
     * does not play nice with Alarm.
     *
     * @return an arraylist of all GameObject in the game.
     */
    public ArrayList<GameObject> getAllGameObjects() {
        return new ArrayList<>(getGameObjectItems());
    }

//BoosterEffects code:

    /**
     * Adds a booster effect to the list of active booster effects and starts the effect if
     * there wasn't one of that type in the list yet.
     *
     * @param boosterEffect The BoosterEffect to add to the list.
     */
    public void addBoosterEffect(BoosterEffect boosterEffect) {
        if (!isBooster(boosterEffect.booster.getTypeId())) {
            boosterEffect.booster.startEffect();
        }
        activeBoosterEffects.add(boosterEffect);
    }

    /**
     * Checks if the booster type is already in the list of active booster effects.
     *
     * @return true if the booster is already in effect, false if not
     */
    private boolean isBooster(BoosterId typeId) {
        for (BoosterEffect effect : activeBoosterEffects) {
            if (effect.booster.getTypeId().equals(typeId)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Removes a boostereffect from the list of active booster effects,
     * then, if that was the last of that booster's type in the list, ends the effect.
     *
     * @param boosterEffect The boosterEffect to remove.
     */
    public void removeBoosterEffect(BoosterEffect boosterEffect) {
        activeBoosterEffects.remove(boosterEffect);
        if (!isBooster(boosterEffect.booster.getTypeId())) {
            boosterEffect.booster.endEffect();
        }
    }

    /**
     * Finds the player object from the list of all game objects.
     * Used once to set the player variable.
     *
     * @return The player object.
     */
    public Player findPlayer() {
        for (GameObject o : getAllGameObjects()) {
            if (o instanceof Player) {
                return (Player) o;
            }
        }
        System.out.println("Fout: Player object niet gevonden. Game.findPlayer()");
        return null;
    }

    /**
     * Gets player object
     * @return player object
     */
    public Player getPlayer() {
        return player;
    }

    ;

    /**
     * Filters through the list of all game objects to find the enemies.
     * Used to slow all enemies in the FlashBomb class
     *
     * @return An arraylist of all the enemies in the game.
     */
    public ArrayList<Enemy> getAllEnemies() {
        ArrayList<Enemy> allEnemies = new ArrayList<>();
        for (GameObject o : getAllGameObjects()) {
            if (o instanceof Enemy) {
                allEnemies.add((Enemy) o);
            }
        }
        return allEnemies;
    }

    /**
     * Counts the number of coins in the list of game objects.
     *
     * @return The number of coins in the game.
     */
    public int countCoins() {
        int coinCount = 0;
        for (GameObject o : getAllGameObjects()) {
            if (o instanceof Coin) {
                coinCount++;
            }
        }
        return coinCount;
    }
}
