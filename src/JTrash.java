import javax.swing.*;

import Model.Gioco;
import View.*;
import Controller.*;

public class JTrash {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("JTrash");

        Gioco gioco = new Gioco();
        ControllerUtente controllerUtente = new ControllerUtente(gioco);
        ControllerGioco controllerGioco = new ControllerGioco(gioco); // creazione del controller con il model
        GamePanel gamePanel = new GamePanel(controllerGioco, controllerUtente); // la view riceve il controller

        controllerGioco.setGamePanel(gamePanel); // imposta il riferimento a GamePanel in ControllerGioco
        controllerUtente.setGamePanel(gamePanel);


        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
        gamePanel.setupGame();
        gamePanel.requestFocusInWindow();

    }
}