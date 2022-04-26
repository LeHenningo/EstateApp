package de.dis.data;

import java.sql.*;
import java.util.ArrayList;


public class Person {

    private int id;
    private String firstname;
    private String name;
    private String address;

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void create() {
        Connection con = DbConnectionManager.getInstance().getConnection();

        try {
            // FC<ge neues Element hinzu, wenn das Objekt noch keine ID hat.
                // Achtung, hier wird noch ein Parameter mitgegeben,
                // damit spC$ter generierte IDs zurC<ckgeliefert werden!
                String insertSQL = "INSERT INTO \"person\"(firstname,name,address) VALUES (?, ?, ?)";

                PreparedStatement pstmt = con.prepareStatement(insertSQL,
                        Statement.RETURN_GENERATED_KEYS);

                // Setze Anfrageparameter und führe Anfrage aus
                pstmt.setString(1, getFirstname());
                pstmt.setString(2, getName());
                pstmt.setString(3, getAddress());
                pstmt.executeUpdate();

                // Hole die Id des engefC<gten Datensatzes
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
     * Lädt alle Personen aus der Datenbank
     * @return Personen-Instanzen
     */
    public static ArrayList<Person> loadAll() {
        try {
            // Hole Verbindung
            Connection con = DbConnectionManager.getInstance().getConnection();

            ArrayList<Person> people = new ArrayList<>();

            // Erzeuge Anfrage
            String selectSQL = "SELECT * FROM \"person\" ";
            PreparedStatement pstmt = con.prepareStatement(selectSQL);

            // Führe Anfrage aus
            ResultSet rs = pstmt.executeQuery();
            while (rs.next())
            {
                try {
                    Person person = new Person();
                    person.setId(rs.getInt("id"));
                    person.setName(rs.getString("name"));
                    person.setFirstname(rs.getString("firstname"));
                    person.setAddress(rs.getString("address"));

                    people.add(person);
                }
                catch (SQLException e) {
                    e.printStackTrace();
                }

            }
            rs.close();
            pstmt.close();
            return people;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
