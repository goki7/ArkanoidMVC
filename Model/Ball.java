package Model;

/**
 *
 * @author David Berardi
 */
public class Ball {
    private int x, y, radius = Model.BALL_RADIUS, vel_x = Model.BALL_VELOCITY, vel_y = Model.BALL_VELOCITY;
    public static int lives = 3;

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getVel_x() {
        return vel_x;
    }

    public void setVel_x(int vel_x) {
        this.vel_x = vel_x;
    }

    public int getVel_y() {
        return vel_y;
    }

    public void setVel_y(int vel_y) {
        this.vel_y = vel_y;
    }

    public void update() {
        x += vel_x;
        y += vel_y;

        if (left() < 0)
            vel_x = Model.BALL_VELOCITY;
        else if (right() > 800)
            vel_x = -Model.BALL_VELOCITY;
        if (top() < 0) {
            vel_y = Model.BALL_VELOCITY;
        } else if (bottom() > 600) {
            vel_y = -Model.BALL_VELOCITY;
            x = 400;
            y = 400;

            if (lives <= 0) {
                Model.dead = true;
            }

            lives--;
        }
    }

    double left() {
        return x - radius;
    }

    double right() {
        return x + radius;
    }

    double top() {
        return y - radius;
    }

    double bottom() {
        return y + radius;
    }

    boolean isIntersecting(Block mA, Ball mB) {
        return mA.right() >= mB.left() && mA.left() <= mB.right() && mA.bottom() >= mB.top() && mA.top() <= mB.bottom();
    }

    boolean isIntersecting(Model mA, Ball mB) {
        return mA.right() >= mB.left() && mA.left() <= mB.right() && mA.bottom() >= mB.top() && mA.top() <= mB.bottom();
    }

    void testCollision(Block mBrick, Ball mBall) {
        if (!isIntersecting(mBrick, mBall))
            return;

        mBrick.destroyed = true;

        double overlapLeft = mBall.right() - mBrick.left();
        double overlapRight = mBrick.right() - mBall.left();
        double overlapTop = mBall.bottom() - mBrick.top();
        double overlapBottom = mBrick.bottom() - mBall.top();

        boolean ballFromLeft = overlapLeft < overlapRight;
        boolean ballFromTop = overlapTop < overlapBottom;

        double minOverlapX = ballFromLeft ? overlapLeft : overlapRight;
        double minOverlapY = ballFromTop ? overlapTop : overlapBottom;

        if (minOverlapX < minOverlapY) {
            mBall.vel_x = ballFromLeft ? -Model.BALL_VELOCITY : Model.BALL_VELOCITY;
        } else {
            mBall.vel_y = ballFromTop ? -Model.BALL_VELOCITY : Model.BALL_VELOCITY;
        }
    }

    void testCollision2(Model mBrick, Ball mBall) {
        if (!isIntersecting(mBrick, mBall))
            return;

        double overlapLeft = mBall.right() - mBrick.left();
        double overlapRight = mBrick.right() - mBall.left();
        double overlapTop = mBall.bottom() - mBrick.top();
        double overlapBottom = mBrick.bottom() - mBall.top();

        boolean ballFromLeft = overlapLeft < overlapRight;
        boolean ballFromTop = overlapTop < overlapBottom;

        double minOverlapX = ballFromLeft ? overlapLeft : overlapRight;
        double minOverlapY = ballFromTop ? overlapTop : overlapBottom;

        if (minOverlapX < minOverlapY) {
            mBall.vel_x = ballFromLeft ? -Model.BALL_VELOCITY : Model.BALL_VELOCITY;
        } else {
            mBall.vel_y = ballFromTop ? -Model.BALL_VELOCITY : Model.BALL_VELOCITY;
        }
    }
}
