package sokoban.model;

import java.awt.*;

public class Box extends CollisionObject implements Movable {
    public Box(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {
        Graphics2D g2 = (Graphics2D) graphics;
        g2.setPaint(Color.PINK);
        g2.fillRect(getX() - getWidth()/2, getY() - getHeight()/2, getWidth(), getHeight());
        g2.setPaint(Color.BLACK);
        g2.drawRect(getX() - getWidth()/2, getY() - getHeight()/2, getWidth(), getHeight());
        g2.drawLine(getX() + getWidth()/2, getY() + getHeight()/2, getX() - getWidth()/2, getY() - getHeight()/2);
        g2.drawLine(getX() + getWidth()/2, getY() - getHeight()/2, getX() - getWidth()/2, getY() + getHeight()/2);
    }

    @Override
    public void move(int x, int y) {
        setX(getX() + x);
        setY(getY() + y);
    }
}