package Model;

import Model.Card_Rank_Suit.Card;
import Model.Card_Rank_Suit.Rank;

public class GiocatoreUmano extends Giocatore {
    private boolean isAI = false;
    public GiocatoreUmano(String name) {
        super(name);
        this.isAI = isAI;
    }

    @Override
    public void playTurn(Gioco gioco, String sceltaPescare) {
        // mostra il mazzo di scarti se non è vuoto, altrimenti informa che è vuoto
        if (!gioco.getMazzoDiScarti().isEmpty()) {
            //.out.println("il mazzo di scarti contiene: " + gioco.getMazzoDiScarti().get(gioco.getMazzoDiScarti().size() - 1));
        } else {
            //System.out.println("il mazzo di scarti è vuoto.");
        }

        Card cartaScelta = null; // Inizializza cartaScelta a null

        // logica per pescare dal mazzo di scarti o dal mazzo principale
        if (sceltaPescare.equalsIgnoreCase("s") && !gioco.getMazzoDiScarti().isEmpty()) {
            cartaScelta = gioco.getMazzoDiScarti().remove(gioco.getMazzoDiScarti().size() - 1);
        } else {
            cartaScelta = gioco.getDeck().draw();
        }

        // Controlla se la cartaScelta è null prima di chiamare metodi su di essa
        if (cartaScelta != null) {
            cartaScelta.flip();
            gioco.notifyCardFlipped(cartaScelta);
            //System.out.println("\nUTENTE Hai pescato: " + cartaScelta);
            eseguiLogicaCarta(cartaScelta, gioco); // Esegui la logica specifica della carta solo se non è null
        } else {
            //System.out.println("Non ci sono più carte da pescare.");
            // Potresti voler gestire questa situazione, ad esempio passando il turno o notificando che il mazzo è esaurito
        }
    }


    @Override
    void eseguiLogicaCarta(Card carta, Gioco gioco) {
        if (carta.isFacedown()){
            carta.flip();
        }

        // determina l'azione in base al tipo di carta
        if (carta.getRank() == Rank.KING || carta.getRank() == Rank.JOKER) {
            gioco.getControllerUtente().gestisciCartaJolly(this, carta);
        } else if (carta.getRank() == Rank.JACK || carta.getRank() == Rank.QUEEN) {

        } else {
            if (isCardPlayable(carta, gioco)) {
                playCard(carta, gioco);

            } else {
                gioco.discardCard(carta);
            }
        }
    }
}
