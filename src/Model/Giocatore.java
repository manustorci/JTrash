package Model;
import Model.Card_Rank_Suit.*;

public abstract class Giocatore {
    protected String name;
    protected int n_mano = 10;
    protected boolean trash = false;
    protected Hand hand;
    protected Card cartaGiocata; //ultima carta giocata
    public Giocatore(String name) {
        this.name = name;
        this.hand = new Hand();
    }
    public abstract void playTurn(Gioco gioco, String s); //metodo astratto per giocare un turno
    protected boolean isAI;

    public Hand getHand(){
        return hand;
    }
    public String getName(){
        return name;
    }
    //poter impostare la mano di un giocatore dopo la sua creazione
    public void setHand (Hand hand){
        this.hand = hand;
    }
    // Logica se una carta è giocabile
    boolean isCardPlayable(Card card, Gioco gioco) {
        if (card.isFacedown() || card.getRank() == Rank.JACK || card.getRank() == Rank.QUEEN) {
            return false; // Le carte coperte, Jack e Regina non sono mai giocabili
        }

        int position = card.getRank().ordinal();
        // Verifica che l'indice sia entro i limiti della mano attuale del giocatore
        if (position >= 0 && position < this.hand.getCards().size()) {
            Card cardInLayout = this.hand.getCards().get(position);
            return cardInLayout.isFacedown(); // Puoi giocare la carta se quella nella posizione è coperta
        }
        return false; // Se l'indice è fuori dai limiti, la carta non è giocabile
    }
    void eseguiLogicaCarta(Card carta, Gioco gioco) {
        if (carta.isFacedown()){
            carta.flip();
        }

        // Determina l'azione in base al tipo di carta
        if (carta.getRank() == Rank.KING || carta.getRank() == Rank.JOKER) {
            playWildCard(carta, gioco);

        } else if (carta.getRank() == Rank.JACK || carta.getRank() == Rank.QUEEN) {
            System.out.println("Hai pescato un " + carta.getRank() + ". Questa carta viene scartata.");
        } else {
            if (isCardPlayable(carta, gioco)) {
                playCard(carta, gioco);
                System.out.println("Hai giocato: " + carta);
            } else {
                System.out.println("Non puoi giocare questa carta. Viene scartata.");
                gioco.discardCard(carta);
            }
        }
    }
    // logica per giocare una carta
    void playCard(Card card, Gioco gioco) {
        int position = card.getRank().ordinal();
        Hand hand = this.getHand();
        Card currentCard = hand.getCards().get(position);

        if (currentCard.isFacedown()) {
            hand.getCards().set(position, card); // posiziona la nuova carta
            eseguiLogicaCarta(currentCard, gioco);
        }
    }
    // metodo per giocare una carta jolly o un Re
    void playWildCard(Card card, Gioco gioco) {
        // Cerca la prima carta coperta nella mano per posizionare il jolly/re
        for (int position = 0; position < this.hand.getCards().size(); position++) {
            if (this.hand.getCards().get(position).isFacedown()) {
                this.hand.getCards().set(position, card); // Posiziona la carta jolly o il Re
                // Non c'è bisogno di chiamare eseguiLogicaCarta qui se stai solo posizionando la carta
                return; // Termina il metodo dopo aver posizionato la carta
            }
        }
        // Se nessuna carta coperta è stata trovata, potresti voler gestire questo caso
    }
    public boolean checkForTrash() {
        // Assumi che il set sia completo fino a prova contraria
        boolean setCompleto = true;
        for (Card card : hand.getCards()) {
            if (card.isFacedown()) {
                setCompleto = false;
                break;
            }
        }
        if (setCompleto) {
            this.trash = true;
            this.n_mano--;
        }
        return setCompleto;
    }
    public int getN_mano() {
        return n_mano;
    }
    public boolean getisAI(){return isAI;}
}
