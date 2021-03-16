package me.main;

import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.tile.TileMap;
import nl.han.ica.oopg.tile.TileType;
import nl.han.ica.oopg.view.View;
import processing.core.PVector;
import tiles.CoinTile;
import tiles.WallTile;

public class Game extends GameEngine{
    private Player player;
    private ObjectSpawner objectSpawner;
    public static final int WIDTH = 945, HEIGHT = WIDTH / 12 * 9;
    public static final String MEDIA_URL = "src/media/";

    public static void main(String[] args) {
        Game game = new Game();
        game.runSketch();
    }

    @Override
    public void setupGame() {
        player = new Player(this);
        addGameObject(player,((WIDTH /35) * 17),((HEIGHT /35) *15));

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
    private void initView(int width, int height){
        setView(new View(width,height));
        size(width, height);
    }

    private void initTileMap(){

        Sprite wallSprite = new Sprite(Game.MEDIA_URL.concat("wallTile.png"));
        //Sprite coinSprite = new Sprite(Game.MEDIA_URL.concat("coin.gif"));
        TileType<WallTile> wallTileType = new TileType<>(WallTile.class, wallSprite);
        //TileType<CoinTile> coinTileType = new TileType<>(CoinTile.class,coinSprite);
        /*
        Nummertjes die hij niet kent maakt de tilemap vanzelf EmptyTiles van.
         */
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
