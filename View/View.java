package View;

import Model.*;
import java.awt.Color;
import java.awt.Font;
import static java.awt.Font.BOLD;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

/**
 *
 * @author David Berardi
 */
public class View extends javax.swing.JFrame implements Observer {
    private Model model;
    public final int WIDTH = 800;
    public final int HEIGHT = 600;

    /**
     * Creates new form View
     */
    public View() {
        initComponents();
    }

    public View(Model model) {
        this();
        this.model = model;

        this.model.addObserver(this);

        this.setSize(WIDTH + 16, HEIGHT + 39);

    }

    public void render(Graphics gg) {
        Graphics2D g = (Graphics2D) gg;

        // background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH + 400, HEIGHT + 400);

        if (!Model.dead) {
            // blocks
            g.setColor(Color.YELLOW);
            for (Block b : model.blocks) {
                g.drawRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
                g.fillRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
            }

            // paddle
            g.setColor(Color.RED);
            g.fillRect(model.getX(), model.getY(), model.getWidth(), model.getHeight());

            // ball
            g.setColor(Color.GREEN);
            g.fillOval(model.ball.getX() - model.ball.getRadius(), model.ball.getY() - model.ball.getRadius(),
                    model.ball.getRadius() * 2, model.ball.getRadius() * 2);

            // score
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", BOLD, 20));
            g.drawString("score: " + model.score, 5, 30);
            g.drawString("lifes: " + model.ball.lives, 5, 50);

        } else if (Model.dead) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", BOLD, 55));
            g.drawString("You're dead!", WIDTH / 2 - 220, HEIGHT / 2);
        } else if (Model.win) {
            System.out.println("win");
            g.setColor(Color.GREEN);
            g.setFont(new Font("Arial", BOLD, 55));
            g.drawString("You win!", WIDTH / 2 - 220, HEIGHT / 2);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        this.repaint();
    }

    public void addGameKeyboardListener(KeyListener l) {
        this.addKeyListener(l);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new JPanel() {
            @Override
            public void paint(Graphics g) {
                render(g);
            }
        };

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 400, Short.MAX_VALUE));
        panelLayout.setVerticalGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 300, Short.MAX_VALUE));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
                panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
                panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        pack();
    }// </editor-fold>//GEN-END:initComponents
     // Variables declaration - do not modify//GEN-BEGIN:variables

    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables
}
