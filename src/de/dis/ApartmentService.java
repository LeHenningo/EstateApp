package de.dis;

import de.dis.data.EstateAgent;
import de.dis.data.Apartment;
import de.dis.data.House;

import java.util.Objects;

public class ApartmentService {
    public static void newApartment() {
        Apartment m = new Apartment();

        m.setCity(FormUtil.readString("City"));
        m.setPostalcode(FormUtil.readInt("Postalcode"));
        m.setStreet(FormUtil.readString("Street"));
        m.setStreetnumber(FormUtil.readInt("Streetnumber"));
        m.setSquarearea(FormUtil.readInt("Squarearea"));
        m.setFloor(FormUtil.readInt("Floor"));
        m.setRent(FormUtil.readFloat("Rent"));
        m.setRooms(FormUtil.readFloat("Rooms"));
        m.setBalcony(FormUtil.readBoolean("Balcony"));
        m.setBuiltInKitchen(FormUtil.readBoolean("BuiltInKitchen"));
        m.setEstateAgent(EstateAgent.load(FormUtil.readInt("EstateAgentID")));

        m.save();

        System.out.println("Apartment mit der ID " + m.getId() + " wurde erzeugt.");
    }

    public static void updateApartment() {

        System.out.println("Wähle Sie das zu aktualisierende Apartment");
        for (Apartment apartment : Objects.requireNonNull(Apartment.loadAll())
        ) {
            System.out.println(apartment.getId() + " " + apartment.getStreet() + " " + apartment.getStreetnumber());
        }

        Integer id = FormUtil.readInt("Id: ");

        try {
            Apartment selectedApartment = Apartment.load(id);
            if (selectedApartment != null) {
                System.out.println("Gewähltes Haus: " + selectedApartment.getStreet() + " " + selectedApartment.getStreetnumber());
                System.out.println("Welches Attribut soll aktualisiert werden?");
                //Menüoptionen
                final int CITY = 0;
                final int POSTALCODE = 1;
                final int STREET = 2;
                final int STREETNUMBER = 3;
                final int SQUAREAREA = 4;
                final int FLOOR = 5;
                final int RENT = 6;
                final int ROOMS = 7;
                final int BALCONY = 8;
                final int BUILTINKITCHEN = 9;
                final int ESTATEAGENT = 10 ;

                Menu updateHouseMenu = new Menu("Attribute");
                updateHouseMenu.addEntry("City", CITY);
                updateHouseMenu.addEntry("Postalcode", POSTALCODE);
                updateHouseMenu.addEntry("Street", STREET);
                updateHouseMenu.addEntry("Streetnumber", STREETNUMBER);
                updateHouseMenu.addEntry("Squarearea", SQUAREAREA);
                updateHouseMenu.addEntry("Floor", FLOOR);
                updateHouseMenu.addEntry("Rent", RENT);
                updateHouseMenu.addEntry("Rooms", ROOMS);
                updateHouseMenu.addEntry("Balcony", BALCONY);
                updateHouseMenu.addEntry("BuiltInKitchen", BUILTINKITCHEN);
                updateHouseMenu.addEntry("EstateAgent", ESTATEAGENT);

                int response = updateHouseMenu.show();

                switch (response) {
                    case CITY:
                        String updatedCity = FormUtil.readString("Neue Stadt");
                        selectedApartment.setCity(updatedCity);
                        break;
                    case POSTALCODE:
                        int updatedPostalcode = FormUtil.readInt("Neuer Postalcode");
                        selectedApartment.setPostalcode(updatedPostalcode);
                        break;
                    case STREET:
                        String updatedStreet = FormUtil.readString("Neue Street");
                        selectedApartment.setStreet(updatedStreet);
                        break;
                    case STREETNUMBER:
                        int updatedStreetnumber = FormUtil.readInt("Neue Streenumber");
                        selectedApartment.setStreetnumber(updatedStreetnumber);
                        break;
                    case SQUAREAREA:
                        int updatedSquareArea = FormUtil.readInt("Neue Squarearea");
                        selectedApartment.setStreetnumber(updatedSquareArea);
                        break;
                    case FLOOR:
                        int updatedFloor = FormUtil.readInt("Neue Floor");
                        selectedApartment.setFloor(updatedFloor);
                        break;
                    case RENT:
                        Float updatedRent = FormUtil.readFloat("Neue Rent");
                        selectedApartment.setRent(updatedRent);
                        break;
                    case ROOMS:
                        Float updatedRooms = FormUtil.readFloat("Neuer Rooms");
                        selectedApartment.setRooms(updatedRooms);
                        break;
                    case BALCONY:
                        Boolean updatedBalcony = FormUtil.readBoolean("Neuer Balcony");
                        selectedApartment.setBalcony(updatedBalcony);
                        break;
                    case BUILTINKITCHEN:
                        Boolean updatedKitchen = FormUtil.readBoolean("Neuer Kitchen");
                        selectedApartment.setBalcony(updatedKitchen);
                        break;
                    case ESTATEAGENT:
                        int updatedAgent = FormUtil.readInt("Neuer Agent");
                        selectedApartment.setEstateAgent(EstateAgent.load(updatedAgent));
                        break;

                }

                selectedApartment.save();
            }
        } catch (Exception e) {
            System.out.println("Error at selecting Apartment " + e.getMessage());
        }


    }

    public static void deleteApartment() {

        System.out.println("Wähle den zu löschenden Apartment");
        for (Apartment apartment : Objects.requireNonNull(Apartment.loadAll())
        ) {
            System.out.println(apartment.getId() + " " + apartment.getStreet() + " " + apartment.getStreetnumber());
        }

        Integer id = FormUtil.readInt("Gewählte Id");

        Apartment.delete(id);
    }


}
