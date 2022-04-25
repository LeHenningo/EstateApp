package de.dis.data;

public abstract class Estate {

    private int id = -1;
    private String city;
    private int postalcode;
    private String street;
    private int streetnumber;
    private int squarearea;
    private EstateAgent estateAgent;

    public int getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public int getPostalcode() {
        return postalcode;
    }

    public String getStreet() {
        return street;
    }

    public int getStreetnumber() {
        return streetnumber;
    }

    public int getSquarearea() {
        return squarearea;
    }

    public EstateAgent getEstateAgent() {
        return estateAgent;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPostalcode(int postalcode) {
        this.postalcode = postalcode;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setStreetnumber(int streetnumber) {
        this.streetnumber = streetnumber;
    }

    public void setSquarearea(int squarearea) {
        this.squarearea = squarearea;
    }

    public void setEstateAgent(EstateAgent estateAgent) {
        this.estateAgent = estateAgent;
    }
}
