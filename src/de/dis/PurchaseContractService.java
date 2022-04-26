package de.dis;

import de.dis.data.*;

import java.time.LocalDate;
import java.util.Objects;

public class PurchaseContractService {


    public static void createPurchaseContract() {

        PurchaseContract purchaseContract = new PurchaseContract();

        System.out.println("Wähle Sie das zu verkaufende Haus");
        for (House house : Objects.requireNonNull(House.loadAll())
        ) {
            System.out.println(house.getId() + " " + house.getStreet() + " " + house.getStreetnumber());
        }

        Integer houseId = FormUtil.readInt("Id");
        purchaseContract.setHouseId(houseId);

        System.out.println("Wählen Sie die kaufende Person");
        for (Person person : Objects.requireNonNull(Person.loadAll())
        ) {
            System.out.println(person.getId() + " " + person.getFirstname() + " " + person.getName());
        }

        Integer personId = FormUtil.readInt("Id");
        purchaseContract.setPersonId(personId);

        purchaseContract.setNoOfInstallments(FormUtil.readInt("Anzahl Installationen"));
        purchaseContract.setInterestrate(FormUtil.readFloat("Zinssatz"));
        purchaseContract.setDate(LocalDate.parse( FormUtil.readString("Datum (\"2018-05-05\")")));
        purchaseContract.setPlace(FormUtil.readString("Ort"));

        purchaseContract.create();

        System.out.println("Kaufvertrag erstellt");

    }


}
