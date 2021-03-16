package me.main;

import enemy.Zombie;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.tile.TileMap;
import nl.han.ica.oopg.tile.TileType;
import nl.han.ica.oopg.view.View;
import tiles.WallTile;

import java.awt.*;

public class Game extends GameEngine{
    private Player player;
    private ObjectSpawner objectSpawner;
    //private Zombie zombie;
    public static final int WIDTH = 945, HEIGHT = WIDTH / 12 * 9;
    public static final float centerX = ((WIDTH /35) * 17), centerY = ((HEIGHT /35) *15);
    public static final String MEDIA_URL = "src/media/";

    private Window frame = new Window();
    public static void main(String[] args) {
        Game game = new Game();
        game.runSketch();
    }

    @Override
    public void setupGame() {
        player = new Player(this);
    //    zombie = new Zombie(this);
        addGameObject(player,centerX,centerY);
    //    addGameObject(zombie,35, 635);

//        setView(new View(WIDTH,HEIGHT));
//        size(WIDTH,HEIGHT);
        initView(WIDTH,HEIGHT);
        initTileMap();
        objectSpawner = new ObjectSpawner(this, tileMap);
        objectSpawner.initCoins();
    }

    @Override
    public void update() {
    }

    public void initView(int width, int height){
        setView(new View(width,height));
        size(width,height);
    }

    public void initTileMap(){

        Sprite wallSprite = new Sprite(Game.MEDIA_URL.concat("wallTile.png"));
        TileType<WallTile> wallTileType = new TileType<>(WallTile.class, wallSprite);

        //Nummertjes die hij niet kent maakt de tilemap vanzelf EmptyTiles van.

        TileType[] tileTypes = {wallTileType/*, coinTileType*/};
        int tileSize = 35;
        int tilesMap[][] = {

                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,-1,-1,-1,-1,0,0,0,0,0,-1,0,-1,0,-1,-1,-1,-1,-1,0,-1,0,-1,-1,-1,0,0},
                {0,-1,0,0,-1,-1,-1,-1,-1,0,-1,0,-1,-1,-1,0,-1,0,-1,-1,-1,0,1,0,-1,0,0},
                {0,-1,1,-1,-1,0,-1,0,-1,0,-1,0,-1,0,-1,0,-1,0,0,0,-1,-1,-1,0,-1,0,0},
                {0,-1,0,-1,-1,-1,-1,0,-1,0,-1,-1,-1,0,-1,0,-1,-1,-1,0,0,-1,0,0,-1,0,0},
                {0,-1,0,-1,0,0,0,0,-1,-1,-1,0,-1,0,0,0,-1,0,-1,0,-1,-1,-1,0,-1,-1,0},
                {0,-1,-1,-1,0,0,-1,-1,-1,0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,0,-1,0,0,-1,0},
                {0,-1,0,-1,-1,-1,-1,0,0,0,-1,0,0,-1,0,0,-1,0,-1,0,-1,-1,-1,-1,0,-1,0},
                {0,-1,0,0,0,0,-1,0,-1,-1,-1,0,-1,-1,-1,0,-1,0,-1,0,-1,0,-1,1,0,-1,0},
                {0,-1,0,0,0,0,-1,-1,-1,0,-1,0,-1,-1,-1,0,-1,-1,-1,0,-1,0,0,-1,-1,-1,0},
                {0,-1,-1,-1,-1,0,0,0,-1,-1,-1,0,0,0,0,0,0,0,-1,0,-1,-1,-1,0,0,-1,0},
                {0,-1,0,0,-1,0,-1,-1,-1,0,-1,-1,0,-1,-1,0,-1,0,-1,-1,-1,0,-1,0,-1,-1,0},
                {0,0,0,0,-1,-1,-1,0,-1,0,0,-1,0,0,-1,0,-1,-1,-1,0,-1,-1,-1,-1,-1,0,0},
                {0,-1,-1,0,-1,0,-1,-1,-1,0,0,-1,-1,0,-1,0,0,-1,0,0,0,0,-1,0,-1,-1,0},
                {0,-1,-1,-1,-1,0,-1,0,-1,0,0,0,-1,0,-1,-1,1,-1,-1,0,-1,0,-1,0,0,-1,0},
                {0,-1,0,0,-1,-1,-1,0,-1,-1,-1,-1,-1,-1,-1,0,-1,0,-1,0,-1,-1,-1,-1,0,-1,0},
                {0,-1,-1,-1,-1,0,-1,-1,-1,0,-1,0,-1,0,-1,0,-1,0,-1,0,-1,0,0,-1,-1,-1,0},
                {0,1,0,-1,0,0,-1,0,-1,-1,-1,0,-1,0,-1,-1,-1,-1,-1,0,-1,0,-1,-1,0,-1,0},
                {0,-1,-1,-1,-1,-1,-1,0,-1,0,-1,0,-1,-1,-1,0,-1,0,-1,-1,-1,-1,-1,0,0,-1,0},
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
        };
        tileMap = new TileMap(tileSize, tileTypes, tilesMap);
    }

    public static float clamp(float var, float min, float max){
        if (var >= max)
            return var = max;
        else if (var <= min)
            return var = min;
        else return var;
    }

}
