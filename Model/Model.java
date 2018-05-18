package Model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

/**
 *
 * @author David Berardi
 */
public class Model extends Observable {
    private int x, y, width, height, velocity;
    private static final int PADDLE_WIDTH = 60, PADDLE_HEIGHT = 20;
    public static final int BLOCK_WIDTH = 60, BLOCK_HEIGHT = 20;
    public static final int BALL_RADIUS = 10, BALL_VELOCITY = 3;

    public static boolean dead = false;
    public static boolean win = false;

    public int score = 0;

    public List<Block> blocks;
    public Ball ball;

    public Model() {
        this.width = PADDLE_WIDTH;
        this.height = PADDLE_HEIGHT;

        blocks = new ArrayList<>();
        ball = new Ball(400, 350);
    }

    public void addBlock(int x, int y) {
        blocks.add(new Block(x, y));

        setChanged();
        notifyObservers();
    }

    public void clean() {
        blocks.clear();

        setChanged();
        notifyObservers();
    }

    public void win() {
        Model.win = true;

        setChanged();
        notifyObservers();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;

        setChanged();
        notifyObservers();
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;

        setChanged();
        notifyObservers();
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;

        setChanged();
        notifyObservers();
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;

        setChanged();
        notifyObservers();
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;

        setChanged();
        notifyObservers();
    }

    public void ballUpdate() {
        ball.update();

        setChanged();
        notifyObservers();
    }

    public void update() {
        this.x += this.velocity;

        setChanged();
        notifyObservers();
    }

    public void collision1() {
        ball.testCollision2(this, ball);

        setChanged();
        notifyObservers();
    }

    public void collisionBlocks() {
        Iterator<Block> it = blocks.iterator();
        while (it.hasNext()) {
            Block block = it.next();
            ball.testCollision(block, ball);
            if (block.destroyed) {
                score++;
                it.remove();
            }
        }

        setChanged();
        notifyObservers();
    }

    double left() {
        return x - width / 2.0;
    }

    double right() {
        return x + width / 2.0;
    }

    double top() {
        return y - height / 2.0;
    }

    double bottom() {
        return y + height / 2.0;
    }
}
