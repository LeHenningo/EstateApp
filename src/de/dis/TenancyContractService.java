package de.dis;

import de.dis.data.*;

import java.time.LocalDate;
import java.util.Objects;

public class TenancyContractService {


    public static void createTenancyContract() {

        TenancyContract tenancyContract = new TenancyContract();

        System.out.println("Wähle Sie das zu vermietende Apartement");
        for (Apartment apartment : Objects.requireNonNull(Apartment.loadAll())
        ) {
            System.out.println(apartment.getId() + " " + apartment.getStreet() + " " + apartment.getStreetnumber());
        }

        Integer apartmentId = FormUtil.readInt("Id");
        tenancyContract.setApartmentId(apartmentId);

        System.out.println("Wählen Sie die mietende Person");
        for (Person person : Objects.requireNonNull(Person.loadAll())
        ) {
            System.out.println(person.getId() + " " + person.getFirstname() + " " + person.getName());
        }

        Integer personId = FormUtil.readInt("Id");
        tenancyContract.setPersonId(personId);

        tenancyContract.setStartdate(LocalDate.parse( FormUtil.readString("Startdatum (Format: \"2018-05-05\")")));
        tenancyContract.setDuration(FormUtil.readFloat("Dauer in Monaten"));
        tenancyContract.setAdditionalCost(FormUtil.readFloat( "Zusätzliche Kosten in Euro"));
        tenancyContract.setPlace(FormUtil.readString("Ort"));
        tenancyContract.setDate(LocalDate.parse( FormUtil.readString("Vertragsdatum (Format: \"2018-05-05\")")));

        tenancyContract.create();

        System.out.println("Mietvertrag erstellt");

    }


}
