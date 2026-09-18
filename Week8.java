package weeks;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.Random;

public class Week8 extends JPanel implements KeyListener, ActionListener {
public static final int WIDTH = 600;
public static final int HEIGHT = 700;
// Player Car Position
int playerX = 260;
int playerY = 500;
// Enemy Car Position
int enemyX = 260;
int enemyY = -120;
// Game System Variables
Timer timer;
int roadOffsetY = 0;
Random random = new Random();
int score = 0;
int gameTimeSeconds = 0;
int frameCounter = 0;
boolean isGameOver = false;
// Sound Clips
Clip welcomeClip;
Clip runClip;

public Week8() {
setPreferredSize(new Dimension(WIDTH, HEIGHT));
setBackground(new Color(30, 120, 30));
setFocusable(true);
addKeyListener(this);
// Continuous game updating using Java Timer
timer = new Timer(16, this);
timer.start();
// Start Background Sounds
playWelcomeSound();
startRunSound();}

//Sound Effects System Implementation
public void playWelcomeSound() {
try {
URL url = getClass().getResource("start.wav");
if (url != null) {
AudioInputStream audio = AudioSystem.getAudioInputStream(url);
welcomeClip = AudioSystem.getClip();
welcomeClip.open(audio);
welcomeClip.loop(Clip.LOOP_CONTINUOUSLY);}} 
catch (Exception e) {
e.printStackTrace();}}
public void stopWelcomeSound() {
if (welcomeClip != null) {
welcomeClip.stop();}}
public void startRunSound() {
try {
URL url = getClass().getResource("run.wav");
if (url != null) {
AudioInputStream audio = AudioSystem.getAudioInputStream(url);
runClip = AudioSystem.getClip();
runClip.open(audio);
runClip.loop(Clip.LOOP_CONTINUOUSLY);}} 
catch (Exception e) {
e.printStackTrace();}}
public void stopRunSound() {
if (runClip != null) {
runClip.stop();}}
public void playScoreSound() {
try {
URL url = getClass().getResource("score.wav");
if (url != null) {
AudioInputStream audio = AudioSystem.getAudioInputStream(url);
Clip clip = AudioSystem.getClip();
clip.open(audio);
clip.start();}} 
catch (Exception e) {
e.printStackTrace();}}
public void playCrashSound() {
try {
URL url = getClass().getResource("crash.wav");
if (url != null) {
AudioInputStream audio = AudioSystem.getAudioInputStream(url);
Clip clip = AudioSystem.getClip();
clip.open(audio);
clip.start();}} 
catch (Exception e) {
e.printStackTrace();}}

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
// Moving Road Animation (Lane Dividers)
g2.setColor(Color.YELLOW);
for (int y = -80 + roadOffsetY; y < HEIGHT; y += 80) {
g2.fillRect(295, y, 10, 50);}
// Score Counting System & Gameplay Timer Display
g2.setColor(Color.YELLOW);
g2.setFont(new Font("Arial", Font.BOLD, 16));
g2.drawString("SCORE: " + score, 20, 30);
g2.drawString("TIME: " + gameTimeSeconds + "s", 20, 60);
// Enemy Car Creation
drawCar(g2, enemyX, enemyY, Color.BLUE);
g2.setColor(Color.RED);
g2.setFont(new Font("Arial", Font.BOLD, 20));
g2.drawString("ENEMY CAR", enemyX - 10, enemyY - 10);
// Player Car Execution
drawCar(g2, playerX, playerY, Color.RED);
g2.setColor(Color.RED);
g2.setFont(new Font("Arial", Font.BOLD, 20));
g2.drawString("PLAYER CAR", playerX - 10, playerY - 10);
// Game Over Feature Display
if (isGameOver) {
g2.setColor(new Color(0, 0, 0, 180));
g2.fillRect(0, 0, WIDTH, HEIGHT);
g2.setColor(Color.RED);
g2.setFont(new Font("Arial", Font.BOLD, 48));
g2.drawString("GAME OVER", 160, 330);
g2.setColor(Color.WHITE);
g2.setFont(new Font("Arial", Font.BOLD, 22));
g2.drawString("Final Score: " + score, 225, 380);
g2.drawString("Time Survived: " + gameTimeSeconds + " Sec ", 210, 420);}
}

// Drawing Both Cars(Player & Enemy)
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

@Override
public void actionPerformed(ActionEvent e) {
if (!isGameOver) {
// Gameplay Timer Update
frameCounter++;
if (frameCounter >= 60) {
gameTimeSeconds++;
frameCounter = 0;}
// Moving Road Animation Creation
roadOffsetY += 5;
if (roadOffsetY >= 80) {
roadOffsetY = 0;}
// Enemy Movement & Score Counting System Update
enemyY += 6;
if (enemyY > HEIGHT) {
enemyY = -120;
enemyX = 160 + random.nextInt(220);
score += 10;
playScoreSound();}// Play the required sound on passing enemy
// Collision Detection System
Rectangle playerRect = new Rectangle(playerX, playerY, 60, 110);
Rectangle enemyRect = new Rectangle(enemyX, enemyY, 60, 110);
if (playerRect.intersects(enemyRect)) {
isGameOver = true;
timer.stop();
stopRunSound();
stopWelcomeSound();
playCrashSound();} // Play sound on collision
repaint();}
}

@Override
public void keyPressed(KeyEvent e) {
if (!isGameOver) {
int key = e.getKeyCode();
if (key == KeyEvent.VK_LEFT) {
if (playerX > 150) {
playerX -= 15;}}
if (key == KeyEvent.VK_RIGHT) {
if (playerX < 390) {
playerX += 15;}}}}

@Override
public void keyTyped(KeyEvent e) {}
@Override
public void keyReleased(KeyEvent e) {}
public static void main(String[] args) {
SwingUtilities.invokeLater(() -> {
JFrame frame = new JFrame("Car Racing Game");
Week8 panel = new Week8();
frame.add(panel);
frame.pack();
frame.setResizable(false);
frame.setLocationRelativeTo(null);
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setVisible(true);});}
}