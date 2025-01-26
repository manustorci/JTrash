package Model.Card_Rank_Suit;

public enum Rank {
    ASSO("1"),
    DUE("2"),
    TRE("3"),
    QUATTRO("4"),
    CINQUE("5"),
    SEI("6"),
    SETTE("7"),
    OTTO("8"),
    NOVE("9"),
    DIECI("10"),
    JACK("11"),
    QUEEN("12"),
    KING("13"),
    JOKER("Jkr");
    private final String value;

    Rank(String value) {
        this.value = value;
    }
    public String getRankX(){
        return value;
    }
    public String getName() {
        return name();
    }
}