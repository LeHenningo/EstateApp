package de.dis;

import de.dis.data.EstateAgent;

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
        final int QUIT = 1;

        //Erzeuge Menü
        Menu mainMenu = new Menu("Hauptmenü");
        mainMenu.addEntry("Makler-Verwaltung", MENU_MAKLER);
        mainMenu.addEntry("Beenden", QUIT);

        //Verarbeite Eingabe
        while (true) {
            int response = mainMenu.show();

            switch (response) {
                case MENU_MAKLER:
                    showMaklerMenu();
                    break;
                case QUIT:
                    return;
            }
        }
    }

    /**
     * Zeigt die Maklerverwaltung
     */
    public static void showMaklerMenu() {

        // Password to enter makler mode: test
        String password = FormUtil.readString("Enter Password for accessing Maklerverwaltung");
        if(Objects.equals(password, "test")) {

        //Menüoptionen
        final int NEW_MAKLER = 0;
        final int UPDATE_MAKLER = 1;
        final int DELETE_MAKLER = 2;
        final int BACK = 3;

        //Maklerverwaltungsmenü
        Menu maklerMenu = new Menu("Makler-Verwaltung");
        maklerMenu.addEntry("Neuer Makler", NEW_MAKLER);
        maklerMenu.addEntry("Makler aktualisieren", UPDATE_MAKLER);
        maklerMenu.addEntry("Makler löschen", DELETE_MAKLER);
        maklerMenu.addEntry("Zurück zum Hauptmenü", BACK);

        //Verarbeite Eingabe
        while (true) {
            int response = maklerMenu.show();

            switch (response) {
                case NEW_MAKLER:
                    newMakler();
                    break;
                case UPDATE_MAKLER:
                    updateMakler();
                    break;
                case DELETE_MAKLER:
                    deleteMakler();
                    break;
                case BACK:
                    return;
            }
        }

        } else {
            System.out.println("Falsches Passwort.");
            return;
        }

    }

    /**
     * Legt einen neuen Makler an, nachdem der Benutzer
     * die entprechenden Daten eingegeben hat.
     */
    public static void newMakler() {

        EstateAgent m = new EstateAgent();

        m.setName(FormUtil.readString("Name"));
        m.setAddress(FormUtil.readString("Adresse"));
        m.setLogin(FormUtil.readString("Login"));
        m.setPassword(FormUtil.readString("Passwort"));
        m.save();

        System.out.println("Makler mit der ID " + m.getId() + " wurde erzeugt.");
    }

    public static void updateMakler() {

        System.out.println("Wähle den zu aktualisierenden Makler");
        for (EstateAgent agent : Objects.requireNonNull(EstateAgent.loadAll())
        ) {
            System.out.println(agent.getId() + " " + agent.getName());
        }

        Integer id = FormUtil.readInt("Id");

        try {
            EstateAgent selectedAgent = EstateAgent.load(id);
            if (selectedAgent != null) {
                System.out.println("Gewählter Makler: " + selectedAgent.getName());
                System.out.println("Welches Attribut soll aktualisiert werden?");
                //Menüoptionen
                final int ADDRESS = 0;
                final int NAME = 1;
                final int LOGIN = 2;
                final int PASSWORD = 3;

                Menu updateMaklerMenu = new Menu("Attribute");
                updateMaklerMenu.addEntry("Adresse", ADDRESS);
                updateMaklerMenu.addEntry("Name", NAME);
                updateMaklerMenu.addEntry("Login", LOGIN);
                updateMaklerMenu.addEntry("Passwort", PASSWORD);

                int response = updateMaklerMenu.show();

                switch (response) {
                    case ADDRESS:
                        String updatedAddress = FormUtil.readString("Neue Adresse");
                        selectedAgent.setAddress(updatedAddress);
                        break;
                    case NAME:
                        String updatedName = FormUtil.readString("Neuer Name");
                        selectedAgent.setName(updatedName);
                        break;
                    case LOGIN:
                        String updatedLogin = FormUtil.readString("Neuer Nutzername");
                        selectedAgent.setLogin(updatedLogin);
                        break;
                    case PASSWORD:
                        String updatedPassword = FormUtil.readString("Neues Passwort");
                        selectedAgent.setLogin(updatedPassword);
                        break;
                }

                selectedAgent.save();
            }
        } catch (Exception e) {
           System.out.println("Error at selecting Makler " + e.getMessage());
        }

    }

    public static  void deleteMakler(){
        System.out.println("Wähle den zu löschenden Makler");
        for (EstateAgent agent : Objects.requireNonNull(EstateAgent.loadAll())
        ) {
            System.out.println(agent.getId() + " " + agent.getName());
        }

        Integer id = FormUtil.readInt("Gewählte Id");

        EstateAgent.delete(id);
    }
}
