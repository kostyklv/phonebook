package lv.tele2.javaschool.phonebook;

import asg.cliche.Command;
import asg.cliche.Param;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
            @Param(name = "phone", description = "Phone number") String... phones) {
        Record r = new Record (name,email, phones);

//        }
//        Record r = new Record (name,phone,email);
//        r.setName(name);
//        r.setPhone(phone);
//        r.setEmail(email);
        recordList.add(r);
    }

    @Command
    public void addPhone(int id, String Phone) {

    }






    @Command(abbrev = "l", name = "list", description = "Lists all records")
    public List<Record> list(){
        return recordList;
    }
}
