package de.dis.data;
import java.util.Date;

public class TenancyContract {

    private int id;
    private String place;
    private Person person;
    private Apartment apartment;
    private Date date;
    private Date startdate;
    private float duration;
    private float additionalCost;

    public int getId() {
        return id;
    }

    public String getPlace() {
        return place;
    }

    public Person getPerson() {
        return person;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public Date getDate() {
        return date;
    }

    public Date getStartdate() {
        return startdate;
    }

    public float getDuration() {
        return duration;
    }

    public float getAdditionalCost() {
        return additionalCost;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public void setAdditionalCost(float additionalCost) {
        this.additionalCost = additionalCost;
    }
}








