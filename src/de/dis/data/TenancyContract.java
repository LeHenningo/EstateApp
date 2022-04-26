package de.dis.data;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class TenancyContract {

    private int id;
    private String place;
    private int personId;
    private int apartmentId;
    private LocalDate date;

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(int apartmentId) {
        this.apartmentId = apartmentId;
    }

    private LocalDate startdate;
    private float duration;
    private float additionalCost;

    public int getId() {
        return id;
    }

    public String getPlace() {
        return place;
    }


    public LocalDate getDate() {
        return date;
    }

    public LocalDate getStartdate() {
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

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setStartdate(LocalDate startdate) {
        this.startdate = startdate;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public void setAdditionalCost(float additionalCost) {
        this.additionalCost = additionalCost;
    }

    public void create() {
        Connection con = DbConnectionManager.getInstance().getConnection();

        try {
            String insertSQL = "INSERT INTO \"tenancyContract\"(place,person,apartment,date,startdate,duration,additionalcost) VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pstmt = con.prepareStatement(insertSQL,
                    Statement.RETURN_GENERATED_KEYS);

            // Setze Anfrageparameter und führe Anfrage aus
            pstmt.setString(1, getPlace());
            pstmt.setInt(2, getPersonId());
            pstmt.setInt(3, getApartmentId());
            pstmt.setDate(4, java.sql.Date.valueOf(getDate()) );
            pstmt.setDate(5, java.sql.Date.valueOf(getStartdate()) );
            pstmt.setFloat(6, getDuration());
            pstmt.setFloat(7, getAdditionalCost());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                setId(rs.getInt(4));
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lädt alle TenancyContract aus der Datenbank
     * @return Personen-Instanzen
     */
    public static ArrayList<TenancyContract> loadAll() {
        try {
            // Hole Verbindung
            Connection con = DbConnectionManager.getInstance().getConnection();

            ArrayList<TenancyContract> tenancyContracts = new ArrayList<>();

            // Erzeuge Anfrage
            String selectSQL = "SELECT * FROM \"tenancyContract\" ";
            PreparedStatement pstmt = con.prepareStatement(selectSQL);

            // Führe Anfrage aus
            ResultSet rs = pstmt.executeQuery();
            while (rs.next())
            {
                try {
                    TenancyContract tenancyContract = new TenancyContract();
                    tenancyContract.setId(rs.getInt("id"));
                    tenancyContract.setPlace(rs.getString("place"));
                    tenancyContract.setPersonId(rs.getInt("person"));
                    tenancyContract.setApartmentId(rs.getInt("apartment"));
                    tenancyContract.setDuration(rs.getFloat("duration"));
                    tenancyContract.setDate(rs.getDate("date").toLocalDate());
                    tenancyContract.setStartdate(rs.getDate("startdate").toLocalDate());
                    tenancyContract.setAdditionalCost(rs.getFloat("additionalcost"));

                    tenancyContracts.add(tenancyContract);
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            rs.close();
            pstmt.close();
            return tenancyContracts;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}








