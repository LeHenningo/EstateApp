package de.dis.data;
import java.util.Date;

public class PurchaseContract {

    private int id = -1;
    private String place;
    private int noOfInstallments;
    private Person person;
    private House house;
    private Date date;
    private float interestrate;

    public int getId() {
        return id;
    }

    public String getPlace() {
        return place;
    }

    public int getNoOfInstallments() {
        return noOfInstallments;
    }

    public Person getPerson() {
        return person;
    }

    public House getHouse() {
        return house;
    }

    public Date getDate() {
        return date;
    }

    public float getInterestrate() {
        return interestrate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setNoOfInstallments(int noOfInstallments) {
        this.noOfInstallments = noOfInstallments;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setInterestrate(float interestrate) {
        this.interestrate = interestrate;
    }
}
