package Model;

import java.util.*;

public class UserProfile {
    private String nickname;
    private int gamesPlayed;
    private int gamesWon;
    private int gamesLost;
    // costruttore
    public UserProfile(String nickname) {
        this.nickname = nickname;
        this.gamesPlayed = 0;
        this.gamesWon = 0;
        this.gamesLost = 0;
    }
    // metodi per ottenere le informazioni del profilo
    public String getNickname() {
        return nickname;
    }
    public int getGamesPlayed() {
        return gamesPlayed;
    }
    public int getGamesWon() {
        return gamesWon;
    }
    public int getGamesLost() {
        return gamesLost;
    }
    // Metodo per incrementare il numero di partite giocate
    public void incrementGamesPlayed() {
        this.gamesPlayed++;
        notifyObservers(); // Questo dovrebbe notificare il tuo Scoreboard di un cambiamento.
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
    // Metodo per incrementare il numero di partite vinte
    public void incrementGamesWon() {
        gamesWon++;
        notifyObservers();
    }
    // Metodo per incrementare il numero di partite perse
    public void incrementGamesLost() {
        gamesLost++;
        notifyObservers();
    }
    // Metodo per aumentare il livello
    private List<Observer> observers = new ArrayList<>();
    public void addObserver(Observer observer) {
        observers.add(observer);
    }
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }
    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
        notifyObservers(); // Notifica gli osservatori dei cambiamenti
    }

    // Metodo per impostare il numero di partite vinte
    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
        notifyObservers(); // Notifica gli osservatori dei cambiamenti
    }

    // Metodo per impostare il numero di partite perse
    public void setGamesLost(int gamesLost) {
        this.gamesLost = gamesLost;
        notifyObservers(); // Notifica gli osservatori dei cambiamenti
    }

}

