import javax.swing.*;
import java.awt.*;

public class Main {
    private JFrame window;
    private Panel panel1;
    private Panel panel2;

    public static void main(String[] args) {
        new Main();
    }

    public Main(){
        window = new JFrame("Calculator");
        window.setSize(310,340);
        window.setMinimumSize(new Dimension(305,300));
        panel1 = new Panel();
        panel2 = new Panel(this);
        window.getContentPane().add(BorderLayout.NORTH,panel1.North());
        window.getContentPane().add(BorderLayout.CENTER,panel2.Center());
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

    }

    public Panel getPanel1() {
        return panel1;
    }
}
