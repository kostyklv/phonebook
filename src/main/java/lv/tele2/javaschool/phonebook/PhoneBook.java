package lv.tele2.javaschool.phonebook;

import asg.cliche.Command;
import asg.cliche.Param;

import java.util.ArrayList;
import java.util.List;

public class PhoneBook {
    private List<Record> recordList = new ArrayList<>();

    @Command
    public void create1(String  name,  String email, String phone) {
        Record r = new Record (name,email, phone);
//        r.setName(name);
//        r.setPhone(phone);
//        r.setEmail(email);
        recordList.add(r);
    }

    @Command
    public void create(String  name, String email,  String... phone) {
        Record r = new Record (name,email, phone);

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






    @Command
    public List<Record> list(){
        return recordList;
    }
}
