package de.dis;

import de.dis.data.EstateAgent;
import de.dis.data.House;

import java.util.Objects;

public class HouseService {
    /**
     * Legt einen neuen Makler an, nachdem der Benutzer
     * die entprechenden Daten eingegeben hat.
     */
    public static void newHouse() {

        House m = new House();

        m.setCity(FormUtil.readString("City"));
        m.setPostalcode(FormUtil.readInt("Postalcode"));
        m.setStreet(FormUtil.readString("Street"));
        m.setStreetnumber(FormUtil.readInt("Streetnumber"));
        m.setSquarearea(FormUtil.readInt("Squarearea"));
        m.setFloors(FormUtil.readInt("Floors"));
        m.setPrice(FormUtil.readFloat("Price"));
        m.setGarden(FormUtil.readBoolean("Garden"));
        m.setEstateAgent(EstateAgent.load(FormUtil.readInt("EstateAgentID")));

        m.save();

        System.out.println("House mit der ID " + m.getId() + " wurde erzeugt.");
    }

    public static void updateHouse() {

        System.out.println("Wähle den zu aktualisierendes House");
        for (House house : Objects.requireNonNull(House.loadAll())
        ) {
            System.out.println(house.getId() + " " + house.getStreet() + " " + house.getStreetnumber());
        }

        Integer id = FormUtil.readInt("Id: ");

        try {
            House selectedHouse = House.load(id);
            if (selectedHouse != null) {

                System.out.println("Gewähltes Haus: " + selectedHouse.getStreet() + " " + selectedHouse.getStreetnumber());
                System.out.println("Welches Attribut soll aktualisiert werden?");
                //Menüoptionen
                final int CITY = 0;
                final int POSTALCODE = 1;
                final int STREET = 2;
                final int STREETNUMBER = 3;
                final int SQUAREAREA = 4;
                final int PRICE = 5;
                final int GARDEN = 6;
                final int ESTATEAGENT = 7;

                Menu updateHouseMenu = new Menu("Attribute");
                updateHouseMenu.addEntry("City", CITY);
                updateHouseMenu.addEntry("Postalcode", POSTALCODE);
                updateHouseMenu.addEntry("Street", STREET);
                updateHouseMenu.addEntry("Streetnumber", STREETNUMBER);
                updateHouseMenu.addEntry("Squarearea", SQUAREAREA);
                updateHouseMenu.addEntry("Price", PRICE);
                updateHouseMenu.addEntry("Garden", GARDEN);
                updateHouseMenu.addEntry("EstateAgent", ESTATEAGENT);

                int response = updateHouseMenu.show();

                switch (response) {
                    case CITY:
                        String updatedCity = FormUtil.readString("Neue Stadt");
                        selectedHouse.setCity(updatedCity);
                        break;
                    case POSTALCODE:
                        int updatedPostalcode = FormUtil.readInt("Neuer Postalcode");
                        selectedHouse.setPostalcode(updatedPostalcode);
                        break;
                    case STREET:
                        String updatedStreet = FormUtil.readString("Neue Street");
                        selectedHouse.setStreet(updatedStreet);
                        break;
                    case STREETNUMBER:
                        int updatedStreetnumber = FormUtil.readInt("Neue Streenumber");
                        selectedHouse.setStreetnumber(updatedStreetnumber);
                        break;
                    case SQUAREAREA:
                        int updatedSquareArea = FormUtil.readInt("Neue Squarearea");
                        selectedHouse.setStreetnumber(updatedSquareArea);
                        break;
                    case PRICE:
                        float updatedPrice = FormUtil.readFloat("Neue Price");
                        selectedHouse.setPrice(updatedPrice);
                        break;
                    case GARDEN:
                        Boolean updatedGarden = FormUtil.readBoolean("Neue Garden");
                        selectedHouse.setGarden(updatedGarden);
                        break;
                    case ESTATEAGENT:
                        int updatedEsateAgent = FormUtil.readInt("Neuer EstateAgentID");
                        selectedHouse.setEstateAgent(EstateAgent.load(updatedEsateAgent));
                        break;

                }

                selectedHouse.save();
            }
        } catch (Exception e) {
            System.out.println("Error at selecting House " + e.getMessage());
        }

    }

    public static void deleteHouse() {

        System.out.println("Wähle den zu löschenden House");
        for (House house : Objects.requireNonNull(House.loadAll())
        ) {
            System.out.println(house.getId() + " " + house.getStreet() + " " + house.getStreetnumber());
        }

        Integer id = FormUtil.readInt("Gewählte Id");

        House.delete(id);
    }






}
