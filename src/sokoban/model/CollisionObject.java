package sokoban.model;

public abstract class CollisionObject extends GameObject{
    public CollisionObject(int x, int y) {
        super(x, y);
    }

    public boolean isCollision(GameObject gameObject, Direction direction){
        int x = getX();
        int y = getY();
        if (direction.equals(Direction.LEFT)) {
            x -= Model.FIELD_CELL_SIZE;
        } else if (direction.equals(Direction.RIGHT)) {
            x += Model.FIELD_CELL_SIZE;
        } else if (direction.equals(Direction.DOWN)) {
            y += Model.FIELD_CELL_SIZE;
        } else if (direction.equals(Direction.UP)){
            y -= Model.FIELD_CELL_SIZE;
        }

        return x == gameObject.getX() && y == gameObject.getY();
    }
}
