package de.dis;

import de.dis.data.PurchaseContract;
import de.dis.data.TenancyContract;

import java.util.Objects;

import static de.dis.PersonService.newPerson;

public class ContractManagementService {

    /**
     * Zeigt die Vertrgasverwaltung
     */
    public static void showContractManagementMenu() {


        //Menüoptionen
        final int NEW_PERSON = 0;
        final int NEW_CONTRACT = 1;
        final int SHOW_CONTRACTS = 2;
        final int BACK = 3;

        //Maklerverwaltungsmenü
        Menu menu = new Menu("Vertragsverwaltung");
        menu.addEntry("Person erstellen", NEW_PERSON);
        menu.addEntry("Vertrag erstellen", NEW_CONTRACT);
        menu.addEntry("Verträge anzeigen", SHOW_CONTRACTS);

        //Verarbeite Eingabe
        while (true) {
            int response = menu.show();

            switch (response) {
                case NEW_PERSON:
                    newPerson();
                    break;
                case NEW_CONTRACT:
                    newContract();
                    break;
                case SHOW_CONTRACTS:
                    showAllContracts();
                    break;
                case BACK:
                    return;
            }

        }

    }

    private static void newContract() {
        //Menüoptionen
        final int HOUSE = 0;
        final int APARTMENT = 1;
        final int BACK = 2;

        // Select Apartement or House
        Menu menu = new Menu("Typ wählen");
        menu.addEntry("Kaufvertrag Haus", HOUSE);
        menu.addEntry("Mietvertrag Apartment", APARTMENT);
        menu.addEntry("Zurück", BACK);

        //Verarbeite Eingabe
        while (true) {
            int response = menu.show();

            switch (response) {
                case APARTMENT:
                    TenancyContractService.createTenancyContract();
                    break;
                case HOUSE:
                    PurchaseContractService.createPurchaseContract();
                case BACK:
                    return;
            }

        }
    }

    private static void showAllContracts() {
        System.out.println("Kaufverträge");
        System.out.println("HausId | PersonId | Datum");
        for (PurchaseContract contract : Objects.requireNonNull(PurchaseContract.loadAll())
        ) {
            System.out.println(contract.getHouseId() + " | " + contract.getPersonId() + " | " + contract.getDate().toString());
        }

        System.out.println("Mietverträge");
        System.out.println("ApartementId | PersonId | Datum");
        for (TenancyContract contract : Objects.requireNonNull(TenancyContract.loadAll())
        ) {
            System.out.println(contract.getApartmentId() + " | " + contract.getPersonId() + " | " + contract.getDate().toString());
        }
    }

}
