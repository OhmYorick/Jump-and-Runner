package JumpAndRunner;

import JumpAndRunner.tiles.*;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.tile.TileMap;
import nl.han.ica.oopg.tile.TileType;

public class World {

    private TileMap tileMap;

    private TileMap initializeTileMap(int level){
        Sprite topSprite = new Sprite(Main.MEDIA_URL.concat("tiles/solid.png"));
        Sprite wallSprite = new Sprite(Main.MEDIA_URL.concat("tiles/wall.png"));
        Sprite semiSprite = new Sprite(Main.MEDIA_URL.concat("tiles/semi.png"));
        Sprite spikeSprite = new Sprite(Main.MEDIA_URL.concat("tiles/spike.png"));
        Sprite tempActiveSprite = new Sprite(Main.MEDIA_URL.concat("tiles/tempActive.png"));

        TileType<GroundTestTile> groundTileType = new TileType<>(GroundTestTile.class, topSprite);
        TileType<WallTestTile> wallTileType = new TileType<>(WallTestTile.class, wallSprite);
        TileType<WallTopTestTile> wallTopTileType = new TileType<>(WallTopTestTile.class, topSprite);
        TileType<SemiTestTile> semiTileType = new TileType<>(SemiTestTile.class, semiSprite);
        TileType<HazardTestTile> spikeTileType = new TileType<>(HazardTestTile.class, spikeSprite);
        TileType<TempTestTile> tempTileType = new TileType<>(TempTestTile.class, tempActiveSprite);

        TileType[] tileTypes = {groundTileType, wallTileType, wallTopTileType, semiTileType, spikeTileType, tempTileType};
        int tileSize = 64;
        int tilesMap[][] = {

                {2, -1, -1, -1, -1, -1, 2},
                {1, -1, -1, -1, -1, -1, 1},
                {1, -1, -1, -1, -1, -1, 1},
                {1, -1, -1, -1, -1, -1, 1},
                {1, -1, -1, -1, -1, -1, 1},
                {1, -1, -1, -1, -1, -1, 1},
                {1, 0, 0, 3, 0, 0, 1}
        };
        return new TileMap(tileSize, tileTypes, tilesMap);
    }

    public TileMap getTileMap(int level){
        return initializeTileMap(level);
    }
}
