package Controller;

import Model.Scoreboard;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ControllerStatistiche {
    private final String filePath = "/Users/emanuelestorci/Documents/java/JTrash/src/Model/statistiche.txt";
    // Statistiche per ogni giocatore
    private int partiteGiocateTimmy, partiteVinteTimmy, partitePerseTimmy, livelloTimmy;
    private int partiteGiocateBrodie, partiteVinteBrodie, partitePerseBrodie, livelloBrodie;
    private int partiteGiocateDumbo, partiteVinteDumbo, partitePerseDumbo, livelloDumbo;

    public ControllerStatistiche() {
        leggiStatistiche();
        aggiornaLivelli();
    }
    public void leggiStatistiche() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                String identifier = parts[0].substring(0, 1);
                int partiteGiocate = Integer.parseInt(parts[0].split(":")[1].trim());
                int partiteVinte = Integer.parseInt(parts[1].split(":")[1].trim());
                int partitePerse = Integer.parseInt(parts[2].split(":")[1].trim());

                switch (identifier) {
                    case "T":
                        partiteGiocateTimmy = partiteGiocate;
                        partiteVinteTimmy = partiteVinte;
                        partitePerseTimmy = partitePerse;
                        break;
                    case "B":
                        partiteGiocateBrodie = partiteGiocate;
                        partiteVinteBrodie = partiteVinte;
                        partitePerseBrodie = partitePerse;
                        break;
                    case "D":
                        partiteGiocateDumbo = partiteGiocate;
                        partiteVinteDumbo = partiteVinte;
                        partitePerseDumbo = partitePerse;
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void aggiornaLivelli() {
        // aggiorna i livelli in base alle partite vinte
        livelloTimmy = partiteVinteTimmy / 5;
        livelloBrodie = partiteVinteBrodie / 5;
        livelloDumbo = partiteVinteDumbo / 5;
    }
    // Metodi getter per ogni statistica
    //TIMMY
    public int getPartiteGiocateTimmy() { return partiteGiocateTimmy; }
    public int getPartiteVinteTimmy() { return partiteVinteTimmy; }
    public int getPartitePerseTimmy() { return partitePerseTimmy; }
    public int getLivelloTimmy() {
        return livelloTimmy;
    }
    //BRODIE
    public int getPartiteGiocateBrodie() { return partiteGiocateBrodie; }
    public int getPartiteVinteBrodie() { return partiteVinteBrodie; }
    public int getPartitePerseBrodie() { return partitePerseBrodie; }
    public int getLivelloBrodie() {
        return livelloBrodie;
    }
    //DUMBO
    public int getPartiteGiocateDumbo() { return partiteGiocateDumbo; }
    public int getPartiteVinteDumbo() { return partiteVinteDumbo; }

    public int getPartitePerseDumbo() { return partitePerseDumbo; }
    public int getLivelloDumbo() {
        return livelloDumbo;
    }

}
