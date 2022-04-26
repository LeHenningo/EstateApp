package de.dis.data;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class PurchaseContract {

    private int id = -1;
    private String place;
    private int noOfInstallments;
    private int personId;
    private int houseId;

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    private LocalDate date;
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

    public Integer getHouseId() {
        return houseId;
    }

    public LocalDate getDate() {
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

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setInterestrate(float interestrate) {
        this.interestrate = interestrate;
    }

    public void create() {
        Connection con = DbConnectionManager.getInstance().getConnection();

        try {
            String insertSQL = "INSERT INTO \"purchaseContract\"(place,noofinstallements,person,house,date,interestrate) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement pstmt = con.prepareStatement(insertSQL,
                    Statement.RETURN_GENERATED_KEYS);

            // Setze Anfrageparameter und führe Anfrage aus
            pstmt.setString(1, getPlace());
            pstmt.setInt(2, getNoOfInstallments());
            pstmt.setInt(3, getPersonId());
            pstmt.setInt(4, getHouseId());
            pstmt.setDate(5, java.sql.Date.valueOf(getDate()) );
            pstmt.setFloat(6, getInterestrate());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                setId(rs.getInt(5));
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lädt alle PurchaseContract aus der Datenbank
     * @return Personen-Instanzen
     */
    public static ArrayList<PurchaseContract> loadAll() {
        try {
            // Hole Verbindung
            Connection con = DbConnectionManager.getInstance().getConnection();

            ArrayList<PurchaseContract> purchaseContracts = new ArrayList<>();

            // Erzeuge Anfrage
            String selectSQL = "SELECT * FROM \"purchaseContract\" ";
            PreparedStatement pstmt = con.prepareStatement(selectSQL);

            // Führe Anfrage aus
            ResultSet rs = pstmt.executeQuery();
            while (rs.next())
            {
                try {
                    PurchaseContract purchaseContract = new PurchaseContract();
                    purchaseContract.setId(rs.getInt("id"));
                    purchaseContract.setPlace(rs.getString("place"));
                    purchaseContract.setPersonId(rs.getInt("person"));
                    purchaseContract.setHouseId(rs.getInt("house"));
                    purchaseContract.setNoOfInstallments(rs.getInt("noofinstallements"));
                    purchaseContract.setDate(rs.getDate("date").toLocalDate());
                    purchaseContract.setInterestrate(rs.getFloat("interestrate"));

                    purchaseContracts.add(purchaseContract);
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            rs.close();
            pstmt.close();
            return purchaseContracts;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
