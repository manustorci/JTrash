package Model.Card_Rank_Suit;

public enum Suit {
    CUORI("H"),
    QUADRI("D"),
    FIORI("C"),
    PICCHE("S");
    private final String value;

    Suit(String value) {
        this.value = value;
    }
    public String getName(){
        return name();
    }
    public String getSuitX(){
        return value;
    }
}