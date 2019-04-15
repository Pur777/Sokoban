package sokoban.model;

import java.awt.*;

public class Home extends GameObject {
    public Home(int x, int y) {
        super(x, y, 4, 4);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.CYAN);
        graphics.fillOval(getX() - getWidth()/2, getY() - getHeight() / 2, getWidth(), getHeight());
    }
}
