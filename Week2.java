package weeks;
import javax.swing.*;
import java.awt.*;
public class Week2 extends JPanel {
public static final int WIDTH = 600;
public static final int HEIGHT = 700;
// Sound Planning
String startSound = "start.wav";
String runSound = "run.wav";
String scoreSound = "score.wav";
String crashSound = "crash.wav";
public Week2() {
setPreferredSize(new Dimension(WIDTH, HEIGHT));
setBackground(new Color(30, 120, 30));}
@Override
protected void paintComponent(Graphics g) {
super.paintComponent(g);
Graphics2D g2 = (Graphics2D) g;

        // Grass Area
        g2.setColor(new Color(30, 120, 30));
        g2.fillRect(0, 0, WIDTH, HEIGHT);

        // Road Layout
        g2.setColor(Color.DARK_GRAY);
        g2.fillRect(150, 0, 300, HEIGHT);

        // Road Borders
        g2.setColor(Color.WHITE);
        g2.fillRect(145, 0, 5, HEIGHT);
        g2.fillRect(450, 0, 5, HEIGHT);

        // Lane Divider Planning
        g2.setColor(Color.YELLOW);

        for (int y = 0; y < HEIGHT; y += 80) {
            g2.fillRect(295, y, 10, 50);
        }

        // Title
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 30));
        g2.drawString("CAR RACING GAME", 150, 80);
// Placeholder Player Area
        g2.setColor(Color.RED);
        g2.fillRoundRect(260, 500, 60, 110, 15, 15);

        g2.setColor(Color.WHITE);
        g2.drawString("Player Area", 230, 550);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame("Car Racing Game");

            Week2 panel = new Week2();

            frame.add(panel);
            frame.pack();

            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}