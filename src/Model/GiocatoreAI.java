package Model;

import Model.Card_Rank_Suit.Card;
import Model.Card_Rank_Suit.Rank;

import java.util.List;

public class GiocatoreAI extends Giocatore {
    private boolean isAI = true;
    public GiocatoreAI(String name) {
        super(name);
        this.isAI = isAI;
    }
    public void playTurn(Gioco gioco, String s) {
        // controlla se la carta nel mazzo di scarti è utile
        Card cartaDaScarti = gioco.getMazzoDiScarti().isEmpty() ? null : gioco.getMazzoDiScarti().get(gioco.getMazzoDiScarti().size() - 1);

        // determina quale carta giocare
        Card cartaDaGiocare;
        if (cartaDaScarti != null && isCardPlayable(cartaDaScarti, gioco)) {
            // se l'ultima carta nel mazzo di scarti è giocabile, usala
            cartaDaGiocare = cartaDaScarti;
            gioco.getMazzoDiScarti().remove(gioco.getMazzoDiScarti().size() - 1); // rimuovi la carta dal mazzo di scarti
            cartaDaGiocare.flip();
        } else {
            // altrimenti, pesca dal mazzo principale
            cartaDaGiocare = gioco.getDeck().draw();

        }

        System.out.println("\nGiocatore " + this.getName() + " sta giocando la carta: " + cartaDaGiocare);
        //System.out.println("Carte rimanenti nel mazzo: " + gioco.getDeck().size());

        if (cartaDaGiocare != null) {
            eseguiLogicaCarta(cartaDaGiocare, gioco);
        }
    }
    public boolean getisAI(){
        return isAI;
    }
}
