package Model.Card_Rank_Suit;

public class Card {
    private Suit suit;
    private Rank rank;
    private boolean isFacedown;
    private String pathFile;


    // Costruttore
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
        this.isFacedown = true;
        this.pathFile = rank.getRankX()+suit.getSuitX()+".png";
    }
    public Suit getSuit() {
        return suit;
    }
    public Rank getRank() {
        return rank;
    }
    // Metodo per girare la carta
    public void flip() {
        isFacedown = !isFacedown;
    }
    //mi ritorna il fatto che la carta sia coperta o meno
    public boolean isFacedown() {
        return isFacedown;
    }
    public boolean isFaceup() {
        return !isFacedown;
    }

    public String toString() {
        return rank + " di " + suit;
    }
    public String getPathFile(){
        return pathFile;
    }
}