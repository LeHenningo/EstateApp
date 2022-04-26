package de.dis.data;

import java.sql.*;
import java.util.ArrayList;

public class House extends Estate{

private int floors;
private float price;
private Boolean garden;

    public int getFloors() {
        return floors;
    }

    public float getPrice() {
        return price;
    }

    public Boolean getGarden() {
        return garden;
    }

    public void setFloors(int floors) {
        this.floors = floors;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setGarden(Boolean garden) {
        this.garden = garden;
    }


    public static House load(int id) {
        try {
            // Hole Verbindung
            Connection con = DbConnectionManager.getInstance().getConnection();

            // Erzeuge Anfrage
            String selectSQL = "SELECT * FROM \"house\" WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(selectSQL);
            pstmt.setInt(1, id);

            // Führe Anfrage aus
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {

                House ts = getDataFromResultSet(rs);
                rs.close();
                pstmt.close();
                return ts;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Lädt alle estateAgent aus der Datenbank
     * @return estateAgent-Instanzen
     */
    public static ArrayList<House> loadAll() {
        try {
            // Hole Verbindung
            Connection con = DbConnectionManager.getInstance().getConnection();

            ArrayList<House> houses = new ArrayList<>();

            // Erzeuge Anfrage
            String selectSQL = "SELECT * FROM \"house\" ";
            PreparedStatement pstmt = con.prepareStatement(selectSQL);

            // Führe Anfrage aus
            ResultSet rs = pstmt.executeQuery();
            while (rs.next())
            {
                House ts = getDataFromResultSet(rs);

                houses.add(ts);
            }
            rs.close();
            pstmt.close();
            return houses;
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
                String insertSQL = "INSERT INTO \"house\"(city, postalcode, street, streetnumber, squarearea, floors, price, garden, estateagent) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

                PreparedStatement pstmt = con.prepareStatement(insertSQL,
                        Statement.RETURN_GENERATED_KEYS);

                // Setze Anfrageparameter und fC<hre Anfrage aus
                pstmt.setString(1, getCity());
                pstmt.setInt(2, getPostalcode());
                pstmt.setString(3, getStreet());
                pstmt.setInt(4, getStreetnumber());
                pstmt.setInt(5, getSquarearea());
                pstmt.setInt(6, getFloors());
                pstmt.setFloat(7, getPrice());
                pstmt.setBoolean(8, getGarden());
                pstmt.setInt(9, getEstateAgent().getId());
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
                String updateSQL = "UPDATE \"house\" SET city = ?, postalcode = ?, street = ?, streetnumber = ?, squarearea = ?, floors = ?, price = ?, garden = ?, estateagent = ?  WHERE id = ?";
                PreparedStatement pstmt = con.prepareStatement(updateSQL);

                // Setze Anfrage Parameter
                pstmt.setString(1, getCity());
                pstmt.setInt(2, getPostalcode());
                pstmt.setString(3, getStreet());
                pstmt.setInt(4, getStreetnumber());
                pstmt.setInt(5, getSquarearea());
                pstmt.setInt(6, getFloors());
                pstmt.setFloat(7, getPrice());
                pstmt.setBoolean(8, getGarden());
                pstmt.setInt(9, getEstateAgent().getId());
                pstmt.setInt(10, getId());
                pstmt.executeUpdate();

                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete(int id) {
        try {
            // Hole Verbindung
            Connection con = DbConnectionManager.getInstance().getConnection();

            // Erzeuge Anfrage
            String selectSQL = "DELETE FROM \"house\" WHERE id = ?";
            PreparedStatement pstmt = con.prepareStatement(selectSQL);
            pstmt.setInt(1, id);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    public static House getDataFromResultSet(ResultSet rs){
        try {
            House ts = new House();
            ts.setId(rs.getInt("id"));
            ts.setCity(rs.getString("city"));
            ts.setStreet(rs.getString("street"));
            ts.setPostalcode(rs.getInt("postalcode"));
            ts.setSquarearea(rs.getInt("squarearea"));
            ts.setEstateAgent(EstateAgent.load(rs.getInt("estateagent")) );

            ts.setPrice(rs.getFloat("price"));
            ts.setGarden(rs.getBoolean("garden"));
            ts.setFloors(rs.getInt("floors"));

            return ts;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
