package JumpAndRunner;

import JumpAndRunner.tiles.GroundTestTile;
import JumpAndRunner.tiles.SemiTestTile;
import JumpAndRunner.tiles.WallTestTile;
import nl.han.ica.oopg.collision.CollidedTile;
import nl.han.ica.oopg.collision.CollisionSide;
import nl.han.ica.oopg.collision.ICollidableWithTiles;
import nl.han.ica.oopg.exceptions.TileNotFoundException;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.objects.SpriteObject;
import nl.han.ica.oopg.tile.Tile;
import processing.core.PVector;

import java.util.List;

import static java.awt.event.KeyEvent.*;

public class Player extends SpriteObject implements ICollidableWithTiles{

    private Main main;
    final float X_SPEED = 2.0f,
                Y_SPEED = -4.0f,
                GRAVITY = 0.05f,
                CLIMBING_GRAVITY = 0.015f,
                FRICTION = 0.015f;
    private boolean climbLeft = false,
                    climbRight = false;

    /**
     * Create a new SpriteObject with a Sprite object.
     *
     * @param main The world in which the directory for the sprite is stored
     */
    public Player(Main main) {
        super(new Sprite(Main.MEDIA_URL.concat("player.png")));
        this.main = main;
        setGravity(GRAVITY);
        setFriction(FRICTION);
    }

    @Override
    public void update() {
        if(climbLeft || climbRight){
            setGravity(CLIMBING_GRAVITY);
        }else{
            setGravity(GRAVITY);
        }
    }

    @Override
    public void keyPressed(int keyCode, char key) {
        super.keyPressed(keyCode, key);
        if(keyCode == VK_A){
            setxSpeed(-X_SPEED);
            climbRight = false;
        }
        if(keyCode == VK_D){
            setxSpeed(X_SPEED);
            climbLeft = false;
        }
        if(keyCode == VK_SPACE){
            setySpeed(Y_SPEED);
            if(climbRight){
                setxSpeed(getxSpeed() - (0.75f * X_SPEED));
                climbRight = false;
            }
            if(climbLeft){
                setxSpeed(getxSpeed() + (0.75f * X_SPEED));
                climbLeft = false;
            }
        }
        if(keyCode == VK_SHIFT){

        }
    }

    @Override
    public void tileCollisionOccurred(List<CollidedTile> collidedTiles) {
        PVector vector;
        for(CollidedTile ct : collidedTiles){
            try{
                if(ct.getTile() instanceof GroundTestTile || ct.getTile() instanceof SemiTestTile){
                    vector = main.getTileMap().getTilePixelLocation(ct.getTile());
                    setY(vector.y - getHeight());
                }else if(ct.getTile() instanceof WallTestTile) {
                    switch (ct.getCollisionSide()) {
                        case LEFT:
                            vector = main.getTileMap().getTilePixelLocation(ct.getTile());
                            setX(vector.x - getWidth());
                            climbRight = true;
                            break;
                        case RIGHT:
                            vector = main.getTileMap().getTilePixelLocation(ct.getTile());
                            setX(vector.x + main.getTileMap().getTileSize());
                            climbLeft = true;
                            break;
                        case BOTTOM:
                            vector = main.getTileMap().getTilePixelLocation(ct.getTile());
                            setX(vector.y + main.getTileMap().getTileSize());
                            break;
                    }
                }
            }catch(TileNotFoundException e){
                e.printStackTrace();
            }
        }
    }
}
