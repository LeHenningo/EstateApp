package de.dis;

public class EstateService {

    public static void showEstateMenu() {
        //Menüoptionen
        final int NEW_House = 0;
        final int UPDATE_House = 1;
        final int DELETE_House = 2;
        final int NEW_Apartment = 3;
        final int UPDATE_Apartment = 4;
        final int DELETE_Apartment = 5;
        final int BACK = 6;

        //Maklerverwaltungsmenü
        Menu maklerMenu = new Menu("Estate-Management");
        maklerMenu.addEntry("NEW_House", NEW_House);
        maklerMenu.addEntry("UPDATE_House", UPDATE_House);
        maklerMenu.addEntry("DELETE_House", DELETE_House);
        maklerMenu.addEntry("NEW_Apartment", NEW_Apartment);
        maklerMenu.addEntry("UPDATE_Apartment", UPDATE_Apartment);
        maklerMenu.addEntry("DELETE_Apartment", DELETE_Apartment);

        maklerMenu.addEntry("Zurück zum Hauptmenü", BACK);

        //Verarbeite Eingabe
        while (true) {
            int response = maklerMenu.show();

            switch (response) {
                case NEW_House:
                    HouseService.newHouse();
                    break;
                case UPDATE_House:
                    HouseService.updateHouse();
                    break;
                case DELETE_House:
                    HouseService.deleteHouse();
                    break;
                case NEW_Apartment:
                    ApartmentService.newApartment();
                    break;
                case UPDATE_Apartment:
                    ApartmentService.updateApartment();
                    break;
                case DELETE_Apartment:
                    ApartmentService.deleteApartment();
                    break;
                case BACK:
                    return;
            }
        }


    }





}
