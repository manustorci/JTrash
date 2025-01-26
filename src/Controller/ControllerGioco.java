package Controller;
import Model.GiocatoreUmano;
import Model.Gioco;
import Model.Giocatore;
import View.GamePanel;
import Model.Card_Rank_Suit.Card;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
public class ControllerGioco {
    int cardWidth = 80;
    private String giocatoreFoto;
    private Image pathGiocatoreFoto;
    Font maruMonica, purisaB;
    int cardHeight = 100;
    private List<Giocatore> listaGiocatori;
    private Card carta;
    private int giocatori;
    private Gioco gioco;
    private GamePanel gamePanel;
    public ControllerGioco(Gioco gioco) {
        this.gioco = gioco;
        gioco.setControllerGioco(this); // Imposta questo controller come il controller di Gioco
        this.listaGiocatori = gioco.getGiocatori();
        try {
            File fontFile = new File("/Users/emanuelestorci/Library/Fonts/MP16OSF.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            maruMonica = maruMonica.deriveFont(12f);

            fontFile = new File("/Users/emanuelestorci/Library/Fonts/Golden Age Shad.ttf");
            purisaB = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            purisaB = purisaB.deriveFont(12f);

        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void updateCardFlipped(Card carta) {
        this.carta = carta; // Assicurati di chiamare questo metodo con una carta valida prima di usarla
        gamePanel.handleCardFlipped();
    }
    public boolean isGameWon() {
        // check se il giocatore attuale ha esattamente una carta
        if (gioco.getGiocatoreAttuale().getHand().getCards().size() == 1) {
            // check se questa carta è scoperta
            Card card = gioco.getGiocatoreAttuale().getHand().getCards().get(0);

            return !card.isFacedown(); // true se la carta è scoperta
        }
        return false; // false altrimenti
    }
    public void setGiocatori(int i){
        this.giocatori = i;
    }
    public Card getCarta(){
        return carta;
    }
    public void stampaStatoGioco(Graphics2D g2) {



        g2.setFont(purisaB);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        int avatarWidth = 64; // O la larghezza reale dell'avatar
        int avatarHeight = 64; // O l'altezza reale dell'avatar
        int avatarMargin = 10; // Spazio tra il testo e l'avatar
        //stampa giocatore
        String text = giocatoreFoto;
        Image path = pathGiocatoreFoto ;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,30));
        g2.setColor(Color.yellow); // Imposta il colore del testo (modifica come preferisci)
        g2.drawString(text, 20, 280); // Disegna il nome sotto la prima mano
        g2.drawImage(path, 20 + g2.getFontMetrics().stringWidth(text) + avatarMargin, 288 - avatarHeight, avatarWidth, avatarHeight, null);
        //stampa giocatore1
        g2.drawString("Computer 1", 20, 430); // Disegna il nome sotto la prima mano
        switch (this.giocatori) {
            case 1:
                int x = 20;
                int y = 20;
                int j = 0;
                for (Giocatore giocatore : gioco.getListaGiocatori()) {
                    for (Card carta : giocatore.getHand().getCards()) {
                        String statoCarta = carta.isFacedown() ? "Cards/backR.png" : "Cards/" + carta.getPathFile();
                        Image imgCarta = new ImageIcon(getClass().getResource(statoCarta)).getImage();
                        g2.drawImage(imgCarta, x, y, cardWidth, cardHeight, null);


                        if (j == 2 || j == 3 || j == 4 || j == 8 || j == 9 || j == 10) {
                            x = (x + 10 + cardWidth);

                        } else {
                            x += 10 + cardWidth;
                        }
                        j++;
                        if (j == 5) {
                            x = 20;
                            y += 10 + cardHeight;
                        }
                    }

                    x = 20;
                    y = 450;
                    j = 0;

                }
            case 2:
                // 1 vs 2
                int g = 0;
                x = 20;
                y = 20;
                j = 0;
                // stampa lo stato attuale del gioco, come le mani dei giocatori e il mazzo di scarti
                for (Giocatore giocatore : gioco.getListaGiocatori()) {

                    if (g==2){
                        x = 690;
                        y = 20;
                        j = 0;
                        for (Card carta : giocatore.getHand().getCards()) {
                            String statoCarta = carta.isFacedown() ? "Cards/backR.png" : "Cards/" + carta.getPathFile();
                            Image imgCarta = new ImageIcon(getClass().getResource(statoCarta)).getImage();
                            g2.drawImage(imgCarta, x, y, cardWidth, cardHeight, null);


                            if (j == 2 || j == 3 || j == 4 || j == 8 || j == 9 || j == 10) {
                                x = (x + 10 + cardWidth);

                            } else {
                                x += 10 + cardWidth;
                            }
                            j++;
                            if (j == 5) {
                                x = 690;
                                y += 10 + cardHeight;
                            }
                        }
                    }else{

                        for (Card carta : giocatore.getHand().getCards()) {
                            String statoCarta = carta.isFacedown() ? "Cards/backR.png" : "Cards/" + carta.getPathFile();
                            Image imgCarta = new ImageIcon(getClass().getResource(statoCarta)).getImage();
                            g2.drawImage(imgCarta, x, y, cardWidth, cardHeight, null);


                            if (j == 2 || j == 3 || j == 4 || j == 8 || j == 9 || j == 10) {
                                x = (x + 10 + cardWidth);

                            } else {
                                x += 10 + cardWidth;
                            }
                            j++;
                            if (j == 5) {
                                x = 20;
                                y += 10 + cardHeight;
                            }
                        }}

                    x = 20;
                    y = 450;
                    j = 0;
                    g++;

                }
            case 3:
                // 1 vs 3
                g = 0;
                x = 20;
                y = 20;
                j = 0;
                // stampa lo stato attuale del gioco, come le mani dei giocatori e il mazzo di scarti
                for (Giocatore giocatore : gioco.getListaGiocatori()) {

                    if (g>=2){
                        x = 690;
                        y = 20;
                        j = 0;

                        if (g==3){
                            x = 690;
                            y = 450;
                        }

                        for (Card carta : giocatore.getHand().getCards()) {
                            String statoCarta = carta.isFacedown() ? "Cards/backR.png" : "Cards/" + carta.getPathFile();
                            Image imgCarta = new ImageIcon(getClass().getResource(statoCarta)).getImage();
                            g2.drawImage(imgCarta, x, y, cardWidth, cardHeight, null);


                            if (j == 2 || j == 3 || j == 4 || j == 8 || j == 9 || j == 10) {
                                x = (x + 10 + cardWidth);

                            } else {
                                x += 10 + cardWidth;
                            }
                            j++;
                            if (j == 5) {
                                x = 690;
                                y += 10 + cardHeight;
                            }
                        }
                    }else{

                        for (Card carta : giocatore.getHand().getCards()) {
                            String statoCarta = carta.isFacedown() ? "Cards/backR.png" : "Cards/" + carta.getPathFile();
                            Image imgCarta = new ImageIcon(getClass().getResource(statoCarta)).getImage();
                            g2.drawImage(imgCarta, x, y, cardWidth, cardHeight, null);


                            if (j == 2 || j == 3 || j == 4 || j == 8 || j == 9 || j == 10) {
                                x = (x + 10 + cardWidth);

                            } else {
                                x += 10 + cardWidth;
                            }
                            j++;
                            if (j == 5) {
                                x = 20;
                                y += 10 + cardHeight;
                            }
                        }}

                    x = 20;
                    y = 450;
                    j = 0;
                    g++;

                }
        }

        if (!gioco.getMazzoDiScarti().isEmpty()) {
            Card ms = gioco.getMazzoDiScarti().get(gioco.getMazzoDiScarti().size() - 1);
            String msp = "Cards/" + ms.getPathFile();
            Image imgMsp = new ImageIcon(getClass().getResource(msp)).getImage();
            g2.drawImage(imgMsp, 500, 280, cardWidth, cardHeight, null);
        } //stampa a video mazzo scarti
        if (gioco.getDeck().size() != 0) {
            String deck = "Cards/backB.png";
            Image imgDeck = new ImageIcon(getClass().getResource(deck)).getImage();
            g2.drawImage(imgDeck, 510 + cardWidth, 280, cardWidth, cardHeight, null);
        } //stampa a video mazzo principale
        if (giocatori==2){
            g2.drawString("Computer 2", 690, 270); // Disegna il nome sotto la prima mano
        }
        if (giocatori==3){
            g2.drawString("Computer 2", 690, 270); // Disegna il nome sotto la prima mano
            g2.drawString("Computer 3", 690, 430); // Disegna il nome sotto la prima mano
        }

    }
    public void aggiungiGiocatore(String nomeGiocatore){
        gioco.getGiocatori().add(new GiocatoreUmano(nomeGiocatore));
    }
    public void aggiungiGiocatoriAI(int i){
        gioco.addGiocatoriAI(i);
    }
    public void inizializzaGame(){
        gioco.inizializzareGioco();
    }
    public void clearGiocatori(){
        gioco.clearGiocatori();
    }
    public void resettaGioco(){
        gioco.resetGame();
    }
    public void clearGioco(){
        gioco.clearGame();
    }
    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    public void setGiocatoreFoto(String nome){
        this.giocatoreFoto = nome;
    }
    public void setPathGiocatoreFoto(String nome){
        try {
            pathGiocatoreFoto = ImageIO.read(new File(nome));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
