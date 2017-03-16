package lv.tele2.javaschool.phonebook;

import asg.cliche.Command;
import asg.cliche.Param;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PhoneBook implements Serializable {
    private static final long serialVersionUID=1L;
    private List<Record> recordList = new ArrayList<>();

    @Command
    public void create1(String  name,  String email, String phone) {
        Record r = new Record (name,email, phone);
//        r.setName(name);
//        r.setPhone(phone);
//        r.setEmail(email);
        recordList.add(r);
    }

    @Command(abbrev = "c", name = "create", description = "Creates new record")
    public void create(
            @Param(name = "name", description = "Record's name") String  name,
            @Param(name = "mail", description = "Record's e-mail") String email,
            @Param(name = "phone", description = "Phone number") String... phones) throws SQLException {
        Record r = new Record (name,email, phones);
        //        recordList.add(r);
        r.saveDB();


//        }
//        Record r = new Record (name,phone,email);
//        r.setName(name);
//        r.setPhone(phone);
//        r.setEmail(email);


    }

    @Command

    public void addPhone(int id, String Phone) {

    }

    public void deleteRecord(int id, String name) {
        for (Record r : recordList)
        {
            if (r.getId()==id && r.getName().equals (name)) {
                System.out.println("Will be removed:");
                System.out.println(r);
                recordList.remove(r);
                break;
            }
        }
    }


    @Command
    public Record generateMany(){
        JSONObject obj = callNameFake();
        String name=obj.getString("name");
        System.out.println(name);
        String email=obj.getString("email_u")+"@"+obj.getString("email_d");
        System.out.println(email);
        String phone=obj.getString("phone_h");
        System.out.println(phone);

        Record r = new Record (name, email, phone);
        save(r);
        return r;
    }

    @Command
    public void generateMany(int counter) throws SQLException {
        for (int i=1; i<=counter; i++) {
            System.out.println("New record:");
            JSONObject obj = callNameFake();
            String name = obj.getString("name");
            System.out.println(name);
            String email = obj.getString("email_u") + "@" + obj.getString("email_d");
            System.out.println(email);
            String phone = obj.getString("phone_h");
            System.out.println(phone);
            create(name, email, phone);
        }
    }


    public void save(Record r) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Add to phonebook? (Y):");
        String ask = scanner.nextLine();
        if (ask.equals("Y")) {
            recordList.add(r);
        }
    }



    private JSONObject callNameFake() {
        try {
            URL url= new URL ("http://api.namefake.com/english-uk/random");
            try (InputStream is = url.openStream()) {
                JSONTokener t = new JSONTokener(is);
                JSONObject obj = new JSONObject(t);
                return obj;
            }
        } catch (IOException e) {
            return null;
        }
    }



    @Command(abbrev = "l", name = "list", description = "Lists all records")
    public List<Record> list() throws SQLException{

//        System.out.println("version="+serialVersionUID);
//        System.out.println("Max="+recordList.size());

        return Record.findAll();
    }

}
