package de.dis.data;

import java.sql.*;
import java.util.ArrayList;

public class Apartment extends Estate{

    private int floor;
    private float rent;
    private float rooms;
    private Boolean balcony;
    private Boolean builtInKitchen;


    public int getFloor() {
        return floor;
    }
    public float getRent() {
        return rent;
    }

    public float getRooms() {
        return rooms;
    }

    public Boolean getBalcony() {
        return balcony;
    }

    public Boolean getBuiltInKitchen() {
        return builtInKitchen;
    }


    public void setFloor(int floor) {
        this.floor = floor;
    }

    public void setRent(float rent) {
        this.rent = rent;
    }

    public void setRooms(float rooms) {
        this.rooms = rooms;
    }

    public void setBalcony(Boolean balcony) {
        this.balcony = balcony;
    }

    public void setBuiltInKitchen(Boolean builtInKitchen) {
        this.builtInKitchen = builtInKitchen;
    }

    public static Apartment load(int id) {
        try {
            // Hole Verbindung
            Connection con = DbConnectionManager.getInstance().getConnection();

            // Erzeuge Anfrage
            String selectSQL = "SELECT * FROM \"apartment\" WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(selectSQL);
            pstmt.setInt(1, id);

            // Führe Anfrage aus
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Apartment ts = new Apartment();
                getDataFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Apartment> loadAll() {
        try {
            // Hole Verbindung
            Connection con = DbConnectionManager.getInstance().getConnection();

            ArrayList<Apartment> apartments = new ArrayList<>();

            // Erzeuge Anfrage
            String selectSQL = "SELECT * FROM \"apartment\" ";
            PreparedStatement pstmt = con.prepareStatement(selectSQL);

            // Führe Anfrage aus
            ResultSet rs = pstmt.executeQuery();
            while (rs.next())
            {
                Apartment ts = getDataFromResultSet(rs);
                apartments.add(ts);
            }
            rs.close();
            pstmt.close();
            return apartments;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void save() {
        // Hole Verbindung
        Connection con = DbConnectionManager.getInstance().getConnection();

        try {
            // FC<ge neues Element hinzu, wenn das Objekt noch keine ID hat.
            if (getId() == -1) {
                // Achtung, hier wird noch ein Parameter mitgegeben,
                // damit spC$ter generierte IDs zurC<ckgeliefert werden!
                String insertSQL = "INSERT INTO \"apartment\"(city, postalcode, street, streetnumber, squarearea, floor, rent, rooms, balcony, builtInKitchen, estateagent) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?))";

                PreparedStatement pstmt = con.prepareStatement(insertSQL,
                        Statement.RETURN_GENERATED_KEYS);

                // Setze Anfrageparameter und fC<hre Anfrage aus
                pstmt.setString(1, getCity());
                pstmt.setInt(2, getPostalcode());
                pstmt.setString(3, getStreet());
                pstmt.setInt(4, getStreetnumber());
                pstmt.setInt(5, getSquarearea());
                pstmt.setInt(6, getFloor());
                pstmt.setFloat(7, getRent());
                pstmt.setFloat(8, getRooms());
                pstmt.setBoolean(9, getBalcony());
                pstmt.setBoolean(10, getBuiltInKitchen());
                pstmt.setInt(11, getEstateAgent().getId());
                pstmt.executeUpdate();

                // Hole die Id des engefC<gten Datensatzes
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    setId(rs.getInt(5));
                }

                rs.close();
                pstmt.close();
            } else {
                // Falls schon eine ID vorhanden ist, mache ein Update...
                String updateSQL = "UPDATE \"house\" SET city = ?, postalcode = ?, street = ?, streetnumber = ?, squarearea = ?, floor = ?, rent = ?, rooms = ?, balcony = ?, builtInKitchen = ?, estateagent = ?  WHERE id = ?";
                PreparedStatement pstmt = con.prepareStatement(updateSQL);

                // Setze Anfrage Parameter
                pstmt.setString(1, getCity());
                pstmt.setInt(2, getPostalcode());
                pstmt.setString(3, getStreet());
                pstmt.setInt(4, getStreetnumber());
                pstmt.setInt(5, getSquarearea());
                pstmt.setInt(6, getFloor());
                pstmt.setFloat(7, getRent());
                pstmt.setFloat(8, getRooms());
                pstmt.setBoolean(9, getBalcony());
                pstmt.setBoolean(10, getBuiltInKitchen());
                pstmt.setInt(10, getEstateAgent().getId());
                pstmt.setInt(11, getId());
                pstmt.executeUpdate();

                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static Apartment getDataFromResultSet(ResultSet rs){
        try {
            Apartment ts = new Apartment();
            ts.setId(rs.getInt("id"));
            ts.setCity(rs.getString("city"));
            ts.setStreet(rs.getString("street"));
            ts.setPostalcode(rs.getInt("postalcode"));
            ts.setSquarearea(rs.getInt("squarearea"));
            ts.setEstateAgent(EstateAgent.load(rs.getInt("estateagent")) );

            ts.setFloor(rs.getInt("floor"));
            ts.setRent(rs.getFloat("rent"));
            ts.setRooms(rs.getFloat("rooms"));
            ts.setBalcony(rs.getBoolean("balcony"));
            ts.setBuiltInKitchen(rs.getBoolean("builtinkitchen"));

            rs.close();

            return ts;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }









}
