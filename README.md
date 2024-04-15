# JTrash
JTrash è un'applicazione Java dedicata al gioco di carte chiamato "Trash". Attraverso un'interfaccia grafica realizzata con Java Swing, il gioco permette agli utenti di immergersi in un'esperienza interattiva.

Specifiche Tecniche e Funzionalità:

Gestione del Profilo Utente: Gli utenti possono personalizzare i loro profili tramite l'impostazione di un nickname e di un avatar. Il sistema tiene traccia delle partite giocate, vinte e perse, nonché del livello di abilità dell'utente.

Modalità di Gioco: JTrash supporta partite che coinvolgono un giocatore umano contro uno, due o tre giocatori artificiali, simulati da sofisticati algoritmi di intelligenza artificiale.

Architettura Software: Il gioco è progettato seguendo il modello MVC (Model-View-Controller) per una chiara separazione delle logiche di presentazione, di business e di dati. Utilizza inoltre il pattern Observer-Observable per gestire le interazioni tra i vari componenti del software senza accoppiamenti stretti.

Interfaccia Utente Grafica: L'uso di Java Swing o JavaFX garantisce una grafica pulita e reattiva, fondamentale per le animazioni e gli effetti visivi.

Utilizzo di Stream: L'implementazione sfrutta gli stream di Java per gestire le operazioni sui dati in modo più efficiente ed espressivo.

Audio: JTrash include un sistema di gestione audio, definito nella classe AudioManager.Java, che riproduce vari campioni audio durante il gioco, arricchendo l'esperienza utente con suoni ambientali e feedback sonori.

Animazioni ed Effetti Speciali: Anche se limitati, il gioco include diverse animazioni ed effetti speciali che rendono l'interazione più dinamica e coinvolgente
