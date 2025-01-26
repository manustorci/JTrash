package View;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import Controller.ControllerStatistiche;

import javax.imageio.ImageIO;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Animazione animazione;
    Font maruMonica, purisaB;
    private Image avatarImage1,avatarImage2,avatarImage3;
    public int commandNum = 0;
    public int titleScreenState = 0; // 0.0: primo screen, 0.1: secondo screen
    public int gameOverState = 1;
    ControllerStatistiche controllerStatistiche;
    public UI(GamePanel gp){
        this.gp = gp;
        this.controllerStatistiche = new ControllerStatistiche();
        this.animazione = new Animazione();

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

        try {
            avatarImage1 = ImageIO.read(new File("/Users/emanuelestorci/Documents/java/JTrash/src/Controller/Avatar/avatar1.0.png"));
            avatarImage2 = ImageIO.read(new File("/Users/emanuelestorci/Documents/java/JTrash/src/Controller/Avatar/avatar2.0.png"));
            avatarImage3 = ImageIO.read(new File("/Users/emanuelestorci/Documents/java/JTrash/src/Controller/Avatar/avatar3.0.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // TITLE SCREEN
    public void draw(Graphics2D g2){

        this.g2 = g2;

        g2.setFont(purisaB);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.white);

        // TITLE STATE
        if (gp.gameState == gp.titleState){
            drawTitleScreen();
        }
        // GAME STATE
        if (gp.gameState == gp.playState){
            //drawPlayGame();
        }
        // FINE PARTITA
        if (gp.gameState == gp.gameOverState){
            drawGameOver();
        }
    }
    public void drawTitleScreen(){

        if (titleScreenState == 0){
            g2.setColor(new Color(0, 0, 0));
            g2.fillRect(0,0,gp.screenWidth, gp.screenHeight);
            animazione.drawAnimation(g2);

            // TITLE NAME
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
            String text = "JTrash";
            int x = getXforCenteredText(text);
            int y = gp.tileSize*3;

            g2.setColor(Color.white);
            g2.drawString(text, x, y);

            // TIMMY IMAGE
            //x = gp.screenWidth/2;
            //y += gp.tileSize*2;
            //g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);

            // MENU
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));

            text = "NUOVO GIOCO";
            x = getXforCenteredText(text);
            y += gp.tileSize*4;
            g2.drawString(text, x, y);
            if (commandNum == 0){
                g2.drawString(">", x-gp.tileSize, y);
            }

            text = "STATISTICHE";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 1){
                g2.drawString(">", x-gp.tileSize, y);
            }

            text = "ESCI";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 2){
                g2.drawString(">", x-gp.tileSize, y);
            }

        }
        else if (titleScreenState == 1){

            // SELEZIONA PROFILO
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(42F));

            String text = "seleziona profilo";
            int x = getXforCenteredText(text);
            int y = gp.tileSize*3;
            g2.drawString(text, x, y);

            int avatarWidth = 64; // O la larghezza reale dell'avatar
            int avatarHeight = 64; // O l'altezza reale dell'avatar
            int avatarMargin = 10; // Spazio tra il testo e l'avatar

            text = "timmy";
            x = getXforCenteredText(text);
            y += gp.tileSize*3;
            g2.drawString(text, x, y);
            if (commandNum == 0){
                g2.drawString(">", x-gp.tileSize, y);
            }
            g2.drawImage(avatarImage1, x + g2.getFontMetrics().stringWidth(text) + avatarMargin, y - avatarHeight, avatarWidth, avatarHeight, null);

            text = "brodie";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 1){
                g2.drawString(">", x-gp.tileSize, y);
            }
            g2.drawImage(avatarImage2, x + g2.getFontMetrics().stringWidth(text) + avatarMargin, y - avatarHeight+8, avatarWidth, avatarHeight, null);

            text = "dumbo";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 2){
                g2.drawString(">", x-gp.tileSize, y);
            }
            g2.drawImage(avatarImage3, x + g2.getFontMetrics().stringWidth(text) + avatarMargin, y - avatarHeight+10, avatarWidth, avatarHeight, null);

            text = "back";
            x = getXforCenteredText(text);
            y += gp.tileSize*2;
            g2.drawString(text, x, y);
            if (commandNum == 3){
                g2.drawString(">", x-gp.tileSize, y);
            }
        } // seleziona profill
        else if (titleScreenState == 2){

            // SELEZIONA GIOCATORAai
            g2.setColor(Color.white);
            g2.setFont(g2.getFont().deriveFont(42F));

            String text = "numero giocatori";
            int x = getXforCenteredText(text);
            int y = gp.tileSize*3;
            g2.drawString(text, x, y);

            text = "2 giocatori";
            x = getXforCenteredText(text);
            y += gp.tileSize*3;
            g2.drawString(text, x, y);
            if (commandNum == 0){
                g2.drawString(">", x-gp.tileSize, y);
            }
            text = "3 giocatori";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 1){
                g2.drawString(">", x-gp.tileSize, y);
            }
            text = "4 giocatori";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 2){
                g2.drawString(">", x-gp.tileSize, y);
            }
            text = "back";
            x = getXforCenteredText(text);
            y += gp.tileSize*2;
            g2.drawString(text, x, y);
            if (commandNum == 3){
                g2.drawString(">", x-gp.tileSize, y);
            }
        } // seleziona giocatoriAI
        else if (titleScreenState == 3){

            g2.setColor(new Color(0, 0, 0));
            g2.fillRect(0,0,gp.screenWidth, gp.screenHeight);


            // TITLE NAME
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,65f));
            String text = "statistiche";
            int x = getXforCenteredText(text);
            int y = gp.tileSize*2;

            g2.setColor(Color.white);
            g2.drawString(text, x, y);

            //TIMMY
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,40f));
            g2.setColor(Color.yellow);

            text = "timmy";
            x = getXforCenteredText(text);
            y += gp.tileSize*3;
            g2.drawString(text, x-400, y);

            text = "giocate: "+controllerStatistiche.getPartiteGiocateTimmy();
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,39));
            g2.setColor(Color.white);
            x = getXforCenteredText(text);
            y += gp.tileSize+20;
            g2.drawString(text, x-400, y);

            text = "vinte: "+controllerStatistiche.getPartiteVinteTimmy();
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,39));
            g2.setColor(Color.white);
            x = getXforCenteredText(text);
            y += gp.tileSize+20;
            g2.drawString(text, x-400, y);

            text = "perse: "+controllerStatistiche.getPartitePerseTimmy();
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,39));
            g2.setColor(Color.white);
            x = getXforCenteredText(text);
            y += gp.tileSize+20;
            g2.drawString(text, x-400, y);

            text = "livello: "+controllerStatistiche.getLivelloTimmy();
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,39));
            g2.setColor(Color.white);
            x = getXforCenteredText(text);
            y += gp.tileSize+20;
            g2.drawString(text, x-400, y);

            //BRODIE
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,40f));
            g2.setColor(Color.yellow);

            text = "brodie";
            x = getXforCenteredText(text);
            y = gp.tileSize*2;
            y += gp.tileSize*3;
            g2.drawString(text, x, y);

            text = "giocate: "+controllerStatistiche.getPartiteGiocateBrodie();
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,39));
            g2.setColor(Color.white);
            x = getXforCenteredText(text);
            y += gp.tileSize+20;
            g2.drawString(text, x, y);

            text = "vinte: "+controllerStatistiche.getPartiteVinteBrodie();
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,39));
            g2.setColor(Color.white);
            x = getXforCenteredText(text);
            y += gp.tileSize+20;
            g2.drawString(text, x, y);

            text = "perse: "+controllerStatistiche.getPartitePerseBrodie();
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,39));
            g2.setColor(Color.white);
            x = getXforCenteredText(text);
            y += gp.tileSize+20;
            g2.drawString(text, x, y);

            text = "livello: "+controllerStatistiche.getLivelloBrodie();
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,39));
            g2.setColor(Color.white);
            x = getXforCenteredText(text);
            y += gp.tileSize+20;
            g2.drawString(text, x, y);



            //DUMBO
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,40f));
            g2.setColor(Color.yellow);

            text = "dumbo";
            x = getXforCenteredText(text);
            y = gp.tileSize*2;
            y += gp.tileSize*3;
            g2.drawString(text, x+400, y);

            text = "giocate: "+controllerStatistiche.getPartiteGiocateDumbo();
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,39));
            g2.setColor(Color.white);
            x = getXforCenteredText(text);
            y += gp.tileSize+20;
            g2.drawString(text, x+400, y);

            text = "vinte: "+controllerStatistiche.getPartiteVinteDumbo();
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,39));
            g2.setColor(Color.white);
            x = getXforCenteredText(text);
            y += gp.tileSize+20;
            g2.drawString(text, x+400, y);

            text = "perse: "+controllerStatistiche.getPartitePerseDumbo();
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,39));
            g2.setColor(Color.white);
            x = getXforCenteredText(text);
            y += gp.tileSize+20;
            g2.drawString(text, x+400, y);

            text = "livello: "+controllerStatistiche.getLivelloDumbo();
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,39));
            g2.setColor(Color.white);
            x = getXforCenteredText(text);
            y += gp.tileSize+20;
            g2.drawString(text, x+400, y);

            // BOTTONE BACK
            text = "back";
            x = getXforCenteredText(text);
            y += gp.tileSize*2;
            g2.drawString(text, x, y);
            if (commandNum == 0){
                g2.drawString(">", x-gp.tileSize, y);
            }

        } // statistiche
    }
    public void drawGameOver(){

        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0,0,gp.screenWidth, gp.screenHeight);

        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 100f));

        text = "partita finita";
        // SHADOW
        g2.setColor(Color.black);
        x = getXforCenteredText(text);
        y = gp.tileSize*4;
        g2.drawString(text,x,y);

        // se hai vinto allora compare "HAI VINTO", altrimenti "HAI PERSO"


        //MAIN
        g2.setColor(Color.white);
        g2.drawString(text,x-4,y-4);

        //ricomincia
        g2.setFont(g2.getFont().deriveFont(50f));
        text = "ricomincia";
        x = getXforCenteredText(text);
        y += gp.tileSize*4;
        g2.drawString(text,x,y);
        if (commandNum == 0){
            g2.drawString(">",x-40,y);
        }

        //menu principale
        text = "menu";
        x = getXforCenteredText(text);
        y += 60;
        g2.drawString(text,x,y);
        if (commandNum == 1){
            g2.drawString(">",x-40,y);
        }

        //esci
        text = "esci";
        x = getXforCenteredText(text);
        y += 60;
        g2.drawString(text,x,y);
        if (commandNum == 2){
            g2.drawString(">",x-40,y);
        }
    }
    public int getXforCenteredText(String text){
        int lenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - lenght/2;
        return x;
    }
    public void aggiornaStatistiche() {
        // Assicurati che questo metodo rilegga le statistiche dal file
        this.controllerStatistiche.leggiStatistiche();
        this.controllerStatistiche.aggiornaLivelli();
    }
}
