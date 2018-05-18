package Controller;

import Model.*;
import View.View;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.TimerTask;
import java.util.Timer;

/**
 *
 * @author David Berardi
 */
public class Controller {
    private Model model;
    private View view;
    private Timer timer;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;

        // sets the paddle position
        model.setX(view.WIDTH / 2 - 10);
        model.setY(view.HEIGHT - model.getHeight() - 20);

        // adds the blocks the to scene
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < view.WIDTH % Model.BLOCK_WIDTH; j++) {
                model.addBlock((j) * (Model.BLOCK_HEIGHT + 3) + 145, i * (Model.BLOCK_WIDTH + 3));
            }
        }

        // updates the paddle's position
        this.timer = new Timer("Timer");
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!Model.dead && !Model.win) {
                    if (model.getX() < 0)
                        model.setX(1);
                    else if (model.getX() + model.getWidth() >= view.WIDTH)
                        model.setX(view.WIDTH - model.getWidth() - 1);
                    model.update();
                    model.ballUpdate();
                    model.collision1();
                    model.collisionBlocks();
                }
                if (model.blocks.isEmpty()) {
                    model.win();
                }
            }
        }, 16, 16);

        this.view.addGameKeyboardListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();

                switch (key) {
                case KeyEvent.VK_RIGHT:
                    model.setVelocity(10);
                    break;
                case KeyEvent.VK_LEFT:
                    model.setVelocity(-10);
                    break;
                case KeyEvent.VK_1:
                    model.clean();
                    break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                model.setVelocity(0);
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }
        });
    }

}
