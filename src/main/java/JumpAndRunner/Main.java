package JumpAndRunner;

import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.view.View;

public class Main extends GameEngine {

    private Player player;
    private World world;
    public static String MEDIA_URL ="src/main/java/JumpAndRunner/media/";

    public static void main(String[] args){
        Main world = new Main();
        world.runSketch();
    }

    @Override
    public void setupGame() {
        int worldWidth = 500;
        int worldHeight = 500;

        player = new Player(this);
        world = new World();
        addGameObject(player, 200, 200);
        tileMap = world.getTileMap(1);
        View view = new View(worldWidth, worldHeight);

        setView(view);

        size(worldWidth, worldHeight);
    }

    @Override
    public void update() {

    }
}
