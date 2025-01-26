package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Animazione {
    private List<Image> images1; // Lista delle immagini per l'animazione.
    private List<Image> images2; // Lista delle immagini per l'animazione.
    private List<Image> images3; // Lista delle immagini per l'animazione.
    private int currentImageIndex = 0; // Indice dell'immagine corrente.
    private Timer animationTimer; // Timer per aggiornare l'animazione.
    private int currentImageIndex1 = 0;
    private int currentImageIndex2 = 0;
    private int img1X = 0; // Posizione X iniziale dell'immagine animata.
    private int img1Y = 0; // Posizione Y iniziale dell'immagine animata.
    private int img2X = 500; // Prova una posizione iniziale diversa.
    private int img2Y = 100; // Prova una posizione iniziale diversa.
    private int img3X = 1000; // Prova una posizione iniziale diversa.
    private int img3Y = 400; // Prova una posizione iniziale diversa.
    private int xVelocity1 = 4; // Velocità di spostamento orizzontale dell'immagine per la prima animazione.
    private int yVelocity1 = 4; // Velocità di spostamento verticale dell'immagine per la prima animazione.
    private int xVelocity2 = 4; // Rallenta e cambia direzione.
    private int yVelocity2 = 4; // Mantiene la y fissa per il test.
    private int panelWidth = 1152; // Da sostituire con la larghezza effettiva del pannello.
    private int panelHeight = 672; // Da sostituire con l'altezza effettiva del pannello.

    public Animazione(){
        images1 = new ArrayList<>();
        images2 = new ArrayList<>();
        images3 = new ArrayList<>();
        loadImages1();
        loadImages2();
        loadImages3();
        animationTimer = new Timer(100, new ActionListener() { // Regola il tempo per controllare la velocità di animazione.
            @Override
            public void actionPerformed(ActionEvent e) {
                updateAnimation();
                updatePosition();
            }
        });
        animationTimer.start();
    }
    public void drawAnimation(Graphics2D g) {
        if (!images1.isEmpty()) {
            Image currentImage = images1.get(currentImageIndex1);
            g.drawImage(currentImage, img1X, img1Y, null);
        }
        if (!images2.isEmpty()) {
            Image secondImage = images2.get(currentImageIndex2);
            g.drawImage(secondImage, img2X, img2Y, null);
        }        if (!images3.isEmpty()) {
            Image secondImage = images3.get(currentImageIndex2);
            g.drawImage(secondImage, img3X, img3Y, null);
        }
    }
    private void loadImages1() {
        try {
            for (int i = 1; i <= 6; i++) { // Assumendo che hai 5 immagini numerate da 1 a 5.
                Image image = ImageIO.read(new File("/Users/emanuelestorci/Documents/java/JTrash/src/View/gif1/" + i + ".png"));
                images1.add(image);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadImages2() {
        try {
            for (int i = 1; i <= 6; i++) { // Assumendo che hai 5 immagini numerate da 1 a 5.
                Image image = ImageIO.read(new File("/Users/emanuelestorci/Documents/java/JTrash/src/View/gif2/" + i + ".png"));
                images2.add(image);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadImages3() {
        try {
            for (int i = 1; i <= 6; i++) { // Assumendo che hai 5 immagini numerate da 1 a 5.
                Image image = ImageIO.read(new File("/Users/emanuelestorci/Documents/java/JTrash/src/View/gif3/" + i + ".png"));
                images3.add(image);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void updateAnimation() {
        if (!images1.isEmpty()) {
            currentImageIndex1 = (currentImageIndex1 + 1) % images1.size();
        }
        if (!images2.isEmpty()) {
            currentImageIndex2 = (currentImageIndex2 + 1) % images2.size();
        }
        if (!images3.isEmpty()) {
            currentImageIndex2 = (currentImageIndex2 + 1) % images3.size();
        }
    }
    public void updatePosition() {
        // Aggiorna posizione per la prima animazione
        if (!images1.isEmpty()) {
            Image currentImage = images1.get(currentImageIndex1); // Usa currentImageIndex1 qui
            int imageWidth = currentImage.getWidth(null);
            int imageHeight = currentImage.getHeight(null);

            if (img1X >= panelWidth - imageWidth || img1X < 0) {
                xVelocity1 *= -1;
            }
            img1X += xVelocity1;

            if (img1Y >= panelHeight - imageHeight || img1Y < 0) {
                yVelocity1 *= -1;
            }
            img1Y += yVelocity1;
        }

        // Aggiorna posizione per la seconda animazione
        if (!images2.isEmpty()) {
            Image secondImage = images2.get(currentImageIndex2); // Usa currentImageIndex2 qui
            int imageWidth2 = secondImage.getWidth(null);
            int imageHeight2 = secondImage.getHeight(null);

            if (img2X <= 0 || img2X >= panelWidth - imageWidth2) {
                xVelocity2 *= -1;
            }
            img2X += xVelocity2;

            if (img2Y <= 0 || img2Y >= panelHeight - imageHeight2) {
                yVelocity2 *= -1;
            }
            img2Y += yVelocity2;
        }
        if (!images3.isEmpty()) {
            Image secondImage = images3.get(currentImageIndex2); // Usa currentImageIndex2 qui
            int imageWidth2 = secondImage.getWidth(null);
            int imageHeight2 = secondImage.getHeight(null);

            if (img3X <= 0 || img3X >= panelWidth - imageWidth2) {
                xVelocity2 *= -1;
            }
            img3X += xVelocity2;

            if (img3Y <= 0 || img3Y >= panelHeight - imageHeight2) {
                yVelocity2 *= -1;
            }
            img3Y += yVelocity2;
        }
    }

}