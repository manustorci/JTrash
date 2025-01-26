package Model;

import Model.Card_Rank_Suit.*;
import java.util.*;

public class Hand {
    private List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }


    public List<Card> getCards() {
        return cards;
    }

    public void addCard(Card card) {
        if (cards.size() < 10) {
            cards.add(card);
        }
    }
}

