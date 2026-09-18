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

public class Week9 extends JPanel implements KeyListener, ActionListener {
public static final int WIDTH = 600;
public static final int HEIGHT = 700;
// Player Car Position
int playerX = 260;
int playerY = 500;
// Enemy Car Position
int enemyX = 260;
int enemyY = -120;
// Game Loop & Animation Variables
Timer timer;
int roadOffsetY = 0;
Random random = new Random();
// Score & Timer Variables
int score = 0;
int gameTimeSeconds = 0;
int frameCounter = 0;
// Game States(welcome & gameover)
boolean isWelcomeScreen = true;
boolean isGameOver = false;
// Sound Clips
Clip welcomeClip;
Clip runClip;
Clip scoreClip;
Clip crashClip;

public Week9() {
setPreferredSize(new Dimension(WIDTH, HEIGHT));
setBackground(new Color(10, 10, 30));
setFocusable(true);
addKeyListener(this);
// Continuous game updating using Java Timer (~60 FPS)
timer = new Timer(16, this);
timer.start();
// Start Welcome Sound
playWelcomeSound();}

//Sound Effects System implementation
public void playWelcomeSound() {
try { URL url = getClass().getResource("/weeks/welcome.wav");
if (url != null) {
AudioInputStream audio = AudioSystem.getAudioInputStream(url);
welcomeClip = AudioSystem.getClip();
welcomeClip.open(audio);
welcomeClip.loop(Clip.LOOP_CONTINUOUSLY);}}
catch (Exception e) { e.printStackTrace();}}
public void stopWelcomeSound() {
if (welcomeClip != null) {welcomeClip.stop();}}
public void startRunSound() {
try { URL url = getClass().getResource("/weeks/run.wav");
if (url != null) {
AudioInputStream audio = AudioSystem.getAudioInputStream(url);
runClip = AudioSystem.getClip();
runClip.open(audio);
runClip.loop(Clip.LOOP_CONTINUOUSLY);}} 
catch (Exception e) { e.printStackTrace();}}
public void stopRunSound() {
if (runClip != null) { runClip.stop();}}
public void playScoreSound() {
try { URL url = getClass().getResource("/weeks/score.wav");
if (url != null) {
AudioInputStream audio = AudioSystem.getAudioInputStream(url);
scoreClip = AudioSystem.getClip();
scoreClip.open(audio);
scoreClip.start();}} 
catch (Exception e) { e.printStackTrace();}}
public void playCrashSound() {
try { URL url = getClass().getResource("/weeks/crash.wav");
if (url != null) {
AudioInputStream audio = AudioSystem.getAudioInputStream(url);
crashClip = AudioSystem.getClip();
crashClip.open(audio);
crashClip.start();}} 
catch (Exception e) {
e.printStackTrace();}}

// Reset game parameters for restart functionality
private void resetGame() {
playerX = 260;
playerY = 500;
enemyX = 260;
enemyY = -120;
score = 0;
gameTimeSeconds = 0;
frameCounter = 0;
isGameOver = false;
timer.start();
startRunSound();}

@Override
protected void paintComponent(Graphics g) {
super.paintComponent(g);
Graphics2D g2 = (Graphics2D) g;

//Welcome Screen implementation
if (isWelcomeScreen) {
// Dark background on sides
g2.setColor(new Color(10, 10, 30));
g2.fillRect(0, 0, WIDTH, HEIGHT);
// Center Road
g2.setColor(new Color(70, 70, 70));
g2.fillRect(180, 0, 240, HEIGHT);
// Center White Dashed Lane Dividers
g2.setColor(Color.WHITE);
for (int y = -80 + roadOffsetY; y < HEIGHT; y += 80) {
g2.fillRect(290, y, 20, 50);}
// Title "CAR RACING GAME" in Cyan
g2.setColor(Color.CYAN);
g2.setFont(new Font("Arial", Font.BOLD, 36));
FontMetrics fmTitle = g2.getFontMetrics();
int titleX = (WIDTH - fmTitle.stringWidth("CAR RACING GAME")) / 2;
g2.drawString("CAR RACING GAME", titleX, 200);
// Display Car centered on the Welcome Screen
drawCar(g2, 270, 290, Color.RED);
// "Press ENTER To Start" in Yellow
g2.setColor(Color.YELLOW);
g2.setFont(new Font("Arial", Font.BOLD, 28));
FontMetrics fmStart = g2.getFontMetrics();
int startX = (WIDTH - fmStart.stringWidth("Press ENTER To Start")) / 2;
g2.drawString("Press ENTER To Start", startX, 500);
// "Use LEFT and RIGHT Arrow Keys" in White
g2.setColor(Color.WHITE);
g2.setFont(new Font("Arial", Font.BOLD, 20));
FontMetrics fmKeys = g2.getFontMetrics();
int keysX = (WIDTH - fmKeys.stringWidth("Use LEFT and RIGHT Arrow Keys")) / 2;
g2.drawString("Use LEFT and RIGHT Arrow Keys", keysX, 550);} 

else {
//Normal Gameplay Screen implementation
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
g2.drawString("TIME: " + gameTimeSeconds + " Sec ", 20, 60);
// Enemy Car Creation
drawCar(g2, enemyX, enemyY, Color.BLUE);
g2.setColor(Color.RED);
// Player Car Execution
drawCar(g2, playerX, playerY, Color.RED);
g2.setColor(Color.RED);
// Game Over Overlay with Restart Instruction
if (isGameOver) {
g2.setColor(new Color(0, 0, 0, 190));
g2.fillRect(0, 0, WIDTH, HEIGHT);
g2.setColor(Color.RED);
g2.setFont(new Font("Arial", Font.BOLD, 48));
g2.drawString("GAME OVER", 160, 310);
g2.setColor(Color.WHITE);
g2.setFont(new Font("Arial", Font.BOLD, 22));
g2.drawString("Final Score: " + score, 225, 360);
g2.drawString("Time Survived: " + gameTimeSeconds + " Sec ", 210, 400);
g2.setColor(Color.YELLOW);
g2.setFont(new Font("Arial", Font.BOLD, 20));
g2.drawString("PRESS [ R ] TO RESTART", 180, 460);}}}

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
// Keep road animation moving on welcome screen
if (isWelcomeScreen) {
roadOffsetY += 5;
if (roadOffsetY >= 80) {
roadOffsetY = 0;}} 
else if (!isGameOver) {
// Gameplay Timer Update
frameCounter++;
if (frameCounter >= 60) {
gameTimeSeconds++;
frameCounter = 0;}
// Moving Road Animation
roadOffsetY += 5;
if (roadOffsetY >= 80) {
roadOffsetY = 0;}
// Enemy Movement & Score System Update
enemyY += 6;
if (enemyY > HEIGHT) {
enemyY = -120;
enemyX = 160 + random.nextInt(220);
score += 10;
playScoreSound();}
// Collision Detection System
Rectangle playerRect = new Rectangle(playerX, playerY, 60, 110);
Rectangle enemyRect = new Rectangle(enemyX, enemyY, 60, 110);
if (playerRect.intersects(enemyRect)) {
isGameOver = true;
timer.stop();
stopRunSound();
playCrashSound();}}
repaint();}

// KeyListener Controls for Starting, Restarting, and Movement
@Override
public void keyPressed(KeyEvent e) {
int key = e.getKeyCode();
// Start Game on ENTER key press
if (isWelcomeScreen && key == KeyEvent.VK_ENTER) {
isWelcomeScreen = false;
stopWelcomeSound();
startRunSound();}
// Restart Game on R key press when Game Over
if (isGameOver && key == KeyEvent.VK_R) {
resetGame();}
// Player movement controls during gameplay
if (!isWelcomeScreen && !isGameOver) {
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
JFrame frame = new JFrame("Welcome To Car Racing Game");
Week9 panel = new Week9();
frame.add(panel);
frame.pack();
frame.setResizable(false);
frame.setLocationRelativeTo(null);
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setVisible(true);});}
}