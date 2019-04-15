package sokoban.model;

import sokoban.controller.EventListener;

import java.nio.file.Paths;

public class Model {
    public static final int FIELD_CELL_SIZE = 20;
    private EventListener eventListener;
    private GameObjects gameObjects;
    private int currentLevel = 1;
    private LevelLoader levelLoader = new LevelLoader(Paths.get(Paths.get("").toAbsolutePath().toString() + "/src/sokoban/res/levels.txt"));

    public GameObjects getGameObjects() {
        return gameObjects;
    }

    public void restartLevel(int level){
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart(){
        restartLevel(currentLevel);
    }

    public void startNextLevel() {
        currentLevel++;
        restart();
    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public void move(Direction direction) {
        Player player = gameObjects.getPlayer();
        if (checkWallCollision(player, direction)) {
            return;
        }
        if (checkBoxCollisionAndMoveIfAvaliable(direction)) {
            return;
        }

        switch (direction) {
            case LEFT:
                player.move(-FIELD_CELL_SIZE, 0);
                break;
            case RIGHT:
                player.move(FIELD_CELL_SIZE, 0);
                break;
            case UP:
                player.move(0,-FIELD_CELL_SIZE);
                break;
            case DOWN:
                player.move(0,FIELD_CELL_SIZE);
                break;
        }
        checkCompletion();
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction){
        for (Wall w : gameObjects.getWalls()) {
            if (gameObject.isCollision(w, direction)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkBoxCollisionAndMoveIfAvaliable(Direction direction){
        Player player = gameObjects.getPlayer();

        for (Box b : gameObjects.getBoxes()) {
            if (player.isCollision(b, direction)) {
                if (checkWallCollision(b, direction)) {
                    return true;
                } else {
                    for (Box bb : gameObjects.getBoxes()) {
                        if (b != bb && b.isCollision(bb, direction)) {
                            return true;
                        }
                    }
                }

                switch (direction) {
                    case LEFT:
                        b.move(-FIELD_CELL_SIZE, 0);
                        break;
                    case RIGHT:
                        b.move(FIELD_CELL_SIZE, 0);
                        break;
                    case UP:
                        b.move(0,-FIELD_CELL_SIZE);
                        break;
                    case DOWN:
                        b.move(0,FIELD_CELL_SIZE);
                        break;
                }
            }
        }
        return false;
    }

    public void checkCompletion(){
        int count = 0;
        for (Home h : gameObjects.getHomes()) {
            for (Box b : gameObjects.getBoxes()) {
                if (h.getX() == b.getX() && h.getY() == b.getY()) {
                    count++;
                }
            }
        }
        if (count == gameObjects.getHomes().size()){
            eventListener.levelCompleted(currentLevel);
        }
    }
}
