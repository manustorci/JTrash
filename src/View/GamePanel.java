package View;
import Controller.ControllerGioco;
import Controller.ControllerStatistiche;
import Controller.ControllerUtente;
import Controller.KeyHandler;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
public class GamePanel extends JPanel implements Runnable, ActionListener {
    final int originalTitleSize = 16; //16x16 tile
    final int scale = 3;
    final int tileSize = originalTitleSize * scale; // 48x48 tile
    final int maxScreenCol = 24; //16
    final int maxScreenRow = 14; //12
    final int screenWidth = tileSize * maxScreenCol; //1152pixel
    final int screenHeight = tileSize * maxScreenRow; //672pixel
    private boolean gameStatus = true;
    private ControllerGioco controllerGioco;
    private ControllerUtente controllerUtente;
    String giocatore;
    // SYSTEM
    Sound sound = new Sound();
    public UI ui = new UI(this);
    // GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int gameOverState = 3;
    private JButton drawFromDiscardPileButton;
    private JButton drawFromDeckButton;
    public GamePanel(ControllerGioco controllerGioco, ControllerUtente controllerUtente){
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.controllerGioco = controllerGioco;
        this.controllerUtente = controllerUtente;

        drawFromDiscardPileButton = new JButton("scarti");
        drawFromDeckButton = new JButton("deck");

        controllerUtente.setDrawFromDiscardPileButton(drawFromDiscardPileButton);
        controllerUtente.setDrawFromDeckButton(drawFromDeckButton);

        KeyHandler keyH = new KeyHandler(this, controllerGioco);
        this.addKeyListener(keyH);

        drawFromDiscardPileButton.addActionListener(e -> controllerUtente.turno("scarti"));
        drawFromDeckButton.addActionListener(e -> controllerUtente.turno("mazzo"));

        add(drawFromDiscardPileButton);
        add(drawFromDeckButton);
        drawFromDiscardPileButton.setVisible(false);
        drawFromDeckButton.setVisible(false);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        // TITLE SCREEEN
        if (gameState == titleState) {
            // Disegna lo schermo del titolo del gioco
            ui.draw(g2);
            if (ui.animazione != null) {
                ui.animazione.drawAnimation(g2);
            }
        } else if (gameState == playState) {
            // Disegna lo stato attuale del gioco quando è in corso
            this.updateGameStatus();
            controllerGioco.stampaStatoGioco(g2);
        } else if (gameState == gameOverState){
            ui.draw(g2);
        }

    }
    public void setupGame(){
        //playMusic(0);
        gameState = titleState;
    }
    public void playMusic(int i){

        sound.setFile(i);
        sound.play();
        sound.loop();
    }
    public void stopMusic(){

        sound.stop();
    }
    public void playSE(int i){

        sound.setFile(i);
        sound.play();
    }
    public void handleCardFlipped() {
        controllerGioco.getCarta();
        // aggiorna il disegno della carta che è stata flippata
        repaint();
    }
    @Override
    public void run() {
        while (gameStatus) {
            long time = System.currentTimeMillis();

            update(); // Aggiornamento della logica di gioco
            repaint(); // Ridisegna il pannello

            // Calcola il tempo di attesa per mantenere un frame rate costante
            long elapsedTime = System.currentTimeMillis() - time;
            long sleepTime = Math.max(0, 16 - elapsedTime); // Assumendo 60 FPS

            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void update(){

    }
    public void startGameThread() {
        Thread gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    }
    public void updateGameStatus() {
        // metodo che aggiorni i bottoni ogni volta che c'è un cambiamento nello stato del gioco.
        if (controllerGioco.isGameWon()) {
            drawFromDiscardPileButton.setVisible(false);
            drawFromDeckButton.setVisible(false);
            gameState = gameOverState;
            ui.aggiornaStatistiche();
            update();
        } else {
            drawFromDiscardPileButton.setVisible(true);
            drawFromDeckButton.setVisible(true);
        }
        repaint();
    }
    public void setGiocatore(String i){
        this.giocatore = i;
    }
}
