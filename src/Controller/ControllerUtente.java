package Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import Model.Card_Rank_Suit.Card;
import Model.Giocatore;
import Model.GiocatoreUmano;
import Model.Gioco;
import View.GamePanel;
public class ControllerUtente implements ActionListener {
    private Gioco gioco;
    private GamePanel gamePanel;
    // definizione dei bottoni
    private JButton drawFromDiscardPileButton;
    private JButton drawFromDeckButton;
    public ControllerUtente(Gioco gioco) {
        this.gioco = gioco;
        gioco.setControllerUtente(this); // Imposta questo controller come il controller di Gioco
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == drawFromDiscardPileButton) {
            gioco.turno("scarti");
        } else if (e.getSource() == drawFromDeckButton) {
            gioco.turno("mazzo");
        }
        for (Giocatore giocatore : gioco.getListaGiocatori()) {
            if (gioco.getGiocatoreAttuale().getisAI()) {
                gioco.turno("x");
            }
        }
        //gamePanel.repaint(); // aggiorna il pannello di gioco E MO?
    }
    public void gestisciCartaJolly(GiocatoreUmano giocatore, Card cartaJolly) {
        // metti i pulsanti per la finestra di dialogo
        final int numCards = giocatore.getHand().getCards().size();
        Object[] options = new Object[numCards];
        for (int i = 0; i < numCards; i++) {
            options[i] = "Posizione " + (i + 1);
        }

        int posizioneScelta = JOptionPane.showOptionDialog(null, "scegli la posizione dove vuoi posizionare la tua carta jolly:", "scelta Posizione Jolly",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        // check se l'utente ha chiuso la finestra o ha fatto una scelta
        if (posizioneScelta >= 0 && posizioneScelta < numCards) {
            // metto la carta jolly nella posizione scelta
            giocatore.getHand().getCards().set(posizioneScelta, cartaJolly);
        } else {
            gioco.discardCard(cartaJolly);
        }
    }
    public void setDrawFromDiscardPileButton(JButton button) {
        this.drawFromDiscardPileButton = button;
        button.addActionListener(this);
    }
    public void setDrawFromDeckButton(JButton button) {
        this.drawFromDeckButton = button;
        button.addActionListener(this);
    }
    public void turno(String nome){
        gioco.turno(nome);
    }
    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
}