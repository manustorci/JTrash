package Model;

import java.io.*;
import java.util.*;
public class Scoreboard implements Observer {
    private List<UserProfile> userProfiles; //
    private Map<String, UserProfile> userProfileMap = new HashMap<>();
    private String filePath = "/Users/emanuelestorci/Documents/java/JTrash/src/Model/statistiche.txt";
    public Scoreboard() {
        this.userProfiles = new ArrayList<>();
        // Popola la mappa iniziale e la lista con i profili esistenti
        UserProfile timmy = new UserProfile("Timmy");
        UserProfile dumbo = new UserProfile("Dumbo");
        UserProfile brodie = new UserProfile("Brodie");

        // Aggiungi il Scoreboard come osservatore di ciascun UserProfile
        timmy.addObserver(this);
        dumbo.addObserver(this);
        brodie.addObserver(this);

        // Aggiungi gli UserProfile alla mappa e alla lista
        userProfileMap.put("Timmy", timmy);
        userProfileMap.put("Dumbo", dumbo);
        userProfileMap.put("Brodie", brodie);

        userProfiles.add(timmy);
        userProfiles.add(dumbo);
        userProfiles.add(brodie);

        // Inizializza i profili utente dalla lettura del file
        initializeUserProfilesFromFile();
    }
    private void initializeUserProfilesFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.lines() // trasforma le linee in uno stream
                    .map(line -> line.split(" \\| ")) // mappa ogni linea in un array di parti
                    .filter(parts -> parts.length == 3) // filtra solo le linee corrette
                    .forEach(parts -> { // elabora ogni parte
                        String initial = parts[0].substring(0, 1);
                        int gamesPlayed = Integer.parseInt(parts[0].split(":")[1].trim());
                        int gamesWon = Integer.parseInt(parts[1].split(":")[1].trim());
                        int gamesLost = Integer.parseInt(parts[2].split(":")[1].trim());

                        // recupera l'utente corrispondente e aggiorna i suoi dati
                        Optional.ofNullable(userProfileMap.get(nicknameFromInitial(initial)))
                                .ifPresent(userProfile -> {
                                    userProfile.setGamesPlayed(gamesPlayed);
                                    userProfile.setGamesWon(gamesWon);
                                    userProfile.setGamesLost(gamesLost);
                                });
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String nicknameFromInitial(String initial) {
        switch (initial) {
            case "T": return "Timmy";
            case "D": return "Dumbo";
            case "B": return "Brodie";
            default: return null; // se non trova un profilo corrispondente
        }
    }
    @Override
    public void update() {
        System.out.println("Aggiornamento delle statistiche in corso...");

        // Apertura del file per la scrittura delle nuove statistiche.
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Map.Entry<String, UserProfile> entry : userProfileMap.entrySet()) {
                UserProfile userProfile = entry.getValue();
                // Usa l'iniziale del nome utente per la scrittura delle nuove statistiche.
                String initial = entry.getKey().substring(0, 1).toUpperCase();
                writer.write(initial + " partite giocate:" + userProfile.getGamesPlayed() +
                        " | partite vinte:" + userProfile.getGamesWon() +
                        " | partite perse:" + userProfile.getGamesLost());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<UserProfile> getUserProfiles() {
        return new ArrayList<>(userProfileMap.values());
    }
}
