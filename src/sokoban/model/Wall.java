package sokoban.model;

import java.awt.*;

public class Wall extends CollisionObject {
    public Wall(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        Graphics2D g2 = (Graphics2D) graphics;
        g2.setPaint(Color.GRAY);
        g2.fillRect(getX() - getWidth()/2, getY() - getHeight()/2, getWidth(), getHeight());
        g2.setPaint(Color.BLACK);
        g2.drawRect(getX() - getWidth()/2, getY() - getHeight()/2, getWidth(), getHeight());
    }
}
