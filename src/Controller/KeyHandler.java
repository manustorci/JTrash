package Controller;
import Model.*;
import View.GamePanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    ControllerGioco cg;
    GamePanel gp;
    public KeyHandler(GamePanel gp, ControllerGioco cg){
        this.gp = gp;
        this.cg = cg;
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        // gestione dell'input nel TITLE STATE
        if (gp.gameState == gp.titleState) {
            if (gp.ui.titleScreenState == 0){
                handleTitleStateZero(code);
            } else if (gp.ui.titleScreenState == 1){
                handleTitleStateUno(code);
            } else if (gp.ui.titleScreenState == 2){
                handleTitleStateDue(code);
            } else if (gp.ui.titleScreenState == 3){
                handeStatistiche(code);
            }
        }
        // gestione dell'input nel PLAY STATE
        else if (gp.gameState == gp.playState) {
            handlePlayStateInput(code);
        }
        //gestione input GAME OVER STATE
        else if (gp.gameState == gp.gameOverState){
            handleGameOver(code);
        }

        gp.repaint();
    }
    private void handleTitleStateZero(int code) {
        if (code == KeyEvent.VK_W) {
            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = 2;
            }
        }
        else if (code == KeyEvent.VK_S) {
            gp.ui.commandNum++;
            if (gp.ui.commandNum > 2) {
                gp.ui.commandNum = 0;
            }
        }
        else if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.commandNum == 0){
                gp.ui.titleScreenState = 1;
            }
            if (gp.ui.commandNum == 1){
                gp.ui.commandNum = 0;

                gp.ui.titleScreenState = 3;

            } else if (gp.ui.commandNum == 2){
                System.exit(0);
            }
        }
    }
    private void handleTitleStateUno(int code) {
        if (code == KeyEvent.VK_W) {
            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = 3;
            }
        }
        else if (code == KeyEvent.VK_S) {
            gp.ui.commandNum++;
            if (gp.ui.commandNum > 3) {
                gp.ui.commandNum = 0;
            }
        }
        else if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.commandNum == 0){
                cg.aggiungiGiocatore("Timmy");
                gp.setGiocatore("Timmy");
                cg.setGiocatoreFoto("timmy");
                cg.setPathGiocatoreFoto("/Users/emanuelestorci/Documents/java/JTrash/src/Controller/Avatar/avatar1.0.png");
                gp.ui.titleScreenState = 2;
            }
            else if (gp.ui.commandNum == 1){
                cg.aggiungiGiocatore("Brodie");
                gp.setGiocatore("Brodie");
                cg.setGiocatoreFoto("brodie");
                cg.setPathGiocatoreFoto("/Users/emanuelestorci/Documents/java/JTrash/src/Controller/Avatar/avatar2.0.png");
                gp.ui.commandNum = 0;
                gp.ui.titleScreenState = 2;
            }
            else if (gp.ui.commandNum == 2){
                cg.aggiungiGiocatore("Dumbo");
                gp.setGiocatore("Dumbo");
                cg.setGiocatoreFoto("dumbo");
                cg.setPathGiocatoreFoto("/Users/emanuelestorci/Documents/java/JTrash/src/Controller/Avatar/avatar3.0.png");
                gp.ui.commandNum = 0;
                gp.ui.titleScreenState = 2;
            }
            else if (gp.ui.commandNum == 3){
                gp.ui.commandNum = 0;
                gp.ui.titleScreenState = 0;
            }
        }
    }
    private void handleTitleStateDue(int code) {
        if (code == KeyEvent.VK_W) {
            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = 3;
            }
        }
        else if (code == KeyEvent.VK_S) {
            gp.ui.commandNum++;
            if (gp.ui.commandNum > 3) {
                gp.ui.commandNum = 0;
            }
        }
        else if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.commandNum == 0){
                gp.gameState = gp.playState;
                cg.aggiungiGiocatoriAI(1); // vs 1giocatoreAI
                cg.setGiocatori(1);
                cg.inizializzaGame();
            }
            else if (gp.ui.commandNum == 1){
                gp.ui.commandNum = 0;
                cg.aggiungiGiocatoriAI(2); // vs 2 giocatoreAi
                cg.setGiocatori(2);
                Deck.getInstance().prepareDeck(3);
                cg.inizializzaGame();
                gp.gameState = gp.playState;
            }
            else if (gp.ui.commandNum == 2){
                gp.ui.commandNum = 0;
                cg.aggiungiGiocatoriAI(3); // imposta il numero di giocatori per il model
                cg.setGiocatori(3);// vs 3giocatoreAI
                Deck.getInstance().prepareDeck(3);
                cg.inizializzaGame();
                gp.gameState = gp.playState;
            }
            else if (gp.ui.commandNum == 3){
                gp.ui.commandNum = 0;
                cg.clearGiocatori();
                gp.ui.titleScreenState = 1;
            }
        }
    }
    private void handleGameOver(int code){
        if (code == KeyEvent.VK_W) {
            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = 2;
            }
        }
        else if (code == KeyEvent.VK_S) {
            gp.ui.commandNum++;
            if (gp.ui.commandNum > 2) {
                gp.ui.commandNum = 0;
            }
        }
        else if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.commandNum == 0){
                //ricomincia
                cg.resettaGioco();
                gp.gameState = gp.playState;
                gp.repaint();
                gp.requestFocusInWindow(); // riporta il focus alla finestra di gioco se necessario
            }
            else if (gp.ui.commandNum == 1){
                //menu principale
                gp.ui.commandNum = 0;
                //devi resettare:
                //il deck - i giocatori - il profilo selezionato
                cg.clearGioco();
                gp.gameState = gp.titleState;
                gp.repaint();

                gp.ui.titleScreenState = 0;
            }
            else if (gp.ui.commandNum == 2){
                //esci
                System.exit(0);
            }
        }
    }
    private void handlePlayStateInput(int code) {
        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.commandNum == 0){
                gp.gameState = gp.titleState;
            }
        }
    }
    private void handeStatistiche(int code){

        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.commandNum == 0){
                gp.ui.titleScreenState = 0;
            }
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
}