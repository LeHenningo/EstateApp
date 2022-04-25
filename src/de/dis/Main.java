package de.dis;

import de.dis.data.Estate;
import de.dis.data.EstateAgent;
import de.dis.data.House;

import java.text.Normalizer;
import java.util.Objects;

/**
 * Hauptklasse
 */
public class Main {
    /**
     * Startet die Anwendung
     */
    public static void main(String[] args) {
        showMainMenu();
    }

    /**
     * Zeigt das Hauptmenü
     */
    public static void showMainMenu() {
        //Menüoptionen
        final int MENU_MAKLER = 0;
        final int MENU_ESTATE = 2;
        final int QUIT = 1;

        //Erzeuge Menü
        Menu mainMenu = new Menu("Hauptmenü");
        mainMenu.addEntry("Makler-Verwaltung", MENU_MAKLER);
        mainMenu.addEntry("Estate-Management", MENU_ESTATE);
        mainMenu.addEntry("Beenden", QUIT);

        //Verarbeite Eingabe
        while (true) {
            int response = mainMenu.show();

            switch (response) {

                case MENU_MAKLER:
                    MaklerService.showMaklerMenu();
                    break;

                case MENU_ESTATE:
                    EstateService.showEstateMenu();
                    break;
                case QUIT:
                    return;
            }
        }
    }








}
