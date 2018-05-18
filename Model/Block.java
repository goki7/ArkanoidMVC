package Model;

/**
 *
 * @author David Berardi
 */
public class Block {
    private int x, y, width = Model.BLOCK_WIDTH, height = Model.BLOCK_HEIGHT;
    public boolean destroyed = false;

    public Block(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
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
