package lv.tele2.javaschool.phonebook;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.sql.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;



public class Record implements Serializable {
    private static final long serialVersionUID=1L;
    private static int nextId;
    private int id;

    private String name;
 //   private String [] phone;
    private List<String> phoneList = new ArrayList<>();
    private String email;



    public Record (String name, String email, String... phones) {
        this(name, email, Arrays.asList(phones));
    }

    public Record (String name, String email, List<String> phones) {
        this();  //mist be first row!
        //this.id
        id=nextId;
        this.name=name;
        this.email=email;
        phoneList.addAll(phones);
    }

    public Record (){
       this.id=nextId;
       nextId++;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPhoneList() {
        return phoneList;
    }

   // public void setPhone(String... phone) {
   //     this.phone = phone;
   // }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        nextId = Math.max(id + 1, nextId);
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", name='" + name + '\'' +
 //               ", phone=" + Arrays.toString(phoneList) +
                ", phone=" + phoneList +
                ", email='" + email + '\'' +
                '}';
    }


    public void saveDB()throws SQLException {
//        this();
        Connection con = Main.getDatabase().getConnection();
        try (Statement stmt = con.createStatement()) {
            ResultSet rs =stmt.executeQuery("select max(id) from record");
            rs.next();
            int maxId = rs.getInt(1);
            id =maxId+1;
        }
        try (PreparedStatement stmt = con.prepareStatement("insert into record (id, name, mail) values (?,?,?)")){
             //PreparedStatement stmt2 = con.prepareStatement("insert into record (id, name, mail) values (?,?);"

                stmt.setInt(1, id);
                stmt.setString(2,name);
                stmt.setString(3,email);
//            stmt.executeUpdate("insert into record (id, name, mail) values ("+id+", '"+ name+"', '"+email+"')");
            stmt.execute();
             Statement stmt2 = con.createStatement();
            for (String p :phoneList) {
                stmt2.executeUpdate("insert into phone (record_id, phone) values ("+id+", '"+p+"')");
            }
        }
    }

    public static List<Record> findAll() throws SQLException {
        List<Record> result = new ArrayList<>();
        Connection con = Main.getDatabase().getConnection();
        try (Statement stmt = con.createStatement();
            ResultSet rs =  stmt.executeQuery("select * from record")){
            while (rs.next()){
                Record r=construct (rs);

                result.add(r);
            }
        }
        return result;
    }

    private static List<String> findPhones (int id) throws SQLException {
        List<String> result = new ArrayList<>();
        Connection con = Main.getDatabase().getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs =  stmt.executeQuery("select * from phone where record_id="+id+"")){
            while (rs.next()){
                String phone = rs.getString("phone");
                result.add(phone);
            }
        }
        return result;

    }

    private static Record construct (ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String email = rs.getString("mail");
        List<String> phone = findPhones(id);

        Record r = new Record (name, email, phone);
        r.id=id;
        return r;
    }
}