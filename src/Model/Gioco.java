package Model;
import Controller.ControllerUtente;
import Model.Card_Rank_Suit.*;
import java.util.*;
import Controller.ControllerGioco;

public class Gioco {
    private List<Giocatore> listaGiocatori;
    private Deck deck;
    private List<Card> mazzoDiScarti;
    private Giocatore giocatoreAttuale;
    private ControllerGioco controllerGioco;
    private ControllerUtente controllerUtente;
    private Scoreboard scoreBoard;
    private boolean giocoFinito = false;
    //costruttore
    public Gioco() {
        this.listaGiocatori = new ArrayList<>();
        this.mazzoDiScarti = new ArrayList<>();
        this.deck = Deck.getInstance();
        this.scoreBoard = new Scoreboard();
    }
    // Metodo per inizializzare il gioco
    public void inizializzareGioco() {
        // Assumi che il numero di giocatori sia già stato finalizzato qui
        scoreBoard.update();
        int numberOfPlayers = listaGiocatori.size();

        // Distribuisci le carte
        for (Giocatore giocatore : listaGiocatori) {
            Hand hand = deck.dealHand(giocatore.getN_mano()); // Assicurati che dealHand() distribuisca 10 carte
            giocatore.setHand(hand);
        }

        System.out.println("Carte rimanenti nel mazzo dopo la distribuzione: " + deck.size());

        giocatoreAttuale = selezionaPrimoGiocatore();
    }
    // Metodo per selezionare il primo giocatore
    private Giocatore selezionaPrimoGiocatore() {
        return listaGiocatori.stream()
                .min(Comparator.comparingInt(Giocatore::getN_mano))
                .orElse(null);
    }

