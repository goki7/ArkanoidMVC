import Model.Model;
import View.View;
import Controller.Controller;

/**
 *
 * @author David Berardi
 */
public class ArkanoidMVC {
    public static void main(String[] args) {
        Model model = new Model();
        View view = new View(model);
        Controller controller = new Controller(model, view);
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                view.setVisible(true);
            }
        });
    }
}
