package weeks;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Week4 extends JPanel implements KeyListener {
public static final int WIDTH = 600;
public static final int HEIGHT = 700;
// Player Car Position
int playerX = 260;
int playerY = 500;
// Enemy Car Position
int enemyX = 260;
int enemyY = 120;
public Week4() {
setPreferredSize(new Dimension(WIDTH, HEIGHT));
setBackground(new Color(30, 120, 30));
setFocusable(true);
addKeyListener(this);}
@Override

protected void paintComponent(Graphics g) {
super.paintComponent(g);
Graphics2D g2 = (Graphics2D) g;
// Grass Designing
g2.setColor(new Color(30, 120, 30));
g2.fillRect(0, 0, WIDTH, HEIGHT);
// Road Creation
g2.setColor(Color.DARK_GRAY);
g2.fillRect(150, 0, 300, HEIGHT);
// Road Borders Implement
g2.setColor(Color.WHITE);
g2.fillRect(145, 0, 5, HEIGHT);
g2.fillRect(450, 0, 5, HEIGHT);
// Lane Divider creation
g2.setColor(Color.YELLOW);
for (int y = 0; y < HEIGHT; y += 80) {
g2.fillRect(295, y, 10, 50);}
// Title Implementation
g2.setColor(Color.WHITE);
g2.setFont(new Font("Arial", Font.BOLD, 28));
g2.drawString("CAR RACING GAME", 165, 60);
// Enemy Car Creation
drawCar(g2, enemyX, enemyY, Color.BLUE);
g2.setColor(Color.RED);
g2.setFont(new Font("Arial", Font.BOLD, 20));
g2.drawString("ENEMY CAR", enemyX - 10, enemyY - 10);
// Player Car Execution
drawCar(g2, playerX, playerY, Color.RED);
g2.setColor(Color.RED);
g2.setFont(new Font("Arial", Font.BOLD, 20));
g2.drawString("PLAYER CAR", playerX - 10, playerY - 10);}

// Drawing Both Cars(Player_Car & Enemy_Car )
private void drawCar(Graphics2D g2, int x, int y, Color color) {
// Body
g2.setColor(color);
g2.fillRoundRect(x, y, 60, 110, 15, 15);
// Windshield
g2.setColor(Color.CYAN);
g2.fillRoundRect(x + 10, y + 15, 40, 22, 8, 8);
// Rear Glass
g2.fillRoundRect(x + 10, y + 70, 40, 18, 8, 8);
// Wheels
g2.setColor(Color.BLACK);
g2.fillRect(x - 4, y + 15, 8, 22);
g2.fillRect(x + 56, y + 15, 8, 22);
g2.fillRect(x - 4, y + 72, 8, 22);
g2.fillRect(x + 56, y + 72, 8, 22);
// Headlights
g2.setColor(Color.YELLOW);
g2.fillOval(x + 8, y + 3, 8, 8);
g2.fillOval(x + 44, y + 3, 8, 8);
// Tail Lights
g2.setColor(Color.YELLOW);
g2.fillOval(x + 8, y + 100, 10, 10);
g2.fillOval(x + 42, y + 100, 10, 10);}

//KeyListener Controls Implement
@Override
public void keyPressed(KeyEvent e) {
int key = e.getKeyCode();
// Move To Left Direction
if (key == KeyEvent.VK_LEFT) {
if (playerX > 150) { // To Keep within The road boundary
playerX -= 15;}}
// Move To Right Direction
if (key == KeyEvent.VK_RIGHT) {
if (playerX < 390) { // To Keep within The road boundary
playerX += 15;}}
repaint();}

@Override
public void keyTyped(KeyEvent e) {}
@Override
public void keyReleased(KeyEvent e) {}
public static void main(String[] args) {
SwingUtilities.invokeLater(() -> {
JFrame frame = new JFrame("Car Racing Game");
Week4 panel = new Week4();
frame.add(panel);
frame.pack();
frame.setResizable(false);
frame.setLocationRelativeTo(null);
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setVisible(true);});}

 }