    public void notifyCardFlipped(Card carta) {
        if (controllerGioco != null) {
            controllerGioco.updateCardFlipped(carta); // Notifica al controller
        }
    }
    public void turno(String tipoPescata) {
        //giocatore corrente fa la sua mossa
        if (tipoPescata.equals("scarti") && !mazzoDiScarti.isEmpty()) {
            //cartaScelta = mazzoDiScarti.remove(mazzoDiScarti.size() - 1);
            giocatoreAttuale.playTurn(this,"s");
        } else {
            //cartaScelta = deck.draw();
            giocatoreAttuale.playTurn(this,"x");
        }


        // Controlla se il giocatore attuale ha vinto il gioco
        if (controllerGioco.isGameWon()) {
            if (!giocoFinito) {

            // Identifica il giocatore umano
            Giocatore giocatoreH = null;
            for (Giocatore giocatore : listaGiocatori) {
                if (giocatore instanceof GiocatoreUmano) {
                    giocatoreH = giocatore;
                    break;
                }
            }

            // Assumi che ci siano dei UserProfile già associati ai nomi 'Timmy', 'Brodie' e 'Dumbo'
            UserProfile userProfile = null;
            for (UserProfile profile : scoreBoard.getUserProfiles()) { // Supponiamo che scoreBoard sia l'istanza della tua Scoreboard
                if (profile.getNickname().equals(giocatoreH.getName())) {
                    userProfile = profile;
                    break;
                }
            }
            // Incrementa le statistiche
            userProfile.incrementGamesPlayed(); // La partita è finita, quindi incrementiamo il numero di partite giocate

            if (giocatoreAttuale.equals(giocatoreH)) {
                // Il giocatore umano ha vinto
                userProfile.incrementGamesWon(); // Aggiorna le statistiche di vittoria
            } else {
                // Il giocatore umano ha perso
                userProfile.incrementGamesLost(); // Aggiorna le statistiche di sconfitta
                }
                giocoFinito = true;
            }

        } else {
            // Verifica se il giocatore attuale ha fatto "Trash"
            if (giocatoreAttuale.checkForTrash()) {
                fasePostTrash(); // Esegui la fase post-"Trash" se il giocatore ha completato il suo set
            } else {
                // Se nessuno ha fatto "Trash", continua il gioco normalmente
                passaAlProssimoGiocatore();
            }
        }
        System.out.println("CARTE RIMANENTI: "+ getDeck().size() + " carte.");
    }
    private void passaAlProssimoGiocatore() {
        int indice = listaGiocatori.indexOf(giocatoreAttuale);
        giocatoreAttuale = listaGiocatori.get((indice + 1) % listaGiocatori.size());
    }
    public Deck getDeck() {
        return deck;
    }
    public Giocatore getGiocatoreAttuale() {
        return giocatoreAttuale;
    }
    public void discardCard(Card card) {
        // Assicurati che la carta non sia null prima di aggiungerla al mazzo di scarti
        if (card != null) {
            mazzoDiScarti.add(card); // Aggiunge la carta al mazzo di scarti
            //System.out.println(card + " è stata aggiunta al mazzo di scarti.");
        } else {
            //System.out.println("Nessuna carta da scartare.");
        }
    }
    public Iterable<? extends Giocatore> getListaGiocatori() {
        return listaGiocatori;
    }
    public List<Card> getMazzoDiScarti() {
        return mazzoDiScarti;
    }
    public void fasePostTrash() {
        //System.out.println("Inizia la fase post-'Trash'.");
        for (Giocatore giocatore : listaGiocatori) {
            // permetti il turno solo ai giocatori che non hanno ancora fatto "Trash"
            if (!giocatore.trash) {
                //System.out.println(giocatore.getName() + " ha ora il suo turno nella fase post-'Trash'.");
                giocatore.playTurn(this, "");
                // dopo il turno, potresti voler controllare nuovamente se il giocatore ha fatto "Trash"
                if (giocatore.checkForTrash()) {
                    //System.out.println(giocatore.getName() + " ha fatto 'Trash' durante il suo turno post-'Trash'.");
                }
            }
        }

        // dopo che tutti i giocatori hanno avuto l'opportunità di fare il loro turno post-"Trash"
        resetEPreparaPerIlTurnoSuccessivo();
    }
    private void resetEPreparaPerIlTurnoSuccessivo() {
        mazzoDiScarti.clear();

        Deck.getInstance().reset(listaGiocatori.size());

        for (Giocatore giocatore : listaGiocatori){
            giocatore.trash = false;
        }
        // Ridistribuisci le carte ai giocatori
        for (Giocatore giocatore : listaGiocatori) {
            Hand hand = deck.dealHand(giocatore.getN_mano());
            giocatore.setHand(hand);
        }

        // Opzionale: Scegli il giocatore che inizia il nuovo round
        giocatoreAttuale = selezionaPrimoGiocatore();

        // Stampa lo stato iniziale del round per debug o informazione
        System.out.println("\nInizio del nuovo round. Buona fortuna a tutti!");
    }
    public void addGiocatoriAI(int i){
        for (int j = 1; j <= i; j++) {
            listaGiocatori.add(new GiocatoreAI("Computer" + i));
        }
    }
    public ControllerUtente getControllerUtente() {
        return this.controllerUtente;
    }
    public void resetGame() {
        // Svuota le liste dei giocatori e dei mazzi di scarti
        mazzoDiScarti.clear();

        // Passa il numero dei giocatori al metodo reset del mazzo
        Deck.getInstance().reset(listaGiocatori.size());

        for (Giocatore giocatore : listaGiocatori) {
            giocatore.trash = false;
            giocatore.n_mano = 10;
        }

        // Reimposta il giocatore attuale
        giocatoreAttuale = selezionaPrimoGiocatore();

        // Inizializza il gioco come all'inizio
        inizializzareGioco();
    }
    public List<Giocatore> getGiocatori(){
        return listaGiocatori;
    }
    public void clearGame(){
        scoreBoard.update();
        listaGiocatori.clear();
        mazzoDiScarti.clear();
        Deck.getInstance().rimuoviDeck();
        deck = Deck.getInstance(); // Crea una nuova istanza del mazzo
    }
    public void clearGiocatori(){
        listaGiocatori.clear();
    }
    public void setControllerGioco(ControllerGioco controllerGioco) {
        this.controllerGioco = controllerGioco;
    }
    public void setControllerUtente(ControllerUtente controllerUtente) {
        this.controllerUtente = controllerUtente;
    }

}