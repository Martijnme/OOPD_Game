package items;

import me.main.Game;
import me.main.Player;
import nl.han.ica.oopg.collision.ICollidableWithGameObjects;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;

import java.util.List;

public abstract class PickUp extends SpriteObject implements ICollidableWithGameObjects {
    private Game game;

    public PickUp(Game game, Sprite sprite){
        super(sprite);
        this.game=game;
    }
    /*
        is called when the object collides with the player
        this object is destroyed after
     */
    public abstract void pickUp(Player player);

    private void removeThis(){
        game.deleteGameObject(this);
    }

    @Override
    public void gameObjectCollisionOccurred(List<GameObject> list) {
        for(GameObject collidedObject: list ){
            if(collidedObject instanceof Player){
                pickUp((Player) collidedObject);
                removeThis();
            }
        }
    }
}
