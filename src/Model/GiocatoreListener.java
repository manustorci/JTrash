package Model;

import Model.Card_Rank_Suit.Card;

public interface GiocatoreListener {
    void onCardFlipped(Card card);
    void onGameUpdate();
}