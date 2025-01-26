package Model;

import Model.Card_Rank_Suit.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Deck {
    private static Deck instance;
    private List<Card> cards;
    private Gioco gioco;
    Deck() {
        cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
        System.out.println("Mazzo inizializzato con " + cards.size() + " carte."); // x debug

    }
    public static synchronized Deck getInstance() {
        if (instance == null) {
            instance = new Deck();
        }
        return instance;
    }
    public synchronized void shuffle() {
        Collections.shuffle(cards);
    }
    public synchronized Card draw() {
        if (cards.isEmpty()) {
            return null;
        }
        return cards.remove(0);
    }
    public int size() {
        return cards.size();
    }
    public Hand dealHand(int numCards) {
        Hand hand = new Hand();
        for (int i = 0; i < numCards; i++) { // distribuisce x carte
            Card drawnCard = this.draw();
            if (drawnCard != null) {
                hand.addCard(drawnCard);
            }
        }
        return hand;
    }
    public synchronized void reset(int numberOfPlayers) {
        cards.clear(); // Rimuove tutte le carte esistenti

        int numberOfDecks = numberOfPlayers > 2 ? 3 : 1; // Se i giocatori sono più di due, usa il doppio delle carte.
        initializeCards(numberOfDecks);
        shuffle();

        shuffle(); // Mescola il nuovo mazzo
    }
    public synchronized void prepareDeck(int numberOfPlayers) {
        int numberOfDecks = numberOfPlayers > 2 ? 2 : 1; // Se i giocatori sono più di due, usa il doppio delle carte.
        initializeCards(numberOfDecks);
        shuffle();
        System.out.println("POST MODIFICA: Mazzo inizializzato con " + cards.size() + " carte."); // x debug
    }
    private void initializeCards(int numberOfDecks) {
        cards = IntStream.range(0, numberOfDecks)
                .boxed()
                .flatMap(i -> Arrays.stream(Suit.values())
                        .flatMap(suit -> Arrays.stream(Rank.values())
                                .map(rank -> new Card(suit, rank))))
                .collect(Collectors.toList());
        shuffle();
    }

    public synchronized void rimuoviDeck(){
        instance = null;
    }
}
